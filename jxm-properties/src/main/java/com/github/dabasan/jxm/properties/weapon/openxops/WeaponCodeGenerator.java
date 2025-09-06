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

    /**
     * Generates C++ code containing weapon data.
     *
     * @param weapons list containing weapon data
     * @return C++ code
     */
    public String generate(List<Weapon> weapons) {
        var sb = new StringBuilder();
        for (int i = 0; i < weapons.size(); i++) {
            var weapon = weapons.get(i);

            //Name
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.name,
                    weapon.name));
            sb.append("\n");
            //Model
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.model,
                    weapon.model));
            sb.append("\n");
            //Texture
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.texture,
                    weapon.texture));
            sb.append("\n");
            //Attacks
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.attacks,
                    weapon.attackPower));
            sb.append("\n");
            //Penetration
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.penetration,
                    weapon.penetration));
            sb.append("\n");
            //Blazings
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.blazings,
                    weapon.fireInterval));
            sb.append("\n");
            //Speed
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.speed,
                    weapon.bulletSpeed));
            sb.append("\n");
            //NbsMax
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.nbsMax,
                    weapon.magazineCapacity));
            sb.append("\n");
            //Reloads
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.reloads,
                    weapon.reloadTime));
            sb.append("\n");
            //Reaction
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.reaction,
                    weapon.recoil));
            sb.append("\n");
            //ErrorRangeMin
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.errorRangeMin, weapon.errorRangeMin));
            sb.append("\n");
            //ErrorRangeMax
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.errorRangeMax, weapon.errorRangeMax));
            sb.append("\n");
            //ModelPositionX
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.modelPositionX, weapon.modelPositionX));
            sb.append("\n");
            //ModelPositionY
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.modelPositionY, weapon.modelPositionY));
            sb.append("\n");
            //ModelPositionZ
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.modelPositionZ, weapon.modelPositionZ));
            sb.append("\n");
            //FlashPositionX
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.flashPositionX, weapon.muzzleFlashPositionX));
            sb.append("\n");
            //FlashPositionY
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.flashPositionY, weapon.muzzleFlashPositionY));
            sb.append("\n");
            //FlashPositionZ
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.flashPositionZ, weapon.muzzleFlashPositionZ));
            sb.append("\n");
            //YakkyouPositionX
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.yakkyouPositionX, weapon.cartridgePositionX));
            sb.append("\n");
            //YakkyouPositionY
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.yakkyouPositionY, weapon.cartridgePositionY));
            sb.append("\n");
            //YakkyouPositionZ
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.yakkyouPositionZ, weapon.cartridgePositionZ));
            sb.append("\n");
            //YakkyouSpeedX
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.yakkyouSpeedX, weapon.cartridgeEjectionVelocityX));
            sb.append("\n");
            //YakkyouSpeedY
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i,
                    settings.yakkyouSpeedY, weapon.cartridgeEjectionVelocityY));
            sb.append("\n");
            //BlazingMode
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.blazingMode,
                    weapon.rapidFire));
            sb.append("\n");
            //ScopeMode
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.scopeMode,
                    weapon.scopeMode.ordinal()));
            sb.append("\n");
            //Size
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.size,
                    weapon.modelScale));
            sb.append("\n");
            //SoundID
            int soundID = weapon.fireSoundId;
            int openXOPSSoundID = WeaponSpecifierConverter
                    .getOpenXOPSSoundIDFromXOPSSoundID(soundID);
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.soundID,
                    openXOPSSoundID));
            sb.append("\n");
            //SoundVolume
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.soundVolume,
                    weapon.fireSoundVolume));
            sb.append("\n");
            //Silencer
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.silencer,
                    weapon.suppressor));
            sb.append("\n");
            //WeaponP
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.weaponP,
                    weapon.shootingStance.ordinal()));
            sb.append("\n");
            //ChangeWeapon
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.changeWeapon,
                    weapon.switchableWeaponId));
            sb.append("\n");
            //Burst
            sb.append(CPPArrayStringGenerator.generate(settings.arrayName, i, settings.burst,
                    weapon.numProjectiles));
            sb.append("\n");
        }

        return sb.toString();
    }
}
