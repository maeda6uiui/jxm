package com.github.dabasan.jxm.properties.character.xcs;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.dabasan.jxm.properties.character.AILevel;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.CharacterTextureType;
import com.github.dabasan.jxm.properties.character.CharacterType;

/**
 * XCS reader
 * 
 * @author Daba
 *
 */
class XCSReader {
	private Character[] characters;

	public XCSReader(InputStream is, int numCharacters) throws IOException {
		characters = new Character[numCharacters];

		// Read all bytes from a stream
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0x0000000C;

		for (int i = 0; i < numCharacters; i++) {
			var character = new Character();

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

	public Character[] getCharacterData() {
		return characters;
	}
}
