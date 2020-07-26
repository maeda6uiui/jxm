package com.github.dabasan.jxm.vectools;

import static org.junit.Assert.*;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

/**
 * Test class for VectorFunctions
 * 
 * @author Daba
 *
 */
public class VectorFunctionsTest {
	@Test
	public void testCross() {
		double[][] elements1 = new double[3][1];
		elements1[0][0] = 3.0;
		elements1[1][0] = 4.0;
		elements1[2][0] = 1.0;

		double[][] elements2 = new double[3][1];
		elements2[0][0] = 3.0;
		elements2[1][0] = 7.0;
		elements2[2][0] = 5.0;

		var v1 = new SimpleMatrix(elements1);
		var v2 = new SimpleMatrix(elements2);

		var cross = VectorFunctions.cross(v1, v2);
		double[] actuals = new double[]{cross.get(0, 0), cross.get(1, 0), cross.get(2, 0)};

		double[] expected = new double[]{13.0, -12.0, 9.0};

		assertArrayEquals(expected, actuals, 1.0E-6f);
	}
	@Test
	public void testSub() {
		double[][] elements1 = new double[3][1];
		elements1[0][0] = 3.0;
		elements1[1][0] = 4.0;
		elements1[2][0] = 1.0;

		double[][] elements2 = new double[3][1];
		elements2[0][0] = 3.0;
		elements2[1][0] = 7.0;
		elements2[2][0] = 5.0;

		var v1 = new SimpleMatrix(elements1);
		var v2 = new SimpleMatrix(elements2);

		var sub = VectorFunctions.sub(v1, v2);
		double[] actuals = new double[]{sub.get(0, 0), sub.get(1, 0), sub.get(2, 0)};

		double[] expected = new double[]{0.0, -3.0, -4.0};

		assertArrayEquals(expected, actuals, 1.0E-6f);
	}
	@Test
	public void testNormalize() {
		double[][] elements = new double[3][1];
		elements[0][0] = 3.0;
		elements[1][0] = 4.0;
		elements[2][0] = 1.0;

		var v = new SimpleMatrix(elements);

		var normalized = VectorFunctions.normalize(v);
		double[] actuals = new double[]{normalized.get(0, 0), normalized.get(1, 0),
				normalized.get(2, 0)};

		double[] expected = new double[]{0.5883, 0.7845, 0.1961};

		assertArrayEquals(expected, actuals, 1.0E-3f);
	}
}
