package com.github.dabasan.jxm.bintools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * Byte functions
 *
 * @author maeda6uiui
 */
public class ByteFunctions {
    /**
     * Converts a 4-byte byte array to a float value.
     *
     * @param b byte array (big-endian representation)
     * @return float value
     */
    public static float bytesToFloat(byte[] b) {
        if (b.length != 4) {
            throw new IllegalArgumentException(String.format("Array must have length of 4, got %d",b.length));
        }

        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
        buffer.put(b);
        buffer.flip();

        return buffer.getFloat();
    }

    /**
     * Converts a 4-byte byte array to a float value.
     *
     * @param b byte array (little-endian representation)
     * @return float value
     */
    public static float bytesToFloatLE(byte[] b) {
        if (b.length != 4) {
            throw new IllegalArgumentException(String.format("Array must have length of 4, got %d",b.length));
        }

        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(b);
        buffer.flip();

        return buffer.getFloat();
    }

    /**
     * Converts a float value to a 4-byte byte array.
     *
     * @param f float value
     * @return 4-byte byte array (big-endian representation)
     */
    public static byte[] floatToBytes(float f) {
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(f);
        return buffer.array();
    }

    /**
     * Converts a float value to a 4-byte byte array.
     *
     * @param f float value
     * @return 4-byte byte array (little-endian representation)
     */
    public static byte[] floatToBytesLE(float f) {
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(f);
        return buffer.array();
    }

    /**
     * Converts a 2-byte byte array to a short value.
     *
     * @param b 2-byte byte array (big-endian representation)
     * @return short value
     */
    public static short bytesToShort(byte[] b) {
        if (b.length != 2) {
            throw new IllegalArgumentException(String.format("Array must have length of 2, got %d",b.length));
        }

        ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN);
        buffer.put(b);
        buffer.flip();

