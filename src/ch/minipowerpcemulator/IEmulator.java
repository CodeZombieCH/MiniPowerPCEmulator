package ch.minipowerpcemulator;

import java.io.IOException;

public interface IEmulator {
	void loadProgram(String fileName) throws IOException, Exception;
	void loadMemory(String fileName) throws IOException, Exception;
	
	/**
	 * Runs a single cpu cycle.
	 * @return Returns true if the executed opcode was different from END (= 0x0000), if END false
	 */
	boolean runSingleCycle();
	void run();
	
	Configuration getConfiguration();
	ICPU getCpu();
	IMemory getMemory();
}
