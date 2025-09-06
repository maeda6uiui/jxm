package com.github.maeda6uiui.jxm.mif;

import java.util.ArrayList;
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

    public static String generateRandomString(int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            char c = RANDOM_CHARACTERS.charAt(random.nextInt(RANDOM_CHARACTERS.length()));
            ret += c;
        }

        return ret;
    }

    public static MissionInfo generateRandomMissionInfo() {
        var briefingText = new ArrayList<String>();
        int numLines = random.nextInt(1, 10);
        for (int i = 0; i < numLines; i++) {
            String line = generateRandomString(random.nextInt(10, 20));
            briefingText.add(line);
        }

        return new MissionInfo()
                .setMissionTitle(generateRandomString(5))
                .setMissionFullname(generateRandomString(15))
                .setPathnameOfBlock(generateRandomString(20))
                .setPathnameOfPoint(generateRandomString(20))
                .setSkyType(SkyType.values()[random.nextInt(SkyType.values().length)])
                .setPathnameOfImage1(generateRandomString(20))
                .setPathnameOfImage2(generateRandomString(20))
                .setPathnameOfObj(generateRandomString(20))
                .setExtraCollision(random.nextBoolean())
                .setDarkScreen(random.nextBoolean())
                .setBriefingText(briefingText);
    }
}
