package ch.minipowerpc.emulator.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import ch.minipowerpc.emulator.IEmulator;

public class DataMemoryPanel extends JPanel {
	private static final long serialVersionUID = -5412923866092956772L;

	private IEmulator emulator;
	
	private DataMemoryTableModel tableModel;
	private JTable table;
	

	public DataMemoryPanel(IEmulator emulator) {
		this.emulator = emulator;
		
		initialize();
	}

	
	private void initialize() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JLabel title = new JLabel("Data memory"); 
		title.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(title, BorderLayout.PAGE_START);
		
		tableModel = new DataMemoryTableModel(emulator);
		table = new JTable(tableModel);
		
		// Set column size
		TableColumn column = null;
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(50);
		    } else {
		        column.setPreferredWidth(100);
		    }
		}

		// Wrap table in scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(200, 0));
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
