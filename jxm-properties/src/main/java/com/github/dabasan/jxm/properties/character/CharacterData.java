package com.github.dabasan.jxm.properties.character;

import java.util.Arrays;

/**
 * Character data
 * 
 * @author Daba
 *
 */
public class CharacterData {
	private CharacterTextureType texture;
	private CharacterModelType model;
	private int hp;
	private AILevel aiLevel;
	private int[] weapons;
	private CharacterType type;

	public CharacterData() {
		texture = CharacterTextureType.SOLDIER_BLACK;
		model = CharacterModelType.MALE;
		hp = 100;
		aiLevel = AILevel.D;
		weapons = new int[]{0, 0};
		type = CharacterType.HUMAN;
	}

	@Override
	public String toString() {
		return "CharacterData [texture=" + texture + ", model=" + model + ", hp=" + hp
				+ ", aiLevel=" + aiLevel + ", weapons=" + Arrays.toString(weapons) + ", type="
				+ type + "]";
	}

	public CharacterTextureType getTexture() {
		return texture;
	}
	public CharacterModelType getModel() {
		return model;
	}
	public int getHP() {
		return hp;
	}
	public AILevel getAILevel() {
		return aiLevel;
	}
	public int[] getWeapons() {
		return weapons;
	}
	public CharacterType getType() {
		return type;
	}

	public void setTexture(CharacterTextureType texture) {
		this.texture = texture;
	}
	public void setModel(CharacterModelType model) {
		this.model = model;
	}
	public void setHP(int hp) {
		this.hp = hp;
	}
	public void setAILevel(AILevel aiLevel) {
		this.aiLevel = aiLevel;
	}
	public void setWeapons(int[] weapons) {
		if (weapons == null) {
			return;
		}
		if (weapons.length != 2) {
			return;
		}

		this.weapons = weapons;
	}
	public void setType(CharacterType type) {
		this.type = type;
	}
}
