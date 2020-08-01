package com.github.dabasan.jxm.bd1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import com.github.dabasan.ejml_3dtools.Matrix;

/**
 * Test class for BD1Manipulator
 * 
 * @author Daba
 *
 */
public class BD1ManipulatorTest {
	private BD1Manipulator manipulator;

	public BD1ManipulatorTest() {
		try {
			manipulator = new BD1Manipulator("./Data/map.bd1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNumBlocks() {
		int expected = 152;
		int actual = manipulator.getNumBlocks();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetTextureFilename() {
		var expected = new String[]{"floor.jpg", "log.jpg", "log2.jpg", "ornament.jpg",
				"plaster.jpg", "green.jpg", "stairs.bmp", "grass.jpg", "wood.jpg", "brick.jpg"};

		for (int i = 0; i < 10; i++) {
			String actual = manipulator.getTextureFilename(i);
			assertTrue(expected[i].equals(actual));
		}
	}
	@Test
	public void testGetTextureFilenames() {
		// System.out.println(manipulator.getTextureFilenames());
	}
	@Test
	public void testSetTextureFilename() {
		var expected = new String[]{"floor.jpg", "loglog.jpg", "log2.jpg", "ornament.jpg",
				"plaster.jpg", "green.jpg", "stairs.bmp", "grass.jpg", "wood.jpg", "brick.jpg"};

		manipulator.setTextureFilename(1, "loglog.jpg");

		for (int i = 0; i < 10; i++) {
			String actual = manipulator.getTextureFilename(i);
			assertTrue(expected[i].equals(actual));
		}
	}
	@Test
	public void testSetTextureFilenames() {
		var textureFilenames = new HashMap<Integer, String>();
		textureFilenames.put(0, "floor.jpg");
		textureFilenames.put(1, "log.jpg");
		textureFilenames.put(2, "log2.jpg");
		manipulator.setTextureFilenames(textureFilenames);

		// System.out.println(manipulator.getTextureFilenames());
	}

	@Test
	public void testTransform() {
		var rotXMat = Matrix.createRotationXMatrix(Math.PI / 4.0);
		var rotYMat = Matrix.createRotationYMatrix(Math.PI / 4.0);
		var rotZMat = Matrix.createRotationZMatrix(Math.PI / 4.0);
		var rotMat = rotZMat.mult(rotYMat).mult(rotXMat);
		manipulator.transform(rotMat);

		manipulator.saveAsBD1("./Data/transform.bd1");
	}
}
