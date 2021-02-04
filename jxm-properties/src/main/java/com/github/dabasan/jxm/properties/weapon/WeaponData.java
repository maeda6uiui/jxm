package com.github.dabasan.jxm.properties.weapon;

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
	private float modelPositionX;
	private float modelPositionY;
	private float modelPositionZ;
	private float flashPositionX;
	private float flashPositionY;
	private float flashPositionZ;
	private float yakkyouPositionX;
	private float yakkyouPositionY;
	private float yakkyouPositionZ;
	private float yakkyouSpeedX;
	private float yakkyouSpeedY;
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
	public float getModelPositionX() {
		return modelPositionX;
	}
	public float getModelPositionY() {
		return modelPositionY;
	}
	public float getModelPositionZ() {
		return modelPositionZ;
	}
	public float getFlashPositionX() {
		return flashPositionX;
	}
	public float getFlashPositionY() {
		return flashPositionY;
	}
	public float getFlashPositionZ() {
		return flashPositionZ;
	}
	public float getYakkyouPositionX() {
		return yakkyouPositionX;
	}
	public float getYakkyouPositionY() {
		return yakkyouPositionY;
	}
	public float getYakkyouPositionZ() {
		return yakkyouPositionZ;
	}
	public float getYakkyouSpeedX() {
		return yakkyouSpeedX;
	}
	public float getYakkyouSpeedY() {
		return yakkyouSpeedY;
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
	public void setModelPositionX(float modelPositionX) {
		this.modelPositionX = modelPositionX;
	}
	public void setModelPositionY(float modelPositionY) {
		this.modelPositionY = modelPositionY;
	}
	public void setModelPositionZ(float modelPositionZ) {
		this.modelPositionZ = modelPositionZ;
	}
	public void setFlashPositionX(float flashPositionX) {
		this.flashPositionX = flashPositionX;
	}
	public void setFlashPositionY(float flashPositionY) {
		this.flashPositionY = flashPositionY;
	}
	public void setFlashPositionZ(float flashPositionZ) {
		this.flashPositionZ = flashPositionZ;
	}
	public void setYakkyouPositionX(float yakkyouPositionX) {
		this.yakkyouPositionX = yakkyouPositionX;
	}
	public void setYakkyouPositionY(float yakkyouPositionY) {
		this.yakkyouPositionY = yakkyouPositionY;
	}
	public void setYakkyouPositionZ(float yakkyouPositionZ) {
		this.yakkyouPositionZ = yakkyouPositionZ;
	}
	public void setYakkyouSpeedX(float yakkyouSpeedX) {
		this.yakkyouSpeedX = yakkyouSpeedX;
	}
	public void setYakkyouSpeedY(float yakkyouSpeedY) {
		this.yakkyouSpeedY = yakkyouSpeedY;
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
