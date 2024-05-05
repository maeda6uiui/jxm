package com.github.dabasan.jxm.properties.config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Config writer
 *
 * @author maeda6uiui
 */
class ConfigWriter {
    public void write(OutputStream os, Config config) throws IOException {
        var bin = new ArrayList<Byte>();

        bin.add((byte) config.turnUp.ordinal());
        bin.add((byte) config.turnDown.ordinal());
        bin.add((byte) config.turnLeft.ordinal());
        bin.add((byte) config.turnRight.ordinal());
        bin.add((byte) config.moveForward.ordinal());
        bin.add((byte) config.moveBackward.ordinal());
        bin.add((byte) config.moveLeft.ordinal());
        bin.add((byte) config.moveRight.ordinal());
        bin.add((byte) config.walk.ordinal());
        bin.add((byte) config.jump.ordinal());
        bin.add((byte) config.reload.ordinal());
        bin.add((byte) config.dropWeapon.ordinal());
        bin.add((byte) config.zoom.ordinal());
        bin.add((byte) config.fireMode.ordinal());
        bin.add((byte) config.switchWeapon.ordinal());
        bin.add((byte) config.weapon1.ordinal());
        bin.add((byte) config.weapon2.ordinal());
        bin.add((byte) config.fire.ordinal());

        //Other config
        Function<Boolean, Byte> booleanToByte = (b) -> {
            if (!b) {
                return (byte) 0;
            } else {
                return (byte) 1;
            }
        };
        bin.add((byte) config.mouseSensitivity);
        bin.add((byte) config.windowMode.ordinal());
        bin.add(booleanToByte.apply(config.enableSound));
        bin.add(booleanToByte.apply(config.enableBlood));
        bin.add((byte) config.mouseSensitivity);
        bin.add(booleanToByte.apply(config.invertMouse));
        bin.add(booleanToByte.apply(config.frameSkip));
        bin.add(booleanToByte.apply(config.anotherGunsight));
        this.addNameToBin(bin, config.name);

        for (byte b : bin) {
            os.write(b);
        }
    }

    private void addNameToBin(List<Byte> bin, String name) {
        var nameBuffer = name.getBytes();
        nameBuffer = Arrays.copyOf(nameBuffer, 21);
        nameBuffer[20] = 0;

        for (int i = 0; i < 21; i++) {
            bin.add(nameBuffer[i]);
        }
    }
}
