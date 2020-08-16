package com.github.dabasan.jxm.properties.weapon.openxops;

import java.util.List;

import com.github.dabasan.ejml_3dtools.Vector;
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
					weapon.getName()));
			sb.append("\n");
			// Model
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModel(),
					weapon.getModel()));
			sb.append("\n");
			// Texture
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getTexture(),
					weapon.getTexture()));
			sb.append("\n");
			// Attacks
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getAttacks(),
					weapon.getAttacks()));
			sb.append("\n");
			// Penetration
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getPenetration(),
					weapon.getPenetration()));
			sb.append("\n");
			// Blazings
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBlazings(),
					weapon.getBlazings()));
			sb.append("\n");
			// Speed
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSpeed(),
					weapon.getSpeed()));
			sb.append("\n");
			// NbsMax
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getNbsMax(),
					weapon.getNbsMax()));
			sb.append("\n");
			// Reloads
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getReloads(),
					weapon.getReloads()));
			sb.append("\n");
			// Reaction
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getReaction(),
					weapon.getReaction()));
			sb.append("\n");
			// ErrorRangeMin
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getErrorRangeMin(),
					weapon.getErrorRangeMin()));
			sb.append("\n");
			// ErrorRangeMax
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getErrorRangeMax(),
					weapon.getErrorRangeMax()));
			sb.append("\n");

			// ModelPosition
			Vector modelPosition = weapon.getModelPosition();
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionX(),
					modelPosition.getXFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionY(),
					modelPosition.getYFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getModelPositionZ(),
					modelPosition.getZFloat()));
			sb.append("\n");

			// FlashPosition
			Vector flashPosition = weapon.getFlashPosition();
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionX(),
					flashPosition.getXFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionY(),
					flashPosition.getYFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getFlashPositionZ(),
					flashPosition.getZFloat()));
			sb.append("\n");

			// YakkyouPosition
			Vector yakkyouPosition = weapon.getYakkyouPosition();
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionX(),
					yakkyouPosition.getXFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionY(),
					yakkyouPosition.getYFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouPositionZ(),
					yakkyouPosition.getZFloat()));
			sb.append("\n");

			// YakkyouSpeed
			Vector yakkyouSpeed = weapon.getYakkyouSpeed();
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouSpeedX(),
					yakkyouSpeed.getXFloat()));
			sb.append("\n");
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getYakkyouSpeedY(),
					yakkyouSpeed.getYFloat()));
			sb.append("\n");

			// BlazingMode
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBlazingMode(),
					weapon.isBlazingMode()));
			sb.append("\n");
			// ScopeMode
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getScopeMode(),
					weapon.getScopeMode().ordinal()));
			sb.append("\n");
			// Size
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSize(),
					(float) weapon.getSize()));
			sb.append("\n");

			// SoundID
			int soundID = weapon.getSoundID();
			int openXOPSSoundID = WeaponSpecifierConverter
					.getOpenXOPSSoundIDFromXOPSSoundID(soundID);
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSoundID(),
					openXOPSSoundID));
			sb.append("\n");

			// SoundVolume
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSoundVolume(),
					weapon.getSoundVolume()));
			sb.append("\n");
			// Silencer
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getSilencer(),
					weapon.isSilencer()));
			sb.append("\n");
			// WeaponP
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getWeaponP(),
					weapon.getWeaponP().ordinal()));
			sb.append("\n");
			// ChangeWeapon
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getChangeWeapon(),
					weapon.getChangeWeapon()));
			sb.append("\n");
			// Burst
			sb.append(CPPArrayStringGenerator.generate(arrayName, i, settings.getBurst(),
					weapon.getBurst()));
			sb.append("\n");
		}

		return sb.toString();
	}
}
