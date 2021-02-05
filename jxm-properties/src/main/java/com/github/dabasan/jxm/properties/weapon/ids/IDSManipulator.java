package com.github.dabasan.jxm.properties.weapon.ids;

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
 * IDS manipulator
 * 
 * @author Daba
 *
 */
public class IDSManipulator {
	private Logger logger = LoggerFactory.getLogger(IDSManipulator.class);

	private WeaponData weapon;

	public IDSManipulator() {
		weapon = new WeaponData();
	}
	public IDSManipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public IDSManipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
	public IDSManipulator(String filepath) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new IDSReader(is);
		weapon = reader.getWeaponData();
	}

	/**
	 * Returns weapon data.
	 * 
	 * @return Weapon data
	 */
	public WeaponData getWeapon() {
		return weapon;
	}
	/**
	 * Sets weapon data.
	 * 
	 * @param weapon
	 *            Weapon data
	 */
	public void setWeapon(WeaponData weapon) {
		if (weapon == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		this.weapon = weapon;
	}

	private void innerSaveAsIDS(OutputStream os) throws IOException {
		var writer = new IDSWriter();
		writer.write(os, weapon);
	}
	/**
	 * Saves weapon data as an IDS file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsIDS(OutputStream os) {
		int ret = 0;

		try {
			this.innerSaveAsIDS(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves weapon data as an IDS file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsIDS(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.innerSaveAsIDS(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves weapon data as an IDS file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsIDS(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.innerSaveAsIDS(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
