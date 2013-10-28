package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class BZD extends AddressInstruction {
	
	public BZD(IALU alu, short address) {
		super(alu, address);
	}

	public boolean run() {
		alu.BZD(address);
		return true;
	}
	
	@Override
	public String toString() {
		return "BZD #" + Short.toString(address);
	}
}
