package com.github.dabasan.jxm.pd1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.joml.Matrix4f;

/**
 * Test PD1Manipulator
 * 
 * @author Daba
 *
 */
public class PD1ManipulatorTest {
	private final String TARGET_DIR = "./Data";
	private PD1Manipulator manipulator;

	public static void main(String[] args) {
		new PD1ManipulatorTest();
	}

	public PD1ManipulatorTest() {
		var srcFilepath = Paths.get(TARGET_DIR, "point.pd1").toString();
		try {
			manipulator = new PD1Manipulator(srcFilepath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.printNumPoints();
		this.createTransform();
		this.createTranslate();
		this.createRotX();
		this.createRotY();
		this.createRotZ();
		this.createRot();
		this.createRescale();
		this.createInvertZ();
		this.createRotateDirection();
	}

	private void printNumPoints() {
		System.out.println("#Number of points");
		System.out.println("##Total");
		System.out.println(manipulator.getNumPoints());
		System.out.println("##Characters");
		System.out.println(manipulator.getNumPoints(0, 1) + manipulator.getNumPoints(0, 6));
	}

	private void createTransform() {
		System.out.println("#transform()");

		var mat = new Matrix4f().rotate((float) Math.PI / 4.0f, 1.0f, 0.0f, 0.0f)
				.rotate((float) Math.PI / 4.0f, 0.0f, 1.0f, 0.0f)
				.rotate((float) Math.PI / 4.0f, 0.0f, 0.0f, 1.0f).scale(1.0f, 2.0f, 1.0f);
		manipulator.transform(mat);

		var saveFilepath = Paths.get(TARGET_DIR, "transform.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		var invMat = mat.invert();
		manipulator.transform(invMat);
	}
	private void createTranslate() {
		System.out.println("#translate()");

		float amountX = 50.0f;
		float amountY = 50.0f;
		float amountZ = 50.0f;
		manipulator.translate(amountX, amountY, amountZ);

		var saveFilepath = Paths.get(TARGET_DIR, "translate.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.translate(-amountX, -amountY, -amountZ);
	}
	private void createRotX() {
		System.out.println("#rotX()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotX(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_x.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.rotX(-amount);
	}
	private void createRotY() {
		System.out.println("#rotY()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotY(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_y.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.rotY(-amount);
	}
	private void createRotZ() {
		System.out.println("#rotZ()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotZ(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rot_z.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.rotZ(-amount);
	}
	private void createRot() {
		System.out.println("#rot()");

		var origPoints = new ArrayList<PD1Point>();
		manipulator.getPoints().forEach(p -> origPoints.add(new PD1Point(p)));

		float amount = (float) Math.PI / 4.0f;
		manipulator.rot(amount, 1.0f, 1.0f, 1.0f);

		var saveFilepath = Paths.get(TARGET_DIR, "rot.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.setPoints(origPoints);
	}
	private void createRescale() {
		System.out.println("#rescale()");

		float scaleX = 2.0f;
		float scaleY = 2.0f;
		float scaleZ = 2.0f;
		manipulator.rescale(scaleX, scaleY, scaleZ);

		var saveFilepath = Paths.get(TARGET_DIR, "rescale.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.rescale(1.0f / scaleX, 1.0f / scaleY, 1.0f / scaleZ);
	}
	private void createInvertZ() {
		System.out.println("#invertZ()");

		manipulator.invertZ();

		var saveFilepath = Paths.get(TARGET_DIR, "invert_z.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.invertZ();
	}
	private void createRotateDirection() {
		System.out.println("#rotateDirection()");

		float amount = (float) Math.PI / 4.0f;
		manipulator.rotateDirection(amount);

		var saveFilepath = Paths.get(TARGET_DIR, "rotate_direction.pd1").toString();
		manipulator.saveAsPD1(saveFilepath);

		manipulator.rotateDirection(-amount);
	}
}
