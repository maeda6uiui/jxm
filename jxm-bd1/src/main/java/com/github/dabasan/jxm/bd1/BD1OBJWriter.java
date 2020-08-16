package com.github.dabasan.jxm.bd1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.github.dabasan.ejml_3dtools.Vector;

import de.javagl.obj.Mtl;
import de.javagl.obj.MtlWriter;
import de.javagl.obj.Mtls;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjWriter;
import de.javagl.obj.Objs;

/**
 * Writes out BD1 blocks into an OBJ file.
 * 
 * @author Daba
 *
 */
class BD1OBJWriter {
	public static void write(OutputStream osObj, OutputStream osMtl, String mtlFilename,
			List<BD1Block> blocks, Map<Integer, String> textureFilenames, boolean flipV)
			throws IOException {
		// Prepare faces.
		Map<Integer, List<BD1Face>> facesMap = BD1FaceGenerator.generateFaces(blocks);

		// Write out into an OBJ file.
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
			materialName = getFilenameWithoutDirectory(textureFilename);
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
				Vector[] vertexPositions = face.getVertexPositions();
				UV[] uvs = face.getUVs();
				Vector normal = face.getNormal();

				for (int i = 3; i >= 0; i--) {
					obj.addVertex(vertexPositions[i].getXFloat(), vertexPositions[i].getYFloat(),
							vertexPositions[i].getZFloat());
					if (flipV == true) {
						obj.addTexCoord(uvs[i].getUFloat(), uvs[i].getVFloat() * (-1.0f));
					} else {
						obj.addTexCoord(uvs[i].getUFloat(), uvs[i].getVFloat());
					}
					obj.addNormal(normal.getXFloat(), normal.getYFloat(), normal.getZFloat());
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
	private static String getFilenameWithoutDirectory(String filepath) {
		int lastSlashPos = filepath.lastIndexOf('/');
		if (lastSlashPos == -1) {
			return filepath;
		}

		return filepath.substring(lastSlashPos + 1);
	}
}
