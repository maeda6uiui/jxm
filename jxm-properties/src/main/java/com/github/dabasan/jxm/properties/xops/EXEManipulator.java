package com.github.dabasan.jxm.properties.xops;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.xops.BINCharacterManipulator;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.weapon.xops.BINWeaponManipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * EXE manipulator
 *
 * @author Daba
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
     * Creates an EXE manipulator and loads an EXE.
     *
     * @param is input stream load an EXE from
     * @throws IOException if it fails to load
     */
    public EXEManipulator(InputStream is) throws IOException {
        this.constructorBase(is);
    }

    /**
     * Creates an EXE manipulator and loads an EXE.
     *
     * @param file file to load an EXE from
     * @throws IOException if it fails to load
     */
    public EXEManipulator(File file) throws IOException {
        try (var fis = new FileInputStream(file)) {
            this.constructorBase(fis);
        }
    }

    /**
     * Creates an EXE manipulator and loads an EXE.
     *
     * @param filepath filepath to load an EXE from
     * @throws IOException if it fails to load
     */
    public EXEManipulator(String filepath) throws IOException {
        try (var fis = new FileInputStream(filepath)) {
            this.constructorBase(fis);
        }
    }

    private void constructorBase(InputStream is) throws IOException {
        byte[] bin;
        try (var bis = new BufferedInputStream(is)) {
            bin = bis.readAllBytes();
        }

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
    public Weapon[] getWeapons() {
        return weaponManipulator.getWeapons();
    }

    /**
     * Sets weapon data.
     *
     * @param weapons array containing weapon data
     */
    public void setWeapons(Weapon[] weapons) {
        weaponManipulator.setWeapons(weapons);
    }

    /**
     * Returns character data.
     *
     * @return array containing character data
     */
    public Character[] getCharacters() {
        return characterManipulator.getCharacters();
    }

    /**
     * Sets character data.
     *
     * @param characters array containing character data
     */
    public void setCharacters(Character[] characters) {
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

    // Base method for write().
    // Pass a non-null value to fileBackup to make a backup file before
    // overwriting the file.
    private void writeBase(File file, File fileBackup) throws IOException {
        byte[] bin;
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            bin = bis.readAllBytes();
        }

        // Make a backup file
        if (fileBackup != null) {
            try (var fosBackup = new FileOutputStream(fileBackup)) {
                fosBackup.write(bin);
            }
        }

        XOPSVersion version = XOPSFunctions.getXOPSVersion(bin);
        int weaponDataStartPos = this.getWeaponDataStartPos(version);
        int weaponNameStartPos = this.getWeaponNameStartPos(version);
        int characterDataStartPos = this.getCharacterDataStartPos(version);

        weaponManipulator.write(bin, weaponDataStartPos, weaponNameStartPos);
        characterManipulator.write(bin, characterDataStartPos);

        // Overwrite an EXE file of XOPS
        try (var fos = new FileOutputStream(file)) {
            fos.write(bin);
        }
    }

    /**
     * Writes data to an existing EXE. Pass a non-null value to the second
     * argument if you want to make a backup file before overwriting the
     * existing EXE.
     *
     * @param file       file to write the data to
     * @param fileBackup file for backup
     * @return -1: error 0: success
     */
    public int write(File file, File fileBackup) {
        if (file.exists() == false) {
            logger.error("The file specified does not exist. filename={}", file.getName());
            return -1;
        }

        try {
            this.writeBase(file, fileBackup);
        } catch (IOException e) {
            logger.error("Error", e);
            return -1;
        }

        return 0;
    }

    /**
     * Writes data to an existing EXE. Pass a non-null value to the second
     * argument if you want to make a backup file before overwriting the
     * existing EXE.
     *
     * @param filepath       filepath to write the data to
     * @param filepathBackup filepath for backup
     * @return -1: error 0: success
     */
    public int write(String filepath, String filepathBackup) {
        var file = new File(filepath);
        if (file.exists() == false) {
            logger.error("The file specified does not exist. filename={}", file.getName());
            return -1;
        }

        File fileBackup = null;
        if (filepathBackup != null) {
            fileBackup = new File(filepathBackup);
        }

        try {
            this.writeBase(file, fileBackup);
        } catch (IOException e) {
            logger.error("Error", e);
            return -1;
        }

        return 0;
    }
}
