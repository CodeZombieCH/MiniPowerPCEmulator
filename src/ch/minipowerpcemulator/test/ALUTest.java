package ch.minipowerpcemulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpcemulator.DynamicSizeMemory;
import ch.minipowerpcemulator.IALU;
import ch.minipowerpcemulator.ALU;
import ch.minipowerpcemulator.IMemory;
import ch.minipowerpcemulator.IRegisters;
import ch.minipowerpcemulator.Registers;
import ch.minipowerpcemulator.Registers.NamedRegister;


public class ALUTest {
	private IMemory memory;
	private IRegisters registers;
	private IALU alu;


	@Before
	public void setUp() throws Exception {
		this.memory = new DynamicSizeMemory();
		this.registers = new Registers();
		this.alu = new ALU(memory, registers);		
	}


	@Test
	public void testINC()
	{
		registers.set(NamedRegister.Accu, (short)0);
		alu.INC();
		assertEquals((short)0b1, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)32767);
		alu.INC();
		assertEquals((short)-32768, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)-1001);
		alu.INC();
		assertEquals((short)-1000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)-1);
		alu.INC();
		assertEquals((short)0, registers.get(NamedRegister.Accu));
	}

	@Test
	public void testDEC()
	{
		registers.set(NamedRegister.Accu, (short)0);
		alu.DEC();
		assertEquals((short)-1, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)-32768);
		alu.DEC();
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)-1001);
		alu.DEC();
		assertEquals((short)-1002, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0);
		alu.DEC();
		assertEquals((short)-1, registers.get(NamedRegister.Accu));
	}
}
