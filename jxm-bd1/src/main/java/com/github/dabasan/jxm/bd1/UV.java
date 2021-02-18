package com.github.dabasan.jxm.bd1;

/**
 * UV coordinates
 * 
 * @author Daba
 *
 */
public class UV {
	public float u;
	public float v;

	/**
	 * U and V coordinates are set to 0.
	 */
	public UV() {
		u = 0.0f;
		v = 0.0f;
	}
	/**
	 * U and V coordinates are set to the values specified.
	 * 
	 * @param u
	 *            U coordinate
	 * @param v
	 *            V coordinate
	 */
	public UV(float u, float v) {
		this.u = u;
		this.v = v;
	}
	/**
	 * Copies a UV instance.
	 * 
	 * @param uv
	 *            UV instance
	 */
	public UV(UV uv) {
		this.u = uv.u;
		this.v = uv.v;
	}

	@Override
	public String toString() {
		return "(" + u + "," + v + ")";
	}
}
