package ch.minipowerpc.emulator.instructions;

import ch.minipowerpc.emulator.IALU;

public class BD extends AddressInstruction {
	
	public BD(IALU alu, short address) {
		super(alu, address);
	}

	public boolean run() {
		alu.BD(address);
		return true;
	}
	
	@Override
	public String toString() {
		return "BD #" + address;
	}
}
