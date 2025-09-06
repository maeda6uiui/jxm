package com.github.dabasan.jxm.properties.weapon.openxops;

/**
 * Settings for variable names used to generate and parse C++ code
 *
 * @author maeda6uiui
 */
public class WeaponVariableNameSettings {
    public String arrayName;

    public String name;
    public String model;
    public String texture;
    public String attackPower;
    public String penetration;
    public String fireInterval;
    public String bulletSpeed;
    public String magazineCapacity;
    public String reloadTime;
    public String recoil;
    public String errorRangeMin;
    public String errorRangeMax;
    public String modelPositionX;
    public String modelPositionY;
    public String modelPositionZ;
    public String muzzleFlashPositionX;
    public String muzzleFlashPositionY;
    public String muzzleFlashPositionZ;
    public String cartridgePositionX;
    public String cartridgePositionY;
    public String cartridgePositionZ;
    public String cartridgeEjectionVelocityX;
    public String cartridgeEjectionVelocityY;
    public String rapidFire;
    public String scopeMode;
    public String modelScale;
    public String fireSoundId;
    public String fireSoundVolume;
    public String suppressor;
    public String shootingStance;
    public String switchableWeaponId;
    public String numProjectiles;

    /**
     * Creates a WeaponVariableNameSettings instance.
     */
    public WeaponVariableNameSettings() {
        arrayName = "Weapon";

        name = "name";
        model = "model";
        texture = "texture";
        attackPower = "attacks";
        penetration = "penetration";
        fireInterval = "blazings";
        bulletSpeed = "speed";
        magazineCapacity = "nbsmax";
        reloadTime = "reloads";
        recoil = "reaction";
        errorRangeMin = "ErrorRangeMIN";
        errorRangeMax = "ErrorRangeMAX";
        modelPositionX = "mx";
        modelPositionY = "my";
        modelPositionZ = "mz";
        muzzleFlashPositionX = "flashx";
        muzzleFlashPositionY = "flashy";
        muzzleFlashPositionZ = "flashz";
        cartridgePositionX = "yakkyou_px";
        cartridgePositionY = "yakkyou_py";
        cartridgePositionZ = "yakkyou_pz";
        cartridgeEjectionVelocityX = "yakkyou_sx";
        cartridgeEjectionVelocityY = "yakkyou_sy";
        rapidFire = "blazingmode";
        scopeMode = "scopemode";
        modelScale = "size";
        fireSoundId = "soundid";
        fireSoundVolume = "soundvolume";
        suppressor = "silencer";
        shootingStance = "WeaponP";
        switchableWeaponId = "ChangeWeapon";
        numProjectiles = "burst";
    }
}
