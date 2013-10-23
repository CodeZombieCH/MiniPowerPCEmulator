package ch.minipowerpcemulator;

import ch.minipowerpcemulator.Registers.NamedRegister;



public class ALU implements IALU {
	private ICPU cpu;
	private IMemory memory;
	private IRegisters registers;
	private boolean carryflag = false;
	
	public ALU(ICPU cpu, IMemory memory, IRegisters registers) {
		this.cpu = cpu;
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
		short i = registers.get(NamedRegister.Accu);
		short j = registers.get(register);
		if (i+j < 32767 && i+j > -32768){
			registers.set(NamedRegister.Accu, (short)(i+j));
			carryflag = false;
		}
		else{
			registers.set(NamedRegister.Accu, (short)32767);
			carryflag = true;
		}
	}

	@Override
	public void ADDD(short number) {
		short i = registers.get(NamedRegister.Accu);
		short j = number;
		if (i+j < 32767 && i+j > -32768){
			registers.set(NamedRegister.Accu, (short)(i+j));
			carryflag = false;
		}
		else{
			registers.set(NamedRegister.Accu, (short)32767);
			carryflag = true;
		}
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
			i++;
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
			i--;
		}
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void LWDD(NamedRegister register, short address) {
		registers.set(register, address);
	}

	@Override
	public void SWDD(NamedRegister register, short address) {
		memory.set16Bit(address, registers.get(register));
	}

	@Override
	public void SRA() {
		short i = registers.get(NamedRegister.Accu);
		if ((short)(i & 0b1) == (short)0b1)
			carryflag = true;
		else
			carryflag = false;
		i >>= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SLA() {
		short i = registers.get(NamedRegister.Accu);
		if ((short)(i & 0b0100000000000000) == (short)0b0100000000000000)
			carryflag = true;
		else
			carryflag = false;
		if (i < 0){
			i <<= 1;
			i |= (1 << 15);
		}
		else{
			i <<= 1;
			i &= ~(1 << 15);
		}
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SRL() {
		short i = registers.get(NamedRegister.Accu);
		if ((short)(i & 0b1) == (short)0b1)
			carryflag = true;
		else
			carryflag = false;
		i >>>= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SLL() {
		short i = registers.get(NamedRegister.Accu);
		if ((short)(i & 0b1000000000000000) == (short)0b1000000000000000)
			carryflag = true;
		else
			carryflag = false;
		i <<= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void AND(NamedRegister register) {
		short i = registers.get(NamedRegister.Accu);
		short j = registers.get(register);
		registers.set(NamedRegister.Accu, (short)(i&j));
		
	}

	@Override
	public void OR(NamedRegister register) {
		short i = registers.get(NamedRegister.Accu);
		short j = registers.get(register);
		registers.set(NamedRegister.Accu, (short)(i|j));
	}

	@Override
	public void NOT() {
		short i = registers.get(NamedRegister.Accu);
		registers.set(NamedRegister.Accu, (short)(~i));
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