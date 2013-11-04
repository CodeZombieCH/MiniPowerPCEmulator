package ch.minipowerpc.emulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpc.emulator.ALU;
import ch.minipowerpc.emulator.CPU;
import ch.minipowerpc.emulator.DynamicSizeMemory;
import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.IMemory;
import ch.minipowerpc.emulator.OpCodeInterpreter;
import ch.minipowerpc.emulator.Registers;
import ch.minipowerpc.emulator.instructions.IInstruction;

public class OpCodeInterpreterTest {
	private OpCodeInterpreter instance;
	private IALU alu;
	private IInstruction instruction;

	@Before
	public void setUp() throws Exception {
		IMemory memory = new DynamicSizeMemory();
		alu = new ALU(new CPU(memory), memory, new Registers());
		instance = new OpCodeInterpreter(alu);
	}

	@Test
	public void testCLR() {
		// Test CLR
		instruction = instance.interpret((short) 0b0000001010000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("CLR Accu", instruction.toString());
		
		// Test CLR
		instruction = instance.interpret((short) 0b0000111011111111);
		assertEquals((short)0b11, instance.getRegister());	
		assertEquals("CLR R3", instruction.toString());
	}
	
	@Test
	public void testADD() {
		// Test ADD
		instruction = instance.interpret((short) 0b0000011110101010);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("ADD R1", instruction.toString());
	}
	
	@Test
	public void testADDD() {
		// Test ADDD
		instruction = instance.interpret((short) 0b1000011111111111);
		assertEquals((short)0b000011111111111, instance.getNumber());
		assertEquals("ADDD 2047", instruction.toString());
	}
	
	@Test
	public void testINC() {
		// Test INC
		instruction = instance.interpret((short) 0b0000000101010101);
		assertEquals("INC", instruction.toString());
	}
	
	@Test
	public void testDEC() {
		// Test DEC
		instruction = instance.interpret((short) 0b0000010001010101);
		assertEquals("DEC", instruction.toString());
	}
	
	@Test
	public void testLWDD() {
		// Test LWDD
		instruction = instance.interpret((short) 0b0101100011001100);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals((short)0b0011001100, instance.getAddress());
		assertEquals("LWDD R2, #204", instruction.toString());
	}
	
	@Test
	public void testSWDD() {
		// Test SWDD
		instruction = instance.interpret((short) 0b0111010010101100);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals((short)0b0010101100, instance.getAddress());
		assertEquals("SWDD R1, #172", instruction.toString());
	}
	
	@Test
	public void testSRA() {
		// Test SRA
		instruction = instance.interpret((short) 0b0000010100101010);
		assertEquals("SRA", instruction.toString());
	}
	
	@Test
	public void testSLA() {
		// Test SLA
		instruction = instance.interpret((short) 0b0000100000101111);
		assertEquals("SLA", instruction.toString());
	}
	
	@Test
	public void testSRL() {
		// Test SRL
		instruction = instance.interpret((short) 0b0000100100101000);
		assertEquals("SRL", instruction.toString());
		
	}
	
	@Test
	public void testSLL() {
		// Test SLL
		instruction = instance.interpret((short) 0b0000110001101010);
		assertEquals("SLL", instruction.toString());
	}
	
	@Test
	public void testAND() {
		// Test AND
		instruction = instance.interpret((short) 0b0000111000000000);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("AND R3", instruction.toString());
	}

	@Test
	public void testOR() {
		// Test OR
		instruction = instance.interpret((short) 0b0000101100011000);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("OR R2", instruction.toString());
	}

	@Test
	public void testNOT() {
		// Test NOT
		instruction = instance.interpret((short) 0b0000000010101010);
		assertEquals("NOT", instruction.toString());
	}

	@Test
	public void testBZ() {
		// Test BZ
		instruction = instance.interpret((short) 0b0001011000011111);
		assertEquals((short)0b01, instance.getRegister());
		assertEquals("BZ R1", instruction.toString());
	}

	@Test
	public void testBNZ() {
		// Test BNZ
		instruction = instance.interpret((short) 0b0001100100011001);
		assertEquals((short)0b10, instance.getRegister());
		assertEquals("BNZ R2", instruction.toString());	
	}

	@Test
	public void testBC() {
		// Test BC
		instruction = instance.interpret((short) 0b0001111111111111);
		assertEquals((short)0b11, instance.getRegister());
		assertEquals("BC R3", instruction.toString());
	}

	@Test
	public void testB() {
		// Test B
		instruction = instance.interpret((short) 0b0001000000000000);
		assertEquals((short)0b00, instance.getRegister());
		assertEquals("B Accu", instruction.toString());
	}

	@Test
	public void testBZD() {
		// Test BZD
		instruction = instance.interpret((short) 0b0011000000111000);
		assertEquals((short)0b0000111000, instance.getAddress());
		assertEquals("BZD #56", instruction.toString());
	}

	@Test
	public void testBNZD() {
		// Test BNZD
		instruction = instance.interpret((short) 0b0010110000111011);
		assertEquals((short)0b0000111011, instance.getAddress());
		assertEquals("BNZD #59", instruction.toString());
	}

	@Test
	public void testBCD() {
		// Test BCD
		instruction = instance.interpret((short) 0b0011111111111111);
		assertEquals((short)0b1111111111, instance.getAddress());
		assertEquals("BCD #1023", instruction.toString());
	}

	@Test
	public void testBD() {
		// Test BD
		instruction = instance.interpret((short) 0b0010000010101010);
		assertEquals((short)0b0010101010, instance.getAddress());
		assertEquals("BD #170", instruction.toString());
	}

	@Test
	public void testEND() {
		// Test END
		instruction = instance.interpret((short) 0b0000000000000000);
		assertEquals("END", instruction.toString());
	}
}
