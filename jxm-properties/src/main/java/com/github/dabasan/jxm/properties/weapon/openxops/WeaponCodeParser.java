package com.github.dabasan.jxm.properties.weapon.openxops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.jxm.properties.util.CPPArrayStringParser;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.WeaponData;

/**
 * Parses C++ code containing weapon data.
 * 
 * @author Daba
 *
 */
public class WeaponCodeParser {
	private Logger logger = LoggerFactory.getLogger(WeaponCodeParser.class);

	private WeaponVariableNameSettings settings;

	public WeaponCodeParser() {
		settings = new WeaponVariableNameSettings();
	}
	public WeaponCodeParser(WeaponVariableNameSettings settings) {
		this.settings = settings;
	}

	/**
	 * Parses C++ code containing weapon data.
	 * 
	 * @param code
	 *            Code
	 * @return Map containing weapon data
	 */
	public Map<Integer, WeaponData> parse(List<String> code) {
		var ret = new HashMap<Integer, WeaponData>();

		for (int i = 0; i < code.size(); i++) {
			var line = code.get(i);

			String[] parsed = CPPArrayStringParser.parse(line);
			if (parsed == null) {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}

			int arrayIndex;
			try {
				arrayIndex = Integer.parseInt(parsed[1]);
			} catch (NumberFormatException e) {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}

			if (ret.containsKey(arrayIndex) == false) {
				var weapon = new WeaponData();
				ret.put(arrayIndex, weapon);
			}
			var weapon = ret.get(arrayIndex);

			// Name
			if (parsed[2].equals(settings.getName())) {
				weapon.name = parsed[3];
			}
			// Model
			else if (parsed[2].equals(settings.getModel())) {
				weapon.model = parsed[3];
			}
			// Texture
			else if (parsed[2].equals(settings.getTexture())) {
				weapon.texture = parsed[3];
			}
			// Attacks
			else if (parsed[2].equals(settings.getAttacks())) {
				int attacks;
				try {
					attacks = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.attacks = attacks;
			}
			// Penetration
			else if (parsed[2].equals(settings.getPenetration())) {
				int penetration;
				try {
					penetration = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.penetration = penetration;
			}
			// Blazings
			else if (parsed[2].equals(settings.getBlazings())) {
				int blazings;
				try {
					blazings = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.blazings = blazings;
			}
			// Speed
			else if (parsed[2].equals(settings.getSpeed())) {
				int speed;
				try {
					speed = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.speed = speed;
			}
			// NbsMax
			else if (parsed[2].equals(settings.getNbsMax())) {
				int nbsMax;
				try {
					nbsMax = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.nbsMax = nbsMax;
			}
			// Reloads
			else if (parsed[2].equals(settings.getReloads())) {
				int reloads;
				try {
					reloads = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.reloads = reloads;
			}
			// Reaction
			else if (parsed[2].equals(settings.getReaction())) {
				int reaction;
				try {
					reaction = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.reaction = reaction;
			}
			// ErrorRangeMin
			else if (parsed[2].equals(settings.getErrorRangeMin())) {
				int errorRangeMin;
				try {
					errorRangeMin = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.errorRangeMin = errorRangeMin;
			}
			// ErrorRangeMax
			else if (parsed[2].equals(settings.getErrorRangeMax())) {
				int errorRangeMax;
				try {
					errorRangeMax = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.errorRangeMax = errorRangeMax;
			}
			// ModelPositionX
			else if (parsed[2].equals(settings.getModelPositionX())) {
				String valString = this.removeTailingF(parsed[3]);

				float modelPositionX;
				try {
					modelPositionX = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.modelPositionX = modelPositionX;
			}
			// ModelPositionY
			else if (parsed[2].equals(settings.getModelPositionY())) {
				String valString = this.removeTailingF(parsed[3]);

				float modelPositionY;
				try {
					modelPositionY = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.modelPositionY = modelPositionY;
			}
			// ModelPositionZ
			else if (parsed[2].equals(settings.getModelPositionZ())) {
				String valString = this.removeTailingF(parsed[3]);

				float modelPositionZ;
				try {
					modelPositionZ = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.modelPositionZ = modelPositionZ;
			}
			// FlashPositionX
			else if (parsed[2].equals(settings.getFlashPositionX())) {
				String valString = this.removeTailingF(parsed[3]);

				float flashPositionX;
				try {
					flashPositionX = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.flashPositionX = flashPositionX;
			}
			// FlashPositionY
			else if (parsed[2].equals(settings.getFlashPositionY())) {
				String valString = this.removeTailingF(parsed[3]);

				float flashPositionY;
				try {
					flashPositionY = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.flashPositionY = flashPositionY;
			}
			// FlashPositionZ
			else if (parsed[2].equals(settings.getFlashPositionZ())) {
				String valString = this.removeTailingF(parsed[3]);

				float flashPositionZ;
				try {
					flashPositionZ = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.flashPositionZ = flashPositionZ;
			}
			// YakkyouPositionX
			else if (parsed[2].equals(settings.getYakkyouPositionX())) {
				String valString = this.removeTailingF(parsed[3]);

				float yakkyouPositionX;
				try {
					yakkyouPositionX = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.yakkyouPositionX = yakkyouPositionX;
			}
			// YakkyouPositionY
			else if (parsed[2].equals(settings.getYakkyouPositionY())) {
				String valString = this.removeTailingF(parsed[3]);

				float yakkyouPositionY;
				try {
					yakkyouPositionY = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.yakkyouPositionY = yakkyouPositionY;
			}
			// YakkyouPositionZ
			else if (parsed[2].equals(settings.getYakkyouPositionZ())) {
				String valString = this.removeTailingF(parsed[3]);

				float yakkyouPositionZ;
				try {
					yakkyouPositionZ = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.yakkyouPositionZ = yakkyouPositionZ;
			}
			// YakkyouSpeedX
			else if (parsed[2].equals(settings.getYakkyouSpeedX())) {
				String valString = this.removeTailingF(parsed[3]);

				float yakkyouSpeedX;
				try {
					yakkyouSpeedX = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.yakkyouSpeedX = yakkyouSpeedX;
			}
			// YakkyouSpeedY
			else if (parsed[2].equals(settings.getYakkyouSpeedY())) {
				String valString = this.removeTailingF(parsed[3]);

				float yakkyouSpeedY;
				try {
					yakkyouSpeedY = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.yakkyouSpeedY = yakkyouSpeedY;
			}
			// BlazingMode
			else if (parsed[2].equals(settings.getBlazingMode())) {
				boolean blazingMode;
				if (parsed[3].equals("false")) {
					blazingMode = false;
				} else if (parsed[3].equals("true")) {
					blazingMode = true;
				} else {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.blazingMode = blazingMode;
			}
			// ScopeMode
			else if (parsed[2].equals(settings.getScopeMode())) {
				int scopeModeSpc;
				try {
					scopeModeSpc = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.scopeMode = ScopeMode.values()[scopeModeSpc];
			}
			// Size
			else if (parsed[2].equals(settings.getSize())) {
				String valString = this.removeTailingF(parsed[3]);

				float size;
				try {
					size = Float.parseFloat(valString);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.size = size;
			}
			// SoundID
			else if (parsed[2].equals(settings.getSoundID())) {
				int openXOPSSoundID;
				try {
					openXOPSSoundID = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.soundID = WeaponSpecifierConverter
						.getXOPSSoundIDFromOpenXOPSSoundID(openXOPSSoundID);
			}
			// SoundVolume
			else if (parsed[2].equals(settings.getSoundVolume())) {
				int soundVolume;
				try {
					soundVolume = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.soundVolume = soundVolume;
			}
			// Silencer
			else if (parsed[2].equals(settings.getSilencer())) {
				boolean silencer;
				if (parsed[3].equals("false")) {
					silencer = false;
				} else if (parsed[3].equals("true")) {
					silencer = true;
				} else {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.silencer = silencer;
			}
			// WeaponP
			else if (parsed[2].equals(settings.getWeaponP())) {
				int weaponPSpc;
				try {
					weaponPSpc = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.weaponP = ShootingStance.values()[weaponPSpc];
			}
			// ChangeWeapon
			else if (parsed[2].equals(settings.getChangeWeapon())) {
				int changeWeapon;
				try {
					changeWeapon = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.changeWeapon = changeWeapon;
			}
			// Burst
			else if (parsed[2].equals(settings.getBurst())) {
				int burst;
				try {
					burst = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.burst = burst;
			} else {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}
		}

		return ret;
	}
	private String removeTailingF(String parsedString) {
		String ret = parsedString;

		char lastChar = ret.charAt(ret.length() - 1);
		if (lastChar == 'f' || lastChar == 'F') {
			ret = ret.substring(0, ret.length() - 1);
		}

		return ret;
	}
}
