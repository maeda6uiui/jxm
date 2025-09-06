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
            addShortToBinLE(bin, (short) weapons[i].attackPower);
            addShortToBinLE(bin, (short) weapons[i].penetration);
            addShortToBinLE(bin, (short) weapons[i].fireInterval);
            addShortToBinLE(bin, (short) weapons[i].bulletSpeed);
            addShortToBinLE(bin, (short) weapons[i].magazineCapacity);
            addShortToBinLE(bin, (short) weapons[i].reloadTime);
            addShortToBinLE(bin, (short) weapons[i].recoil);
            addShortToBinLE(bin, (short) weapons[i].errorRangeMin);
            addShortToBinLE(bin, (short) weapons[i].errorRangeMax);
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionX));
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionY));
            addShortToBinLE(bin, (short) Math.round(weapons[i].modelPositionZ));
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionX));
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionY));
            addShortToBinLE(bin, (short) Math.round(weapons[i].muzzleFlashPositionZ));
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionX));
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionY));
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgePositionZ));

            ShootingStance shootingStance = weapons[i].shootingStance;
            int shootingStanceSpc = WeaponBinEnumConverter
                    .getBinSpecifierFromShootingStance(shootingStance);
            addShortToBinLE(bin, (short) shootingStanceSpc);

            boolean rapidFireMode = weapons[i].rapidFire;
            int rapidFireModeSpc = (rapidFireMode) ? 0 : 1;
            addShortToBinLE(bin, (short) rapidFireModeSpc);

            ScopeMode scopeMode = weapons[i].scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            addShortToBinLE(bin, (short) scopeModeSpc);

            String textureFilepath = weapons[i].texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            addShortToBinLE(bin, (short) textureTypeSpc);

            String modelFilepath = weapons[i].model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            addShortToBinLE(bin, (short) modelTypeSpc);

            addShortToBinLE(bin, (short) Math.round(weapons[i].modelScale * 10.0f));
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgeEjectionVelocityX));
            addShortToBinLE(bin, (short) Math.round(weapons[i].cartridgeEjectionVelocityY));
            addShortToBinLE(bin, (short) weapons[i].fireSoundId);
            addShortToBinLE(bin, (short) weapons[i].fireSoundVolume);

            boolean suppressor = weapons[i].suppressor;
            int suppressorSpc = (suppressor) ? 1 : 0;
            addShortToBinLE(bin, (short) suppressorSpc);
        }

        for (int i = 0; i < numWeapons; i++) {
            String name = weapons[numWeapons - 1 - i].name;
            this.addNameToBin(bin, name);
        }

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
