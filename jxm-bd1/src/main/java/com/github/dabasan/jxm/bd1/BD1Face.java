package com.github.dabasan.jxm.bd1;

import org.joml.Vector3f;

/**
 * BD1 face
 *
 * @author maeda6uiui
 */
class BD1Face {
    public Vector3f[] vertexPositions;
    public Vector3f normal;
    public BD1UV[] uvs;

    public BD1Face() {
        vertexPositions = new Vector3f[4];
        normal = new Vector3f();
        uvs = new BD1UV[4];

        for (int i = 0; i < 4; i++) {
            vertexPositions[i] = new Vector3f();
            uvs[i] = new BD1UV();
        }
    }
}
