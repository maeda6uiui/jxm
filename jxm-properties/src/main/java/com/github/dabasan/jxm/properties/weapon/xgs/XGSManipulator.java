package com.github.dabasan.jxm.properties.weapon.xgs;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * XGS manipulator
 *
 * @author maeda6uiui
 */
public class XGSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int NUM_WEAPONS = 23;
    private Weapon[] weapons;

    /**
     * Creates a XGS manipulator.
     */
    public XGSManipulator() {
        weapons = new Weapon[NUM_WEAPONS];
    }

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new XGSReader(is, NUM_WEAPONS);
        weapons = reader.getWeaponData();
    }

    /**
     * Creates a XGS manipulator and loads a XGS.
     *
     * @param is input stream to load a XGS from
     * @throws IOException if it fails to load
     */
    public XGSManipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates a XGS manipulator and loads a XGS.
     *
     * @param file file to load a XGS from
     * @throws IOException if it fails to load
     */
    public XGSManipulator(File file) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates a XGS manipulator and loads a XGS.
     *
     * @param filepath filepath to load a XGS from
     * @throws IOException if it fails to load
     */
    public XGSManipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
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
        Objects.requireNonNull(weapons);

        if (weapons.length != NUM_WEAPONS) {
            logger.warn("Invalid number of data contained in the array. number={}", weapons.length);
            return;
        }

        this.weapons = weapons;
    }

    private void saveAsXGSBase(OutputStream os) throws IOException {
        var writer = new XGSWriter();
        writer.write(os, weapons);
    }

    /**
     * Saves weapon data as a XGS.
     *
     * @param os output stream to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsXGS(OutputStream os) throws IOException {
        this.saveAsXGSBase(os);
    }

    /**
     * Saves weapon data as a XGS.
     *
     * @param file file to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsXGS(File file) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.saveAsXGSBase(bos);
        }
    }

    /**
     * Saves weapon data as a XGS.
     *
     * @param filepath filepath to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsXGS(String filepath) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.saveAsXGSBase(bos);
        }
    }
}
