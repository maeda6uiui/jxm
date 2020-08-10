package com.github.dabasan.jxm.properties.weapon.openxops;

import java.util.HashMap;
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
	public Map<Integer, WeaponData> parse(String code) {
		var ret = new HashMap<Integer, WeaponData>();

		// Replace '\r' with a blank.
		String rnReplaced = code.replace('\r', ' ');
		// Split the string per line.
		String[] lines = rnReplaced.split("\n");

		for (int i = 0; i < lines.length; i++) {
			var line = lines[i];

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
				weapon.setName(parsed[3]);
			}
			// Model
			else if (parsed[2].equals(settings.getModel())) {
				weapon.setModel(parsed[3]);
			}
			// Texture
			else if (parsed[2].equals(settings.getTexture())) {
				weapon.setTexture(parsed[3]);
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

				weapon.setAttacks(attacks);
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

				weapon.setPenetration(penetration);
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

				weapon.setBlazings(blazings);
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

				weapon.setSpeed(speed);
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

				weapon.setNbsMax(nbsMax);
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

				weapon.setReloads(reloads);
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

				weapon.setReaction(reaction);
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

				weapon.setErrorRangeMin(errorRangeMin);
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

				weapon.setErrorRangeMax(errorRangeMax);
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

				weapon.getModelPosition().setX(modelPositionX);
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

				weapon.getModelPosition().setY(modelPositionY);
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

				weapon.getModelPosition().setZ(modelPositionZ);
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

				weapon.getFlashPosition().setX(flashPositionX);
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

				weapon.getFlashPosition().setY(flashPositionY);
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

				weapon.getFlashPosition().setZ(flashPositionZ);
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

				weapon.getYakkyouPosition().setX(yakkyouPositionX);
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

				weapon.getYakkyouPosition().setY(yakkyouPositionY);
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

				weapon.getYakkyouPosition().setZ(yakkyouPositionZ);
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

				weapon.getYakkyouSpeed().setX(yakkyouSpeedX);
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

				weapon.getYakkyouSpeed().setY(yakkyouSpeedY);
			}
			// Blazing mode
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

				weapon.setBlazingMode(blazingMode);
			}
			// Scope mode
			else if (parsed[2].equals(settings.getScopeMode())) {
				int scopeModeSpc;
				try {
					scopeModeSpc = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.setScopeMode(ScopeMode.values()[scopeModeSpc]);
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

				weapon.setSize(size);
			}
			// Sound ID
			else if (parsed[2].equals(settings.getSoundID())) {
				int openXOPSSoundID;
				try {
					openXOPSSoundID = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				int xopsSoundID = WeaponSpecifierConverter
						.getXOPSSoundIDFromOpenXOPSSoundID(openXOPSSoundID);
				weapon.setSoundID(xopsSoundID);
			}
			// Sound volume
			else if (parsed[2].equals(settings.getSoundVolume())) {
				int soundVolume;
				try {
					soundVolume = Integer.parseInt(parsed[3]);
				} catch (NumberFormatException e) {
					logger.warn("Parse error ({}): {}", i, line);
					continue;
				}

				weapon.setSoundVolume(soundVolume);
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

				weapon.setSilencer(silencer);
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

				weapon.setWeaponP(ShootingStance.values()[weaponPSpc]);
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

				weapon.setChangeWeapon(changeWeapon);
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

				weapon.setBurst(burst);
			} else {
				logger.warn("Parse error ({}): {}", i, line);
				continue;
			}
		}

		return ret;
	}
	private String removeTailingF(String parsedString) {
		String ret = parsedString;

		char lastChar = ret.charAt(ret.charAt(ret.length() - 1));
		if (lastChar == 'f' || lastChar == 'F') {
			ret = ret.substring(0, ret.length() - 1);
		}

		return ret;
	}
}
