package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeGenerator;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test WeaponCodeGenerator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponCodeGeneratorTest {
    private XGSManipulator manipulator;
    private String expectedCode;

    @BeforeAll
    public void loadWeapons() {
        try {
            manipulator = new XGSManipulator(Paths.get("./Data/Weapon/weapons.xgs").toString());
            expectedCode = Files.readString(Paths.get("./Data/Weapon/weapon_code.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGeneratedCode() {
        var settings = new WeaponVariableNameSettings();
        settings.arrayName = "武器";
        settings.model = "モデル";

        var generator = new WeaponCodeGenerator(settings);

        Weapon[] weapons = manipulator.getWeapons();
        String actualCode = generator.generate(Arrays.asList(weapons));

        assertEquals(expectedCode, actualCode);
    }
}
