package com.github.dabasan.jxm.bd1;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Buffer representation of BD1 blocks
 *
 * @author Daba
 */
public class BD1Buffer {
    public IntBuffer indexBuffer;
    public FloatBuffer posBuffer;
    public FloatBuffer uvBuffer;
    public FloatBuffer normBuffer;

    public int textureID;

    /**
     * Creates a BD1 buffer.
     */
    public BD1Buffer() {
        textureID = -1;
    }
}
