package com.github.dabasan.jxm.properties.character;

/**
 * Bin to enum converter
 *
 * @author maeda6uiui
 */
public class CharacterBinEnumConverter {
    public static CharacterModelType getModelTypeFromBinSpecifier(int spc) {
        return switch (spc) {
            case 0x00 -> CharacterModelType.MALE;
            case 0x1F -> CharacterModelType.SUN_GLASSES;
            case 0x21 -> CharacterModelType.POLICEMAN;
            case 0x23 -> CharacterModelType.FEMALE;
            case 0x24 -> CharacterModelType.BACK_PACK;
            case 0x26 -> CharacterModelType.HELMET;
            default -> throw new IllegalArgumentException(String.format("Unknown specifier was given: 0x%02X", spc));
        };
    }

    public static int getBinSpecifierFromModelType(CharacterModelType modelType) {
        return switch (modelType) {
            case MALE -> 0x00;
            case SUN_GLASSES -> 0x1F;
            case POLICEMAN -> 0x21;
            case FEMALE -> 0x23;
            case BACK_PACK -> 0x24;
            case HELMET -> 0x26;
        };
    }
}
