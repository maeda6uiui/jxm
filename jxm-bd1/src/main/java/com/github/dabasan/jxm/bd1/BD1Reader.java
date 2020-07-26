package com.github.dabasan.jxm.bd1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ejml.simple.SimpleMatrix;

import com.github.dabasan.jxm.bintools.ByteFunctions;

/**
 * BD1 reader
 * 
 * @author Daba
 *
 */
class BD1Reader {
	private Map<Integer, String> textureFilenames;
	private List<BD1Block> blocks;

	public BD1Reader(InputStream is) throws IOException, IndexOutOfBoundsException {
		textureFilenames = new HashMap<>();
		blocks = new ArrayList<>();

		// Read all bytes from a stream.
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0;

		// Texture filenames
		for (int i = 0; i < 10; i++) {
			byte[] textureFilenameBuffer = new byte[31];
			String textureFilename;
			int firstNullPos;

			for (int j = 0; j < 31; j++) {
				textureFilenameBuffer[j] = bin[pos];
				pos++;
			}
			textureFilename = new String(textureFilenameBuffer);

			firstNullPos = 30;
			for (int j = 0; j < 30; j++) {
				if (textureFilename.charAt(j) == '\0') {
					firstNullPos = j;
					break;
				}
			}

			textureFilename = textureFilename.substring(0, firstNullPos);
			textureFilename = textureFilename.replace('\\', '/');

			textureFilenames.put(i, textureFilename);
		}

		// Number of blocks
		int block_num = ByteFunctions.getUnsignedShortValueFromBinLE(bin, pos);
		pos += 2;

		// Blocks
		for (int i = 0; i < block_num; i++) {
			var block = new BD1Block();

			// Vertex positions
			float[] vertexPositionXs = new float[8];
			float[] vertexPositionYs = new float[8];
			float[] vertexPositionZs = new float[8];

			for (int j = 0; j < 8; j++) {
				vertexPositionXs[j] = ByteFunctions.getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 8; j++) {
				vertexPositionYs[j] = ByteFunctions.getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 8; j++) {
				vertexPositionZs[j] = ByteFunctions.getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}

			SimpleMatrix[] vertexPositions = new SimpleMatrix[8];
			for (int j = 0; j < 8; j++) {
				float[][] elements = new float[3][1];
				elements[0][0] = vertexPositionXs[j];
				elements[1][0] = vertexPositionYs[j];
				elements[2][0] = vertexPositionZs[j];

				vertexPositions[j] = new SimpleMatrix(elements);
			}

			block.setVertexPositions(vertexPositions);

			// UVs
			float[] us = new float[24];
			float[] vs = new float[24];

			for (int j = 0; j < 24; j++) {
				us[j] = ByteFunctions.getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 24; j++) {
				vs[j] = ByteFunctions.getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}

			UV[] uvs = new UV[24];
			for (int j = 0; j < 24; j++) {
				uvs[j].setU(us[j]);
				uvs[j].setV(vs[j]);
			}

			block.setUvs(uvs);

			// Texture IDs
			int[] textureIDs = new int[6];

			for (int j = 0; j < 6; j++) {
				textureIDs[j] = Byte.toUnsignedInt(bin[pos]);
				pos += 4;
			}

			block.setTextureIDs(textureIDs);

			// Enabled flag
			int enabled = Byte.toUnsignedInt(bin[pos]);
			if (enabled != 0) {
				block.setEnabled(true);
			} else {
				block.setEnabled(false);
			}
			pos += 4;

			blocks.add(block);
		}
	}

	public List<BD1Block> getBlocks() {
		return blocks;
	}
	public Map<Integer, String> getTextureFilenames() {
		return textureFilenames;
	}
}
