package com.github.maeda6uiui.jxm.properties;

import com.github.maeda6uiui.jxm.properties.character.Character;
import com.github.maeda6uiui.jxm.properties.character.openxops.CharacterCodeGenerator;
import com.github.maeda6uiui.jxm.properties.character.openxops.CharacterVariableNameSettings;
import com.github.maeda6uiui.jxm.properties.character.xcs.XCSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * Test CharacterCodeGenerator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CharacterCodeGeneratorTest {
    private XCSManipulator manipulator;
    private List<String> expectedLines;

    @BeforeAll
    public void loadCharacters() {
        assertDoesNotThrow(() -> {
            manipulator = new XCSManipulator("./TestData/Character/characters.xcs");
            expectedLines = Files.readAllLines(Paths.get("./TestData/Character/character_code.txt"));
        });
    }

    @Test
    public void testGeneratedCode() {
        var settings = new CharacterVariableNameSettings();
        settings.arrayName = "人";
        settings.texture = "テクスチャ";

        var generator = new CharacterCodeGenerator(settings);

        Character[] characters = manipulator.getCharacters();
        String actualCode = generator.generate(Arrays.asList(characters));
        String[] actualLines = actualCode.split("\n");
        assertLinesMatch(expectedLines, Arrays.asList(actualLines));
    }
}
