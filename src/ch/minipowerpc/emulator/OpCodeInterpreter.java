package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.Registers.NamedRegister;
import ch.minipowerpc.emulator.instructions.*;

public class OpCodeInterpreter implements IOpCodeInterpreter {
	private IALU alu;

	private short register;
	private short address;
	private short number;
	private String command;

	public OpCodeInterpreter(IALU alu) {
		this.alu = alu;
		register = 0;
		address = 0;
		number = 0;
	}

	/**
	 * Kind of a IInstruction factory
	 */
	@Override
	public IInstruction interpret(short opcode) {
		
		// CLR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001010000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new CLR(alu, NamedRegister.values()[register]);
		}
		
		// ADD Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001110000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new ADD(alu, NamedRegister.values()[register]);
		}
		
		// ADDD #Zahl
		if((short)(opcode & 0b1000000000000000) == (short)0b1000000000000000){
			number = (short)(opcode & 0b0111111111111111);
			return new ADDD(alu, number);
		}
		
		// INC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000000100000000){
			return new INC(alu);
		}
		
		// DEC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010000000000){
			return new DEC(alu);
		}
		
		// LWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0100000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			return new LWDD(alu, NamedRegister.values()[register], address);
		}
		
		// SWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0110000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			return new SWDD(alu, NamedRegister.values()[register], address);
		}
		
		// SRA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010100000000){
			return new SRA(alu);
		}
		
		// SLA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100000000000){
			return new SLA(alu);
		}
		
		// SRL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100100000000){
			return new SRL(alu);
		}
		
		// SLL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000110000000000){
			return new SLL(alu);
		}
		
		// AND Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new AND(alu, NamedRegister.values()[register]);
		}
		
		// OR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new OR(alu, NamedRegister.values()[register]);
		}
		
		// NOT
		if((short)(opcode & 0b1111001110000000) == (short)0b0000000010000000){
			return new NOT(alu);
		}
		
		// BZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new BZ(alu, NamedRegister.values()[register]);
		}
		
		// BNZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new BNZ(alu, NamedRegister.values()[register]);
		}
		
		// BC Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new BC(alu, NamedRegister.values()[register]);
		}
		
		// B Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			return new B(alu, NamedRegister.values()[register]);
		}
		
		// BZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011000000000000){
			address = (short)(opcode & 0b0000001111111111);
			return new BZD(alu, address);
		}
		
		// BNZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010100000000000){
			address = (short)(opcode & 0b0000001111111111);
			return new BNZD(alu, address);
		}
		
		// BCD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011100000000000){
			address = (short)(opcode & 0b0000001111111111);
			return new BCD(alu, address);
		}
		
		// BD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010000000000000){
			address = (short)(opcode & 0b0000001111111111);
			return new BD(alu, address);
		}
		
		// END
		if((short)(opcode & 0b1111111111111111) == (short)0b0000000000000000){
			// Return false to notify the emulator to terminate
			//System.out.println("END: Reached the end of the program");
			
			return new END();
		}
		
		// DEBUG
		//System.out.println(command);
		
		return null;
	}
	
	public short getRegister(){
		return register;
	}
	
	public short getAddress(){
		return address;
	}
	
	public short getNumber(){
		return number;
	}
	
	public String getCommand(){
		return command;
	}
}
