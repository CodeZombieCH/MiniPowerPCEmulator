package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class INC implements IInstruction {
	private IALU alu;

	public INC(IALU alu) {
		this.alu = alu;
	}
	
	public boolean run() {
		alu.INC();
		return true;
	}
	
	@Override
	public String toString() {
		return "INC";
	}
}
