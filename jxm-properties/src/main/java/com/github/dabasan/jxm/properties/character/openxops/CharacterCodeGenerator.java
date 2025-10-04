package com.github.dabasan.jxm.properties.character.openxops;

import com.github.dabasan.jxm.properties.character.XOPSCharacter;
import com.github.dabasan.jxm.properties.util.CPPArrayStringGenerator;

import java.util.List;

/**
 * Generates C++ code containing character data.
 *
 * @author maeda6uiui
 */
public class CharacterCodeGenerator {
    private final CharacterVariableNameSettings settings;
    private StringBuilder sb;

    /**
     * Creates a code generator.
     */
    public CharacterCodeGenerator() {
        settings = new CharacterVariableNameSettings();
    }

    /**
     * Creates a code generator.
     *
     * @param settings variable name settings
     */
    public CharacterCodeGenerator(CharacterVariableNameSettings settings) {
        this.settings = settings;
    }

    private void appendToBuffer(String arrayName, int index, String fieldName, Object value) {
        sb.append(CPPArrayStringGenerator.generate(arrayName, index, fieldName, value));
        sb.append("\n");
    }

    /**
     * Generates C++ code containing character data.
     *
     * @param characters list containing character data
     * @return C++ code
     */
    public String generate(List<XOPSCharacter> characters) {
        sb = new StringBuilder();
        for (int i = 0; i < characters.size(); i++) {
            var character = characters.get(i);

            int openXOPSTextureId = CharacterSpecifierConverter
                    .getOpenXOPSTextureIdFromXOPSTextureType(character.texture);
            this.appendToBuffer(settings.arrayName, i, settings.texture, openXOPSTextureId);

            this.appendToBuffer(settings.arrayName, i, settings.model, character.model.ordinal());
            this.appendToBuffer(settings.arrayName, i, settings.hp, character.hp);

            int openXOPSAILevel = CharacterSpecifierConverter
                    .getOpenXOPSAILevelFromXOPSAILevel(character.aiLevel);
            this.appendToBuffer(settings.arrayName, i, settings.aiLevel, openXOPSAILevel);

            this.appendToBuffer(settings.arrayName, i, settings.weapon0, character.weapons.get(0));
            this.appendToBuffer(settings.arrayName, i, settings.weapon1, character.weapons.get(1));
            this.appendToBuffer(settings.arrayName, i, settings.type, character.type.ordinal());
        }

        return sb.toString();
    }
}
