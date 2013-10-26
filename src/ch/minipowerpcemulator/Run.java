package ch.minipowerpcemulator;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {
		
		if(args.length != 1) System.exit(-1);
		
		IEmulator emulator = new Emulator();
		
		try {
			emulator.loadProgram(args[0]);
			emulator.loadMemory(args[0]);
			
			emulator.run();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(-2);
		}
		
		System.exit(0);
	}
}
