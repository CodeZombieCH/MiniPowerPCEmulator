package ch.minipowerpc.compiler;

public class CompilerException extends Exception {
	private static final long serialVersionUID = 1950109666386207579L;
	
	private int line;
	private String operation;
	private String fileName;

	
	public CompilerException() {
		
	}

	public CompilerException(String message, int line, String operation, String fileName) {
		super(message);
		this.line = line;
		this.operation = operation;
		this.setFileName(fileName);
	}
	
	
	public int getLine() {
		return line;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format("%s on line %d: \"%s\" in file %s", getMessage(), line, operation, fileName);
	}
}
