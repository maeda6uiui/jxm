package com.github.dabasan.jxm.properties.weapon;

/**
 * Bin to enum converter
 * 
 * @author Daba
 *
 */
public class BinToEnumConverter {
	public static ModelType getModelTypeFromBinSpecifier(int spc) {
		ModelType modelType;

		switch (spc) {
			case 0x00 :
				modelType = ModelType.NONE;
				break;
			case 0x0B :
				modelType = ModelType.MP5;
				break;
			case 0x0A :
				modelType = ModelType.PSG_1;
				break;
			case 0x0D :
				modelType = ModelType.M92F;
				break;
			case 0x10 :
				modelType = ModelType.GLOCK;
				break;
			case 0x15 :
				modelType = ModelType.DESERT_EAGLE;
				break;
			case 0x0E :
				modelType = ModelType.MAC10;
				break;
			case 0x1E :
				modelType = ModelType.UMP;
				break;
			case 0x0F :
				modelType = ModelType.P90;
				break;
			case 0x1D :
				modelType = ModelType.M4;
				break;
			case 0x18 :
				modelType = ModelType.AK47;
				break;
			case 0x16 :
				modelType = ModelType.AUG;
				break;
			case 0x1C :
				modelType = ModelType.M249;
				break;
			case 0x17 :
				modelType = ModelType.GRENADE;
				break;
			case 0x19 :
				modelType = ModelType.MP5SD;
				break;
			case 0x20 :
				modelType = ModelType.CASE;
				break;
			case 0x22 :
				modelType = ModelType.M1911;
				break;
			case 0x39 :
				modelType = ModelType.M1;
				break;
			case 0x3A :
				modelType = ModelType.FAMAS;
				break;
			case 0x3B :
				modelType = ModelType.MK23;
				break;
			case 0x3C :
				modelType = ModelType.MK23SD;
				break;
			default :
				modelType = ModelType.NONE;
				break;
		}

		return modelType;
	}
	public static int getBinSpecifierFromModelType(ModelType modelType) {
		int spc = 0x00;

		switch (modelType) {
			case NONE :
				spc = 0x00;
				break;
			case MP5 :
				spc = 0x0B;
				break;
			case PSG_1 :
				spc = 0x0A;
				break;
			case M92F :
				spc = 0x0D;
				break;
			case GLOCK :
				spc = 0x10;
				break;
			case DESERT_EAGLE :
				spc = 0x15;
				break;
			case MAC10 :
				spc = 0x0E;
				break;
			case UMP :
				spc = 0x1E;
				break;
			case P90 :
				spc = 0x0F;
				break;
			case M4 :
				spc = 0x1D;
				break;
			case AK47 :
				spc = 0x18;
				break;
			case AUG :
				spc = 0x16;
				break;
			case M249 :
				spc = 0x1C;
				break;
			case GRENADE :
				spc = 0x17;
				break;
			case MP5SD :
				spc = 0x19;
				break;
			case CASE :
				spc = 0x20;
				break;
			case M1911 :
				spc = 0x22;
				break;
			case M1 :
				spc = 0x39;
				break;
			case FAMAS :
				spc = 0x3A;
				break;
			case MK23 :
				spc = 0x3B;
				break;
			case MK23SD :
				spc = 0x3C;
				break;
		}

		return spc;
	}

