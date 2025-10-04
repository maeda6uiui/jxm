package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeGenerator;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * Test WeaponCodeGenerator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponCodeGeneratorTest {
    private XGSManipulator manipulator;
    private List<String> expectedLines;

    @BeforeAll
    public void loadWeapons() {
        assertDoesNotThrow(() -> {
            manipulator = new XGSManipulator(Paths.get("./TestData/Weapon/weapons.xgs"));
            expectedLines = Files.readAllLines(Paths.get("./TestData/Weapon/weapon_code.txt"));
        });
    }

    @Test
    public void testGeneratedCode() {
        var settings = new WeaponVariableNameSettings();
        settings.arrayName = "武器";
        settings.model = "モデル";

        var generator = new WeaponCodeGenerator(settings);

        XOPSWeapon[] weapons = manipulator.getWeapons();
        String actualCode = generator.generate(Arrays.asList(weapons));
        String[] actualLines = actualCode.split("\n");
        assertLinesMatch(expectedLines, Arrays.asList(actualLines));
    }
}
