package com.github.dabasan.jxm.properties.weapon.xops;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.WeaponBinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * EXE weapon reader
 * 
 * @author Daba
 *
 */
class BINWeaponReader {
	private Weapon[] weapons;

	public BINWeaponReader(byte[] bin, int numWeapons, int dataStartPos, int nameStartPos) {
		weapons = new Weapon[numWeapons];

		int pos = dataStartPos;
		for (int i = 0; i < numWeapons; i++) {
			var weapon = new Weapon();

			// Attacks
			weapon.attacks = getShortFromBinLE(bin, pos);
			pos += 2;
			// Penetration
			weapon.penetration = getShortFromBinLE(bin, pos);
			pos += 2;
			// Blazings
			weapon.blazings = getShortFromBinLE(bin, pos);
			pos += 2;
			// Speed
			weapon.speed = getShortFromBinLE(bin, pos);
			pos += 2;
			// NbsMax
			weapon.nbsMax = getShortFromBinLE(bin, pos);
			pos += 2;
			// Reloads
			weapon.reloads = getShortFromBinLE(bin, pos);
			pos += 2;
			// Reaction
			weapon.reaction = getShortFromBinLE(bin, pos);
			pos += 2;
			// ErrorRangeMin
			weapon.errorRangeMin = getShortFromBinLE(bin, pos);
			pos += 2;
			// ErrorRangeMax
			weapon.errorRangeMax = getShortFromBinLE(bin, pos);
			pos += 2;
			// ModelPositionX
			weapon.modelPositionX = getShortFromBinLE(bin, pos);
			pos += 2;
			// ModelPositionY
			weapon.modelPositionY = getShortFromBinLE(bin, pos);
			pos += 2;
			// ModelPositionZ
			weapon.modelPositionZ = getShortFromBinLE(bin, pos);
			pos += 2;
			// FlashPositionX
			weapon.flashPositionX = getShortFromBinLE(bin, pos);
			pos += 2;
			// FlashPositionY
			weapon.flashPositionY = getShortFromBinLE(bin, pos);
			pos += 2;
			// FlashPositionZ
			weapon.flashPositionZ = getShortFromBinLE(bin, pos);
			pos += 2;
			// YakkyouPositionX
			weapon.yakkyouPositionX = getShortFromBinLE(bin, pos);
			pos += 2;
			// YakkyouPositionY
			weapon.yakkyouPositionY = getShortFromBinLE(bin, pos);
			pos += 2;
			// YakkyouPositionZ
			weapon.yakkyouPositionZ = getShortFromBinLE(bin, pos);
			pos += 2;
			// WeaponP
			int shootingStanceSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			weapon.weaponP = WeaponBinEnumConverter
					.getShootingStanceFromBinSpecifier(shootingStanceSpc);
			// BlazingMode
			int blazingModeSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			weapon.blazingMode = (blazingModeSpc == 0) ? true : false;
			// ScopeMode
			int scopeModeSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
			// Texture
			int textureTypeSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			WeaponTextureType textureType = WeaponBinEnumConverter
					.getTextureTypeFromBinSpecifier(textureTypeSpc);
			weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());
			// Model
			int modelTypeSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			WeaponModelType modelType = WeaponBinEnumConverter
					.getModelTypeFromBinSpecifier(modelTypeSpc);
			weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());
			// Size
			weapon.size = getShortFromBinLE(bin, pos) * 0.1f;
			pos += 2;
			// YakkyouSpeedX
			weapon.yakkyouSpeedX = getShortFromBinLE(bin, pos);
			pos += 2;
			// YakkyouSpeedY
			weapon.yakkyouSpeedY = getShortFromBinLE(bin, pos);
			pos += 2;
			// SoundID
			weapon.soundID = getShortFromBinLE(bin, pos);
			pos += 2;
			// SoundVolume
			weapon.soundVolume = getShortFromBinLE(bin, pos);
			pos += 2;
			// Silencer
			int silencerSpc = getShortFromBinLE(bin, pos);
			pos += 2;
			weapon.silencer = (silencerSpc == 0) ? false : true;

			// Change weapon
			if (i == 4) {
				weapon.changeWeapon = 16;
			} else if (i == 16) {
				weapon.changeWeapon = 4;
			}

			// Burst
			if (i == 19) {
				weapon.burst = 6;
			}

			weapons[i] = weapon;
		}

		// Name
		pos = nameStartPos;

		for (int i = 0; i < numWeapons; i++) {
			String name = this.getNameFromBin(bin, pos);
			pos += 16;

			weapons[numWeapons - 1 - i].name = name;
		}
	}
	private String getNameFromBin(byte[] bin, int start) {
		var nameBuffer = new byte[16];
		for (int i = 0; i < 15; i++) {
			nameBuffer[i] = bin[start + i];
		}
		nameBuffer[15] = 0;

		var name = new String(nameBuffer);

		int firstNullPos = 15;
		for (int i = 0; i < 15; i++) {
			if (name.charAt(i) == 0) {
				firstNullPos = i;
				break;
			}
		}

		return name.substring(0, firstNullPos);
	}

	public Weapon[] getWeaponData() {
		return weapons;
	}
}
