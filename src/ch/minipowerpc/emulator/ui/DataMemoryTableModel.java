package ch.minipowerpc.emulator.ui;

import javax.swing.table.AbstractTableModel;

import ch.minipowerpc.emulator.Utilities;

public class DataMemoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7117097916319996431L;
	
	private IEmulatorModel emulatorModel;
	private short from;
	private short to;
	private String[] columnNames = new String[] { "#", "Byte1", "Byte2" };
	
	
	public DataMemoryTableModel(IEmulatorModel emulatorModel) {
		this.emulatorModel = emulatorModel;
		this.from = emulatorModel.getConfiguration().getDataRangeFrom();
		this.to = emulatorModel.getConfiguration().getDataRangeTo();
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
    		return convertValue(emulatorModel.getMemory().getByte((short)(from + row*2)));
    	}
    	else if(column == 2) {
    		return convertValue(emulatorModel.getMemory().getByte((short)(from + row*2 + 1)));
    	}
    	
    	return null;
    }
    
    
    private String convertValue(byte value) {
    	if(emulatorModel.getBase() == 2) {
    		return Utilities.toBinary(value);
    	}
    	else {
    		return Integer.toString(value, emulatorModel.getBase());
    	}
    }
}
