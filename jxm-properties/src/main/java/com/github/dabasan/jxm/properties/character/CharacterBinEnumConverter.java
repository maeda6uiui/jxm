package com.github.dabasan.jxm.properties.character;

/**
 * Bin to enum converter
 *
 * @author Daba
 */
public class CharacterBinEnumConverter {
    public static CharacterModelType getModelTypeFromBinSpecifier(int spc) {
        CharacterModelType modelType;

        switch (spc) {
            case 0x00:
                modelType = CharacterModelType.MALE;
                break;
            case 0x1F:
                modelType = CharacterModelType.SUN_GLASSES;
                break;
            case 0x21:
                modelType = CharacterModelType.POLICEMAN;
                break;
            case 0x23:
                modelType = CharacterModelType.FEMALE;
                break;
            case 0x24:
                modelType = CharacterModelType.BACK_PACK;
                break;
            case 0x26:
                modelType = CharacterModelType.HELMET;
                break;
            default:
                modelType = CharacterModelType.MALE;
                break;
        }

        return modelType;
    }

    public static int getBinSpecifierFromModelType(CharacterModelType modelType) {
        int spc = 0x00;

        switch (modelType) {
            case MALE:
                spc = 0x00;
                break;
            case SUN_GLASSES:
                spc = 0x1F;
                break;
            case POLICEMAN:
                spc = 0x21;
                break;
            case FEMALE:
                spc = 0x23;
                break;
            case BACK_PACK:
                spc = 0x24;
                break;
            case HELMET:
                spc = 0x26;
                break;
        }

        return spc;
    }
}
