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
	private Vector3fc[] vertexPositions;
	private Vector3fc normal;
	private UV[] uvs;

	public BD1Face() {
		vertexPositions = new Vector3fc[4];
		normal = new Vector3f();
		uvs = new UV[4];

		for (int i = 0; i < 4; i++) {
			vertexPositions[i] = new Vector3f();
			uvs[i] = new UV();
		}
	}

	public Vector3fc[] getVertexPositions() {
		return vertexPositions;
	}
	public Vector3fc getNormal() {
		return normal;
	}
	public UV[] getUVs() {
		return uvs;
	}

	public void setVertexPositions(Vector3fc[] vertexPositions) {
		this.vertexPositions = vertexPositions;
	}
	public void setNormal(Vector3fc normal) {
		this.normal = normal;
	}
	public void setUVs(UV[] uvs) {
		this.uvs = uvs;
	}
}
