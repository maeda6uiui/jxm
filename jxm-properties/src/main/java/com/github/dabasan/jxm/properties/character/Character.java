package com.github.dabasan.jxm.properties.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Character
 * 
 * @author Daba
 *
 */
public class Character {
	public CharacterTextureType texture;
	public CharacterModelType model;
	public int hp;
	public AILevel aiLevel;
	public List<Integer> weapons;
	public CharacterType type;

	public Character() {
		texture = CharacterTextureType.SOLDIER_BLACK;
		model = CharacterModelType.MALE;
		hp = 100;
		aiLevel = AILevel.D;
		weapons = new ArrayList<>(Arrays.asList(0, 0));
		type = CharacterType.HUMAN;
	}
	/**
	 * Copies a character.
	 * 
	 * @param character
	 *            Character
	 */
	public Character(Character character) {
		this.texture = character.texture;
		this.model = character.model;
		this.hp = character.hp;
		this.aiLevel = character.aiLevel;
		this.weapons = new ArrayList<>(character.weapons);
		this.type = character.type;
	}

	@Override
	public String toString() {
		return "CharacterData [texture=" + texture + ", model=" + model + ", hp=" + hp
				+ ", aiLevel=" + aiLevel + ", weapons=" + weapons + ", type=" + type + "]";
	}
}
