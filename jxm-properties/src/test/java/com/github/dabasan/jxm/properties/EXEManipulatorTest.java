package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.JXMCharacter;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import com.github.dabasan.jxm.properties.weapon.JXMWeapon;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import com.github.dabasan.jxm.properties.xops.XOPSVersion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test EXEManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EXEManipulatorTest {
    private static final String TARGET_DIR = "./TestData/XOPS";
    private Map<String, EXEManipulator> manipulators;
    private Map<String, XOPSVersion> expectedVersions;  //(filename, version)
    private JXMWeapon[] expectedWeapons;
    private JXMCharacter[] expectedCharacters;

    @BeforeAll
    public void prepareForTest() {
        var srcFilenames = new ArrayList<String>();
        srcFilenames.add("xops096.exe");
        srcFilenames.add("xops096t.exe");
        srcFilenames.add("xops097ft.exe");
        srcFilenames.add("xops0975t.exe");
        srcFilenames.add("xopsolt18f2.exe");
        srcFilenames.add("xopsolt19f2.exe");

        expectedVersions = new HashMap<>();
        expectedVersions.put("xops096.exe", XOPSVersion.XOPS096);
        expectedVersions.put("xops096t.exe", XOPSVersion.XOPS096T);
        expectedVersions.put("xops097ft.exe", XOPSVersion.XOPS097FT);
        expectedVersions.put("xops0975t.exe", XOPSVersion.XOPS0975T);
        expectedVersions.put("xopsolt18f2.exe", XOPSVersion.XOPSOLT18F2);
        expectedVersions.put("xopsolt19f2.exe", XOPSVersion.XOPSOLT19F2);

        assertDoesNotThrow(() -> {
            for (var srcFilename : srcFilenames) {
                Path originalPath = Paths.get(TARGET_DIR, "Original", srcFilename);
                Path copyPath = Paths.get(TARGET_DIR, srcFilename);
                Files.copy(originalPath, copyPath, StandardCopyOption.REPLACE_EXISTING);
            }

            manipulators = new HashMap<>();
            for (var srcFilename : srcFilenames) {
                var manipulator = new EXEManipulator(Paths.get(TARGET_DIR, srcFilename).toString());
                manipulators.put(srcFilename, manipulator);
            }

            var xgsManipulator = new XGSManipulator(Paths.get(TARGET_DIR, "Expected", "weapons.xgs").toString());
            expectedWeapons = xgsManipulator.getWeapons();

            var xcsManipulator = new XCSManipulator(Paths.get(TARGET_DIR, "Expected", "characters.xcs").toString());
            expectedCharacters = xcsManipulator.getCharacters();
        });
    }

    @Test
    public void testXOPSVersion() {
        manipulators.forEach((filename, manipulator) -> {
            XOPSVersion actualVersion = manipulator.getSrcXOPSVersion();
            XOPSVersion expectedVersion = expectedVersions.get(filename);
            assertEquals(expectedVersion, actualVersion);
        });
    }

    @Test
    public void testWeapons() {
        manipulators.values().forEach(manipulator -> {
            JXMWeapon[] actualWeapons = manipulator.getWeapons();
            assertArrayEquals(expectedWeapons, actualWeapons);
        });
    }

    @Test
    public void testUpdateWeapons() {
        manipulators.values().forEach(manipulator -> {
            JXMWeapon[] currentWeapons = manipulator.getWeapons();

            var newWeapons = new JXMWeapon[currentWeapons.length];
            for (int i = 0; i < currentWeapons.length; i++) {
                newWeapons[i] = TestUtils.generateRandomWeapon();
            }
            manipulator.setWeapons(newWeapons);
            assertArrayEquals(newWeapons, manipulator.getWeapons());

            manipulator.setWeapons(currentWeapons);
        });
    }

    @Test
    public void testCharacters() {
        manipulators.values().forEach(manipulator -> {
            JXMCharacter[] actualCharacters = manipulator.getCharacters();
            assertArrayEquals(expectedCharacters, actualCharacters);
        });
    }

    @Test
    public void testUpdateCharacters() {
        manipulators.values().forEach(manipulator -> {
            JXMCharacter[] currentCharacters = manipulator.getCharacters();

            var newCharacters = new JXMCharacter[currentCharacters.length];
            for (int i = 0; i < currentCharacters.length; i++) {
                newCharacters[i] = TestUtils.generateRandomCharacter();
            }
            manipulator.setCharacters(newCharacters);
            assertArrayEquals(newCharacters, manipulator.getCharacters());

            manipulator.setCharacters(currentCharacters);
        });
    }

    @Test
    public void write() {
        manipulators.forEach((filename, manipulator) -> {
            String exeFilepath = Paths.get(TARGET_DIR, filename).toString();
            String backupFilepath = Paths.get(TARGET_DIR, filename + ".backup").toString();
            assertDoesNotThrow(() -> manipulator.write(exeFilepath, backupFilepath));
        });

        manipulators.keySet().forEach(filename -> {
            String targetFilepath = Paths.get(TARGET_DIR, filename).toString();
            String backupFilepath = Paths.get(TARGET_DIR, filename + ".backup").toString();
            String originalFilepath = Paths.get(TARGET_DIR, "Original", filename).toString();

            String targetFileHash = TestUtils.getFileHash(targetFilepath);
            String backupFileHash = TestUtils.getFileHash(backupFilepath);
            String originalFileHash = TestUtils.getFileHash(originalFilepath);

            assertAll(
                    () -> assertEquals(targetFileHash, backupFileHash),
                    () -> assertEquals(backupFileHash, originalFileHash)
            );
        });
    }
}
