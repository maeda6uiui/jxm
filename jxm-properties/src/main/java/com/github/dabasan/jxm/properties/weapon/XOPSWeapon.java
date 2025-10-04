package com.github.dabasan.jxm.properties.weapon;

import java.util.Objects;

/**
 * Weapon
 *
 * @author maeda6uiui
 */
public class XOPSWeapon {
    public String name;
    public String model;
    public String texture;
    public int attackPower;
    public int penetration;
    public int fireInterval;
    public int bulletSpeed;
    public int magazineCapacity;
    public int reloadTime;
    public int recoil;
    public int errorRangeMin;
    public int errorRangeMax;
    public float modelPositionX;
    public float modelPositionY;
    public float modelPositionZ;
    public float muzzleFlashPositionX;
    public float muzzleFlashPositionY;
    public float muzzleFlashPositionZ;
    public float cartridgeEjectionPositionX;
    public float cartridgeEjectionPositionY;
    public float cartridgeEjectionPositionZ;
    public float cartridgeEjectionVelocityX;
    public float cartridgeEjectionVelocityY;
    public boolean rapidFire;
    public ScopeMode scopeMode;
    public float modelScale;
    public int fireSoundId;
    public int fireSoundVolume;
    public boolean suppressor;
    public ShootingStance shootingStance;
    public int switchableWeaponId;
    public int numProjectiles;

    /**
     * Creates a weapon.
     */
    public XOPSWeapon() {
        name = "";
        model = "";
        texture = "";
        attackPower = 0;
        penetration = 0;
        fireInterval = 0;
        bulletSpeed = 0;
        magazineCapacity = 0;
        reloadTime = 0;
        recoil = 0;
        errorRangeMin = 0;
        errorRangeMax = 0;
        modelPositionX = 0.0f;
        modelPositionY = 0.0f;
        modelPositionZ = 0.0f;
        muzzleFlashPositionX = 0.0f;
        muzzleFlashPositionY = 0.0f;
        muzzleFlashPositionZ = 0.0f;
        cartridgeEjectionPositionX = 0.0f;
        cartridgeEjectionPositionY = 0.0f;
        cartridgeEjectionPositionZ = 0.0f;
        cartridgeEjectionVelocityX = 0.0f;
        cartridgeEjectionVelocityY = 0.0f;
        rapidFire = false;
        scopeMode = ScopeMode.NONE;
        modelScale = 1.0f;
        fireSoundId = 0;
        fireSoundVolume = 0;
        suppressor = false;
        shootingStance = ShootingStance.RIFLE;
        switchableWeaponId = -1;
        numProjectiles = 1;
    }

    /**
     * Copies a weapon.
     *
     * @param weapon Weapon
     */
    public XOPSWeapon(XOPSWeapon weapon) {
        this.name = weapon.name;
        this.model = weapon.model;
        this.texture = weapon.texture;
        this.attackPower = weapon.attackPower;
        this.penetration = weapon.penetration;
        this.fireInterval = weapon.fireInterval;
        this.bulletSpeed = weapon.bulletSpeed;
        this.magazineCapacity = weapon.magazineCapacity;
        this.reloadTime = weapon.reloadTime;
        this.recoil = weapon.recoil;
        this.errorRangeMin = weapon.errorRangeMin;
        this.errorRangeMax = weapon.errorRangeMax;
        this.modelPositionX = weapon.modelPositionX;
        this.modelPositionY = weapon.modelPositionY;
        this.modelPositionZ = weapon.modelPositionZ;
        this.muzzleFlashPositionX = weapon.muzzleFlashPositionX;
        this.muzzleFlashPositionY = weapon.muzzleFlashPositionY;
        this.muzzleFlashPositionZ = weapon.muzzleFlashPositionZ;
        this.cartridgeEjectionPositionX = weapon.cartridgeEjectionPositionX;
        this.cartridgeEjectionPositionY = weapon.cartridgeEjectionPositionY;
        this.cartridgeEjectionPositionZ = weapon.cartridgeEjectionPositionZ;
        this.cartridgeEjectionVelocityX = weapon.cartridgeEjectionVelocityX;
        this.cartridgeEjectionVelocityY = weapon.cartridgeEjectionVelocityY;
        this.rapidFire = weapon.rapidFire;
        this.scopeMode = weapon.scopeMode;
        this.modelScale = weapon.modelScale;
        this.fireSoundId = weapon.fireSoundId;
        this.fireSoundVolume = weapon.fireSoundVolume;
        this.suppressor = weapon.suppressor;
        this.shootingStance = weapon.shootingStance;
        this.switchableWeaponId = weapon.switchableWeaponId;
        this.numProjectiles = weapon.numProjectiles;
    }

