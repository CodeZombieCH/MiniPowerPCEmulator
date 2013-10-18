package ch.minipowerpcemulator;

import ch.minipowerpcemulator.Registers.NamedRegister;

/**
 * 16-bit ALU
 * The current implementation assumes every opcode is executed by the ALU
 * (even memory operations not doing calculations (no segmentation)
 */
public interface IALU {
	
	boolean getCarryFlag();
	
	void CRL(NamedRegister register);
	void ADD(NamedRegister register);
	void ADDD(short number);
	void INC();
	void DEC();
	
	void LWDD(NamedRegister register, short address);
	void SWDD(NamedRegister register, short address);
	
	void SRA();
	void SLA();
	void SRL();
	void SLL();
	
	void AND(NamedRegister register);
	void OR(NamedRegister register);
	void NOT();
	
	void BZ(NamedRegister register);
	void BNZ(NamedRegister register);
	void BC(NamedRegister register);
	void B(NamedRegister register);
	
	void BZD(short address);
	void BNZD(short address);
	void BCD(short address);
	void BD(short address);
}
