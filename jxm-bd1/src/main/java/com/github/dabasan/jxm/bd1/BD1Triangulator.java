package com.github.dabasan.jxm.bd1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 triangulator
 * 
 * @author Daba
 *
 */
class BD1Triangulator {
	private List<BD1Triangle> triangles;

	public BD1Triangulator() {
		triangles = new ArrayList<>();
	}

	public void triangulateBlock(BD1Block block) {
		Vector[] positions = block.getVertexPositions();
		UV[] uvs = block.getUVs();

		var vertices = new BD1Vertex[24];
		for (int i = 0; i < 24; i++) {
			vertices[i] = new BD1Vertex();
		}

		for (int i = 0; i < 6; i++) {
			int[] vertexIndices = BD1Functions.getFaceCorrespondingVertexIndices(i);
			int[] uvIndices = BD1Functions.getFaceCorrespondingUVIndices(i);

			var v1 = positions[vertexIndices[3]].sub(positions[vertexIndices[0]]);
			var v2 = positions[vertexIndices[1]].sub(positions[vertexIndices[0]]);
			var faceNormal = v1.cross(v2).normalize();

			for (int j = 0; j < 4; j++) {
				int arrayIndex = i * 4 + j;
				int vertexIndex = vertexIndices[j];
				int uvIndex = uvIndices[j];

				vertices[arrayIndex].setPosition(positions[vertexIndex]);
				vertices[arrayIndex].setUV(uvs[uvIndex]);
				vertices[arrayIndex].setNormal(faceNormal);
			}
		}

		var reverseVertices = new BD1Vertex[24];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				reverseVertices[i * 4 + j] = vertices[(i + 1) * 4 - 1 - j];
			}
		}

		var bd1Triangles = new BD1Triangle[12];
		for (int i = 0; i < 12; i++) {
			bd1Triangles[i] = new BD1Triangle();
		}

		for (int i = 0; i < 12; i += 2) {
			int vertexArrayIndex;
			var triangleVertices = new BD1Vertex[3];

			for (int j = 0; j < 3; j++) {
				vertexArrayIndex = (i / 2) * 4 + j;
				triangleVertices[j] = reverseVertices[vertexArrayIndex];
			}
			bd1Triangles[i].setVertices(triangleVertices);

			for (int j = 0; j < 3; j++) {
				vertexArrayIndex = (i / 2) * 4 + (j + 2) % 4;
				triangleVertices[j] = reverseVertices[vertexArrayIndex];
			}
			bd1Triangles[i + 1].setVertices(triangleVertices);
		}

		int[] textureIDs = block.getTextureIDs();
		for (int i = 0; i < 12; i += 2) {
			int textureID = textureIDs[i / 2];
			bd1Triangles[i].setTextureID(textureID);
			bd1Triangles[i + 1].setTextureID(textureID);
		}

		for (int i = 0; i < 12; i++) {
			triangles.add(bd1Triangles[i]);
		}
	}
	public void triangulateBlocks(List<BD1Block> blocks) {
		for (var block : blocks) {
			this.triangulateBlock(block);
		}
	}

	public List<BD1Triangle> getTriangles() {
		return triangles;
	}
	public Map<Integer, List<BD1Triangle>> getTrianglesPerTextureID() {
		var ret = new HashMap<Integer, List<BD1Triangle>>();

		for (var triangle : triangles) {
			int textureID = triangle.getTextureID();

			if (ret.containsKey(textureID) == false) {
				var listTmp = new ArrayList<BD1Triangle>();
				ret.put(textureID, listTmp);
			}
			var listTmp = ret.get(textureID);
			listTmp.add(triangle);
		}

		return ret;
	}
}
