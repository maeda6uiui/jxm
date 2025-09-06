package com.github.maeda6uiui.jxm.bd1;

import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BD1 face generator
 *
 * @author maeda6uiui
 */
class BD1FaceGenerator {
    public static Map<Integer, List<BD1Face>> generateFaces(List<BD1Block> blocks) {
        var facesMap = new HashMap<Integer, List<BD1Face>>();

        for (var block : blocks) {
            Vector3f[] vertexPositions = block.vertexPositions;
            UV[] uvs = block.uvs;
            int[] textureIDs = block.textureIDs;

            //Calculate normals
            var normals = new Vector3f[6];

            for (int i = 0; i < 6; i++) {
                int[] vertexIndices = BD1Functions.getFaceCorrespondingVertexIndices(i);

                var v1 = new Vector3f();
                var v2 = new Vector3f();
                vertexPositions[vertexIndices[3]].sub(vertexPositions[vertexIndices[0]], v1);
                vertexPositions[vertexIndices[1]].sub(vertexPositions[vertexIndices[0]], v2);

                normals[i] = v1.cross(v2);
                normals[i].normalize(normals[i]);
            }

            //Generate faces
            var faces = new BD1Face[6];
            for (int i = 0; i < 6; i++) {
                faces[i] = new BD1Face();
            }

            for (int i = 0; i < 6; i++) {
                int[] vertexIndices = BD1Functions.getFaceCorrespondingVertexIndices(i);
                int[] uvIndices = BD1Functions.getFaceCorrespondingUVIndices(i);

                var faceVertexPositions = new Vector3f[4];
                var faceUVs = new UV[4];
                for (int j = 0; j < 4; j++) {
                    faceVertexPositions[j] = vertexPositions[vertexIndices[j]];
                    faceUVs[j] = uvs[uvIndices[j]];
                }

                faces[i].vertexPositions = faceVertexPositions;
                faces[i].uvs = faceUVs;
                faces[i].normal = normals[i];
            }

            for (int i = 0; i < 6; i++) {
                //Create a list for this texture ID if it does not exist
                if (!facesMap.containsKey(textureIDs[i])) {
                    var facesList = new ArrayList<BD1Face>();
                    facesMap.put(textureIDs[i], facesList);
                }
                //Add a face to the list
                var facesList = facesMap.get(textureIDs[i]);
                facesList.add(faces[i]);
            }
        }

        return facesMap;
    }
}
