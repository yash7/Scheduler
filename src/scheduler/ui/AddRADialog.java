package scheduler.ui;

import scheduler.ui.gen.AddRADialogGen;

import java.sql.*;

public class AddRADialog 
{
	private AddRADialogGen addRADialogGen;
	
	public AddRADialog(Connection c, String tableName)
	{
		/*
		setLayout(null);
		
		JLabel l = new JLabel("hello");
		l.setBounds(100, 100, 50, 20);
		this.add(l);
		JButton b = new JButton("OK");
		b.setBounds(100, 200, 100, 20);
		this.add(b);
		
		this.setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);
		
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		setVisible(true); // always make this the last line
		*/
		
		addRADialogGen = new AddRADialogGen(c, tableName);
		
		
	}
	
	public AddRADialog(Connection c, String tableName, int ID)
	{
		addRADialogGen = new AddRADialogGen(c, tableName, ID);
	}
	
	public AddRADialogGen getAddRADialogGen()
	{
		return addRADialogGen;
	}
}
