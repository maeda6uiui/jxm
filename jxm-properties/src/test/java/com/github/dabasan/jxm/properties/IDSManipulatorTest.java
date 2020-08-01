package com.github.dabasan.jxm.properties;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.jxm.properties.weapon.ids.IDSManipulator;

/**
 * Test class for IDSManipulator
 * 
 * @author Daba
 *
 */
public class IDSManipulatorTest {
	private IDSManipulator manipulator;

	public IDSManipulatorTest() {
		try {
			manipulator = new IDSManipulator("./Data/mp5.ids");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadIDS() {
		// System.out.println(manipulator.getWeaponData());
	}

	@Test
	public void testSaveAsIDS() {
		manipulator.saveAsIDS("./Data/mp5Save.ids");
	}
}
