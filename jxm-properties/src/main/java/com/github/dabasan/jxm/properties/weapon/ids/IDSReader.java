package com.github.dabasan.jxm.properties.weapon.ids;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.WeaponBinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * IDS reader
 * 
 * @author Daba
 *
 */
class IDSReader {
	private Weapon weapon;

	public IDSReader(InputStream is) throws IOException {
		weapon = new Weapon();

		// Read all bytes from a stream
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0x0000000A;

		// Attacks
		weapon.attacks = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Penetration
		weapon.penetration = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Blazings
		weapon.blazings = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Speed
		weapon.speed = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// NbsMax
		weapon.nbsMax = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Reloads
		weapon.reloads = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Reaction
		weapon.reaction = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// ErrorRangeMin
		weapon.errorRangeMin = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// ErrorRangeMax
		weapon.errorRangeMax = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// ModelPositionX
		weapon.modelPositionX = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// ModelPositionY
		weapon.modelPositionY = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// ModelPositionZ
		weapon.modelPositionZ = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// FlashPositionX
		weapon.flashPositionX = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// FlashPositionY
		weapon.flashPositionY = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// FlashPositionZ
		weapon.flashPositionZ = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// YakkyouPositionX
		weapon.yakkyouPositionX = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// YakkyouPositionY
		weapon.yakkyouPositionY = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// YakkyouPositionZ
		weapon.yakkyouPositionZ = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// WeaponP
		int shootingStanceSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		weapon.weaponP = WeaponBinEnumConverter
				.getShootingStanceFromBinSpecifier(shootingStanceSpc);
		// BlazingMode
		int blazingModeSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		weapon.blazingMode = (blazingModeSpc == 0) ? true : false;
		// ScopeMode
		int scopeModeSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
		// Texture
		int textureTypeSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		WeaponTextureType textureType = WeaponBinEnumConverter
				.getTextureTypeFromBinSpecifier(textureTypeSpc);
		weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());
		// Model
		int modelTypeSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		WeaponModelType modelType = WeaponBinEnumConverter
				.getModelTypeFromBinSpecifier(modelTypeSpc);
		weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());
		// Size
		weapon.size = getShortValueFromBinLE(bin, pos) * 0.1f;
		pos += 2;
		// YakkyouSpeedX
		weapon.yakkyouSpeedX = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// YakkyouSpeedY
		weapon.yakkyouSpeedY = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// SoundID
		weapon.soundID = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// SoundVolume
		weapon.soundVolume = getShortValueFromBinLE(bin, pos);
		pos += 2;
		// Silencer
		int silencerSpc = getShortValueFromBinLE(bin, pos);
		pos += 2;
		weapon.silencer = (silencerSpc == 0) ? false : true;
		// Name
		weapon.name = this.getNameFromBin(bin, pos);
		pos += 2;
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

	public Weapon getWeaponData() {
		return weapon;
	}
}
