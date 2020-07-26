package com.github.dabasan.jxm.bintools;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ByteFunctions
 * 
 * @author Daba
 *
 */
public class ByteFunctionsTest {
	@Test
	public void testBytesToFloat() {
		byte[] b = new byte[]{(byte) 0x40, (byte) 0x48, (byte) 0xF5, (byte) 0xC2};
		assertEquals(3.14f, ByteFunctions.bytesToFloat(b), 1.0E-3f);
	}
	@Test
	public void testBytesToFloatLE() {
		byte[] b = new byte[]{(byte) 0xC2, (byte) 0xF5, (byte) 0x48, (byte) 0x40};
		assertEquals(3.14f, ByteFunctions.bytesToFloatLE(b), 1.0E-3f);
	}
	@Test
	public void testFloatToBytes() {
		float f1 = 3.14f;
		byte[] b1 = ByteFunctions.floatToBytes(f1);
		float f2 = ByteFunctions.bytesToFloat(b1);

		assertEquals(f1, f2, 1.0E-3f);
	}
	@Test
	public void testFloatToBytesLE() {
		float f1 = 3.14f;
		byte[] b1 = ByteFunctions.floatToBytesLE(f1);
		float f2 = ByteFunctions.bytesToFloatLE(b1);

		assertEquals(f1, f2, 1.0E-3f);
	}

	@Test
	public void testBytesToShort() {
		byte[] b = new byte[]{0x30, 0x39};
		assertEquals(12345, ByteFunctions.bytesToShort(b));
	}
	@Test
	public void testBytesToShortLE() {
		byte[] b = new byte[]{0x39, 0x30};
		assertEquals(12345, ByteFunctions.bytesToShortLE(b));
	}
	@Test
	public void testShortToBytes() {
		short s1 = 12345;
		byte[] b1 = ByteFunctions.shortToBytes(s1);
		short s2 = ByteFunctions.bytesToShort(b1);

		assertEquals(s1, s2);
	}
	@Test
	public void testShortToBytesLE() {
		short s1 = 12345;
		byte[] b1 = ByteFunctions.shortToBytesLE(s1);
		short s2 = ByteFunctions.bytesToShortLE(b1);

		assertEquals(s1, s2);
	}

	@Test
	public void testBytesToUnsignedShort() {
		byte[] b = new byte[]{(byte) 0xC3, (byte) 0x50};
		assertEquals(50000, ByteFunctions.bytesToUnsignedShort(b));
	}
	@Test
	public void testBytesToUnsignedShortLE() {
		byte[] b = new byte[]{(byte) 0x50, (byte) 0xC3};
		assertEquals(50000, ByteFunctions.bytesToUnsignedShortLE(b));
	}
	@Test
	public void testUnsignedShortToBytes() {
		int us1 = 50000;
		byte[] b1 = ByteFunctions.unsignedShortToBytes(us1);
		int us2 = ByteFunctions.bytesToUnsignedShort(b1);

		assertEquals(us1, us2);
	}
	@Test
	public void testUnsignedShortToBytesLE() {
		int us1 = 50000;
		byte[] b1 = ByteFunctions.unsignedShortToBytesLE(us1);
		int us2 = ByteFunctions.bytesToUnsignedShortLE(b1);

		assertEquals(us1, us2);
	}
}
