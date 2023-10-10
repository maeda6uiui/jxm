package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test XCSManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XCSManipulatorTest {
    private static final String TARGET_DIR = "./TestData/Character";
    private XCSManipulator manipulator;
    private Character[] expectedCharacters;

    @BeforeAll
    public void loadCharacters() {
        assertDoesNotThrow(() -> {
            manipulator = new XCSManipulator(Paths.get(TARGET_DIR, "characters.xcs").toString());

            var exeManipulator = new EXEManipulator(Paths.get(TARGET_DIR, "xops0975t.exe").toString());
            expectedCharacters = exeManipulator.getCharacters();
        });
    }

    @Test
    public void testCharacters() {
        Character[] actualCharacters = manipulator.getCharacters();
        for (int i = 0; i < actualCharacters.length; i++) {
            Character actualCharacter = actualCharacters[i];
            Character expectedCharacter = expectedCharacters[i];

            assertAll(
                    () -> assertEquals(expectedCharacter.texture, actualCharacter.texture),
                    () -> assertEquals(expectedCharacter.model, actualCharacter.model),
                    () -> assertEquals(expectedCharacter.hp, actualCharacter.hp),
                    () -> assertEquals(expectedCharacter.aiLevel, actualCharacter.aiLevel),
                    () -> assertArrayEquals(expectedCharacter.weapons.toArray(), actualCharacter.weapons.toArray()),
                    () -> assertEquals(expectedCharacter.type, actualCharacter.type)
            );
        }
    }

    @Test
    public void saveAsXCS() {
        var srcFilepath = Paths.get(TARGET_DIR, "characters.xcs").toString();
        var outputFilepath = Paths.get(TARGET_DIR, "characters_2.xcs").toString();
        assertDoesNotThrow(() -> manipulator.saveAsXCS(outputFilepath));

        String srcFileHash = TestUtils.getFileHash(srcFilepath);
        String outputFileHash = TestUtils.getFileHash(outputFilepath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
