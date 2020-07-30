package com.github.dabasan.jxm.properties.character.xcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.character.CharacterData;

/**
 * XCS manipulator
 * 
 * @author Daba
 *
 */
public class XCSManipulator {
	private Logger logger = LoggerFactory.getLogger(XCSManipulator.class);

	private static final int NUM_CHARACTERS = 43;
	private CharacterData[] characters;

	public XCSManipulator() {
		characters = new CharacterData[NUM_CHARACTERS];
	}
	public XCSManipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public XCSManipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
	public XCSManipulator(String filepath) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new XCSReader(is, NUM_CHARACTERS);
		characters = reader.getCharacterData();
	}

	/**
	 * Returns character data.
	 * 
	 * @return Array containing character data
	 */
	public CharacterData[] getCharacterData() {
		return characters.clone();
	}
	/**
	 * Sets character data.
	 * 
	 * @param characters
	 *            Array containing character data
	 */
	public void setCharacterData(CharacterData[] characters) {
		if (characters == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}
		if (characters.length != NUM_CHARACTERS) {
			logger.warn("Invalid number of data contained in the array. number={}",
					characters.length);
			return;
		}

		this.characters = characters;
	}

	private void saveAsXCSBase(OutputStream os) throws IOException {
		var writer = new XCSWriter();
		writer.write(os, characters);
	}
	/**
	 * Saves character data as a XCS file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsXCS(OutputStream os) {
		int ret = 0;

		try {
			this.saveAsXCSBase(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves character data as a XCS file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsXCS(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsXCSBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves character data as a XCS file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsXCS(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsXCSBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
