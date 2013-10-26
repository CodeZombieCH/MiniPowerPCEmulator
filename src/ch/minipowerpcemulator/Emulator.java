package ch.minipowerpcemulator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Emulator implements IEmulator {
	private ICPU cpu;
	private IMemory memory;
	
	
	public Emulator() {
		// Create something like a mainboard, consisting of a CPU, memory and invisible busses (references to objects)
		memory = new DynamicSizeMemory();
		cpu = new CPU(memory); // Instance passed to the CPU represents the busses
	}

	
	@Override
	public void loadProgram(String fileName) throws IOException, Exception {
		Path path = Paths.get("programs/" + fileName + ".bin");
		byte[] data = Files.readAllBytes(path);
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.rewind();
		
		int address = 100;
		while(buffer.hasRemaining()) {
			memory.set16Bit((short)address, buffer.getShort());
			address += 2;
			
			if(address >= 500) {
				throw new Exception("Maximum program size exeeded");
			}
		}
	}
	
	@Override
	public void loadMemory(String fileName) throws IOException, Exception {
		Path path = Paths.get("programs/" + fileName + ".mem");
		byte[] data = Files.readAllBytes(path);
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.rewind();
		
		while(buffer.hasRemaining()) {
			short address = buffer.getShort();
			short value = buffer.getShort();
			
			if(address < 500) {
				throw new Exception("Writing to memory addresses for program instructions is not allowed");
			}
			
			memory.set16Bit(address, value);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

	
	public ICPU getCpu() {
		return cpu;
	}

	public IMemory getMemory() {
		return memory;
	}
}
