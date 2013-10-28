package ch.minipowerpc.emulator.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import ch.minipowerpc.emulator.IEmulator;

public class InstructionMemoryPanel extends JPanel {
	private static final long serialVersionUID = 2201979751294303103L;

	private IEmulator emulator;
	
	private InstructionMemoryTableModel tableModel;
	private JTable table;
	

	public InstructionMemoryPanel(IEmulator emulator) {
		this.emulator = emulator;
		
		initialize();
	}

	
	private void initialize() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JLabel title = new JLabel("Instruction memory"); 
		title.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(title, BorderLayout.PAGE_START);
		
		tableModel = new InstructionMemoryTableModel(emulator);
		table = new JTable(tableModel);
		
		// Set column size
		TableColumn column = null;
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(50);
		    }
		    else {
		        column.setPreferredWidth(200);
		    }
		}
		
		table.setFont(new Font("Monospaced", table.getFont().getStyle(), table.getFont().getSize()));
				
		// Wrap table in scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
		int row = (emulator.getCpu().getProgramCounter() - emulator.getConfiguration().getInstructionRangeFrom()) / 2;
		table.setRowSelectionInterval(row, row);
	}
}
