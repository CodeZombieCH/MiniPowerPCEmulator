package ch.minipowerpc.emulator.ui;

import java.util.List;
import java.util.Observable;

import javax.swing.SwingWorker;

import org.junit.runner.notification.StoppedByUserException;

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
	private boolean isRunning = false;
	
	private SwingWorker<Boolean, Void> worker;
	private long start;

	
	public EmulatorModel(final IEmulator emulator) {
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
		// Create new worker (as they are not designed to be reused)
		worker = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// CPU loop
				while(!isCancelled() && emulator.runSingleCycle()) {
					
				}
				return true;
			}
			
			@Override
			protected void done() {
				long duration = System.nanoTime() - start;
				double clockRate = emulator.getCpu().getCycleCount() / (duration *  Math.pow(10, -9));
				System.out.println(String.format("Duration: %f ns, Clock rate: %.0f Hz", duration *  Math.pow(10, -9), clockRate));
				setRunning(false);
			}
		};

		setRunning(true);
		start = System.nanoTime();
		worker.execute();
	}

	@Override
	public void runAndNotify() {
		// Create new worker (as they are not designed to be reused)
		worker = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// CPU loop
				while(!isCancelled() && emulator.runSingleCycle()) {
					publish();
					setProgress(0);
					
					// TODO: Think about adding a delay
					Thread.sleep(250);
				}
				return true;
			}
			
			@Override
			protected void process(List<Void> chunks) {
				setChanged();
				notifyObservers();
			}
			
			@Override
			protected void done() {
				setRunning(false);
			}
		};

		setRunning(true);
		worker.execute();
	}

	@Override
	public boolean runSingleCycle() {
		setRunning(true);
		boolean status = emulator.runSingleCycle();
		setRunning(false);
		return status;
	}
	
	@Override
	public void cancel() {
		if(isRunning)
			worker.cancel(false);
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
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		setChanged();
		notifyObservers();
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
