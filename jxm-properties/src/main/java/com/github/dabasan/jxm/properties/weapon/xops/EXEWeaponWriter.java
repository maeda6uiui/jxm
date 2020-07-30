package com.github.dabasan.jxm.properties.weapon.xops;

import java.util.List;

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
 * EXE weapon writer
 * 
 * @author Daba
 *
 */
class EXEWeaponWriter {
	public EXEWeaponWriter() {

	}

	public void write(List<Byte> bin, WeaponData[] weapons, int dataStartPos, int nameStartPos) {
		int pos = dataStartPos;

		int numWeapons = weapons.length;
		for (int i = 0; i < numWeapons; i++) {
			// Attacks
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getAttacks());
			pos += 2;
			// Penetration
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getPenetration());
			pos += 2;
			// Blazings
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getBlazings());
			pos += 2;
			// Speed
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getSpeed());
			pos += 2;
			// NbsMax
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getNbsMax());
			pos += 2;
			// Reloads
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getReloads());
			pos += 2;
			// Reaction
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getReaction());
			pos += 2;
			// ErrorRangeMin
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getErrorRangeMin());
			pos += 2;
			// ErrorRangeMax
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getErrorRangeMax());
			pos += 2;
			// Model position
			Vector modelPosition = weapons[i].getM();
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) Math.round(modelPosition.getX()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 2,
					(short) Math.round(modelPosition.getY()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 4,
					(short) Math.round(modelPosition.getZ()));
			pos += 6;
			// Flash position
			Vector flashPosition = weapons[i].getM();
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) Math.round(flashPosition.getX()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 2,
					(short) Math.round(flashPosition.getY()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 4,
					(short) Math.round(flashPosition.getZ()));
			pos += 6;
			// Yakkyou position
			Vector yakkyouPosition = weapons[i].getYakkyouPosition();
			ByteFunctions.setShortValueToBinLE(bin, pos,
					(short) Math.round(yakkyouPosition.getX()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 2,
					(short) Math.round(yakkyouPosition.getY()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 4,
					(short) Math.round(yakkyouPosition.getZ()));
			pos += 6;
			// WeaponP
			ShootingStance shootingStance = weapons[i].getWeaponP();
			int shootingStanceSpc = WeaponBinEnumConverter
					.getBinSpecifierFromShootingStance(shootingStance);
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) shootingStanceSpc);
			pos += 2;
			// Blazing mode
			boolean blazingMode = weapons[i].isBlazingMode();
			int blazingModeSpc = (blazingMode) ? 0 : 1;
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) blazingModeSpc);
			pos += 2;
			// Scope mode
			ScopeMode scopeMode = weapons[i].getScopeMode();
			int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) scopeModeSpc);
			pos += 2;
			// Texture
			String textureFilepath = weapons[i].getTexture();
			WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
			int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) textureTypeSpc);
			pos += 2;
			// Model
			String modelFilepath = weapons[i].getModel();
			WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
			int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) modelTypeSpc);
			pos += 2;
			// Size
			ByteFunctions.setShortValueToBinLE(bin, pos,
					(short) Math.round(weapons[i].getSize() * 10.0));
			pos += 2;
			// Yakkyou speed
			Vector yakkyouSpeed = weapons[i].getYakkyouSpeed();
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) Math.round(yakkyouSpeed.getX()));
			ByteFunctions.setShortValueToBinLE(bin, pos + 2,
					(short) Math.round(yakkyouSpeed.getY()));
			pos += 4;
			// Sound ID
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getSoundID());
			pos += 2;
			// Sound volume
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) weapons[i].getSoundVolume());
			pos += 2;
			// Silencer
			boolean silencer = weapons[i].isSilencer();
			int silencerSpc = (silencer) ? 1 : 0;
			ByteFunctions.setShortValueToBinLE(bin, pos, (short) silencerSpc);
			pos += 2;
		}

		// Name
		pos = nameStartPos;

		for (int i = 0; i < numWeapons; i++) {
			String name = weapons[i].getName();
			this.setNameToBin(bin, pos, name);

			pos += 16;
		}
	}
	private void setNameToBin(List<Byte> bin, int pos, String name) {
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
			bin.set(pos + i, nameBuffer[i]);
		}
	}
}
