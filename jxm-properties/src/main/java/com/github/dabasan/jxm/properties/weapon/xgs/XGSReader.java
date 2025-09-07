package com.github.dabasan.jxm.properties.weapon.xgs;

import com.github.dabasan.jxm.properties.weapon.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;

/**
 * XGS reader
 *
 * @author maeda6uiui
 */
class XGSReader {
    private final Weapon[] weapons;
    private int pos;

    public XGSReader(InputStream is, int numWeapons) throws IOException {
        weapons = new Weapon[numWeapons];

        byte[] bin = is.readAllBytes();
        pos = 0x0000000E;

        for (int i = 0; i < numWeapons; i++) {
            var weapon = new Weapon();

            weapon.attackPower = this.getShortAndIncrementPos(bin);
            weapon.penetration = this.getShortAndIncrementPos(bin);
            weapon.fireInterval = this.getShortAndIncrementPos(bin);
            weapon.bulletSpeed = this.getShortAndIncrementPos(bin);
            weapon.magazineCapacity = this.getShortAndIncrementPos(bin);
            weapon.reloadTime = this.getShortAndIncrementPos(bin);
            weapon.recoil = this.getShortAndIncrementPos(bin);
            weapon.errorRangeMin = this.getShortAndIncrementPos(bin);
            weapon.errorRangeMax = this.getShortAndIncrementPos(bin);
            weapon.modelPositionX = this.getShortAndIncrementPos(bin);
            weapon.modelPositionY = this.getShortAndIncrementPos(bin);
            weapon.modelPositionZ = this.getShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionX = this.getShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionY = this.getShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionZ = this.getShortAndIncrementPos(bin);
            weapon.cartridgeEjectionPositionX = this.getShortAndIncrementPos(bin);
            weapon.cartridgeEjectionPositionY = this.getShortAndIncrementPos(bin);
            weapon.cartridgeEjectionPositionZ = this.getShortAndIncrementPos(bin);

            int shootingStanceSpc = this.getShortAndIncrementPos(bin);
            weapon.shootingStance = WeaponBinEnumConverter.getShootingStanceFromBinSpecifier(shootingStanceSpc);

            int rapidFireSpc = this.getShortAndIncrementPos(bin);
            weapon.rapidFire = rapidFireSpc == 0;

            int scopeModeSpc = this.getShortAndIncrementPos(bin);
            weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);

            int textureTypeSpc = this.getShortAndIncrementPos(bin);
            WeaponTextureType textureType = WeaponBinEnumConverter.getTextureTypeFromBinSpecifier(textureTypeSpc);
            weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());

            int modelTypeSpc = this.getShortAndIncrementPos(bin);
            WeaponModelType modelType = WeaponBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);
            weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());

            weapon.modelScale = this.getShortAndIncrementPos(bin) * 0.1f;
            weapon.cartridgeEjectionVelocityX = this.getShortAndIncrementPos(bin);
            weapon.cartridgeEjectionVelocityY = this.getShortAndIncrementPos(bin);
            weapon.fireSoundId = this.getShortAndIncrementPos(bin);
            weapon.fireSoundVolume = this.getShortAndIncrementPos(bin);

            int suppressorSpc = this.getShortAndIncrementPos(bin);
            weapon.suppressor = suppressorSpc != 0;

            //Glock 18
            if (i == 4) {
                weapon.switchableWeaponId = 16;
            } else if (i == 16) {
                weapon.switchableWeaponId = 4;
            }

            //M1
            if (i == 19) {
                weapon.numProjectiles = 6;
            }

            weapons[i] = weapon;
        }

        pos = 0x00000544;
        for (int i = 0; i < numWeapons; i++) {
            String name = this.getNameFromBin(bin, pos);
            pos += 16;

            weapons[numWeapons - 1 - i].name = name;
        }
    }

    private int getShortAndIncrementPos(byte[] bin) {
        int ret = getShortFromBinLE(bin, pos);
        pos += 2;
        return ret;
    }

    private String getNameFromBin(byte[] bin, int start) {
        var nameBuffer = new byte[16];
        if (start + 15 <= bin.length) {
            System.arraycopy(bin, start, nameBuffer, 0, 15);
        } else {
            Arrays.fill(nameBuffer, (byte) 0);
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
