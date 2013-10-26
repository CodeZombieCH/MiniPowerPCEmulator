package ch.minipowerpcemulator;

public interface ICPU {
	/**
	 * Executes a single instruction by reading the opcode from the instruction register,
	 * interpreting and executing the opcode.
	 */
	boolean executeSingle();
	public short getProgramCounter();
	public void setProgramCounter(short programCounter);
	public short getInstructionRegister();
}
