package com.github.dabasan.jxm.pd1;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * PD1 point
 * 
 * @author Daba
 *
 */
public class PD1Point {
	private Vector position;
	private double rotation;
	private int[] parameters;

	/**
	 * Creates a point.
	 */
	public PD1Point() {
		position = new Vector();
		rotation = 0.0;
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

	public Vector getPosition() {
		return position;
	}
	public double getRotation() {
		return rotation;
	}
	public int[] getParameters() {
		return parameters;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public void setParameters(int[] parameters) {
		this.parameters = parameters;
	}
}
