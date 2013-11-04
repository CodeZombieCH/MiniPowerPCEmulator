package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.instructions.IInstruction;

public interface IOpCodeInterpreter {
	/**
	 * Interprets the passed Opcode and executes the matching operations.
	 * @param opCode
	 */
	public IInstruction interpret(short opcode);
	
	public short getRegister();
	
	public short getAddress();
	
	public short getNumber();
}
