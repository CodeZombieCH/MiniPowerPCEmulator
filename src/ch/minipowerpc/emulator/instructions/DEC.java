package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class DEC implements IInstruction {
	private IALU alu;

	public DEC(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.DEC();
		return true;
	}
	
	@Override
	public String toString() {
		return "DEC";
	}
}
