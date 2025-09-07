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

        for (Weapon weapon : weapons) {
            addShortToBinLE(bin, (short) weapon.attackPower);
            addShortToBinLE(bin, (short) weapon.penetration);
            addShortToBinLE(bin, (short) weapon.fireInterval);
            addShortToBinLE(bin, (short) weapon.bulletSpeed);
            addShortToBinLE(bin, (short) weapon.magazineCapacity);
            addShortToBinLE(bin, (short) weapon.reloadTime);
            addShortToBinLE(bin, (short) weapon.recoil);
            addShortToBinLE(bin, (short) weapon.errorRangeMin);
            addShortToBinLE(bin, (short) weapon.errorRangeMax);
            addShortToBinLE(bin, (short) Math.round(weapon.modelPositionX));
            addShortToBinLE(bin, (short) Math.round(weapon.modelPositionY));
            addShortToBinLE(bin, (short) Math.round(weapon.modelPositionZ));
            addShortToBinLE(bin, (short) Math.round(weapon.muzzleFlashPositionX));
            addShortToBinLE(bin, (short) Math.round(weapon.muzzleFlashPositionY));
            addShortToBinLE(bin, (short) Math.round(weapon.muzzleFlashPositionZ));
            addShortToBinLE(bin, (short) Math.round(weapon.cartridgeEjectionPositionX));
            addShortToBinLE(bin, (short) Math.round(weapon.cartridgeEjectionPositionY));
            addShortToBinLE(bin, (short) Math.round(weapon.cartridgeEjectionPositionZ));

            ShootingStance shootingStance = weapon.shootingStance;
            int shootingStanceSpc = WeaponBinEnumConverter
                    .getBinSpecifierFromShootingStance(shootingStance);
            addShortToBinLE(bin, (short) shootingStanceSpc);

            boolean rapidFireMode = weapon.rapidFire;
            int rapidFireModeSpc = (rapidFireMode) ? 0 : 1;
            addShortToBinLE(bin, (short) rapidFireModeSpc);

            ScopeMode scopeMode = weapon.scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            addShortToBinLE(bin, (short) scopeModeSpc);

            String textureFilepath = weapon.texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            addShortToBinLE(bin, (short) textureTypeSpc);

            String modelFilepath = weapon.model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            addShortToBinLE(bin, (short) modelTypeSpc);

            addShortToBinLE(bin, (short) Math.round(weapon.modelScale * 10.0f));
            addShortToBinLE(bin, (short) Math.round(weapon.cartridgeEjectionVelocityX));
            addShortToBinLE(bin, (short) Math.round(weapon.cartridgeEjectionVelocityY));
            addShortToBinLE(bin, (short) weapon.fireSoundId);
            addShortToBinLE(bin, (short) weapon.fireSoundVolume);

            boolean suppressor = weapon.suppressor;
            int suppressorSpc = (suppressor) ? 1 : 0;
            addShortToBinLE(bin, (short) suppressorSpc);
        }

        int numWeapons = weapons.length;
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
