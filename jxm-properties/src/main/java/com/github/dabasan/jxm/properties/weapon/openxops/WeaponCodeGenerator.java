package com.github.dabasan.jxm.properties.weapon.openxops;

import com.github.dabasan.jxm.properties.util.CPPArrayStringGenerator;
import com.github.dabasan.jxm.properties.weapon.Weapon;

import java.util.List;

/**
 * Generates C++ code containing weapon data.
 *
 * @author maeda6uiui
 */
public class WeaponCodeGenerator {
    private final WeaponVariableNameSettings settings;
    private StringBuilder sb;

    /**
     * Creates a code generator.
     */
    public WeaponCodeGenerator() {
        settings = new WeaponVariableNameSettings();
    }

    /**
     * Creates a code generator.
     *
     * @param settings variable name settings
     */
    public WeaponCodeGenerator(WeaponVariableNameSettings settings) {
        this.settings = settings;
    }

    private void appendToBuffer(String arrayName, int index, String fieldName, Object value) {
        sb.append(CPPArrayStringGenerator.generate(arrayName, index, fieldName, value));
        sb.append("\n");
    }

    /**
     * Generates C++ code containing weapon data.
     *
     * @param weapons list containing weapon data
     * @return C++ code
     */
    public String generate(List<Weapon> weapons) {
        sb = new StringBuilder();
        for (int i = 0; i < weapons.size(); i++) {
            var weapon = weapons.get(i);

            this.appendToBuffer(settings.arrayName, i, settings.name, weapon.name);
            this.appendToBuffer(settings.arrayName, i, settings.model, weapon.model);
            this.appendToBuffer(settings.arrayName, i, settings.texture, weapon.texture);
            this.appendToBuffer(settings.arrayName, i, settings.attackPower, weapon.attackPower);
            this.appendToBuffer(settings.arrayName, i, settings.penetration, weapon.penetration);
            this.appendToBuffer(settings.arrayName, i, settings.fireInterval, weapon.fireInterval);
            this.appendToBuffer(settings.arrayName, i, settings.bulletSpeed, weapon.bulletSpeed);
            this.appendToBuffer(settings.arrayName, i, settings.magazineCapacity, weapon.magazineCapacity);
            this.appendToBuffer(settings.arrayName, i, settings.reloadTime, weapon.reloadTime);
            this.appendToBuffer(settings.arrayName, i, settings.recoil, weapon.recoil);
            this.appendToBuffer(settings.arrayName, i, settings.errorRangeMin, weapon.errorRangeMin);
            this.appendToBuffer(settings.arrayName, i, settings.errorRangeMax, weapon.errorRangeMax);
            this.appendToBuffer(settings.arrayName, i, settings.modelPositionX, weapon.modelPositionX);
            this.appendToBuffer(settings.arrayName, i, settings.modelPositionY, weapon.modelPositionY);
            this.appendToBuffer(settings.arrayName, i, settings.modelPositionZ, weapon.modelPositionZ);
            this.appendToBuffer(settings.arrayName, i, settings.muzzleFlashPositionX, weapon.muzzleFlashPositionX);
            this.appendToBuffer(settings.arrayName, i, settings.muzzleFlashPositionY, weapon.muzzleFlashPositionY);
            this.appendToBuffer(settings.arrayName, i, settings.muzzleFlashPositionZ, weapon.muzzleFlashPositionZ);
            this.appendToBuffer(settings.arrayName, i, settings.cartridgeEjectionPositionX, weapon.cartridgeEjectionPositionX);
            this.appendToBuffer(settings.arrayName, i, settings.cartridgeEjectionPositionY, weapon.cartridgeEjectionPositionY);
            this.appendToBuffer(settings.arrayName, i, settings.cartridgeEjectionPositionZ, weapon.cartridgeEjectionPositionZ);
            this.appendToBuffer(settings.arrayName, i, settings.cartridgeEjectionVelocityX, weapon.cartridgeEjectionVelocityX);
            this.appendToBuffer(settings.arrayName, i, settings.cartridgeEjectionVelocityY, weapon.cartridgeEjectionVelocityY);
            this.appendToBuffer(settings.arrayName, i, settings.rapidFire, weapon.rapidFire);
            this.appendToBuffer(settings.arrayName, i, settings.scopeMode, weapon.scopeMode.ordinal());
            this.appendToBuffer(settings.arrayName, i, settings.modelScale, weapon.modelScale);

            int soundId = weapon.fireSoundId;
            int openXOPSSoundId = WeaponSpecifierConverter.getOpenXOPSSoundIdFromXOPSSoundId(soundId);
            this.appendToBuffer(settings.arrayName, i, settings.fireSoundId, openXOPSSoundId);

            this.appendToBuffer(settings.arrayName, i, settings.fireSoundVolume, weapon.fireSoundVolume);
            this.appendToBuffer(settings.arrayName, i, settings.suppressor, weapon.suppressor);
            this.appendToBuffer(settings.arrayName, i, settings.shootingStance, weapon.shootingStance.ordinal());
            this.appendToBuffer(settings.arrayName, i, settings.switchableWeaponId, weapon.switchableWeaponId);
            this.appendToBuffer(settings.arrayName, i, settings.numProjectiles, weapon.numProjectiles);
        }

        return sb.toString();
    }
}
