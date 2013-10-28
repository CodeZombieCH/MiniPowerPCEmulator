package ch.minipowerpc.emulator.ui;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.minipowerpc.emulator.Utilities;

public class JNumericTextField extends JTextField {
	private static final long serialVersionUID = 8753804770734609839L;
	
	private int value;
	private int digits = 16;
	private int base = 10;
	
	
	public JNumericTextField() {
		this.setEditable(false);
		this.setPreferredSize(new Dimension(120, 20));
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public JNumericTextField(int digits) {
		this();
		this.digits = digits;
	}
	
	public JNumericTextField(int digits, int base) {
		this();
		this.digits = digits;
		this.base = base;
	}
	
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
		super.setText(this.getText());
	}
	
	@Override
	public String getText() {
		if(base == 2 && digits == 16) {
			return Utilities.toBinary((short)value);
		}
		return Integer.toString(value, base);
		
	}
	
	@Override
	public void setText(String t) {
		throw new UnsupportedOperationException("Use setValue() instead");
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
		setValue(value);
	}
}
