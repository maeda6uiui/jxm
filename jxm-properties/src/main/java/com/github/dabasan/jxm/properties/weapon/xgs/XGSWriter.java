package com.github.dabasan.jxm.properties.weapon.xgs;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.dabasan.jxm.properties.weapon.ModelFilepaths;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.TextureFilepaths;
import com.github.dabasan.jxm.properties.weapon.WeaponBinEnumConverter;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * XGS writer
 * 
 * @author Daba
 *
 */
class XGSWriter {
	public void write(OutputStream os, Weapon[] weapons) throws IOException {
		var bin = new ArrayList<Byte>();

		bin.add((byte) 0x58);// X
		bin.add((byte) 0x47);// G
		bin.add((byte) 0x53);// S
		bin.add((byte) 0x00);
		bin.add((byte) 0x01);
		bin.add((byte) 0x00);
		bin.add((byte) 0x0E);
		bin.add((byte) 0x00);
		bin.add((byte) 0x17);
		bin.add((byte) 0x00);
		bin.add((byte) 0x1D);
		bin.add((byte) 0x00);
		bin.add((byte) 0x08);
		bin.add((byte) 0x00);

		int numWeapons = weapons.length;
		for (int i = 0; i < numWeapons; i++) {
			// Attacks
			addShortValueToBinLE(bin, (short) weapons[i].attacks);
			// Penetration
			addShortValueToBinLE(bin, (short) weapons[i].penetration);
			// Blazings
			addShortValueToBinLE(bin, (short) weapons[i].blazings);
			// Speed
			addShortValueToBinLE(bin, (short) weapons[i].speed);
			// NbsMax
			addShortValueToBinLE(bin, (short) weapons[i].nbsMax);
			// Reloads
			addShortValueToBinLE(bin, (short) weapons[i].reloads);
			// Reaction
			addShortValueToBinLE(bin, (short) weapons[i].reaction);
			// ErrorRangeMin
			addShortValueToBinLE(bin, (short) weapons[i].errorRangeMin);
			// ErrorRangeMax
			addShortValueToBinLE(bin, (short) weapons[i].errorRangeMax);
			// ModelPositionX
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].modelPositionX));
			// ModelPositionY
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].modelPositionY));
			// ModelPositionZ
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].modelPositionZ));
			// FlashPositionX
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].flashPositionX));
			// FlashPositionY
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].flashPositionY));
			// FlashPositionZ
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].flashPositionZ));
			// YakkyouPositionX
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].yakkyouPositionX));
			// YakkyouPositionY
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].yakkyouPositionY));
			// YakkyouPositionZ
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].yakkyouPositionZ));
			// WeaponP
			ShootingStance shootingStance = weapons[i].weaponP;
			int shootingStanceSpc = WeaponBinEnumConverter
					.getBinSpecifierFromShootingStance(shootingStance);
			addShortValueToBinLE(bin, (short) shootingStanceSpc);
			// BlazingMode
			boolean blazingMode = weapons[i].blazingMode;
			int blazingModeSpc = (blazingMode) ? 0 : 1;
			addShortValueToBinLE(bin, (short) blazingModeSpc);
			// ScopeMode
			ScopeMode scopeMode = weapons[i].scopeMode;
			int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
			addShortValueToBinLE(bin, (short) scopeModeSpc);
			// Texture
			String textureFilepath = weapons[i].texture;
			WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
			int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
			addShortValueToBinLE(bin, (short) textureTypeSpc);
			// Model
			String modelFilepath = weapons[i].model;
			WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
			int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
			addShortValueToBinLE(bin, (short) modelTypeSpc);
			// Size
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].size * 10.0f));
			// YakkyouSpeedX
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].yakkyouSpeedX));
			// YakkyouSpeedY
			addShortValueToBinLE(bin, (short) Math.round(weapons[i].yakkyouSpeedY));
			// SoundID
			addShortValueToBinLE(bin, (short) weapons[i].soundID);
			// SoundVolume
			addShortValueToBinLE(bin, (short) weapons[i].soundVolume);
			// Silencer
			boolean silencer = weapons[i].silencer;
			int silencerSpc = (silencer) ? 1 : 0;
			addShortValueToBinLE(bin, (short) silencerSpc);
		}

		// Name
		for (int i = 0; i < numWeapons; i++) {
			String name = weapons[numWeapons - 1 - i].name;
			this.addNameToBin(bin, name);
		}

		// Zero padding
		for (int i = 0; i < 16; i++) {
			bin.add((byte) 0x00);
		}

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
		}
	}
	private void addNameToBin(List<Byte> bin, String name) {
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
			bin.add(nameBuffer[i]);
		}
	}
}
