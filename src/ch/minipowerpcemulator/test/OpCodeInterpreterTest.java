package ch.minipowerpcemulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpcemulator.OpCodeInterpreter;

public class OpCodeInterpreterTest {
	private OpCodeInterpreter instance;

	@Before
	public void setUp() throws Exception {
		instance = new OpCodeInterpreter();
	}
	
	@Test
	public void test() {
		// Test CLR
		instance.interpret((short) 0b0000001010000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("CLR 0", instance.getCommand());
		// Test CLR
		instance.interpret((short) 0b0000111011111111);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("CLR 3", instance.getCommand());
		// Test ADD
		instance.interpret((short) 0b0000011110101010);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("ADD 1", instance.getCommand());
		// Test ADDD
		instance.interpret((short) 0b1000011111111111);
		assertEquals((short)0b000011111111111, instance.getNumber());
		assertEquals("ADDD 2047", instance.getCommand());
		// Test INC
		instance.interpret((short) 0b0000000101010101);
		assertEquals("INC", instance.getCommand());
		// Test DEC
		instance.interpret((short) 0b0000010001010101);
		assertEquals("DEC", instance.getCommand());
		// Test LWDD
		instance.interpret((short) 0b0101100011001100);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals((short)0b0011001100, instance.getAddress());
		assertEquals("LWDD 2, 204", instance.getCommand());
		// Test LWDD
		instance.interpret((short) 0b0111010010101100);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals((short)0b0010101100, instance.getAddress());
		assertEquals("SWDD 1, 172", instance.getCommand());
		// Test SRA
		instance.interpret((short) 0b0000010100101010);
		assertEquals("SRA", instance.getCommand());
		// Test SLA
		instance.interpret((short) 0b0000100000101111);
		assertEquals("SLA", instance.getCommand());
		// Test SRL
		instance.interpret((short) 0b0000100100101000);
		assertEquals("SRL", instance.getCommand());
		// Test SLL
		instance.interpret((short) 0b0000110001101010);
		assertEquals("SLL", instance.getCommand());
		// Test AND
		instance.interpret((short) 0b0000111000000000);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("AND 3", instance.getCommand());
		// Test OR
		instance.interpret((short) 0b0000101100011000);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("OR 2", instance.getCommand());
		// Test NOT
		instance.interpret((short) 0b0000000010101010);
		assertEquals("NOT", instance.getCommand());
		// Test BZ
		instance.interpret((short) 0b0001011000011111);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("BZ 1", instance.getCommand());
		// Test BNZ
		instance.interpret((short) 0b0001100100011001);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("BNZ 2", instance.getCommand());
		// Test BC
		instance.interpret((short) 0b0001111111111111);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("BC 3", instance.getCommand());
		// Test BC
		instance.interpret((short) 0b0001000000000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("B 0", instance.getCommand());
		// Test BZD
		instance.interpret((short) 0b0011000000111000);
		assertEquals((short)0b0000111000, instance.getAddress());
		assertEquals("BZD 56", instance.getCommand());
		// Test BNZD
		instance.interpret((short) 0b0010110000111011);
		assertEquals((short)0b0000111011, instance.getAddress());
		assertEquals("BNZD 59", instance.getCommand());
		// Test BCD
		instance.interpret((short) 0b0011111111111111);
		assertEquals((short)0b1111111111, instance.getAddress());
		assertEquals("BCD 1023", instance.getCommand());
		// Test BD
		instance.interpret((short) 0b0010000010101010);
		assertEquals((short)0b0010101010, instance.getAddress());
		assertEquals("BD 170", instance.getCommand());
		// Test END
		instance.interpret((short) 0b0000000000000000);
		assertEquals("END", instance.getCommand());
	}

}
