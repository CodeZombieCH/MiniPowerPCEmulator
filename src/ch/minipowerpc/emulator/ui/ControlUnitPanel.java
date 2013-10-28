package ch.minipowerpc.emulator.ui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.minipowerpc.emulator.Emulator;
import ch.minipowerpc.emulator.Registers.NamedRegister;

public class ControlUnitPanel extends JPanel {
	private static final long serialVersionUID = -1791783695848045272L;

	private Emulator emulator;
	
	private JNumericTextField programCounterField;
	private JNumericTextField instructionRegister;
	private JNumericTextField cycleCount;
	private JNumericTextField accu;
	private JNumericTextField register1;
	private JNumericTextField register2;
	private JNumericTextField register3;
	private JCheckBox carryFlag;


	public ControlUnitPanel(Emulator emulator) {
		this.emulator = emulator;
		
		initialize();
	}

	
	private void initialize() {
		setLayout(new GridLayout(0, 2, 10, 10));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel title = new JLabel("<html><strong>Control unit</strong></html>"); 
		title.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(title);
		add(new JLabel(""));
		
		add(new JLabel("Program counter:"));
		programCounterField = new JNumericTextField();
		add(programCounterField);
		
		add(new JLabel("Instruction register:"));
		instructionRegister = new JNumericTextField();
		add(instructionRegister);
		
		add(new JLabel("Cycle count:"));
		cycleCount = new JNumericTextField();
		add(cycleCount);
		
		add(new JLabel("Accu:"));
		accu = new JNumericTextField();
		add(accu);
		
		add(new JLabel("Register #1:"));
		register1 = new JNumericTextField();
		add(register1);

		add(new JLabel("Register #2:"));
		register2 = new JNumericTextField();
		add(register2);

		add(new JLabel("Register #3:"));
		register3 = new JNumericTextField();
		add(register3);
		
		add(new JLabel("Status register"));
		carryFlag = new JCheckBox("Carry flag");
		add(carryFlag);
	}
	
	public void refresh() {
		programCounterField.setValue(emulator.getCpu().getProgramCounter());
		instructionRegister.setValue(emulator.getCpu().getInstructionRegister());
		cycleCount.setValue(emulator.getCpu().getCycleCount());
		accu.setValue(emulator.getCpu().getRegisters().get(NamedRegister.Accu));
		register1.setValue(emulator.getCpu().getRegisters().get(NamedRegister.R1));
		register2.setValue(emulator.getCpu().getRegisters().get(NamedRegister.R2));
		register3.setValue(emulator.getCpu().getRegisters().get(NamedRegister.R3));
		carryFlag.setSelected(emulator.getCpu().getALU().getCarryFlag());
	}
}
