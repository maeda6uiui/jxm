package com.github.dabasan.jxm.pd1;

import org.joml.Vector3f;

/**
 * PD1 point
 *
 * @author maeda6uiui
 */
public class PD1Point {
    public Vector3f position;
    public float rotation;
    public int[] parameters;

    /**
     * Creates a point.
     */
    public PD1Point() {
        position = new Vector3f();
        rotation = 0.0f;
        parameters = new int[4];
    }

    /**
     * Copies a point.
     *
     * @param point Point
     */
    public PD1Point(PD1Point point) {
        this.position = new Vector3f(point.position);
        this.rotation = point.rotation;
        this.parameters = point.parameters.clone();
    }

    public PD1Point setPosition(Vector3f position) {
        this.position = position;
        return this;
    }

    public PD1Point setRotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public PD1Point setParameters(int[] parameters) {
        this.parameters = parameters;
        return this;
    }
}
