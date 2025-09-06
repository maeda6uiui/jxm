package com.github.dabasan.jxm.properties.weapon.xops;

import com.github.dabasan.jxm.properties.weapon.*;

import static com.github.dabasan.jxm.bintools.ByteFunctions.setShortToBinLE;

/**
 * EXE weapon writer
 *
 * @author maeda6uiui
 */
class BINWeaponWriter {
    private int pos;

    public void write(byte[] bin, Weapon[] weapons, int dataStartPos, int nameStartPos) {
        pos = dataStartPos;
        int numWeapons = weapons.length;
        for (int i = 0; i < numWeapons; i++) {
            this.setShortAndIncrementPos(bin, weapons[i].attackPower);
            this.setShortAndIncrementPos(bin, weapons[i].penetration);
            this.setShortAndIncrementPos(bin, weapons[i].fireInterval);
            this.setShortAndIncrementPos(bin, weapons[i].bulletSpeed);
            this.setShortAndIncrementPos(bin, weapons[i].magazineCapacity);
            this.setShortAndIncrementPos(bin, weapons[i].reloadTime);
            this.setShortAndIncrementPos(bin, weapons[i].recoil);
            this.setShortAndIncrementPos(bin, weapons[i].errorRangeMin);
            this.setShortAndIncrementPos(bin, weapons[i].errorRangeMax);
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].modelPositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].modelPositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].modelPositionZ));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].muzzleFlashPositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].muzzleFlashPositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].muzzleFlashPositionZ));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].cartridgePositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].cartridgePositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].cartridgePositionZ));

            ShootingStance shootingStance = weapons[i].shootingStance;
            int shootingStanceSpc = WeaponBinEnumConverter.getBinSpecifierFromShootingStance(shootingStance);
            this.setShortAndIncrementPos(bin, shootingStanceSpc);

            boolean rapidFire = weapons[i].rapidFire;
            int rapidFireSpc = (rapidFire) ? 0 : 1;
            this.setShortAndIncrementPos(bin, rapidFireSpc);

            ScopeMode scopeMode = weapons[i].scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            this.setShortAndIncrementPos(bin, scopeModeSpc);

            String textureFilepath = weapons[i].texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            this.setShortAndIncrementPos(bin, textureTypeSpc);

            String modelFilepath = weapons[i].model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            this.setShortAndIncrementPos(bin, modelTypeSpc);

            this.setShortAndIncrementPos(bin, Math.round(weapons[i].modelScale * 10.0f));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].cartridgeEjectionVelocityX));
            this.setShortAndIncrementPos(bin, Math.round(weapons[i].cartridgeEjectionVelocityY));
            this.setShortAndIncrementPos(bin, weapons[i].fireSoundId);
            this.setShortAndIncrementPos(bin, weapons[i].fireSoundVolume);

            boolean suppressor = weapons[i].suppressor;
            int suppressorSpc = (suppressor) ? 1 : 0;
            this.setShortAndIncrementPos(bin, suppressorSpc);
        }

        pos = nameStartPos;
        for (int i = 0; i < numWeapons; i++) {
            String name = weapons[numWeapons - 1 - i].name;
            this.setNameToBin(bin, pos, name);

            pos += 16;
        }
    }

    private void setShortAndIncrementPos(byte[] bin, int v) {
        setShortToBinLE(bin, pos, (short) v);
        pos += 2;
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

        System.arraycopy(nameBuffer, 0, bin, pos + 0, 16);
    }
}
