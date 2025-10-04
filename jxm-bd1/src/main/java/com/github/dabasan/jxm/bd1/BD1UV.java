package com.github.dabasan.jxm.bd1;

/**
 * UV coordinates
 *
 * @author maeda6uiui
 */
public class BD1UV {
    public float u;
    public float v;

    /**
     * Creates a UV. U and V are set to 0.
     */
    public BD1UV() {
        u = 0.0f;
        v = 0.0f;
    }

    /**
     * Creates a UV. U and V coordinates are set to the values specified.
     *
     * @param u U coordinate
     * @param v V coordinate
     */
    public BD1UV(float u, float v) {
        this.u = u;
        this.v = v;
    }

    /**
     * Copies a UV.
     *
     * @param uv UV
     */
    public BD1UV(BD1UV uv) {
        this.u = uv.u;
        this.v = uv.v;
    }

    @Override
    public String toString() {
        return "(" + u + "," + v + ")";
    }
}
