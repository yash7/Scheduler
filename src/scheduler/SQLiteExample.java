package scheduler;

import java.sql.*;
import org.sqlite.SQLiteConfig;

public class SQLiteExample {
	
	public static void showAllRecords(Connection c, String tableName) throws Exception
	{
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY ID ASC;");
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int numOfColumns = rsmd.getColumnCount();
		
		for (int i = 1; i <= numOfColumns; i++)
		{
			if (i > 1)
			{
				System.out.print(", ");
			}
			String columnName = rsmd.getColumnName(i);
			System.out.print(columnName);
		}
		System.out.println("");
		
		while(rs.next())
		{
			for (int i = 1; i <= numOfColumns; i++)
			{
				if (i > 1)
				{
					System.out.print(", ");
				}
				String columnValue = rs.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
					
		rs.close();
		stmt.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			c = DriverManager.getConnection("jdbc:sqlite:database.db", config.toProperties());
//			c.setAutoCommit(false);
//			System.out.println("Opened Database Successfully");
//
			stmt = c.createStatement();
//			
			String sql;
//			
//			sql =	"CREATE TABLE Resident_Assistants" + 
//							"(ID 			INT		PRIMARY KEY NOT NULL," +
//							"name			TEXT	NOT NULL," +
//							"weekendsWorked	INT		NOT NULL," +
//							"weekdaysWorked	INT		NOT NULL)";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (1, 'John', 0, 0);";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (2, 'Sam', 0, 0);";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (3, 'Maggi', 0, 0);";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (4, 'Maddie', 0, 0);";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (5, 'Graeme', 0, 0);";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO Resident_Assistants (ID, NAME, WEEKENDSWORKED, WEEKDAYSWORKED) " +
//					"VALUES (6, 'Yash', 0, 0);";
//			stmt.executeUpdate(sql);
//
//			sql =	"CREATE TABLE unavailableDays" + 
//					"(ID 			INT		NOT NULL," +
//					"unavailableDay	TEXT	NOT NULL," +
//					"PRIMARY KEY (ID, unavailableDay)," +
//					"FOREIGN KEY (ID) REFERENCES Resident_Assistants(ID));";
//			stmt.executeUpdate(sql);
//			
//			sql =	"CREATE TABLE unavailableNightDates" + 
//					"(ID 			INT		NOT NULL," +
//					"unavailableNightDate	DATE	NOT NULL," +
//					"PRIMARY KEY (ID, unavailableNightDate)," +
//					"FOREIGN KEY (ID) REFERENCES Resident_Assistants(ID));";
//			stmt.executeUpdate(sql);
//
//			sql =	"CREATE TABLE unavailableNights" + 
//					"(ID 			INT		NOT NULL," +
//					"unavailableNight	TEXT	NOT NULL," +
//					"PRIMARY KEY (ID, unavailableNight)," +
//					"FOREIGN KEY (ID) REFERENCES Resident_Assistants(ID));";
//			stmt.executeUpdate(sql);
//
//			sql = 	"DROP TABLE unavailableNightDates;";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDays (ID, unavailableDay) " +
//					"VALUES (6, 'sunday'), (6, 'monday'), (6, 'tuesday'), (6, 'wednesday'), (6, 'thursday'), (6, 'friday'), (6, 'saturday');";
//			stmt.executeUpdate(sql);
//
//	
//			sql =	"INSERT INTO unavailableNights (ID, unavailableNight) " +
//			"VALUES (9, 'sunday');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableNightDates (ID, unavailableNightDate) " +
//					"VALUES (1, '2015-08-08');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableNightDates (ID, unavailableNightDate) " +
//					"VALUES (1, '2015-08-23');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (2, '2015-07-03');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (2, '2015-07-18');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (3, '2015-07-04');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (3, '2015-07-17');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (5, '2015-07-09');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (5, '2015-07-22');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (6, '2015-07-04');";
//			stmt.executeUpdate(sql);
//			
//			sql =	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (6, '2015-07-05');";
//			stmt.executeUpdate(sql);
//			sql = "DELETE FROM unavailableDays;"; 
//			stmt.executeUpdate(sql);
//			
//			sql = 	"INSERT INTO unavailableDates (ID, unavailableDate) " +
//					"VALUES (1, '2015-04-10');";
//			stmt.executeUpdate(sql);
			

//			sql = "DROP TABLE Resident_Assistants;";
//			stmt.executeUpdate(sql);
//			sql = "DROP TABLE unavailableDays;";
//			stmt.executeUpdate(sql);
//			sql = "DROP TABLE unavailableDates;";
//			stmt.executeUpdate(sql);
//			
//			showAllRecords(c, "Resident_Assistants");
//			System.out.println("");
//			showAllRecords(c, "unavailableDays");
//			System.out.println("");
//			showAllRecords(c, "unavailableDates");
//			System.out.println("");
//
			showAllRecords(c, "unavailableNightDates");
			System.out.println("");
			showAllRecords(c, "unavailableNights");
			System.out.println("");
//			Print names of all tables
//			DatabaseMetaData md = c.getMetaData();
//			ResultSet rs2 = md.getTables(null, null, "%", null);
//			while(rs2.next())
//			{
//				System.out.println(rs2.getString(3));
//			}
			
			
			stmt.close();
//			c.commit();
			c.close();
			
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
		}
		//System.out.println("Table created successfully");

	}

}
