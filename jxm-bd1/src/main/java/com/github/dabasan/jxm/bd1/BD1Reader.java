package com.github.dabasan.jxm.bd1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getFloatFromBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.getUnsignedShortFromBinLE;

/**
 * BD1 reader
 *
 * @author maeda6uiui
 */
class BD1Reader {
    private final Map<Integer, String> textureFilenames;
    private final List<BD1Block> blocks;
    private int pos;

    public BD1Reader(Path path) throws IOException, IndexOutOfBoundsException {
        textureFilenames = new HashMap<>();
        blocks = new ArrayList<>();

        //Read all bytes
        byte[] bin = Files.readAllBytes(path);
        pos = 0;

        //Texture filenames
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

        //Number of blocks
        int numBlocks = getUnsignedShortFromBinLE(bin, pos);
        pos += 2;

        //Blocks
        for (int i = 0; i < numBlocks; i++) {
            var block = new BD1Block();

            //Vertex positions
            for (int j = 0; j < 8; j++) {
                block.vertexPositions[j].x = this.getFloatAndIncrementPos(bin);
            }
            for (int j = 0; j < 8; j++) {
                block.vertexPositions[j].y = this.getFloatAndIncrementPos(bin);
            }
            for (int j = 0; j < 8; j++) {
                block.vertexPositions[j].z = this.getFloatAndIncrementPos(bin);
            }

            //UVs
            for (int j = 0; j < 24; j++) {
                block.uvs[j].u = this.getFloatAndIncrementPos(bin);
            }
            for (int j = 0; j < 24; j++) {
                block.uvs[j].v = this.getFloatAndIncrementPos(bin);
            }

            //Texture IDs
            for (int j = 0; j < 6; j++) {
                block.textureIDs[j] = this.getUnsignedIntAndIncrementPos(bin);
            }

            //Enabled flag
            int enabled = this.getUnsignedIntAndIncrementPos(bin);
            block.enabled = enabled != 0;

            blocks.add(block);
        }
    }

    private float getFloatAndIncrementPos(byte[] bin) {
        float ret = getFloatFromBinLE(bin, pos);
        pos += 4;
        return ret;
    }

    private int getUnsignedIntAndIncrementPos(byte[] bin) {
        int ret = Byte.toUnsignedInt(bin[pos]);
        pos += 4;
        return ret;
    }

    public List<BD1Block> getBlocks() {
        return blocks;
    }

    public Map<Integer, String> getTextureFilenames() {
        return textureFilenames;
    }
}
