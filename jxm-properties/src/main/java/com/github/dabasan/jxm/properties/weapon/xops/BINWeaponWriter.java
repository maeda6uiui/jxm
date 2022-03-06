package com.github.dabasan.jxm.properties.weapon.xops;

import com.github.dabasan.jxm.properties.weapon.*;

import static com.github.dabasan.jxm.bintools.ByteFunctions.setShortToBinLE;

/**
 * EXE weapon writer
 *
 * @author Daba
 */
class BINWeaponWriter {
    public void write(byte[] bin, Weapon[] weapons, int dataStartPos, int nameStartPos) {
        int pos = dataStartPos;
        int numWeapons = weapons.length;
        for (int i = 0; i < numWeapons; i++) {
            // Attacks
            setShortToBinLE(bin, pos, (short) weapons[i].attacks);
            pos += 2;
            // Penetration
            setShortToBinLE(bin, pos, (short) weapons[i].penetration);
            pos += 2;
            // Blazings
            setShortToBinLE(bin, pos, (short) weapons[i].blazings);
            pos += 2;
            // Speed
            setShortToBinLE(bin, pos, (short) weapons[i].speed);
            pos += 2;
            // NbsMax
            setShortToBinLE(bin, pos, (short) weapons[i].nbsMax);
            pos += 2;
            // Reloads
            setShortToBinLE(bin, pos, (short) weapons[i].reloads);
            pos += 2;
            // Reaction
            setShortToBinLE(bin, pos, (short) weapons[i].reaction);
            pos += 2;
            // ErrorRangeMin
            setShortToBinLE(bin, pos, (short) weapons[i].errorRangeMin);
            pos += 2;
            // ErrorRangeMax
            setShortToBinLE(bin, pos, (short) weapons[i].errorRangeMax);
            pos += 2;
            // ModelPositionX
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionX));
            // ModelPositionY
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionY));
            // ModelPositionZ
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].modelPositionZ));
            // FlashPositionX
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionX));
            // FlashPositionY
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionY));
            // FlashPositionZ
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].flashPositionZ));
            // YakkyouPositionX
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionX));
            // YakkyouPositionY
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionY));
            // YakkyouPositionZ
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouPositionZ));
            // WeaponP
            ShootingStance shootingStance = weapons[i].weaponP;
            int shootingStanceSpc = WeaponBinEnumConverter
                    .getBinSpecifierFromShootingStance(shootingStance);
            setShortToBinLE(bin, pos, (short) shootingStanceSpc);
            pos += 2;
            // BlazingMode
            boolean blazingMode = weapons[i].blazingMode;
            int blazingModeSpc = (blazingMode) ? 0 : 1;
            setShortToBinLE(bin, pos, (short) blazingModeSpc);
            pos += 2;
            // ScopeMode
            ScopeMode scopeMode = weapons[i].scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            setShortToBinLE(bin, pos, (short) scopeModeSpc);
            pos += 2;
            // Texture
            String textureFilepath = weapons[i].texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            setShortToBinLE(bin, pos, (short) textureTypeSpc);
            pos += 2;
            // Model
            String modelFilepath = weapons[i].model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            setShortToBinLE(bin, pos, (short) modelTypeSpc);
            pos += 2;
            // Size
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].size * 10.0));
            pos += 2;
            // YakkyouSpeedX
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouSpeedX));
            // YakkyouSpeedY
            setShortToBinLE(bin, pos, (short) Math.round(weapons[i].yakkyouSpeedY));
            // SoundID
            setShortToBinLE(bin, pos, (short) weapons[i].soundID);
            pos += 2;
            // SoundVolume
            setShortToBinLE(bin, pos, (short) weapons[i].soundVolume);
            pos += 2;
            // Silencer
            boolean silencer = weapons[i].silencer;
            int silencerSpc = (silencer) ? 1 : 0;
            setShortToBinLE(bin, pos, (short) silencerSpc);
            pos += 2;
        }

        // Name
        pos = nameStartPos;

        for (int i = 0; i < numWeapons; i++) {
            String name = weapons[numWeapons - 1 - i].name;
            this.setNameToBin(bin, pos, name);

            pos += 16;
        }
    }

    private void setNameToBin(byte[] bin, int pos, String name) {
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
            bin[pos + i] = nameBuffer[i];
        }
    }
}
