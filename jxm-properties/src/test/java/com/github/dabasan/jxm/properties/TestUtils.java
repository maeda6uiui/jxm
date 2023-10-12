package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.character.*;
import com.github.dabasan.jxm.properties.weapon.ScopeMode;
import com.github.dabasan.jxm.properties.weapon.ShootingStance;
import com.github.dabasan.jxm.properties.weapon.Weapon;

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
        var ret = new Weapon();

        ret.name = generateRandomString(10);
        ret.model = generateRandomString(20);
        ret.texture = generateRandomString(20);
        ret.attacks = random.nextInt();
        ret.penetration = random.nextInt();
        ret.blazings = random.nextInt();
        ret.speed = random.nextInt();
        ret.nbsMax = random.nextInt();
        ret.reloads = random.nextInt();
        ret.reaction = random.nextInt();
        ret.errorRangeMin = random.nextInt();
        ret.errorRangeMax = random.nextInt();
        ret.modelPositionX = random.nextFloat();
        ret.modelPositionY = random.nextFloat();
        ret.modelPositionZ = random.nextFloat();
        ret.flashPositionX = random.nextFloat();
        ret.flashPositionY = random.nextFloat();
        ret.flashPositionZ = random.nextFloat();
        ret.yakkyouPositionX = random.nextFloat();
        ret.yakkyouPositionY = random.nextFloat();
        ret.yakkyouPositionZ = random.nextFloat();
        ret.yakkyouSpeedX = random.nextFloat();
        ret.yakkyouSpeedY = random.nextFloat();
        ret.blazingMode = random.nextBoolean();
        ret.scopeMode = ScopeMode.values()[random.nextInt(ScopeMode.values().length)];
        ret.size = random.nextFloat();
        ret.soundID = random.nextInt();
        ret.soundVolume = random.nextInt();
        ret.silencer = random.nextBoolean();
        ret.weaponP = ShootingStance.values()[random.nextInt(ShootingStance.values().length)];
        ret.changeWeapon = random.nextInt();
        ret.burst = random.nextInt();

        return ret;
    }

    public static Character generateRandomCharacter() {
        var ret = new Character();

        ret.texture = CharacterTextureType.values()[random.nextInt(CharacterTextureType.values().length)];
        ret.model = CharacterModelType.values()[random.nextInt(CharacterModelType.values().length)];
        ret.aiLevel = AILevel.values()[random.nextInt(AILevel.values().length)];
        ret.weapons = new ArrayList<>(Arrays.asList(random.nextInt(), random.nextInt()));
        ret.type = CharacterType.values()[random.nextInt(CharacterType.values().length)];

        return ret;
    }
}
