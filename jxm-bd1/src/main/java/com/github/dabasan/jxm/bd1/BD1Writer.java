package com.github.dabasan.jxm.bd1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector3fc;

import com.github.dabasan.jxm.bintools.ByteFunctions;

/**
 * BD1 writer
 * 
 * @author Daba
 *
 */
class BD1Writer {
	public BD1Writer() {

	}

	public void write(OutputStream os, List<BD1Block> blocks, Map<Integer, String> textureFilenames)
			throws IOException {
		List<Byte> bin = new ArrayList<>();

		// Texture filenames
		this.addTextureFilenamesToBin(bin, textureFilenames);

		// Number of blocks
		int numBlocks = blocks.size();
		ByteFunctions.addUnsignedShortValueToBinLE(bin, numBlocks);

		// Blocks
		for (int i = 0; i < numBlocks; i++) {
			BD1Block block = blocks.get(i);

			// Vertex positions
			Vector3fc[] vertexPositions = block.getVertexPositions();

			// X
			for (int j = 0; j < 8; j++) {
				ByteFunctions.addFloatValueToBinLE(bin, vertexPositions[j].x());
			}
			// Y
			for (int j = 0; j < 8; j++) {
				ByteFunctions.addFloatValueToBinLE(bin, vertexPositions[j].y());
			}
			// Z
			for (int j = 0; j < 8; j++) {
				ByteFunctions.addFloatValueToBinLE(bin, vertexPositions[j].z());
			}

			// UVs
			UV[] uvs = block.getUVs();

			// U
			for (int j = 0; j < 24; j++) {
				ByteFunctions.addFloatValueToBinLE(bin, uvs[j].getUFloat());
			}
			// V
			for (int j = 0; j < 24; j++) {
				ByteFunctions.addFloatValueToBinLE(bin, uvs[j].getVFloat());
			}

			// Texture IDs
			int[] textureIDs = block.getTextureIDs();

			for (int j = 0; j < 6; j++) {
				bin.add((byte) textureIDs[j]);
				for (int k = 0; k < 3; k++) {
					bin.add((byte) 0x00);
				}
			}

			// Enabled flag
			if (block.isEnabled()) {
				bin.add((byte) 0x01);
			} else {
				bin.add((byte) 0x00);
			}
			for (int j = 0; j < 3; j++) {
				bin.add((byte) 0x00);
			}
		}

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
		}
	}
	private void addTextureFilenamesToBin(List<Byte> bin, Map<Integer, String> textureFilenames) {
		var sortedTextureFilenames = new TreeMap<Integer, String>(textureFilenames);

		int textureCount = 0;
		for (var entry : sortedTextureFilenames.entrySet()) {
			String textureFilename = entry.getValue();

			byte[] textureFilenameBuffer = textureFilename.getBytes();
			textureFilenameBuffer = Arrays.copyOfRange(textureFilenameBuffer, 0, 31);
			textureFilenameBuffer[30] = 0;

			for (int i = 0; i < 31; i++) {
				bin.add(textureFilenameBuffer[i]);
			}

			textureCount++;
			if (textureCount == 10) {
				break;
			}
		}

		for (int i = textureCount; i < 10; i++) {
			for (int j = 0; j < 31; j++) {
				bin.add((byte) 0x00);
			}
		}
	}
}
