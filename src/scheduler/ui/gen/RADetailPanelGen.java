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

	private JList availableNightsJList;
	private DefaultListModel<DayOfWeek> availableNightsListModel;
	private JList unavailableNightsJList;
	private DefaultListModel<DayOfWeek> unavailableNightsListModel;
	private JList unavailableNightDatesJList;
	private DefaultListModel<String> unavailableNightDatesListModel;

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
		
		availableNightsScrollPane = new JScrollPane();
		availableNightsScrollPane.setBounds(32, 156, 131, 136);
		MainPanel.add(availableNightsScrollPane);
		
		unavailableNightsScrollPane = new JScrollPane();
		unavailableNightsScrollPane.setBounds(313, 156, 131, 136);
		MainPanel.add(unavailableNightsScrollPane);
		
		toUnavailableNightsButton = new JButton(">");
		toUnavailableNightsButton.setBounds(216, 197, 45, 25);
		toUnavailableNightsButton.addActionListener(new ToUnavailableNightsButtonListener());
		MainPanel.add(toUnavailableNightsButton);
		
		toAvailableNightsButton = new JButton("<");
		toAvailableNightsButton.setBounds(216, 235, 45, 25);
		toAvailableNightsButton.addActionListener(new ToAvailableNightsButtonListener());
		MainPanel.add(toAvailableNightsButton);
		
		JLabel availableNightsLabel = new JLabel("Available Nights:");
		availableNightsLabel.setBounds(32, 128, 131, 16);
		MainPanel.add(availableNightsLabel);
		
		JLabel unavailableNightsLabel = new JLabel("Unavailable Nights:");
		unavailableNightsLabel.setBounds(313, 127, 131, 16);
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
		addUnavailableNightDateButton.setBounds(216, 350, 61, 25);
		addUnavailableNightDateButton.addActionListener(new AddUnavailableNightDateButtonListener());
		MainPanel.add(addUnavailableNightDateButton);
		
		unavailableNightDatesScrollPane = new JScrollPane();
		unavailableNightDatesScrollPane.setBounds(313, 344, 131, 136);
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
		
		removeUnavailableNightDateButton = new JButton("Remove Date");
		removeUnavailableNightDateButton.setBounds(180, 455, 121, 25);
		removeUnavailableNightDateButton.addActionListener(new RemoveUnavailableNightDateButtonListener());
		MainPanel.add(removeUnavailableNightDateButton);

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
}
