package com.github.dabasan.jxm.properties.character.xcs;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

/**
 * XCS writer
 * 
 * @author Daba
 *
 */
class XCSWriter {
	public void write(OutputStream os, Character[] characters) throws IOException {
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
			addShortToBinLE(bin, (short) characters[i].texture.ordinal());
			// Model
			CharacterModelType modelType = characters[i].model;
			int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
			addShortToBinLE(bin, (short) modelTypeSpc);
			// HP
			addUnsignedShortToBinLE(bin, (short) characters[i].hp);
			// AI level
			addShortToBinLE(bin, (short) characters[i].aiLevel.ordinal());
			// Weapons
			addShortToBinLE(bin, characters[i].weapons.get(0).shortValue());
			addShortToBinLE(bin, characters[i].weapons.get(1).shortValue());
			// Type
			addShortToBinLE(bin, (short) characters[i].type.ordinal());
		}

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
		}
	}
}
