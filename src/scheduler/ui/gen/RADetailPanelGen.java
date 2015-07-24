package scheduler.ui.gen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.jdatepicker.impl.*;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Properties;

import scheduler.utilities.DateLabelFormatter;

public class RADetailPanelGen extends JPanel {
	private JTextField nameTextField;
	private JTextField weekdaysWorkedTextField;
	private JTextField weekendsWorkedTextField;
	private JDatePickerImpl unDatePicker;
	private JScrollPane unavailableDatesScrollPane;
	private JScrollPane availableDaysScrollPane;
	private JScrollPane unavailableDaysScrollPane;
	private JButton toAvailableButton;
	private JButton addUnavailableDateButton;
	private JButton toUnavailableButton;

	private JList availableDaysJList;
	private DefaultListModel<DayOfWeek> availableDaysListModel;
	private JList unavailableDaysJList;
	private DefaultListModel<DayOfWeek> unavailableDaysListModel;
	private JList unavailableDatesJList;
	private DefaultListModel<String> unavailableDatesListModel;

	class ToUnavailableButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = availableDaysJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				DayOfWeek temp = availableDaysListModel.getElementAt(index);
				unavailableDaysListModel.addElement(temp);
				availableDaysListModel.remove(index);	
			}
		}
	}
	
	class ToAvailableButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = unavailableDaysJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				DayOfWeek temp = unavailableDaysListModel.getElementAt(index);
				availableDaysListModel.addElement(temp);
				unavailableDaysListModel.remove(index);	
			}
		}
	}
	
	class AddUnavailableDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String tempDate = unDatePicker.getModel().getValue().toString();
			if (!(unavailableDatesListModel.contains(tempDate)))
			{
				unavailableDatesListModel.addElement(tempDate);
			}
		}
	}
	
	class RemoveUnavailableDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = unavailableDatesJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				unavailableDatesListModel.remove(index);
			}
		}
	}
	
	/**
	 * Create the panel.
	 */
	public RADetailPanelGen() {
		setLayout(null);
		setBounds(0, 0, 500, 500);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 500, 500);
		add(MainPanel);
		MainPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name (required) :");
		nameLabel.setBounds(32, 28, 104, 16);
		MainPanel.add(nameLabel);
		
		JLabel weekdaysWorkedLabel = new JLabel("Weekdays Worked:");
		weekdaysWorkedLabel.setBounds(32, 86, 121, 16);
		MainPanel.add(weekdaysWorkedLabel);
		
		JLabel weekendsWorkedLabel = new JLabel("Weekends Worked:");
		weekendsWorkedLabel.setBounds(32, 57, 121, 16);
		MainPanel.add(weekendsWorkedLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(148, 25, 215, 22);
		MainPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		weekdaysWorkedTextField = new JTextField();
		weekdaysWorkedTextField.setText("0");
		weekdaysWorkedTextField.setColumns(10);
		weekdaysWorkedTextField.setBounds(148, 86, 39, 22);
		MainPanel.add(weekdaysWorkedTextField);
		
		weekendsWorkedTextField = new JTextField();
		weekendsWorkedTextField.setText("0");
		weekendsWorkedTextField.setColumns(10);
		weekendsWorkedTextField.setBounds(148, 54, 39, 22);
		MainPanel.add(weekendsWorkedTextField);
		
		availableDaysScrollPane = new JScrollPane();
		availableDaysScrollPane.setBounds(32, 156, 131, 136);
		MainPanel.add(availableDaysScrollPane);
		
		unavailableDaysScrollPane = new JScrollPane();
		unavailableDaysScrollPane.setBounds(313, 156, 131, 136);
		MainPanel.add(unavailableDaysScrollPane);
		
		toUnavailableButton = new JButton(">");
		toUnavailableButton.setBounds(216, 197, 45, 25);
		toUnavailableButton.addActionListener(new ToUnavailableButtonListener());
		MainPanel.add(toUnavailableButton);
		
		toAvailableButton = new JButton("<");
		toAvailableButton.setBounds(216, 235, 45, 25);
		toAvailableButton.addActionListener(new ToAvailableButtonListener());
		MainPanel.add(toAvailableButton);
		
		JLabel availableDaysLabel = new JLabel("Available Days:");
		availableDaysLabel.setBounds(32, 128, 131, 16);
		MainPanel.add(availableDaysLabel);
		
		JLabel unavailableDaysLabel = new JLabel("Unavailable Days:");
		unavailableDaysLabel.setBounds(313, 127, 131, 16);
		MainPanel.add(unavailableDaysLabel);
		
		JPanel unavailableDatePanel = new JPanel();
		unavailableDatePanel.setBounds(32, 344, 180, 51);
		MainPanel.add(unavailableDatePanel);
		
		SqlDateModel model = new SqlDateModel();
		model.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue() - 1), LocalDateTime.now().getDayOfMonth());
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		unavailableDatePanel.setLayout(null);
		unDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		unDatePicker.setBounds(0, 5, 173, 33);
		
		unavailableDatePanel.add(unDatePicker);
		model.setSelected(true);
		
		addUnavailableDateButton = new JButton("Add");
		addUnavailableDateButton.setBounds(216, 350, 61, 25);
		addUnavailableDateButton.addActionListener(new AddUnavailableDateButtonListener());
		MainPanel.add(addUnavailableDateButton);
		
		unavailableDatesScrollPane = new JScrollPane();
		unavailableDatesScrollPane.setBounds(313, 344, 131, 136);
		MainPanel.add(unavailableDatesScrollPane);
		
		JLabel unavailableDatesLabel = new JLabel("Unavailable Dates:");
		unavailableDatesLabel.setBounds(32, 315, 131, 16);
		MainPanel.add(unavailableDatesLabel);
		
		availableDaysListModel = new DefaultListModel<DayOfWeek>();
		availableDaysJList = new JList(availableDaysListModel);
		availableDaysScrollPane.setViewportView(availableDaysJList);
		availableDaysJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableDaysListModel = new DefaultListModel<DayOfWeek>();
		unavailableDaysJList = new JList(unavailableDaysListModel);
		unavailableDaysScrollPane.setViewportView(unavailableDaysJList);
		unavailableDaysJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableDatesListModel = new DefaultListModel<String>();
		unavailableDatesJList = new JList(unavailableDatesListModel);
		unavailableDatesScrollPane.setViewportView(unavailableDatesJList);
		unavailableDatesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton removeUnavailableDateButton = new JButton("Remove Date");
		removeUnavailableDateButton.setBounds(180, 455, 121, 25);
		removeUnavailableDateButton.addActionListener(new RemoveUnavailableDateButtonListener());
		MainPanel.add(removeUnavailableDateButton);

	}
	public JTextField getNameTextField() {
		return nameTextField;
	}
	public JTextField getWeekendsWorkedTextField() {
		return weekendsWorkedTextField;
	}
	public JTextField getWeekdaysWorkedTextField() {
		return weekdaysWorkedTextField;
	}
	public JScrollPane getUnavailableDatesScrollPane() {
		return unavailableDatesScrollPane;
	}
	public JScrollPane getAvailableDaysScrollPane() {
		return availableDaysScrollPane;
	}
	public JScrollPane getUnavailableDaysScrollPane() {
		return unavailableDaysScrollPane;
	}
	public JButton getToAvailableButton() {
		return toAvailableButton;
	}
	public JButton getAddUnavailableDateButton() {
		return addUnavailableDateButton;
	}
	public JButton getToUnavailableButton() {
		return toUnavailableButton;
	}
	public JDatePickerImpl getUnDatePicker(){
		return unDatePicker;
	}
	public JList<DayOfWeek> getAvailableDaysJList()
	{
		return availableDaysJList;
	}
	public JList<DayOfWeek> getUnavailableDaysJList()
	{
		return unavailableDaysJList;
	}
	public JList<String> getUnavailableDatesJList()
	{
		return unavailableDatesJList;
	}
	public DefaultListModel<DayOfWeek> getAvailableDaysListModel()
	{
		return availableDaysListModel;
	}
	public DefaultListModel<DayOfWeek> getUnavailableDaysListModel()
	{
		return unavailableDaysListModel;
	}
	public DefaultListModel<String> getUnavailableDatesListModel()
	{
		return unavailableDatesListModel;
	}
}
