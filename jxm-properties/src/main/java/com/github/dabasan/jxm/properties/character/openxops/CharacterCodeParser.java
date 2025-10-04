package com.github.dabasan.jxm.properties.character.openxops;

import com.github.dabasan.jxm.properties.character.*;
import com.github.dabasan.jxm.properties.util.CPPArrayStringParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses C++ code containing character data.
 *
 * @author maeda6uiui
 */
public class CharacterCodeParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CharacterVariableNameSettings settings;

    /**
     * Creates a code parser.
     */
    public CharacterCodeParser() {
        settings = new CharacterVariableNameSettings();
    }

    /**
     * Creates a code parser.
     *
     * @param settings variable name settings
     */
    public CharacterCodeParser(CharacterVariableNameSettings settings) {
        this.settings = settings;
    }

    /**
     * Parses C++ code containing character data.
     *
     * @param code C++ code
     * @return map containing character data
     */
    public Map<Integer, XOPSCharacter> parse(List<String> code) {
        var ret = new HashMap<Integer, XOPSCharacter>();

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

            if (!ret.containsKey(arrayIndex)) {
                var character = new XOPSCharacter();
                ret.put(arrayIndex, character);
            }
            var character = ret.get(arrayIndex);

            if (parsed[2].equals(settings.texture)) {
                int openXOPSTextureId;
                try {
                    openXOPSTextureId = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                CharacterTextureType xopsTextureType = CharacterSpecifierConverter
                        .getXOPSTextureTypeFromOpenXOPSTextureId(openXOPSTextureId);
                character.texture = xopsTextureType;
            } else if (parsed[2].equals(settings.model)) {
                int modelSpc;
                try {
                    modelSpc = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                character.model = CharacterModelType.values()[modelSpc];
            } else if (parsed[2].equals(settings.hp)) {
                int hp;
                try {
                    hp = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                character.hp = hp;
            } else if (parsed[2].equals(settings.aiLevel)) {
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
            } else if (parsed[2].equals(settings.weapon0)) {
                int weapon0;
                try {
                    weapon0 = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                character.weapons.set(0, weapon0);
            } else if (parsed[2].equals(settings.weapon1)) {
                int weapon1;
                try {
                    weapon1 = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                character.weapons.set(1, weapon1);
            } else if (parsed[2].equals(settings.type)) {
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
