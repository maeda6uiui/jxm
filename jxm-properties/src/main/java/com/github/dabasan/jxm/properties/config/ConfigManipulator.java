package com.github.dabasan.jxm.properties.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Config manipulator
 *
 * @author maeda6uiui
 */
public class ConfigManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JXMConfig config;

    /**
     * Creates a config manipulator.
     */
    public ConfigManipulator() {
        config = new JXMConfig();
    }

    /**
     * Creates a config manipulator and loads a config file.
     *
     * @param path Path of the config file
     * @throws IOException If it fails to load the file
     */
    public ConfigManipulator(Path path) throws IOException {
        var reader = new ConfigReader(path);
        config = reader.getConfig();
    }

    /**
     * Returns config.
     *
     * @return config
     */
    public JXMConfig getConfig() {
        return config;
    }

    /**
     * Sets config.
     *
     * @param config config to set
     */
    public void setConfig(JXMConfig config) {
        this.config = config;
    }

    /**
     * Saves the config data in a file.
     *
     * @param path Path of the file
     * @throws IOException If it fails to write to the file
     */
    public void save(Path path) throws IOException {
        var writer = new ConfigWriter();
        writer.write(path, config);
    }
}
