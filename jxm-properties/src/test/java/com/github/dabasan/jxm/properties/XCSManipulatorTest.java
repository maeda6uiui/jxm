package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.JXMCharacter;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;
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
    private JXMCharacter[] expectedCharacters;

    @BeforeAll
    public void loadCharacters() {
        assertDoesNotThrow(() -> {
            manipulator = new XCSManipulator(Paths.get(TARGET_DIR, "characters.xcs"));

            var exeManipulator = new EXEManipulator(Paths.get(TARGET_DIR, "xops0975t.exe"));
            expectedCharacters = exeManipulator.getCharacters();
        });
    }

    @Test
    public void testCharacters() {
        JXMCharacter[] actualCharacters = manipulator.getCharacters();
        assertArrayEquals(expectedCharacters, actualCharacters);
    }

    @Test
    public void testUpdateCharacters() {
        JXMCharacter[] currentCharacters = manipulator.getCharacters();

        var newCharacters = new JXMCharacter[currentCharacters.length];
        for (int i = 0; i < currentCharacters.length; i++) {
            newCharacters[i] = TestUtils.generateRandomCharacter();
        }
        manipulator.setCharacters(newCharacters);
        assertArrayEquals(newCharacters, manipulator.getCharacters());

        manipulator.setCharacters(currentCharacters);
    }

    @Test
    public void testSave() {
        Path srcPath = Paths.get(TARGET_DIR, "characters.xcs");
        Path outputPath = Paths.get(TARGET_DIR, "characters_2.xcs");
        assertDoesNotThrow(() -> manipulator.saveAsXCS(outputPath));

        String srcFileHash = TestUtils.getFileHash(srcPath);
        String outputFileHash = TestUtils.getFileHash(outputPath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
