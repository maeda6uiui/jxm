package com.github.dabasan.jxm.properties;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;

/**
 * Test class for XGSManipulator
 * 
 * @author Daba
 *
 */
public class XGSManipulatorTest {
	private XGSManipulator manipulator;

	public XGSManipulatorTest() {
		try {
			manipulator = new XGSManipulator("./Data/weapons.xgs");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadXGS() {
		WeaponData[] weapons = manipulator.getWeaponData();
		for (var weapon : weapons) {
			// System.out.println(weapon);
		}
	}

	@Test
	public void testSaveAsXGS() {
		manipulator.saveAsXGS("./Data/weaponsSave.xgs");
	}
}
