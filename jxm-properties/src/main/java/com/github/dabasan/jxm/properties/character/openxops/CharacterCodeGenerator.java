package com.github.dabasan.jxm.properties.character.openxops;

import java.util.List;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.util.CPPArrayStringGenerator;

/**
 * Generates C++ code containing character data.
 * 
 * @author Daba
 *
 */
public class CharacterCodeGenerator {
	private CharacterVariableNameSettings settings;

	public CharacterCodeGenerator() {
		settings = new CharacterVariableNameSettings();
	}
	public CharacterCodeGenerator(CharacterVariableNameSettings settings) {
		this.settings = settings;
	}

	/**
	 * Generates C++ code containing character data.
	 * 
	 * @param characters
	 *            List containing character data
	 * @return Code
	 */
	public String generate(List<CharacterData> characters) {
		String arrayName = settings.getArrayName();

		var sb = new StringBuilder();
		for (int i = 0; i < characters.size(); i++) {
			var character = characters.get(i);

			// Texture
			int openXOPSTextureID = CharacterSpecifierConverter
					.getOpenXOPSTextureIDFromXOPSTextureType(character.getTexture());
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getTexture(),
					openXOPSTextureID));
			sb.append("\n");
			// Model
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModel(),
					character.getModel().ordinal()));
			sb.append("\n");
			// HP
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getHP(),
					character.getHP()));
			sb.append("\n");
			// AILevel
			int openXOPSAILevel = CharacterSpecifierConverter
					.getOpenXOPSAILevelFromXOPSAILevel(character.getAILevel());
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getAILevel(),
					openXOPSAILevel));
			sb.append("\n");
			// Weapon[0]
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getWeapon0(),
					character.getWeapons()[0]));
			sb.append("\n");
			// Weapon[1]
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getWeapon1(),
					character.getWeapons()[1]));
			sb.append("\n");
			// Type
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getType(),
					character.getType().ordinal()));
			sb.append("\n");
		}

		return sb.toString();
	}
}
