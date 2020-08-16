package com.github.dabasan.jxm.bd1;

/**
 * BD1 triangle
 * 
 * @author Daba
 *
 */
class BD1Triangle {
	private BD1Vertex[] vertices;
	private int textureID;

	public BD1Triangle() {
		textureID = -1;
	}

	public BD1Vertex[] getVertices() {
		return vertices;
	}
	public int getTextureID() {
		return textureID;
	}

	public void setVertices(BD1Vertex[] vertices) {
		this.vertices = vertices;
	}
	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}
}
