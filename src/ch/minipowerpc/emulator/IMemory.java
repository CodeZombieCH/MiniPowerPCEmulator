package ch.minipowerpc.emulator;

public interface IMemory {
	/**
	 * Initializes the memory by setting all bytes to 0
	 */
	void initialize();
	short get16Bit(short address);
	void set16Bit(short address, short value);
	byte getByte(short address);
	void setByte(short address, byte value);
}
