package com.github.dabasan.jxm.bd1;

import org.joml.Vector3f;

import java.util.Arrays;
import java.util.Objects;

/**
 * BD1 block
 *
 * @author maeda6uiui
 */
public class BD1Block {
    public Vector3f[] vertexPositions;
    public UV[] uvs;
    public int[] textureIDs;
    public boolean enabled;

    /**
     * Creates a block.
     */
    public BD1Block() {
        vertexPositions = new Vector3f[8];
        for (int i = 0; i < 8; i++) {
            vertexPositions[i] = new Vector3f();
        }

        uvs = new UV[24];
        for (int i = 0; i < 24; i++) {
            uvs[i] = new UV();
        }

        textureIDs = new int[6];

        enabled = true;
    }

    /**
     * Copies a block.
     *
     * @param block Block
     */
    public BD1Block(BD1Block block) {
        vertexPositions = new Vector3f[8];
        for (int i = 0; i < 8; i++) {
            vertexPositions[i] = new Vector3f(block.vertexPositions[i]);
        }

        uvs = new UV[24];
        for (int i = 0; i < 24; i++) {
            uvs[i] = new UV(block.uvs[i]);
        }

        textureIDs = block.textureIDs.clone();

        enabled = block.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BD1Block bd1Block = (BD1Block) o;
        return enabled == bd1Block.enabled
                && Arrays.equals(vertexPositions, bd1Block.vertexPositions)
                && Arrays.equals(uvs, bd1Block.uvs)
                && Arrays.equals(textureIDs, bd1Block.textureIDs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(enabled);
        result = 31 * result + Arrays.hashCode(vertexPositions);
        result = 31 * result + Arrays.hashCode(uvs);
        result = 31 * result + Arrays.hashCode(textureIDs);
        return result;
    }

    public BD1Block setVertexPositions(Vector3f[] vertexPositions) {
        this.vertexPositions = vertexPositions;
        return this;
    }

    public BD1Block setUvs(UV[] uvs) {
        this.uvs = uvs;
        return this;
    }

    public BD1Block setTextureIDs(int[] textureIDs) {
        this.textureIDs = textureIDs;
        return this;
    }

    public BD1Block setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
