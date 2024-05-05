package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.*;

import java.io.IOException;
import java.io.InputStream;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.getUnsignedShortFromBinLE;

/**
 * XCS reader
 *
 * @author maeda6uiui
 */
class XCSReader {
    private Character[] characters;

    public XCSReader(InputStream is, int numCharacters) throws IOException {
        characters = new Character[numCharacters];

        //Read all bytes from a stream
        byte[] bin = is.readAllBytes();

        int pos = 0x0000000C;

        for (int i = 0; i < numCharacters; i++) {
            var character = new Character();

            //Texture
            int textureTypeSpc = getShortFromBinLE(bin, pos);
            pos += 2;
            character.texture = CharacterTextureType.values()[textureTypeSpc];
            //Model
            int modelTypeSpc = getShortFromBinLE(bin, pos);
            pos += 2;
            character.model = CharacterBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);
            //HP
            character.hp = getUnsignedShortFromBinLE(bin, pos);
            pos += 2;
            //AI level
            int aiLevelSpc = getShortFromBinLE(bin, pos);
            pos += 2;
            character.aiLevel = AILevel.values()[aiLevelSpc];
            //Weapons
            int[] weapons = new int[2];
            weapons[0] = getShortFromBinLE(bin, pos);
            weapons[1] = getShortFromBinLE(bin, pos + 2);
            pos += 4;
            character.weapons.set(0, weapons[0]);
            character.weapons.set(1, weapons[1]);
            //Type
            int typeSpc = getShortFromBinLE(bin, pos);
            pos += 2;
            character.type = CharacterType.values()[typeSpc];

            characters[i] = character;
        }
    }

    public Character[] getCharacterData() {
        return characters;
    }
}
