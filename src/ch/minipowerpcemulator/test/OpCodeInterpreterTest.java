package ch.minipowerpcemulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpcemulator.ALU;
import ch.minipowerpcemulator.DynamicSizeMemory;
import ch.minipowerpcemulator.IALU;
import ch.minipowerpcemulator.OpCodeInterpreter;
import ch.minipowerpcemulator.Registers;

public class OpCodeInterpreterTest {
	private OpCodeInterpreter instance;
	private IALU alu;

	@Before
	public void setUp() throws Exception {
		alu = new ALU(new DynamicSizeMemory(), new Registers());
		instance = new OpCodeInterpreter(alu);
	}

	@Test
	public void testCLR() {
		// Test CLR
		instance.interpret((short) 0b0000001010000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("CLR 0", instance.getCommand());
		// Test CLR
		instance.interpret((short) 0b0000111011111111);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("CLR 3", instance.getCommand());
	}
	
	@Test
	public void testADD() {
		// Test ADD
		instance.interpret((short) 0b0000011110101010);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("ADD 1", instance.getCommand());
	}
	
	@Test
	public void testADDD() {
		// Test ADDD
		instance.interpret((short) 0b1000011111111111);
		assertEquals((short)0b000011111111111, instance.getNumber());
		assertEquals("ADDD 2047", instance.getCommand());
	}
	
	@Test
	public void testINC() {
		// Test INC
		instance.interpret((short) 0b0000000101010101);
		assertEquals("INC", instance.getCommand());
	}
	
	@Test
	public void testDEC() {
		// Test DEC
		instance.interpret((short) 0b0000010001010101);
		assertEquals("DEC", instance.getCommand());
	}
	
	@Test
	public void testLWDD() {
		// Test LWDD
		instance.interpret((short) 0b0101100011001100);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals((short)0b0011001100, instance.getAddress());
		assertEquals("LWDD 2, 204", instance.getCommand());
	}
	
	@Test
	public void testSWDD() {
		// Test SWDD
		instance.interpret((short) 0b0111010010101100);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals((short)0b0010101100, instance.getAddress());
		assertEquals("SWDD 1, 172", instance.getCommand());
	}
	
	@Test
	public void testSRA() {
		// Test SRA
		instance.interpret((short) 0b0000010100101010);
		assertEquals("SRA", instance.getCommand());
	}
	
	@Test
	public void testSLA() {
		// Test SLA
		instance.interpret((short) 0b0000100000101111);
		assertEquals("SLA", instance.getCommand());
	}
	
	@Test
	public void testSRL() {
		// Test SRL
		instance.interpret((short) 0b0000100100101000);
		assertEquals("SRL", instance.getCommand());
		
	}
	
	@Test
	public void testSLL() {
		// Test SLL
		instance.interpret((short) 0b0000110001101010);
		assertEquals("SLL", instance.getCommand());
	}
	
	@Test
	public void testAND() {
		// Test AND
		instance.interpret((short) 0b0000111000000000);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("AND 3", instance.getCommand());
	}

	@Test
	public void testOR() {
		// Test OR
		instance.interpret((short) 0b0000101100011000);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("OR 2", instance.getCommand());
	}

	@Test
	public void testNOT() {
		// Test NOT
		instance.interpret((short) 0b0000000010101010);
		assertEquals("NOT", instance.getCommand());
	}

	@Test
	public void testBZ() {
		// Test BZ
		instance.interpret((short) 0b0001011000011111);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("BZ 1", instance.getCommand());
	}

	@Test
	public void testBNZ() {
		// Test BNZ
		instance.interpret((short) 0b0001100100011001);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("BNZ 2", instance.getCommand());	
	}

	@Test
	public void testBC() {
		// Test BC
		instance.interpret((short) 0b0001111111111111);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("BC 3", instance.getCommand());
	}

	@Test
	public void testB() {
		// Test B
		instance.interpret((short) 0b0001000000000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("B 0", instance.getCommand());
	}

	@Test
	public void testBZD() {
		// Test BZD
		instance.interpret((short) 0b0011000000111000);
		assertEquals((short)0b0000111000, instance.getAddress());
		assertEquals("BZD 56", instance.getCommand());
	}

	@Test
	public void testBNZD() {
		// Test BNZD
		instance.interpret((short) 0b0010110000111011);
		assertEquals((short)0b0000111011, instance.getAddress());
		assertEquals("BNZD 59", instance.getCommand());
	}

	@Test
	public void testBCD() {
		// Test BCD
		instance.interpret((short) 0b0011111111111111);
		assertEquals((short)0b1111111111, instance.getAddress());
		assertEquals("BCD 1023", instance.getCommand());
	}

	@Test
	public void testBD() {
		// Test BD
		instance.interpret((short) 0b0010000010101010);
		assertEquals((short)0b0010101010, instance.getAddress());
		assertEquals("BD 170", instance.getCommand());
	}

	@Test
	public void testEND() {
		// Test END
		instance.interpret((short) 0b0000000000000000);
		assertEquals("END", instance.getCommand());
	}
}
