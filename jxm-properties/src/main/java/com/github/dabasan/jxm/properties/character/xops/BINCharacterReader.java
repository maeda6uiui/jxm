package com.github.dabasan.jxm.properties.character.xops;

import com.github.dabasan.jxm.properties.character.*;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.getUnsignedShortFromBinLE;

/**
 * BIN character reader
 *
 * @author maeda6uiui
 */
class BINCharacterReader {
    private final XOPSCharacter[] characters;
    private int pos;

    public BINCharacterReader(byte[] bin, int numCharacters, int dataStartPos) {
        characters = new XOPSCharacter[numCharacters];

        pos = dataStartPos;
        for (int i = 0; i < numCharacters; i++) {
            var character = new XOPSCharacter();

            int textureTypeSpc = this.getShortAndIncrementPos(bin);
            character.texture = CharacterTextureType.values()[textureTypeSpc];

            int modelTypeSpc = this.getShortAndIncrementPos(bin);
            character.model = CharacterBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);

            character.hp = this.getUnsignedShortAndIncrementPos(bin);

            int aiLevelSpc = this.getShortAndIncrementPos(bin);
            character.aiLevel = AILevel.values()[aiLevelSpc];

            int[] weapons = new int[2];
            weapons[0] = this.getShortAndIncrementPos(bin);
            weapons[1] = this.getShortAndIncrementPos(bin);
            character.weapons.set(0, weapons[0]);
            character.weapons.set(1, weapons[1]);

            int typeSpc = this.getShortAndIncrementPos(bin);
            character.type = CharacterType.values()[typeSpc];

            characters[i] = character;
        }
    }

    private int getShortAndIncrementPos(byte[] bin) {
        int ret = getShortFromBinLE(bin, pos);
        pos += 2;
        return ret;
    }

    private int getUnsignedShortAndIncrementPos(byte[] bin) {
        int ret = getUnsignedShortFromBinLE(bin, pos);
        pos += 2;
        return ret;
    }

    public XOPSCharacter[] getCharacterData() {
        return characters;
    }
}
