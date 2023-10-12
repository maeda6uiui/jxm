package com.github.dabasan.jxm.properties.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Character
 *
 * @author maeda6uiui
 */
public class Character {
    public CharacterTextureType texture;
    public CharacterModelType model;
    public int hp;
    public AILevel aiLevel;
    public List<Integer> weapons;
    public CharacterType type;

    /**
     * Creates a character.
     */
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
     * @param character Character
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return hp == character.hp
                && texture == character.texture
                && model == character.model
                && aiLevel == character.aiLevel
                && Objects.equals(weapons, character.weapons)
                && type == character.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(texture, model, hp, aiLevel, weapons, type);
    }
}
