package ch.minipowerpc.emulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpc.emulator.Registers;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class RegistersTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInitialize() {
		Registers registers = new Registers();
		registers.initialize();
		
		assertEquals(0b0, registers.get(NamedRegister.Accu));
		assertEquals(0b0, registers.get(NamedRegister.R1));
		assertEquals(0b0, registers.get(NamedRegister.R2));
		assertEquals(0b0, registers.get(NamedRegister.R3));
	}

	@Test
	public void testGet() {
		Registers registers = new Registers();
		registers.initialize();
		
		short expected = (short)0b10101010;
		registers.set(NamedRegister.R1, expected);
		assertEquals(expected, registers.get(NamedRegister.R1));
		
		expected = (short)0b01010101;
		registers.set(NamedRegister.R1, expected);
		assertEquals(expected, registers.get(NamedRegister.R1));
	}

	@Test
	public void testSet() {
		Registers registers = new Registers();
		registers.initialize();
		
		short expected = (short)0b10101010;
		registers.set(NamedRegister.Accu, expected);
		assertEquals(expected, registers.get(NamedRegister.Accu));
		
		expected = (short)0b10101010;
		registers.set(NamedRegister.R1, expected);
		assertEquals(expected, registers.get(NamedRegister.R1));

		expected = (short)0b10101010;
		registers.set(NamedRegister.R2, expected);
		assertEquals(expected, registers.get(NamedRegister.R2));

		expected = (short)0b10101010;
		registers.set(NamedRegister.R3, expected);
		assertEquals(expected, registers.get(NamedRegister.R3));		
	}
}
