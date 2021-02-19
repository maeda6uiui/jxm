package com.github.dabasan.jxm.properties.character.xops;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

/**
 * BIN character writer
 * 
 * @author Daba
 *
 */
class BINCharacterWriter {
	public void write(byte[] bin, Character[] characters, int dataStartPos) {
		int pos = dataStartPos;
		int numCharacters = characters.length;
		for (int i = 0; i < numCharacters; i++) {
			// Texture
			setShortValueToBinLE(bin, pos, (short) characters[i].texture.ordinal());
			pos += 2;
			// Model
			CharacterModelType modelType = characters[i].model;
			int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
			setShortValueToBinLE(bin, pos, (short) modelTypeSpc);
			pos += 2;
			// HP
			setUnsignedShortValueToBinLE(bin, pos, (short) characters[i].hp);
			pos += 2;
			// AI level
			setShortValueToBinLE(bin, pos, (short) characters[i].aiLevel.ordinal());
			pos += 2;
			// Weapons
			setShortValueToBinLE(bin, pos, characters[i].weapons.get(0).shortValue());
			setShortValueToBinLE(bin, pos + 2, characters[i].weapons.get(1).shortValue());
			pos += 4;
			// Type
			setShortValueToBinLE(bin, pos, (short) characters[i].type.ordinal());
			pos += 2;
		}
	}
}
