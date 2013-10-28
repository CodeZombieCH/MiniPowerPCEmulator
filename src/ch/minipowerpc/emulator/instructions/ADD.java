package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class ADD extends RegisterInstruction {
	
	public ADD(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.ADD(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "ADD " + register.toString();
	}
}
