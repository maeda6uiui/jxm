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

	}
	public BD1Block(BD1Block block) {
		vertexPositions = new Vector3fc[8];
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
}
