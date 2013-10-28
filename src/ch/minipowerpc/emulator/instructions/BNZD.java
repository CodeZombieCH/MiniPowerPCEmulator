package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class BNZD extends AddressInstruction {
	
	public BNZD(IALU alu, short address) {
		super(alu, address);
	}

	public boolean run() {
		alu.BNZD(address);
		return true;
	}
	
	@Override
	public String toString() {
		return "BNZD #" + address;
	}
}
