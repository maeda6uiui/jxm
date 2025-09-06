package com.github.maeda6uiui.jxm.properties.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Config manipulator
 *
 * @author maeda6uiui
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

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new ConfigReader(is);
        config = reader.getConfig();
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
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates a config manipulator and loads config.
     *
     * @param filepath filepath to load config from
     * @throws IOException if it fails to load
     */
    public ConfigManipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
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
     * @throws IOException if it fails to output
     */
    public void saveAsDAT(OutputStream os) throws IOException {
        this.saveAsDATBase(os);
    }

    /**
     * Saves config as a DAT.
     *
     * @param file file to write the config to
     * @throws IOException if it fails to output
     */
    public void saveAsDAT(File file) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.saveAsDATBase(bos);
        }
    }

    /**
     * Saves config as a DAT.
     *
     * @param filepath filepath to write the config to
     * @throws IOException if it fails to output
     */
    public void saveAsDAT(String filepath) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.saveAsDATBase(bos);
        }
    }
}
