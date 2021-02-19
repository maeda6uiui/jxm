package com.github.dabasan.jxm.pd1;

import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 * PD1 point
 * 
 * @author Daba
 *
 */
public class PD1Point {
	public Vector3fc position;
	public float rotation;
	public int[] parameters;

	public PD1Point() {

	}
	/**
	 * Copies a point.
	 * 
	 * @param point
	 *            Point
	 */
	public PD1Point(PD1Point point) {
		this.position = new Vector3f(point.position);
		this.rotation = point.rotation;
		this.parameters = point.parameters.clone();
	}
}
