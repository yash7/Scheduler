package scheduler.ui.gen;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;

import scheduler.utilities.UtilityMethods;

import java.time.DayOfWeek;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRADialogGen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private RADetailPanelGen raDetailPanelGen;
	private Connection c;
	private String tableName;
	private int ID;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddRADialogGen dialog = new AddRADialogGen();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	class CancelButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	
	class AddRAOkButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name = raDetailPanelGen.getNameTextField().getText();
			
			if(name.compareTo("") == 0)
			{
				dispose(); // add dialog box showing warning
			}
			else
			{
				try
				{
					Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY ID DESC LIMIT 1;"); // to get last record
					
					int lastID = (rs.getInt("ID"));
					int newID = ++lastID;
					
					addRA(name, newID, stmt);
					
					dispose();
				}
				catch(SQLException s)
				{
					System.err.println(s.getClass().getName() + ": " + s.getMessage());
					System.exit(0);
				}
			}
		}
	}
	
	private void addRA(String name, int ID, Statement stmt) throws SQLException
	{
		int weekdaysWorked = Integer.parseInt(raDetailPanelGen.getWeekdaysWorkedTextField().getText());
		int weekendsWorked = Integer.parseInt(raDetailPanelGen.getWeekendsWorkedTextField().getText());
		
		String sql = 	"INSERT INTO " + tableName + " (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
						"VALUES (" + ID + ", '" + name + "', " + weekendsWorked + ", " + weekdaysWorked + ");";
		stmt.execute(sql);
		
		int unDaysListSize = raDetailPanelGen.getUnavailableDaysListModel().getSize();
		if (unDaysListSize > 0)
		{
			for (int i=0; i<unDaysListSize; i++)
			{
				String tempDay = raDetailPanelGen.getUnavailableDaysListModel().get(i).toString();
				sql = 	"INSERT INTO unavailableDays (ID, unavailableDay) " +
						"VALUES (" + ID + ", '" + tempDay.toLowerCase() + "');";
				stmt.execute(sql);
			}	
		}
		
		int unDatesListSize = raDetailPanelGen.getUnavailableDatesListModel().getSize();
		if (unDatesListSize > 0)
		{
			for (int i = 0; i < unDatesListSize; i++)
			{
				String tempDate = raDetailPanelGen.getUnavailableDatesListModel().get(i);
				sql = 	"INSERT INTO unavailableDates (ID, unavailableDate) " +
						"VALUES (" + ID + ", '" + tempDate + "');";
				stmt.execute(sql);
			}
		}
	}
	
	class EditRAOkButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name = raDetailPanelGen.getNameTextField().getText();
			
			if(name.compareTo("") == 0)
			{
				dispose(); // add dialog box showing warning
			}
			else
			{
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?", "Confirm changes", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION)
				{
					try
					{
						Statement stmt = c.createStatement();
						String sql = ("DELETE FROM unavailableDays WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM unavailableDates WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						sql = ("DELETE FROM " + tableName + " WHERE ID = " + ID + ";");
						stmt.executeUpdate(sql);
						
						addRA(name, ID, stmt);
						
						dispose();
					}
					catch(SQLException s)
					{
						System.err.println(s.getClass().getName() + ": " + s.getMessage());
						System.exit(0);
					}
				}
			}
		}
	}
	
	// "@wbp.parser.constructor" Makes this the default constructor
	
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor 
	 */
	public AddRADialogGen(Connection c, String tableName) 
	{
		this.c = c;
		this.tableName = tableName;
		
		initDialog();
		
//		JLabel nameLabel = new JLabel("Name*:");
//		nameLabel.setBounds(31, 28, 56, 16);
//		contentPanel.add(nameLabel);
		
		initContentPanel();
		
		newRAUnavailableDays();

		initButtonPane();
		
		okButton.addActionListener(new AddRAOkButtonListener());
		
		setTitle("New RA");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		setVisible(true);
	}
	
	private void initDialog()
	{
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}
	
	private void initContentPanel()
	{
		// contentPanel code
		raDetailPanelGen = new RADetailPanelGen();
		contentPanel.add(raDetailPanelGen);
	}
	
	private void newRAUnavailableDays()
	{
		for (int i=1; i<=7; i++)
		{
			raDetailPanelGen.getAvailableDaysListModel().addElement(DayOfWeek.of(i));
		}
	}
	
	private void initButtonPane()
	{
		// buttonPane code
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new CancelButtonListener());
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void getRADetails() throws SQLException
	{
		Statement stmt = c.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE ID = " + ID + ";");
		String name = rs.getString(2);
		int weekendsWorked = rs.getInt(3);
		int weekdaysWorked = rs.getInt(4);
		
		raDetailPanelGen.getNameTextField().setText(name);
		raDetailPanelGen.getWeekendsWorkedTextField().setText(Integer.toString(weekendsWorked));
		raDetailPanelGen.getWeekdaysWorkedTextField().setText(Integer.toString(weekdaysWorked));
		
		rs = stmt.executeQuery("SELECT unavailableDay FROM unavailableDays WHERE ID = " + ID + ";");
		
		while (rs.next())
		{
			DayOfWeek dw = UtilityMethods.getDayOfWeekFromString(rs.getString("unavailableDay"));
			raDetailPanelGen.getUnavailableDaysListModel().addElement(dw);
		}
		
		for (int i=1; i<=7; i++)
		{
			DayOfWeek dw = DayOfWeek.of(i);
			if (!(raDetailPanelGen.getUnavailableDaysListModel().contains(dw)))
			{
				raDetailPanelGen.getAvailableDaysListModel().addElement(dw);
			}
		}
		
		rs = stmt.executeQuery("SELECT unavailableDate FROM unavailableDates where ID = " + ID + ";");
		
		while (rs.next())
		{
			raDetailPanelGen.getUnavailableDatesListModel().addElement(rs.getString("unavailableDate"));
		}
		
//		System.out.print(weekendsWorked);
//		System.out.print(" ");
//		System.out.println(weekdaysWorked);
	}
	
	public AddRADialogGen(Connection c, String tableName, int ID)
	{
		this.c = c;
		this.tableName = tableName;
		this.ID = ID;
		
		initDialog();
		
		initContentPanel();
		
		try
		{
			getRADetails();
		}
		catch(SQLException s)
		{
			System.err.println(s.getClass().getName() + ": " + s.getMessage());
			System.exit(0);
		}
		
		// fill details of selected ra
		
		initButtonPane();
		
		okButton.addActionListener(new EditRAOkButtonListener());
		
		setTitle("Edit RA");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		setVisible(true);
	}
	
	
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public RADetailPanelGen getRADetailPanelGen() {
		return raDetailPanelGen;
	}
}
