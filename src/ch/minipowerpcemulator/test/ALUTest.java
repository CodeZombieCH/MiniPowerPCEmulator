package ch.minipowerpcemulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zweierkomplement.EnumProbe;
import zweierkomplement.EnumProbe.Day;

import ch.minipowerpcemulator.IALU;
import ch.minipowerpcemulator.ALU;
import ch.minipowerpcemulator.IRegisters;
import ch.minipowerpcemulator.Registers;
import ch.minipowerpcemulator.Registers.NamedRegister;


public class ALUTest {
	private ALU alu;
	private Registers registers;

	@Before
	public void setUp() throws Exception {
		alu = new ALU();
		registers = new Registers();
		
	}

	
	@Test
	public void testINC()
	{
		registers.set(NamedRegister.Accu, 0);
		alu.INC();
		assertEquals((short)0b1, registers.get(Accu));
		registers.set(Accu, 32767);
		alu.INC();
		assertEquals((short)-32768, registers.get(Accu));
		registers.set(Accu, -1001);
		alu.INC();
		assertEquals((short)-1000, registers.get(Accu));
		registers.set(Accu, -1);
		alu.INC();
		assertEquals((short) 0, registers.get(Accu));
	}

	@Test
	public void testDEC()
	{
		registers.set(Accu, 0);
		alu.DEC();
		assertEquals((short) -1, registers.get(Accu));
		registers.set(Accu, -32768);
		alu.DEC();
		assertEquals((short) 32767, registers.get(Accu));
		registers.set(Accu, -1001);
		alu.DEC();
		assertEquals((short)-1002, registers.get(Accu));
		registers.set(Accu, 0);
		alu.DEC();
		assertEquals((short) -1, registers.get(Accu));
	}

}
