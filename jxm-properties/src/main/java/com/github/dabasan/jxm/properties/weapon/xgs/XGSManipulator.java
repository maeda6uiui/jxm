package com.github.dabasan.jxm.properties.weapon.xgs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.weapon.WeaponData;

/**
 * XGS manipulator
 * 
 * @author Daba
 *
 */
public class XGSManipulator {
	private Logger logger = LoggerFactory.getLogger(XGSManipulator.class);

	private static final int NUM_WEAPONS = 23;
	private WeaponData[] weapons;

	public XGSManipulator() {
		weapons = new WeaponData[NUM_WEAPONS];
	}
	public XGSManipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public XGSManipulator(File file) throws IOException {
		try (var bis = new FileInputStream(file)) {
			this.readConstructorBase(bis);
		}
	}
	public XGSManipulator(String filepath) throws IOException {
		try (var bis = new FileInputStream(filepath)) {
			this.readConstructorBase(bis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new XGSReader(is, NUM_WEAPONS);
		weapons = reader.getWeaponData();
	}

	/**
	 * Returns weapon data.
	 * 
	 * @return Array containing weapon data
	 */
	public WeaponData[] getWeaponData() {
		return weapons.clone();
	}
	/**
	 * Sets weapon data.
	 * 
	 * @param weapons
	 *            Array containing weapon data
	 */
	public void setWeaponData(WeaponData[] weapons) {
		if (weapons == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}
		if (weapons.length != NUM_WEAPONS) {
			logger.warn("Invalid number of data contained in the array. number={}", weapons.length);
			return;
		}

		this.weapons = weapons;
	}

	private void saveAsXGSBase(OutputStream os) throws IOException {
		var writer = new XGSWriter();
		writer.write(os, weapons, NUM_WEAPONS);
	}
	/**
	 * Saves weapon data as a XGS file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsXGS(OutputStream os) {
		int ret = 0;

		try {
			this.saveAsXGSBase(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves weapon data as a XGS file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsXGS(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsXGSBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves weapon data as a XGS file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsXGS(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsXGSBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
