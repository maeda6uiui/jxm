package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeGenerator;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test CharacterCodeGenerator
 *
 * @author maeda6uiui
 */
public class CharacterCodeGeneratorTest {
    private XCSManipulator manipulator;
    private String expectedCode;

    @BeforeAll
    public void loadCharacters() {
        try {
            manipulator = new XCSManipulator("./Data/Character/characters.xcs");
            expectedCode = Files.readString(Paths.get("./Data/Character/character_code.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGeneratedCode() {
        var settings = new CharacterVariableNameSettings();
        settings.arrayName = "人";
        settings.texture = "テクスチャ";

        var generator = new CharacterCodeGenerator(settings);

        Character[] characters = manipulator.getCharacters();
        String actualCode = generator.generate(Arrays.asList(characters));

        assertEquals(expectedCode, actualCode);
    }
}
