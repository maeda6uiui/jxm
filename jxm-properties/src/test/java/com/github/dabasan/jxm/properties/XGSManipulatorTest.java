package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test XGSManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XGSManipulatorTest {
    private static final String TARGET_DIR = "./TestData/Weapon";
    private XGSManipulator manipulator;
    private XOPSWeapon[] expectedWeapons;

    @BeforeAll
    public void loadWeapons() {
        assertDoesNotThrow(() -> {
            manipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs"));

            var exeManipulator = new EXEManipulator(Paths.get(TARGET_DIR, "xops0975t.exe"));
            expectedWeapons = exeManipulator.getWeapons();
        });
    }

    @Test
    public void testWeapons() {
        XOPSWeapon[] actualWeapons = manipulator.getWeapons();
        assertArrayEquals(expectedWeapons, actualWeapons);
    }

    @Test
    public void testUpdateWeapons() {
        XOPSWeapon[] currentWeapons = manipulator.getWeapons();

        var newWeapons = new XOPSWeapon[currentWeapons.length];
        for (int i = 0; i < currentWeapons.length; i++) {
            newWeapons[i] = TestUtils.generateRandomWeapon();
        }
        manipulator.setWeapons(newWeapons);
        assertArrayEquals(newWeapons, manipulator.getWeapons());

        manipulator.setWeapons(currentWeapons);
    }

    @Test
    public void testSave() {
        Path srcPath = Paths.get(TARGET_DIR, "weapons.xgs");
        Path outputPath = Paths.get(TARGET_DIR, "weapons_2.xgs");
        assertDoesNotThrow(() -> manipulator.save(outputPath));

        String srcFileHash = TestUtils.getFileHash(srcPath);
        String outputFileHash = TestUtils.getFileHash(outputPath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
