package com.github.dabasan.jxm.properties.weapon;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * Weapon data for X operations
 * 
 * @author Daba
 *
 */
public class WeaponData {
	private String name;
	private String model;
	private String texture;
	private int attacks;
	private int penetration;
	private int blazings;
	private int speed;
	private int nbsMax;
	private int reloads;
	private int reaction;
	private int errorRangeMin;
	private int errorRangeMax;
	private Vector m;
	private Vector flash;
	private Vector yakkyouPosition;
	private Vector yakkyouSpeed;
	private boolean blazingMode;
	private ScopeMode scopeMode;
	private float size;
	private int soundID;
	private int soundVolume;
	private boolean silencer;
	private ShootingStance weaponP;
	private int changeWeapon;
	private int burst;

	public WeaponData() {
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
		m = new Vector();
		flash = new Vector();
		yakkyouPosition = new Vector();
		yakkyouSpeed = new Vector();
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

	@Override
	public String toString() {
		return "WeaponData [name=" + name + ", model=" + model + ", texture=" + texture
				+ ", attacks=" + attacks + ", penetration=" + penetration + ", blazings=" + blazings
				+ ", speed=" + speed + ", nbsMax=" + nbsMax + ", reloads=" + reloads + ", reaction="
				+ reaction + ", errorRangeMin=" + errorRangeMin + ", errorRangeMax=" + errorRangeMax
				+ ", m=" + m + ", flash=" + flash + ", yakkyouPosition=" + yakkyouPosition
				+ ", yakkyouSpeed=" + yakkyouSpeed + ", blazingMode=" + blazingMode + ", scopeMode="
				+ scopeMode + ", size=" + size + ", soundID=" + soundID + ", soundVolume="
				+ soundVolume + ", silencer=" + silencer + ", weaponP=" + weaponP
				+ ", changeWeapon=" + changeWeapon + ", burst=" + burst + "]";
	}

	public String getName() {
		return name;
	}
	public String getModel() {
		return model;
	}
	public String getTexture() {
		return texture;
	}
	public int getAttacks() {
		return attacks;
	}
	public int getPenetration() {
		return penetration;
	}
	public int getBlazings() {
		return blazings;
	}
	public int getSpeed() {
		return speed;
	}
	public int getNbsMax() {
		return nbsMax;
	}
	public int getReloads() {
		return reloads;
	}
	public int getReaction() {
		return reaction;
	}
	public int getErrorRangeMin() {
		return errorRangeMin;
	}
	public int getErrorRangeMax() {
		return errorRangeMax;
	}
	public Vector getM() {
		return m;
	}
	public Vector getFlash() {
		return flash;
	}
	public Vector getYakkyouPosition() {
		return yakkyouPosition;
	}
	public Vector getYakkyouSpeed() {
		return yakkyouSpeed;
	}
	public boolean isBlazingMode() {
		return blazingMode;
	}
	public ScopeMode getScopeMode() {
		return scopeMode;
	}
	public float getSize() {
		return size;
	}
	public int getSoundID() {
		return soundID;
	}
	public int getSoundVolume() {
		return soundVolume;
	}
	public boolean isSilencer() {
		return silencer;
	}
	public ShootingStance getWeaponP() {
		return weaponP;
	}
	public int getChangeWeapon() {
		return changeWeapon;
	}
	public int getBurst() {
		return burst;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}
	public void setPenetration(int penetration) {
		this.penetration = penetration;
	}
	public void setBlazings(int blazings) {
		this.blazings = blazings;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setNbsMax(int nbsMax) {
		this.nbsMax = nbsMax;
	}
	public void setReloads(int reloads) {
		this.reloads = reloads;
	}
	public void setReaction(int reaction) {
		this.reaction = reaction;
	}
	public void setErrorRangeMin(int errorRangeMin) {
		this.errorRangeMin = errorRangeMin;
	}
	public void setErrorRangeMax(int errorRangeMax) {
		this.errorRangeMax = errorRangeMax;
	}
	public void setM(Vector m) {
		this.m = m;
	}
	public void setFlash(Vector flash) {
		this.flash = flash;
	}
	public void setYakkyouPosition(Vector yakkyouPosition) {
		this.yakkyouPosition = yakkyouPosition;
	}
	public void setYakkyouSpeed(Vector yakkyouSpeed) {
		this.yakkyouSpeed = yakkyouSpeed;
	}
	public void setBlazingMode(boolean blazingMode) {
		this.blazingMode = blazingMode;
	}
	public void setScopeMode(ScopeMode scopeMode) {
		this.scopeMode = scopeMode;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public void setSoundID(int soundID) {
		this.soundID = soundID;
	}
	public void setSoundVolume(int soundVolume) {
		this.soundVolume = soundVolume;
	}
	public void setSilencer(boolean silencer) {
		this.silencer = silencer;
	}
	public void setWeaponP(ShootingStance weaponP) {
		this.weaponP = weaponP;
	}
	public void setChangeWeapon(int changeWeapon) {
		this.changeWeapon = changeWeapon;
	}
	public void setBurst(int burst) {
		this.burst = burst;
	}
}
