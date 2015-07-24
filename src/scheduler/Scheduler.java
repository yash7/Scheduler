package scheduler;

import scheduler.objects.RAObject;
import scheduler.objects.DutyNight;
import scheduler.ui.CoreFrame;
import scheduler.utilities.UtilityMethods;

import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.*;
import java.sql.*;

import javax.swing.JOptionPane;

import org.jdatepicker.impl.*;
import org.sqlite.SQLiteConfig;

public class Scheduler 
{
		
	public static ArrayList<DutyNight> run(LocalDate startDate, LocalDate endDate, ArrayList<RAObject> RAs)
	{
		ArrayList<DutyNight> dutyList = new ArrayList<DutyNight>();
		
		long i = ChronoUnit.DAYS.between(startDate, endDate);
		LocalDate tempDate = startDate;
		for (int j = 0; j <= i; j++)
		{
			DutyNight tempDutyNight = new DutyNight();
			tempDutyNight.setDate(tempDate);
			for (int k = 0; k < RAs.size(); k++)
			{
				RAObject tempRA = RAs.get(k);
				if (!(tempRA.getUnavailableDays().contains(tempDate.getDayOfWeek())) && !(tempRA.getUnavailableDates().contains(tempDate)))
				{
					if (tempDutyNight.getRA1() == null)
					{
						tempDutyNight.setRA1(tempRA);
					}
					else if (tempDutyNight.getRA2() == null)
					{
						tempDutyNight.setRA2(tempRA);
					}
					else
					{
						tempDutyNight.getAlternateRAs().add(tempRA);
					}
				}
			}
			dutyList.add(tempDutyNight);
			tempDate = tempDate.plusDays(1);
		}
		
		return dutyList;
	}
	
	private static ArrayList<RAObject> getRAs (Connection c, String tableName) throws SQLException
	{
		ArrayList<RAObject> RAs = new ArrayList<RAObject>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY ID ASC;");
		
		while(rs.next())
		{
			RAObject tempRA = new RAObject();
			tempRA.setName(rs.getString("name"));
			tempRA.setWeekdaysWorked(rs.getInt("weekdaysWorked"));
			tempRA.setWeekendsWorked(rs.getInt("weekendsWorked"));
			// Remove hardcoding
			int ID = rs.getInt("ID");
			tempRA.setUnavailableDays(obtainUnavailableDays(c, "unavailableDays", ID));
			tempRA.setUnavailableDates(obtainUnavailableDates(c, "unavailableDates", ID));
			RAs.add(tempRA);
		}
		
		return RAs;
	}
	
	private static ArrayList<DayOfWeek> obtainUnavailableDays (Connection c, String tableName, int ID) throws SQLException
	{
		
		ArrayList<DayOfWeek> unavailableDays = new ArrayList<DayOfWeek>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableDay FROM " + tableName + " where ID = " + ID + ";");
		
		while(rs.next())
		{
			DayOfWeek dw = UtilityMethods.getDayOfWeekFromString(rs.getString("unavailableDay"));
			unavailableDays.add(dw);
		}
		
		return unavailableDays;
		
	}
	
	private static ArrayList<LocalDate> obtainUnavailableDates (Connection c, String tableName, int ID) throws SQLException
	{
		
		ArrayList<LocalDate> unavailableDates = new ArrayList<LocalDate>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableDate FROM " + tableName + " where ID = " + ID + ";");
		
		while(rs.next())
		{
			LocalDate ld = RAObject.getLocalDateFromString(rs.getString("unavailableDate"));
			unavailableDates.add(ld);
		}
		
		return unavailableDates;
		
	}
	
