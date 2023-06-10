package com.github.dabasan.jxm.properties.weapon.ids;

import com.github.dabasan.jxm.properties.weapon.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * IDS manipulator
 *
 * @author maeda6uiui
 */
public class IDSManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Weapon weapon;

    /**
     * Creates an IDS manipulator.
     */
    public IDSManipulator() {
        weapon = new Weapon();
    }

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new IDSReader(is);
        weapon = reader.getWeaponData();
    }

    /**
     * Creates an IDS manipulator.
     *
     * @param is input stream to load an IDS from
     * @throws IOException if it fails to load
     */
    public IDSManipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates an IDS manipulator.
     *
     * @param file file to load an IDS from
     * @throws IOException if it fails to load
     */
    public IDSManipulator(File file) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates an IDS manipulator.
     *
     * @param filepath filepath to load an IDS from
     * @throws IOException if it fails to load
     */
    public IDSManipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Returns weapon data.
     *
     * @return weapon data
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets weapon data.
     *
     * @param weapon weapon data to set
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = Objects.requireNonNull(weapon);
    }

    private void innerSaveAsIDS(OutputStream os) throws IOException {
        var writer = new IDSWriter();
        writer.write(os, weapon);
    }

    /**
     * Saves weapon data as an IDS.
     *
     * @param os output stream to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsIDS(OutputStream os) throws IOException {
        this.innerSaveAsIDS(os);
    }

    /**
     * Saves weapon data as an IDS.
     *
     * @param file file to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsIDS(File file) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            this.innerSaveAsIDS(bos);
        }
    }

    /**
     * Saves weapon data as an IDS.
     *
     * @param filepath filepath to write the weapon data to
     * @throws IOException if it fails to output
     */
    public void saveAsIDS(String filepath) throws IOException {
        try (var bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            this.innerSaveAsIDS(bos);
        }
    }
}
