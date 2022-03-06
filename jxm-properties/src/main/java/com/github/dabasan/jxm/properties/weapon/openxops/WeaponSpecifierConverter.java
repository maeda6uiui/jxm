package com.github.dabasan.jxm.properties.weapon.openxops;

/**
 * Conversion between XOPS and OpenXOPS specifiers
 *
 * @author Daba
 */
class WeaponSpecifierConverter {
    public static int getOpenXOPSSoundIDFromXOPSSoundID(int xopsSoundID) {
        int ret;

        switch (xopsSoundID) {
            case 0:
                ret = 0;
                break;
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 2;
                break;
            case 3:
                ret = 3;
                break;
            case 9:
                ret = 13;
                break;
            case 13:
                ret = 4;
                break;
            default:
                ret = 0;
                break;
        }

        return ret;
    }

    public static int getXOPSSoundIDFromOpenXOPSSoundID(int openXOPSSoundID) {
        int ret;

        switch (openXOPSSoundID) {
            case 0:
                ret = 0;
                break;
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 2;
                break;
            case 3:
                ret = 3;
                break;
            case 13:
                ret = 9;
                break;
            case 4:
                ret = 13;
                break;
            default:
                ret = 0;
                break;
        }

        return ret;
    }
}
