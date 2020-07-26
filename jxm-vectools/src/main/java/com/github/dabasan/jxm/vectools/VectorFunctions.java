package com.github.dabasan.jxm.vectools;

import org.ejml.simple.SimpleMatrix;

/**
 * Vector functions
 * 
 * @author Daba
 *
 */
public class VectorFunctions {
	/**
	 * Computes cross product.<br>
	 * Returns null if input vectors do not satisfy the requirements.
	 * 
	 * @param v1
	 *            3-dimensional vector
	 * @param v2
	 *            3-dimensional vector
	 * @return v1 x v2
	 */
	public static SimpleMatrix cross(SimpleMatrix v1, SimpleMatrix v2) {
		if (!(v1.numRows() == 3 && v1.numCols() == 1)) {
			return null;
		}
		if (!(v2.numRows() == 3 && v2.numCols() == 1)) {
			return null;
		}

		double a1 = v1.get(0, 0);
		double a2 = v1.get(1, 0);
		double a3 = v1.get(2, 0);
		double b1 = v2.get(0, 0);
		double b2 = v2.get(1, 0);
		double b3 = v2.get(2, 0);

		double[][] elements = new double[3][1];
		elements[0][0] = a2 * b3 - b2 * a3;
		elements[1][0] = a3 * b1 - b3 * a1;
		elements[2][0] = a1 * b2 - b1 * a2;

		return new SimpleMatrix(elements);
	}
	/**
	 * Subtraction
	 * 
	 * @param v1
	 *            3-dimensional vector
	 * @param v2
	 *            3-dimensional vector
	 * @return v1 - v2
	 */
	public static SimpleMatrix sub(SimpleMatrix v1, SimpleMatrix v2) {
		if (!(v1.numRows() == 3 && v1.numCols() == 1)) {
			return null;
		}
		if (!(v2.numRows() == 3 && v2.numCols() == 1)) {
			return null;
		}

		double[][] elements = new double[3][1];
		elements[0][0] = v1.get(0, 0) - v2.get(0, 0);
		elements[1][0] = v1.get(1, 0) - v2.get(1, 0);
		elements[2][0] = v1.get(2, 0) - v2.get(2, 0);

		return new SimpleMatrix(elements);
	}
}
