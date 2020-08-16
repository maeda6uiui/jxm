package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeParser;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;

/**
 * Test class for CharacterCodeParser
 * 
 * @author Daba
 *
 */
public class CharacterCodeParserTest {
	@Test
	public void testParse() {
		var settings = new CharacterVariableNameSettings();
		settings.setArrayName("人");
		settings.setTexture("テクスチャ");

		var parser = new CharacterCodeParser(settings);

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("./Data/characterCode.txt"),
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
