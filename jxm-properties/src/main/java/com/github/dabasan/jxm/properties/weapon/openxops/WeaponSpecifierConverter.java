package com.github.dabasan.jxm.properties.weapon.openxops;

/**
 * Conversion between XOPS and OpenXOPS specifiers
 *
 * @author maeda6uiui
 */
class WeaponSpecifierConverter {
    public static int getOpenXOPSSoundIdFromXOPSSoundId(int xopsSoundId) {
        return switch (xopsSoundId) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 9 -> 13;
            case 13 -> 4;
            default ->
                    throw new IllegalArgumentException(String.format("Unknown specifier was given: %d", xopsSoundId));
        };
    }

    public static int getXOPSSoundIdFromOpenXOPSSoundId(int openXOPSSoundId) {
        return switch (openXOPSSoundId) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 13 -> 9;
            case 4 -> 13;
            default ->
                    throw new IllegalArgumentException(String.format("Unknown specifier was given: %d", openXOPSSoundId));
        };
    }
}
