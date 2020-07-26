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
	 * Cross product<br>
	 * Returns null if input is not a 3-dimensional vector.
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
	 * Subtraction<br>
	 * Returns null if input is not a 3-dimensional vector.
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
	/**
	 * Normalization<br>
	 * Returns null if input is not a 3-dimensional vector.
	 * 
	 * @param v
	 *            3-dimensional vector
	 * @return Normalized vector
	 */
	public static SimpleMatrix normalize(SimpleMatrix v) {
		if (!(v.numRows() == 3 && v.numCols() == 1)) {
			return null;
		}

		double x = v.get(0, 0);
		double y = v.get(1, 0);
		double z = v.get(2, 0);

		double size = Math.sqrt(x * x + y * y + z * z);

		double[][] elements = new double[3][1];
		elements[0][0] = x / size;
		elements[1][0] = y / size;
		elements[2][0] = z / size;

		return new SimpleMatrix(elements);
	}
}
