package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.*;
import com.github.dabasan.jxm.properties.character.Character;

import java.io.IOException;
import java.io.InputStream;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;

/**
 * XCS reader
 *
 * @author maeda6uiui
 */
class XCSReader {
    private final Character[] characters;

    private int pos;

    public XCSReader(InputStream is, int numCharacters) throws IOException {
        characters = new Character[numCharacters];

        byte[] bin = is.readAllBytes();
        pos = 0x0000000C;

        for (int i = 0; i < numCharacters; i++) {
            var character = new Character();

            int textureTypeSpc = this.getShortAndIncrementPos(bin);
            character.texture = CharacterTextureType.values()[textureTypeSpc];

            int modelTypeSpc = this.getShortAndIncrementPos(bin);
            character.model = CharacterBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);

            character.hp = this.getShortAndIncrementPos(bin);

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

    public Character[] getCharacterData() {
        return characters;
    }
}
