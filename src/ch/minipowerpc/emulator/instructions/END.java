package ch.minipowerpc.emulator.instructions;


public class END implements IInstruction {
	public END() {
		
	}
	
	public boolean run() {
		return false;
	}
	
	@Override
	public String toString() {
		return "END";
	}
}
