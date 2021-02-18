package com.github.dabasan.jxm.properties.character;

import java.util.ArrayList;
import java.util.List;

/**
 * Character data
 * 
 * @author Daba
 *
 */
public class CharacterData {
	public CharacterTextureType texture;
	public CharacterModelType model;
	public int hp;
	public AILevel aiLevel;
	public List<Integer> weapons;
	public CharacterType type;

	public CharacterData() {
		texture = CharacterTextureType.SOLDIER_BLACK;
		model = CharacterModelType.MALE;
		hp = 100;
		aiLevel = AILevel.D;
		weapons = new ArrayList<>();
		type = CharacterType.HUMAN;
	}

	@Override
	public String toString() {
		return "CharacterData [texture=" + texture + ", model=" + model + ", hp=" + hp
				+ ", aiLevel=" + aiLevel + ", weapons=" + weapons + ", type=" + type + "]";
	}
}
