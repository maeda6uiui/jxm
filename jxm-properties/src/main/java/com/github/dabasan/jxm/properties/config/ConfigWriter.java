package com.github.dabasan.jxm.properties.config;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Config writer
 * 
 * @author Daba
 *
 */
class ConfigWriter {
	public ConfigWriter() {

	}

	public void write(OutputStream os, Config config) throws IOException {
		var bin = new ArrayList<Byte>();

		bin.add((byte) config.getTurnUp().ordinal());
		bin.add((byte) config.getTurnDown().ordinal());
		bin.add((byte) config.getTurnLeft().ordinal());
		bin.add((byte) config.getTurnRight().ordinal());
		bin.add((byte) config.getMoveForward().ordinal());
		bin.add((byte) config.getMoveBackward().ordinal());
		bin.add((byte) config.getMoveLeft().ordinal());
		bin.add((byte) config.getMoveRight().ordinal());
		bin.add((byte) config.getWalk().ordinal());
		bin.add((byte) config.getJump().ordinal());
		bin.add((byte) config.getReload().ordinal());
		bin.add((byte) config.getDropWeapon().ordinal());
		bin.add((byte) config.getZoom().ordinal());
		bin.add((byte) config.getFireMode().ordinal());
		bin.add((byte) config.getSwitchWeapon().ordinal());
		bin.add((byte) config.getWeapon1().ordinal());
		bin.add((byte) config.getWeapon2().ordinal());
		bin.add((byte) config.getFire().ordinal());

		// Other config
		Function<Boolean, Byte> booleanToByte = (b) -> {
			if (b == false) {
				return 0;
			} else {
				return 1;
			}
		};

		bin.add((byte) config.getMouseSensitivity());
		bin.add((byte) config.getWindowMode().ordinal());
		bin.add(booleanToByte.apply(config.isEnableSound()));
		bin.add(booleanToByte.apply(config.isEnableBlood()));
		bin.add((byte) config.getMouseSensitivity());
		bin.add(booleanToByte.apply(config.isInvertMouse()));
		bin.add(booleanToByte.apply(config.isFrameSkip()));
		bin.add(booleanToByte.apply(config.isAnotherGunsight()));
		this.addNameToBin(bin, config.getName());

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
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
