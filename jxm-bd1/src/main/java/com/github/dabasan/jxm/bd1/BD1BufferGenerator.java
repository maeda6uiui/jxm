package com.github.dabasan.jxm.bd1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 buffer generator
 * 
 * @author Daba
 *
 */
class BD1BufferGenerator {
	public static List<BD1Buffer> generateBuffers(Map<Integer, List<BD1Face>> facesMap) {
		var buffers = new ArrayList<BD1Buffer>();

		var indexValues = new ArrayList<Integer>();
		var posValues = new ArrayList<Float>();
		var uvValues = new ArrayList<Float>();
		var normValues = new ArrayList<Float>();

		for (var entry : facesMap.entrySet()) {
			var buffer = new BD1Buffer();

			int textureID = entry.getKey();
			buffer.setTextureID(textureID);

			int countIndex = 0;
			for (var face : entry.getValue()) {
				Vector[] vertexPositions = face.getVertexPositions();
				UV[] uvs = face.getUVs();
				Vector normal = face.getNormal();

				// First triangle
				indexValues.add(countIndex);
				indexValues.add(countIndex + 1);
				indexValues.add(countIndex + 2);
				// Second triangle
				indexValues.add(countIndex + 2);
				indexValues.add(countIndex + 3);
				indexValues.add(countIndex);
				countIndex += 4;

				for (int i = 3; i >= 0; i--) {
					// Position
					posValues.add(vertexPositions[i].getXFloat());
					posValues.add(vertexPositions[i].getYFloat());
					posValues.add(vertexPositions[i].getZFloat());
					// UV
					uvValues.add(uvs[i].getUFloat());
					uvValues.add(uvs[i].getVFloat() * (-1.0f));
					// Normal
					normValues.add(normal.getXFloat());
					normValues.add(normal.getYFloat());
					normValues.add(normal.getZFloat());
				}
			}

			buffers.add(buffer);
		}

		return buffers;
	}
}
