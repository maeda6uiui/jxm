package com.github.dabasan.jxm.bd1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
		/*
		var expected = new String[]{"floor.jpg", "loglog.jpg", "log2.jpg", "ornament.jpg",
				"plaster.jpg", "green.jpg", "stairs.bmp", "grass.jpg", "wood.jpg", "brick.jpg"};
		
		manipulator.setTextureFilename(1, "loglog.jpg");
		
		for (int i = 0; i < 10; i++) {
			String actual = manipulator.getTextureFilename(i);
			assertTrue(expected[i].equals(actual));
		}
		*/
	}
	@Test
	public void testSetTextureFilenames() {
		/*
		var textureFilenames = new HashMap<Integer, String>();
		textureFilenames.put(0, "floor.jpg");
		textureFilenames.put(1, "log.jpg");
		textureFilenames.put(2, "log2.jpg");
		manipulator.setTextureFilenames(textureFilenames);
		
		System.out.println(manipulator.getTextureFilenames());
		*/
	}

	@Test
	public void testTransform() {
		/*
		var rotXMat = Matrix.createRotationXMatrix(Math.PI / 4.0);
		var rotYMat = Matrix.createRotationYMatrix(Math.PI / 4.0);
		var rotZMat = Matrix.createRotationZMatrix(Math.PI / 4.0);
		var rotMat = rotZMat.mult(rotYMat).mult(rotXMat);
		manipulator.transform(rotMat);
		
		manipulator.saveAsBD1("./Data/transform.bd1");
		*/
	}

	@Test
	public void testTranslate() {
		// manipulator.translate(50.0, 50.0, 50.0);
		// manipulator.saveAsBD1("./Data/translate.bd1");
	}
	@Test
	public void testRotX() {
		// manipulator.rotX(Math.PI / 4.0);
		// manipulator.saveAsBD1("./Data/rotX.bd1");
	}
	@Test
	public void testRotY() {
		// manipulator.rotY(Math.PI / 4.0);
		// manipulator.saveAsBD1("./Data/rotY.bd1");
	}
	@Test
	public void testRotZ() {
		// manipulator.rotZ(Math.PI / 4.0);
		// manipulator.saveAsBD1("./Data/rotZ.bd1");
	}
	@Test
	public void testRot() {
		// manipulator.rot(0.0, 1.0, 0.0, Math.PI / 4.0);
		// manipulator.saveAsBD1("./Data/rot.bd1");
	}

	@Test
	public void testRescale() {
		// manipulator.rescale(2.0, 2.0, 2.0);
		// manipulator.saveAsBD1("./Data/rescale.bd1");
	}

	@Test
	public void testInvertZ() {
		// manipulator.invertZ();
		// manipulator.saveAsBD1("./Data/invertZ.bd1");
	}

	@Test
	public void testSaveAsOBJ() {
		// manipulator.saveAsOBJ("./Data/map.obj", "./Data/map.mtl", "map.mtl",
		// false);
	}

	@Test
	public void testGenerateBuffers() {
		// Test with OpenGL code
	}
}
