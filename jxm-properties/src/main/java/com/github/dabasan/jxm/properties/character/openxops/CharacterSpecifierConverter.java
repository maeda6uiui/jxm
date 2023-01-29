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
        int openXOPSTextureID = 0;

        switch (xopsTextureType) {
            case CIV1:
                openXOPSTextureID = 0;
                break;
            case CIV2:
                openXOPSTextureID = 1;
                break;
            case CIV3:
                openXOPSTextureID = 2;
                break;
            case GATES:
                openXOPSTextureID = 3;
                break;
            case GS:
                openXOPSTextureID = 4;
                break;
            case HAGE:
                openXOPSTextureID = 5;
                break;
            case ISLAM:
                openXOPSTextureID = 6;
                break;
            case ISLAM2:
                openXOPSTextureID = 7;
                break;
            case POLICE:
                openXOPSTextureID = 8;
                break;
            case RIIMAN:
                openXOPSTextureID = 9;
                break;
            case RIIMAN_B:
                openXOPSTextureID = 10;
                break;
            case RIIMAN_G:
                openXOPSTextureID = 11;
                break;
            case RIIMAN_K:
                openXOPSTextureID = 12;
                break;
            case ROBOT:
                openXOPSTextureID = 13;
                break;
            case SOLDIER_BLACK:
                openXOPSTextureID = 14;
                break;
            case SOLDIER_BLUE:
                openXOPSTextureID = 15;
                break;
            case SOLDIER_GREEN:
                openXOPSTextureID = 16;
                break;
            case SOLDIER_VIOLET:
                openXOPSTextureID = 17;
                break;
            case SOLDIER_WHITE:
                openXOPSTextureID = 18;
                break;
            case SOLDIER0:
                openXOPSTextureID = 19;
                break;
            case SOLDIER1:
                openXOPSTextureID = 20;
                break;
            case SOLDIER2:
                openXOPSTextureID = 21;
                break;
            case SOLDIER3:
                openXOPSTextureID = 22;
                break;
            case SYATU:
                openXOPSTextureID = 23;
                break;
            case SYATU2:
                openXOPSTextureID = 24;
                break;
            case WOMAN:
                openXOPSTextureID = 25;
                break;
            case ZOMBIE1:
                openXOPSTextureID = 26;
                break;
            case ZOMBIE2:
                openXOPSTextureID = 27;
                break;
            case ZOMBIE3:
                openXOPSTextureID = 28;
                break;
            case ZOMBIE4:
                openXOPSTextureID = 29;
                break;
        }

        return openXOPSTextureID;
    }

    public static CharacterTextureType getXOPSTextureTypeFromOpenXOPSTextureID(
            int openXOPSTextureID) {
        CharacterTextureType xopsTextureType = CharacterTextureType.CIV1;

        switch (openXOPSTextureID) {
            case 0:
                xopsTextureType = CharacterTextureType.CIV1;
                break;
            case 1:
                xopsTextureType = CharacterTextureType.CIV2;
                break;
            case 2:
                xopsTextureType = CharacterTextureType.CIV3;
                break;
            case 3:
                xopsTextureType = CharacterTextureType.GATES;
                break;
            case 4:
                xopsTextureType = CharacterTextureType.GS;
                break;
            case 5:
                xopsTextureType = CharacterTextureType.HAGE;
                break;
            case 6:
                xopsTextureType = CharacterTextureType.ISLAM;
                break;
            case 7:
                xopsTextureType = CharacterTextureType.ISLAM2;
                break;
            case 8:
                xopsTextureType = CharacterTextureType.POLICE;
                break;
            case 9:
                xopsTextureType = CharacterTextureType.RIIMAN;
                break;
            case 10:
                xopsTextureType = CharacterTextureType.RIIMAN_B;
                break;
            case 11:
                xopsTextureType = CharacterTextureType.RIIMAN_G;
                break;
            case 12:
                xopsTextureType = CharacterTextureType.RIIMAN_K;
                break;
            case 13:
                xopsTextureType = CharacterTextureType.ROBOT;
                break;
            case 14:
                xopsTextureType = CharacterTextureType.SOLDIER_BLACK;
                break;
            case 15:
                xopsTextureType = CharacterTextureType.SOLDIER_BLUE;
                break;
            case 16:
                xopsTextureType = CharacterTextureType.SOLDIER_GREEN;
                break;
            case 17:
                xopsTextureType = CharacterTextureType.SOLDIER_VIOLET;
                break;
            case 18:
                xopsTextureType = CharacterTextureType.SOLDIER_WHITE;
                break;
            case 19:
                xopsTextureType = CharacterTextureType.SOLDIER0;
                break;
            case 20:
                xopsTextureType = CharacterTextureType.SOLDIER1;
                break;
            case 21:
                xopsTextureType = CharacterTextureType.SOLDIER2;
                break;
            case 22:
                xopsTextureType = CharacterTextureType.SOLDIER3;
                break;
            case 23:
                xopsTextureType = CharacterTextureType.SYATU;
                break;
            case 24:
                xopsTextureType = CharacterTextureType.SYATU2;
                break;
            case 25:
                xopsTextureType = CharacterTextureType.WOMAN;
                break;
            case 26:
                xopsTextureType = CharacterTextureType.ZOMBIE1;
                break;
            case 27:
                xopsTextureType = CharacterTextureType.ZOMBIE2;
                break;
            case 28:
                xopsTextureType = CharacterTextureType.ZOMBIE3;
                break;
            case 29:
                xopsTextureType = CharacterTextureType.ZOMBIE4;
                break;
            default:
                xopsTextureType = CharacterTextureType.CIV1;
                break;
        }

        return xopsTextureType;
    }

    public static int getOpenXOPSAILevelFromXOPSAILevel(AILevel xopsAILevel) {
        int openXOPSAILevel = 0;

        switch (xopsAILevel) {
            case NONE:
            case NO_WEAPON:
                openXOPSAILevel = 0;
                break;
            case D:
                openXOPSAILevel = 0;
                break;
            case C:
                openXOPSAILevel = 1;
                break;
            case B:
                openXOPSAILevel = 2;
                break;
            case A:
                openXOPSAILevel = 3;
                break;
            case S:
            case SS:
                openXOPSAILevel = 4;
                break;
        }

        return openXOPSAILevel;
    }

    public static AILevel getXOPSAILevelFromOpenXOPSAILevel(int openXOPSAILevel) {
        AILevel xopsAILevel;

        switch (openXOPSAILevel) {
            case 0:
                xopsAILevel = AILevel.D;
                break;
            case 1:
                xopsAILevel = AILevel.C;
                break;
            case 2:
                xopsAILevel = AILevel.B;
                break;
            case 3:
                xopsAILevel = AILevel.A;
                break;
            case 4:
                xopsAILevel = AILevel.S;
                break;
            default:
                xopsAILevel = AILevel.D;
                break;
        }

        return xopsAILevel;
    }
}
