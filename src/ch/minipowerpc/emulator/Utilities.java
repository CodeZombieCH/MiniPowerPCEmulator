package ch.minipowerpc.emulator;

public class Utilities {
	public static void printMemoryInstruction(byte[] instruction) {
		for (int i = 0; i < instruction.length; i++) {
			System.out.print(String.format("%8s", Integer.toBinaryString(instruction[i] & 0xff)).replace(' ', '0') + " ");
			if(i % 4 == 3) System.out.println();
		}
	}
	
    public static String toBinary(short value) {
    	return String.format("%16s", Integer.toString(value & 0xFFFF, 2)).replace(' ', '0');
    }
    
    public static String toBinary(byte value) {
    	return String.format("%8s", Integer.toString(value & 0xFF, 2)).replace(' ', '0');
    }
}
