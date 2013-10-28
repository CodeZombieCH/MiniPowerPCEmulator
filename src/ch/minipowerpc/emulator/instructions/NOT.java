package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class NOT implements IInstruction {
	private IALU alu;

	public NOT(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.NOT();
		return true;
	}
	
	@Override
	public String toString() {
		return "NOT";
	}
}