        return buffer.getShort();
    }

    /**
     * Converts a 2-byte byte array to a short value.
     *
     * @param b 2-byte byte array (little-endian representation)
     * @return short value
     */
    public static short bytesToShortLE(byte[] b) {
        if (b.length != 2) {
            throw new IllegalArgumentException(String.format("Array must have length of 2, got %d",b.length));
        }

        ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(b);
        buffer.flip();

        return buffer.getShort();
    }

    /**
     * Converts a short value to a 2-byte byte array.
     *
     * @param s short value
     * @return 2-byte byte array (big-endian representation)
     */
    public static byte[] shortToBytes(short s) {
        ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort(s);
        return buffer.array();
    }

    /**
     * Converts a short value to a 2-byte byte array.
     *
     * @param s short value
     * @return 2-byte byte array (little-endian representation)
     */
    public static byte[] shortToBytesLE(short s) {
        ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(s);
        return buffer.array();
    }

    /**
     * Converts a 2-byte byte array to an unsigned short value.
     *
     * @param b 2-byte byte array (big-endian representation)
     * @return unsigned short value
     */
    public static int bytesToUnsignedShort(byte[] b) {
        if (b.length != 2) {
            throw new IllegalArgumentException(String.format("Array must have length of 2, got %d",b.length));
        }

        int first = Byte.toUnsignedInt(b[0]);
        int second = Byte.toUnsignedInt(b[1]);

        int ret = (first << 8) + second;

        return ret;
    }

    /**
     * Converts a 2-byte byte array to an unsigned short value.
     *
     * @param b 2-byte byte array (little-endian representation)
     * @return unsigned short value
     */
    public static int bytesToUnsignedShortLE(byte[] b) {
        if (b.length != 2) {
            throw new IllegalArgumentException(String.format("Array must have length of 2, got %d",b.length));
        }

        int first = Byte.toUnsignedInt(b[1]);
        int second = Byte.toUnsignedInt(b[0]);

        int ret = (first << 8) + second;

        return ret;
    }

    /**
     * Converts an unsigned short value to a 2-byte byte array.
     *
     * @param s unsigned short value
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
     * @param s unsigned short value
     * @return 2-byte byte array (little-endian representation)
     */
    public static byte[] unsignedShortToBytesLE(int s) {
        byte[] b = new byte[2];

        b[1] = (byte) (s >> 8);
        b[0] = (byte) s;

        return b;
    }

    /**
     * Adds a byte representation of a float value to a list.
     *
     * @param bin   list to add the value to
     * @param value float value
     */
    public static void addFloatToBinLE(List<Byte> bin, float value) {
        byte[] buffer = ByteFunctions.floatToBytesLE(value);
        for (int i = 0; i < 4; i++) {
            bin.add(buffer[i]);
        }
    }

    /**
     * Adds a byte representation of a short value to a list.
     *
     * @param bin   list to add the value to
     * @param value short value
     */
    public static void addShortToBinLE(List<Byte> bin, short value) {
        byte[] buffer = ByteFunctions.shortToBytesLE(value);
        for (int i = 0; i < 2; i++) {
            bin.add(buffer[i]);
        }
    }

    /**
     * Adds a byte representation of an unsigned short value to a list.
     *
     * @param bin   list to add the value to
     * @param value unsigned short value
     */
    public static void addUnsignedShortToBinLE(List<Byte> bin, int value) {
        byte[] buffer = ByteFunctions.unsignedShortToBytesLE(value);
        for (int i = 0; i < 2; i++) {
            bin.add(buffer[i]);
        }
    }

    /**
     * Sets a byte representation of a float value in a list.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value float value
     */
    public static void setFloatToBinLE(List<Byte> bin, int pos, float value) {
        byte[] buffer = ByteFunctions.floatToBytesLE(value);
        for (int i = 0; i < 4; i++) {
            bin.set(pos + i, buffer[i]);
        }
    }

    /**
     * Sets a byte representation of a short value in a list.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value short value
     */
    public static void setShortToBinLE(List<Byte> bin, int pos, short value) {
        byte[] buffer = ByteFunctions.shortToBytesLE(value);
        for (int i = 0; i < 2; i++) {
            bin.set(pos + i, buffer[i]);
        }
    }

    /**
     * Sets a byte representation of an unsigned short value in a list.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value unsigned short value
     */
    public static void setUnsignedShortToBinLE(List<Byte> bin, int pos, int value) {
        byte[] buffer = ByteFunctions.unsignedShortToBytesLE(value);
        for (int i = 0; i < 2; i++) {
            bin.set(pos + i, buffer[i]);
        }
    }

    /**
     * Sets a byte representation of a float value in an array.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value float value
     */
    public static void setFloatToBinLE(byte[] bin, int pos, float value) {
        byte[] buffer = ByteFunctions.floatToBytesLE(value);
        System.arraycopy(buffer, 0, bin, pos + 0, 4);
    }

    /**
     * Sets a byte representation of a short value in an array.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value short value
     */
    public static void setShortToBinLE(byte[] bin, int pos, short value) {
        byte[] buffer = ByteFunctions.shortToBytesLE(value);
        System.arraycopy(buffer, 0, bin, pos + 0, 2);
    }

    /**
     * Sets a byte representation of an unsigned short value in an array.
     *
     * @param bin   list to set the value in
     * @param pos   position to set the value
     * @param value unsigned short value
     */
    public static void setUnsignedShortToBinLE(byte[] bin, int pos, int value) {
        byte[] buffer = ByteFunctions.unsignedShortToBytesLE(value);
        System.arraycopy(buffer, 0, bin, pos + 0, 2);
    }

    /**
     * Returns a float value from a byte representation.
     *
     * @param bin array to get the value from
     * @param pos position to get the value
     * @return float value
     */
    public static float getFloatFromBinLE(byte[] bin, int pos) {
        byte[] buffer = new byte[]{bin[pos], bin[pos + 1], bin[pos + 2], bin[pos + 3]};
        float ret = ByteFunctions.bytesToFloatLE(buffer);

        return ret;
    }

    /**
     * Returns a short value from a byte representation.
     *
     * @param bin array to get the value from
     * @param pos position to get the value
     * @return short value
     */
    public static short getShortFromBinLE(byte[] bin, int pos) {
        byte[] buffer = new byte[]{bin[pos], bin[pos + 1]};
        short ret = ByteFunctions.bytesToShortLE(buffer);

        return ret;
    }

    /**
     * Returns an unsigned short value from a byte representation.
     *
     * @param bin array to get the value from
     * @param pos position to get the value
     * @return unsigned short value
     */
    public static int getUnsignedShortFromBinLE(byte[] bin, int pos) {
        byte[] buffer = new byte[]{bin[pos], bin[pos + 1]};
        int ret = ByteFunctions.bytesToUnsignedShortLE(buffer);

        return ret;
    }
}
