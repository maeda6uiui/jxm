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
	 * Creates a UV. U and V are set to 0.
	 */
	public UV() {
		u = 0.0f;
		v = 0.0f;
	}
	/**
	 * Creates a UV. U and V coordinates are set to the values specified.
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
	 * Copies a UV.
	 * 
	 * @param uv
	 *            UV
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
