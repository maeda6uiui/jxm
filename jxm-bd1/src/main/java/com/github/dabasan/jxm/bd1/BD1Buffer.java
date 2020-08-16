package com.github.dabasan.jxm.bd1;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Buffer representation of BD1 blocks<br>
 * May be useful when rendering BD1 blocks with OpenGL.
 * 
 * @author Daba
 *
 */
public class BD1Buffer {
	private IntBuffer indexBuffer;
	private FloatBuffer posBuffer;
	private FloatBuffer uvBuffer;
	private FloatBuffer normBuffer;

	private int textureID;

	public BD1Buffer() {
		textureID = -1;
	}

	public IntBuffer getIndexBuffer() {
		return indexBuffer;
	}
	public FloatBuffer getPosBuffer() {
		return posBuffer;
	}
	public FloatBuffer getUVBuffer() {
		return uvBuffer;
	}
	public FloatBuffer getNormBuffer() {
		return normBuffer;
	}
	public int getTextureID() {
		return textureID;
	}

	public void setIndexBuffer(IntBuffer indexBuffer) {
		this.indexBuffer = indexBuffer;
	}
	public void setPosBuffer(FloatBuffer posBuffer) {
		this.posBuffer = posBuffer;
	}
	public void setUVBuffer(FloatBuffer uvBuffer) {
		this.uvBuffer = uvBuffer;
	}
	public void setNormBuffer(FloatBuffer normBuffer) {
		this.normBuffer = normBuffer;
	}
	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}
}
