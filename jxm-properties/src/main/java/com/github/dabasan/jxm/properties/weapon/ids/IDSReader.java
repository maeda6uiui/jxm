package com.github.dabasan.jxm.properties.weapon.ids;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.dabasan.ejml_3dtools.Vector;
import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.WeaponBinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * IDS reader
 * 
 * @author Daba
 *
 */
class IDSReader {
	private WeaponData weapon;

	public IDSReader(InputStream is) throws IOException {
		weapon = new WeaponData();

		// Read all bytes from a stream.
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0x0000000A;

		// Attacks
		weapon.setAttacks(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Penetration
		weapon.setPenetration(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Blazings
		weapon.setBlazings(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Speed
		weapon.setSpeed(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// NbsMax
		weapon.setNbsMax(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Reloads
		weapon.setReloads(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Reaction
		weapon.setReaction(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// ErrorRangeMin
		weapon.setErrorRangeMin(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// ErrorRangeMax
		weapon.setErrorRangeMax(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Model position
		int mx = ByteFunctions.getShortValueFromBinLE(bin, pos);
		int my = ByteFunctions.getShortValueFromBinLE(bin, pos + 2);
		int mz = ByteFunctions.getShortValueFromBinLE(bin, pos + 4);
		pos += 6;
		weapon.setModelPosition(new Vector(mx, my, mz));
		// Flash position
		int flashx = ByteFunctions.getShortValueFromBinLE(bin, pos);
		int flashy = ByteFunctions.getShortValueFromBinLE(bin, pos + 2);
		int flashz = ByteFunctions.getShortValueFromBinLE(bin, pos + 4);
		pos += 6;
		weapon.setFlashPosition(new Vector(flashx, flashy, flashz));
		// Yakkyou position
		int yakkyouPx = ByteFunctions.getShortValueFromBinLE(bin, pos);
		int yakkyouPy = ByteFunctions.getShortValueFromBinLE(bin, pos + 2);
		int yakkyouPz = ByteFunctions.getShortValueFromBinLE(bin, pos + 4);
		pos += 6;
		weapon.setYakkyouPosition(new Vector(yakkyouPx, yakkyouPy, yakkyouPz));
		// WeaponP
		int shootingStanceSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		ShootingStance shootingStance = WeaponBinEnumConverter
				.getShootingStanceFromBinSpecifier(shootingStanceSpc);
		weapon.setWeaponP(shootingStance);
		// Blazing mode
		int blazingModeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		boolean blazingMode = (blazingModeSpc == 0) ? true : false;
		weapon.setBlazingMode(blazingMode);
		// Scope mode
		int scopeModeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		ScopeMode scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
		weapon.setScopeMode(scopeMode);
		// Texture
		int textureTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		WeaponTextureType textureType = WeaponBinEnumConverter
				.getTextureTypeFromBinSpecifier(textureTypeSpc);
		String textureFilepath = TextureFilepaths.getTextureFilepath(textureType.ordinal());
		weapon.setTexture(textureFilepath);
		// Model
		int modelTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		WeaponModelType modelType = WeaponBinEnumConverter
				.getModelTypeFromBinSpecifier(modelTypeSpc);
		String modelFilepath = ModelFilepaths.getModelFilepath(modelType.ordinal());
		weapon.setModel(modelFilepath);
		// Size
		weapon.setSize(ByteFunctions.getShortValueFromBinLE(bin, pos) * 0.1f);
		pos += 2;
		// Yakkyou speed
		int yakkyouSx = ByteFunctions.getShortValueFromBinLE(bin, pos);
		int yakkyouSy = ByteFunctions.getShortValueFromBinLE(bin, pos + 2);
		pos += 4;
		weapon.setYakkyouSpeed(new Vector(yakkyouSx, yakkyouSy, 0.0));
		// Sound ID
		weapon.setSoundID(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Sound volume
		weapon.setSoundVolume(ByteFunctions.getShortValueFromBinLE(bin, pos));
		pos += 2;
		// Silencer
		int silencerSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
		pos += 2;
		boolean silencer = (silencerSpc == 0) ? false : true;
		weapon.setSilencer(silencer);
		// Name
		weapon.setName(this.getNameFromBin(bin, pos));
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

	public WeaponData getWeaponData() {
		return weapon;
	}
}
