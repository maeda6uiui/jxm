package com.github.maeda6uiui.jxm.properties.weapon;

import java.util.Objects;

/**
 * Weapon
 *
 * @author maeda6uiui
 */
public class Weapon {
    public String name;
    public String model;
    public String texture;
    public int attacks;
    public int penetration;
    public int blazings;
    public int speed;
    public int nbsMax;
    public int reloads;
    public int reaction;
    public int errorRangeMin;
    public int errorRangeMax;
    public float modelPositionX;
    public float modelPositionY;
    public float modelPositionZ;
    public float flashPositionX;
    public float flashPositionY;
    public float flashPositionZ;
    public float yakkyouPositionX;
    public float yakkyouPositionY;
    public float yakkyouPositionZ;
    public float yakkyouSpeedX;
    public float yakkyouSpeedY;
    public boolean blazingMode;
    public ScopeMode scopeMode;
    public float size;
    public int soundID;
    public int soundVolume;
    public boolean silencer;
    public ShootingStance weaponP;
    public int changeWeapon;
    public int burst;

    /**
     * Creates a weapon.
     */
    public Weapon() {
        name = "";
        model = "";
        texture = "";
        attacks = 0;
        penetration = 0;
        blazings = 0;
        speed = 0;
        nbsMax = 0;
        reloads = 0;
        reaction = 0;
        errorRangeMin = 0;
        errorRangeMax = 0;
        modelPositionX = 0.0f;
        modelPositionY = 0.0f;
        modelPositionZ = 0.0f;
        flashPositionX = 0.0f;
        flashPositionY = 0.0f;
        flashPositionZ = 0.0f;
        yakkyouPositionX = 0.0f;
        yakkyouPositionY = 0.0f;
        yakkyouPositionZ = 0.0f;
        yakkyouSpeedX = 0.0f;
        yakkyouSpeedY = 0.0f;
        blazingMode = false;
        scopeMode = ScopeMode.NONE;
        size = 1.0f;
        soundID = 0;
        soundVolume = 0;
        silencer = false;
        weaponP = ShootingStance.RIFLE;
        changeWeapon = -1;
        burst = 1;
    }

    /**
     * Copies a weapon.
     *
     * @param weapon Weapon
     */
    public Weapon(Weapon weapon) {
        this.name = weapon.name;
        this.model = weapon.model;
        this.texture = weapon.texture;
        this.attacks = weapon.attacks;
        this.penetration = weapon.penetration;
        this.blazings = weapon.blazings;
        this.speed = weapon.speed;
        this.nbsMax = weapon.nbsMax;
        this.reloads = weapon.reloads;
        this.reaction = weapon.reaction;
        this.errorRangeMin = weapon.errorRangeMin;
        this.errorRangeMax = weapon.errorRangeMax;
        this.modelPositionX = weapon.modelPositionX;
        this.modelPositionY = weapon.modelPositionY;
        this.modelPositionZ = weapon.modelPositionZ;
        this.flashPositionX = weapon.flashPositionX;
        this.flashPositionY = weapon.flashPositionY;
        this.flashPositionZ = weapon.flashPositionZ;
        this.yakkyouPositionX = weapon.yakkyouPositionX;
        this.yakkyouPositionY = weapon.yakkyouPositionY;
        this.yakkyouPositionZ = weapon.yakkyouPositionZ;
        this.yakkyouSpeedX = weapon.yakkyouSpeedX;
        this.yakkyouSpeedY = weapon.yakkyouSpeedY;
        this.blazingMode = weapon.blazingMode;
        this.scopeMode = weapon.scopeMode;
        this.size = weapon.size;
        this.soundID = weapon.soundID;
        this.soundVolume = weapon.soundVolume;
        this.silencer = weapon.silencer;
        this.weaponP = weapon.weaponP;
        this.changeWeapon = weapon.changeWeapon;
        this.burst = weapon.burst;
    }

