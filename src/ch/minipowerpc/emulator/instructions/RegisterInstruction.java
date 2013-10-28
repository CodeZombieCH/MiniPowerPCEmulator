package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public abstract class RegisterInstruction implements IInstruction {
	protected IALU alu;
	protected NamedRegister register;

	public RegisterInstruction(IALU alu, NamedRegister register) {
		this.alu = alu;
		this.register = register;
	}
	
	public abstract boolean run();
}
