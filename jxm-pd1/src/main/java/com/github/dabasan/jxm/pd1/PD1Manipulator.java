package com.github.dabasan.jxm.pd1;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PD1 manipulator
 *
 * @author maeda6uiui
 */
public class PD1Manipulator {
    private List<PD1Point> points;

    private Matrix4f transformationMat;

    /**
     * Creates a PD1 manipulator.
     */
    public PD1Manipulator() {
        points = new ArrayList<>();

        transformationMat = new Matrix4f().identity();
    }

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new PD1Reader(is);
        points = reader.getPoints();

        transformationMat = new Matrix4f().identity();
    }

    /**
     * Creates a PD1 manipulator and loads a PD1.
     *
     * @param is input stream to load a PD1 from
     * @throws IOException if it fails to load
     */
    public PD1Manipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates a PD1 manipulator and loads a PD1.
     *
     * @param file file to load a PD1 from
     * @throws IOException if it fails to load
     */
    public PD1Manipulator(File file) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates a PD1 manipulator and loads a PD1.
     *
     * @param filepath filepath to load a PD1 from
     * @throws IOException if it fails to load
     */
    public PD1Manipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Returns points.
     *
     * @return points
     */
    public List<PD1Point> getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points points to set
     */
    public void setPoints(List<PD1Point> points) {
        this.points = points;
    }

    /**
     * Returns the number of points.
     *
     * @return number of points
     */
    public int getNumPoints() {
        return points.size();
    }

    /**
     * Returns the number of points with a specified value in parameters.
     *
     * @param paramIndex index of parameters (0, 1, 2 or 3)
     * @param value      value
     * @return number of points
     */
    public int getNumPoints(int paramIndex, int value) {
        int pointCount = 0;

        for (var point : points) {
            int[] parameters = point.parameters;
            if (parameters[paramIndex] == value) {
                pointCount++;
            }
        }

        return pointCount;
    }

    /**
     * Applies transformation.
     */
    public void applyTransformation() {
        points.forEach(point -> {
            Vector3f newPosition = transformationMat.transformPosition(new Vector3f(point.position));
            point.setPosition(newPosition);
        });
    }

    /**
     * Resets transformation.
     */
    public void resetTransformation() {
        transformationMat = new Matrix4f().identity();
    }

    /**
     * Returns current transformation matrix.
     *
     * @return Transformation matrix
     */
    public Matrix4f getTransformationMat() {
        return transformationMat;
    }

    /**
     * Transforms the points with a matrix.
     *
     * @param mat matrix for transformation
     * @return this
     */
    public PD1Manipulator transform(Matrix4fc mat) {
        transformationMat = transformationMat.mul(mat);
        return this;
    }

    /**
     * Translates the points.
     *
     * @param translationX amount of translation along the X-axis
     * @param translationY amount of translation along the Y-axis
     * @param translationZ amount of translation along the Z-axis
     * @return this
     */
    public PD1Manipulator translate(float translationX, float translationY, float translationZ) {
        var translationMat = new Matrix4f().translate(translationX, translationY, translationZ);
        this.transform(translationMat);

        return this;
    }

    /**
     * Rotate the points around the X-axis.
     *
     * @param th rotation angle in radian
     * @return this
     */
    public PD1Manipulator rotX(float th) {
        var rotMat = new Matrix4f().rotate(th, 1.0f, 0.0f, 0.0f);
        this.transform(rotMat);

        return this;
    }

    /**
     * Rotate the points around the Y-axis.
     *
     * @param th rotation angle in radian
     * @return this
     */
    public PD1Manipulator rotY(float th) {
        var rotMat = new Matrix4f().rotate(th, 0.0f, 1.0f, 0.0f);
        this.transform(rotMat);

        return this;
    }

    /**
     * Rotate the points around the Z-axis.
     *
     * @param th rotation angle in radian
     * @return this
     */
    public PD1Manipulator rotZ(float th) {
        var rotMat = new Matrix4f().rotate(th, 0.0f, 0.0f, 1.0f);
        this.transform(rotMat);

        return this;
    }

    /**
     * Rotates the points around an arbitrary axis.
     *
     * @param th    rotation angle in radian
     * @param axisX X-component of the axis
     * @param axisY Y-component of the axis
     * @param axisZ Z-component of the axis
     * @return this
     */
    public PD1Manipulator rot(float th, float axisX, float axisY, float axisZ) {
        var rotMat = new Matrix4f().rotate(th, axisZ, axisY, axisZ);
        this.transform(rotMat);

        return this;
    }

    /**
     * Rescales the points.
     *
     * @param scaleX X-axis scale
     * @param scaleY Y-axis scale
     * @param scaleZ Z-axis scale
     * @return this
     */
    public PD1Manipulator rescale(float scaleX, float scaleY, float scaleZ) {
        var scaleMat = new Matrix4f().scale(scaleX, scaleY, scaleZ);
        this.transform(scaleMat);

        return this;
    }

    /**
     * Rotates the direction of each point.
     *
     * @param th rotation angle in radian
     * @return this
     */
    public PD1Manipulator rotateDirection(float th) {
        for (var point : points) {
            float rotation = point.rotation;
            point.rotation = rotation + th;
        }

        return this;
    }

    /**
     * Inverts the points with respect to the Z-axis.
     *
     * @return this
     */
    public PD1Manipulator invertZ() {
        var newPoints = new ArrayList<PD1Point>();
        points.forEach(point -> newPoints.add(new PD1Point(point)));

        newPoints.forEach(point -> {
            var newPosition = new Vector3f(point.position).mul(new Vector3f(1.0f, 1.0f, -1.0f));
            point.setPosition(newPosition);

            float newRotation = point.rotation * (-1.0f) + (float) Math.PI;
            point.setRotation(newRotation);
        });

        this.points = newPoints;

        return this;
    }

    private void saveAsPD1Base(OutputStream os) throws IOException {
        var writer = new PD1Writer();
        writer.write(os, points);
    }

    /**
     * Saves the points as a PD1.
     *
     * @param os output stream to write the points to
     * @throws IOException if it fails to output
     */
    public void saveAsPD1(OutputStream os) throws IOException {
        this.saveAsPD1Base(os);
    }

    /**
     * Saves the points as a PD1.
     *
     * @param file file to write the points to
     * @throws IOException if it fails to output
     */
    public void saveAsPD1(File file) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.saveAsPD1Base(bos);
        }
    }

    /**
     * Saves the points as a PD1.
     *
     * @param filepath filepath to write the points to
     * @throws IOException if it fails to output
     */
    public void saveAsPD1(String filepath) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.saveAsPD1Base(bos);
        }
    }
}
