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
	public Vector3fc[] vertexPositions;
	public UV[] uvs;
	public int[] textureIDs;
	public boolean enabled;

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
}
