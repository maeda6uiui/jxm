package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.JXMCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

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

    /**
     * Creates a XCS manipulator and loads a XCS file.
     *
     * @param path Path of the XCS file
     * @throws IOException If it fails to load the file
     */
    public XCSManipulator(Path path) throws IOException {
        var reader = new XCSReader(path, NUM_CHARACTERS);
        characters = reader.getCharacterData();
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

    /**
     * Saves the character data in a XCS file.
     *
     * @param path Path of the XCS file
     * @throws IOException if it fails to write to the file
     */
    public void saveAsXCS(Path path) throws IOException {
        var writer = new XCSWriter();
        writer.write(path, characters);
    }
}
