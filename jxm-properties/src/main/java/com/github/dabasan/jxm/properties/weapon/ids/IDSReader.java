package com.github.dabasan.jxm.properties.weapon.ids;

import com.github.dabasan.jxm.properties.weapon.*;

import java.io.IOException;
import java.io.InputStream;

import static com.github.dabasan.jxm.bintools.ByteFunctions.getShortFromBinLE;

/**
 * IDS reader
 *
 * @author maeda6uiui
 */
class IDSReader {
    private final Weapon weapon;
    private int pos;

    public IDSReader(InputStream is) throws IOException {
        weapon = new Weapon();

        //Read all bytes from a stream
        byte[] bin = is.readAllBytes();

        //Read values
        pos = 0x0000000A;

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

        weapon.name = this.getNameFromBin(bin, pos);
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

    public Weapon getWeaponData() {
        return weapon;
    }
}
