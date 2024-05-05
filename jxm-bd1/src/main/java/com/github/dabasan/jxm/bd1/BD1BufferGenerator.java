package com.github.dabasan.jxm.bd1;

import org.joml.Vector3fc;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BD1 buffer generator
 *
 * @author maeda6uiui
 */
class BD1BufferGenerator {
    public static List<BD1Buffer> generateBuffers(Map<Integer, List<BD1Face>> facesMap,
                                                  boolean flipV) {
        var buffers = new ArrayList<BD1Buffer>();

        for (var entry : facesMap.entrySet()) {
            var indexValues = new ArrayList<Integer>();
            var posValues = new ArrayList<Float>();
            var uvValues = new ArrayList<Float>();
            var normValues = new ArrayList<Float>();

            int countIndex = 0;
            for (var face : entry.getValue()) {
                Vector3fc[] vertexPositions = face.vertexPositions;
                UV[] uvs = face.uvs;
                Vector3fc normal = face.normal;

                //First triangle
                indexValues.add(countIndex);
                indexValues.add(countIndex + 1);
                indexValues.add(countIndex + 2);
                //Second triangle
                indexValues.add(countIndex + 2);
                indexValues.add(countIndex + 3);
                indexValues.add(countIndex);
                countIndex += 4;

                for (int i = 3; i >= 0; i--) {
                    //Position
                    posValues.add(vertexPositions[i].x());
                    posValues.add(vertexPositions[i].y());
                    posValues.add(vertexPositions[i].z());
                    //UVs
                    uvValues.add(uvs[i].u);
                    if (flipV) {
                        uvValues.add(uvs[i].v * (-1.0f));
                    } else {
                        uvValues.add(uvs[i].v);
                    }
                    //Normal
                    normValues.add(normal.x());
                    normValues.add(normal.y());
                    normValues.add(normal.z());
                }
            }

            IntBuffer indexBuffer = IntBuffer.allocate(indexValues.size());
            FloatBuffer posBuffer = FloatBuffer.allocate(posValues.size());
            FloatBuffer uvBuffer = FloatBuffer.allocate(uvValues.size());
            FloatBuffer normBuffer = FloatBuffer.allocate(normValues.size());
            for (var indexValue : indexValues) {
                indexBuffer.put(indexValue);
            }
            for (var posValue : posValues) {
                posBuffer.put(posValue);
            }
            for (var uvValue : uvValues) {
                uvBuffer.put(uvValue);
            }
            for (var normValue : normValues) {
                normBuffer.put(normValue);
            }
            indexBuffer.flip();
            posBuffer.flip();
            uvBuffer.flip();
            normBuffer.flip();

            var buffer = new BD1Buffer();
            buffer.indexBuffer = indexBuffer;
            buffer.posBuffer = posBuffer;
            buffer.uvBuffer = uvBuffer;
            buffer.normBuffer = normBuffer;

            int textureID = entry.getKey();
            buffer.textureID = textureID;

            buffers.add(buffer);
        }

        return buffers;
    }
}
