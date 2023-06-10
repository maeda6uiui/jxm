package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.ids.IDSManipulator;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test IDSManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IDSManipulatorTest {
    private static final String TARGET_DIR = "./Data/Weapon";
    private IDSManipulator manipulator;
    private Weapon expectedWeapon;

    @BeforeAll
    public void loadWeapon() {
        try {
            manipulator = new IDSManipulator(Paths.get(TARGET_DIR, "mp5.ids").toString());

            var xgsManipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs").toString());
            expectedWeapon = xgsManipulator.getWeapons()[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWeapon() {
        Weapon actualWeapon = manipulator.getWeapon();

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
                () -> assertEquals(expectedWeapon.yakkyouSpeedX, actualWeapon.yakkyouSpeedX),
                () -> assertEquals(expectedWeapon.yakkyouSpeedY, actualWeapon.yakkyouSpeedY),
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

    @Test
    public void saveAsIDS() {
        var srcFilepath = Paths.get(TARGET_DIR, "mp5.ids").toString();
        var saveFilepath = Paths.get(TARGET_DIR, "mp5_2.ids").toString();
        assertDoesNotThrow(() -> manipulator.saveAsIDS(saveFilepath));

        String srcFileHash = TestUtils.getFileHash(srcFilepath);
        String outputFileHash = TestUtils.getFileHash(saveFilepath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
