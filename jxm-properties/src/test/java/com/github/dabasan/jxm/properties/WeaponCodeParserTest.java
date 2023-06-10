package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeParser;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for WeaponCodeParser
 *
 * @author maeda6uiui
 */
public class WeaponCodeParserTest {
    private Weapon[] expectedWeapons;
    private List<String> codeLines;

    @BeforeAll
    public void loadWeapons() {
        try {
            var manipulator = new XGSManipulator("./Data/Weapon/weapons.xgs");
            expectedWeapons = manipulator.getWeapons();

            codeLines = Files.readAllLines(Paths.get("./Data/Weapon/weapon_code.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParsedWeapons() {
        var settings = new WeaponVariableNameSettings();
        settings.model = "モデル";

        var parser = new WeaponCodeParser(settings);
        Map<Integer, Weapon> actualWeapons = parser.parse(codeLines);
        actualWeapons.forEach((id, actualWeapon) -> {
            Weapon expectedWeapon = expectedWeapons[id];

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
        });
    }
}
