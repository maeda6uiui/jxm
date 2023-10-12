package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.ids.IDSManipulator;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test IDSManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IDSManipulatorTest {
    private static final String TARGET_DIR = "./TestData/Weapon";
    private IDSManipulator manipulator;
    private Weapon expectedWeapon;

    @BeforeAll
    public void loadWeapon() {
        assertDoesNotThrow(() -> {
            manipulator = new IDSManipulator(Paths.get(TARGET_DIR, "mp5.ids").toString());

            var xgsManipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs").toString());
            expectedWeapon = xgsManipulator.getWeapons()[1];
        });
    }

    @Test
    public void testWeapon() {
        Weapon actualWeapon = manipulator.getWeapon();
        assertEquals(expectedWeapon, actualWeapon);
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
