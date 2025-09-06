package com.github.maeda6uiui.jxm.properties.weapon;

/**
 * Bin to enum converter
 *
 * @author maeda6uiui
 */
public class WeaponBinEnumConverter {
    public static WeaponModelType getModelTypeFromBinSpecifier(int spc) {
        return switch (spc) {
            case 0x00 -> WeaponModelType.NONE;
            case 0x0B -> WeaponModelType.MP5;
            case 0x0A -> WeaponModelType.PSG_1;
            case 0x0D -> WeaponModelType.M92F;
            case 0x10 -> WeaponModelType.GLOCK;
            case 0x15 -> WeaponModelType.DESERT_EAGLE;
            case 0x0E -> WeaponModelType.MAC10;
            case 0x1E -> WeaponModelType.UMP;
            case 0x0F -> WeaponModelType.P90;
            case 0x1D -> WeaponModelType.M4;
            case 0x18 -> WeaponModelType.AK47;
            case 0x16 -> WeaponModelType.AUG;
            case 0x1C -> WeaponModelType.M249;
            case 0x17 -> WeaponModelType.GRENADE;
            case 0x19 -> WeaponModelType.MP5SD;
            case 0x20 -> WeaponModelType.CASE;
            case 0x22 -> WeaponModelType.M1911;
            case 0x39 -> WeaponModelType.M1;
            case 0x3A -> WeaponModelType.FAMAS;
            case 0x3B -> WeaponModelType.MK23;
            case 0x3C -> WeaponModelType.MK23SD;
            default -> throw new IllegalArgumentException(String.format("Unknown specifier was given: 0x%02X", spc));
        };
    }

    public static int getBinSpecifierFromModelType(WeaponModelType modelType) {
        return switch (modelType) {
            case NONE -> 0x00;
            case MP5 -> 0x0B;
            case PSG_1 -> 0x0A;
            case M92F -> 0x0D;
            case GLOCK -> 0x10;
            case DESERT_EAGLE -> 0x15;
            case MAC10 -> 0x0E;
            case UMP -> 0x1E;
            case P90 -> 0x0F;
            case M4 -> 0x1D;
            case AK47 -> 0x18;
            case AUG -> 0x16;
            case M249 -> 0x1C;
            case GRENADE -> 0x17;
            case MP5SD -> 0x19;
            case CASE -> 0x20;
            case M1911 -> 0x22;
            case M1 -> 0x39;
            case FAMAS -> 0x3A;
            case MK23 -> 0x3B;
            case MK23SD -> 0x3C;
        };
    }

    public static WeaponTextureType getTextureTypeFromBinSpecifier(int spc) {
        return switch (spc) {
            case 0x00 -> WeaponTextureType.NONE;
            case 0x10 -> WeaponTextureType.MP5;
            case 0x0B -> WeaponTextureType.PSG_1;
            case 0x13 -> WeaponTextureType.M92F;
            case 0x11 -> WeaponTextureType.GLOCK18;
            case 0x32 -> WeaponTextureType.DESERT_EAGLE;
            case 0x28 -> WeaponTextureType.MAC10;
            case 0x27 -> WeaponTextureType.UMP;
            case 0x24 -> WeaponTextureType.P90;
            case 0x26 -> WeaponTextureType.M4;
            case 0x21 -> WeaponTextureType.AK47;
            case 0x33 -> WeaponTextureType.AUG;
            case 0x25 -> WeaponTextureType.M249;
            case 0x20 -> WeaponTextureType.GRENADE;
            case 0x22 -> WeaponTextureType.MP5SD;
            case 0x2C -> WeaponTextureType.CASE;
            case 0x2D -> WeaponTextureType.M1911;
            case 0x30 -> WeaponTextureType.GLOCK17;
            case 0x36 -> WeaponTextureType.M1;
            case 0x37 -> WeaponTextureType.FAMAS;
            case 0x38 -> WeaponTextureType.MK23;
            default -> throw new IllegalArgumentException(String.format("Unknown specifier was given: 0x%02X", spc));
        };
    }

    public static int getBinSpecifierFromTextureType(WeaponTextureType textureType) {
        return switch (textureType) {
            case NONE -> 0x00;
            case MP5 -> 0x10;
            case PSG_1 -> 0x0B;
            case M92F -> 0x13;
            case GLOCK18 -> 0x11;
            case DESERT_EAGLE -> 0x32;
            case MAC10 -> 0x28;
            case UMP -> 0x27;
            case P90 -> 0x24;
            case M4 -> 0x26;
            case AK47 -> 0x21;
            case AUG -> 0x33;
            case M249 -> 0x25;
            case GRENADE -> 0x20;
            case MP5SD -> 0x22;
            case CASE -> 0x2C;
            case M1911 -> 0x2D;
            case GLOCK17 -> 0x30;
            case M1 -> 0x36;
            case FAMAS -> 0x37;
            case MK23 -> 0x38;
        };
    }

    public static ShootingStance getShootingStanceFromBinSpecifier(int spc) {
        return switch (spc) {
            case 0x08 -> ShootingStance.RIFLE;
            case 0x09 -> ShootingStance.HANDGUN;
            case 0x1B -> ShootingStance.CARRY;
            default -> throw new IllegalArgumentException(String.format("Unknown specifier was given: 0x%02X", spc));
        };
    }

    public static int getBinSpecifierFromShootingStance(ShootingStance shootingStance) {
        return switch (shootingStance) {
            case RIFLE -> 0x08;
            case HANDGUN -> 0x09;
            case CARRY -> 0x1B;
        };
    }

    public static ScopeMode getScopeModeFromBinSpecifier(int spc) {
        return switch (spc) {
            case 0x00 -> ScopeMode.NONE;
            case 0x01 -> ScopeMode.LOW;
            case 0x02 -> ScopeMode.HIGH;
            case 0x03 -> ScopeMode.EQUAL;
            default -> throw new IllegalArgumentException(String.format("Unknown specifier was given: 0x%02X", spc));
        };
    }

    public static int getBinSpecifierFromScopeMode(ScopeMode scopeMode) {
        return switch (scopeMode) {
            case NONE -> 0x00;
            case LOW -> 0x01;
            case HIGH -> 0x02;
            case EQUAL -> 0x03;
        };
    }
}
