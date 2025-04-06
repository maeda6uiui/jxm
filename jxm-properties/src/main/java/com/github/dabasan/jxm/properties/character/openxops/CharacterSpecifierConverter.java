package com.github.dabasan.jxm.properties.character.openxops;

import com.github.dabasan.jxm.properties.character.AILevel;
import com.github.dabasan.jxm.properties.character.CharacterTextureType;

/**
 * Conversion between XOPS and OpenXOPS specifiers
 *
 * @author maeda6uiui
 */
class CharacterSpecifierConverter {
    public static int getOpenXOPSTextureIDFromXOPSTextureType(
            CharacterTextureType xopsTextureType) {
        return switch (xopsTextureType) {
            case CIV1 -> 0;
            case CIV2 -> 1;
            case CIV3 -> 2;
            case GATES -> 3;
            case GS -> 4;
            case HAGE -> 5;
            case ISLAM -> 6;
            case ISLAM2 -> 7;
            case POLICE -> 8;
            case RIIMAN -> 9;
            case RIIMAN_B -> 10;
            case RIIMAN_G -> 11;
            case RIIMAN_K -> 12;
            case ROBOT -> 13;
            case SOLDIER_BLACK -> 14;
            case SOLDIER_BLUE -> 15;
            case SOLDIER_GREEN -> 16;
            case SOLDIER_VIOLET -> 17;
            case SOLDIER_WHITE -> 18;
            case SOLDIER0 -> 19;
            case SOLDIER1 -> 20;
            case SOLDIER2 -> 21;
            case SOLDIER3 -> 22;
            case SYATU -> 23;
            case SYATU2 -> 24;
            case WOMAN -> 25;
            case ZOMBIE1 -> 26;
            case ZOMBIE2 -> 27;
            case ZOMBIE3 -> 28;
            case ZOMBIE4 -> 29;
        };
    }

    public static CharacterTextureType getXOPSTextureTypeFromOpenXOPSTextureID(
            int openXOPSTextureID) {
        return switch (openXOPSTextureID) {
            case 0 -> CharacterTextureType.CIV1;
            case 1 -> CharacterTextureType.CIV2;
            case 2 -> CharacterTextureType.CIV3;
            case 3 -> CharacterTextureType.GATES;
            case 4 -> CharacterTextureType.GS;
            case 5 -> CharacterTextureType.HAGE;
            case 6 -> CharacterTextureType.ISLAM;
            case 7 -> CharacterTextureType.ISLAM2;
            case 8 -> CharacterTextureType.POLICE;
            case 9 -> CharacterTextureType.RIIMAN;
            case 10 -> CharacterTextureType.RIIMAN_B;
            case 11 -> CharacterTextureType.RIIMAN_G;
            case 12 -> CharacterTextureType.RIIMAN_K;
            case 13 -> CharacterTextureType.ROBOT;
            case 14 -> CharacterTextureType.SOLDIER_BLACK;
            case 15 -> CharacterTextureType.SOLDIER_BLUE;
            case 16 -> CharacterTextureType.SOLDIER_GREEN;
            case 17 -> CharacterTextureType.SOLDIER_VIOLET;
            case 18 -> CharacterTextureType.SOLDIER_WHITE;
            case 19 -> CharacterTextureType.SOLDIER0;
            case 20 -> CharacterTextureType.SOLDIER1;
            case 21 -> CharacterTextureType.SOLDIER2;
            case 22 -> CharacterTextureType.SOLDIER3;
            case 23 -> CharacterTextureType.SYATU;
            case 24 -> CharacterTextureType.SYATU2;
            case 25 -> CharacterTextureType.WOMAN;
            case 26 -> CharacterTextureType.ZOMBIE1;
            case 27 -> CharacterTextureType.ZOMBIE2;
            case 28 -> CharacterTextureType.ZOMBIE3;
            case 29 -> CharacterTextureType.ZOMBIE4;
            default ->
                    throw new IllegalArgumentException(String.format("Unknown specifier was given: %d", openXOPSTextureID));
        };
    }

    public static int getOpenXOPSAILevelFromXOPSAILevel(AILevel xopsAILevel) {
        return switch (xopsAILevel) {
            case NONE, NO_WEAPON -> 0;
            case D -> 0;
            case C -> 1;
            case B -> 2;
            case A -> 3;
            case S, SS -> 4;
        };
    }

    public static AILevel getXOPSAILevelFromOpenXOPSAILevel(int openXOPSAILevel) {
        return switch (openXOPSAILevel) {
            case 0 -> AILevel.D;
            case 1 -> AILevel.C;
            case 2 -> AILevel.B;
            case 3 -> AILevel.A;
            case 4 -> AILevel.S;
            default ->
                    throw new IllegalArgumentException(String.format("Unknown specifier was given: %d", openXOPSAILevel));
        };
    }
}
