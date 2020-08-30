package com.github.dabasan.jxm.bd1;

/**
 * UV coordinates
 * 
 * @author Daba
 *
 */
public class UV {
	private double u;
	private double v;

	/**
	 * U and V coordinates are set to 0.
	 */
	public UV() {
		u = 0.0;
		v = 0.0;
	}
	/**
	 * U and V coordinates are set to the values specified.
	 * 
	 * @param u
	 *            U coordinate
	 * @param v
	 *            V coordinate
	 */
	public UV(double u, double v) {
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

	public double getU() {
		return u;
	}
	public double getV() {
		return v;
	}
	public float getUFloat() {
		return (float) u;
	}
	public float getVFloat() {
		return (float) v;
	}

	public void setU(double u) {
		this.u = u;
	}
	public void setV(double v) {
		this.v = v;
	}
}
