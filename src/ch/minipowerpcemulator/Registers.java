package ch.minipowerpcemulator;

public class Registers implements IRegisters {

	public Registers() {
		// TODO Auto-generated constructor stub
	}

	
	public enum NamedRegister {
		Accu,
		R1,
		R2,
		R3
	}
	

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}


	@Override
	public short get(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}


	@Override
	public void set(NamedRegister register) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void set(NamedRegister register, short value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
