package com.github.dabasan.jxm.properties.character.openxops;

/**
 * Settings for variable names used to generate and parse C++ code
 *
 * @author maeda6uiui
 */
public class CharacterVariableNameSettings {
    public String arrayName;

    //Character data
    public String texture;
    public String model;
    public String hp;
    public String aiLevel;
    public String weapon0;
    public String weapon1;
    public String type;

    /**
     * Creates a CharacterVariableNameSettings instance.
     */
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
}
