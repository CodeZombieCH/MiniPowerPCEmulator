package ch.minipowerpc.emulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpc.emulator.DynamicSizeMemory;

public class DynamicSizeMemoryTest {
	private DynamicSizeMemory instance;

	@Before
	public void setUp() throws Exception {
		instance = new DynamicSizeMemory();
	}

	@Test
	public void testGet16Bit() {
		// Check first byte of memory, supposed to be 0 if properly initialized
		assertEquals((short)0b0, instance.get16Bit((short)0));
		
		assertEquals((short)0b0, instance.get16Bit((short)100));
	}

	@Test
	public void testSet16Bit() {
		instance.set16Bit((short)0, (short)0b1111111100000000);
		assertEquals((short)0b1111111100000000, instance.get16Bit((short)0));

		instance.set16Bit((short)2, (short)0b0000000011111111);
		assertEquals((short)0b0000000011111111, instance.get16Bit((short)2));
	}

	@Test
	public void testGetByte() {
		// Check first byte of memory, supposed to be 0 if properly initialized
		assertEquals((byte)0b0, instance.getByte((short)0));
		
		assertEquals((byte)0b0, instance.getByte((short)100));
	}

	@Test
	public void testSetByte() {
		// Store 10101010 at #100
		instance.setByte((short)100, (byte)0b10101010);
		assertEquals((byte)0b10101010, instance.getByte((short)100));
		
		// Store 01010101 at #101
		instance.setByte((short)101, (byte)0b01010101);
		assertEquals((byte)0b01010101, instance.getByte((short)101));
	}
}
