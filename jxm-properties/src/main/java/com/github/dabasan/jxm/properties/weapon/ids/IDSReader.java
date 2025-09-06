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

    public IDSReader(InputStream is) throws IOException {
        weapon = new Weapon();

        //Read all bytes from a stream
        byte[] bin = is.readAllBytes();

        int pos = 0x0000000A;

        //Attacks
        weapon.attackPower = getShortFromBinLE(bin, pos);
        pos += 2;
        //Penetration
        weapon.penetration = getShortFromBinLE(bin, pos);
        pos += 2;
        //Blazings
        weapon.fireInterval = getShortFromBinLE(bin, pos);
        pos += 2;
        //Speed
        weapon.bulletSpeed = getShortFromBinLE(bin, pos);
        pos += 2;
        //NbsMax
        weapon.magazineCapacity = getShortFromBinLE(bin, pos);
        pos += 2;
        //Reloads
        weapon.reloadTime = getShortFromBinLE(bin, pos);
        pos += 2;
        //Reaction
        weapon.recoil = getShortFromBinLE(bin, pos);
        pos += 2;
        //ErrorRangeMin
        weapon.errorRangeMin = getShortFromBinLE(bin, pos);
        pos += 2;
        //ErrorRangeMax
        weapon.errorRangeMax = getShortFromBinLE(bin, pos);
        pos += 2;
        //ModelPositionX
        weapon.modelPositionX = getShortFromBinLE(bin, pos);
        pos += 2;
        //ModelPositionY
        weapon.modelPositionY = getShortFromBinLE(bin, pos);
        pos += 2;
        //ModelPositionZ
        weapon.modelPositionZ = getShortFromBinLE(bin, pos);
        pos += 2;
        //FlashPositionX
        weapon.muzzleFlashPositionX = getShortFromBinLE(bin, pos);
        pos += 2;
        //FlashPositionY
        weapon.muzzleFlashPositionY = getShortFromBinLE(bin, pos);
        pos += 2;
        //FlashPositionZ
        weapon.muzzleFlashPositionZ = getShortFromBinLE(bin, pos);
        pos += 2;
        //YakkyouPositionX
        weapon.cartridgePositionX = getShortFromBinLE(bin, pos);
        pos += 2;
        //YakkyouPositionY
        weapon.cartridgePositionY = getShortFromBinLE(bin, pos);
        pos += 2;
        //YakkyouPositionZ
        weapon.cartridgePositionZ = getShortFromBinLE(bin, pos);
        pos += 2;
        //WeaponP
        int shootingStanceSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        weapon.shootingStance = WeaponBinEnumConverter
                .getShootingStanceFromBinSpecifier(shootingStanceSpc);
        //BlazingMode
        int blazingModeSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        weapon.rapidFire = blazingModeSpc == 0;
        //ScopeMode
        int scopeModeSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        weapon.scopeMode = WeaponBinEnumConverter.getScopeModeFromBinSpecifier(scopeModeSpc);
        //Texture
        int textureTypeSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        WeaponTextureType textureType = WeaponBinEnumConverter
                .getTextureTypeFromBinSpecifier(textureTypeSpc);
        weapon.texture = TextureFilepaths.getTextureFilepath(textureType.ordinal());
        //Model
        int modelTypeSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        WeaponModelType modelType = WeaponBinEnumConverter
                .getModelTypeFromBinSpecifier(modelTypeSpc);
        weapon.model = ModelFilepaths.getModelFilepath(modelType.ordinal());
        //Size
        weapon.modelScale = getShortFromBinLE(bin, pos) * 0.1f;
        pos += 2;
        //YakkyouSpeedX
        weapon.cartridgeEjectionVelocityX = getShortFromBinLE(bin, pos);
        pos += 2;
        //YakkyouSpeedY
        weapon.cartridgeEjectionVelocityY = getShortFromBinLE(bin, pos);
        pos += 2;
        //SoundID
        weapon.fireSoundId = getShortFromBinLE(bin, pos);
        pos += 2;
        //SoundVolume
        weapon.fireSoundVolume = getShortFromBinLE(bin, pos);
        pos += 2;
        //Silencer
        int silencerSpc = getShortFromBinLE(bin, pos);
        pos += 2;
        weapon.suppressor = silencerSpc != 0;
        //Name
        weapon.name = this.getNameFromBin(bin, pos);
        pos += 2;
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
