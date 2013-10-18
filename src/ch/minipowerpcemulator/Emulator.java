package ch.minipowerpcemulator;

public class Emulator implements IEmulator {
	private ICPU cpu;
	private IMemory memory;

	public Emulator() {
		// Create something like a mainboard, consisting of a CPU, memory and invisible busses (references to objects)
		memory = new Memory();
		cpu = new CPU(memory); // Instance passed to the CPU represents the busses
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