	public static void main(String[] args) 
	{
		
		Connection c = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			c = DriverManager.getConnection("jdbc:sqlite:database.db", config.toProperties());
			CoreFrame core = new CoreFrame(c, "Resident_Assistants");
			
//			String s = core.getWorkPanel().getWorkPanelGen().getStartDatePicker().getModel().getValue().toString();
//			String e = core.getWorkPanel().getWorkPanelGen().getEndDatePicker().getModel().getValue().toString();
//			
//			ArrayList<RAObject> RAs = getRAs(c, "Resident_Assistants"); // Remove hardcoding
//			
//			for (int i = 0; i < RAs.size(); i++)
//			{
//				System.out.println(RAs.get(i));
//			}
			
//			LocalDate startDate = LocalDate.of(2015, 07, 01);
//			LocalDate endDate = LocalDate.of(2015, 07, 30);
//			
//			LocalDate startDate = RAObject.getLocalDateFromString(s);
//			LocalDate endDate = RAObject.getLocalDateFromString(e);
//			
//			System.out.println(startDate.toString());
//			System.out.println(endDate.toString());
//			ArrayList<DutyNight> dutyNights = run(startDate, endDate, RAs);
//
//			for (int i = 0; i < dutyNights.size(); i++)
//			{
//				System.out.println(dutyNights.get(i));
//			}

			
			
		}	
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		
		
		
//		LocalDate startDate = LocalDate.of(2015, 07, 01);
//		LocalDate endDate = LocalDate.of(2015, 07, 30);
//		
//		// 4 generic RAs
//		
//		RAObject ra1 = new RAObject("Sam", "Slater");
//		ra1.addUnavailableDay("Monday");
//		ra1.addUnavailableDay("Tuesday");
//		ra1.addUnavailableDay("Wednesday");
//		ra1.addUnavailableDay("Thursday");
//		ra1.addUnavailableDay("Sunday");
//		ra1.addUnavailableDate(LocalDate.of(2015, 07, 4));
//		ra1.addUnavailableDate(LocalDate.of(2015, 07, 10));
//		
//		RAObject ra2 = new RAObject("Margo", "BP");
//		ra2.addUnavailableDay("Friday");
//		ra2.addUnavailableDay("Saturday");
//		ra2.addUnavailableDate(LocalDate.of(2015, 07, 29));
//		ra2.addUnavailableDate(LocalDate.of(2015, 07, 30));
//		
//		RAObject ra3 = new RAObject("Graeme", "MC");
//		ra3.addUnavailableDay("Monday");
//		ra3.addUnavailableDay("Tuesday");
//		ra3.addUnavailableDay("Wednesday");
//		ra3.addUnavailableDay("Thursday");
//		ra3.addUnavailableDay("Sunday");
//		ra3.addUnavailableDate(LocalDate.of(2015, 07, 3));
//		ra3.addUnavailableDate(LocalDate.of(2015, 07, 11));
//		
//		RAObject ra4 = new RAObject("J", "Malens");
//		ra4.addUnavailableDay("Friday");
//		ra4.addUnavailableDay("Saturday");
//		ra4.addUnavailableDate(LocalDate.of(2015, 07, 29));
//		ra4.addUnavailableDate(LocalDate.of(2015, 07, 30));
//		
//		RAObject ra5 = new RAObject("Y", "L");
//		
//		ArrayList<RAObject> RAs = new ArrayList<RAObject>();
//		RAs.add(ra1);
//		RAs.add(ra2);
//		RAs.add(ra3);
//		RAs.add(ra4);
//		RAs.add(ra5);
//		
//		ArrayList<DutyNight> dl = run(startDate, endDate, RAs);
//		
//		System.out.println(dl.size());
//		for (int i = 0; i < dl.size(); i++)
//		{
//			System.out.println(dl.get(i).toString());
//		}
//		
//		
//		
//		LocalDate ld1 = LocalDate.of(1994, 5, 26);
//		LocalDate ld2 = LocalDate.of(1994, 5, 28);
//		
//		System.out.println(ld1.toString() + " " + ld1.getDayOfWeek() + ld1.getMonth());
//		System.out.println(ld2.toString() + " " + ld2.getDayOfWeek());
//		
//		System.out.println(ChronoUnit.DAYS.between(ld1, ld2));
		
//		RAObject ra1 = new RAObject("j", "malens");
//		
//		System.out.println(ra1.getUnavailableDays().size());
//		ra1.addUnavailableDay("Friday");
//		System.out.println(ra1.getUnavailableDays().size());
//		System.out.println(ra1.getUnavailableDays().get(0));
//		

//		System.out.println(ld1.toString());
//		System.out.println(ld2.toString());
//		System.out.println("Loop");

		
		
	}

}
