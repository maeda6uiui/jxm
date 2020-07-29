package com.github.dabasan.jxm.properties.weapon;

import java.util.HashMap;
import java.util.Map;

/**
 * Texture filenames
 * 
 * @author Daba
 *
 */
public class TextureFilepaths {
	private static Map<Integer, String> textureFilepaths;

	static {
		textureFilepaths = new HashMap<>();

		textureFilepaths.put(0, "");
		textureFilepaths.put(1, "./data/model/weapon/mp5.bmp");
		textureFilepaths.put(2, "./data/model/weapon/psg1.bmp");
		textureFilepaths.put(3, "./data/model/weapon/m92f.bmp");
		textureFilepaths.put(4, "./data/model/weapon/glock18.bmp");
		textureFilepaths.put(5, "./data/model/weapon/de.bmp");
		textureFilepaths.put(6, "./data/model/weapon/mac10.bmp");
		textureFilepaths.put(7, "./data/model/weapon/ump.bmp");
		textureFilepaths.put(8, "./data/model/weapon/p90.bmp");
		textureFilepaths.put(9, "./data/model/weapon/m4.bmp");
		textureFilepaths.put(10, "./data/model/weapon/ak47.bmp");
		textureFilepaths.put(11, "./data/model/weapon/aug.bmp");
		textureFilepaths.put(12, "./data/model/weapon/m249.bmp");
		textureFilepaths.put(13, "./data/model/weapon/grenade.bmp");
		textureFilepaths.put(14, "./data/model/weapon/mp5sd.bmp");
		textureFilepaths.put(15, "./data/model/weapon/case.bmp");
		textureFilepaths.put(16, "./data/model/weapon/cg.bmp");
		textureFilepaths.put(17, "./data/model/weapon/glock17.bmp");
		textureFilepaths.put(18, "./data/model/weapon/m1.bmp");
		textureFilepaths.put(19, "./data/model/weapon/famas.bmp");
		textureFilepaths.put(20, "./data/model/weapon/mk23.bmp");
	}

	public static String getTextureFilepath(int key) {
		return textureFilepaths.get(key);
	}
	public static void putTextureFilepath(int key, String textureFilepath) {
		textureFilepaths.put(key, textureFilepath);
	}

	public static boolean keyExists(int key) {
		return textureFilepaths.containsKey(key);
	}
}
