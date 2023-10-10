package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeParser;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test CharacterCodeParser
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CharacterCodeParserTest {
    private Character[] expectedCharacters;
    private List<String> codeLines;

    @BeforeAll
    public void loadCharacters() {
        assertDoesNotThrow(() -> {
            var manipulator = new XCSManipulator("./TestData/Character/characters.xcs");
            expectedCharacters = manipulator.getCharacters();

            codeLines = Files.readAllLines(Paths.get("./TestData/character/character_code.txt"));
        });
    }

    @Test
    public void testParsedCharacters() {
        var settings = new CharacterVariableNameSettings();
        settings.arrayName = "人";
        settings.texture = "テクスチャ";

        var parser = new CharacterCodeParser(settings);
        Map<Integer, Character> actualCharacters = parser.parse(codeLines);
        actualCharacters.forEach((id, actualCharacter) -> {
            Character expectedCharacter = expectedCharacters[id];

            assertAll(
                    () -> assertEquals(expectedCharacter.texture, actualCharacter.texture),
                    () -> assertEquals(expectedCharacter.model, actualCharacter.model),
                    () -> assertEquals(expectedCharacter.hp, actualCharacter.hp),
                    () -> assertEquals(expectedCharacter.aiLevel, actualCharacter.aiLevel),
                    () -> assertArrayEquals(expectedCharacter.weapons.toArray(), actualCharacter.weapons.toArray()),
                    () -> assertEquals(expectedCharacter.type, actualCharacter.type)
            );
        });
    }
}
