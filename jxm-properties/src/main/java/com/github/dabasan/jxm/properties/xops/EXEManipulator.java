package com.github.dabasan.jxm.properties.xops;

import com.github.dabasan.jxm.properties.character.XOPSCharacter;
import com.github.dabasan.jxm.properties.character.xops.BINCharacterManipulator;
import com.github.dabasan.jxm.properties.weapon.XOPSWeapon;
import com.github.dabasan.jxm.properties.weapon.xops.BINWeaponManipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * EXE manipulator
 *
 * @author maeda6uiui
 */
public class EXEManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private XOPSVersion srcXOPSVersion;
    private BINWeaponManipulator weaponManipulator;
    private BINCharacterManipulator characterManipulator;

    /**
     * Creates an EXE manipulator.
     */
    public EXEManipulator() {
        weaponManipulator = new BINWeaponManipulator();
        characterManipulator = new BINCharacterManipulator();
    }

    /**
     * Creates an EXE manipulator and loads an EXE file.
     *
     * @param path Path of the EXE file
     * @throws IOException If it fails to load the file
     */
    public EXEManipulator(Path path) throws IOException {
        byte[] bin = Files.readAllBytes(path);

        XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
        int weaponDataStartPos = this.getWeaponDataStartPos(version);
        int weaponNameStartPos = this.getWeaponNameStartPos(version);
        int characterDataStartPos = this.getCharacterDataStartPos(version);
        srcXOPSVersion = version;

        weaponManipulator = new BINWeaponManipulator(bin, weaponDataStartPos, weaponNameStartPos);
        characterManipulator = new BINCharacterManipulator(bin, characterDataStartPos);
    }

    private int getWeaponDataStartPos(XOPSVersion version) {
        return switch (version) {
            case XOPS096, XOPS096T -> 0x0005D32C;
            case XOPS097FT -> 0x0005E32C;
            case XOPS0975T -> 0x00077FB0;
            case XOPSOLT18F2 -> 0x0006640C;
            case XOPSOLT19F2 -> 0x000777E8;
        };
    }

    private int getWeaponNameStartPos(XOPSVersion version) {
        return switch (version) {
            case XOPS096, XOPS096T -> 0x000661E4;
            case XOPS097FT -> 0x000671E4;
            case XOPS0975T -> 0x00079140;
            case XOPSOLT18F2 -> 0x0006EF84;
            case XOPSOLT19F2 -> 0x00077370;
        };
    }

    private int getCharacterDataStartPos(XOPSVersion version) {
        return switch (version) {
            case XOPS096, XOPS096T -> 0x0005D864;
            case XOPS097FT -> 0x0005E864;
            case XOPS0975T -> 0x000784E8;
            case XOPSOLT18F2 -> 0x00066944;
            case XOPSOLT19F2 -> 0x00077D20;
        };
    }

    /**
     * Returns weapon data.
     *
     * @return array containing weapon data
     */
    public XOPSWeapon[] getWeapons() {
        return weaponManipulator.getWeapons();
    }

    /**
     * Sets weapon data.
     *
     * @param weapons array containing weapon data
     */
    public void setWeapons(XOPSWeapon[] weapons) {
        weaponManipulator.setWeapons(weapons);
    }

    /**
     * Returns character data.
     *
     * @return array containing character data
     */
    public XOPSCharacter[] getCharacters() {
        return characterManipulator.getCharacters();
    }

    /**
     * Sets character data.
     *
     * @param characters array containing character data
     */
    public void setCharacters(XOPSCharacter[] characters) {
        characterManipulator.setCharacters(characters);
    }

    /**
     * Returns the version of XOPS passed to the constructor.
     *
     * @return version of XOPS
     */
    public XOPSVersion getSrcXOPSVersion() {
        return srcXOPSVersion;
    }

    //Base method for write().
    //Pass a non-null value to fileBackup to make a backup file before
    //overwriting the file.
    private void writeBase(File file, File fileBackup) throws IOException {
        byte[] bin;
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            bin = bis.readAllBytes();
        }

        //Make a backup file
        if (fileBackup != null) {
            try (var bosBackup = new BufferedOutputStream(new FileOutputStream(fileBackup))) {
                bosBackup.write(bin);
            }
        }

        XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
        int weaponDataStartPos = this.getWeaponDataStartPos(version);
        int weaponNameStartPos = this.getWeaponNameStartPos(version);
        int characterDataStartPos = this.getCharacterDataStartPos(version);

        weaponManipulator.write(bin, weaponDataStartPos, weaponNameStartPos);
        characterManipulator.write(bin, characterDataStartPos);

        //Overwrite an EXE file of XOPS
        try (var bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(bin);
        }
    }

    /**
     * Writes the data to an existing EXE file.
     * Pass a non-null value to the second argument
     * if you want to make a backup file before overwriting the existing EXE file.
     *
     * @param path       Path of the XOPS executable
     * @param pathBackup Path of the backup file
     * @throws IOException If it fails to write to the file
     */
    public void write(Path path, Path pathBackup) throws IOException {
        byte[] bin = Files.readAllBytes(path);

        //Make a backup file
        if (pathBackup != null) {
            Files.write(pathBackup, bin);
        }

        //Update weapon and character data
        XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
        int weaponDataStartPos = this.getWeaponDataStartPos(version);
        int weaponNameStartPos = this.getWeaponNameStartPos(version);
        int characterDataStartPos = this.getCharacterDataStartPos(version);

        weaponManipulator.write(bin, weaponDataStartPos, weaponNameStartPos);
        characterManipulator.write(bin, characterDataStartPos);

        //Overwrite an EXE file of XOPS
        Files.write(path, bin);
    }
}
