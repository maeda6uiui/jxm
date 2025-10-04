package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeParser;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for WeaponCodeParser
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponCodeParserTest {
    private XOPSWeapon[] expectedWeapons;
    private List<String> codeLines;

    @BeforeAll
    public void loadWeapons() {
        assertDoesNotThrow(() -> {
            var manipulator = new XGSManipulator(Paths.get("./TestData/Weapon/weapons.xgs"));
            expectedWeapons = manipulator.getWeapons();

            codeLines = Files.readAllLines(Paths.get("./TestData/Weapon/weapon_code.txt"));
        });
    }

    @Test
    public void testParsedWeapons() {
        var settings = new WeaponVariableNameSettings();
        settings.model = "モデル";

        var parser = new WeaponCodeParser(settings);
        Map<Integer, XOPSWeapon> actualWeapons = parser.parse(codeLines);
        actualWeapons.forEach((id, actualWeapon) -> {
            XOPSWeapon expectedWeapon = expectedWeapons[id];
            assertEquals(expectedWeapon, actualWeapon);
        });
    }
}
