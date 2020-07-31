package com.github.dabasan.jxm.properties.character.xops;

import java.util.List;

import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

/**
 * BIN character writer
 * 
 * @author Daba
 *
 */
class BINCharacterWriter {
	public BINCharacterWriter() {

	}

	public void write(List<Byte> bin, CharacterData[] characters, int dataStartPos) {
		int pos = dataStartPos;

		int numCharacters = characters.length;
		for (int i = 0; i < numCharacters; i++) {
			// Texture
			ByteFunctions.setShortValueToBinLE(bin, pos,
					(short) characters[i].getTexture().ordinal());
			pos += 2;
			// Model
			CharacterModelType modelType = characters[i].getModel();
			int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) modelTypeSpc);
			pos += 2;
			// HP
			ByteFunctions.setUnsignedShortValueToBinLE(bin, pos, (short) characters[i].getHP());
			pos += 2;
			// AI level
			ByteFunctions.setShortValueToBinLE(bin, pos,
					(short) characters[i].getAILevel().ordinal());
			pos += 2;
			// Weapons
			int[] weapons = characters[i].getWeapons();
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[0]);
			ByteFunctions.setShortValueToBinLE(bin, pos + 2, (short) weapons[1]);
			pos += 4;
			// Type
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) characters[i].getType().ordinal());
			pos += 2;
		}
	}
}
