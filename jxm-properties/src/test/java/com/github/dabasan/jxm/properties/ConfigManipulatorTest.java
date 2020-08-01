package com.github.dabasan.jxm.properties;

import java.io.IOException;

import org.junit.Test;

import com.github.dabasan.jxm.properties.config.ConfigManipulator;

/**
 * Test class for ConfigManipulator
 * 
 * @author Daba
 *
 */
public class ConfigManipulatorTest {
	private ConfigManipulator manipulator;

	public ConfigManipulatorTest() {
		try {
			manipulator = new ConfigManipulator("./Data/config.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadConfig() {
		// System.out.println(manipulator.getConfig());
	}

	@Test
	public void testSaveAsDAT() {
		manipulator.saveAsDAT("./Data/configSave.dat");
	}
}
