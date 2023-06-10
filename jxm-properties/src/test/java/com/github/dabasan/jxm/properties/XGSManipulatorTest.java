package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test XGSManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XGSManipulatorTest {
    private static final String TARGET_DIR = "./Data/Weapon";
    private XGSManipulator manipulator;
    private Weapon[] expectedWeapons;

    @BeforeAll
    public void loadWeapons() {
        try {
            manipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs").toString());

            var exeManipulator = new EXEManipulator(Paths.get(TARGET_DIR, "xops0975t.exe").toString());
            expectedWeapons = exeManipulator.getWeapons();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWeapons() {
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
    }

    @Test
    public void saveAsXGS() {
        var srcFilepath = Paths.get(TARGET_DIR, "weapons.xgs").toString();
        var outputFilepath = Paths.get(TARGET_DIR, "weapons_2.xgs").toString();
        assertDoesNotThrow(() -> manipulator.saveAsXGS(outputFilepath));

        String srcFileHash = TestUtils.getFileHash(srcFilepath);
        String outputFileHash = TestUtils.getFileHash(outputFilepath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
