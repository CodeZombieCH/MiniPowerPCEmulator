package ch.minipowerpc.emulator.ui;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.minipowerpc.emulator.Utilities;

public class JNumericTextField extends JTextField {
	private static final long serialVersionUID = 8753804770734609839L;
	
	private int value;
	private int base = 10;
	

	public JNumericTextField() {
		this.setEditable(false);
		this.setPreferredSize(new Dimension(80, 20));
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public JNumericTextField(int base) {
		this();
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
