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
	private JDatePickerImpl unNightDatePicker;
	private JScrollPane unavailableNightDatesScrollPane;
	private JScrollPane availableNightsScrollPane;
	private JScrollPane unavailableNightsScrollPane;
	private JButton toAvailableNightsButton;
	private JButton addUnavailableNightDateButton;
	private JButton toUnavailableNightsButton;
	private JButton removeUnavailableNightDateButton;
	
	private JDatePickerImpl unDayDatePicker;
	private JScrollPane availableDaysScrollPane;
	private JScrollPane unavailableDaysScrollPane;
	private JScrollPane unavailableDayDatesScrollPane;
	private JButton toUnavailableDaysButton;
	private JButton toAvailableDaysButton;
	private JButton removeUnavailableDayDateButton;
	private JButton addUnavailableDayDateButton;
	
	private JList availableNightsJList;
	private DefaultListModel<DayOfWeek> availableNightsListModel;
	private JList unavailableNightsJList;
	private DefaultListModel<DayOfWeek> unavailableNightsListModel;
	private JList unavailableNightDatesJList;
	private DefaultListModel<String> unavailableNightDatesListModel;
	
	private JList availableDaysJList;
	private DefaultListModel<DayOfWeek> availableDaysListModel;
	private JList unavailableDaysJList;
	private DefaultListModel<DayOfWeek> unavailableDaysListModel;
	private JList unavailableDayDatesJList;
	private DefaultListModel<String> unavailableDayDatesListModel;
	
	class ToUnavailableNightsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = availableNightsJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				DayOfWeek temp = availableNightsListModel.getElementAt(index);
				unavailableNightsListModel.addElement(temp);
				availableNightsListModel.remove(index);	
			}
		}
	}
	
	class ToAvailableNightsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = unavailableNightsJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				DayOfWeek temp = unavailableNightsListModel.getElementAt(index);
				availableNightsListModel.addElement(temp);
				unavailableNightsListModel.remove(index);	
			}
		}
	}
	
	class AddUnavailableNightDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String tempDate = unNightDatePicker.getModel().getValue().toString();
			if (!(unavailableNightDatesListModel.contains(tempDate)))
			{
				unavailableNightDatesListModel.addElement(tempDate);
			}
		}
	}
	
	class RemoveUnavailableNightDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = unavailableNightDatesJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				unavailableNightDatesListModel.remove(index);
			}
		}
	}
	
	class ToUnavailableDaysButtonListener implements ActionListener
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
	
	class ToAvailableDaysButtonListener implements ActionListener
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
	
	class AddUnavailableDayDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String tempDate = unDayDatePicker.getModel().getValue().toString();
			if (!(unavailableDayDatesListModel.contains(tempDate)))
			{
				unavailableDayDatesListModel.addElement(tempDate);
			}
		}
	}
	
	class RemoveUnavailableDayDateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = unavailableDayDatesJList.getSelectedIndex();
			if (index < 0)
			{
				
			}
			else
			{
				unavailableDayDatesListModel.remove(index);
			}
		}
	}
	
	/**
	 * Create the panel.
	 */
	public RADetailPanelGen() {
		setLayout(null);
		setBounds(0, 0, 810, 500);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 810, 500);
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
		nameTextField.setBounds(148, 25, 227, 22);
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
		
		availableNightsScrollPane = new JScrollPane();
		availableNightsScrollPane.setBounds(32, 156, 131, 136);
		MainPanel.add(availableNightsScrollPane);
		
		unavailableNightsScrollPane = new JScrollPane();
		unavailableNightsScrollPane.setBounds(244, 156, 131, 136);
		MainPanel.add(unavailableNightsScrollPane);
		
		toUnavailableNightsButton = new JButton(">");
		toUnavailableNightsButton.setBounds(180, 197, 45, 25);
		toUnavailableNightsButton.addActionListener(new ToUnavailableNightsButtonListener());
		MainPanel.add(toUnavailableNightsButton);
		
		toAvailableNightsButton = new JButton("<");
		toAvailableNightsButton.setBounds(180, 235, 45, 25);
		toAvailableNightsButton.addActionListener(new ToAvailableNightsButtonListener());
		MainPanel.add(toAvailableNightsButton);
		
		JLabel availableNightsLabel = new JLabel("Available Nights:");
		availableNightsLabel.setBounds(32, 128, 131, 16);
		MainPanel.add(availableNightsLabel);
		
		JLabel unavailableNightsLabel = new JLabel("Unavailable Nights:");
		unavailableNightsLabel.setBounds(244, 127, 131, 16);
		MainPanel.add(unavailableNightsLabel);
		
		JPanel unavailableNightDatePanel = new JPanel();
		unavailableNightDatePanel.setBounds(32, 344, 180, 51);
		MainPanel.add(unavailableNightDatePanel);
		
		SqlDateModel model = new SqlDateModel();
		model.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue() - 1), LocalDateTime.now().getDayOfMonth());
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl nightDatePanel = new JDatePanelImpl(model, p);
		unavailableNightDatePanel.setLayout(null);
		unNightDatePicker = new JDatePickerImpl(nightDatePanel, new DateLabelFormatter());
		unNightDatePicker.setBounds(0, 5, 173, 33);
		
		unavailableNightDatePanel.add(unNightDatePicker);
		model.setSelected(true);
		
		addUnavailableNightDateButton = new JButton("Add");
		addUnavailableNightDateButton.setBounds(92, 408, 61, 25);
		addUnavailableNightDateButton.addActionListener(new AddUnavailableNightDateButtonListener());
		MainPanel.add(addUnavailableNightDateButton);
		
		unavailableNightDatesScrollPane = new JScrollPane();
		unavailableNightDatesScrollPane.setBounds(244, 344, 131, 136);
		MainPanel.add(unavailableNightDatesScrollPane);
		
		JLabel unavailableNightDatesLabel = new JLabel("Unavailable Night Dates:");
		unavailableNightDatesLabel.setBounds(32, 315, 155, 16);
		MainPanel.add(unavailableNightDatesLabel);
		
		availableNightsListModel = new DefaultListModel<DayOfWeek>();
		availableNightsJList = new JList(availableNightsListModel);
		availableNightsScrollPane.setViewportView(availableNightsJList);
		availableNightsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableNightsListModel = new DefaultListModel<DayOfWeek>();
		unavailableNightsJList = new JList(unavailableNightsListModel);
		unavailableNightsScrollPane.setViewportView(unavailableNightsJList);
		unavailableNightsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableNightDatesListModel = new DefaultListModel<String>();
		unavailableNightDatesJList = new JList(unavailableNightDatesListModel);
		unavailableNightDatesScrollPane.setViewportView(unavailableNightDatesJList);
		unavailableNightDatesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		removeUnavailableNightDateButton = new JButton("Remove Selected Date");
		removeUnavailableNightDateButton.setBounds(32, 455, 180, 25);
		removeUnavailableNightDateButton.addActionListener(new RemoveUnavailableNightDateButtonListener());
		MainPanel.add(removeUnavailableNightDateButton);
		
		availableDaysScrollPane = new JScrollPane();
		availableDaysScrollPane.setBounds(433, 156, 131, 136);
		MainPanel.add(availableDaysScrollPane);
		
		unavailableDaysScrollPane = new JScrollPane();
		unavailableDaysScrollPane.setBounds(645, 156, 131, 136);
		MainPanel.add(unavailableDaysScrollPane);
		
		toUnavailableDaysButton = new JButton(">");
		toUnavailableDaysButton.setBounds(581, 197, 45, 25);
		toUnavailableDaysButton.addActionListener(new ToUnavailableDaysButtonListener());
		MainPanel.add(toUnavailableDaysButton);
		
		toAvailableDaysButton = new JButton("<");
		toAvailableDaysButton.setBounds(581, 235, 45, 25);
		toAvailableDaysButton.addActionListener(new ToAvailableDaysButtonListener());
		MainPanel.add(toAvailableDaysButton);
		
		JLabel availableDaysLabel = new JLabel("Available Days:");
		availableDaysLabel.setBounds(433, 128, 131, 16);
		MainPanel.add(availableDaysLabel);
		
		JLabel unavailableDaysLabel = new JLabel("Unavailable Days:");
		unavailableDaysLabel.setBounds(645, 127, 131, 16);
		MainPanel.add(unavailableDaysLabel);
		
		unavailableDayDatesScrollPane = new JScrollPane();
		unavailableDayDatesScrollPane.setBounds(645, 344, 131, 136);
		MainPanel.add(unavailableDayDatesScrollPane);
		
		JLabel unavailableDayDatesLabel = new JLabel("Unavailable Day Dates:");
		unavailableDayDatesLabel.setBounds(433, 315, 155, 16);
		MainPanel.add(unavailableDayDatesLabel);
		
		removeUnavailableDayDateButton = new JButton("Remove Selected Date");
		removeUnavailableDayDateButton.setBounds(433, 455, 180, 25);
		removeUnavailableDayDateButton.addActionListener(new RemoveUnavailableDayDateButtonListener());
		MainPanel.add(removeUnavailableDayDateButton);
		
		addUnavailableDayDateButton = new JButton("Add");
		addUnavailableDayDateButton.setBounds(493, 408, 61, 25);
		addUnavailableDayDateButton.addActionListener(new AddUnavailableDayDateButtonListener());
		MainPanel.add(addUnavailableDayDateButton);
		
		JPanel unavailableDayDatePanel = new JPanel();
		unavailableDayDatePanel.setBounds(433, 344, 180, 51);
		MainPanel.add(unavailableDayDatePanel);
		
		SqlDateModel model2 = new SqlDateModel();
		model2.setDate(LocalDateTime.now().getYear(), (LocalDateTime.now().getMonthValue() - 1), LocalDateTime.now().getDayOfMonth());
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl dayDatePanel = new JDatePanelImpl(model2, p2);
		unavailableDayDatePanel.setLayout(null);
		unDayDatePicker = new JDatePickerImpl(dayDatePanel, new DateLabelFormatter());
		unDayDatePicker.setBounds(0, 5, 173, 33);
		
		unavailableDayDatePanel.add(unDayDatePicker);
		model2.setSelected(true);
		
		availableDaysListModel = new DefaultListModel<DayOfWeek>();
		availableDaysJList = new JList(availableDaysListModel);
		availableDaysScrollPane.setViewportView(availableDaysJList);
		availableDaysJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableDaysListModel = new DefaultListModel<DayOfWeek>();
		unavailableDaysJList = new JList(unavailableDaysListModel);
		unavailableDaysScrollPane.setViewportView(unavailableDaysJList);
		unavailableDaysJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unavailableDayDatesListModel = new DefaultListModel<String>();
		unavailableDayDatesJList = new JList(unavailableDayDatesListModel);
		unavailableDayDatesScrollPane.setViewportView(unavailableDayDatesJList);
		unavailableDayDatesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
	public JScrollPane getUnavailableNightDatesScrollPane() {
		return unavailableNightDatesScrollPane;
	}
	public JScrollPane getAvailableNightsScrollPane() {
		return availableNightsScrollPane;
	}
	public JScrollPane getUnavailableNightsScrollPane() {
		return unavailableNightsScrollPane;
	}
	public JButton getToAvailableNightsButton() {
		return toAvailableNightsButton;
	}
	public JButton getAddUnavailableNightDateButton() {
		return addUnavailableNightDateButton;
	}
	public JButton getToUnavailableNightsButton() {
		return toUnavailableNightsButton;
	}
	public JDatePickerImpl getUnNightDatePicker(){
		return unNightDatePicker;
	}
	public JList<DayOfWeek> getAvailableNightsJList()
	{
		return availableNightsJList;
	}
	public JList<DayOfWeek> getUnavailableNightsJList()
	{
		return unavailableNightsJList;
	}
	public JList<String> getUnavailableNightDatesJList()
	{
		return unavailableNightDatesJList;
	}
	public DefaultListModel<DayOfWeek> getAvailableNightsListModel()
	{
		return availableNightsListModel;
	}
	public DefaultListModel<DayOfWeek> getUnavailableNightsListModel()
	{
		return unavailableNightsListModel;
	}
	public DefaultListModel<String> getUnavailableNightDatesListModel()
	{
		return unavailableNightDatesListModel;
	}
	public JScrollPane getAvailableDaysScrollPane() {
		return availableDaysScrollPane;
	}
	public JScrollPane getUnavailableDaysScrollPane() {
		return unavailableDaysScrollPane;
	}
	public JButton getToUnavailableDaysButton() {
		return toUnavailableDaysButton;
	}
	public JButton getToAvailableDaysButton() {
		return toAvailableDaysButton;
	}
	public JButton getAddUnavailableDayDateButton() {
		return addUnavailableDayDateButton;
	}
	public JButton getRemoveUnavailableDayDateButton() {
		return removeUnavailableDayDateButton;
	}
	public JScrollPane getUnavailableDayDatesScrollPane() {
		return unavailableDayDatesScrollPane;
	}
	public JList<DayOfWeek> getAvailableDaysJList()
	{
		return availableDaysJList;
	}
	public JList<DayOfWeek> getUnavailableDaysJList()
	{
		return unavailableDaysJList;
	}
	public JList<String> getUnavailableDayDatesJList()
	{
		return unavailableDayDatesJList;
	}
	public DefaultListModel<DayOfWeek> getAvailableDaysListModel()
	{
		return availableDaysListModel;
	}
	public DefaultListModel<DayOfWeek> getUnavailableDaysListModel()
	{
		return unavailableDaysListModel;
	}
	public DefaultListModel<String> getUnavailableDayDatesListModel()
	{
		return unavailableDayDatesListModel;
	}
	
}
