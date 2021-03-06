package ch.minipowerpc.emulator.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EmulatorControlPanel extends JPanel {
	private static final long serialVersionUID = 5374854286108970105L;
	
	private IEmulatorModel emulatorModel;
	private JButton fastMode;
	private JButton slowMode;
	private JButton stepMode;
	private JButton stop;
	private JButton toggleBase;
	

	public EmulatorControlPanel(IEmulatorModel emulatorModel) {
		this.emulatorModel = emulatorModel;
		
		initialize();
	}

	
	private void initialize() {
		setLayout(new FlowLayout());
		
		fastMode = new JButton("Fast");
		fastMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				emulatorModel.run();
			}
		});
		add(fastMode);
		
		slowMode = new JButton("Slow");
		slowMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				emulatorModel.runAndNotify();
			}
		});
		add(slowMode);
		
		stepMode = new JButton("Step");
		stepMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean status = emulatorModel.runSingleCycle();
				stepMode.setEnabled(status);
			}
		});
		add(stepMode);
		
		stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stop.setEnabled(false);
				emulatorModel.cancel();
			}
		});
		add(stop);
		
		toggleBase = new JButton("Toggle base");
		toggleBase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				emulatorModel.toggleBase();
			}
		});
		add(toggleBase);
	}
	
	private void setControlMode(boolean isRunning) {
		fastMode.setEnabled(!isRunning);
		slowMode.setEnabled(!isRunning);
		stepMode.setEnabled(!isRunning);
		toggleBase.setEnabled(!isRunning);
		stop.setEnabled(isRunning);
	}
		
	public void refresh() {
		setControlMode(emulatorModel.isRunning());
	}
}
