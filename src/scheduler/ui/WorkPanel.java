package scheduler.ui;

import java.sql.*;
import java.time.LocalDateTime;
import java.io.File;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import org.sqlite.SQLiteConfig;

import scheduler.ui.gen.WorkPanelGen;
import scheduler.utilities.*;

public class WorkPanel 
{
	class TableSelectionListener implements ListSelectionListener
	{
		private JTable table;
		// private WorkPanelGen workPanelGen;
		private Connection c;
		private String tableName;
		
		public void valueChanged(ListSelectionEvent e)
		{
			// ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			
			if (!(e.getValueIsAdjusting()))
			{
				// System.out.println(lsm.getMinSelectionIndex());
				// int selectedRowIndex = table.getSelectedRow();
				// int selectedRowColumn = table.getSelectedColumn();
				int ID = (int) table.getValueAt(table.getSelectedRow(), 0);
				try
				{
					JTable unNightsTable = WorkPanel.updateUnNightsScrollPane(c, tableName, ID);
					workPanelGen.getUnNightsScrollPane().setViewportView(unNightsTable);
					
					JTable unNightDatesTable = WorkPanel.updateUnNightDatesScrollPane(c, tableName, ID);
					workPanelGen.getUnNightDatesScrollPane().setViewportView(unNightDatesTable);
					
					JTable unDaysTable = WorkPanel.updateUnDaysScrollPane(c, tableName, ID);
					workPanelGen.getUnDaysScrollPane().setViewportView(unDaysTable);
					
					JTable unDayDatesTable = WorkPanel.updateUnDayDatesScrollPane(c, tableName, ID);
					workPanelGen.getUnDayDatesScrollPane().setViewportView(unDayDatesTable);
				}
				catch (SQLException s)
				{
					System.err.println(s.getClass().getName() + ": " + s.getMessage());
					System.exit(0);
				}
			}
		}
		
		public TableSelectionListener(JTable table, Connection c, String tableName)
		{
			this.table = table;
			this.c = c;
			this.tableName = tableName;
		}
	}
	
	class RunButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String sDate = workPanelGen.getStartDatePicker().getModel().getValue().toString();
			String eDate = workPanelGen.getEndDatePicker().getModel().getValue().toString();
			
