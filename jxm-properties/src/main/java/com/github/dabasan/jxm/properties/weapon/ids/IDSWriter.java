package com.github.dabasan.jxm.properties.weapon.ids;

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
import com.github.dabasan.jxm.properties.weapon.WeaponData;
import com.github.dabasan.jxm.properties.weapon.WeaponModelType;
import com.github.dabasan.jxm.properties.weapon.WeaponTextureType;

/**
 * IDS writer
 * 
 * @author Daba
 *
 */
class IDSWriter {
	public void write(OutputStream os, WeaponData weapon) throws IOException {
		var bin = new ArrayList<Byte>();

		bin.add((byte) 0x49);// I
		bin.add((byte) 0x44);// D
		bin.add((byte) 0x53);// S
		bin.add((byte) 0x00);
		bin.add((byte) 0x01);
		bin.add((byte) 0x00);
		bin.add((byte) 0x0A);
		bin.add((byte) 0x00);
		bin.add((byte) 0x1D);
		bin.add((byte) 0x00);

		// Attacks
		addShortValueToBinLE(bin, (short) weapon.attacks);
		// Penetration
		addShortValueToBinLE(bin, (short) weapon.penetration);
		// Blazings
		addShortValueToBinLE(bin, (short) weapon.blazings);
		// Speed
		addShortValueToBinLE(bin, (short) weapon.speed);
		// NbsMax
		addShortValueToBinLE(bin, (short) weapon.nbsMax);
		// Reloads
		addShortValueToBinLE(bin, (short) weapon.reloads);
		// Reaction
		addShortValueToBinLE(bin, (short) weapon.reaction);
		// ErrorRangeMin
		addShortValueToBinLE(bin, (short) weapon.errorRangeMin);
		// ErrorRangeMax
		addShortValueToBinLE(bin, (short) weapon.errorRangeMax);
		// ModelPositionX
		addShortValueToBinLE(bin, (short) Math.round(weapon.modelPositionX));
		// ModelPositionY
		addShortValueToBinLE(bin, (short) Math.round(weapon.modelPositionY));
		// ModelPositionZ
		addShortValueToBinLE(bin, (short) Math.round(weapon.modelPositionZ));
		// FlashPositionX
		addShortValueToBinLE(bin, (short) Math.round(weapon.flashPositionX));
		// FlashPositionY
		addShortValueToBinLE(bin, (short) Math.round(weapon.flashPositionY));
		// FlashPositionZ
		addShortValueToBinLE(bin, (short) Math.round(weapon.flashPositionZ));
		// YakkyouPositionX
		addShortValueToBinLE(bin, (short) Math.round(weapon.yakkyouPositionX));
		// YakkyouPositionY
		addShortValueToBinLE(bin, (short) Math.round(weapon.yakkyouPositionY));
		// YakkyouPositionZ
		addShortValueToBinLE(bin, (short) Math.round(weapon.yakkyouPositionZ));
		// WeaponP
		ShootingStance shootingStance = weapon.weaponP;
		int shootingStanceSpc = WeaponBinEnumConverter
				.getBinSpecifierFromShootingStance(shootingStance);
		addShortValueToBinLE(bin, (short) shootingStanceSpc);
		// BlazingMode
		boolean blazingMode = weapon.blazingMode;
		int blazingModeSpc = (blazingMode) ? 0 : 1;
		addShortValueToBinLE(bin, (short) blazingModeSpc);
		// ScopeMode
		ScopeMode scopeMode = weapon.scopeMode;
		int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
		addShortValueToBinLE(bin, (short) scopeModeSpc);
		// Texture
		String textureFilepath = weapon.texture;
		WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
		int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
		addShortValueToBinLE(bin, (short) textureTypeSpc);
		// Model
		String modelFilepath = weapon.model;
		WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
		int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
		addShortValueToBinLE(bin, (short) modelTypeSpc);
		// Size
		addShortValueToBinLE(bin, (short) Math.round(weapon.size * 10.0f));
		// YakkyouSpeedX
		addShortValueToBinLE(bin, (short) Math.round(weapon.yakkyouSpeedX));
		// YakkyouSpeedY
		addShortValueToBinLE(bin, (short) Math.round(weapon.yakkyouSpeedY));
		// SoundID
		addShortValueToBinLE(bin, (short) weapon.soundID);
		// SoundVolume
		addShortValueToBinLE(bin, (short) weapon.soundVolume);
		// Silencer
		boolean silencer = weapon.silencer;
		int silencerSpc = (silencer) ? 1 : 0;
		addShortValueToBinLE(bin, (short) silencerSpc);
		// Name
		this.addNameToBin(bin, weapon.name);

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