	public static TextureType getTextureTypeFromBinSpecifier(int spc) {
		TextureType textureType;

		switch (spc) {
			case 0x00 :
				textureType = TextureType.NONE;
				break;
			case 0x10 :
				textureType = TextureType.MP5;
				break;
			case 0x0B :
				textureType = TextureType.PSG_1;
				break;
			case 0x13 :
				textureType = TextureType.M92F;
				break;
			case 0x11 :
				textureType = TextureType.GLOCK18;
				break;
			case 0x32 :
				textureType = TextureType.DESERT_EAGLE;
				break;
			case 0x28 :
				textureType = TextureType.MAC10;
				break;
			case 0x27 :
				textureType = TextureType.UMP;
				break;
			case 0x24 :
				textureType = TextureType.P90;
				break;
			case 0x26 :
				textureType = TextureType.M4;
				break;
			case 0x21 :
				textureType = TextureType.AK47;
				break;
			case 0x33 :
				textureType = TextureType.AUG;
				break;
			case 0x25 :
				textureType = TextureType.M249;
				break;
			case 0x20 :
				textureType = TextureType.GRENADE;
				break;
			case 0x22 :
				textureType = TextureType.MP5SD;
				break;
			case 0x2C :
				textureType = TextureType.CASE;
				break;
			case 0x2D :
				textureType = TextureType.M1911;
				break;
			case 0x30 :
				textureType = TextureType.GLOCK17;
				break;
			case 0x36 :
				textureType = TextureType.M1;
				break;
			case 0x37 :
				textureType = TextureType.FAMAS;
				break;
			case 0x38 :
				textureType = TextureType.MK23;
				break;
			default :
				textureType = TextureType.NONE;
				break;
		}

		return textureType;
	}
	public static int getBinSpecifierFromTextureType(TextureType textureType) {
		int spc = 0x00;

		switch (textureType) {
			case NONE :
				spc = 0x00;
				break;
			case MP5 :
				spc = 0x10;
				break;
			case PSG_1 :
				spc = 0x0B;
				break;
			case M92F :
				spc = 0x13;
				break;
			case GLOCK18 :
				spc = 0x11;
				break;
			case DESERT_EAGLE :
				spc = 0x32;
				break;
			case MAC10 :
				spc = 0x28;
				break;
			case UMP :
				spc = 0x27;
				break;
			case P90 :
				spc = 0x24;
				break;
			case M4 :
				spc = 0x26;
				break;
			case AK47 :
				spc = 0x21;
				break;
			case AUG :
				spc = 0x33;
				break;
			case M249 :
				spc = 0x25;
				break;
			case GRENADE :
				spc = 0x20;
				break;
			case MP5SD :
				spc = 0x22;
				break;
			case CASE :
				spc = 0x2C;
				break;
			case M1911 :
				spc = 0x2D;
				break;
			case GLOCK17 :
				spc = 0x30;
				break;
			case M1 :
				spc = 0x36;
				break;
			case FAMAS :
				spc = 0x37;
				break;
			case MK23 :
				spc = 0x38;
				break;
		}

		return spc;
	}

	public static ShootingStance getShootingStanceFromBinSpecifier(int spc) {
		ShootingStance shootingStance;

		switch (spc) {
			case 0x08 :
				shootingStance = ShootingStance.RIFLE;
				break;
			case 0x09 :
				shootingStance = ShootingStance.HANDGUN;
				break;
			case 0x1B :
				shootingStance = ShootingStance.CARRY;
				break;
			default :
				shootingStance = ShootingStance.RIFLE;
				break;
		}

		return shootingStance;
	}
	public static int getBinSpecifierFromShootingStance(ShootingStance shootingStance) {
		int spc = 0x08;

		switch (shootingStance) {
			case RIFLE :
				spc = 0x08;
				break;
			case HANDGUN :
				spc = 0x09;
				break;
			case CARRY :
				spc = 0x1B;
				break;
		}

		return spc;
	}

	public static ScopeMode getScopeModeFromBinSpecifier(int spc) {
		ScopeMode scopeMode;

		switch (spc) {
			case 0x00 :
				scopeMode = ScopeMode.NONE;
				break;
			case 0x01 :
				scopeMode = ScopeMode.LOW;
				break;
			case 0x02 :
				scopeMode = ScopeMode.HIGH;
				break;
			case 0x03 :
				scopeMode = ScopeMode.EQUAL;
				break;
			default :
				scopeMode = ScopeMode.NONE;
				break;
		}

		return scopeMode;
	}
	public static int getBinSpecifierFromScopeMode(ScopeMode scopeMode) {
		int spc = 0x00;

		switch (scopeMode) {
			case NONE :
				spc = 0x00;
				break;
			case LOW :
				spc = 0x01;
				break;
			case HIGH :
				spc = 0x02;
				break;
			case EQUAL :
				spc = 0x03;
				break;
		}

		return spc;
	}
}
