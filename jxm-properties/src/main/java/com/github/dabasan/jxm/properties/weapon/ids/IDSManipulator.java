package com.github.dabasan.jxm.properties.weapon.ids;

import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * IDS manipulator
 *
 * @author maeda6uiui
 */
public class IDSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private XOPSWeapon weapon;

    /**
     * Creates an IDS manipulator.
     */
    public IDSManipulator() {
        weapon = new XOPSWeapon();
    }

    /**
     * Creates an IDS manipulator and loads an IDS file.
     *
     * @param path Path of the IDS file
     * @throws IOException If it fails to load the file
     */
    public IDSManipulator(Path path) throws IOException {
        var reader = new IDSReader(path);
        weapon = reader.getWeaponData();
    }

    /**
     * Returns weapon data.
     *
     * @return weapon data
     */
    public XOPSWeapon getWeapon() {
        return weapon;
    }

    /**
     * Sets weapon data.
     *
     * @param weapon weapon data to set
     */
    public void setWeapon(XOPSWeapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Saves the weapon data in an IDS file.
     *
     * @param path Path of the IDS file
     * @throws IOException if it fails to write to the file
     */
    public void save(Path path) throws IOException {
        var writer = new IDSWriter();
        writer.write(path, weapon);
    }
}
