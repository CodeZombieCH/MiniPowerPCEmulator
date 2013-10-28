package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class BCD extends AddressInstruction {
	
	public BCD(IALU alu, short address) {
		super(alu, address);
	}

	public boolean run() {
		alu.BCD(address);
		return true;
	}
	
	@Override
	public String toString() {
		return "BCD #" + address;
	}
}
