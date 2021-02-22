package com.github.dabasan.jxm.bd1;

import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 * BD1 face
 * 
 * @author Daba
 *
 */
class BD1Face {
	public Vector3fc[] vertexPositions;
	public Vector3fc normal;
	public UV[] uvs;

	public BD1Face() {
		vertexPositions = new Vector3fc[4];
		normal = new Vector3f();
		uvs = new UV[4];

		for (int i = 0; i < 4; i++) {
			vertexPositions[i] = new Vector3f();
			uvs[i] = new UV();
		}
	}
}
