package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class SRA implements IInstruction {
	private IALU alu;

	public SRA(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.SRA();
		return true;
	}
	
	@Override
	public String toString() {
		return "SRA";
	}
}
