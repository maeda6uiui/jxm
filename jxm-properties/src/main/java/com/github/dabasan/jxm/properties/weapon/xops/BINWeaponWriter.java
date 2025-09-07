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
        for (Weapon weapon : weapons) {
            this.setShortAndIncrementPos(bin, weapon.attackPower);
            this.setShortAndIncrementPos(bin, weapon.penetration);
            this.setShortAndIncrementPos(bin, weapon.fireInterval);
            this.setShortAndIncrementPos(bin, weapon.bulletSpeed);
            this.setShortAndIncrementPos(bin, weapon.magazineCapacity);
            this.setShortAndIncrementPos(bin, weapon.reloadTime);
            this.setShortAndIncrementPos(bin, weapon.recoil);
            this.setShortAndIncrementPos(bin, weapon.errorRangeMin);
            this.setShortAndIncrementPos(bin, weapon.errorRangeMax);
            this.setShortAndIncrementPos(bin, Math.round(weapon.modelPositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapon.modelPositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapon.modelPositionZ));
            this.setShortAndIncrementPos(bin, Math.round(weapon.muzzleFlashPositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapon.muzzleFlashPositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapon.muzzleFlashPositionZ));
            this.setShortAndIncrementPos(bin, Math.round(weapon.cartridgeEjectionPositionX));
            this.setShortAndIncrementPos(bin, Math.round(weapon.cartridgeEjectionPositionY));
            this.setShortAndIncrementPos(bin, Math.round(weapon.cartridgeEjectionPositionZ));

            ShootingStance shootingStance = weapon.shootingStance;
            int shootingStanceSpc = WeaponBinEnumConverter.getBinSpecifierFromShootingStance(shootingStance);
            this.setShortAndIncrementPos(bin, shootingStanceSpc);

            boolean rapidFire = weapon.rapidFire;
            int rapidFireSpc = (rapidFire) ? 0 : 1;
            this.setShortAndIncrementPos(bin, rapidFireSpc);

            ScopeMode scopeMode = weapon.scopeMode;
            int scopeModeSpc = WeaponBinEnumConverter.getBinSpecifierFromScopeMode(scopeMode);
            this.setShortAndIncrementPos(bin, scopeModeSpc);

            String textureFilepath = weapon.texture;
            WeaponTextureType textureType = TextureFilepaths.getEnumFromFilepath(textureFilepath);
            int textureTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromTextureType(textureType);
            this.setShortAndIncrementPos(bin, textureTypeSpc);

            String modelFilepath = weapon.model;
            WeaponModelType modelType = ModelFilepaths.getEnumFromFilepath(modelFilepath);
            int modelTypeSpc = WeaponBinEnumConverter.getBinSpecifierFromModelType(modelType);
            this.setShortAndIncrementPos(bin, modelTypeSpc);

            this.setShortAndIncrementPos(bin, Math.round(weapon.modelScale * 10.0f));
            this.setShortAndIncrementPos(bin, Math.round(weapon.cartridgeEjectionVelocityX));
            this.setShortAndIncrementPos(bin, Math.round(weapon.cartridgeEjectionVelocityY));
            this.setShortAndIncrementPos(bin, weapon.fireSoundId);
            this.setShortAndIncrementPos(bin, weapon.fireSoundVolume);

            boolean suppressor = weapon.suppressor;
            int suppressorSpc = (suppressor) ? 1 : 0;
            this.setShortAndIncrementPos(bin, suppressorSpc);
        }

        pos = nameStartPos;
        int numWeapons = weapons.length;
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
