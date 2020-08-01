package com.github.dabasan.jxm.pd1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
		/*
		var rotXMat = Matrix.createRotationXMatrix(Math.PI / 4.0);
		var rotYMat = Matrix.createRotationYMatrix(Math.PI / 4.0);
		var rotZMat = Matrix.createRotationZMatrix(Math.PI / 4.0);
		var rotMat = rotZMat.mult(rotYMat).mult(rotXMat);
		manipulator.transform(rotMat);
		
		manipulator.saveAsPD1("./Data/transform.pd1");
		*/
	}

	@Test
	public void testTranslate() {
		// manipulator.translate(50.0, 50.0, 50.0);
		// manipulator.saveAsPD1("./Data/translate.pd1");
	}
	@Test
	public void testRotX() {
		// manipulator.rotX(Math.PI / 4.0);
		// manipulator.saveAsPD1("./Data/rotX.pd1");
	}
	@Test
	public void testRotY() {
		// manipulator.rotY(Math.PI / 4.0);
		// manipulator.saveAsPD1("./Data/rotY.pd1");
	}
	@Test
	public void testRotZ() {
		// manipulator.rotZ(Math.PI / 4.0);
		// manipulator.saveAsPD1("./Data/rotZ.pd1");
	}
	@Test
	public void testRot() {
		// manipulator.rot(0.0, 1.0, 0.0, Math.PI / 4.0);
		// manipulator.saveAsPD1("./Data/rot.pd1");
	}

	@Test
	public void testRescale() {
		// manipulator.rescale(2.0, 2.0, 2.0);
		// manipulator.saveAsPD1("./Data/rescale.pd1");
	}

	@Test
	public void testRotateDirection() {
		// manipulator.rotateDirection(Math.PI / 4.0);
		// manipulator.saveAsPD1("./Data/rotateDirection.pd1");
	}

	@Test
	public void testInvertZ() {
		// manipulator.invertZ();
		// manipulator.saveAsPD1("./Data/invertZ.pd1");
	}
}
