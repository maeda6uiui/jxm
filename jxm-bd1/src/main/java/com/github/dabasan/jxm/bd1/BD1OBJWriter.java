package com.github.dabasan.jxm.bd1;

import de.javagl.obj.*;
import org.joml.Vector3fc;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Writes out BD1 blocks into an OBJ file.
 *
 * @author maeda6uiui
 */
class BD1OBJWriter {
    public static void write(OutputStream osObj, OutputStream osMtl, String mtlFilename,
                             List<BD1Block> blocks, Map<Integer, String> textureFilenames, boolean flipV)
            throws IOException {
        // Prepare faces
        Map<Integer, List<BD1Face>> facesMap = BD1FaceGenerator.generateFaces(blocks);

        // Write out into an OBJ file
        Obj obj = Objs.create();
        var mtls = new ArrayList<Mtl>();

        obj.setMtlFileNames(Arrays.asList(mtlFilename));
        obj.setActiveGroupNames(Arrays.asList("map"));

        int countIndex = 0;
        for (var entry : facesMap.entrySet()) {
            int textureID = entry.getKey();
            String textureFilename = textureFilenames.get(textureID);
            if (textureFilename == null) {
                textureFilename = "unknown_" + textureID;
            }

            String materialName;
            materialName = Paths.get(textureFilename).getFileName().toString();
            materialName = getFilepathWithoutExtension(materialName);
            materialName += "_" + textureID;

            Mtl mtl = Mtls.create(materialName);
            mtl.setNs(0.0f);
            mtl.setKa(1.0f, 1.0f, 1.0f);
            mtl.setKd(1.0f, 1.0f, 1.0f);
            mtl.setKs(0.0f, 0.0f, 0.0f);
            mtl.setD(1.0f);
            mtl.setNs(0.0f);
            mtl.setMapKd(textureFilename);
            mtls.add(mtl);

            obj.setActiveMaterialGroupName(materialName);

            var faces = entry.getValue();
            for (var face : faces) {
                Vector3fc[] vertexPositions = face.vertexPositions;
                UV[] uvs = face.uvs;
                Vector3fc normal = face.normal;

                for (int i = 3; i >= 0; i--) {
                    obj.addVertex(vertexPositions[i].x(), vertexPositions[i].y(),
                            vertexPositions[i].z());
                    if (flipV == true) {
                        obj.addTexCoord(uvs[i].u, uvs[i].v * (-1.0f));
                    } else {
                        obj.addTexCoord(uvs[i].u, uvs[i].v);
                    }
                    obj.addNormal(normal.x(), normal.y(), normal.z());
                }

                int[] indices = new int[]{countIndex, countIndex + 1, countIndex + 2,
                        countIndex + 3};
                obj.addFace(indices, indices, indices);

                countIndex += 4;
            }
        }

        ObjWriter.write(obj, osObj);
        MtlWriter.write(mtls, osMtl);
    }

    private static String getFilepathWithoutExtension(String filepath) {
        int lastDotPos = filepath.lastIndexOf('.');
        if (lastDotPos == -1) {
            return filepath;
        }

        return filepath.substring(0, lastDotPos);
    }
}
