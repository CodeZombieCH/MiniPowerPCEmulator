package ch.minipowerpcemulator;

public class CPU implements ICPU {
	private IMemory memory;
	private IALU alu;
	private IRegisters registers;
	private IOpCodeInterpreter interpreter;
	
	private short programCounter;
	private short instructionRegister;

	public CPU(IMemory memory) {
		this.memory = memory;
		
		this.alu = new ALU(this.memory, this.registers);
		this.registers = new Registers();
		this.interpreter = new OpCodeInterpreter(alu);
	}

	@Override
	public void executeSingle() {
		// TODO Auto-generated method stub
		
		/*
			„Programmablauf“:
			1.	Der aktuelle Befehl wird aus der Speicherzelle, auf die der
				Befehlszähler zeigt, ausgelesen und in das Steuerwerk
				übertragen
			2.	Das Steuerwerk dekodiert den Befehl und schaltet die entsprechenden
				Signale auf den Steuerleitungen
			3.	Die für den Befehl erforderlichen Operanden werden aus dem
				Speicherwerk gelesen und in das Rechenwerk bzw. die festgelegten
				Register übertragen
			4.	Die dekodierte Operation wird ausgeführt; das Ergebnis in ein
				Register (oder den Speicher) geschrieben
			5.	Der Befehlszähler wird um eins erhöht oder auf Grund eines
				Sprung-Befehls um einen anderen Wert verändert
		 */
	}
}
