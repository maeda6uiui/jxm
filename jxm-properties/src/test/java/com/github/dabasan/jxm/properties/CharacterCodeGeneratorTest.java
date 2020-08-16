package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.openxops.CharacterCodeGenerator;
import com.github.dabasan.jxm.properties.character.openxops.CharacterVariableNameSettings;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;

/**
 * Test class for CharacterCodeGenerator
 * 
 * @author Daba
 *
 */
public class CharacterCodeGeneratorTest {
	private CharacterCodeGenerator generator;
	private List<CharacterData> characters;

	public CharacterCodeGeneratorTest() {
		var settings = new CharacterVariableNameSettings();
		settings.setArrayName("キャラクター");
		settings.setTexture("テクスチャ");

		generator = new CharacterCodeGenerator(settings);

		XCSManipulator xcsManipulator;
		try {
			xcsManipulator = new XCSManipulator("./Data/characters.xcs");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		CharacterData[] charactersArr = xcsManipulator.getCharacterData();
		characters = Arrays.asList(charactersArr);
	}

	@Test
	public void testGenerate() {
		String code = generator.generate(characters);
		System.out.println(code);
	}
}
