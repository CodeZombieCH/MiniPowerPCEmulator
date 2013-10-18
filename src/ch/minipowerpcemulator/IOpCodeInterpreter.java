package ch.minipowerpcemulator;

public interface IOpCodeInterpreter {
	/**
	 * Interprets the passed Opcode and executes the matching operations.
	 * @param opCode
	 */
	public void interpret(short opcode);
}
