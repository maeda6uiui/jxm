package com.github.dabasan.jxm.properties.character.xcs;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

/**
 * XCS writer
 * 
 * @author Daba
 *
 */
class XCSWriter {
	public XCSWriter() {

	}

	public void write(OutputStream os, CharacterData[] characters) throws IOException {
		var bin = new ArrayList<Byte>();

		bin.add((byte) 0x58);// X
		bin.add((byte) 0x43);// C
		bin.add((byte) 0x53);// S
		bin.add((byte) 0x00);
		bin.add((byte) 0x01);
		bin.add((byte) 0x00);
		bin.add((byte) 0x0C);
		bin.add((byte) 0x00);
		bin.add((byte) 0x2B);
		bin.add((byte) 0x00);
		bin.add((byte) 0x07);
		bin.add((byte) 0x00);

		int numCharacters = characters.length;
		for (int i = 0; i < numCharacters; i++) {
			// Texture
			ByteFunctions.addShortValueToBinLE(bin, (short) characters[i].getTexture().ordinal());
			// Model
			CharacterModelType modelType = characters[i].getModel();
			int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
			ByteFunctions.addShortValueToBinLE(bin, (short) modelTypeSpc);
			// HP
			ByteFunctions.addShortValueToBinLE(bin, (short) characters[i].getHP());
			// AI level
			ByteFunctions.addShortValueToBinLE(bin, (short) characters[i].getAILevel().ordinal());
			// Weapons
			int[] weapons = characters[i].getWeapons();
			ByteFunctions.addShortValueToBinLE(bin, (short) weapons[0]);
			ByteFunctions.addShortValueToBinLE(bin, (short) weapons[1]);
			// Type
			ByteFunctions.addShortValueToBinLE(bin, (short) characters[i].getType().ordinal());
		}

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
		}
	}
}
