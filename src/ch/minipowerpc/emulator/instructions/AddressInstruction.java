package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public abstract class AddressInstruction implements IInstruction {
	protected IALU alu;
	protected short address;

	public AddressInstruction(IALU alu, short address) {
		this.alu = alu;
		this.address = address;
	}
	
	public abstract boolean run();
}
