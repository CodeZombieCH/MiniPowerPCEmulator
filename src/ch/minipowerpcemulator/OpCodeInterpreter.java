package ch.minipowerpcemulator;

public class OpCodeInterpreter implements IOpCodeInterpreter {

	private short register;
	private short address;
	private short number;
	private String command;

	public OpCodeInterpreter() {
		register = 0;
		address = 0;
		number = 0;
		command = "";
	}

	@Override
	public void interpret(short opcode) {
		// CLR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001010000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "CLR "+register;
		}
		
		// ADD Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001110000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "ADD "+register;
		}
		
		// ADDD #Zahl
		if((short)(opcode & 0b1000000000000000) == (short)0b1000000000000000){
			number = (short)(opcode & 0b0111111111111111);
			command = "ADDD "+number;
		}
		
		// INC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000000100000000){
			command = "INC";
		}
		
		// DEC
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010000000000){
			command = "DEC";
		}
		
		// LWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0100000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			command = "LWDD "+register+", "+address;
		}
		
		// SWDD Rnr, #Adr
		if((short)(opcode & 0b1110000000000000) == (short)0b0110000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			address = (short)(opcode & 0b0000001111111111);
			command = "SWDD "+register+", "+address;
		}
		
		// SRA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000010100000000){
			command = "SRA";
		}
		
		// SLA
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100000000000){
			command = "SLA";
		}
		
		// SRL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000100100000000){
			command = "SRL";
		}
		
		// SLL
		if((short)(opcode & 0b1111111100000000) == (short)0b0000110000000000){
			command = "SLL";
		}
		
		// AND Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "AND "+register;
		}
		
		// OR Rnr
		if((short)(opcode & 0b1111001110000000) == (short)0b0000001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "OR "+register;
		}
		
		// NOT
		if((short)(opcode & 0b1111001110000000) == (short)0b0000000010000000){
			command = "NOT";
		}
		
		// BZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BZ "+register;
		}
		
		// BNZ Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BNZ "+register;
		}
		
		// BC Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001001100000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "BC "+register;
		}
		
		// B Rnr
		if((short)(opcode & 0b1111001100000000) == (short)0b0001000000000000){
			register = (short)(opcode & 0b0000110000000000);
			register >>= 10;
			command = "B "+register;
		}
		
		// BZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011000000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BZD "+address;
		}
		
		// BNZD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010100000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BNZD "+address;
		}
		
		// BCD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0011100000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BCD "+address;
		}
		
		// BD #Adr
		if((short)(opcode & 0b1111100000000000) == (short)0b0010000000000000){
			address = (short)(opcode & 0b0000001111111111);
			command = "BD "+address;
		}
		
		// END
		if((short)(opcode & 0b1111111111111111) == (short)0b0000000000000000){
			command = "END";
		}
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
