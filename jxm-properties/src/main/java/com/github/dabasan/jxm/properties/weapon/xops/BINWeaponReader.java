package com.github.dabasan.jxm.properties.weapon.xops;

import com.github.dabasan.jxm.properties.weapon.*;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;

/**
 * EXE weapon reader
 *
 * @author maeda6uiui
 */
class BINWeaponReader {
    private final Weapon[] weapons;
    private int pos;

    public BINWeaponReader(byte[] bin, int numWeapons, int dataStartPos, int nameStartPos) {
        weapons = new Weapon[numWeapons];

        pos = dataStartPos;
        for (int i = 0; i < numWeapons; i++) {
            var weapon = new Weapon();

            weapon.attackPower = this.readShortAndIncrementPos(bin);
            weapon.penetration = this.readShortAndIncrementPos(bin);
            weapon.fireInterval = this.readShortAndIncrementPos(bin);
            weapon.bulletSpeed = this.readShortAndIncrementPos(bin);
            weapon.magazineCapacity = this.readShortAndIncrementPos(bin);
            weapon.reloadTime = this.readShortAndIncrementPos(bin);
            weapon.recoil = this.readShortAndIncrementPos(bin);
            weapon.errorRangeMin = this.readShortAndIncrementPos(bin);
            weapon.errorRangeMax = this.readShortAndIncrementPos(bin);
            weapon.modelPositionX = this.readShortAndIncrementPos(bin);
            weapon.modelPositionY = this.readShortAndIncrementPos(bin);
            weapon.modelPositionZ = this.readShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionX = this.readShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionY = this.readShortAndIncrementPos(bin);
            weapon.muzzleFlashPositionZ = this.readShortAndIncrementPos(bin);
            weapon.cartridgePositionX = this.readShortAndIncrementPos(bin);
            weapon.cartridgePositionY = this.readShortAndIncrementPos(bin);
            weapon.cartridgePositionZ = this.readShortAndIncrementPos(bin);

            int shootingStanceSpc = this.readShortAndIncrementPos(bin);
            weapon.shootingStance = WeaponBinEnumConverter.getShootingStanceFromBinSpecifier(shootingStanceSpc);

            int rapidFireSpc = this.readShortAndIncrementPos(bin);
            weapon.rapidFire = rapidFireSpc == 0;

            int scopeModeSpc = this.readShortAndIncrementPos(bin);
            weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);

            int textureTypeSpc = this.readShortAndIncrementPos(bin);
            WeaponTextureType textureType = WeaponBinEnumConverter.getTextureTypeFromBinSpecifier(textureTypeSpc);
            weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());

            int modelTypeSpc = this.readShortAndIncrementPos(bin);
            WeaponModelType modelType = WeaponBinEnumConverter.getModelTypeFromBinSpecifier(modelTypeSpc);
            weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());

            weapon.modelScale = this.readShortAndIncrementPos(bin) * 0.1f;
            weapon.cartridgeEjectionVelocityX = this.readShortAndIncrementPos(bin);
            weapon.cartridgeEjectionVelocityY = this.readShortAndIncrementPos(bin);
            weapon.fireSoundId = this.readShortAndIncrementPos(bin);
            weapon.fireSoundVolume = this.readShortAndIncrementPos(bin);

            int suppressorSpc = this.readShortAndIncrementPos(bin);
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

        //Name
        pos = nameStartPos;
        for (int i = 0; i < numWeapons; i++) {
            String name = this.getNameFromBin(bin, pos);
            pos += 16;

            weapons[numWeapons - 1 - i].name = name;
        }
    }

    private int readShortAndIncrementPos(byte[] bin) {
        int ret = getShortFromBinLE(bin, pos);
        pos += 2;
        return ret;
    }

    private String getNameFromBin(byte[] bin, int start) {
        var nameBuffer = new byte[16];
        System.arraycopy(bin, start + 0, nameBuffer, 0, 15);
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
