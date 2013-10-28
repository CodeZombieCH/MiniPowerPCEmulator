package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class SRL implements IInstruction {
	private IALU alu;

	public SRL(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.SRL();
		return true;
	}
	
	@Override
	public String toString() {
		return "SRL";
	}
}
