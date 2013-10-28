package ch.minipowerpc.emulator.ui;

import java.util.Observable;

import ch.minipowerpc.emulator.Configuration;
import ch.minipowerpc.emulator.IALU;
import ch.minipowerpc.emulator.ICPU;
import ch.minipowerpc.emulator.IEmulator;
import ch.minipowerpc.emulator.IMemory;
import ch.minipowerpc.emulator.IRegisters;

public class EmulatorModel extends Observable implements IEmulatorModel {
	private IEmulator emulator;
	private Configuration configuration;
	private IMemory memory;
	private ICPU cpu;
	private IRegisters registers;
	private IALU alu;
	
	private int base = 10;

	
	public EmulatorModel(IEmulator emulator) {
		if(emulator == null) throw new NullPointerException("Must pass an instantiated instance of emulator");
		
		this.emulator = emulator;
		
		// Cache references to components for better performance
		this.configuration = emulator.getConfiguration();
		this.memory = emulator.getMemory();
		this.cpu = emulator.getCpu();
		this.registers = emulator.getCpu().getRegisters();
		this.alu = emulator.getCpu().getALU();
	}
	

	@Override
	public void run() {
		// CPU loop
		while(emulator.runSingleCycle()) {
			
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public void run(boolean notifyObservers) {
		// CPU loop
		while(emulator.runSingleCycle()) {
			// Notify observers is desired
			if(notifyObservers) {
				setChanged();
				notifyObservers();
			}
			
			// TODO: Think about adding a delay
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean runSingleCycle() {
		boolean status = emulator.runSingleCycle();
		setChanged();
		notifyObservers();
		return status;
	}

	@Override
	public int getBase() {
		return base;
	}

	@Override
	public void setBase(int base) {
		this.base = base;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void toggleBase() {
		setBase((base == 2) ? 10 : 2);
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}
	
	@Override
	public IMemory getMemory() {
		return memory;
	}

	@Override
	public ICPU getCPU() {
		return cpu;
	}

	@Override
	public IRegisters getRegisters() {
		return registers;
	}

	@Override
	public IALU getALU() {
		return alu;
	}
}
