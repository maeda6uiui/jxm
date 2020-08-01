package com.github.dabasan.jxm.properties;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;

/**
 * Test class for XCSManipulator
 * 
 * @author Daba
 *
 */
public class XCSManipulatorTest {
	private XCSManipulator manipulator;

	public XCSManipulatorTest() {
		try {
			manipulator = new XCSManipulator("./Data/characters.xcs");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadXCS() {
		CharacterData[] characters = manipulator.getCharacterData();
		for (int i = 0; i < characters.length; i++) {
			// System.out.println(characters[i]);
		}
	}

	@Test
	public void testSaveAsXCS() {
		manipulator.saveAsXCS("./Data/charactersSave.xcs");
	}
}
