package ch.minipowerpc.emulator.ui;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import ch.minipowerpc.emulator.Utilities;

public class DataMemoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7117097916319996431L;
	
	private IEmulatorModel emulatorModel;
	private short from;
	private short to;
	private String[] columnNames = new String[] { "#", "Byte1", "Byte2", "Word" };
	
	
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
    	else if(column == 3) {
    		return convertValue(emulatorModel.getMemory().get16Bit((short)(from + row*2)));
    	}
    	
    	return null;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return (columnIndex > 0);
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
    	try {
    		
    	if(column == 0) {
    		return;
    	}
    	else if(column == 1) {
    		
    		byte numericValue = Byte.parseByte((String)value, emulatorModel.getBase());
    		emulatorModel.getMemory().setByte((short)(from + row*2), numericValue);
    	}
    	else if(column == 2) {
    		byte numericValue = Byte.parseByte((String)value, emulatorModel.getBase());
    		emulatorModel.getMemory().setByte((short)(from + row*2 + 1), numericValue);
    	}
    	else if(column == 3) {
    		short numericValue = Short.parseShort((String)value, emulatorModel.getBase());
    		emulatorModel.getMemory().set16Bit((short)(from + row*2), numericValue);
    	}
    	}
    	catch(Exception ex) {
    		JOptionPane.showMessageDialog(null, "This number is valid. Bazinga!");
    	}
    }
    
    private String convertValue(byte value) {
    	if(emulatorModel.getBase() == 2) {
    		return Utilities.toBinary(value);
    	}
    	else {
    		return Integer.toString(value, emulatorModel.getBase());
    	}
    }
    
    private String convertValue(short value) {
    	if(emulatorModel.getBase() == 2) {
    		return Utilities.toBinary(value);
    	}
    	else {
    		return Integer.toString(value, emulatorModel.getBase());
    	}
    }
}
