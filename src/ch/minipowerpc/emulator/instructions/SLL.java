package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class SLL implements IInstruction {
	private IALU alu;

	public SLL(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.SLL();
		return true;
	}
	
	@Override
	public String toString() {
		return "SLL";
	}
}
