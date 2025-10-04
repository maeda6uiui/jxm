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
public class XOPSCharacter {
    public CharacterTextureType texture;
    public CharacterModelType model;
    public int hp;
    public AILevel aiLevel;
    public List<Integer> weapons;
    public CharacterType type;

    /**
     * Creates a character.
     */
    public XOPSCharacter() {
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
    public XOPSCharacter(XOPSCharacter character) {
        this.texture = character.texture;
        this.model = character.model;
        this.hp = character.hp;
        this.aiLevel = character.aiLevel;
        this.weapons = new ArrayList<>(character.weapons);
        this.type = character.type;
    }

    @Override
    public String toString() {
        return "XOPSCharacter{" +
                "texture=" + texture +
                ", model=" + model +
                ", hp=" + hp +
                ", aiLevel=" + aiLevel +
                ", weapons=" + weapons +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XOPSCharacter character = (XOPSCharacter) o;
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

    public XOPSCharacter setTexture(CharacterTextureType texture) {
        this.texture = texture;
        return this;
    }

    public XOPSCharacter setModel(CharacterModelType model) {
        this.model = model;
        return this;
    }

    public XOPSCharacter setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public XOPSCharacter setAiLevel(AILevel aiLevel) {
        this.aiLevel = aiLevel;
        return this;
    }

    public XOPSCharacter setWeapons(List<Integer> weapons) {
        this.weapons = weapons;
        return this;
    }

    public XOPSCharacter setType(CharacterType type) {
        this.type = type;
        return this;
    }
}
