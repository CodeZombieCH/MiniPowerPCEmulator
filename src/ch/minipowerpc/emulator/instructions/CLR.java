package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class CLR extends RegisterInstruction {
	
	public CLR(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.CLR(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "CLR " + register.toString();
	}
}
