package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.Registers.NamedRegister;

public class ALU implements IALU {
	private ICPU cpu;
	private IMemory memory;
	private IRegisters registers;
	private boolean carryFlag = false;
	private boolean overflowFlag = false;
	
	
	public ALU(ICPU cpu, IMemory memory, IRegisters registers) {
		this.cpu = cpu;
		this.memory = memory;
		this.registers = registers;	 
	}

	
	@Override
	public boolean getCarryFlag() {
		return carryFlag;
	}
	
	@Override
	public boolean getOverflowFlag() {
		return overflowFlag;
	}
	

	@Override
	public void CLR(NamedRegister register) {
		registers.set(register, (short)0);
		overflowFlag = false;
		carryFlag = false;
	}

	@Override
	public void ADD(NamedRegister register) {
		short operand1 = registers.get(NamedRegister.Accu);
		short operand2 = registers.get(register);
		
		int result = operand1 + operand2;
		
		// Set flags
		// Use integer as a result to be able to detect the bit carried
		 
		carryFlag =
				// One of the higher (integer) bytes are set
				(((operand1 + operand2) & 0b11111111_11111111_00000000_00000000) != 0)
				/* Special case: (-x) + x results in zero, which is correct math
				 * BUT: In binary math this should trigger the carry flag which is not detected due to the zero result
				 * Example:
                 *      1111 (-1)       0010 (+2)
                 *    + 0001 (+1)     + 1110 (-2)
                 *      ----            ----
                 *     10000           10000
				 */
				|| (operand1 != 0 && result == 0);
		
		// If the short result is different than the integer result, we exceeded the range of a short
		//carryFlag = result > Short.MAX_VALUE || result < Short.MIN_VALUE; // Wrong
		
		//carryFlag = (result != (short)result); // Wrong
		
		setOverflowFlag(operand1, operand2, (short)result);

		registers.set(NamedRegister.Accu, (short)result);
	}

	@Override
	public void ADDD(short number) {
		short operand1 = registers.get(NamedRegister.Accu);
		short operand2 = number;
		
		int result = operand1 + operand2;
		
		// Set flags
		// Use integer as a result to be able to detect the bit carried
		 
		carryFlag =
				// One of the higher (integer) bytes are set
				(((operand1 + operand2) & 0b11111111_11111111_00000000_00000000) != 0)
				/* Special case: (-x) + x results in zero, which is correct math
				 * BUT: In binary math this should trigger the carry flag which is not detected due to the zero result
				 * Example:
                 *      1111 (-1)       0010 (+2)
                 *    + 0001 (+1)     + 1110 (-2)
                 *      ----            ----
                 *     10000           10000
				 */
				|| (operand1 != 0 && result == 0);
		
		// If the short result is different than the integer result, we exceeded the range of a short
		//carryFlag = result > Short.MAX_VALUE || result < Short.MIN_VALUE; // Wrong
		
		//carryFlag = (result != (short)result); // Wrong
		
		setOverflowFlag(operand1, operand2, (short)result);

		registers.set(NamedRegister.Accu, (short)result);
	}

	@Override
	public void INC() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = (i == (short)0b11111111_11111111);
		overflowFlag = (i == Short.MAX_VALUE);
		
		i++;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void DEC() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = (i == (short)0b0);
		overflowFlag = (i == Short.MIN_VALUE);
		
		i--;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void LWDD(NamedRegister register, short address) {
		registers.set(register, memory.get16Bit(address));
	}

	@Override
	public void SWDD(NamedRegister register, short address) {
		memory.set16Bit(address, registers.get(register));
	}

	@Override
	public void SRA() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = ((short)(i & 0b1) == (short)0b1);

		if ((short)(i & 0b1) == (short)0b1)
			overflowFlag = true;
		else
			overflowFlag = false;
		i >>= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SLA() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = ((short)(i & 0b0100000000000000) == (short)0b0100000000000000);
		
		if ((short)(i & 0b0100000000000000) == (short)0b0100000000000000)
			overflowFlag = true;
		else
			overflowFlag = false;
		if (i < 0){
			i <<= 1;
			i |= (1 << 15);
		}
		else{
			i <<= 1;
			i &= ~(1 << 15);
		}
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SRL() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = ((short)(i & 0b1) == (short)0b1);
		
		if ((short)(i & 0b1) == (short)0b1)
			overflowFlag = true;
		else
			overflowFlag = false;
		i >>>= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void SLL() {
		short i = registers.get(NamedRegister.Accu);
		
		carryFlag = ((short)(i & 0b10000000_00000000) == (short)0b10000000_00000000);
		
		if ((short)(i & 0b1000000000000000) == (short)0b1000000000000000)
			overflowFlag = true;
		else
			overflowFlag = false;
		i <<= 1;
		registers.set(NamedRegister.Accu, i);
	}

	@Override
	public void AND(NamedRegister register) {
		short i = registers.get(NamedRegister.Accu);
		short j = registers.get(register);
		registers.set(NamedRegister.Accu, (short)(i&j));
		
	}

	@Override
	public void OR(NamedRegister register) {
		short i = registers.get(NamedRegister.Accu);
		short j = registers.get(register);
		registers.set(NamedRegister.Accu, (short)(i|j));
	}

	@Override
	public void NOT() {
		short i = registers.get(NamedRegister.Accu);
		registers.set(NamedRegister.Accu, (short)(~i));
	}

	@Override
	public void BZ(NamedRegister register) {
		if (registers.get(NamedRegister.Accu) == (short)0){
			cpu.setProgramCounter(registers.get(register));
		}
	}
	
	@Override
	public void BNZ(NamedRegister register) {
		if (registers.get(NamedRegister.Accu) != (short)0){
			cpu.setProgramCounter(registers.get(register));
		}
	}

	@Override
	public void BC(NamedRegister register) {
		if (overflowFlag == true){
			cpu.setProgramCounter(registers.get(register));
		}
	}

	@Override
	public void B(NamedRegister register) {
		cpu.setProgramCounter(registers.get(register));
	}

	@Override
	public void BZD(short address) {
		if (registers.get(NamedRegister.Accu) == (short)0){
			cpu.setProgramCounter(address);
		}
	}

	@Override
	public void BNZD(short address) {
		if (registers.get(NamedRegister.Accu) != (short)0){
			cpu.setProgramCounter(address);
		}
	}

	@Override
	public void BCD(short address) {
		if (overflowFlag == true){
			cpu.setProgramCounter(address);
		}
	}

	@Override
	public void BD(short address) {
		cpu.setProgramCounter(address);
	}
	
	private void setOverflowFlag(short operand1, short operand2, short result) {
		overflowFlag = (
					// Check positive numbers
					((operand1 & (short)0b10000000_00000000) == 0)
					&& ((operand2 & (short)0b10000000_00000000) == 0)
					&& ((result & (short)0b10000000_00000000) != 0)
				)
				|| (
					// Check negative numbers
					((operand1 & (short)0b10000000_00000000) != 0)
					&& ((operand2 & (short)0b10000000_00000000) != 0)
					&& ((result & (short)0b10000000_00000000) == 0)
				);
	}
}
