package com.github.dabasan.jxm.properties.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * Config reader
 *
 * @author Daba
 */
class ConfigReader {
    private Config config;

    public ConfigReader(InputStream is) throws IOException {
        config = new Config();

        // Read all bytes from a stream
        byte[] bin;
        try (var bis = new BufferedInputStream(is)) {
            bin = bis.readAllBytes();
        }

        var keyCodes = KeyCode.values();

        // Key code
        config.turnUp = keyCodes[bin[0]];
        config.turnDown = keyCodes[bin[1]];
        config.turnLeft = keyCodes[bin[2]];
        config.turnRight = keyCodes[bin[3]];
        config.moveForward = keyCodes[bin[4]];
        config.moveBackward = keyCodes[bin[5]];
        config.moveLeft = keyCodes[bin[6]];
        config.moveRight = keyCodes[bin[7]];
        config.walk = keyCodes[bin[8]];
        config.jump = keyCodes[bin[9]];
        config.reload = keyCodes[bin[10]];
        config.dropWeapon = keyCodes[bin[11]];
        config.zoom = keyCodes[bin[12]];
        config.fireMode = keyCodes[bin[13]];
        config.switchWeapon = keyCodes[bin[14]];
        config.weapon1 = keyCodes[bin[15]];
        config.weapon2 = keyCodes[bin[16]];
        config.fire = keyCodes[bin[17]];

        // Other config
        Function<Byte, Boolean> byteToBoolean = (b) -> {
            if (b == 0) {
                return false;
            } else {
                return true;
            }
        };
        config.mouseSensitivity = Byte.toUnsignedInt(bin[18]);
        config.windowMode = WindowMode.values()[bin[19]];
        config.enableSound = byteToBoolean.apply(bin[20]);
        config.enableBlood = byteToBoolean.apply(bin[21]);
        config.brightness = Byte.toUnsignedInt(bin[22]);
        config.invertMouse = byteToBoolean.apply(bin[23]);
        config.frameSkip = byteToBoolean.apply(bin[24]);
        config.anotherGunsight = byteToBoolean.apply(bin[25]);
        config.name = this.getNameFromBin(bin, 26);
    }

    private String getNameFromBin(byte[] bin, int start) {
        var nameBuffer = new byte[21];
        for (int i = 0; i < 20; i++) {
            nameBuffer[i] = bin[start + i];
        }
        nameBuffer[20] = 0;

        var name = new String(nameBuffer);

        int firstNullPos = 20;
        for (int i = 0; i < 20; i++) {
            if (name.charAt(i) == 0) {
                firstNullPos = i;
                break;
            }
        }

        return name.substring(0, firstNullPos);
    }

    public Config getConfig() {
        return config;
    }
}
