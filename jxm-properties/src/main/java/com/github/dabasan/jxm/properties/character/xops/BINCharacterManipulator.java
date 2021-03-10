package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.properties.character.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * BIN character manipulator
 *
 * @author Daba
 */
public class BINCharacterManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_CHARACTERS = 43;
    private Character[] characters;

    /**
     * Creates a BINCharacterManipulator instance.
     */
    public BINCharacterManipulator() {
        characters = new Character[NUM_CHARACTERS];
    }

    /**
     * Creates a BINCharacterManipulator instance.
     *
     * @param bin          binary
     * @param dataStartPos start position of the character data
     */
    public BINCharacterManipulator(byte[] bin, int dataStartPos) {
        var reader = new BINCharacterReader(bin, NUM_CHARACTERS, dataStartPos);
        characters = reader.getCharacterData();
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

    /**
     * Writes out character data to an array containing bytes.
     *
     * @param bin          byte array to write character data in
     * @param dataStartPos start position of character data
     */
    public void write(byte[] bin, int dataStartPos) {
        var writer = new BINCharacterWriter();
        writer.write(bin, characters, dataStartPos);
    }
}
