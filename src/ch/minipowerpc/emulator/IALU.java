package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.Registers.NamedRegister;

/**
 * 16-bit ALU The current implementation assumes every opcode is executed by the
 * ALU (even memory operations not doing calculations (no segmentation)
 */
public interface IALU {
	/**
	 * Carry Flag
	 * ----------
	 * 
	 * The rules for turning on the carry flag in binary/integer math are two:
	 * 
	 * 1. The carry flag is set if the addition of two numbers causes a carry
	 * out of the most significant (leftmost) bits added.
	 * 
	 * 1111 + 0001 = 0000 (carry flag is turned on)
	 * 
	 * 2. The carry (borrow) flag is also set if the subtraction of two numbers
	 * requires a borrow into the most significant (leftmost) bits subtracted.
	 * 
	 * 0000 - 0001 = 1111 (carry flag is turned on)
	 * 
	 * Otherwise, the carry flag is turned off (zero).
	 * - 0111 + 0001 = 1000 (carry flag is turned off [zero])
	 * - 1000 - 0001 = 0111 (carry flag is turned off [zero])
	 * 
	 * In unsigned arithmetic, watch the carry flag to detect errors. In signed
	 * arithmetic, the carry flag tells you nothing interesting.
	 * 
	 * @see http://teaching.idallen.com/dat2343/10f/notes/040_overflow.txt
	 * 
	 * @return carry flag
	 */
	boolean getCarryFlag();

	/**
	 * Overflow Flag
	 * -------------
	 *
	 * The rules for turning on the overflow flag in binary/integer math are two:
	 *
	 * 1. If the sum of two numbers with the sign bits off yields a result number
	 *    with the sign bit on, the "overflow" flag is turned on.
	 *
	 *    0100 + 0100 = 1000 (overflow flag is turned on)
	 *
	 * 2. If the sum of two numbers with the sign bits on yields a result number
	 *    with the sign bit off, the "overflow" flag is turned on.
	 *
	 *    1000 + 1000 = 0000 (overflow flag is turned on)
	 *
	 * Otherwise, the overflow flag is turned off.
	 *  * 0100 + 0001 = 0101 (overflow flag is turned off)
	 *  * 0110 + 1001 = 1111 (overflow flag is turned off)
	 *  * 1000 + 0001 = 1001 (overflow flag is turned off)
	 *  * 1100 + 1100 = 1000 (overflow flag is turned off)
	 *
	 * Note that you only need to look at the sign bits (leftmost) of the three
	 * numbers to decide if the overflow flag is turned on or off.
	 *
	 * If you are doing two's complement (signed) arithmetic, overflow flag on
	 * means the answer is wrong - you added two positive numbers and got a
	 * negative, or you added two negative numbers and got a positive.
	 *
	 * If you are doing unsigned arithmetic, the overflow flag means nothing
	 * and should be ignored.
	 *
	 * The rules for two's complement detect errors by examining the sign of
	 * the result.  A negative and positive added together cannot be wrong,
	 * because the sum is between the addends. Since both of the addends fit
	 * within the allowable range of numbers, and their sum is between them, it
	 * must fit as well.  Mixed-sign addition never turns on the overflow flag.
	 *
	 * In signed arithmetic, watch the overflow flag to detect errors.
	 * In unsigned arithmetic, the overflow flag tells you nothing interesting.
	 * 
	 * @see http://teaching.idallen.com/dat2343/10f/notes/040_overflow.txt
	 * 
	 * @return overflow flag
	 */
	boolean getOverflowFlag();

	void CLR(NamedRegister register);

	void ADD(NamedRegister register);

	void ADDD(short number);

	void INC();

	void DEC();

	void LWDD(NamedRegister register, short address);

	void SWDD(NamedRegister register, short address);

	void SRA();

	void SLA();

	void SRL();

	void SLL();

	void AND(NamedRegister register);

	void OR(NamedRegister register);

	void NOT();

	void BZ(NamedRegister register);

	void BNZ(NamedRegister register);

	void BC(NamedRegister register);

	void B(NamedRegister register);

	void BZD(short address);

	void BNZD(short address);

	void BCD(short address);

	void BD(short address);
}