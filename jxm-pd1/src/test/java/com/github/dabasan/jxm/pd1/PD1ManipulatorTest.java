package com.github.dabasan.jxm.pd1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.ejml_3dtools.Matrix;

/**
 * Test class for PD1Manipulator
 * 
 * @author Daba
 *
 */
public class PD1ManipulatorTest {
	private PD1Manipulator manipulator;

	public PD1ManipulatorTest() {
		try {
			manipulator = new PD1Manipulator("./Data/point.pd1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNumPoints() {
		int expected = 110;
		int actual = manipulator.getNumPoints();

		assertEquals(expected, actual);
	}
	@Test
	public void testGetNumPoints2() {
		int expected = 33;
		int actual = manipulator.getNumPoints(0, 1) + manipulator.getNumPoints(0, 6);

		assertEquals(expected, actual);
	}

	@Test
	public void testTransform() {
		var rotXMat = Matrix.createRotationXMatrix(Math.PI / 4.0);
		var rotYMat = Matrix.createRotationYMatrix(Math.PI / 4.0);
		var rotZMat = Matrix.createRotationZMatrix(Math.PI / 4.0);
		var rotMat = rotZMat.mult(rotYMat).mult(rotXMat);
		manipulator.transform(rotMat);

		manipulator.saveAsPD1("./Data/transform.pd1");
	}
}