    @Override
    public String toString() {
        return "WeaponData [name=" + name + ", model=" + model + ", texture=" + texture
                + ", attacks=" + attacks + ", penetration=" + penetration + ", blazings=" + blazings
                + ", speed=" + speed + ", nbsMax=" + nbsMax + ", reloads=" + reloads + ", reaction="
                + reaction + ", errorRangeMin=" + errorRangeMin + ", errorRangeMax=" + errorRangeMax
                + ", modelPositionX=" + modelPositionX + ", modelPositionY=" + modelPositionY
                + ", modelPositionZ=" + modelPositionZ + ", flashPositionX=" + flashPositionX
                + ", flashPositionY=" + flashPositionY + ", flashPositionZ=" + flashPositionZ
                + ", yakkyouPositionX=" + yakkyouPositionX + ", yakkyouPositionY="
                + yakkyouPositionY + ", yakkyouPositionZ=" + yakkyouPositionZ + ", yakkyouSpeedX="
                + yakkyouSpeedX + ", yakkyouSpeedY=" + yakkyouSpeedY + ", blazingMode="
                + blazingMode + ", scopeMode=" + scopeMode + ", size=" + size + ", soundID="
                + soundID + ", soundVolume=" + soundVolume + ", silencer=" + silencer + ", weaponP="
                + weaponP + ", changeWeapon=" + changeWeapon + ", burst=" + burst + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return attacks == weapon.attacks
                && penetration == weapon.penetration
                && blazings == weapon.blazings
                && speed == weapon.speed
                && nbsMax == weapon.nbsMax
                && reloads == weapon.reloads
                && reaction == weapon.reaction
                && errorRangeMin == weapon.errorRangeMin
                && errorRangeMax == weapon.errorRangeMax
                && Float.compare(weapon.modelPositionX, modelPositionX) == 0
                && Float.compare(weapon.modelPositionY, modelPositionY) == 0
                && Float.compare(weapon.modelPositionZ, modelPositionZ) == 0
                && Float.compare(weapon.flashPositionX, flashPositionX) == 0
                && Float.compare(weapon.flashPositionY, flashPositionY) == 0
                && Float.compare(weapon.flashPositionZ, flashPositionZ) == 0
                && Float.compare(weapon.yakkyouPositionX, yakkyouPositionX) == 0
                && Float.compare(weapon.yakkyouPositionY, yakkyouPositionY) == 0
                && Float.compare(weapon.yakkyouPositionZ, yakkyouPositionZ) == 0
                && Float.compare(weapon.yakkyouSpeedX, yakkyouSpeedX) == 0
                && Float.compare(weapon.yakkyouSpeedY, yakkyouSpeedY) == 0
                && blazingMode == weapon.blazingMode
                && Float.compare(weapon.size, size) == 0
                && soundID == weapon.soundID
                && soundVolume == weapon.soundVolume
                && silencer == weapon.silencer
                && changeWeapon == weapon.changeWeapon
                && burst == weapon.burst
                && Objects.equals(name, weapon.name)
                && Objects.equals(model, weapon.model)
                && Objects.equals(texture, weapon.texture)
                && scopeMode == weapon.scopeMode
                && weaponP == weapon.weaponP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                model,
                texture,
                attacks,
                penetration,
                blazings,
                speed,
                nbsMax,
                reloads,
                reaction,
                errorRangeMin,
                errorRangeMax,
                modelPositionX,
                modelPositionY,
                modelPositionZ,
                flashPositionX,
                flashPositionY,
                flashPositionZ,
                yakkyouPositionX,
                yakkyouPositionY,
                yakkyouPositionZ,
                yakkyouSpeedX,
                yakkyouSpeedY,
                blazingMode,
                scopeMode,
                size,
                soundID,
                soundVolume,
                silencer,
                weaponP,
                changeWeapon,
                burst
        );
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public Weapon setModel(String model) {
        this.model = model;
        return this;
    }

    public Weapon setTexture(String texture) {
        this.texture = texture;
        return this;
    }

    public Weapon setAttacks(int attacks) {
        this.attacks = attacks;
        return this;
    }

    public Weapon setPenetration(int penetration) {
        this.penetration = penetration;
        return this;
    }

    public Weapon setBlazings(int blazings) {
        this.blazings = blazings;
        return this;
    }

    public Weapon setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public Weapon setNbsMax(int nbsMax) {
        this.nbsMax = nbsMax;
        return this;
    }

    public Weapon setReloads(int reloads) {
        this.reloads = reloads;
        return this;
    }

    public Weapon setReaction(int reaction) {
        this.reaction = reaction;
        return this;
    }

    public Weapon setErrorRangeMin(int errorRangeMin) {
        this.errorRangeMin = errorRangeMin;
        return this;
    }

    public Weapon setErrorRangeMax(int errorRangeMax) {
        this.errorRangeMax = errorRangeMax;
        return this;
    }

    public Weapon setModelPositionX(float modelPositionX) {
        this.modelPositionX = modelPositionX;
        return this;
    }

    public Weapon setModelPositionY(float modelPositionY) {
        this.modelPositionY = modelPositionY;
        return this;
    }

    public Weapon setModelPositionZ(float modelPositionZ) {
        this.modelPositionZ = modelPositionZ;
        return this;
    }

    public Weapon setFlashPositionX(float flashPositionX) {
        this.flashPositionX = flashPositionX;
        return this;
    }

    public Weapon setFlashPositionY(float flashPositionY) {
        this.flashPositionY = flashPositionY;
        return this;
    }

    public Weapon setFlashPositionZ(float flashPositionZ) {
        this.flashPositionZ = flashPositionZ;
        return this;
    }

    public Weapon setYakkyouPositionX(float yakkyouPositionX) {
        this.yakkyouPositionX = yakkyouPositionX;
        return this;
    }

    public Weapon setYakkyouPositionY(float yakkyouPositionY) {
        this.yakkyouPositionY = yakkyouPositionY;
        return this;
    }

    public Weapon setYakkyouPositionZ(float yakkyouPositionZ) {
        this.yakkyouPositionZ = yakkyouPositionZ;
        return this;
    }

    public Weapon setYakkyouSpeedX(float yakkyouSpeedX) {
        this.yakkyouSpeedX = yakkyouSpeedX;
        return this;
    }

    public Weapon setYakkyouSpeedY(float yakkyouSpeedY) {
        this.yakkyouSpeedY = yakkyouSpeedY;
        return this;
    }

    public Weapon setBlazingMode(boolean blazingMode) {
        this.blazingMode = blazingMode;
        return this;
    }

    public Weapon setScopeMode(ScopeMode scopeMode) {
        this.scopeMode = scopeMode;
        return this;
    }

    public Weapon setSize(float size) {
        this.size = size;
        return this;
    }

    public Weapon setSoundID(int soundID) {
        this.soundID = soundID;
        return this;
    }

    public Weapon setSoundVolume(int soundVolume) {
        this.soundVolume = soundVolume;
        return this;
    }

    public Weapon setSilencer(boolean silencer) {
        this.silencer = silencer;
        return this;
    }

    public Weapon setWeaponP(ShootingStance weaponP) {
        this.weaponP = weaponP;
        return this;
    }

    public Weapon setChangeWeapon(int changeWeapon) {
        this.changeWeapon = changeWeapon;
        return this;
    }

    public Weapon setBurst(int burst) {
        this.burst = burst;
        return this;
    }
}
