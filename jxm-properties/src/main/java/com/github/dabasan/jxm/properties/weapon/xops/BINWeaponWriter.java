package com.github.dabasan.jxm.properties.weapon.xops;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.WeaponBinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * EXE weapon writer
 * 
 * @author Daba
 *
 */
class BINWeaponWriter {
	public void write(byte[] bin, WeaponData[] weapons, int dataStartPos, int nameStartPos) {
		int pos = dataStartPos;
		int numWeapons = weapons.length;
		for (int i = 0; i < numWeapons; i++) {
			// Attacks
			setShortValueToBinLE(bin, pos, (short) weapons[i].attacks);
			pos += 2;
			// Penetration
			setShortValueToBinLE(bin, pos, (short) weapons[i].penetration);
			pos += 2;
			// Blazings
			setShortValueToBinLE(bin, pos, (short) weapons[i].blazings);
			pos += 2;
			// Speed
			setShortValueToBinLE(bin, pos, (short) weapons[i].speed);
			pos += 2;
			// NbsMax
			setShortValueToBinLE(bin, pos, (short) weapons[i].nbsMax);
			pos += 2;
			// Reloads
			setShortValueToBinLE(bin, pos, (short) weapons[i].reloads);
			pos += 2;
			// Reaction
			setShortValueToBinLE(bin, pos, (short) weapons[i].reaction);
			pos += 2;
			// ErrorRangeMin
			setShortValueToBinLE(bin, pos, (short) weapons[i].errorRangeMin);
			pos += 2;
			// ErrorRangeMax
			setShortValueToBinLE(bin, pos, (short) weapons[i].errorRangeMax);
			pos += 2;
			// ModelPositionX
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionX));
			// ModelPositionY
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionY));
			// ModelPositionZ
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionZ));
			// FlashPositionX
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionX));
			// FlashPositionY
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionY));
			// FlashPositionZ
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionZ));
			// YakkyouPositionX
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionX));
			// YakkyouPositionY
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionY));
			// YakkyouPositionZ
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionZ));
			// WeaponP
			ShootingStance shootingStance = weapons[i].weaponP;
			int shootingStanceSpc = WeaponBinEnumConverter
					.getBinSpecifierFromShootingStance(shootingStance);
			setShortValueToBinLE(bin, pos, (short) shootingStanceSpc);
			pos += 2;
			// BlazingMode
			boolean blazingMode = weapons[i].blazingMode;
			int blazingModeSpc = (blazingMode) ? 0 : 1;
			setShortValueToBinLE(bin, pos, (short) blazingModeSpc);
			pos += 2;
			// ScopeMode
			ScopeMode scopeMode = weapons[i].scopeMode;
			int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
			setShortValueToBinLE(bin, pos, (short) scopeModeSpc);
			pos += 2;
			// Texture
			String textureFilepath = weapons[i].texture;
			WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
			int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
			setShortValueToBinLE(bin, pos, (short) textureTypeSpc);
			pos += 2;
			// Model
			String modelFilepath = weapons[i].model;
			WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
			int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
			setShortValueToBinLE(bin, pos, (short) modelTypeSpc);
			pos += 2;
			// Size
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].size * 10.0));
			pos += 2;
			// YakkyouSpeedX
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouSpeedX));
			// YakkyouSpeedY
			setShortValueToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouSpeedY));
			// SoundID
			setShortValueToBinLE(bin, pos, (short) weapons[i].soundID);
			pos += 2;
			// SoundVolume
			setShortValueToBinLE(bin, pos, (short) weapons[i].soundVolume);
			pos += 2;
			// Silencer
			boolean silencer = weapons[i].silencer;
			int silencerSpc = (silencer) ? 1 : 0;
			setShortValueToBinLE(bin, pos, (short) silencerSpc);
			pos += 2;
		}

		// Name
		pos = nameStartPos;

		for (int i = 0; i < numWeapons; i++) {
			String name = weapons[numWeapons - 1 - i].name;
			this.setNameToBin(bin, pos, name);

			pos += 16;
		}
	}
	private void setNameToBin(byte[] bin, int pos, String name) {
		var nameBuffer = new byte[16];
		for (int i = 0; i < 16; i++) {
			nameBuffer[i] = 0;
		}

		for (int i = 0; i < name.length(); i++) {
			if (i >= 15) {
				break;
			}
			nameBuffer[i] = (byte) name.charAt(i);
		}

		for (int i = 0; i < 16; i++) {
			bin[pos + i] = nameBuffer[i];
		}
	}
}
