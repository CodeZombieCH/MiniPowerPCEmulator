package ch.minipowerpc.emulator.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ch.minipowerpc.emulator.Emulator;

public class MainWindow extends JFrame implements Observer {
	private static final long serialVersionUID = 6829224920697585349L;
	
	private IEmulatorModel model;
	
	// UI controls
	private InstructionMemoryPanel instructionMemoryPanel;
	private DataMemoryPanel dataMemoryPanel;
	private EmulatorControlPanel emulatorControlPanel;
	private ControlUnitPanel controlUnitPanel;
	

	public MainWindow(IEmulatorModel model) {
		this.model = model;
		((Observable)model).addObserver(this);
		
		initialize();
	}

	private void initialize() {
		setTitle("MiniPowerPC emulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex) {
			
		}
		
		Container content = getContentPane();
		
		
		// Program memory
		/*
		programMemory = new ProgramMemory(emulator);
		content.add(programMemory, BorderLayout.CENTER);
		*/
		
		instructionMemoryPanel = new InstructionMemoryPanel(model); 
		content.add(instructionMemoryPanel, BorderLayout.CENTER);
		
		dataMemoryPanel = new DataMemoryPanel(model); 
		content.add(dataMemoryPanel, BorderLayout.LINE_END);
		
		/*
		JButton button = new JButton("Toggle base");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				base = (base == 2) ? 10 : 2;
				programMemory.setBase(base);
				programMemory.setPosition(programMemory.getPosition() + 2);
				tableModel.setBase(base);
				tableModel.fireTableDataChanged();
			}
			
		});
		content.add(button, BorderLayout.PAGE_END);
		*/
		
		emulatorControlPanel = new EmulatorControlPanel(model);
		content.add(emulatorControlPanel, BorderLayout.PAGE_END);
		
		// Registers and so on
		controlUnitPanel = new ControlUnitPanel(model);
		content.add(controlUnitPanel, BorderLayout.LINE_START);
		
		update(null, null);
		
		setSize(900, 500);
	}

	@Override
	public void update(Observable o, Object arg) {
		instructionMemoryPanel.refresh();
		dataMemoryPanel.refresh();
		emulatorControlPanel.refresh();
		controlUnitPanel.refresh();
	}
	
	public static void main(String[] args) {
		if(args.length != 1) System.exit(-1);
		
		Emulator emulator = new Emulator();
		
		// HACK: Change configuration for special Aufgabe4
		if(args[0].equals("Aufgabe4")) {
			emulator.getConfiguration().setInstructionRangeTo((short)199);
			emulator.getConfiguration().setDataRangeFrom((short)200);
			emulator.getConfiguration().setDataRangeTo((short)299);
		}
		
		try {
			emulator.loadProgram(args[0]);
			emulator.loadMemory(args[0]);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(-2);
		}
		
		MainWindow window = new MainWindow(new EmulatorModel(emulator));
		window.setVisible(true);
	}
}
