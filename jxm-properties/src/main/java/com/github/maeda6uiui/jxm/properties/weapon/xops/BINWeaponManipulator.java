package com.github.maeda6uiui.jxm.properties.weapon.xops;

import com.github.maeda6uiui.jxm.properties.weapon.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BIN weapon manipulator
 *
 * @author maeda6uiui
 */
public class BINWeaponManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_WEAPONS = 23;
    private Weapon[] weapons;

    /**
     * Creates a BINWeaponManipulator instance.
     */
    public BINWeaponManipulator() {
        weapons = new Weapon[NUM_WEAPONS];
    }

    /**
     * Creates a BINWeaponManipulator instance.
     *
     * @param bin          binary
     * @param dataStartPos start position of the weapon data
     * @param nameStartPos start position of the weapon names
     */
    public BINWeaponManipulator(byte[] bin, int dataStartPos, int nameStartPos) {
        var reader = new BINWeaponReader(bin, NUM_WEAPONS, dataStartPos, nameStartPos);
        weapons = reader.getWeaponData();
    }

    /**
     * Returns weapon data.
     *
     * @return array containing weapon data
     */
    public Weapon[] getWeapons() {
        return weapons;
    }

    /**
     * Sets weapon data.
     *
     * @param weapons array containing weapon data
     */
    public void setWeapons(Weapon[] weapons) {
        if (weapons.length != NUM_WEAPONS) {
            logger.warn("Invalid number of data contained in the array. number={}", weapons.length);
            return;
        }

        this.weapons = weapons;
    }

    /**
     * Writes out weapon data to an array containing bytes.
     *
     * @param bin          byte array to write weapon data in
     * @param dataStartPos start position of weapon data
     * @param nameStartPos start position of weapon names
     */
    public void write(byte[] bin, int dataStartPos, int nameStartPos) {
        var writer = new BINWeaponWriter();
        writer.write(bin, weapons, dataStartPos, nameStartPos);
    }
}
