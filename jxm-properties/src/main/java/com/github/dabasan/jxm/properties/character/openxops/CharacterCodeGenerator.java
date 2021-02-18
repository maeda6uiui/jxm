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
		var sb = new StringBuilder();
		for (int i = 0; i < characters.size(); i++) {
			var character = characters.get(i);

			// Texture
			int openXOPSTextureID = CharacterSpecifierConverter
					.getOpenXOPSTextureIDFromXOPSTextureType(character.texture);
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.texture,
					openXOPSTextureID));
			sb.append("\n");
			// Model
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.model,
					character.model.ordinal()));
			sb.append("\n");
			// HP
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.hp,
					character.hp));
			sb.append("\n");
			// AILevel
			int openXOPSAILevel = CharacterSpecifierConverter
					.getOpenXOPSAILevelFromXOPSAILevel(character.aiLevel);
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.aiLevel,
					openXOPSAILevel));
			sb.append("\n");
			// Weapon[0]
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.weapon0,
					character.weapons.get(0)));
			sb.append("\n");
			// Weapon[1]
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.weapon1,
					character.weapons.get(1)));
			sb.append("\n");
			// Type
			sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.type,
					character.type.ordinal()));
			sb.append("\n");
		}

		return sb.toString();
	}
}
