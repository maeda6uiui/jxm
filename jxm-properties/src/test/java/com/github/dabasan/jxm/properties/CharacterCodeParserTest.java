package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeParser;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;

/**
 * Test CharacterCodeParser
 * 
 * @author Daba
 *
 */
public class CharacterCodeParserTest {
	public CharacterCodeParserTest() {
		var settings = new CharacterVariableNameSettings();
		settings.setArrayName("人");
		settings.setTexture("テクスチャ");

		var parser = new CharacterCodeParser(settings);

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("./Data/Character/character_code.txt"),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Map<Integer, CharacterData> characters = parser.parse(lines);
		for (var entry : characters.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("====================");
		}
	}
}
