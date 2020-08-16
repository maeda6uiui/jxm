package com.github.dabasan.jxm.bd1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 face generator
 * 
 * @author Daba
 *
 */
class BD1FaceGenerator {
	public static Map<Integer, List<BD1Face>> generateFaces(List<BD1Block> blocks) {
		var facesMap = new HashMap<Integer, List<BD1Face>>();

		for (var block : blocks) {
			Vector[] vertexPositions = block.getVertexPositions();
			UV[] uvs = block.getUVs();
			int[] textureIDs = block.getTextureIDs();

			// Calculate normals.
			var normals = new Vector[6];

			for (int i = 0; i < 6; i++) {
				int[] vertexIndices = BD1Functions.getFaceCorrespondingVertexIndices(i);

				var v1 = vertexPositions[vertexIndices[3]].sub(vertexPositions[vertexIndices[0]]);
				var v2 = vertexPositions[vertexIndices[1]].sub(vertexPositions[vertexIndices[0]]);

				normals[i] = v1.cross(v2);
				normals[i] = normals[i].normalize();
			}

			// Generate faces.
			var faces = new BD1Face[6];
			for (int i = 0; i < 6; i++) {
				faces[i] = new BD1Face();
			}

			for (int i = 0; i < 6; i++) {
				int[] vertexIndices = BD1Functions.getFaceCorrespondingVertexIndices(i);
				int[] uvIndices = BD1Functions.getFaceCorrespondingUVIndices(i);

				var faceVertexPositions = new Vector[4];
				var faceUVs = new UV[4];
				for (int j = 0; j < 4; j++) {
					faceVertexPositions[j] = vertexPositions[vertexIndices[j]];
					faceUVs[j] = uvs[uvIndices[j]];
				}

				faces[i].setVertexPositions(faceVertexPositions);
				faces[i].setUVs(faceUVs);
				faces[i].setNormal(normals[i]);
			}

			for (int i = 0; i < 6; i++) {
				// Create a list for this texture ID if it does not exist.
				if (facesMap.containsKey(textureIDs[i]) == false) {
					var facesList = new ArrayList<BD1Face>();
					facesMap.put(textureIDs[i], facesList);
				}
				// Add a face to the list.
				var facesList = facesMap.get(textureIDs[i]);
				facesList.add(faces[i]);
			}
		}

		return facesMap;
	}
}
