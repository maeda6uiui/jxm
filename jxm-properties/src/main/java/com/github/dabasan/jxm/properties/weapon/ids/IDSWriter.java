package com.github.dabasan.jxm.properties.weapon.ids;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
 * IDS writer
 * 
 * @author Daba
 *
 */
class IDSWriter {
	public IDSWriter() {

	}

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
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getAttacks());
		// Penetration
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getPenetration());
		// Blazings
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getBlazings());
		// Speed
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getSpeed());
		// NbsMax
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getNbsMax());
		// Reloads
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getReloads());
		// Reaction
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getReaction());
		// ErrorRangeMin
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getErrorRangeMin());
		// ErrorRangeMax
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getErrorRangeMax());
		// ModelPositionX
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getModelPositionX()));
		// ModelPositionY
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getModelPositionY()));
		// ModelPositionZ
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getModelPositionZ()));
		// FlashPositionX
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getFlashPositionX()));
		// FlashPositionY
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getFlashPositionY()));
		// FlashPositionZ
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getFlashPositionZ()));
		// YakkyouPositionX
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getYakkyouPositionX()));
		// YakkyouPositionY
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getYakkyouPositionY()));
		// YakkyouPositionZ
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getYakkyouPositionZ()));
		// WeaponP
		ShootingStance shootingStance = weapon.getWeaponP();
		int shootingStanceSpc = WeaponBinEnumConverter
				.getBinSpecifierFromShootingStance(shootingStance);
		ByteFunctions.addShortValueToBinLE(bin, (short) shootingStanceSpc);
		// BlazingMode
		boolean blazingMode = weapon.isBlazingMode();
		int blazingModeSpc = (blazingMode) ? 0 : 1;
		ByteFunctions.addShortValueToBinLE(bin, (short) blazingModeSpc);
		// ScopeMode
		ScopeMode scopeMode = weapon.getScopeMode();
		int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
		ByteFunctions.addShortValueToBinLE(bin, (short) scopeModeSpc);
		// Texture
		String textureFilepath = weapon.getTexture();
		WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
		int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
		ByteFunctions.addShortValueToBinLE(bin, (short) textureTypeSpc);
		// Model
		String modelFilepath = weapon.getModel();
		WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
		int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
		ByteFunctions.addShortValueToBinLE(bin, (short) modelTypeSpc);
		// Size
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getSize() * 10.0f));
		// YakkyouSpeedX
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getYakkyouSpeedX()));
		// YakkyouSpeedY
		ByteFunctions.addShortValueToBinLE(bin, (short) Math.round(weapon.getYakkyouSpeedY()));
		// SoundID
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getSoundID());
		// SoundVolume
		ByteFunctions.addShortValueToBinLE(bin, (short) weapon.getSoundVolume());
		// Silencer
		boolean silencer = weapon.isSilencer();
		int silencerSpc = (silencer) ? 1 : 0;
		ByteFunctions.addShortValueToBinLE(bin, (short) silencerSpc);
		// Name
		this.addNameToBin(bin, weapon.getName());

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
