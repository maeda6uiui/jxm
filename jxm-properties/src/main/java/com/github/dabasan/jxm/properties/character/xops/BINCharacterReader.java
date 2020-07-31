package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.character.AILevel;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.CharacterModelType;
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
			int textureTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			CharacterTextureType textureType = CharacterTextureType.values()[textureTypeSpc];
			character.setTexture(textureType);
			// Model
			int modelTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			CharacterModelType modelType = CharacterBinEnumConverter
					.getModelTypeFromBinSpecifier(modelTypeSpc);
			character.setModel(modelType);
			// HP
			character.setHP(ByteFunctions.getUnsignedShortValueFromBinLE(bin, pos));
			pos += 2;
			// AI level
			int aiLevelSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			AILevel aiLevel = AILevel.values()[aiLevelSpc];
			character.setAILevel(aiLevel);
			// Weapons
			int[] weapons = new int[2];
			weapons[0] = ByteFunctions.getShortValueFromBinLE(bin, pos);
			weapons[1] = ByteFunctions.getShortValueFromBinLE(bin, pos + 2);
			pos += 4;
			character.setWeapons(weapons);
			// Type
			int typeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			CharacterType type = CharacterType.values()[typeSpc];
			character.setType(type);

			characters[i] = character;
		}
	}

	public CharacterData[] getCharacterData() {
		return characters;
	}
}
