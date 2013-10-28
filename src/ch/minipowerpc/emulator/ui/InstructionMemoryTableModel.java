package ch.minipowerpc.emulator.ui;

import javax.swing.table.AbstractTableModel;

import ch.minipowerpc.emulator.IOpCodeInterpreter;
import ch.minipowerpc.emulator.OpCodeInterpreter;
import ch.minipowerpc.emulator.Utilities;
import ch.minipowerpc.emulator.instructions.IInstruction;

public class InstructionMemoryTableModel extends AbstractTableModel {	
	private static final long serialVersionUID = -1473270879765061250L;
	
	private IEmulatorModel emulatorModel;
	private IOpCodeInterpreter opCodeInterpreter;
	private String[] columnNames = new String[] { "#", "OpCode", "Mnemonic" };
	private int base = 2;
	
	
	public InstructionMemoryTableModel(IEmulatorModel emulatorModel) {
		this.emulatorModel = emulatorModel;
		this.opCodeInterpreter = new OpCodeInterpreter(null);
	}
	
	public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (
        		emulatorModel.getConfiguration().getInstructionRangeTo()
        		- emulatorModel.getConfiguration().getInstructionRangeFrom()
        		+ 1)
        		/2;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column) {
    	if(column == 0) {
    		return (emulatorModel.getConfiguration().getInstructionRangeFrom() + row*2);
    	}
    	else if(column == 1) {
    		return convertValue(emulatorModel.getMemory().get16Bit((short)(emulatorModel.getConfiguration().getInstructionRangeFrom() + row*2)));
    	}
    	else if(column == 2) {
    		IInstruction instruction = opCodeInterpreter.interpret(emulatorModel.getMemory().get16Bit((short)(emulatorModel.getConfiguration().getInstructionRangeFrom() + row*2)));
    		if(instruction != null) {
    			return instruction.toString();
    		}
    		else {
    			return "n/a";
    		}
    	}
    	
    	return null;
    }
    
    
    private String convertValue(short value) {
    	if(base == 2) {
    		return Utilities.toBinary(value);
    	}
    	else {
    		return Integer.toString(value, base);
    	}
    }
    

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

}
