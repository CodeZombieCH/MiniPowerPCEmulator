package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class BNZ extends RegisterInstruction {
	
	public BNZ(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.BNZ(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "BNZ " + register.toString();
	}
}
