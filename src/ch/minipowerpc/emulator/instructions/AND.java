package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class AND extends RegisterInstruction {
	
	public AND(IALU alu, NamedRegister register) {
		super(alu, register);
	}

	public boolean run() {
		alu.AND(register);
		return true;
	}
	
	@Override
	public String toString() {
		return "AND " + register.toString();
	}
}
