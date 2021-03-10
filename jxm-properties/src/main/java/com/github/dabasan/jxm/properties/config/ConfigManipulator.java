package com.github.dabasan.jxm.properties.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * Config manipulator
 *
 * @author Daba
 */
public class ConfigManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Config config;

    /**
     * Creates a config manipulator.
     */
    public ConfigManipulator() {
        config = new Config();
    }

    /**
     * Creates a config manipulator and loads config.
     *
     * @param is input stream to load config from
     * @throws IOException if it fails to load
     */
    public ConfigManipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates a config manipulator and loads config.
     *
     * @param file file to load config from
     * @throws IOException if it fails to load
     */
    public ConfigManipulator(File file) throws IOException {
        try (var fis = new FileInputStream(file)) {
            this.readConstructorBase(fis);
        }
    }

    /**
     * Creates a config manipulator and loads config.
     *
     * @param filepath filepath to load config from
     * @throws IOException if it fails to load
     */
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
     * @return config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * Sets config.
     *
     * @param config config to set
     */
    public void setConfig(Config config) {
        Objects.requireNonNull(config);
        this.config = config;
    }

    private void saveAsDATBase(OutputStream os) throws IOException {
        var writer = new ConfigWriter();
        writer.write(os, config);
    }

    /**
     * Saves config as a DAT.
     *
     * @param os output stream to write the config to
     * @return -1: error 0: success
     */
    public int saveAsDAT(OutputStream os) {
        int ret = 0;

        try {
            this.saveAsDATBase(os);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves config as a DAT.
     *
     * @param file file to write the config to
     * @return -1: error 0: success
     */
    public int saveAsDAT(File file) {
        int ret = 0;

        try (var fos = new FileOutputStream(file)) {
            this.saveAsDATBase(fos);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves config as a DAT.
     *
     * @param filepath filepath to write the config to
     * @return -1: error 0: success
     */
    public int saveAsDAT(String filepath) {
        int ret = 0;

        try (var fos = new FileOutputStream(filepath)) {
            this.saveAsDATBase(fos);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }
}
