package com.github.dabasan.jxm.properties.xops;

import com.github.dabasan.jxm.properties.character.JXMCharacter;
import com.github.dabasan.jxm.properties.character.xops.BINCharacterManipulator;
import com.github.dabasan.jxm.properties.weapon.JXMWeapon;
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
        int weaponDataStartPos;

        switch (version) {
            case XOPS096:
                weaponDataStartPos = 0x0005D32C;
                break;
            case XOPS096T:
                weaponDataStartPos = 0x0005D32C;
                break;
            case XOPS097FT:
                weaponDataStartPos = 0x0005E32C;
                break;
            case XOPS0975T:
                weaponDataStartPos = 0x00077FB0;
                break;
            case XOPSOLT18F2:
                weaponDataStartPos = 0x0006640C;
                break;
            case XOPSOLT19F2:
                weaponDataStartPos = 0x000777E8;
                break;
            default:
                weaponDataStartPos = 0x00000000;
                break;
        }

        return weaponDataStartPos;
    }

    private int getWeaponNameStartPos(XOPSVersion version) {
        int weaponNameStartPos;

        switch (version) {
            case XOPS096:
                weaponNameStartPos = 0x000661E4;
                break;
            case XOPS096T:
                weaponNameStartPos = 0x000661E4;
                break;
            case XOPS097FT:
                weaponNameStartPos = 0x000671E4;
                break;
            case XOPS0975T:
                weaponNameStartPos = 0x00079140;
                break;
            case XOPSOLT18F2:
                weaponNameStartPos = 0x0006EF84;
                break;
            case XOPSOLT19F2:
                weaponNameStartPos = 0x00077370;
                break;
            default:
                weaponNameStartPos = 0x00000000;
                break;
        }

        return weaponNameStartPos;
    }

    private int getCharacterDataStartPos(XOPSVersion version) {
        int characterDataStartPos;

        switch (version) {
            case XOPS096:
                characterDataStartPos = 0x0005D864;
                break;
            case XOPS096T:
                characterDataStartPos = 0x0005D864;
                break;
            case XOPS097FT:
                characterDataStartPos = 0x0005E864;
                break;
            case XOPS0975T:
                characterDataStartPos = 0x000784E8;
                break;
            case XOPSOLT18F2:
                characterDataStartPos = 0x00066944;
                break;
            case XOPSOLT19F2:
                characterDataStartPos = 0x00077D20;
                break;
            default:
                characterDataStartPos = 0x00000000;
                break;
        }

        return characterDataStartPos;
    }

    /**
     * Returns weapon data.
     *
     * @return array containing weapon data
     */
    public JXMWeapon[] getWeapons() {
        return weaponManipulator.getWeapons();
    }

    /**
     * Sets weapon data.
     *
     * @param weapons array containing weapon data
     */
    public void setWeapons(JXMWeapon[] weapons) {
        weaponManipulator.setWeapons(weapons);
    }

    /**
     * Returns character data.
     *
     * @return array containing character data
     */
    public JXMCharacter[] getCharacters() {
        return characterManipulator.getCharacters();
    }

    /**
     * Sets character data.
     *
     * @param characters array containing character data
     */
    public void setCharacters(JXMCharacter[] characters) {
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

        //Detect the version of XOPS
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
