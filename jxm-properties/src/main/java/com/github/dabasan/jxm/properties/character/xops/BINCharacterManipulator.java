package com.github.dabasan.jxm.properties.character.xops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.character.CharacterData;

/**
 * BIN character manipulator
 * 
 * @author Daba
 *
 */
public class BINCharacterManipulator {
	private Logger logger = LoggerFactory.getLogger(BINCharacterManipulator.class);

	private static final int NUM_CHARACTERS = 43;
	private CharacterData[] characters;

	public BINCharacterManipulator() {
		characters = new CharacterData[NUM_CHARACTERS];
	}
	public BINCharacterManipulator(byte[] bin, int dataStartPos) {
		var reader = new BINCharacterReader(bin, NUM_CHARACTERS, dataStartPos);
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

	/**
	 * Writes out character data to an array containing bytes.
	 * 
	 * @param bin
	 *            Array
	 * @param dataStartPos
	 *            Start position of character data
	 */
	public void write(byte[] bin, int dataStartPos) {
		var writer = new BINCharacterWriter();
		writer.write(bin, characters, dataStartPos);
	}
}
