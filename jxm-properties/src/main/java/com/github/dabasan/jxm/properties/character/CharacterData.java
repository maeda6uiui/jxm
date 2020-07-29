package com.github.dabasan.jxm.properties.character;

import java.util.Arrays;

/**
 * Character data
 * 
 * @author Daba
 *
 */
public class CharacterData {
	private TextureType texture;
	private ModelType model;
	private int hp;
	private AILevel aiLevel;
	private int[] weapons;
	private CharacterType type;

	public CharacterData() {
		texture = TextureType.SOLDIER_BLACK;
		model = ModelType.MALE;
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

	public TextureType getTexture() {
		return texture;
	}
	public ModelType getModel() {
		return model;
	}
	public int getHp() {
		return hp;
	}
	public AILevel getAiLevel() {
		return aiLevel;
	}
	public int[] getWeapons() {
		return weapons;
	}
	public CharacterType getType() {
		return type;
	}

	public void setTexture(TextureType texture) {
		this.texture = texture;
	}
	public void setModel(ModelType model) {
		this.model = model;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAiLevel(AILevel aiLevel) {
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
