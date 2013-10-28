package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class BC extends RegisterInstruction {
	
	public BC(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.BC(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "BC " + register.toString();
	}
}
