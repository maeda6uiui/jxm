package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeParser;

/**
 * Test class for WeaponCodeParser
 * 
 * @author Daba
 *
 */
public class WeaponCodeParserTest {
	@Test
	public void testParse() {
		var parser = new WeaponCodeParser();

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("./Data/weaponCode.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Map<Integer, WeaponData> weapons = parser.parse(lines);
		for (var entry : weapons.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("====================");
		}
	}
}
