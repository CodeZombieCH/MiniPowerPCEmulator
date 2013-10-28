package ch.minipowerpc.emulator;

public interface ICPU {
	/**
	 * Runs a single instruction by reading the opcode from the instruction register,
	 * interpreting and executing the opcode.
	 * @return Returns true if the executed opcode was different from END (= 0x0000), if END false
	 */
	boolean runSingleCycle();
	
	short getProgramCounter();
	void setProgramCounter(short programCounter);
	
	/**
	 * Convenience method to increment the program counter
	 */
	void incrementProgramCounter();
	
	short getInstructionRegister();
	int getCycleCount();
	
	IALU getALU();
	IRegisters getRegisters();
}
