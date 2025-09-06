package com.github.maeda6uiui.jxm.properties;

import com.github.maeda6uiui.jxm.properties.weapon.Weapon;
import com.github.maeda6uiui.jxm.properties.weapon.xgs.XGSManipulator;
import com.github.maeda6uiui.jxm.properties.xops.EXEManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
    private Weapon[] expectedWeapons;

    @BeforeAll
    public void loadWeapons() {
        assertDoesNotThrow(() -> {
            manipulator = new XGSManipulator(Paths.get(TARGET_DIR, "weapons.xgs").toString());

            var exeManipulator = new EXEManipulator(Paths.get(TARGET_DIR, "xops0975t.exe").toString());
            expectedWeapons = exeManipulator.getWeapons();
        });
    }

    @Test
    public void testWeapons() {
        Weapon[] actualWeapons = manipulator.getWeapons();
        assertArrayEquals(expectedWeapons, actualWeapons);
    }

    @Test
    public void testUpdateWeapons() {
        Weapon[] currentWeapons = manipulator.getWeapons();

        var newWeapons = new Weapon[currentWeapons.length];
        for (int i = 0; i < currentWeapons.length; i++) {
            newWeapons[i] = TestUtils.generateRandomWeapon();
        }
        manipulator.setWeapons(newWeapons);
        assertArrayEquals(newWeapons, manipulator.getWeapons());

        manipulator.setWeapons(currentWeapons);
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
