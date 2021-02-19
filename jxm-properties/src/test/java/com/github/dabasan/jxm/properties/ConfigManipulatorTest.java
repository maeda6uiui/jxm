package com.github.dabasan.jxm.properties;

import java.io.IOException;
import java.nio.file.Paths;

import com.github.dabasan.jxm.properties.config.ConfigManipulator;
import com.github.dabasan.jxm.properties.config.KeyCode;

/**
 * Test ConfigManipulator
 * 
 * @author Daba
 *
 */
public class ConfigManipulatorTest {
	private final String TARGET_DIR = "./Data/Config";
	private ConfigManipulator manipulator;

	public static void main(String[] args) {
		new ConfigManipulatorTest();
	}

	public ConfigManipulatorTest() {
		var srcFilepath = Paths.get(TARGET_DIR, "config_src.dat").toString();
		try {
			manipulator = new ConfigManipulator(srcFilepath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.printConfig();
		this.saveAsDAT();
	}

	private void printConfig() {
		System.out.println("#Config");
		System.out.println("##Original");
		System.out.println(manipulator.getConfig());

		System.out.println("##Modified");

		manipulator.getConfig().moveForward = KeyCode.KEY_ENTER;
		manipulator.getConfig().brightness = 1000;
		manipulator.getConfig().enableSound = false;
		manipulator.getConfig().name = "Test";

		System.out.println(manipulator.getConfig());
	}
	private void saveAsDAT() {
		System.out.println("#saveAsDAT()");

		var saveFilepath = Paths.get(TARGET_DIR, "config.dat").toString();
		manipulator.saveAsDAT(saveFilepath);
	}
}
