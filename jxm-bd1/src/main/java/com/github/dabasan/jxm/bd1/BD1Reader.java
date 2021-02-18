package com.github.dabasan.jxm.bd1;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Vector3f;
import org.joml.Vector3fc;

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

		// Read all bytes from a stream
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
		int numBlocks = getUnsignedShortValueFromBinLE(bin, pos);
		pos += 2;

		// Blocks
		for (int i = 0; i < numBlocks; i++) {
			var block = new BD1Block();

			// Vertex positions
			float[] vertexPositionXs = new float[8];
			float[] vertexPositionYs = new float[8];
			float[] vertexPositionZs = new float[8];

			for (int j = 0; j < 8; j++) {
				vertexPositionXs[j] = getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 8; j++) {
				vertexPositionYs[j] = getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 8; j++) {
				vertexPositionZs[j] = getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}

			var vertexPositions = new Vector3fc[8];
			for (int j = 0; j < 8; j++) {
				vertexPositions[j] = new Vector3f(vertexPositionXs[j], vertexPositionYs[j],
						vertexPositionZs[j]);
			}

			block.vertexPositions = vertexPositions;

			// UVs
			float[] us = new float[24];
			float[] vs = new float[24];

			for (int j = 0; j < 24; j++) {
				us[j] = getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}
			for (int j = 0; j < 24; j++) {
				vs[j] = getFloatValueFromBinLE(bin, pos);
				pos += 4;
			}

			var uvs = new UV[24];
			for (int j = 0; j < 24; j++) {
				var uv = new UV(us[j], vs[j]);
				uvs[j] = uv;
			}

			block.uvs = uvs;

			// Texture IDs
			int[] textureIDs = new int[6];

			for (int j = 0; j < 6; j++) {
				textureIDs[j] = Byte.toUnsignedInt(bin[pos]);
				pos += 4;
			}

			block.textureIDs = textureIDs;

			// Enabled flag
			int enabled = Byte.toUnsignedInt(bin[pos]);
			if (enabled != 0) {
				block.enabled = true;
			} else {
				block.enabled = false;
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
