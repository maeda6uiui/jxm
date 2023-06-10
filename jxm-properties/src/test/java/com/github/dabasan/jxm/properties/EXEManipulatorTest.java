package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import com.github.dabasan.jxm.properties.xops.XOPSVersion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
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
    private static final String TARGET_DIR = "./Data/XOPS";
    private Map<String, EXEManipulator> manipulators;
    private Map<String, XOPSVersion> expectedVersions;  //(filename, version)
    private Weapon[] expectedWeapons;
    private Character[] expectedCharacters;

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

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            Weapon[] actualWeapons = manipulator.getWeapons();
            for (int i = 0; i < actualWeapons.length; i++) {
                Weapon actualWeapon = actualWeapons[i];
                Weapon expectedWeapon = expectedWeapons[i];

                assertAll(
                        () -> assertEquals(expectedWeapon.name, actualWeapon.name),
                        () -> assertEquals(expectedWeapon.model, actualWeapon.model),
                        () -> assertEquals(expectedWeapon.texture, actualWeapon.texture),
                        () -> assertEquals(expectedWeapon.attacks, actualWeapon.attacks),
                        () -> assertEquals(expectedWeapon.penetration, actualWeapon.penetration),
                        () -> assertEquals(expectedWeapon.blazings, actualWeapon.blazings),
                        () -> assertEquals(expectedWeapon.speed, actualWeapon.speed),
                        () -> assertEquals(expectedWeapon.nbsMax, actualWeapon.nbsMax),
                        () -> assertEquals(expectedWeapon.reloads, actualWeapon.reloads),
                        () -> assertEquals(expectedWeapon.reaction, actualWeapon.reaction),
                        () -> assertEquals(expectedWeapon.errorRangeMin, actualWeapon.errorRangeMin),
                        () -> assertEquals(expectedWeapon.errorRangeMax, actualWeapon.errorRangeMax),
                        () -> assertEquals(expectedWeapon.modelPositionX, actualWeapon.modelPositionX),
                        () -> assertEquals(expectedWeapon.modelPositionY, actualWeapon.modelPositionY),
                        () -> assertEquals(expectedWeapon.modelPositionZ, actualWeapon.modelPositionZ),
                        () -> assertEquals(expectedWeapon.flashPositionX, actualWeapon.flashPositionX),
                        () -> assertEquals(expectedWeapon.flashPositionY, actualWeapon.flashPositionY),
                        () -> assertEquals(expectedWeapon.flashPositionZ, actualWeapon.flashPositionZ),
                        () -> assertEquals(expectedWeapon.yakkyouPositionX, actualWeapon.yakkyouPositionX),
                        () -> assertEquals(expectedWeapon.yakkyouPositionY, actualWeapon.yakkyouPositionY),
                        () -> assertEquals(expectedWeapon.yakkyouPositionZ, actualWeapon.yakkyouPositionZ),
                        () -> assertEquals(expectedWeapon.yakkyouSpeedX, actualWeapon.yakkyouSpeedY),
                        () -> assertEquals(expectedWeapon.blazingMode, actualWeapon.blazingMode),
                        () -> assertEquals(expectedWeapon.scopeMode, actualWeapon.scopeMode),
                        () -> assertEquals(expectedWeapon.size, actualWeapon.size),
                        () -> assertEquals(expectedWeapon.soundID, actualWeapon.soundID),
                        () -> assertEquals(expectedWeapon.soundVolume, actualWeapon.soundVolume),
                        () -> assertEquals(expectedWeapon.silencer, actualWeapon.silencer),
                        () -> assertEquals(expectedWeapon.weaponP, actualWeapon.weaponP),
                        () -> assertEquals(expectedWeapon.changeWeapon, actualWeapon.changeWeapon),
                        () -> assertEquals(expectedWeapon.burst, actualWeapon.burst)
                );
            }
        });
    }

    @Test
    public void testCharacters() {
        manipulators.values().forEach(manipulator -> {
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
