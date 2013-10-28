package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class ADDD implements IInstruction {
	private IALU alu;
	private short number;

	public ADDD(IALU alu, short number) {
		this.alu = alu;
		this.number = number;
	}
	
	public boolean run() {
		alu.ADDD(number);
		return true;
	}
	
	@Override
	public String toString() {
		return "ADDD " + number;
	}
}
