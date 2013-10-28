package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class LWDD implements IInstruction {
	protected IALU alu;
	protected NamedRegister register;
	private short address;

	public LWDD(IALU alu, NamedRegister register, short address) {
		this.alu = alu;
		this.register = register;
		this.address = address;
	}
	
	public boolean run() {
		alu.LWDD(register, address);
		return true;
	}
	
	@Override
	public String toString() {
		return "LWDD " + register.toString() + ", #" + address;
	}
}
