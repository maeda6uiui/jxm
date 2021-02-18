package com.github.dabasan.jxm.properties.weapon.openxops;

import java.util.List;

import com.github.dabasan.jxm.properties.util.CPPArrayStringGenerator;
import com.github.dabasan.jxm.properties.weapon.WeaponData;

/**
 * Generates C++ code containing weapon data.
 * 
 * @author Daba
 *
 */
public class WeaponCodeGenerator {
	private WeaponVariableNameSettings settings;

	public WeaponCodeGenerator() {
		settings = new WeaponVariableNameSettings();
	}
	public WeaponCodeGenerator(WeaponVariableNameSettings settings) {
		this.settings = settings;
	}

	/**
	 * Generates C++ code containing weapon data.
	 * 
	 * @param weapons
	 *            List containing weapon data
	 * @return Code
	 */
	public String generate(List<WeaponData> weapons) {
		String arrayName = settings.getArrayName();

		var sb = new StringBuilder();
		for (int i = 0; i < weapons.size(); i++) {
			var weapon = weapons.get(i);

			// Name
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getName(),
					weapon.name));
			sb.append("\n");
			// Model
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModel(),
					weapon.model));
			sb.append("\n");
			// Texture
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getTexture(),
					weapon.texture));
			sb.append("\n");
			// Attacks
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getAttacks(),
					weapon.attacks));
			sb.append("\n");
			// Penetration
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getPenetration(),
					weapon.penetration));
			sb.append("\n");
			// Blazings
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBlazings(),
					weapon.blazings));
			sb.append("\n");
			// Speed
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSpeed(),
					weapon.speed));
			sb.append("\n");
			// NbsMax
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getNbsMax(),
					weapon.nbsMax));
			sb.append("\n");
			// Reloads
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getReloads(),
					weapon.reloads));
			sb.append("\n");
			// Reaction
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getReaction(),
					weapon.reaction));
			sb.append("\n");
			// ErrorRangeMin
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getErrorRangeMin(),
					weapon.errorRangeMin));
			sb.append("\n");
			// ErrorRangeMax
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getErrorRangeMax(),
					weapon.errorRangeMax));
			sb.append("\n");
			// ModelPositionX
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionX(),
					weapon.modelPositionX));
			sb.append("\n");
			// ModelPositionY
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionY(),
					weapon.modelPositionY));
			sb.append("\n");
			// ModelPositionZ
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionZ(),
					weapon.modelPositionZ));
			sb.append("\n");
			// FlashPositionX
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionX(),
					weapon.flashPositionX));
			sb.append("\n");
			// FlashPositionY
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionY(),
					weapon.flashPositionY));
			sb.append("\n");
			// FlashPositionZ
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionZ(),
					weapon.flashPositionZ));
			sb.append("\n");
			// YakkyouPositionX
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionX(),
					weapon.yakkyouPositionX));
			sb.append("\n");
			// YakkyouPositionY
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionY(),
					weapon.yakkyouPositionY));
			sb.append("\n");
			// YakkyouPositionZ
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionZ(),
					weapon.yakkyouPositionZ));
			sb.append("\n");
			// YakkyouSpeedX
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouSpeedX(),
					weapon.yakkyouSpeedX));
			sb.append("\n");
			// YakkyouSpeedY
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouSpeedY(),
					weapon.yakkyouSpeedY));
			sb.append("\n");
			// BlazingMode
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBlazingMode(),
					weapon.blazingMode));
			sb.append("\n");
			// ScopeMode
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getScopeMode(),
					weapon.scopeMode.ordinal()));
			sb.append("\n");
			// Size
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSize(),
					weapon.size));
			sb.append("\n");
			// SoundID
			int soundID = weapon.soundID;
			int openXOPSSoundID = WeaponSpecifierConverter
					.getOpenXOPSSoundIDFromXOPSSoundID(soundID);
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSoundID(),
					openXOPSSoundID));
			sb.append("\n");
			// SoundVolume
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSoundVolume(),
					weapon.soundVolume));
			sb.append("\n");
			// Silencer
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSilencer(),
					weapon.silencer));
			sb.append("\n");
			// WeaponP
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getWeaponP(),
					weapon.weaponP.ordinal()));
			sb.append("\n");
			// ChangeWeapon
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getChangeWeapon(),
					weapon.changeWeapon));
			sb.append("\n");
			// Burst
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBurst(),
					weapon.burst));
			sb.append("\n");
		}

		return sb.toString();
	}
}
