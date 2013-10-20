package ch.minipowerpcemulator;

import ch.minipowerpcemulator.Registers.NamedRegister;



public class ALU implements IALU {
	private IMemory memory;
	private IRegisters registers;
	private boolean carryflag = false;
	
	public ALU(IMemory memory, IRegisters registers) {
		this.memory = memory;
		this.registers = registers;	 
	}

	
	@Override
	public boolean getCarryFlag() {
		return carryflag;
	}

	@Override
	public void CLR(NamedRegister register) {
		short i = 0;
		registers.set(register, i);
		carryflag = false;
	}

	@Override
	public void ADD(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void ADDD(short number) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void INC() {
		short i = registers.get(NamedRegister.Accu);
		if (i == 32767) { 
			carryflag = true;
			i = -32768;
		}
		else {
			carryflag = false;
			i = i++;
		}
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void DEC() {
		short i = registers.get(NamedRegister.Accu);
		if (i == -32768) { 
			carryflag = true;
			i = 32767;
		}
		else {
			carryflag = false;
			i = i--;
		}
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void LWDD(NamedRegister register, short address) {
		registers.set(register, address);
	}

	@Override
	public void SWDD(NamedRegister register, short address) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void SRA() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void SLA() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void SRL() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void SLL() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void AND(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void OR(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void NOT() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BZ(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BNZ(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BC(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void B(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BZD(short address) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BNZD(short address) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BCD(short address) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void BD(short address) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
