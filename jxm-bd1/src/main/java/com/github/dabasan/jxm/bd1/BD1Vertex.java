package com.github.dabasan.jxm.bd1;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 vertex
 * 
 * @author Daba
 *
 */
class BD1Vertex {
	private Vector position;
	private UV uv;
	private Vector normal;

	public BD1Vertex() {
		position = new Vector();
		uv = new UV();
		normal = new Vector();
	}

	public Vector getPosition() {
		return position;
	}
	public UV getUV() {
		return uv;
	}
	public Vector getNormal() {
		return normal;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setUV(UV uv) {
		this.uv = uv;
	}
	public void setNormal(Vector normal) {
		this.normal = normal;
	}
}
