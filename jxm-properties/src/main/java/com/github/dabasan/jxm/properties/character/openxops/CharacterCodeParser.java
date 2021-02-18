package com.github.dabasan.jxm.properties.character.openxops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.character.AILevel;
import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.CharacterModelType;
import com.github.dabasan.jxm.properties.character.CharacterTextureType;
import com.github.dabasan.jxm.properties.character.CharacterType;
import com.github.dabasan.jxm.properties.util.CPPArrayStringParser;

/**
 * Parses C++ code containing character data.
 * 
 * @author Daba
 *
 */
public class CharacterCodeParser {
	private Logger logger = LoggerFactory.getLogger(CharacterCodeParser.class);

	private CharacterVariableNameSettings settings;

	public CharacterCodeParser() {
		settings = new CharacterVariableNameSettings();
	}
	public CharacterCodeParser(CharacterVariableNameSettings settings) {
		this.settings = settings;
	}

	/**
	 * Parses C++ code containing character data.
	 * 
	 * @param code
	 *            Code
	 * @return Map containing character data
	 */
	public Map<Integer, CharacterData> parse(List<String> code) {
		var ret = new HashMap<Integer, CharacterData>();

		for (int i = 0; i < code.size(); i++) {
			var line = code.get(i);

			String[] parsed = CPPArrayStringParser.parse(line);
			if (parsed == null) {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}

			int arrayIndex;
			try {
				arrayIndex = Integer.parseInt(parsed[1]);
			} catch (NumberFormatException e) {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}

			if (ret.containsKey(arrayIndex) == false) {
				var character = new CharacterData();
				ret.put(arrayIndex, character);
			}
			var character = ret.get(arrayIndex);

			// Texture
			if (parsed[2].equals(settings.getTexture())) {
				int openXOPSTextureID;
				try {
					openXOPSTextureID = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				CharacterTextureType xopsTextureType = CharacterSpecifierConverter
						.getXOPSTextureTypeFromOpenXOPSTextureID(openXOPSTextureID);
				character.texture = xopsTextureType;
			}
			// Model
			else if (parsed[2].equals(settings.getModel())) {
				int modelSpc;
				try {
					modelSpc = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				character.model = CharacterModelType.values()[modelSpc];
			}
			// HP
			else if (parsed[2].equals(settings.getHP())) {
				int hp;
				try {
					hp = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				character.hp = hp;
			}
			// AILevel
			else if (parsed[2].equals(settings.getAILevel())) {
				int openXOPSAILevel;
				try {
					openXOPSAILevel = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				AILevel xopsAILevel = CharacterSpecifierConverter
						.getXOPSAILevelFromOpenXOPSAILevel(openXOPSAILevel);
				character.aiLevel = xopsAILevel;
			}
			// Weapon 0
			else if (parsed[2].equals(settings.getWeapon0())) {
				int weapon0;
				try {
					weapon0 = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				character.weapons.set(0, weapon0);
			}
			// Weapon 1
			else if (parsed[2].equals(settings.getWeapon1())) {
				int weapon1;
				try {
					weapon1 = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				character.weapons.set(1, weapon1);
			}
			// Type
			else if (parsed[2].equals(settings.getType())) {
				int typeSpc;
				try {
					typeSpc = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				character.type = CharacterType.values()[typeSpc];
			} else {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}
		}

		return ret;
	}
}
