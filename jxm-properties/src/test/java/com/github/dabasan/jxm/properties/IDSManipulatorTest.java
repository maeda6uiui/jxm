package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.JXMWeapon;
import com.github.dabasan.jxm.properties.weapon.ids.IDSManipulator;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;
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
    private JXMWeapon expectedWeapon;

    @BeforeAll
    public void loadWeapon() {
        assertDoesNotThrow(() -> {
            manipulator = new IDSManipulator(Paths.get(TARGET_DIR, "mp5.ids"));

            var xgsManipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs"));
            expectedWeapon = xgsManipulator.getWeapons()[1];
        });
    }

    @Test
    public void testWeapon() {
        JXMWeapon actualWeapon = manipulator.getWeapon();
        assertEquals(expectedWeapon, actualWeapon);
    }

    @Test
    public void testUpdateWeapon() {
        JXMWeapon currentWeapon = manipulator.getWeapon();

        JXMWeapon newWeapon = TestUtils.generateRandomWeapon();
        manipulator.setWeapon(newWeapon);
        assertEquals(newWeapon, manipulator.getWeapon());

        manipulator.setWeapon(currentWeapon);
    }

    @Test
    public void testSave() {
        Path srcPath = Paths.get(TARGET_DIR, "mp5.ids");
        Path savePath = Paths.get(TARGET_DIR, "mp5_2.ids");
        assertDoesNotThrow(() -> manipulator.save(savePath));

        String srcFileHash = TestUtils.getFileHash(srcPath);
        String outputFileHash = TestUtils.getFileHash(savePath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
