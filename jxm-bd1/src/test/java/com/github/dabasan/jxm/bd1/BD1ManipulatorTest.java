package com.github.dabasan.jxm.bd1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.joml.Matrix4f;

/**
 * Test BD1Manipulator
 * 
 * @author Daba
 *
 */
public class BD1ManipulatorTest {
	private final String TARGET_DIR = "./Data/Mission";
	private BD1Manipulator manipulator;

	public BD1ManipulatorTest() {
		var srcFilepath = Paths.get(TARGET_DIR, "map.bd1").toString();
		try {
			manipulator = new BD1Manipulator(srcFilepath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.printNumBlocks();
		this.printTextureFilenames();
		this.createTransform();
		this.createTranslate();
		this.createRotX();
		this.createRotY();
		this.createRotZ();
		this.createRot();
		this.createRescale();
		this.createInvertZ();
		this.saveAsOBJ();
	}

	private void printNumBlocks() {
		System.out.println("#Number of blocks");
		System.out.println(manipulator.getNumBlocks());
	}
	private void printTextureFilenames() {
		System.out.println("#Texture filenames");
		System.out.println("##getTextureFilename()");
		for (int i = 0; i < 10; i++) {
			System.out.println(i + ": " + manipulator.getTextureFilename(i));
		}

		System.out.println("##getTextureFilenames()");
		manipulator.getTextureFilenames().forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("##setTextureFilename()");
		System.out.println("New texture filenames are set");
		for (int i = 0; i < 10; i++) {
			manipulator.setTextureFilename(i, "tex_" + i + ".jpg");
		}
		manipulator.getTextureFilenames().forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("##setTextureFilenames()");
		System.out.println("New texture filenames are set");
		var textureFilenames = new HashMap<Integer, String>();
		for (int i = 0; i < 10; i++) {
			textureFilenames.put(i, "texx_" + i + ".jpg");
		}
		manipulator.setTextureFilenames(textureFilenames);
		manipulator.getTextureFilenames().forEach((k, v) -> System.out.println(k + ": " + v));
	}

	private void createTransform() {
		System.out.println("#transform()");

		var mat = new Matrix4f().rotate((float) Math.PI / 4.0f, 1.0f, 0.0f, 0.0f)
				.rotate((float) Math.PI / 4.0f, 0.0f, 1.0f, 0.0f)
				.rotate((float) Math.PI / 4.0f, 0.0f, 0.0f, 1.0f).scale(1.0f, 2.0f, 1.0f);
		manipulator.transform(mat);

		var saveFilepath = Paths.get(TARGET_DIR, "transform.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		var invMat = mat.invert();
		manipulator.transform(invMat);
	}
	private void createTranslate() {
		System.out.println("#translate()");

		float amountX = 50.0f;
		float amountY = 50.0f;
		float amountZ = 50.0f;
		manipulator.translate(amountX, amountY, amountZ);

		var saveFilepath = Paths.get(TARGET_DIR, "translate.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.translate(-amountX, -amountY, -amountZ);
	}
	private void createRotX() {
		System.out.println("#rotX()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotX(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_x.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.rotX(-amount);
	}
	private void createRotY() {
		System.out.println("#rotY()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotY(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_y.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.rotY(-amount);
	}
	private void createRotZ() {
		System.out.println("#rotZ()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotZ(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_z.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.rotZ(-amount);
	}
	private void createRot() {
		System.out.println("#rot()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rot(amount, 1.0f, 1.0f, 1.0f);

		var saveFilepath = Paths.get(TARGET_DIR, "rot.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.rot(-amount, 1.0f, 1.0f, 1.0f);
	}
	private void createRescale() {
		System.out.println("#rescale()");

		float scaleX = 2.0f;
		float scaleY = 2.0f;
		float scaleZ = 2.0f;
		manipulator.rescale(scaleX, scaleY, scaleZ);

		var saveFilepath = Paths.get(TARGET_DIR, "rescale.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.rescale(1.0f / scaleX, 1.0f / scaleY, 1.0f / scaleZ);
	}
	private void createInvertZ() {
		System.out.println("#invertZ()");

		manipulator.invertZ();

		var saveFilepath = Paths.get(TARGET_DIR, "invert_z.bd1").toString();
		manipulator.saveAsBD1(saveFilepath);

		manipulator.invertZ();
	}

	private void saveAsOBJ() {
		System.out.println("#saveAsOBJ()");

		var objFilepath = Paths.get(TARGET_DIR, "map.obj").toString();
		var mtlFilepath = Paths.get(TARGET_DIR, "map.mtl").toString();
		manipulator.saveAsOBJ(objFilepath, mtlFilepath, "map.mtl", false);

		var objFilepath_2 = Paths.get(TARGET_DIR, "map_2.obj").toString();
		var mtlFilepath_2 = Paths.get(TARGET_DIR, "map_2.mtl").toString();
		manipulator.saveAsOBJ(objFilepath_2, mtlFilepath_2, "map_2.mtl", true);
	}
}