    @Override
    public String toString() {
        return "XOPSWeapon{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", texture='" + texture + '\'' +
                ", attackPower=" + attackPower +
                ", penetration=" + penetration +
                ", fireInterval=" + fireInterval +
                ", bulletSpeed=" + bulletSpeed +
                ", magazineCapacity=" + magazineCapacity +
                ", reloadTime=" + reloadTime +
                ", recoil=" + recoil +
                ", errorRangeMin=" + errorRangeMin +
                ", errorRangeMax=" + errorRangeMax +
                ", modelPositionX=" + modelPositionX +
                ", modelPositionY=" + modelPositionY +
                ", modelPositionZ=" + modelPositionZ +
                ", muzzleFlashPositionX=" + muzzleFlashPositionX +
                ", muzzleFlashPositionY=" + muzzleFlashPositionY +
                ", muzzleFlashPositionZ=" + muzzleFlashPositionZ +
                ", cartridgeEjectionPositionX=" + cartridgeEjectionPositionX +
                ", cartridgeEjectionPositionY=" + cartridgeEjectionPositionY +
                ", cartridgeEjectionPositionZ=" + cartridgeEjectionPositionZ +
                ", cartridgeEjectionVelocityX=" + cartridgeEjectionVelocityX +
                ", cartridgeEjectionVelocityY=" + cartridgeEjectionVelocityY +
                ", rapidFire=" + rapidFire +
                ", scopeMode=" + scopeMode +
                ", modelScale=" + modelScale +
                ", fireSoundId=" + fireSoundId +
                ", fireSoundVolume=" + fireSoundVolume +
                ", suppressor=" + suppressor +
                ", shootingStance=" + shootingStance +
                ", switchableWeaponId=" + switchableWeaponId +
                ", numProjectiles=" + numProjectiles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XOPSWeapon weapon = (XOPSWeapon) o;
        return attackPower == weapon.attackPower
                && penetration == weapon.penetration
                && fireInterval == weapon.fireInterval
                && bulletSpeed == weapon.bulletSpeed
                && magazineCapacity == weapon.magazineCapacity
                && reloadTime == weapon.reloadTime
                && recoil == weapon.recoil
                && errorRangeMin == weapon.errorRangeMin
                && errorRangeMax == weapon.errorRangeMax
                && Float.compare(weapon.modelPositionX, modelPositionX) == 0
                && Float.compare(weapon.modelPositionY, modelPositionY) == 0
                && Float.compare(weapon.modelPositionZ, modelPositionZ) == 0
                && Float.compare(weapon.muzzleFlashPositionX, muzzleFlashPositionX) == 0
                && Float.compare(weapon.muzzleFlashPositionY, muzzleFlashPositionY) == 0
                && Float.compare(weapon.muzzleFlashPositionZ, muzzleFlashPositionZ) == 0
                && Float.compare(weapon.cartridgeEjectionPositionX, cartridgeEjectionPositionX) == 0
                && Float.compare(weapon.cartridgeEjectionPositionY, cartridgeEjectionPositionY) == 0
                && Float.compare(weapon.cartridgeEjectionPositionZ, cartridgeEjectionPositionZ) == 0
                && Float.compare(weapon.cartridgeEjectionVelocityX, cartridgeEjectionVelocityX) == 0
                && Float.compare(weapon.cartridgeEjectionVelocityY, cartridgeEjectionVelocityY) == 0
                && rapidFire == weapon.rapidFire
                && Float.compare(weapon.modelScale, modelScale) == 0
                && fireSoundId == weapon.fireSoundId
                && fireSoundVolume == weapon.fireSoundVolume
                && suppressor == weapon.suppressor
                && switchableWeaponId == weapon.switchableWeaponId
                && numProjectiles == weapon.numProjectiles
                && Objects.equals(name, weapon.name)
                && Objects.equals(model, weapon.model)
                && Objects.equals(texture, weapon.texture)
                && scopeMode == weapon.scopeMode
                && shootingStance == weapon.shootingStance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                model,
                texture,
                attackPower,
                penetration,
                fireInterval,
                bulletSpeed,
                magazineCapacity,
                reloadTime,
                recoil,
                errorRangeMin,
                errorRangeMax,
                modelPositionX,
                modelPositionY,
                modelPositionZ,
                muzzleFlashPositionX,
                muzzleFlashPositionY,
                muzzleFlashPositionZ,
                cartridgeEjectionPositionX,
                cartridgeEjectionPositionY,
                cartridgeEjectionPositionZ,
                cartridgeEjectionVelocityX,
                cartridgeEjectionVelocityY,
                rapidFire,
                scopeMode,
                modelScale,
                fireSoundId,
                fireSoundVolume,
                suppressor,
                shootingStance,
                switchableWeaponId,
                numProjectiles
        );
    }

    public XOPSWeapon setName(String name) {
        this.name = name;
        return this;
    }

    public XOPSWeapon setModel(String model) {
        this.model = model;
        return this;
    }

    public XOPSWeapon setTexture(String texture) {
        this.texture = texture;
        return this;
    }

    public XOPSWeapon setAttackPower(int attackPower) {
        this.attackPower = attackPower;
        return this;
    }

    public XOPSWeapon setPenetration(int penetration) {
        this.penetration = penetration;
        return this;
    }

    public XOPSWeapon setFireInterval(int fireInterval) {
        this.fireInterval = fireInterval;
        return this;
    }

    public XOPSWeapon setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
        return this;
    }

    public XOPSWeapon setMagazineCapacity(int magazineCapacity) {
        this.magazineCapacity = magazineCapacity;
        return this;
    }

    public XOPSWeapon setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
        return this;
    }

    public XOPSWeapon setRecoil(int recoil) {
        this.recoil = recoil;
        return this;
    }

    public XOPSWeapon setErrorRangeMin(int errorRangeMin) {
        this.errorRangeMin = errorRangeMin;
        return this;
    }

    public XOPSWeapon setErrorRangeMax(int errorRangeMax) {
        this.errorRangeMax = errorRangeMax;
        return this;
    }

    public XOPSWeapon setModelPositionX(float modelPositionX) {
        this.modelPositionX = modelPositionX;
        return this;
    }

    public XOPSWeapon setModelPositionY(float modelPositionY) {
        this.modelPositionY = modelPositionY;
        return this;
    }

    public XOPSWeapon setModelPositionZ(float modelPositionZ) {
        this.modelPositionZ = modelPositionZ;
        return this;
    }

    public XOPSWeapon setMuzzleFlashPositionX(float muzzleFlashPositionX) {
        this.muzzleFlashPositionX = muzzleFlashPositionX;
        return this;
    }

    public XOPSWeapon setMuzzleFlashPositionY(float muzzleFlashPositionY) {
        this.muzzleFlashPositionY = muzzleFlashPositionY;
        return this;
    }

    public XOPSWeapon setMuzzleFlashPositionZ(float muzzleFlashPositionZ) {
        this.muzzleFlashPositionZ = muzzleFlashPositionZ;
        return this;
    }

    public XOPSWeapon setCartridgeEjectionPositionX(float cartridgeEjectionPositionX) {
        this.cartridgeEjectionPositionX = cartridgeEjectionPositionX;
        return this;
    }

    public XOPSWeapon setCartridgeEjectionPositionY(float cartridgeEjectionPositionY) {
        this.cartridgeEjectionPositionY = cartridgeEjectionPositionY;
        return this;
    }

    public XOPSWeapon setCartridgeEjectionPositionZ(float cartridgeEjectionPositionZ) {
        this.cartridgeEjectionPositionZ = cartridgeEjectionPositionZ;
        return this;
    }

    public XOPSWeapon setCartridgeEjectionVelocityX(float cartridgeEjectionVelocityX) {
        this.cartridgeEjectionVelocityX = cartridgeEjectionVelocityX;
        return this;
    }

    public XOPSWeapon setCartridgeEjectionVelocityY(float cartridgeEjectionVelocityY) {
        this.cartridgeEjectionVelocityY = cartridgeEjectionVelocityY;
        return this;
    }

    public XOPSWeapon setRapidFire(boolean rapidFire) {
        this.rapidFire = rapidFire;
        return this;
    }

    public XOPSWeapon setScopeMode(ScopeMode scopeMode) {
        this.scopeMode = scopeMode;
        return this;
    }

    public XOPSWeapon setModelScale(float modelScale) {
        this.modelScale = modelScale;
        return this;
    }

    public XOPSWeapon setFireSoundId(int fireSoundId) {
        this.fireSoundId = fireSoundId;
        return this;
    }

    public XOPSWeapon setFireSoundVolume(int fireSoundVolume) {
        this.fireSoundVolume = fireSoundVolume;
        return this;
    }

    public XOPSWeapon setSuppressor(boolean suppressor) {
        this.suppressor = suppressor;
        return this;
    }

    public XOPSWeapon setShootingStance(ShootingStance shootingStance) {
        this.shootingStance = shootingStance;
        return this;
    }

    public XOPSWeapon setSwitchableWeaponId(int switchableWeaponId) {
        this.switchableWeaponId = switchableWeaponId;
        return this;
    }

    public XOPSWeapon setNumProjectiles(int numProjectiles) {
        this.numProjectiles = numProjectiles;
        return this;
    }
}
