package ch.minipowerpc.emulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpc.emulator.Emulator;
import ch.minipowerpc.emulator.IMemory;

public class EmulatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadProgram() throws Exception {
		Emulator instance = new Emulator();
		instance.loadProgram("EmulatorTest");
		
		IMemory memory = instance.getMemory();
		assertEquals(0b0000000100000000, memory.get16Bit((short)100) & 0b1111111100000000);
	}

	@Test
	public void testLoadMemory() throws Exception {
		Emulator instance = new Emulator();
		instance.loadMemory("EmulatorTest");
		
		IMemory memory = instance.getMemory();
		assertEquals(100, memory.get16Bit((short)500));
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}
}