			try
			{
				String outputFilePath = workPanelGen.getOutputFileTextField().getText();
				if (outputFilePath.compareTo("") == 0)
				{
					JOptionPane.showMessageDialog(workPanelGen, "You must enter a valid location to save the file.", "Empty file path", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Prioritize.run(c, sDate, eDate, outputFilePath);
					run = true;
					
					File f = new File(outputFilePath);
					if (Desktop.isDesktopSupported())
					{
						Desktop.getDesktop().edit(f);
					}
				}
			}
			catch (Exception ex)
			{
				System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
				System.exit(0);
			}
		}
	}
	
	class NewRAButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			addRADialog = new AddRADialog(c, tableName);
			addRADialog.getAddRADialogGen().addWindowListener(new NewRAWindowListener());
			try
			{
				updateRAListScrollPane(c, tableName);
			}
			catch (SQLException s)
			{
				System.err.println(s.getClass().getName() + ": " + s.getMessage());
				System.exit(0);
			}
		}
	}
	
	class EditRAButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selectedRow = raTable.getSelectedRow();
			
			if (selectedRow < 0)
			{
				JOptionPane.showMessageDialog(null, "You must select an RA.", "No RA selected", JOptionPane.OK_OPTION); // fix parent components for all dialogs everywhere
			}
			else
			{
				int ID = (int) raTable.getValueAt(selectedRow, 0);
				addRADialog = new AddRADialog(c, tableName, ID);
				addRADialog.getAddRADialogGen().addWindowListener(new NewRAWindowListener());
				try
				{
					updateRAListScrollPane(c, tableName);
					raTable.setRowSelectionInterval(selectedRow, selectedRow);
				}
				catch (SQLException s)
				{
					System.err.println(s.getClass().getName() + ": " + s.getMessage());
					System.exit(0);
				}
			}
		}
	}
	
	class NewRAWindowListener implements WindowListener
	{
		public void windowActivated(WindowEvent w)
		{
			
		}
		
		public void windowClosed(WindowEvent w)
		{
			addRADialog = null;
			System.out.println("AddRA Dialog disposed");
		}
		
		public void windowClosing(WindowEvent w)
		{
			// Add validation here
		}
		
		public void windowDeactivated(WindowEvent w)
		{
			
		}
		
		public void windowDeiconified(WindowEvent w)
		{
			
		}
		
		public void windowIconified(WindowEvent w)
		{
			
		}
		
		public void windowOpened(WindowEvent w)
		{
			
		}
	}
	
	class RefreshButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				updateRAListScrollPane(c, tableName);
			}
			catch (SQLException s)
			{
				System.err.println(s.getClass().getName() + ": " + s.getMessage());
				System.exit(0);
			}
		}
	}
	
	class DeleteButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selectedRow = raTable.getSelectedRow();
			
			if (selectedRow < 0)
			{
				JOptionPane.showMessageDialog(null, "You must select an RA.", "No RA selected", JOptionPane.OK_OPTION); // fix parent components for all dialogs everywhere
			}
			else
			{
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this RA?", "Confirm Delete RA", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION)
				{
					int ID = (int) raTable.getValueAt(selectedRow, 0);
					
					try
					{
						Statement stmt = c.createStatement();
						String sql = ("DELETE FROM unavailableNights WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM unavailableNightDates WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM unavailableDays WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM unavailableDayDates WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM " + tableName + " WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						updateRAListScrollPane(c, tableName);
						workPanelGen.getUnNightsScrollPane().setViewportView(null);
						workPanelGen.getUnNightDatesScrollPane().setViewportView(null);
						workPanelGen.getUnDaysScrollPane().setViewportView(null);
						workPanelGen.getUnDayDatesScrollPane().setViewportView(null);
					}
					catch (SQLException s)
					{
						System.err.println(s.getClass().getName() + ": " + s.getMessage());
						System.exit(0);
					}
				}
			}
		}
	}
	
	class BrowseButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// "C:/Users/Yash/Desktop/Test_" + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + ".txt"
			JFileChooser c = new JFileChooser();
			// File f = new File("C:/Users/Yash/Desktop/untitled.txt");
			// c.setCurrentDirectory(f);
			// System.out.println(c.getCurrentDirectory().getAbsolutePath());
			int res = c.showSaveDialog(workPanelGen);
			
			if (res == JFileChooser.APPROVE_OPTION)
			{
				workPanelGen.getOutputFileTextField().setText(c.getSelectedFile().getAbsolutePath() + ".txt");
			}
		}
	}
	
	class CommitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (run) // Implement other checks
			{
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to implement the new days and nights worked in the database?", "Confirm changes", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION)
				{
					try
					{
						Prioritize.commit(c);
						updateRAListScrollPane(c, tableName);
					}
					catch (SQLException s)
					{
						System.err.println(s.getClass().getName() + ": " + s.getMessage());
						System.exit(0);
					}
					
				}
			}
			
		}
	}
	
	class DevResetButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Statement stmt = c.createStatement();
				String sql = ("UPDATE Resident_Assistants SET weekendsWorked = 0;");
				stmt.executeUpdate(sql);
				sql = ("UPDATE Resident_Assistants SET weekdaysWorked = 0;");
				stmt.executeUpdate(sql);
				updateRAListScrollPane(c, tableName);
			}
			catch (SQLException s)
			{
				System.err.println(s.getClass().getName() + ": " + s.getMessage());
				System.exit(0);
			}
		}
	}
	
	private WorkPanelGen workPanelGen;
	private Connection c;
	private String tableName;
	private AddRADialog addRADialog = null;
	private JTable raTable;
	private boolean run = false;
	
	public WorkPanel(Connection c, String tableName)
	{
		workPanelGen = new WorkPanelGen();
		this.c = c;
		this.tableName = tableName;
		
		// workPanelGen.getStartDatePanel().add(createStartDatePicker());
		
		try
		{
			updateRAListScrollPane(c, tableName);
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		workPanelGen.getRunButton().addActionListener(new RunButtonListener());
		workPanelGen.getNewRAButton().addActionListener(new NewRAButtonListener());
		workPanelGen.getRefreshButton().addActionListener(new RefreshButtonListener());
		workPanelGen.getDeleteRAButton().addActionListener(new DeleteButtonListener());
		workPanelGen.getEditRAButton().addActionListener(new EditRAButtonListener());
		workPanelGen.getBrowseButton().addActionListener(new BrowseButtonListener());
		workPanelGen.getCommitButton().addActionListener(new CommitButtonListener());
		workPanelGen.getDevResetButton().addActionListener(new DevResetButtonListener());
		workPanelGen.getOutputFileTextField().setText("C:\\Users\\Yash\\Desktop\\test20.txt");
	}
	
//	private static JDatePickerImpl createStartDatePicker()
//	{
//		// UtilDateModel model = new UtilDateModel();
//		SqlDateModel model = new SqlDateModel();
//		model.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue() - 1), LocalDateTime.now().getDayOfMonth());
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		
//		model.setSelected(true);
//		
//		String sd = datePicker.getModel().getValue().toString();
//		System.out.println(sd);
//		
//		return datePicker;
//	}
	
	public WorkPanelGen getWorkPanelGen() // return type changed from JComponent
	{
		return workPanelGen;
	}
	
	public Connection getConnection()
	{
		return c;
	}
	
	public void updateRAListScrollPane(Connection c, String tableName) throws SQLException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY ID ASC;");
		
		raTable = new JTable(buildTableModel(rs));
		raTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// raTable.getSelectionModel().setSelectionInterval(0, 0);
		raTable.getSelectionModel().addListSelectionListener(new TableSelectionListener(raTable, c, tableName));
		
		workPanelGen.getRAListScrollPane().setViewportView(raTable);
		
		// workPanelGen.getRAListPanel().add(new JScrollPane(raTable));
		
		//JOptionPane.showMessageDialog(null, new JScrollPane(raTable));
		
	}
	
	public static JTable updateUnNightsScrollPane(Connection c, String tableName, int ID) throws SQLException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableNight FROM unavailableNights where ID = " + ID + ";"); // Fix tableName 
		
		JTable table = new JTable(buildTableModel(rs));
		
		return table;
	}
	
	public static JTable updateUnNightDatesScrollPane(Connection c, String tableName, int ID) throws SQLException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableNightDate FROM unavailableNightDates where ID = " + ID + ";"); // Fix tableName 
		
		JTable table = new JTable(buildTableModel(rs));
		
		return table;
	}
	
	public static JTable updateUnDaysScrollPane(Connection c, String tableName, int ID) throws SQLException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableDay FROM unavailableDays where ID = " + ID + ";"); // Fix tableName 
		
		JTable table = new JTable(buildTableModel(rs));
		
		return table;
	}
	
	public static JTable updateUnDayDatesScrollPane(Connection c, String tableName, int ID) throws SQLException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableDayDate FROM unavailableDayDates where ID = " + ID + ";"); // Fix tableName 
		
		JTable table = new JTable(buildTableModel(rs));
		
		return table;
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
	{
		ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++)
		{
			columnNames.add(metaData.getColumnName(i));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next())
		{
			Vector<Object> ar = new Vector<Object>();
			for (int i = 1; i <= columnCount; i++)
			{
				ar.add(rs.getObject(i));
			}
			data.add(ar);
		}
		
		return new DefaultTableModel(data, columnNames);
		
	}
	
//	public static void main(String[] args)
//	{
//		Connection c = null;
//		
//		try
//		{
//			Class.forName("org.sqlite.JDBC");
//			SQLiteConfig config = new SQLiteConfig();
//			config.enforceForeignKeys(true);
//			c = DriverManager.getConnection("jdbc:sqlite:database.db", config.toProperties());
//			
//			WorkPanel wp = new WorkPanel();
//			wp.updateRAListScrollPane(c, "Resident_Assistants");
//			
//		}
//		catch(Exception e)
//		{
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//	}
	
}
