package ch.minipowerpc.emulator;

public class Configuration {
	private short instructionRangeFrom = 100;
	private short instructionRangeTo = 499;
	
	private short dataRangeFrom = 500;
	private short dataRangeTo = 599;
	
	
	public Configuration() {
		
	}
	
	
	public short getInstructionRangeFrom() {
		return instructionRangeFrom;
	}

	public void setInstructionRangeFrom(short instructionRangeFrom) {
		this.instructionRangeFrom = instructionRangeFrom;
	}

	public short getInstructionRangeTo() {
		return instructionRangeTo;
	}

	public void setInstructionRangeTo(short instructionRangeTo) {
		this.instructionRangeTo = instructionRangeTo;
	}

	public short getDataRangeFrom() {
		return dataRangeFrom;
	}

	public void setDataRangeFrom(short dataRangeFrom) {
		this.dataRangeFrom = dataRangeFrom;
	}

	public short getDataRangeTo() {
		return dataRangeTo;
	}

	public void setDataRangeTo(short dataRangeTo) {
		this.dataRangeTo = dataRangeTo;
	}
}
