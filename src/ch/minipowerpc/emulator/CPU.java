package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.instructions.IInstruction;

public class CPU implements ICPU {
	private IMemory memory;
	private IALU alu;
	private IRegisters registers;
	private IOpCodeInterpreter interpreter;

	/**
	 * Holds the memory address of the next instruction that would be executed.
	 */
	private short programCounter;
	/**
	 * The instruction register holds the instruction currently being decoded or executed
	 */
	private short instructionRegister;
	/**
	 * Counts the number of cycles executed
	 */
	private int cycleCount = 0;
	

	public CPU(IMemory memory) {
		this.memory = memory;
		this.registers = new Registers();
		this.alu = new ALU(this, this.memory, this.registers);
		this.interpreter = new OpCodeInterpreter(alu);
	}

	@Override
	public boolean runSingleCycle() {
		cycleCount++;
		
		/*
		1.	Der aktuelle Befehl wird aus der Speicherzelle, auf die der
			Befehlszähler zeigt, ausgelesen und in das Steuerwerk übertragen
		*/
		// FETCH
		instructionRegister = memory.get16Bit(programCounter);
		// Increment program counter (according to the German Wikipedia article)
		// Increment by 2 (word size is 16bit, address granularity is 8bit --> 8 * 2)
		programCounter += 2;

		/*
		2.	Das Steuerwerk dekodiert den Befehl und schaltet die entsprechenden
			Signale auf den Steuerleitungen
		3.	Die für den Befehl erforderlichen Operanden werden aus dem
			Speicherwerk gelesen und in das Rechenwerk bzw. die festgelegten Register übertragen
		4.	Die dekodierte Operation wird ausgeführt; das Ergebnis in ein
			Register (oder den Speicher) geschrieben
		*/
		// DECODE, FETCH OPERANDS, EXECUTE und UPDATE INSTRUCTION POINTER
		IInstruction instruction = interpreter.interpret(instructionRegister);
		//System.out.println(instruction);
		return instruction.run();
		
		/*
		5.	Der Befehlszähler wird um eins erhöht oder auf Grund eines
			Sprung-Befehls um einen anderen Wert verändert
		 */
		// Different approach described in 1.
	}

	@Override
	public short getProgramCounter() {
		return programCounter;
	}

	@Override
	public void setProgramCounter(short programCounter) {
		this.programCounter = programCounter;
	}
	
	@Override
	public void incrementProgramCounter() {
		// Increment by 2 (word size is 16bit, address granularity is 8bit --> 8 * 2)
		programCounter += 2;
	}

	@Override
	public short getInstructionRegister() {
		return instructionRegister;
	}
	
	@Override
	public int getCycleCount() {
		return cycleCount;
	}
	
	@Override
	public IALU getALU() {
		return alu;
	}

	@Override
	public IRegisters getRegisters() {
		return registers;
	}
}
