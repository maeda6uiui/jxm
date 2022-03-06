package com.github.dabasan.jxm.properties.weapon.xgs;

import com.github.dabasan.jxm.bintools.ByteFunctions;
import com.github.dabasan.jxm.properties.weapon.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * XGS reader
 *
 * @author Daba
 */
class XGSReader {
    private Weapon[] weapons;

    public XGSReader(InputStream is, int numWeapons) throws IOException {
        weapons = new Weapon[numWeapons];

        // Read all bytes from a stream
        byte[] bin;
        try (var bis = new BufferedInputStream(is)) {
            bin = bis.readAllBytes();
        }

        int pos = 0x0000000E;

        for (int i = 0; i < numWeapons; i++) {
            var weapon = new Weapon();

            // Attacks
            weapon.attacks = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Penetration
            weapon.penetration = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Blazings
            weapon.blazings = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Speed
            weapon.speed = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // NbsMax
            weapon.nbsMax = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Reloads
            weapon.reloads = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Reaction
            weapon.reaction = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // ErrorRangeMin
            weapon.errorRangeMin = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // ErrorRangeMax
            weapon.errorRangeMax = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // ModelPositionX
            weapon.modelPositionX = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // ModelPositionY
            weapon.modelPositionY = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // ModelPositionZ
            weapon.modelPositionZ = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // FlashPositionX
            weapon.flashPositionX = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // FlashPositionY
            weapon.flashPositionY = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // FlashPositionZ
            weapon.flashPositionZ = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // YakkyouPositionX
            weapon.yakkyouPositionX = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // YakkyouPositionY
            weapon.yakkyouPositionY = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // YakkyouPositionZ
            weapon.yakkyouPositionZ = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // WeaponP
            int shootingStanceSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            weapon.weaponP = WeaponBinEnumConverter
                    .getShootingStanceFromBinSpecifier(shootingStanceSpc);
            // BlazingMode
            int blazingModeSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            weapon.blazingMode = (blazingModeSpc == 0) ? true : false;
            // ScopeMode
            int scopeModeSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
            // Texture
            int textureTypeSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            WeaponTextureType textureType = WeaponBinEnumConverter
                    .getTextureTypeFromBinSpecifier(textureTypeSpc);
            weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());
            // Model
            int modelTypeSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            WeaponModelType modelType = WeaponBinEnumConverter
                    .getModelTypeFromBinSpecifier(modelTypeSpc);
            weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());
            // Size
            weapon.size = ByteFunctions.getShortFromBinLE(bin, pos) * 0.1f;
            pos += 2;
            // YakkyouSpeedX
            weapon.yakkyouSpeedX = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // YakkyouSpeedY
            weapon.yakkyouSpeedY = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // SoundID
            weapon.soundID = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // SoundVolume
            weapon.soundVolume = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            // Silencer
            int silencerSpc = ByteFunctions.getShortFromBinLE(bin, pos);
            pos += 2;
            weapon.silencer = (silencerSpc == 0) ? false : true;

            // Change weapon
            if (i == 4) {
                weapon.changeWeapon = 16;
            } else if (i == 16) {
                weapon.changeWeapon = 4;
            }

            // Burst
            if (i == 19) {
                weapon.burst = 6;
            }

            weapons[i] = weapon;
        }

        // Name
        pos = 0x00000544;

        for (int i = 0; i < numWeapons; i++) {
            String name = this.getNameFromBin(bin, pos);
            pos += 16;

            weapons[numWeapons - 1 - i].name = name;
        }
    }

    private String getNameFromBin(byte[] bin, int start) {
        var nameBuffer = new byte[16];
        for (int i = 0; i < 15; i++) {
            nameBuffer[i] = bin[start + i];
        }
        nameBuffer[15] = 0;

        var name = new String(nameBuffer);

        int firstNullPos = 15;
        for (int i = 0; i < 15; i++) {
            if (name.charAt(i) == 0) {
                firstNullPos = i;
                break;
            }
        }

        return name.substring(0, firstNullPos);
    }

    public Weapon[] getWeaponData() {
        return weapons;
    }
}
