package com.github.dabasan.jxm.properties.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * Config reader
 * 
 * @author Daba
 *
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
		config.setTurnUp(keyCodes[bin[0]]);
		config.setTurnDown(keyCodes[bin[1]]);
		config.setTurnLeft(keyCodes[bin[2]]);
		config.setTurnRight(keyCodes[bin[3]]);
		config.setMoveForward(keyCodes[bin[4]]);
		config.setMoveBackward(keyCodes[bin[5]]);
		config.setMoveLeft(keyCodes[bin[6]]);
		config.setMoveRight(keyCodes[bin[7]]);
		config.setWalk(keyCodes[bin[8]]);
		config.setJump(keyCodes[bin[9]]);
		config.setReload(keyCodes[bin[10]]);
		config.setDropWeapon(keyCodes[bin[11]]);
		config.setZoom(keyCodes[bin[12]]);
		config.setFireMode(keyCodes[bin[13]]);
		config.setSwitchWeapon(keyCodes[bin[14]]);
		config.setWeapon1(keyCodes[bin[15]]);
		config.setWeapon2(keyCodes[bin[16]]);
		config.setFire(keyCodes[bin[17]]);

		// Other config
		Function<Byte, Boolean> byteToBoolean = (b) -> {
			if (b == 0) {
				return false;
			} else {
				return true;
			}
		};

		config.setMouseSensitivity(Byte.toUnsignedInt(bin[18]));
		config.setWindowMode(WindowMode.values()[bin[19]]);
		config.setEnableSound(byteToBoolean.apply(bin[20]));
		config.setEnableBlood(byteToBoolean.apply(bin[21]));
		config.setBrightness(Byte.toUnsignedInt(bin[22]));
		config.setInvertMouse(byteToBoolean.apply(bin[23]));
		config.setFrameSkip(byteToBoolean.apply(bin[24]));
		config.setAnotherGunsight(byteToBoolean.apply(bin[25]));
		config.setName(this.getNameFromBin(bin, 26));
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
