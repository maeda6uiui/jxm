package com.github.maeda6uiui.jxm.properties;

import com.github.maeda6uiui.jxm.properties.character.*;
import com.github.maeda6uiui.jxm.properties.character.Character;
import com.github.maeda6uiui.jxm.properties.config.Config;
import com.github.maeda6uiui.jxm.properties.config.KeyCode;
import com.github.maeda6uiui.jxm.properties.config.WindowMode;
import com.github.maeda6uiui.jxm.properties.weapon.ScopeMode;
import com.github.maeda6uiui.jxm.properties.weapon.ShootingStance;
import com.github.maeda6uiui.jxm.properties.weapon.Weapon;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Utility methods for test
 *
 * @author maeda6uiui
 */
public class TestUtils {
    private static final Random random;
    private static final String RANDOM_CHARACTERS
            = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&\\'()*+,-./:;<=>?@[\\\\]^_`{|}~ ";

    static {
        random = new Random();
    }

    public static String getFileHash(String filepath) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] hash = null;
        try (var dis = new DigestInputStream(new BufferedInputStream(new FileInputStream(filepath)), md)) {
            while (dis.read() != -1) {

            }

            hash = md.digest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var sb = new StringBuilder();
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }

        return sb.toString();
    }

    public static String generateRandomString(int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            char c = RANDOM_CHARACTERS.charAt(random.nextInt(RANDOM_CHARACTERS.length()));
            ret += c;
        }

        return ret;
    }

    public static Weapon generateRandomWeapon() {
        return new Weapon()
                .setName(generateRandomString(10))
                .setModel(generateRandomString(20))
                .setTexture(generateRandomString(20))
                .setAttacks(random.nextInt())
                .setPenetration(random.nextInt())
                .setBlazings(random.nextInt())
                .setSpeed(random.nextInt())
                .setNbsMax(random.nextInt())
                .setReloads(random.nextInt())
                .setReaction(random.nextInt())
                .setErrorRangeMin(random.nextInt())
                .setErrorRangeMax(random.nextInt())
                .setModelPositionX(random.nextFloat())
                .setModelPositionY(random.nextFloat())
                .setModelPositionZ(random.nextFloat())
                .setFlashPositionX(random.nextFloat())
                .setFlashPositionY(random.nextFloat())
                .setFlashPositionZ(random.nextFloat())
                .setYakkyouPositionX(random.nextFloat())
                .setYakkyouPositionY(random.nextFloat())
                .setYakkyouPositionZ(random.nextFloat())
                .setYakkyouSpeedX(random.nextFloat())
                .setYakkyouSpeedY(random.nextFloat())
                .setBlazingMode(random.nextBoolean())
                .setScopeMode(ScopeMode.values()[random.nextInt(ScopeMode.values().length)])
                .setSize(random.nextFloat())
                .setSoundID(random.nextInt())
                .setSoundVolume(random.nextInt())
                .setSilencer(random.nextBoolean())
                .setWeaponP(ShootingStance.values()[random.nextInt(ShootingStance.values().length)])
                .setChangeWeapon(random.nextInt())
                .setBurst(random.nextInt());
    }

    public static Character generateRandomCharacter() {
        return new Character()
                .setTexture(CharacterTextureType.values()[random.nextInt(CharacterTextureType.values().length)])
                .setModel(CharacterModelType.values()[random.nextInt(CharacterModelType.values().length)])
                .setAiLevel(AILevel.values()[random.nextInt(AILevel.values().length)])
                .setWeapons(new ArrayList<>(Arrays.asList(random.nextInt(), random.nextInt())))
                .setType(CharacterType.values()[random.nextInt(CharacterType.values().length)]);
    }

    public static Config generateRandomConfig() {
        return new Config()
                .setTurnUp(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setTurnDown(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setTurnLeft(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setTurnRight(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setMoveForward(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setMoveBackward(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setMoveLeft(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setMoveRight(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setWalk(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setJump(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setReload(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setDropWeapon(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setZoom(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setFire(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setSwitchWeapon(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setWeapon1(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setWeapon2(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setFire(KeyCode.values()[random.nextInt(KeyCode.values().length)])
                .setMouseSensitivity(random.nextInt())
                .setBrightness(random.nextInt())
                .setWindowMode(WindowMode.values()[random.nextInt(WindowMode.values().length)])
                .setEnableSound(random.nextBoolean())
                .setEnableBlood(random.nextBoolean())
                .setInvertMouse(random.nextBoolean())
                .setFrameSkip(random.nextBoolean())
                .setAnotherGunsight(random.nextBoolean())
                .setName(generateRandomString(8));
    }
}
