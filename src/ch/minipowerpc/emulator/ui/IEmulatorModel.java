package ch.minipowerpc.emulator.ui;

import ch.minipowerpc.emulator.Configuration;
import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.ICPU;
import ch.minipowerpc.emulator.IMemory;
import ch.minipowerpc.emulator.IRegisters;

public interface IEmulatorModel {
	void run();
	void run(boolean notifyObservers);
	boolean runSingleCycle();
	
	int getBase();
	void setBase(int base);
	void toggleBase();
	
	Configuration getConfiguration();
	IMemory getMemory();
	ICPU getCPU();
	IRegisters getRegisters();
	IALU getALU();
}
