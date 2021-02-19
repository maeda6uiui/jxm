package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.util.Arrays;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeGenerator;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;

/**
 * Test CharacterCodeGenerator
 * 
 * @author Daba
 *
 */
public class CharacterCodeGeneratorTest {
	private CharacterCodeGenerator generator;

	public static void main(String[] args) {
		new CharacterCodeGeneratorTest();
	}

	public CharacterCodeGeneratorTest() {
		var settings = new CharacterVariableNameSettings();
		settings.arrayName = "人";
		settings.texture = "テクスチャ";

		generator = new CharacterCodeGenerator(settings);

		XCSManipulator xcsManipulator;
		try {
			xcsManipulator = new XCSManipulator("./Data/Character/characters.xcs");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		CharacterData[] characters = xcsManipulator.getCharacters();
		String code = generator.generate(Arrays.asList(characters));
		System.out.println(code);
	}
}
