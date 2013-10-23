package ch.minipowerpcemulator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.minipowerpcemulator.CPU;
import ch.minipowerpcemulator.DynamicSizeMemory;
import ch.minipowerpcemulator.IALU;
import ch.minipowerpcemulator.ALU;
import ch.minipowerpcemulator.ICPU;
import ch.minipowerpcemulator.IMemory;
import ch.minipowerpcemulator.IRegisters;
import ch.minipowerpcemulator.Registers;
import ch.minipowerpcemulator.Registers.NamedRegister;


public class ALUTest {
	private IMemory memory;
	private ICPU cpu;
	private IRegisters registers;
	private IALU alu;


	@Before
	public void setUp() throws Exception {
		this.memory = new DynamicSizeMemory();
		this.cpu = new CPU(this.memory);
		this.registers = new Registers();
		this.alu = new ALU(this.cpu, this.memory, this.registers);		
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
	
	@Test
	public void testSRA()
	{
		registers.set(NamedRegister.Accu, (short)0b0);
		alu.SRA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1);
		alu.SRA();
		assertEquals(true, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b100);
		alu.SRA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b10, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1000000000000100);
		alu.SRA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b1100000000000010, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0100000000000100);
		alu.SRA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b0010000000000010, registers.get(NamedRegister.Accu));
	}
	
	@Test
	public void testSLA()
	{
		registers.set(NamedRegister.Accu, (short)0b0);
		alu.SLA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b100);
		alu.SLA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b1000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1100000000000100);
		alu.SLA();
		assertEquals(true, alu.getCarryFlag());
		assertEquals((short)0b1000000000001000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0100000000000100);
		alu.SLA();
		assertEquals(true, alu.getCarryFlag());
		assertEquals((short)0b0000000000001000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1000000000000100);
		alu.SLA();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b1000000000001000, registers.get(NamedRegister.Accu));
	}

	@Test
	public void testSRL()
	{
		registers.set(NamedRegister.Accu, (short)0b0);
		alu.SRL();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1);
		alu.SRL();
		assertEquals(true, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b100);
		alu.SRL();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b10, registers.get(NamedRegister.Accu));
	}
	
	@Test
	public void testSLL()
	{
		registers.set(NamedRegister.Accu, (short)0b0);
		alu.SLL();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b100);
		alu.SLL();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b1000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1000000000000100);
		alu.SLL();
		assertEquals(true, alu.getCarryFlag());
		assertEquals((short)0b1000, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0100000000000100);
		alu.SLL();
		assertEquals(false, alu.getCarryFlag());
		assertEquals((short)0b1000000000001000, registers.get(NamedRegister.Accu));
	}
}