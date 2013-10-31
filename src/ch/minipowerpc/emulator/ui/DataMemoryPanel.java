package ch.minipowerpc.emulator.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class DataMemoryPanel extends JPanel {
	private static final long serialVersionUID = -5412923866092956772L;

	private IEmulatorModel emulatorModel;
	
	private DataMemoryTableModel tableModel;
	private JTable table;
	

	public DataMemoryPanel(IEmulatorModel emulatorModel) {
		this.emulatorModel = emulatorModel;
		
		initialize();
	}

	
	private void initialize() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JLabel title = new JLabel("Data memory"); 
		title.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(title, BorderLayout.PAGE_START);
		
		tableModel = new DataMemoryTableModel(emulatorModel);
		table = new JTable(tableModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Set column size
		TableColumn column = null;
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(50);
		    }
		    else if(i == 3) {
		        column.setPreferredWidth(200);
		        column.setCellRenderer(rightRenderer);
		    }
		    else {
		        column.setPreferredWidth(100);
		        column.setCellRenderer(rightRenderer);
		    }
		}
		
		table.setFont(new Font("Monospaced", table.getFont().getStyle(), table.getFont().getSize()));
		table.getTableHeader().setResizingAllowed(false);

		// Wrap table in scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(300, 0));
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
