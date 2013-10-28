package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class SWDD implements IInstruction {
	protected IALU alu;
	protected NamedRegister register;
	private short address;

	public SWDD(IALU alu, NamedRegister register, short address) {
		this.alu = alu;
		this.register = register;
		this.address = address;
	}
	
	public boolean run() {
		alu.SWDD(register, address);
		return true;
	}
	
	@Override
	public String toString() {
		return "SWDD " + register.toString() + ", #" + address;
	}
}
