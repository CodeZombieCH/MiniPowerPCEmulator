package ch.minipowerpcemulator;

public interface ICPU {
	/**
	 * Runs a single instruction by reading the opcode from the instruction register,
	 * interpreting and executing the opcode.
	 */
	boolean runSingleCycle();
	public short getProgramCounter();
	public void setProgramCounter(short programCounter);
	public void incrementProgramCounter();
	public short getInstructionRegister();
}
