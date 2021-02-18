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

	/**
	 * Creates a point.
	 */
	public PD1Point() {
		position = new Vector3f();
		rotation = 0.0f;
		parameters = new int[]{0, 0, 0, 0};
	}
	/**
	 * Creates a point from a PD1Point instance.
	 * 
	 * @param point
	 *            PD1Point
	 */
	public PD1Point(PD1Point point) {
		this.position = point.position;
		this.rotation = point.rotation;
		this.parameters = point.parameters.clone();
	}
}
