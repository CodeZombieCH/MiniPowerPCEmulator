package ch.minipowerpc.compiler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.minipowerpc.emulator.Utilities;

public class Compiler {
	private final Pattern operationPattern = Pattern.compile("^(\\w+)(.*?)(//.+)?");
	private final Pattern registerPattern = Pattern.compile("^(R\\d|Accu)$");
	private final Pattern addressPattern = Pattern.compile("^#(\\d+)$");
	private final Pattern memoryPattern = Pattern.compile("^@(\\d+)\\s(-?\\d+)");
	
	
	public Compiler() {
		
	}
	
	
	public void compile(String fileName) throws CompilerException, FileNotFoundException, IOException {
		fileName = "programs/" + fileName;
		compile(fileName + ".asm", fileName + ".bin", fileName + ".mem");
	}
	
	public void compile(String sourcePath, String binPath, String memPath) throws CompilerException, IOException {
		BufferedReader reader = null;
		BufferedOutputStream outBin = null;
		BufferedOutputStream outMem = null;
		
		CompilerContext context = new CompilerContext(sourcePath);
		
		try {
			reader = new BufferedReader(new FileReader(sourcePath));
			outBin = new BufferedOutputStream(new FileOutputStream(binPath));
			outMem = new BufferedOutputStream(new FileOutputStream(memPath));
	
	        String line;
	        int lineNumber = 0;
	        while((line = reader.readLine()) != null) {
	        	lineNumber++;
	        	
	        	// Set context
	        	context.setLine(line);
	        	context.setLineNumber(lineNumber);
	        	
	        	// Skip comments
	        	if(line.isEmpty() || line.startsWith("#")) {
	        		continue;
	        	}
	        	else if(line.startsWith("@")) {
	        		byte[] memory = parseMemory(line, context);
	        		Utilities.printMemoryInstruction(memory);
	        		
	        		outMem.write(memory);
	        	}
	        	else {
		        	short opcode = parse(line, context);
		        	System.out.println(String.format("%04X", opcode & 0xFFFF));
		        	
		    		ByteBuffer bb = ByteBuffer.allocate(2);
		    		bb.order(ByteOrder.BIG_ENDIAN);
		    		bb.putShort(opcode);

		    		outBin.write(bb.array());
	        	}
	        }
	        
	        outBin.flush();
	        outMem.flush();
		}
		finally {
			if(reader != null) reader.close();
			if(outBin != null) outBin.close();
			if(outMem != null) outMem.close();
		}
	}

	public short parse(String line) throws CompilerException {
		return parse(line, new CompilerContext("Dummy"));
	}
	
	public short parse(String line, CompilerContext context) throws CompilerException {
		
		Matcher matcher = operationPattern.matcher(line);
		if(!matcher.matches()) {
			throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
		}
		
		String operation = matcher.group(1);
		String[] arguments = matcher.group(2).trim().split("\\s*,\\s*");
		
		int register;
		int address;
		
		switch(operation) {
			// Arithmetic operations
			case "CLR":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				System.out.println("CLR R" + register);
				return (short)(0b0000_00_10_10000000 | ((register & 0b11) << 10));
			case "ADD":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				System.out.println("ADD R" + register);
				return (short)(0b0000_00_11_10000000 | ((register & 0b11) << 10));				
			case "ADDD":
				throw new UnsupportedOperationException("Not yet implemented");
			case "INC":
				System.out.println("INC");
				return 0b0000000100000000;
			case "DEC":
				System.out.println("DEC");
				return 0b0000010000000000;
				
			
			// Memory and register operations
			case "LWDD":
				if(arguments.length != 2)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				address = getAddress(arguments[1], context);
				System.out.println("LWDD R" + register + ", #" + address);
				return (short)(0b0100_00_0000000000 | ((register & 0b11) << 10) | (address & 0b000000_1111111111));
			case "SWDD":
				if(arguments.length != 2)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				address = getAddress(arguments[1], context);
				System.out.println("SWDD R" + register + ", #" + address);
				return (short)(0b0110_00_0000000000 | ((register & 0b11) << 10) | (address & 0b000000_1111111111));
				
			
			// Shift operations
			case "SRA":
				System.out.println("SRA");
				return 0b00000101_00000000;
			case "SLA":
				System.out.println("SLA");
				return 0b00001000_00000000;
			case "SRL":
				System.out.println("SRL");
				return 0b00001001_00000000;
			case "SLL":
				System.out.println("SLL");
				return 0b00001100_00000000;
			
				
			// Logical operations
			case "AND":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				System.out.println("AND R" + register);
				return (short)(0b0000_00_100_0000000 | ((register & 0b11) << 10));
			case "OR":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				register = getRegister(arguments[0], context);
				System.out.println("AND R" + register);
				return (short)(0b0000_00_110_0000000 | ((register & 0b11) << 10));
			case "NOT":
				System.out.println("NOT");
				return 0b00000000_10000000;
				
				
			// Indirect jumps
				
				
			// Direct jumps
			case "BZD":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				address = getAddress(arguments[0], context);
				System.out.println("BZD #" + address);
				return (short)(0b001100_0000000000 | (address & 0b1111111111));
			case "BNZD":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				address = getAddress(arguments[0], context);
				System.out.println("BNZD #" + address);
				return (short)(0b001010_0000000000 | (address & 0b1111111111));
			case "BCD":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				address = getAddress(arguments[0], context);
				System.out.println("BCD #" + address);
				return (short)(0b001110_0000000000 | (address & 0b1111111111));
			case "BD":
				if(arguments.length != 1)
					throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
				address = getAddress(arguments[0], context);
				System.out.println("BD #" + address);
				return (short)(0b001000_0000000000 | (address & 0b1111111111));
			
				
			// Helper command to mark the end of the program
			case "END":
				System.out.println("END");
				return 0b00000000_00000000;

			default:
				throw new CompilerException("Unknown operation", context.getLineNumber(), context.getLine(), context.getFileName());
		}
	}
	
	private int getRegister(String argument, CompilerContext context) throws CompilerException {
		Matcher matcher = registerPattern.matcher(argument);
		if(!matcher.matches()) {
			throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
		}
		
		switch(matcher.group(0)) {
			case "Accu":
			case "R0":
				return 0;
			case "R1":
				return 1;
			case "R2":
				return 2;
			case "R3":
				return 3;
			default:
				throw new CompilerException("Invalid register", context.getLineNumber(), context.getLine(), context.getFileName());
		}
	}
	
	private int getAddress(String argument, CompilerContext context) throws CompilerException {
		Matcher matcher = addressPattern.matcher(argument);
		if(!matcher.matches()) {
			throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
		}
		
		return Integer.parseInt(matcher.group(1));
	}
	
	private byte[] parseMemory(String line, CompilerContext context) throws CompilerException {
		Matcher matcher = memoryPattern.matcher(line);
		if(!matcher.matches()) {
			throw new CompilerException("Syntax error", context.getLineNumber(), context.getLine(), context.getFileName());
		}
		
		short address = Short.parseShort(matcher.group(1));
		short value = Short.parseShort(matcher.group(2));
		
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.putShort(address);
		bb.putShort(value);
		
		return bb.array();
	}
	
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments");
			System.exit(-1);
		}
		
		try {
			Compiler compiler = new Compiler();
			compiler.compile(args[0]);
			
			System.out.println("Compilation succeeded!");
			System.exit(0);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Compilation failed!");
			System.exit(-2);
		}
	}
}
