package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterModelType;
import com.github.dabasan.jxm.properties.character.JXMCharacter;

import static com.github.dabasan.jxm.bintools.ByteFunctions.setShortToBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.setUnsignedShortToBinLE;

/**
 * BIN character writer
 *
 * @author maeda6uiui
 */
class BINCharacterWriter {
    private int pos;

    public void write(byte[] bin, JXMCharacter[] characters, int dataStartPos) {
        pos = dataStartPos;
        for (JXMCharacter character : characters) {
            this.setShortAndIncrementPos(bin, character.texture.ordinal());

            CharacterModelType modelType = character.model;
            int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
            this.setShortAndIncrementPos(bin, modelTypeSpc);

            this.setUnsignedShortAndIncrementPos(bin, character.hp);
            this.setShortAndIncrementPos(bin, character.aiLevel.ordinal());
            this.setShortAndIncrementPos(bin, character.weapons.get(0));
            this.setShortAndIncrementPos(bin, character.weapons.get(1));
            this.setShortAndIncrementPos(bin, character.type.ordinal());
        }
    }

    private void setShortAndIncrementPos(byte[] bin, int v) {
        setShortToBinLE(bin, pos, (short) v);
        pos += 2;
    }

    private void setUnsignedShortAndIncrementPos(byte[] bin, int v) {
        setUnsignedShortToBinLE(bin, pos, v);
        pos += 2;
    }
}
