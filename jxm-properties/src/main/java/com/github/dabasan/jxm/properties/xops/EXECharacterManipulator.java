package com.github.dabasan.jxm.properties.xops;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.character.CharacterData;
import com.github.dabasan.jxm.properties.character.xops.BINCharacterManipulator;

/**
 * EXE character manipulator
 * 
 * @author Daba
 *
 */
public class EXECharacterManipulator {
	private Logger logger = LoggerFactory.getLogger(EXECharacterManipulator.class);

	private XOPSVersion srcXOPSVersion;
	private BINCharacterManipulator manipulator;

	public EXECharacterManipulator(byte[] bin) {
		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXECharacterManipulator(InputStream is) throws IOException {
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXECharacterManipulator(File file) throws IOException {
		byte[] bin;
		try (var bis = new BufferedInputStream(new FileInputStream(file))) {
			bin = bis.readAllBytes();
		}

		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXECharacterManipulator(String filepath) throws IOException {
		byte[] bin;
		try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
			bin = bis.readAllBytes();
		}

		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	private void constructorBase(byte[] bin, XOPSVersion version) {
		int dataStartPos = this.getDataStartPos(version);
		manipulator = new BINCharacterManipulator(bin, dataStartPos);
		srcXOPSVersion = version;
	}

	private int getDataStartPos(XOPSVersion version) {
		int dataStartPos;

		switch (version) {
			case XOPS096 :
				dataStartPos = 0x0005D864;
				break;
			case XOPS096T :
				dataStartPos = 0x0005D864;
				break;
			case XOPS097FT :
				dataStartPos = 0x0005E864;
				break;
			case XOPS0975T :
				dataStartPos = 0x000784E8;
				break;
			case XOPSOLT18F2 :
				dataStartPos = 0x00066944;
				break;
			case XOPSOLT19F2 :
				dataStartPos = 0x00077D20;
				break;
			default :
				dataStartPos = 0x00000000;
				break;
		}

		return dataStartPos;
	}

	/**
	 * Returns character data.
	 * 
	 * @return Array containing character data
	 */
	public CharacterData[] getCharacterData() {
		return manipulator.getCharacterData();
	}
	/**
	 * Sets character data.
	 * 
	 * @param characters
	 *            Array containing character data
	 */
	public void setCharacterData(CharacterData[] characters) {
		manipulator.setCharacterData(characters);
	}

	/**
	 * Returns the version of XOPS passed to the constructor.
	 * 
	 * @return Version of XOPS
	 */
	public XOPSVersion getSrcXOPSVersion() {
		return srcXOPSVersion;
	}

	// Base method for write().
	// Pass a non-null value to fileBackup to make a backup file before
	// overwriting the file.
	private void writeBase(File file, File fileBackup) throws IOException {
		byte[] bin;
		try (var bis = new BufferedInputStream(new FileInputStream(file))) {
			bin = bis.readAllBytes();
		}

		// Make a backup file.
		if (fileBackup != null) {
			try (var fosBackup = new FileOutputStream(fileBackup)) {
				fosBackup.write(bin);
			}
		}

		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		int dataStartPos = this.getDataStartPos(version);

		manipulator.write(bin, dataStartPos);

		// Overwrite an EXE file of XOPS.
		try (var fos = new FileOutputStream(file)) {
			fos.write(bin);
		}
	}
	/**
	 * Writes character data to an existing EXE file.<br>
	 * Pass a non-null value to the second argument if you want to make a backup
	 * file before overwriting the existing EXE file.
	 * 
	 * @param file
	 *            File
	 * @param fileBackup
	 *            File for backup
	 * @return -1: error 0: success
	 */
	public int write(File file, File fileBackup) {
		if (file.exists() == false) {
			logger.error("The file specified does not exist. filename={}", file.getName());
			return -1;
		}

		try {
			this.writeBase(file, fileBackup);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			return -1;
		}

		return 0;
	}
	/**
	 * Writes character data to an existing EXE file.<br>
	 * Pass a non-null value to the second argument if you want to make a backup
	 * file before overwriting the existing EXE file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @param filepathBackup
	 *            Filepath for backup
	 * @return -1: error 0: success
	 */
	public int write(String filepath, String filepathBackup) {
		var file = new File(filepath);
		if (file.exists() == false) {
			logger.error("The file specified does not exist. filename={}", file.getName());
			return -1;
		}

		File fileBackup = null;
		if (filepathBackup != null) {
			fileBackup = new File(filepathBackup);
		}

		try {
			this.writeBase(file, fileBackup);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			return -1;
		}

		return 0;
	}
}
