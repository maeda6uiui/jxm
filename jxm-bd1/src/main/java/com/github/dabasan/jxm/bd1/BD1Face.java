package com.github.dabasan.jxm.bd1;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 face
 * 
 * @author Daba
 *
 */
class BD1Face {
	private Vector[] vertexPositions;
	private Vector normal;
	private UV[] uvs;

	public BD1Face() {
		vertexPositions = new Vector[4];
		normal = new Vector();
		uvs = new UV[4];

		for (int i = 0; i < 4; i++) {
			vertexPositions[i] = new Vector();
			uvs[i] = new UV();
		}
	}

	public Vector[] getVertexPositions() {
		return vertexPositions;
	}
	public Vector getNormal() {
		return normal;
	}
	public UV[] getUVs() {
		return uvs;
	}

	public void setVertexPositions(Vector[] vertexPositions) {
		this.vertexPositions = vertexPositions;
	}
	public void setNormal(Vector normal) {
		this.normal = normal;
	}
	public void setUVs(UV[] uvs) {
		this.uvs = uvs;
	}
}
