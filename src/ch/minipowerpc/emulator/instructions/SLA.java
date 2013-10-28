package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class SLA implements IInstruction {
	private IALU alu;

	public SLA(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.SLA();
		return true;
	}
	
	@Override
	public String toString() {
		return "SLA";
	}
}
