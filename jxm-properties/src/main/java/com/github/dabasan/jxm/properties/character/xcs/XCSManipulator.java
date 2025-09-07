package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.JXMCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * XCS manipulator
 *
 * @author maeda6uiui
 */
public class XCSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_CHARACTERS = 43;
    private JXMCharacter[] characters;

    /**
     * Creates a XCS manipulator.
     */
    public XCSManipulator() {
        characters = new JXMCharacter[NUM_CHARACTERS];
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
    public JXMCharacter[] getCharacters() {
        return characters;
    }

    /**
     * Sets character data.
     *
     * @param characters array containing character data
     */
    public void setCharacters(JXMCharacter[] characters) {
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
     * @throws IOException if it fails to output
     */
    public void saveAsXCS(OutputStream os) throws IOException {
        this.saveAsXCSBase(os);
    }

    /**
     * Saves character data as a XCS.
     *
     * @param file file to write characters to
     * @throws IOException if it fails to output
     */
    public void saveAsXCS(File file) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.saveAsXCSBase(bos);
        }
    }

    /**
     * Saves character data as a XCS.
     *
     * @param filepath filepath to write characters to
     * @throws IOException if it fails to output
     */
    public void saveAsXCS(String filepath) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.saveAsXCSBase(bos);
        }
    }
}
