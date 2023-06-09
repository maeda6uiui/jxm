package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * XCS manipulator
 *
 * @author maeda6uiui
 */
public class XCSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_CHARACTERS = 43;
    private Character[] characters;

    /**
     * Creates a XCS manipulator.
     */
    public XCSManipulator() {
        characters = new Character[NUM_CHARACTERS];
    }

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new XCSReader(is, NUM_CHARACTERS);
        characters = reader.getCharacterData();
    }

    /**
     * Creates a XCS manipulator and loads a XCS.
     *
     * @param is input stream to load a XCS from
     * @throws IOException if it fails to load
     */
    public XCSManipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates a XCS manipulator and loads a XCS.
     *
     * @param file file to load a XCS from
     * @throws IOException if it fails to load
     */
    public XCSManipulator(File file) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates a XCS manipulator and loads a XCS.
     *
     * @param filepath filepath to load a XCS from
     * @throws IOException if it fails to load
     */
    public XCSManipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Returns character data.
     *
     * @return array containing character data
     */
    public Character[] getCharacters() {
        return characters.clone();
    }

    /**
     * Sets character data.
     *
     * @param characters array containing character data
     */
    public void setCharacters(Character[] characters) {
        Objects.requireNonNull(characters);

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
     * Saves character data as a XCS.
     *
     * @param os output stream to write characters to
     * @return -1: error 0: success
     */
    public int saveAsXCS(OutputStream os) {
        int ret = 0;

        try {
            this.saveAsXCSBase(os);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves character data as a XCS.
     *
     * @param file file to write characters to
     * @return -1: error 0: success
     */
    public int saveAsXCS(File file) {
        int ret = 0;

        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.saveAsXCSBase(bos);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves character data as a XCS.
     *
     * @param filepath filepath to write characters to
     * @return -1: error 0: success
     */
    public int saveAsXCS(String filepath) {
        int ret = 0;

        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.saveAsXCSBase(bos);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }
}
