package com.github.dabasan.jxm.properties.character.openxops;

/**
 * Settings for characters' variable names in OpenXOPS
 * 
 * @author Daba
 *
 */
public class CharacterVariableNameSettings {
	private String arrayName;

	// Character data
	private String texture;
	private String model;
	private String hp;
	private String aiLevel;
	private String weapon0;
	private String weapon1;
	private String type;

	public CharacterVariableNameSettings() {
		arrayName = "Human";

		texture = "texture";
		model = "model";
		hp = "hp";
		aiLevel = "AIlevel";
		weapon0 = "Weapon[0]";
		weapon1 = "Weapon[1]";
		type = "type";
	}

	public String getArrayName() {
		return arrayName;
	}
	public String getTexture() {
		return texture;
	}
	public String getModel() {
		return model;
	}
	public String getHP() {
		return hp;
	}
	public String getAILevel() {
		return aiLevel;
	}
	public String getWeapon0() {
		return weapon0;
	}
	public String getWeapon1() {
		return weapon1;
	}
	public String getType() {
		return type;
	}

	public void setArrayName(String arrayName) {
		this.arrayName = arrayName;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setHP(String hp) {
		this.hp = hp;
	}
	public void setAILevel(String aiLevel) {
		this.aiLevel = aiLevel;
	}
	public void setWeapon0(String weapon0) {
		this.weapon0 = weapon0;
	}
	public void setWeapon1(String weapon1) {
		this.weapon1 = weapon1;
	}
	public void setType(String type) {
		this.type = type;
	}
}
