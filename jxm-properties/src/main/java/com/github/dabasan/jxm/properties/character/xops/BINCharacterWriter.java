package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

import static com.github.dabasan.jxm.bintools.ByteFunctions.setShortToBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.setUnsignedShortToBinLE;

/**
 * BIN character writer
 *
 * @author Daba
 */
class BINCharacterWriter {
    public void write(byte[] bin, Character[] characters, int dataStartPos) {
        int pos = dataStartPos;
        int numCharacters = characters.length;
        for (int i = 0; i < numCharacters; i++) {
            // Texture
            setShortToBinLE(bin, pos, (short) characters[i].texture.ordinal());
            pos += 2;
            // Model
            CharacterModelType modelType = characters[i].model;
            int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
            setShortToBinLE(bin, pos, (short) modelTypeSpc);
            pos += 2;
            // HP
            setUnsignedShortToBinLE(bin, pos, (short) characters[i].hp);
            pos += 2;
            // AI level
            setShortToBinLE(bin, pos, (short) characters[i].aiLevel.ordinal());
            pos += 2;
            // Weapons
            setShortToBinLE(bin, pos, characters[i].weapons.get(0).shortValue());
            setShortToBinLE(bin, pos + 2, characters[i].weapons.get(1).shortValue());
            pos += 4;
            // Type
            setShortToBinLE(bin, pos, (short) characters[i].type.ordinal());
            pos += 2;
        }
    }
}
