package com.github.dabasan.jxm.properties.xops;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.xops.BINWeaponManipulator;

/**
 * EXE weapon manipulator
 * 
 * @author Daba
 *
 */
public class EXEWeaponManipulator {
	private Logger logger = LoggerFactory.getLogger(EXEWeaponManipulator.class);

	private XOPSVersion srcXOPSVersion;
	private BINWeaponManipulator manipulator;

	public EXEWeaponManipulator(List<Byte> bin) {
		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXEWeaponManipulator(InputStream is) throws IOException {
		List<Byte> bin = this.readAllBin(is);
		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXEWeaponManipulator(File file) throws IOException {
		List<Byte> bin;
		try (var fis = new FileInputStream(file)) {
			bin = this.readAllBin(fis);
		}
		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}
	public EXEWeaponManipulator(String filepath) throws IOException {
		List<Byte> bin;
		try (var fis = new FileInputStream(filepath)) {
			bin = this.readAllBin(fis);
		}
		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		this.constructorBase(bin, version);
	}

	private int getDataStartPos(XOPSVersion version) {
		int dataStartPos;

		switch (version) {
			case XOPS096 :
				dataStartPos = 0x0005D32C;
				break;
			case XOPS096T :
				dataStartPos = 0x0005D32C;
				break;
			case XOPS097FT :
				dataStartPos = 0x0005E32C;
				break;
			case XOPS0975T :
				dataStartPos = 0x00077FB0;
				break;
			case XOPSOLT18F2 :
				dataStartPos = 0x0006640C;
				break;
			case XOPSOLT19F2 :
				dataStartPos = 0x000777E8;
				break;
			default :
				dataStartPos = 0x00000000;
				break;
		}

		return dataStartPos;
	}
	private int getNameStartPos(XOPSVersion version) {
		int nameStartPos;

		switch (version) {
			case XOPS096 :
				nameStartPos = 0x000661E4;
				break;
			case XOPS096T :
				nameStartPos = 0x000661E4;
				break;
			case XOPS097FT :
				nameStartPos = 0x000671E4;
				break;
			case XOPS0975T :
				nameStartPos = 0x00079140;
				break;
			case XOPSOLT18F2 :
				nameStartPos = 0x0006EF84;
				break;
			case XOPSOLT19F2 :
				nameStartPos = 0x00077370;
				break;
			default :
				nameStartPos = 0x00000000;
				break;
		}

		return nameStartPos;
	}
	private List<Byte> readAllBin(InputStream is) throws IOException {
		byte[] binArray;
		try (var bis = new BufferedInputStream(is)) {
			binArray = bis.readAllBytes();
		}

		var bin = new ArrayList<Byte>();
		for (var b : binArray) {
			bin.add(b);
		}

		return bin;
	}
	private void constructorBase(List<Byte> bin, XOPSVersion version) {
		int dataStartPos = this.getDataStartPos(version);
		int nameStartPos = this.getNameStartPos(version);
		manipulator = new BINWeaponManipulator(bin, dataStartPos, nameStartPos);
		srcXOPSVersion = version;
	}

	/**
	 * Returns weapon data.
	 * 
	 * @return Array containing weapon data
	 */
	public WeaponData[] getWeaponData() {
		return manipulator.getWeaponData();
	}
	/**
	 * Sets weapon data.
	 * 
	 * @param weapons
	 *            Array containing weapon data
	 */
	public void setWeaponData(WeaponData[] weapons) {
		manipulator.setWeaponData(weapons);
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
		List<Byte> bin;
		try (var fis = new FileInputStream(file)) {
			bin = this.readAllBin(fis);
		}

		// Make a backup file.
		if (fileBackup != null) {
			try (var fosBackup = new FileOutputStream(fileBackup)) {
				for (Byte b : bin) {
					fosBackup.write(b);
				}
			}
		}

		XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
		int dataStartPos = this.getDataStartPos(version);
		int nameStartPos = this.getNameStartPos(version);

		manipulator.write(bin, dataStartPos, nameStartPos);

		// Overwrite an EXE file of XOPS.
		try (var fos = new FileOutputStream(file)) {
			for (Byte b : bin) {
				fos.write(b);
			}
		}
	}
	/**
	 * Writes weapon data to an existing EXE file.<br>
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
	 * Writes weapon data to an existing EXE file.<br>
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
