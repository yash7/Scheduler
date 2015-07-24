package scheduler.ui;

import java.awt.*;

import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import scheduler.ui.gen.AddRADialogGen;

public class CoreFrame extends JFrame 
{

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 725;
	
	private JPanel contentPane;
	private WorkPanel workPanel;
	private CardLayout cardLayout;

	private void init()
	{
		setBounds(100, 100, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scheduler");
	}
	
//	
//	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CoreFrame frame = new CoreFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public CoreFrame(Connection c, String tableName) 
	{
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		
		init();
		
		
		setLayout(null);
		
		workPanel = new WorkPanel(c, tableName);
		add(workPanel.getWorkPanelGen());
		
		setVisible(true);
//		JPanel test = new JPanel();
//		test.setSize(600, 600);
//		test.setLayout(null);
//		
//		JLabel Test2 = new JLabel("New label");
//		Test2.setBounds(423, 316, 56, 16);
//		test.add(Test2);
//		
//		add(test);
		
		// AddRADialogGen ar = new AddRADialogGen();

		
	}
	
	public WorkPanel getWorkPanel()
	{
		return workPanel;
	}

}
