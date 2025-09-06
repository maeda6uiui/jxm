package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterModelType;

import static com.github.dabasan.jxm.bintools.ByteFunctions.setShortToBinLE;

/**
 * BIN character writer
 *
 * @author maeda6uiui
 */
class BINCharacterWriter {
    private int pos;

    public void write(byte[] bin, Character[] characters, int dataStartPos) {
        pos = dataStartPos;
        int numCharacters = characters.length;
        for (int i = 0; i < numCharacters; i++) {
            this.setShortAndIncrementPos(bin, characters[i].texture.ordinal());

            CharacterModelType modelType = characters[i].model;
            int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
            this.setShortAndIncrementPos(bin, modelTypeSpc);

            this.setShortAndIncrementPos(bin, characters[i].hp);
            this.setShortAndIncrementPos(bin, characters[i].aiLevel.ordinal());
            this.setShortAndIncrementPos(bin, characters[i].weapons.get(0));
            this.setShortAndIncrementPos(bin, characters[i].weapons.get(1));
            this.setShortAndIncrementPos(bin, characters[i].type.ordinal());
        }
    }

    private void setShortAndIncrementPos(byte[] bin, int v) {
        setShortToBinLE(bin, pos, (short) v);
        pos += 2;
    }
}
