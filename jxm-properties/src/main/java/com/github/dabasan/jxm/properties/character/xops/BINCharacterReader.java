package com.github.dabasan.jxm.properties.character.xops;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import com.github.dabasan.jxm.properties.character.AILevel;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.CharacterTextureType;
import com.github.dabasan.jxm.properties.character.CharacterType;

/**
 * BIN character reader
 * 
 * @author Daba
 *
 */
class BINCharacterReader {
	private CharacterData[] characters;

	public BINCharacterReader(byte[] bin, int numCharacters, int dataStartPos) {
		characters = new CharacterData[numCharacters];

		int pos = dataStartPos;
		for (int i = 0; i < numCharacters; i++) {
			var character = new CharacterData();

			// Texture
			int textureTypeSpc = getShortValueFromBinLE(bin, pos);
			pos += 2;
			character.texture = CharacterTextureType.values()[textureTypeSpc];
			// Model
			int modelTypeSpc = getShortValueFromBinLE(bin, pos);
			pos += 2;
			character.model = CharacterBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);
			// HP
			character.hp = getUnsignedShortValueFromBinLE(bin, pos);
			pos += 2;
			// AI level
			int aiLevelSpc = getShortValueFromBinLE(bin, pos);
			pos += 2;
			character.aiLevel = AILevel.values()[aiLevelSpc];
			// Weapons
			int[] weapons = new int[2];
			weapons[0] = getShortValueFromBinLE(bin, pos);
			weapons[1] = getShortValueFromBinLE(bin, pos + 2);
			pos += 4;
			character.weapons.set(0, weapons[0]);
			character.weapons.set(1, weapons[1]);
			// Type
			int typeSpc = getShortValueFromBinLE(bin, pos);
			pos += 2;
			character.type = CharacterType.values()[typeSpc];

			characters[i] = character;
		}
	}

	public CharacterData[] getCharacterData() {
		return characters;
	}
}
