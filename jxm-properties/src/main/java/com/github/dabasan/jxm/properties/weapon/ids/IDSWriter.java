package com.github.dabasan.jxm.properties.weapon.ids;

import com.github.dabasan.jxm.properties.weapon.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.github.dabasan.jxm.bintools.ByteFunctions.addShortToBinLE;

/**
 * IDS writer
 *
 * @author Daba
 */
class IDSWriter {
    public void write(OutputStream os, Weapon weapon) throws IOException {
        var bin = new ArrayList<Byte>();

        bin.add((byte) 0x49);// I
        bin.add((byte) 0x44);// D
        bin.add((byte) 0x53);// S
        bin.add((byte) 0x00);
        bin.add((byte) 0x01);
        bin.add((byte) 0x00);
        bin.add((byte) 0x0A);
        bin.add((byte) 0x00);
        bin.add((byte) 0x1D);
        bin.add((byte) 0x00);

        // Attacks
        addShortToBinLE(bin, (short) weapon.attacks);
        // Penetration
        addShortToBinLE(bin, (short) weapon.penetration);
        // Blazings
        addShortToBinLE(bin, (short) weapon.blazings);
        // Speed
        addShortToBinLE(bin, (short) weapon.speed);
        // NbsMax
        addShortToBinLE(bin, (short) weapon.nbsMax);
        // Reloads
        addShortToBinLE(bin, (short) weapon.reloads);
        // Reaction
        addShortToBinLE(bin, (short) weapon.reaction);
        // ErrorRangeMin
        addShortToBinLE(bin, (short) weapon.errorRangeMin);
        // ErrorRangeMax
        addShortToBinLE(bin, (short) weapon.errorRangeMax);
        // ModelPositionX
        addShortToBinLE(bin, (short) Math.round(weapon.modelPositionX));
        // ModelPositionY
        addShortToBinLE(bin, (short) Math.round(weapon.modelPositionY));
        // ModelPositionZ
        addShortToBinLE(bin, (short) Math.round(weapon.modelPositionZ));
        // FlashPositionX
        addShortToBinLE(bin, (short) Math.round(weapon.flashPositionX));
        // FlashPositionY
        addShortToBinLE(bin, (short) Math.round(weapon.flashPositionY));
        // FlashPositionZ
        addShortToBinLE(bin, (short) Math.round(weapon.flashPositionZ));
        // YakkyouPositionX
        addShortToBinLE(bin, (short) Math.round(weapon.yakkyouPositionX));
        // YakkyouPositionY
        addShortToBinLE(bin, (short) Math.round(weapon.yakkyouPositionY));
        // YakkyouPositionZ
        addShortToBinLE(bin, (short) Math.round(weapon.yakkyouPositionZ));
        // WeaponP
        ShootingStance shootingStance = weapon.weaponP;
        int shootingStanceSpc = WeaponBinEnumConverter
                .getBinSpecifierFromShootingStance(shootingStance);
        addShortToBinLE(bin, (short) shootingStanceSpc);
        // BlazingMode
        boolean blazingMode = weapon.blazingMode;
        int blazingModeSpc = (blazingMode) ? 0 : 1;
        addShortToBinLE(bin, (short) blazingModeSpc);
        // ScopeMode
        ScopeMode scopeMode = weapon.scopeMode;
        int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
        addShortToBinLE(bin, (short) scopeModeSpc);
        // Texture
        String textureFilepath = weapon.texture;
        WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
        int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
        addShortToBinLE(bin, (short) textureTypeSpc);
        // Model
        String modelFilepath = weapon.model;
        WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
        int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
        addShortToBinLE(bin, (short) modelTypeSpc);
        // Size
        addShortToBinLE(bin, (short) Math.round(weapon.size * 10.0f));
        // YakkyouSpeedX
        addShortToBinLE(bin, (short) Math.round(weapon.yakkyouSpeedX));
        // YakkyouSpeedY
        addShortToBinLE(bin, (short) Math.round(weapon.yakkyouSpeedY));
        // SoundID
        addShortToBinLE(bin, (short) weapon.soundID);
        // SoundVolume
        addShortToBinLE(bin, (short) weapon.soundVolume);
        // Silencer
        boolean silencer = weapon.silencer;
        int silencerSpc = (silencer) ? 1 : 0;
        addShortToBinLE(bin, (short) silencerSpc);
        // Name
        this.addNameToBin(bin, weapon.name);

        try (var bos = new BufferedOutputStream(os)) {
            for (Byte b : bin) {
                bos.write(b);
            }
        }
    }

    private void addNameToBin(List<Byte> bin, String name) {
        var nameBuffer = new byte[16];
        for (int i = 0; i < 16; i++) {
            nameBuffer[i] = 0;
        }

        for (int i = 0; i < name.length(); i++) {
            if (i >= 15) {
                break;
            }
            nameBuffer[i] = (byte) name.charAt(i);
        }

        for (int i = 0; i < 16; i++) {
            bin.add(nameBuffer[i]);
        }
    }
}
