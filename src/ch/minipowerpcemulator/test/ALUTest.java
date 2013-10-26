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
	public void testCLR() {
		registers.set(NamedRegister.Accu, (short)0b1111111111111111);
		registers.set(NamedRegister.R1, (short)0b1111111111111111);
		registers.set(NamedRegister.R2, (short)0b1111111111111111);
		registers.set(NamedRegister.R3, (short)0b1111111111111111);
		alu.CLR(NamedRegister.Accu);
		assertEquals((short)0, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R1);
		assertEquals((short)0, registers.get(NamedRegister.R1));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R2);
		assertEquals((short)0, registers.get(NamedRegister.R2));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R3);
		assertEquals((short)0, registers.get(NamedRegister.R3));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)0);
		registers.set(NamedRegister.R1, (short)0);
		registers.set(NamedRegister.R2, (short)0);
		registers.set(NamedRegister.R3, (short)0);
		alu.CLR(NamedRegister.Accu);
		assertEquals((short)0, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R1);
		assertEquals((short)0, registers.get(NamedRegister.R1));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R2);
		assertEquals((short)0, registers.get(NamedRegister.R2));
		assertEquals((false), alu.getCarryFlag());
		alu.CLR(NamedRegister.R3);
		assertEquals((short)0, registers.get(NamedRegister.R3));
		assertEquals((false), alu.getCarryFlag());
	}
	
	@Test
	public void testADD() {
		registers.set(NamedRegister.Accu, (short)0);
		registers.set(NamedRegister.R1, (short)0);
		alu.ADD(NamedRegister.R1);
		assertEquals((short)0, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)123);
		registers.set(NamedRegister.R1, (short)0);
		alu.ADD(NamedRegister.R1);
		assertEquals((short)123, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)123);
		registers.set(NamedRegister.R1, (short)456);
		alu.ADD(NamedRegister.R1);
		assertEquals((short)579, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)456);
		registers.set(NamedRegister.R1, (short)-123);
		alu.ADD(NamedRegister.R1);
		assertEquals((short)333, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)-123);
		registers.set(NamedRegister.R1, (short)-456);
		alu.ADD(NamedRegister.R1);
		assertEquals((short)-579, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)0b1111111111111111); // Negative short overload
		registers.set(NamedRegister.R1, (short)0b1000000000000000); 
		alu.ADD(NamedRegister.R1);
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		assertEquals((true), alu.getCarryFlag()); 
		registers.set(NamedRegister.Accu, (short)0b1); // Positive short overload
		registers.set(NamedRegister.R1, (short)0b0111111111111111); 
		alu.ADD(NamedRegister.R1);
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		assertEquals((true), alu.getCarryFlag());
	}
	
	
	@Test
	public void testADDD() {
		registers.set(NamedRegister.Accu, (short)0);
		alu.ADDD((short)0);
		assertEquals((short)0, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)0);
		alu.ADDD((short)123);
		assertEquals((short)123, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)123);
		alu.ADDD((short)456);
		assertEquals((short)579, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)456);
		alu.ADDD((short)-123);
		assertEquals((short)333, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)-123);
		alu.ADDD((short)-456);
		assertEquals((short)-579, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)0b1111111111111111); // Negative short overload 
		alu.ADDD((short)0b1000000000000000);
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		assertEquals((true), alu.getCarryFlag()); 
		registers.set(NamedRegister.Accu, (short)0b0111111111111111); // Positive short overload
		alu.ADDD((short)1);
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		assertEquals((true), alu.getCarryFlag());
	}
	


	@Test
	public void testINC()
	{
		registers.set(NamedRegister.Accu, (short)0);
		alu.INC();
		assertEquals((short)0b1, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)32767);
		alu.INC();
		assertEquals((short)-32768, registers.get(NamedRegister.Accu));
		assertEquals((true), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)-1001);
		alu.INC();
		assertEquals((short)-1000, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)-1);
		alu.INC();
		assertEquals((short)0, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
	}

	@Test
	public void testDEC()
	{
		registers.set(NamedRegister.Accu, (short)0);
		alu.DEC();
		assertEquals((short)-1, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)-32768);
		alu.DEC();
		assertEquals((short)32767, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)-1001);
		assertEquals((true), alu.getCarryFlag());
		alu.DEC();
		assertEquals((short)-1002, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
		registers.set(NamedRegister.Accu, (short)0);
		alu.DEC();
		assertEquals((short)-1, registers.get(NamedRegister.Accu));
		assertEquals((false), alu.getCarryFlag());
	}
	
	

	@Test
	public void testLWDD() {
		memory.set16Bit((short)800, (short)2345);
		alu.LWDD(NamedRegister.Accu, (short)800);
		assertEquals((short)2345, registers.get(NamedRegister.Accu));
	}
	
	@Test
	public void testSWDD() {
		registers.set(NamedRegister.Accu, (short)4545);
		alu.SWDD(NamedRegister.Accu, (short)900);
		assertEquals((short)4545, memory.get16Bit((short)900));
		
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

	
	@Test
	public void testAND () {
		registers.set(NamedRegister.Accu, (short)0b0);
		registers.set(NamedRegister.R1, (short)0b1111111111111111);
		alu.AND(NamedRegister.R1);
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0101010101010101);
		registers.set(NamedRegister.R1, (short)0b1111111111111111);
		alu.AND(NamedRegister.R1);
		assertEquals((short)0b0101010101010101, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0101010101010101);
		registers.set(NamedRegister.R1, (short)0b1010101010101010);
		alu.AND(NamedRegister.R1);
		assertEquals((short)0b0, registers.get(NamedRegister.Accu));
	}
	
	@Test
	public void testOR () {
		registers.set(NamedRegister.Accu, (short)0b0);
		registers.set(NamedRegister.R1, (short)0b1111111111111111);
		alu.OR(NamedRegister.R1);
		assertEquals((short)0b1111111111111111, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0101010101010101);
		registers.set(NamedRegister.R1, (short)0b1111111111111111);
		alu.OR(NamedRegister.R1);
		assertEquals((short)0b1111111111111111, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0101010101010101);
		registers.set(NamedRegister.R1, (short)0b1010101010101010);
		alu.OR(NamedRegister.R1);
		assertEquals((short)0b1111111111111111, registers.get(NamedRegister.Accu));
	}
	
	@Test
	public void testNOT () {
		registers.set(NamedRegister.Accu, (short)0b0);
		alu.NOT();
		assertEquals((short)0b1111111111111111, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b0101010101010101);
		alu.NOT();
		assertEquals((short)0b1010101010101010, registers.get(NamedRegister.Accu));
		registers.set(NamedRegister.Accu, (short)0b1111111111111111);
		alu.NOT();
		assertEquals((short)0, registers.get(NamedRegister.Accu));
	}
}