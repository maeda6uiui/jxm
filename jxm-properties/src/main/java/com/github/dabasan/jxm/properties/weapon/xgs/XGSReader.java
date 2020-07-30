package com.github.dabasan.jxm.properties.weapon.xgs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.dabasan.ejml_3dtools.Vector;
import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.weapon.BinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.ModelType;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.TextureType;
import com.github.dabasan.jxm.properties.weapon.WeaponData;

/**
 * XGS reader
 * 
 * @author Daba
 *
 */
class XGSReader {
	private WeaponData[] weapons;

	public XGSReader(InputStream is, int numWeapons) throws IOException {
		weapons = new WeaponData[numWeapons];

		// Read all bytes from a stream.
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0x0000000E;

		for (int i = 0; i < numWeapons; i++) {
			var weapon = new WeaponData();

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
			pos += 2;
			int my = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int mz = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			weapon.setM(new Vector(mx, my, mz));
			// Flash position
			int flashx = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int flashy = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int flashz = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			weapon.setFlash(new Vector(flashx, flashy, flashz));
			// Yakkyou position
			int yakkyouPx = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int yakkyouPy = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int yakkyouPz = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			weapon.setYakkyouPosition(new Vector(yakkyouPx, yakkyouPy, yakkyouPz));
			// WeaponP
			int shootingStanceSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			ShootingStance shootingStance = BinEnumConverter
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
			ScopeMode scopeMode = BinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
			weapon.setScopeMode(scopeMode);
			// Texture
			int textureTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			TextureType textureType = BinEnumConverter
					.getTextureTypeFromBinSpecifier(textureTypeSpc);
			String textureFilepath = TextureFilepaths.getTextureFilepath(textureType.ordinal());
			weapon.setTexture(textureFilepath);
			// Model
			int modelTypeSpc = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			ModelType modelType = BinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);
			String modelFilepath = ModelFilepaths.getModelFilepath(modelType.ordinal());
			weapon.setModel(modelFilepath);
			// Size
			weapon.setSize(ByteFunctions.getShortValueFromBinLE(bin, pos) * 0.1f);
			pos += 2;
			// Yakkyou speed
			int yakkyouSx = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
			int yakkyouSy = ByteFunctions.getShortValueFromBinLE(bin, pos);
			pos += 2;
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

			// Change weapon
			if (i == 4) {
				weapon.setChangeWeapon(16);
			} else if (i == 16) {
				weapon.setChangeWeapon(4);
			}

			// Burst
			if (i == 19) {
				weapon.setBurst(6);
			}

			weapons[i] = weapon;
		}

		// Name
		pos = 0x00000544;

		for (int i = 0; i < numWeapons; i++) {
			String name = this.getNameFromBin(bin, pos);
			pos += 16;

			weapons[numWeapons - 1 - i].setName(name);
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

	public WeaponData[] getWeaponData() {
		return weapons;
	}
}
