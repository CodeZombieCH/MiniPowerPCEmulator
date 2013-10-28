package ch.minipowerpc.emulator;

public interface IOpCodeInterpreter {
	/**
	 * Interprets the passed Opcode and executes the matching operations.
	 * @param opCode
	 */
	public boolean interpret(short opcode);
}