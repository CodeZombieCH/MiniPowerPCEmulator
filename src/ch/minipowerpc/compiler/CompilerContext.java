package ch.minipowerpc.compiler;

public class CompilerContext {
	private String fileName;
	private int lineNumber;
	private String line;
	

	public CompilerContext(String fileName) {
		this.fileName = fileName;
	}


	public String getFileName() {
		return fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
