package com.github.dabasan.jxm.properties.xops;

/**
 * XOPS functions
 * 
 * @author Daba
 *
 */
class XOPSFunctions {
	private static final int XOPS_096_OR_096T_FILE_SIZE = 458752;
	private static final int XOPS097FT_FILE_SIZE = 462848;
	private static final int XOPS0975T_FILE_SIZE = 552960;
	private static final int XOPSOLT18F2_FILE_SIZE = 495616;
	private static final int XOPSOLT19F2_FILE_SIZE = 598016;

	public static XOPSVersion getXOPSVersion(byte[] bin) {
		XOPSVersion version = XOPSVersion.XOPS096;

		int fileSize = bin.length;

		if (fileSize == XOPS_096_OR_096T_FILE_SIZE) {
			byte[] b = new byte[3];
			b[0] = bin[0x00000100];
			b[1] = bin[0x00000101];
			b[2] = bin[0x00000102];

			int[] ub = new int[3];
			ub[0] = Byte.toUnsignedInt(b[0]);
			ub[1] = Byte.toUnsignedInt(b[1]);
			ub[2] = Byte.toUnsignedInt(b[2]);

			if (ub[0] == 0xB7 && ub[1] == 0xBF && ub[2] == 0x54) {
				version = XOPSVersion.XOPS096;
			} else if (ub[0] == 0x97 && ub[1] == 0x7B && ub[2] == 0xAA) {
				version = XOPSVersion.XOPS096T;
			}
		} else if (fileSize == XOPS097FT_FILE_SIZE) {
			version = XOPSVersion.XOPS097FT;
		} else if (fileSize == XOPS0975T_FILE_SIZE) {
			version = XOPSVersion.XOPS0975T;
		} else if (fileSize == XOPSOLT18F2_FILE_SIZE) {
			version = XOPSVersion.XOPSOLT18F2;
		} else if (fileSize == XOPSOLT19F2_FILE_SIZE) {
			version = XOPSVersion.XOPSOLT19F2;
		}

		return version;
	}
}
