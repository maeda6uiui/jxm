package com.github.dabasan.jxm.mif;

import java.io.IOException;

import org.junit.Test;

/**
 * Test class for MIFManipulator
 * 
 * @author Daba
 *
 */
public class MIFManipulatorTest {
	private MIFManipulator manipulator;

	public MIFManipulatorTest() {
		try {
			manipulator = new MIFManipulator("./Data/test.mif", "Shift-JIS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadMIF() {
		// System.out.println(manipulator.getMissionInfo());
	}

	@Test
	public void testSaveAsMIF() {
		manipulator.saveAsMIF("./Data/testUTF8.mif", "UTF-8");
	}
}
