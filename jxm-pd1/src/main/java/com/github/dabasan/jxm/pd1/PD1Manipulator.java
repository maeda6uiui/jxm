package com.github.dabasan.jxm.pd1;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

import java.io.IOException;
import java.nio.file.Path;
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

    /**
     * Creates a PD1 manipulator and loads a PD1 file.
     *
     * @param path Path of the PD1 file
     * @throws IOException If it fails to load the PD1 file
     */
    public PD1Manipulator(Path path) throws IOException {
        var reader = new PD1Reader(path);
        points = reader.getPoints();

        transformationMat = new Matrix4f().identity();
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

    /**
     * Saves the point data in a PD1 file.
     *
     * @param path Path of the PD1 file
     * @throws IOException If it fails to write to the file
     */
    public void save(Path path) throws IOException {
        var writer = new PD1Writer();
        writer.write(path, points);
    }
}
