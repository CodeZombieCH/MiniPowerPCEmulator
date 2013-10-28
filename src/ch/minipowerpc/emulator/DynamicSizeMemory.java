package ch.minipowerpc.emulator;

import java.util.ArrayList;

public class DynamicSizeMemory implements IMemory {
	private ArrayList<Byte> memory;
	private final byte defaultValue = 0b00000000;

	public DynamicSizeMemory() {
		this.memory = new ArrayList<Byte>();
	}

	@Override
	public void initialize() {
		// Set the whole memory to zeros.
		// Not required in this dynamic implementation
	}

	@Override
	public short get16Bit(short address) {
		if(address % 2 != 0) {
			throw new IllegalArgumentException("In order to get a 16 bits from address, the address must be a multipe of 2");
		}
		
		// Dynamically expand, allocate and initialize memory
		if(address > memory.size() - 2) {
			while(address > memory.size() - 2)
				memory.add(defaultValue);
		}
		
		// Little endian
		//return (short)((memory.get(address) & 0xff) | (memory.get(address + 1) << 8));
		
		// Big endian
		return (short)((memory.get(address) << 8) | (memory.get(address + 1) & 0xff));
	}

	@Override
	public void set16Bit(short address, short value) {
		if(address % 2 != 0) {
			throw new IllegalArgumentException("In order to get a 16 bits from address, the address must be a multipe of 2");
		}
		
		// Dynamically expand, allocate and initialize memory
		if(address > memory.size() - 2) {
			while(address > memory.size() - 2)
				memory.add(defaultValue);
		}
		
		// Little endian
		//memory.set(address, (byte)(value & 0xff));
		//memory.set(address + 1, (byte)((value >> 8) & 0xff));
		
		// Big endian
		memory.set(address, (byte)((value >> 8) & 0xff));
		memory.set(address + 1, (byte)(value & 0xff));

	}

	@Override
	public byte getByte(short address) {
		// Dynamically expand, allocate and initialize memory
		if(address > memory.size() - 1) {
			while(address > memory.size() - 1)
				memory.add(defaultValue);
		}
		
		return memory.get(address);
	}

	@Override
	public void setByte(short address, byte value) {
		// Dynamically expand, allocate and initialize memory
		if(address > memory.size()) {
			while(address > memory.size() - 2)
				memory.add(defaultValue);
		}
		
		memory.set(address, value);
	}
}
