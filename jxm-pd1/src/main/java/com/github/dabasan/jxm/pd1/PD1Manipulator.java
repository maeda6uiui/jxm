package com.github.dabasan.jxm.pd1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.ejml_3dtools.Matrix;
import com.github.dabasan.ejml_3dtools.Vector;

/**
 * PD1 manipulator
 * 
 * @author Daba
 *
 */
public class PD1Manipulator {
	private Logger logger = LoggerFactory.getLogger(PD1Manipulator.class);

	private List<PD1Point> points;

	public PD1Manipulator() {
		points = new ArrayList<>();
	}
	public PD1Manipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public PD1Manipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
	public PD1Manipulator(String filepath) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new PD1Reader(is);
		points = reader.getPoints();
	}

	/**
	 * Returns points.
	 * 
	 * @return Points
	 */
	public List<PD1Point> getPoints() {
		return new ArrayList<>(points);
	}
	/**
	 * Sets points.<br>
	 * Null argument is rejected.
	 * 
	 * @param points
	 *            Points
	 */
	public void setPoints(List<PD1Point> points) {
		if (points == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		this.points = points;
	}

	/**
	 * Returns the number of points.
	 * 
	 * @return Number of points
	 */
	public int getNumPoints() {
		return points.size();
	}
	/**
	 * Returns the number of points with a specified value in a parameter.
	 * 
	 * @param paramIndex
	 *            Index of parameters (0, 1, 2 or 3)
	 * @param value
	 *            Value
	 * @return Number of points
	 */
	public int getNumPoints(int paramIndex, int value) {
		int pointCount = 0;

		for (var point : points) {
			int[] parameters = point.getParameters();

			if (parameters[paramIndex] == value) {
				pointCount++;
			}
		}

		return pointCount;
	}

	/**
	 * Transforms the points with a matrix.
	 * 
	 * @param mat
	 *            Matrix
	 */
	public void transform(Matrix mat) {
		for (var point : points) {
			Vector position = point.getPosition();
			position = position.transform(mat);
		}
	}

	/**
	 * Translates the points.
	 * 
	 * @param translationX
	 *            Translation X
	 * @param translationY
	 *            Translation Y
	 * @param translationZ
	 *            Translation Z
	 */
	public void translate(double translationX, double translationY, double translationZ) {
		var translationMat = Matrix.createTranslationMatrix(translationX, translationY,
				translationZ);
		this.transform(translationMat);
	}

	/**
	 * Rotate the points around the X-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 */
	public void rotX(double th) {
		var rotMat = Matrix.createRotationXMatrix(th);
		this.transform(rotMat);
	}
	/**
	 * Rotate the points around the Y-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 */
	public void rotY(double th) {
		var rotMat = Matrix.createRotationYMatrix(th);
		this.transform(rotMat);
	}
	/**
	 * Rotate the points around the Z-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 */
	public void rotZ(double th) {
		var rotMat = Matrix.createRotationZMatrix(th);
		this.transform(rotMat);
	}
	/**
	 * Rotates the points around an arbitrary axis.
	 * 
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @param th
	 *            Rotation angle (radian)
	 */
	public void rot(double axisX, double axisY, double axisZ, double th) {
		var rotMat = Matrix.createRotationMatrix(axisX, axisY, axisZ, th);
		this.transform(rotMat);
	}

	/**
	 * Rescales the points.
	 * 
	 * @param scaleX
	 *            Scale X
	 * @param scaleY
	 *            Scale Y
	 * @param scaleZ
	 *            Scale Z
	 */
	public void rescale(double scaleX, double scaleY, double scaleZ) {
		var scaleMat = Matrix.createScalingMatrix(scaleX, scaleY, scaleZ);
		this.transform(scaleMat);
	}

	/**
	 * Rotates the direction of each point.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 */
	public void rotateDirection(double th) {
		for (var point : points) {
			double rotation = point.getRotation();
			point.setRotation(rotation + th);
		}
	}

	/**
	 * Inverts the points with respect to the Z-axis.
	 */
	public void invertZ() {
		for (var point : points) {
			Vector position = point.getPosition();
			position.setZ(position.getZ() * (-1.0));

			double rotation = point.getRotation();
			rotation *= (-1.0);
			rotation += Math.PI;
			point.setRotation(rotation);
		}
	}

	private void innerSaveAsPD1(OutputStream os) throws IOException {
		var writer = new PD1Writer();
		writer.write(os, points);
	}
	/**
	 * Saves the points as a PD1 file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsPD1(OutputStream os) {
		int ret = 0;

		try {
			this.innerSaveAsPD1(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the points as a PD1 file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsPD1(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.innerSaveAsPD1(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the points as a PD1 file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsPD1(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.innerSaveAsPD1(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
