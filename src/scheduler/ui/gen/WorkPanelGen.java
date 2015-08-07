package scheduler.ui.gen;

import scheduler.utilities.DateLabelFormatter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.time.LocalDateTime;
import java.util.Properties;

import org.jdatepicker.impl.*;
import javax.swing.JButton;
import javax.swing.JTextField;

public class WorkPanelGen extends JPanel 
{
	private JScrollPane RAListScrollPane;
	private JScrollPane UnNightsScrollPane;
	private JScrollPane UnNightDatesScrollPane;
	private JPanel StartDatePanel;
	private JPanel EndDatePanel;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private JButton RunButton;
	private JButton NewRAButton;
	private JButton RefreshButton;
	private JButton DeleteRAButton;
	private JButton EditRAButton;
	private JTextField OutputFileTextField;
	private JButton BrowseButton;
	private JButton CommitButton;
	private JLabel EndDateLabel;
	private JLabel SaveFileLabel;
	private JLabel UnNightsLabel;
	private JLabel UnNightDatesLabel;
	private JLabel RALabel;
	private JButton DevResetButton;
	
	/**
	 * Create the panel.
	 */
	public WorkPanelGen() 
	{
		setLayout(null);
		setBounds(0, 0, 1000, 725);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 1000, 725);
		add(MainPanel);
		MainPanel.setLayout(null);
		
		RAListScrollPane = new JScrollPane();
		RAListScrollPane.setBounds(46, 94, 476, 241);
		MainPanel.add(RAListScrollPane);
		
		UnNightsScrollPane = new JScrollPane();
		UnNightsScrollPane.setBounds(46, 458, 210, 189);
		MainPanel.add(UnNightsScrollPane);
		
		UnNightDatesScrollPane = new JScrollPane();
		UnNightDatesScrollPane.setBounds(312, 458, 210, 189);
		MainPanel.add(UnNightDatesScrollPane);
		
		StartDatePanel = new JPanel();
		StartDatePanel.setBounds(635, 94, 304, 43);
		MainPanel.add(StartDatePanel);
		
		SqlDateModel model1 = new SqlDateModel();
		model1.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue() - 1), LocalDateTime.now().getDayOfMonth());
//		startDatePicker = createDatePicker(model1);
//		StartDatePanel.add(startDatePicker);
		
		EndDatePanel = new JPanel();
		EndDatePanel.setBounds(635, 190, 304, 43);
		MainPanel.add(EndDatePanel);
		
		SqlDateModel model2 = new SqlDateModel();
		model2.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue()), LocalDateTime.now().getDayOfMonth());
//		endDatePicker = createDatePicker(model2);
//		EndDatePanel.add(endDatePicker);
		
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		startDatePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		
		StartDatePanel.add(startDatePicker);
		model1.setSelected(true);
		
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		endDatePicker = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		
		EndDatePanel.add(endDatePicker);
		model2.setSelected(true);
		
		RunButton = new JButton("Run");
		RunButton.setBounds(566, 436, 97, 25);
		MainPanel.add(RunButton);
		
		NewRAButton = new JButton("New RA");
		NewRAButton.setBounds(207, 348, 97, 25);
		MainPanel.add(NewRAButton);
		
		RefreshButton = new JButton("Refresh");
		RefreshButton.setBounds(425, 56, 97, 25);
		MainPanel.add(RefreshButton);
		
		DeleteRAButton = new JButton("Delete RA");
		DeleteRAButton.setBounds(425, 348, 97, 25);
		MainPanel.add(DeleteRAButton);
		
		EditRAButton = new JButton("Edit RA");
		EditRAButton.setBounds(316, 348, 97, 25);
		MainPanel.add(EditRAButton);
		
		OutputFileTextField = new JTextField();
		OutputFileTextField.setBounds(566, 366, 253, 33);
		MainPanel.add(OutputFileTextField);
		OutputFileTextField.setColumns(10);
		
		BrowseButton = new JButton("Browse");
		BrowseButton.setBounds(842, 370, 97, 25);
		MainPanel.add(BrowseButton);
		
		CommitButton = new JButton("Commit changes to Database");
		CommitButton.setBounds(705, 436, 234, 25);
		MainPanel.add(CommitButton);
		
		JLabel StartDateLabel = new JLabel("Start Date:");
		StartDateLabel.setBounds(566, 94, 69, 30);
		MainPanel.add(StartDateLabel);
		
		EndDateLabel = new JLabel("End Date:");
		EndDateLabel.setBounds(566, 190, 69, 30);
		MainPanel.add(EndDateLabel);
		
		SaveFileLabel = new JLabel("Save Output File As:");
		SaveFileLabel.setBounds(566, 329, 141, 30);
		MainPanel.add(SaveFileLabel);
		
		UnNightsLabel = new JLabel("Unavailable Nights:");
		UnNightsLabel.setBounds(46, 422, 148, 30);
		MainPanel.add(UnNightsLabel);
		
		UnNightDatesLabel = new JLabel("Unavailable Night Dates:");
		UnNightDatesLabel.setBounds(312, 420, 148, 30);
		MainPanel.add(UnNightDatesLabel);
		
		RALabel = new JLabel("Resident Assistants on Database:");
		RALabel.setBounds(46, 58, 210, 30);
		MainPanel.add(RALabel);
		
		DevResetButton = new JButton("Dev - Reset");
		DevResetButton.setBounds(386, 393, 136, 25);
		MainPanel.add(DevResetButton);
		
		
		
// 		The duplication unfortunately cannot be removed, as they both need separate Properties to work with Windowbuilder
//		(actual program still seems to run fine).
		
	}
	
//	private JDatePickerImpl createDatePicker(SqlDateModel model, Properties p)
//	{
//		Unfortunately the same Properties cannot be used for 2 different date pickers for some reason (not a problem in
//		actual working, just a problem with WindowBuilder).
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		
//		model.setSelected(true);
//		
//		return datePicker;
//	}
	
	private JDatePickerImpl createDatePicker2(SqlDateModel model, Properties p)
	{
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		model.setSelected(true);
		
		return datePicker;
	}
	
	private Properties createTodayProperties()
	{
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		return p;
	}
	
	public JScrollPane getRAListScrollPane() 
	{
		return RAListScrollPane;
	}
	public JScrollPane getUnNightsScrollPane() 
	{
		return UnNightsScrollPane;
	}
	public JScrollPane getUnNightDatesScrollPane() 
	{
		return UnNightDatesScrollPane;
	}
	public JPanel getStartDatePanel() 
	{
		return StartDatePanel;
	}
	public JPanel getEndDatePanel() 
	{
		return EndDatePanel;
	}
	public JDatePickerImpl getStartDatePicker()
	{
		return startDatePicker;
	}
	public JDatePickerImpl getEndDatePicker()
	{
		return endDatePicker;
	}
	public JButton getRunButton() {
		return RunButton;
	}
	public JButton getNewRAButton() {
		return NewRAButton;
	}
	public JButton getRefreshButton() {
		return RefreshButton;
	}
	public JButton getDeleteRAButton() {
		return DeleteRAButton;
	}
	public JButton getEditRAButton() {
		return EditRAButton;
	}
	public JTextField getOutputFileTextField() {
		return OutputFileTextField;
	}
	public JButton getBrowseButton() {
		return BrowseButton;
	}
	public JButton getCommitButton() {
		return CommitButton;
	}
	public JButton getDevResetButton() {
		return DevResetButton;
	}
}
