package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class B extends RegisterInstruction {
	
	public B(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.B(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "B " + register.toString();
	}
}
