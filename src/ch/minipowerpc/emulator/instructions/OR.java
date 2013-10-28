package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class OR extends RegisterInstruction {
	
	public OR(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.OR(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "OR " + register.toString();
	}
}
