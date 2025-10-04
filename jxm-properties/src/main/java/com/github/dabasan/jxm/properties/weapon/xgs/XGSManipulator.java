package com.github.dabasan.jxm.properties.weapon.xgs;

import com.github.dabasan.jxm.properties.weapon.JXMWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * XGS manipulator
 *
 * @author maeda6uiui
 */
public class XGSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_WEAPONS = 23;
    private JXMWeapon[] weapons;

    /**
     * Creates a XGS manipulator.
     */
    public XGSManipulator() {
        weapons = new JXMWeapon[NUM_WEAPONS];
    }

    /**
     * Creates a XGS manipulator and loads a XGS file.
     *
     * @param path Path of the XGS file
     * @throws IOException If it fails to load the file
     */
    public XGSManipulator(Path path) throws IOException {
        var reader = new XGSReader(path, NUM_WEAPONS);
        weapons = reader.getWeaponData();
    }

    /**
     * Returns weapon data.
     *
     * @return array containing weapon data
     */
    public JXMWeapon[] getWeapons() {
        return weapons;
    }

    /**
     * Sets weapon data.
     *
     * @param weapons array containing weapon data
     */
    public void setWeapons(JXMWeapon[] weapons) {
        if (weapons.length != NUM_WEAPONS) {
            logger.warn("Invalid number of data contained in the array. number={}", weapons.length);
            return;
        }

        this.weapons = weapons;
    }

    /**
     * Saves the weapon data in a XGS file.
     *
     * @param path Path of the XGS file
     * @throws IOException if it fails to write to the file
     */
    public void save(Path path) throws IOException {
        var writer = new XGSWriter();
        writer.write(path, weapons);
    }
}
