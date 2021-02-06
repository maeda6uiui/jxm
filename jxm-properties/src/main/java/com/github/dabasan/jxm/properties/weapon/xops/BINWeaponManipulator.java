package com.github.dabasan.jxm.properties.weapon.xops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.weapon.WeaponData;

/**
 * BIN weapon manipulator
 * 
 * @author Daba
 *
 */
public class BINWeaponManipulator {
	private Logger logger = LoggerFactory.getLogger(BINWeaponManipulator.class);

	private static final int NUM_WEAPONS = 23;
	private WeaponData[] weapons;

	public BINWeaponManipulator() {
		weapons = new WeaponData[NUM_WEAPONS];
	}
	public BINWeaponManipulator(byte[] bin, int dataStartPos, int nameStartPos) {
		var reader = new BINWeaponReader(bin, NUM_WEAPONS, dataStartPos, nameStartPos);
		weapons = reader.getWeaponData();
	}

	/**
	 * Returns weapon data.
	 * 
	 * @return Array containing weapon data
	 */
	public WeaponData[] getWeapons() {
		return weapons.clone();
	}
	/**
	 * Sets weapon data.
	 * 
	 * @param weapons
	 *            Array containing weapon data
	 */
	public void setWeapons(WeaponData[] weapons) {
		if (weapons == null) {
			logger.warn("Null argument where non-null required");
			return;
		}
		if (weapons.length != NUM_WEAPONS) {
			logger.warn("Invalid number of data contained in the array. number={}", weapons.length);
			return;
		}

		this.weapons = weapons;
	}

	/**
	 * Writes out weapon data to an array containing bytes.
	 * 
	 * @param bin
	 *            Array
	 * @param dataStartPos
	 *            Start position of weapon data
	 * @param nameStartPos
	 *            Start position of weapon names
	 */
	public void write(byte[] bin, int dataStartPos, int nameStartPos) {
		var writer = new BINWeaponWriter();
		writer.write(bin, weapons, dataStartPos, nameStartPos);
	}
}
