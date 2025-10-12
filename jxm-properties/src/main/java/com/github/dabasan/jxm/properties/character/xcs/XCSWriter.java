package com.github.dabasan.jxm.properties.character.xcs;

import com.github.dabasan.jxm.properties.character.CharacterBinEnumConverter;
import com.github.dabasan.jxm.properties.character.CharacterModelType;
import com.github.dabasan.jxm.properties.character.XOPSCharacter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static com.github.dabasan.jxm.bintools.ByteFunctions.addShortToBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.addUnsignedShortToBinLE;

/**
 * XCS writer
 *
 * @author maeda6uiui
 */
class XCSWriter {
    public void write(Path path, XOPSCharacter[] characters) throws IOException {
        var bin = new ArrayList<Byte>();

        bin.add((byte) 0x58);//X
        bin.add((byte) 0x43);//C
        bin.add((byte) 0x53);//S
        bin.add((byte) 0x00);
        bin.add((byte) 0x01);
        bin.add((byte) 0x00);
        bin.add((byte) 0x0C);
        bin.add((byte) 0x00);
        bin.add((byte) 0x2B);
        bin.add((byte) 0x00);
        bin.add((byte) 0x07);
        bin.add((byte) 0x00);

        for (XOPSCharacter character : characters) {
            addShortToBinLE(bin, (short) character.texture.ordinal());

            CharacterModelType modelType = character.model;
            int modelTypeSpc = CharacterBinEnumConverter.getBinSpecifierFromModelType(modelType);
            addShortToBinLE(bin, (short) modelTypeSpc);

            addUnsignedShortToBinLE(bin, (short) character.hp);
            addShortToBinLE(bin, (short) character.aiLevel.ordinal());

            addShortToBinLE(bin, character.weapons.get(0).shortValue());
            addShortToBinLE(bin, character.weapons.get(1).shortValue());

            addShortToBinLE(bin, (short) character.type.ordinal());
        }

        Byte[] boxedBytes = bin.toArray(Byte[]::new);
        byte[] byteArray = ArrayUtils.toPrimitive(boxedBytes);
        Files.write(path, byteArray);
    }
}
