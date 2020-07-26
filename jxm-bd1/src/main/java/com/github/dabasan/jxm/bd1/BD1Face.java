package com.github.dabasan.jxm.bd1;

import org.ejml.simple.SimpleMatrix;

/**
 * BD1 face
 * 
 * @author Daba
 *
 */
class BD1Face {
	private SimpleMatrix[] vertexPositions;
	private SimpleMatrix normal;
	private UV[] uvs;

	public BD1Face() {
		vertexPositions = new SimpleMatrix[4];
		normal = new SimpleMatrix(3, 1);
		uvs = new UV[4];

		for (int i = 0; i < 4; i++) {
			vertexPositions[i] = new SimpleMatrix(3, 1);
			uvs[i] = new UV();
		}
	}

	public SimpleMatrix[] getVertexPositions() {
		return vertexPositions;
	}
	public SimpleMatrix getNormal() {
		return normal;
	}
	public UV[] getUvs() {
		return uvs;
	}

	public void setVertexPositions(SimpleMatrix[] vertexPositions) {
		this.vertexPositions = vertexPositions;
	}
	public void setNormal(SimpleMatrix normal) {
		this.normal = normal;
	}
	public void setUvs(UV[] uvs) {
		this.uvs = uvs;
	}
}
