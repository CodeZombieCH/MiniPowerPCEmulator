package ch.minipowerpcemulator;

import java.io.IOException;

public interface IEmulator {
	void loadProgram(String fileName) throws IOException, Exception;
	void loadMemory(String fileName) throws IOException, Exception;
	void run();
	
	/*
	ICPU getCpu();
	IMemory getMemory();
	*/
}
