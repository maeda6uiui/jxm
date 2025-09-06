package com.github.maeda6uiui.jxm.bd1;

import org.joml.Vector3fc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static com.github.maeda6uiui.jxm.bintools.ByteFunctions.addFloatToBinLE;
import static com.github.maeda6uiui.jxm.bintools.ByteFunctions.addUnsignedShortToBinLE;

/**
 * BD1 writer
 *
 * @author maeda6uiui
 */
class BD1Writer {
    public void write(OutputStream os, List<BD1Block> blocks, Map<Integer, String> textureFilenames)
            throws IOException {
        List<Byte> bin = new ArrayList<>();

        //Texture filenames
        this.addTextureFilenamesToBin(bin, textureFilenames);

        //Number of blocks
        int numBlocks = blocks.size();
        addUnsignedShortToBinLE(bin, numBlocks);

        //Blocks
        for (int i = 0; i < numBlocks; i++) {
            BD1Block block = blocks.get(i);

            //Vertex positions
            Vector3fc[] vertexPositions = block.vertexPositions;

            //X
            for (int j = 0; j < 8; j++) {
                addFloatToBinLE(bin, vertexPositions[j].x());
            }
            //Y
            for (int j = 0; j < 8; j++) {
                addFloatToBinLE(bin, vertexPositions[j].y());
            }
            //Z
            for (int j = 0; j < 8; j++) {
                addFloatToBinLE(bin, vertexPositions[j].z());
            }

            //UVs
            UV[] uvs = block.uvs;

            //U
            for (int j = 0; j < 24; j++) {
                addFloatToBinLE(bin, uvs[j].u);
            }
            //V
            for (int j = 0; j < 24; j++) {
                addFloatToBinLE(bin, uvs[j].v);
            }

            //Texture IDs
            int[] textureIDs = block.textureIDs;

            for (int j = 0; j < 6; j++) {
                bin.add((byte) textureIDs[j]);
                for (int k = 0; k < 3; k++) {
                    bin.add((byte) 0x00);
                }
            }

            //Enabled flag
            if (block.enabled) {
                bin.add((byte) 0x01);
            } else {
                bin.add((byte) 0x00);
            }
            for (int j = 0; j < 3; j++) {
                bin.add((byte) 0x00);
            }
        }

        for (byte b : bin) {
            os.write(b);
        }
    }

    private void addTextureFilenamesToBin(List<Byte> bin, Map<Integer, String> textureFilenames) {
        var sortedTextureFilenames = new TreeMap<>(textureFilenames);

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
