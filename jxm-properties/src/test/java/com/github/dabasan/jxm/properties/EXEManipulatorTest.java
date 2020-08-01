package com.github.dabasan.jxm.properties;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;

/**
 * Test class for EXEManipulator
 * 
 * @author Daba
 *
 */
public class EXEManipulatorTest {
	private EXEManipulator manipulator;

	public EXEManipulatorTest() {
		try {
			manipulator = new EXEManipulator("./Data/xops0975t.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSrcXOPSVersion() {
		// System.out.println(manipulator.getSrcXOPSVersion());
	}

	@Test
	public void testLoadWeaponData() {
		WeaponData[] weapons = manipulator.getWeaponData();
		for (var weapon : weapons) {
			// System.out.println(weapon);
		}
	}
	@Test
	public void testLoadCharacterData() {
		CharacterData[] characters = manipulator.getCharacterData();
		for (var character : characters) {
			// System.out.println(character);
		}
	}

	@Test
	public void testWrite() {
		manipulator.write("./Data/xops0975t.exe", "./Data/xops0975tBackup.exe");
	}
}
