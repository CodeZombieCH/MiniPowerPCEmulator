package ch.minipowerpcemulator;

public class Registers implements IRegisters {
	private short[] registers = new short[4];

	public Registers() {
		
	}
	
	public enum NamedRegister {
		Accu(0),
		R1(1),
		R2(2),
		R3(3);
		
		private int number;
		
		NamedRegister(int number) {
			this.number = number;
		}
		
		public int getNumber() {
			return number;
		}
	}
	
	@Override
	public void initialize() {
		
	}

	@Override
	public short get(NamedRegister register) {
		return registers[register.number];
	}
	
	@Override
	public void set(NamedRegister register, short value) {
		registers[register.number] = value;
	}
}
