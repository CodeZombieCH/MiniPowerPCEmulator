package ch.minipowerpc.emulator.ui;

import java.awt.GridLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.minipowerpc.emulator.Registers.NamedRegister;

public class ControlUnitPanel extends JPanel {
	private static final long serialVersionUID = -1791783695848045272L;

	private IEmulatorModel emulatorModel;
	
	private JNumericTextField programCounterField;
	private JNumericTextField instructionRegister;
	private JNumericTextField cycleCount;
	private JNumericTextField accu;
	private JNumericTextField register1;
	private JNumericTextField register2;
	private JNumericTextField register3;
	private JNumericTextField result;
	private JCheckBox carryFlag;
	private JCheckBox overflowFlag;


	public ControlUnitPanel(IEmulatorModel emulatorModel) {
		this.emulatorModel = emulatorModel;
		
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
		
		add(new JLabel("Result (#504-#507)"));
		result = new JNumericTextField(32);
		add(result);
		
		add(new JLabel("Status register"));
		carryFlag = new JCheckBox("Carry flag");
		carryFlag.setEnabled(false);
		add(carryFlag);
		
		add(new JLabel(""));
		overflowFlag = new JCheckBox("Overflow flag");
		overflowFlag.setEnabled(false);
		add(overflowFlag);
	}
	
	public void refresh() {
		programCounterField.setValue(emulatorModel.getCPU().getProgramCounter());
		programCounterField.setBase(emulatorModel.getBase());
		
		instructionRegister.setValue(emulatorModel.getCPU().getInstructionRegister());
		instructionRegister.setBase(emulatorModel.getBase());
		
		cycleCount.setValue(emulatorModel.getCPU().getCycleCount());
		//cycleCount.setBase(emulatorModel.getBase());
		
		accu.setValue(emulatorModel.getRegisters().get(NamedRegister.Accu));
		accu.setBase(emulatorModel.getBase());
		
		register1.setValue(emulatorModel.getRegisters().get(NamedRegister.R1));
		register1.setBase(emulatorModel.getBase());
		
		register2.setValue(emulatorModel.getRegisters().get(NamedRegister.R2));
		register2.setBase(emulatorModel.getBase());
		
		register3.setValue(emulatorModel.getRegisters().get(NamedRegister.R3));
		register3.setBase(emulatorModel.getBase());
		
		
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.put(emulatorModel.getMemory().getByte((short)504));
		bb.put(emulatorModel.getMemory().getByte((short)505));
		bb.put(emulatorModel.getMemory().getByte((short)506));
		bb.put(emulatorModel.getMemory().getByte((short)507));
		bb.rewind();
		result.setValue(bb.getInt());
		result.setBase(emulatorModel.getBase());
		
		carryFlag.setSelected(emulatorModel.getALU().getCarryFlag());
		overflowFlag.setSelected(emulatorModel.getALU().getOverflowFlag());
	}
}
