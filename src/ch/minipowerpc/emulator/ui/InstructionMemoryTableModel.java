package ch.minipowerpc.emulator.ui;

import javax.swing.table.AbstractTableModel;

import ch.minipowerpc.emulator.IEmulator;

public class InstructionMemoryTableModel extends AbstractTableModel {	
	private static final long serialVersionUID = -1473270879765061250L;
	
	private IEmulator emulator;
	private String[] columnNames = new String[] { "#", "OpCode", "Mnemonic" };
	private int base = 2;
	
	
	public InstructionMemoryTableModel(IEmulator emulator) {
		this.emulator = emulator;
	}
	
	public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (
        		emulator.getConfiguration().getInstructionRangeTo()
        		- emulator.getConfiguration().getInstructionRangeFrom()
        		+ 1)
        		/2;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column) {
    	if(column == 0) {
    		return (emulator.getConfiguration().getInstructionRangeFrom() + row*2);
    	}
    	else if(column == 1) {
    		return convertValue(emulator.getMemory().get16Bit((short)(emulator.getConfiguration().getInstructionRangeFrom() + row*2)));
    	}
    	else if(column == 2) {
    		return "n/a";
    	}
    	
    	return null;
    }
    
    
    private String convertValue(short value) {
    	if(base == 2) {
    		return String.format("%16s", Integer.toString(value & 0xFFFF, base)).replace(' ', '0');
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
