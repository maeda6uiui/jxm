package com.github.dabasan.jxm.properties.weapon.xgs;

import com.github.dabasan.jxm.properties.weapon.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.github.dabasan.jxm.bintools.ByteFunctions.addShortToBinLE;

/**
 * XGS writer
 *
 * @author maeda6uiui
 */
class XGSWriter {
    public void write(OutputStream os, Weapon[] weapons) throws IOException {
        var bin = new ArrayList<Byte>();

        bin.add((byte) 0x58);//X
        bin.add((byte) 0x47);//G
        bin.add((byte) 0x53);//S
        bin.add((byte) 0x00);
        bin.add((byte) 0x01);
        bin.add((byte) 0x00);
        bin.add((byte) 0x0E);
        bin.add((byte) 0x00);
        bin.add((byte) 0x17);
        bin.add((byte) 0x00);
        bin.add((byte) 0x1D);
        bin.add((byte) 0x00);
        bin.add((byte) 0x08);
        bin.add((byte) 0x00);

        int numWeapons = weapons.length;
        for (int i = 0; i < numWeapons; i++) {
            //Attacks
            addShortToBinLE(bin, (short) weapons[i].attackPower);
            //Penetration
            addShortToBinLE(bin, (short) weapons[i].penetration);
            //Blazings
            addShortToBinLE(bin, (short) weapons[i].fireInterval);
            //Speed
            addShortToBinLE(bin, (short) weapons[i].bulletSpeed);
            //NbsMax
            addShortToBinLE(bin, (short) weapons[i].magazineCapacity);
            //Reloads
            addShortToBinLE(bin, (short) weapons[i].reloadTime);
            //Reaction
            addShortToBinLE(bin, (short) weapons[i].recoil);
            //ErrorRangeMin
            addShortToBinLE(bin, (short) weapons[i].errorRangeMin);
            //ErrorRangeMax
            addShortToBinLE(bin, (short) weapons[i].errorRangeMax);
            //ModelPositionX
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionX));
            //ModelPositionY
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionY));
            //ModelPositionZ
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionZ));
            //FlashPositionX
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionX));
            //FlashPositionY
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionY));
            //FlashPositionZ
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionZ));
            //YakkyouPositionX
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionX));
            //YakkyouPositionY
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionY));
            //YakkyouPositionZ
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionZ));
            //WeaponP
            ShootingStance shootingStance = weapons[i].shootingStance;
            int shootingStanceSpc = WeaponBinEnumConverter
                    .getBinSpecifierFromShootingStance(shootingStance);
            addShortToBinLE(bin, (short) shootingStanceSpc);
            //BlazingMode
            boolean blazingMode = weapons[i].rapidFire;
            int blazingModeSpc = (blazingMode) ? 0 : 1;
            addShortToBinLE(bin, (short) blazingModeSpc);
            //ScopeMode
            ScopeMode scopeMode = weapons[i].scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            addShortToBinLE(bin, (short) scopeModeSpc);
            //Texture
            String textureFilepath = weapons[i].texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            addShortToBinLE(bin, (short) textureTypeSpc);
            //Model
            String modelFilepath = weapons[i].model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            addShortToBinLE(bin, (short) modelTypeSpc);
            //Size
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelScale * 10.0f));
            //YakkyouSpeedX
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgeEjectionVelocityX));
            //YakkyouSpeedY
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgeEjectionVelocityY));
            //SoundID
            addShortToBinLE(bin, (short) weapons[i].fireSoundId);
            //SoundVolume
            addShortToBinLE(bin, (short) weapons[i].fireSoundVolume);
            //Silencer
            boolean silencer = weapons[i].suppressor;
            int silencerSpc = (silencer) ? 1 : 0;
            addShortToBinLE(bin, (short) silencerSpc);
        }

        //Name
        for (int i = 0; i < numWeapons; i++) {
            String name = weapons[numWeapons - 1 - i].name;
            this.addNameToBin(bin, name);
        }

        //Zero padding
        for (int i = 0; i < 16; i++) {
            bin.add((byte) 0x00);
        }

        for (byte b : bin) {
            os.write(b);
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
