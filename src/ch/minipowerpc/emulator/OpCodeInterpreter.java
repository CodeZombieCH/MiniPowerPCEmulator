package ch.minipowerpc.emulator;

import ch.minipowerpc.emulator.Registers.NamedRegister;

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
		command = "";
	}

	@Override
	public boolean interpret(short opcode) {
		
		// CLR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001010000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "CLR "+register;
			alu.CLR(NamedRegister.values()[register]);
		}
		
		// ADD Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001110000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "ADD "+register;
			alu.ADD(NamedRegister.values()[register]);
		}
		
		// ADDD #Zahl
		if((short)(opcode & 0b1000000000000000) == (short)0b1000000000000000){
			number = (short)(opcode & 0b0111111111111111);
			command = "ADDD "+number;
			alu.ADDD(number);
		}
		
		// INC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000000100000000){
			command = "INC";
			alu.INC();
		}
		
		// DEC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010000000000){
			command = "DEC";
			alu.DEC();
		}
		
		// LWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0100000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			command = "LWDD "+register+", "+address;
			alu.LWDD(NamedRegister.values()[register], address);
		}
		
		// SWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0110000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			command = "SWDD "+register+", "+address;
			alu.SWDD(NamedRegister.values()[register], address);
		}
		
		// SRA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010100000000){
			command = "SRA";
			alu.SRA();
		}
		
		// SLA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100000000000){
			command = "SLA";
			alu.SLA();
		}
		
		// SRL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100100000000){
			command = "SRL";
			alu.SRL();
		}
		
		// SLL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000110000000000){
			command = "SLL";
			alu.SLL();
		}
		
		// AND Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "AND "+register;
			alu.AND(NamedRegister.values()[register]);
		}
		
		// OR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "OR "+register;
			alu.OR(NamedRegister.values()[register]);
		}
		
		// NOT
		if((short)(opcode & 0b1111001110000000) == (short)0b0000000010000000){
			command = "NOT";
			alu.NOT();
		}
		
		// BZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BZ "+register;
			alu.BZ(NamedRegister.values()[register]);
		}
		
		// BNZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BNZ "+register;
			alu.BNZ(NamedRegister.values()[register]);
		}
		
		// BC Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BC "+register;
			alu.BC(NamedRegister.values()[register]);
		}
		
		// B Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "B "+register;
			alu.B(NamedRegister.values()[register]);
		}
		
		// BZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011000000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BZD "+address;
			alu.BZD(address);
		}
		
		// BNZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010100000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BNZD "+address;
			alu.BNZD(address);
		}
		
		// BCD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011100000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BCD "+address;
			alu.BCD(address);
		}
		
		// BD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010000000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BD "+address;
			alu.BD(address);
		}
		
		// END
		if((short)(opcode & 0b1111111111111111) == (short)0b0000000000000000){
			command = "END";
			// Return false to notify the emulator to terminate
			//System.out.println("END: Reached the end of the program");
			
			return false;
		}
		
		// DEBUG
		//System.out.println(command);
		
		return true;
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
