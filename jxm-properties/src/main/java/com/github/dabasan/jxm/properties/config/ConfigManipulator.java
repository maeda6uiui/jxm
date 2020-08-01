package com.github.dabasan.jxm.properties.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config manipulator
 * 
 * @author Daba
 *
 */
public class ConfigManipulator {
	private Logger logger = LoggerFactory.getLogger(ConfigManipulator.class);

	private Config config;

	public ConfigManipulator() {
		config = new Config();
	}
	public ConfigManipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public ConfigManipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
	public ConfigManipulator(String filepath) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new ConfigReader(is);
		config = reader.getConfig();
	}

	/**
	 * Returns config.
	 * 
	 * @return Config
	 */
	public Config getConfig() {
		return config;
	}
	/**
	 * Sets config.
	 * 
	 * @param config
	 *            Config
	 */
	public void setConfig(Config config) {
		if (config == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		this.config = config;
	}

	private void saveAsDATBase(OutputStream os) throws IOException {
		var writer = new ConfigWriter();
		writer.write(os, config);
	}
	/**
	 * Saves config as a DAT file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsDAT(OutputStream os) {
		int ret = 0;

		try {
			this.saveAsDATBase(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves config as a DAT file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsDAT(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsDATBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves config as a DAT file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsDAT(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsDATBase(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
}
