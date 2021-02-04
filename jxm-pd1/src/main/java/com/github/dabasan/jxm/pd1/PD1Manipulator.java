package com.github.dabasan.jxm.pd1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Sets points.
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
	 * Returns the number of points with a specified value in parameters.
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
	 * @return This instance
	 */
	public PD1Manipulator transform(Matrix4fc mat) {
		for (var point : points) {
			Vector3fc position = point.getPosition();
			var position4f = new Vector4f(position.x(), position.y(), position.z(), 1.0f);
			position4f = mat.transform(position4f);

			point.setPosition(new Vector3f(position4f.x(), position4f.y(), position4f.z()));
		}

		return this;
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
	 * @return This instance
	 */
	public PD1Manipulator translate(float translationX, float translationY, float translationZ) {
		var translationMat = new Matrix4f().translate(translationX, translationY, translationZ);
		this.transform(translationMat);

		return this;
	}

	/**
	 * Rotate the points around the X-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public PD1Manipulator rotX(float th) {
		var rotMat = new Matrix4f().rotate(th, 1.0f, 0.0f, 0.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotate the points around the Y-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public PD1Manipulator rotY(float th) {
		var rotMat = new Matrix4f().rotate(th, 0.0f, 1.0f, 0.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotate the points around the Z-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public PD1Manipulator rotZ(float th) {
		var rotMat = new Matrix4f().rotate(th, 0.0f, 0.0f, 1.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the points around an arbitrary axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @return This instance
	 */
	public PD1Manipulator rot(float th, float axisX, float axisY, float axisZ) {
		var rotMat = new Matrix4f().rotate(th, axisZ, axisY, axisZ);
		this.transform(rotMat);

		return this;
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
	 * @return This instance
	 */
	public PD1Manipulator rescale(float scaleX, float scaleY, float scaleZ) {
		var scaleMat = new Matrix4f().scale(scaleX, scaleY, scaleZ);
		this.transform(scaleMat);

		return this;
	}

	/**
	 * Rotates the direction of each point.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public PD1Manipulator rotateDirection(float th) {
		for (var point : points) {
			float rotation = point.getRotation();
			point.setRotation(rotation + th);
		}

		return this;
	}

	/**
	 * Inverts the points with respect to the Z-axis.
	 * 
	 * @return This instance
	 */
	public PD1Manipulator invertZ() {
		for (var point : points) {
			Vector3fc position = point.getPosition();
			position = new Vector3f(position.x(), position.y(), position.z() * (-1.0f));
			point.setPosition(position);

			float rotation = point.getRotation();
			rotation *= (-1.0);
			rotation += Math.PI;
			point.setRotation(rotation);
		}

		return this;
	}

	private void saveAsPD1Base(OutputStream os) throws IOException {
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
			this.saveAsPD1Base(os);
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
			this.saveAsPD1Base(fos);
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
			this.saveAsPD1Base(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
