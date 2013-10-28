package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.Registers.NamedRegister;

public interface IRegisters {
	/**
	 * Initializes the registers by setting them to 0
	 */
	void initialize();
	short get(NamedRegister register);
	void set(NamedRegister register, short value);
}
