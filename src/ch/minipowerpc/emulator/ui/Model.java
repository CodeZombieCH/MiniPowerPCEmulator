package ch.minipowerpc.emulator.ui;

import java.util.Observable;

import ch.minipowerpc.emulator.ICPU;
import ch.minipowerpc.emulator.IMemory;

public class Model extends Observable{
	private ICPU cpu;
	private IMemory memory;

	
	public Model(ICPU cpu, IMemory memory) {
		this.setCpu(cpu);
		this.setMemory(memory);
	}


	public ICPU getCpu() {
		return cpu;
	}

	public void setCpu(ICPU cpu) {
		this.cpu = cpu;
	}

	public IMemory getMemory() {
		return memory;
	}

	public void setMemory(IMemory memory) {
		this.memory = memory;
	}
}
