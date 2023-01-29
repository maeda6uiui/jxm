package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeParser;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Test class for WeaponCodeParser
 *
 * @author maeda6uiui
 */
public class WeaponCodeParserTest {
    public static void main(String[] args) {
        new WeaponCodeParserTest();
    }

    public WeaponCodeParserTest() {
        var settings = new WeaponVariableNameSettings();
        settings.model = "モデル";

        var parser = new WeaponCodeParser(settings);

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("./Data/Weapon/weapon_code.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Map<Integer, Weapon> weapons = parser.parse(lines);
        for (var entry : weapons.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("====================");
        }
    }
}
