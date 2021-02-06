package com.github.dabasan.jxm.mif;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MIF manipulator
 * 
 * @author Daba
 *
 */
public class MIFManipulator {
	private Logger logger = LoggerFactory.getLogger(MIFManipulator.class);

	private MissionInfo missionInfo;

	public MIFManipulator() {
		missionInfo = new MissionInfo();
	}
	public MIFManipulator(InputStream is, String encoding) throws IOException {
		this.readConstructorBase(is, encoding);
	}
	public MIFManipulator(File file, String encoding) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis, encoding);
		}
	}
	public MIFManipulator(String filepath, String encoding) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis, encoding);
		}
	}
	private void readConstructorBase(InputStream is, String encoding) throws IOException {
		var reader = new MIFReader(is, encoding);
		missionInfo = reader.getMissionInfo();
	}

	/**
	 * Returns mission info.
	 * 
	 * @return Mission info
	 */
	public MissionInfo getMissionInfo() {
		return missionInfo;
	}
	/**
	 * Sets mission info.
	 * 
	 * @param missionInfo
	 *            Mission info
	 */
	public void setMissionInfo(MissionInfo missionInfo) {
		if (missionInfo == null) {
			logger.warn("Null argument where non-null required");
			return;
		}

		this.missionInfo = missionInfo;
	}

	private void saveAsMIFBase(OutputStream os, String encoding) throws IOException {
		var writer = new MIFWriter();
		writer.write(os, encoding, missionInfo);
	}
	/**
	 * Saves the mission info as a MIF file.
	 * 
	 * @param os
	 *            OutputStream
	 * @param encoding
	 *            Encoding
	 * @return -1: error 0: success
	 */
	public int saveAsMIF(OutputStream os, String encoding) {
		int ret = 0;

		try {
			this.saveAsMIFBase(os, encoding);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the mission info as a MIF file.
	 * 
	 * @param file
	 *            File
	 * @param encoding
	 *            Encoding
	 * @return -1: error 0: success
	 */
	public int saveAsMIF(File file, String encoding) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsMIFBase(fos, encoding);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the mission info as a MIF file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @param encoding
	 *            Encoding
	 * @return -1: error 0: success
	 */
	public int saveAsMIF(String filepath, String encoding) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsMIFBase(fos, encoding);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
}
