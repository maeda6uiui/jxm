package com.github.dabasan.jxm.properties.weapon;

import java.util.HashMap;
import java.util.Map;

/**
 * Model filepaths
 * 
 * @author Daba
 *
 */
public class ModelFilepaths {
	private static Map<Integer, String> modelFilepaths;

	static {
		modelFilepaths = new HashMap<>();

		modelFilepaths.put(0, "");
		modelFilepaths.put(1, "./data/model/weapon/mp5.x");
		modelFilepaths.put(2, "./data/model/weapon/psg1.x");
		modelFilepaths.put(3, "./data/model/weapon/m92f.x");
		modelFilepaths.put(4, "./data/model/weapon/glock18.x");
		modelFilepaths.put(5, "./data/model/weapon/de.x");
		modelFilepaths.put(6, "./data/model/weapon/mac10.x");
		modelFilepaths.put(7, "./data/model/weapon/ump.x");
		modelFilepaths.put(8, "./data/model/weapon/p90.x");
		modelFilepaths.put(9, "./data/model/weapon/m4.x");
		modelFilepaths.put(10, "./data/model/weapon/ak47.x");
		modelFilepaths.put(11, "./data/model/weapon/aug.x");
		modelFilepaths.put(12, "./data/model/weapon/m249.x");
		modelFilepaths.put(13, "./data/model/weapon/grenade.x");
		modelFilepaths.put(14, "./data/model/weapon/mp5sd.x");
		modelFilepaths.put(15, "./data/model/weapon/case.x");
		modelFilepaths.put(16, "./data/model/weapon/cg.x");
		modelFilepaths.put(17, "./data/model/weapon/m1.x");
		modelFilepaths.put(18, "./data/model/weapon/famas.x");
		modelFilepaths.put(19, "./data/model/weapon/mk23.x");
		modelFilepaths.put(20, "./data/model/weapon/mk23sd.x");
	}

	public static String getModelFilepath(int key) {
		return modelFilepaths.get(key);
	}
	public static void putModelFilepath(int key, String modelFilename) {
		modelFilepaths.put(key, modelFilename);
	}

	public static boolean keyExists(int key) {
		return modelFilepaths.containsKey(key);
	}

	public static int getIDFromFilepath(String filepath) {
		int id = -1;

		for (var entry : modelFilepaths.entrySet()) {
			String modelFilepath = entry.getValue();
			if (modelFilepath.equals(filepath)) {
				id = entry.getKey();
				break;
			}
		}

		return id;
	}
	public static ModelType getEnumFromFilepath(String filepath) {
		int id = getIDFromFilepath(filepath);
		return ModelType.values()[id];
	}
}
