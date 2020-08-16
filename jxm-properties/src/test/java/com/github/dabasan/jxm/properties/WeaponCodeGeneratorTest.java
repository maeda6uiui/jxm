package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponCodeGenerator;
import com.github.dabasan.jxm.properties.weapon.openxops.WeaponVariableNameSettings;
import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;

/**
 * Test class for WeaponCodeGenerator
 * 
 * @author Daba
 *
 */
public class WeaponCodeGeneratorTest {
	private WeaponCodeGenerator generator;
	private List<WeaponData> weapons;

	public WeaponCodeGeneratorTest() {
		var settings = new WeaponVariableNameSettings();
		settings.setArrayName("武器");
		settings.setModel("モデル");

		generator = new WeaponCodeGenerator(settings);

		XGSManipulator xgsManipulator;
		try {
			xgsManipulator = new XGSManipulator("./Data/weapons.xgs");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		WeaponData[] weaponsArr = xgsManipulator.getWeaponData();
		weapons = Arrays.asList(weaponsArr);
	}

	@Test
	public void testGenerate() {
		String code = generator.generate(weapons);
		System.out.println(code);
	}
}
