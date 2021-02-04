package com.github.dabasan.jxm.bd1;

import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 * BD1 block
 * 
 * @author Daba
 *
 */
public class BD1Block {
	private Vector3fc[] vertexPositions;
	private UV[] uvs;
	private int[] textureIDs;
	private boolean enabled;

	public BD1Block() {
		vertexPositions = new Vector3fc[8];
		uvs = new UV[24];
		textureIDs = new int[6];

		for (int i = 0; i < 8; i++) {
			vertexPositions[i] = new Vector3f();
		}
		for (int i = 0; i < 24; i++) {
			uvs[i] = new UV();
		}
		for (int i = 0; i < 6; i++) {
			textureIDs[i] = 0;
		}
		enabled = true;
	}

	public Vector3fc[] getVertexPositions() {
		return vertexPositions;
	}
	public UV[] getUVs() {
		return uvs;
	}
	public int[] getTextureIDs() {
		return textureIDs;
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void setVertexPositions(Vector3fc[] vertexPositions) {
		this.vertexPositions = vertexPositions;
	}
	public void setUVs(UV[] uvs) {
		this.uvs = uvs;
	}
	public void setTextureIDs(int[] textureIDs) {
		this.textureIDs = textureIDs;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
