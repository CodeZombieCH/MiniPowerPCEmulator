package ch.minipowerpc.emulator.ui;

import javax.swing.table.AbstractTableModel;

import ch.minipowerpc.emulator.IEmulator;

public class DataMemoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7117097916319996431L;
	
	private IEmulator emulator;
	private short from;
	private short to;
	private String[] columnNames = new String[] { "#", "Byte1", "Byte2" };
	private int base = 10;
	
	
	public DataMemoryTableModel(IEmulator emulator) {
		this.emulator = emulator;
		this.from = emulator.getConfiguration().getDataRangeFrom();
		this.to = emulator.getConfiguration().getDataRangeTo();
	}
	
	public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (to - from)/2;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column) {
    	if(column == 0) {
    		return (from + row*2);
    	}
    	else if(column == 1) {
    		return convertValue(emulator.getMemory().getByte((short)(from + row*2)));
    	}
    	else if(column == 2) {
    		return convertValue(emulator.getMemory().getByte((short)(from + row*2 + 1)));
    	}
    	
    	return null;
    }
    
    
    private String convertValue(short value) {
    	if(base == 2) {
    		return String.format("%8s", Integer.toString(value, base)).replace(' ', '0');
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
