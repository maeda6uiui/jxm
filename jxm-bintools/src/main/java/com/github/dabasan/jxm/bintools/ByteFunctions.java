package com.github.dabasan.jxm.bintools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * Byte functions
 * 
 * @author Daba
 *
 */
public class ByteFunctions {
	/**
	 * Converts a 4-byte byte array to a float value.
	 * 
	 * @param b
	 *            Byte array (big-endian representation)
	 * @return Float value
	 */
	public static float bytesToFloat(byte[] b) {
		if (b.length != 4) {
			return 0.0f;
		}

		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
		buffer.put(b);
		buffer.flip();

		return buffer.getFloat();
	}
	/**
	 * Converts a 4-byte byte array to a float value.
	 * 
	 * @param b
	 *            Byte array (little-endian representation)
	 * @return Float value
	 */
	public static float bytesToFloatLE(byte[] b) {
		if (b.length != 4) {
			return 0.0f;
		}

		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
		buffer.put(b);
		buffer.flip();

		return buffer.getFloat();
	}
	/**
	 * Converts a float value to a 4-byte byte array.
	 * 
	 * @param f
	 *            Float value
	 * @return 4-byte byte array (big-endian representation)
	 */
	public static byte[] floatToBytes(float f) {
		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(f);
		return buffer.array();
	}
	/**
	 * Converts a float value to a 4-byte byte array.
	 * 
	 * @param f
	 *            Float value
	 * @return 4-byte byte array (little-endian representation)
	 */
	public static byte[] floatToBytesLE(float f) {
		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(f);
		return buffer.array();
	}

	/**
	 * Converts a 2-byte byte array to a short value.
	 * 
	 * @param b
	 *            2-byte byte array (big-endian representation)
	 * @return Short value
	 */
	public static short bytesToShort(byte[] b) {
		if (b.length != 2) {
			return 0;
		}

		ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN);
		buffer.put(b);
		buffer.flip();

		return buffer.getShort();
	}
	/**
	 * Converts a 2-byte byte array to a short value.
	 * 
	 * @param b
	 *            2-byte byte array (little-endian representation)
	 * @return Short value
	 */
	public static short bytesToShortLE(byte[] b) {
		if (b.length != 2) {
			return 0;
		}

		ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
		buffer.put(b);
		buffer.flip();

		return buffer.getShort();
	}
	/**
	 * Converts a short value to a 2-byte byte array.
	 * 
	 * @param s
	 *            Short value
	 * @return 2-byte byte array (big-endian representation)
	 */
	public static byte[] shortToBytes(short s) {
		ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort(s);
		return buffer.array();
	}
	/**
	 * Converts a short value to a 2-byte byte array.
	 * 
	 * @param s
	 *            Short value
	 * @return 2-byte byte array (little-endian representation)
	 */
	public static byte[] shortToBytesLE(short s) {
		ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(s);
		return buffer.array();
	}

	/**
	 * Converts a 2-byte byte array to an unsigned short value.
	 * 
	 * @param b
	 *            2-byte byte array (big-endian representation)
	 * @return Unsigned short value
	 */
	public static int bytesToUnsignedShort(byte[] b) {
		if (b.length != 2) {
			return 0;
		}

		int first = Byte.toUnsignedInt(b[0]);
		int second = Byte.toUnsignedInt(b[1]);

		int ret = (first << 8) + second;

		return ret;
	}
	/**
	 * Converts a 2-byte byte array to an unsigned short value.
	 * 
	 * @param b
	 *            2-byte byte array (little-endian representation)
	 * @return Unsigned short value
	 */
	public static int bytesToUnsignedShortLE(byte[] b) {
		if (b.length != 2) {
			return 0;
		}

		int first = Byte.toUnsignedInt(b[1]);
		int second = Byte.toUnsignedInt(b[0]);

		int ret = (first << 8) + second;

		return ret;
	}
	/**
	 * Converts an unsigned short value to a 2-byte byte array.
	 * 
	 * @param s
	 *            Unsigned short value
	 * @return 2-byte byte array (big-endian representation)
	 */
	public static byte[] unsignedShortToBytes(int s) {
		byte[] b = new byte[2];

		b[0] = (byte) (s >> 8);
		b[1] = (byte) s;

		return b;
	}
	/**
	 * Converts an unsigned short value to a 2-byte byte array.
	 * 
	 * @see ushort_to_byte(int)
	 * @param s
	 *            Unsigned short value
	 * @return 2-byte byte array (little-endian representation)
	 */
	public static byte[] unsignedShortToBytesLE(int s) {
		byte[] b = new byte[2];

		b[1] = (byte) (s >> 8);
		b[0] = (byte) s;

		return b;
	}

	/**
	 * Adds a byte representation of a float value into a list.
	 * 
	 * @param bin
	 *            List
	 * @param value
	 *            Float value
	 */
	public static void addFloatValueToBinLE(List<Byte> bin, float value) {
		byte[] buffer = ByteFunctions.floatToBytesLE(value);
		for (int i = 0; i < 4; i++) {
			bin.add(buffer[i]);
		}
	}
	/**
	 * Adds a byte representation of a short value into a list.
	 * 
	 * @param bin
	 *            List
	 * @param value
	 *            Short value
	 */
	public static void addShortValueToBinLE(List<Byte> bin, short value) {
		byte[] buffer = ByteFunctions.shortToBytesLE(value);
		for (int i = 0; i < 2; i++) {
			bin.add(buffer[i]);
		}
	}
	/**
	 * Adds a byte representation of an unsigned short value into a list.
	 * 
	 * @param bin
	 *            List
	 * @param value
	 *            Unsigned short value
	 */
	public static void addUnsignedShortValueToBinLE(List<Byte> bin, int value) {
		byte[] buffer = ByteFunctions.unsignedShortToBytesLE(value);
		for (int i = 0; i < 2; i++) {
			bin.add(buffer[i]);
		}
	}

	/**
	 * Sets a byte representation of a float value in a list.
	 * 
	 * @param bin
	 *            List
	 * @param pos
	 *            Position
	 * @param value
	 *            Float value
	 */
	public static void setFloatValueToBinLE(List<Byte> bin, int pos, float value) {
		byte[] buffer = ByteFunctions.floatToBytesLE(value);
		for (int i = 0; i < 4; i++) {
			bin.set(pos + i, buffer[i]);
		}
	}
	/**
	 * Sets a byte representation of a short value in a list.
	 * 
	 * @param bin
	 *            List
	 * @param pos
	 *            Position
	 * @param value
	 *            Short value
	 */
	public static void setShortValueToBinLE(List<Byte> bin, int pos, short value) {
		byte[] buffer = ByteFunctions.shortToBytesLE(value);
		for (int i = 0; i < 2; i++) {
			bin.set(pos + i, buffer[i]);
		}
	}
	/**
	 * Sets a byte representation of an unsigned short value in a list.
	 * 
	 * @param bin
	 *            List
	 * @param pos
	 *            Position
	 * @param value
	 *            Unsigned short value
	 */
	public static void setUnsignedShortValueToBinLE(List<Byte> bin, int pos, int value) {
		byte[] buffer = ByteFunctions.unsignedShortToBytesLE(value);
		for (int i = 0; i < 2; i++) {
			bin.set(pos + i, buffer[i]);
		}
	}
}
