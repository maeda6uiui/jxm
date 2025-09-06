package com.github.maeda6uiui.jxm.properties.weapon.openxops;

/**
 * Settings for variable names used to generate and parse C++ code
 *
 * @author maeda6uiui
 */
public class WeaponVariableNameSettings {
    public String arrayName;

    //Weapon data
    public String name;
    public String model;
    public String texture;
    public String attacks;
    public String penetration;
    public String blazings;
    public String speed;
    public String nbsMax;
    public String reloads;
    public String reaction;
    public String errorRangeMin;
    public String errorRangeMax;
    public String modelPositionX;
    public String modelPositionY;
    public String modelPositionZ;
    public String flashPositionX;
    public String flashPositionY;
    public String flashPositionZ;
    public String yakkyouPositionX;
    public String yakkyouPositionY;
    public String yakkyouPositionZ;
    public String yakkyouSpeedX;
    public String yakkyouSpeedY;
    public String blazingMode;
    public String scopeMode;
    public String size;
    public String soundID;
    public String soundVolume;
    public String silencer;
    public String weaponP;
    public String changeWeapon;
    public String burst;

    /**
     * Creates a WeaponVariableNameSettings instance.
     */
    public WeaponVariableNameSettings() {
        arrayName = "Weapon";

        name = "name";
        model = "model";
        texture = "texture";
        attacks = "attacks";
        penetration = "penetration";
        blazings = "blazings";
        speed = "speed";
        nbsMax = "nbsmax";
        reloads = "reloads";
        reaction = "reaction";
        errorRangeMin = "ErrorRangeMIN";
        errorRangeMax = "ErrorRangeMAX";
        modelPositionX = "mx";
        modelPositionY = "my";
        modelPositionZ = "mz";
        flashPositionX = "flashx";
        flashPositionY = "flashy";
        flashPositionZ = "flashz";
        yakkyouPositionX = "yakkyou_px";
        yakkyouPositionY = "yakkyou_py";
        yakkyouPositionZ = "yakkyou_pz";
        yakkyouSpeedX = "yakkyou_sx";
        yakkyouSpeedY = "yakkyou_sy";
        blazingMode = "blazingmode";
        scopeMode = "scopemode";
        size = "size";
        soundID = "soundid";
        soundVolume = "soundvolume";
        silencer = "silencer";
        weaponP = "WeaponP";
        changeWeapon = "ChangeWeapon";
        burst = "burst";
    }
}
