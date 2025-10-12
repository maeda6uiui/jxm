package com.github.dabasan.jxm.properties.weapon.openxops;

import com.github.dabasan.jxm.properties.util.CPPArrayStringParser;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses C++ code containing weapon data.
 *
 * @author maeda6uiui
 */
public class WeaponCodeParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WeaponVariableNameSettings settings;

    /**
     * Creates a code parser.
     */
    public WeaponCodeParser() {
        settings = new WeaponVariableNameSettings();
    }

    /**
     * Creates a code parser.
     *
     * @param settings variable name settings
     */
    public WeaponCodeParser(WeaponVariableNameSettings settings) {
        this.settings = settings;
    }

    /**
     * Parses C++ code containing weapon data.
     *
     * @param code C++ code
     * @return map containing weapon data
     */
    public Map<Integer, XOPSWeapon> parse(List<String> code) {
        var ret = new HashMap<Integer, XOPSWeapon>();

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

            if (!ret.containsKey(arrayIndex)) {
                var weapon = new XOPSWeapon();
                ret.put(arrayIndex, weapon);
            }
            var weapon = ret.get(arrayIndex);

            if (parsed[2].equals(settings.name)) {
                weapon.name = parsed[3];
            } else if (parsed[2].equals(settings.model)) {
                weapon.model = parsed[3];
            } else if (parsed[2].equals(settings.texture)) {
                weapon.texture = parsed[3];
            } else if (parsed[2].equals(settings.attackPower)) {
                int attacks;
                try {
                    attacks = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.attackPower = attacks;
            } else if (parsed[2].equals(settings.penetration)) {
                int penetration;
                try {
                    penetration = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.penetration = penetration;
            } else if (parsed[2].equals(settings.fireInterval)) {
                int blazings;
                try {
                    blazings = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.fireInterval = blazings;
            } else if (parsed[2].equals(settings.bulletSpeed)) {
                int speed;
                try {
                    speed = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.bulletSpeed = speed;
            } else if (parsed[2].equals(settings.magazineCapacity)) {
                int nbsMax;
                try {
                    nbsMax = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.magazineCapacity = nbsMax;
            } else if (parsed[2].equals(settings.reloadTime)) {
                int reloads;
                try {
                    reloads = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.reloadTime = reloads;
            } else if (parsed[2].equals(settings.recoil)) {
                int reaction;
                try {
                    reaction = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.recoil = reaction;
            } else if (parsed[2].equals(settings.errorRangeMin)) {
                int errorRangeMin;
                try {
                    errorRangeMin = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.errorRangeMin = errorRangeMin;
            } else if (parsed[2].equals(settings.errorRangeMax)) {
                int errorRangeMax;
                try {
                    errorRangeMax = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.errorRangeMax = errorRangeMax;
            } else if (parsed[2].equals(settings.modelPositionX)) {
                String valString = this.removeTrailingF(parsed[3]);

                float modelPositionX;
                try {
                    modelPositionX = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.modelPositionX = modelPositionX;
            } else if (parsed[2].equals(settings.modelPositionY)) {
                String valString = this.removeTrailingF(parsed[3]);

                float modelPositionY;
                try {
                    modelPositionY = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.modelPositionY = modelPositionY;
            } else if (parsed[2].equals(settings.modelPositionZ)) {
                String valString = this.removeTrailingF(parsed[3]);

                float modelPositionZ;
                try {
                    modelPositionZ = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.modelPositionZ = modelPositionZ;
            } else if (parsed[2].equals(settings.muzzleFlashPositionX)) {
                String valString = this.removeTrailingF(parsed[3]);

                float muzzleFlashPositionX;
                try {
                    muzzleFlashPositionX = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.muzzleFlashPositionX = muzzleFlashPositionX;
            } else if (parsed[2].equals(settings.muzzleFlashPositionY)) {
                String valString = this.removeTrailingF(parsed[3]);

                float muzzleFlashPositionY;
                try {
                    muzzleFlashPositionY = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.muzzleFlashPositionY = muzzleFlashPositionY;
            } else if (parsed[2].equals(settings.muzzleFlashPositionZ)) {
                String valString = this.removeTrailingF(parsed[3]);

                float muzzleFlashPositionZ;
                try {
                    muzzleFlashPositionZ = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.muzzleFlashPositionZ = muzzleFlashPositionZ;
            } else if (parsed[2].equals(settings.cartridgeEjectionPositionX)) {
                String valString = this.removeTrailingF(parsed[3]);

                float cartridgeEjectionPositionX;
                try {
                    cartridgeEjectionPositionX = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.cartridgeEjectionPositionX = cartridgeEjectionPositionX;
            } else if (parsed[2].equals(settings.cartridgeEjectionPositionY)) {
                String valString = this.removeTrailingF(parsed[3]);

                float cartridgeEjectionPositionY;
                try {
                    cartridgeEjectionPositionY = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.cartridgeEjectionPositionY = cartridgeEjectionPositionY;
            } else if (parsed[2].equals(settings.cartridgeEjectionPositionZ)) {
                String valString = this.removeTrailingF(parsed[3]);

                float cartridgeEjectionPositionZ;
                try {
                    cartridgeEjectionPositionZ = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.cartridgeEjectionPositionZ = cartridgeEjectionPositionZ;
            } else if (parsed[2].equals(settings.cartridgeEjectionVelocityX)) {
                String valString = this.removeTrailingF(parsed[3]);

                float cartridgeEjectionVelocityX;
                try {
                    cartridgeEjectionVelocityX = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.cartridgeEjectionVelocityX = cartridgeEjectionVelocityX;
            } else if (parsed[2].equals(settings.cartridgeEjectionVelocityY)) {
                String valString = this.removeTrailingF(parsed[3]);

                float cartridgeEjectionVelocityY;
                try {
                    cartridgeEjectionVelocityY = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.cartridgeEjectionVelocityY = cartridgeEjectionVelocityY;
            } else if (parsed[2].equals(settings.rapidFire)) {
                boolean blazingMode;
                if (parsed[3].equals("false")) {
                    blazingMode = false;
                } else if (parsed[3].equals("true")) {
                    blazingMode = true;
                } else {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.rapidFire = blazingMode;
            } else if (parsed[2].equals(settings.scopeMode)) {
                int scopeModeSpc;
                try {
                    scopeModeSpc = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.scopeMode = ScopeMode.values()[scopeModeSpc];
            } else if (parsed[2].equals(settings.modelScale)) {
                String valString = this.removeTrailingF(parsed[3]);

                float size;
                try {
                    size = Float.parseFloat(valString);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.modelScale = size;
            } else if (parsed[2].equals(settings.fireSoundId)) {
                int openXOPSSoundId;
                try {
                    openXOPSSoundId = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.fireSoundId = WeaponSpecifierConverter
                        .getXOPSSoundIdFromOpenXOPSSoundId(openXOPSSoundId);
            } else if (parsed[2].equals(settings.fireSoundVolume)) {
                int soundVolume;
                try {
                    soundVolume = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.fireSoundVolume = soundVolume;
            } else if (parsed[2].equals(settings.suppressor)) {
                boolean silencer;
                if (parsed[3].equals("false")) {
                    silencer = false;
                } else if (parsed[3].equals("true")) {
                    silencer = true;
                } else {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.suppressor = silencer;
            } else if (parsed[2].equals(settings.shootingStance)) {
                int weaponPSpc;
                try {
                    weaponPSpc = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.shootingStance = ShootingStance.values()[weaponPSpc];
            } else if (parsed[2].equals(settings.switchableWeaponId)) {
                int changeWeapon;
                try {
                    changeWeapon = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.switchableWeaponId = changeWeapon;
            } else if (parsed[2].equals(settings.numProjectiles)) {
                int burst;
                try {
                    burst = Integer.parseInt(parsed[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Parse error ({}): {}", i, line);
                    continue;
                }

                weapon.numProjectiles = burst;
            } else {
                logger.warn("Parse error ({}): {}", i, line);
                continue;
            }
        }

        return ret;
    }

    private String removeTrailingF(String parsedString) {
        String ret = parsedString;

        char lastChar = ret.charAt(ret.length() - 1);
        if (lastChar == 'f' || lastChar == 'F') {
            ret = ret.substring(0, ret.length() - 1);
        }

        return ret;
    }
}
