package scheduler.utilities;

import scheduler.objects.*;

import java.util.ArrayList;
import java.util.Random;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Prioritize 
{
	
	private static ArrayList<DutyNight> dutyNights;
	private static ArrayList<RAObject> RAs;
	
	public static ArrayList<DutyNight> getDutyList(LocalDate startDate, LocalDate endDate)
	{
		ArrayList<DutyNight> dutyList = new ArrayList<DutyNight>();
		
		long i = ChronoUnit.DAYS.between(startDate, endDate);
		LocalDate tempDate = startDate;
		for (int j = 0; j <= i; j++)
		{
			DayOfWeek tempDW = tempDate.getDayOfWeek();
			
			if (tempDW.equals(DayOfWeek.of(6)) || tempDW.equals(DayOfWeek.of(7))) // refactor with DutyDay objects
			{
				DutyNight tempDutyDay = new DutyNight(1);
				tempDutyDay.setDate(tempDate);
				for (int k = 0; k < RAs.size(); k++)
				{
					RAObject tempRA = RAs.get(k);
					if (!(tempRA.getUnavailableDays().contains(tempDW)) && !(tempRA.getUnavailableDayDates().contains(tempDate)))
					{
						tempDutyDay.getAvailableRAs().add(tempRA);
					}
				}
				dutyList.add(tempDutyDay);
				
				DutyNight tempDutyNight = new DutyNight(2);
				tempDutyNight.setDate(tempDate);
				for (int k = 0; k < RAs.size(); k++)
				{
					RAObject tempRA = RAs.get(k);
					if (!(tempRA.getUnavailableNights().contains(tempDW)) && !(tempRA.getUnavailableNightDates().contains(tempDate)))
					{
						tempDutyNight.getAvailableRAs().add(tempRA);
					}
				}
				dutyList.add(tempDutyNight);
			}			
			else
			{
				DutyNight tempDutyNight = new DutyNight(2);
				tempDutyNight.setDate(tempDate);
				for (int k = 0; k < RAs.size(); k++)
				{
					RAObject tempRA = RAs.get(k);
					if (!(tempRA.getUnavailableNights().contains(tempDW)) && !(tempRA.getUnavailableNightDates().contains(tempDate)))
					{
						tempDutyNight.getAvailableRAs().add(tempRA);
					}
				}
				dutyList.add(tempDutyNight);
			}
			tempDate = tempDate.plusDays(1);
			
//			for (int k = 0; k < RAs.size(); k++)
//			{
//				RAObject tempRA = RAs.get(k);
//				if (!(tempRA.getUnavailableNights().contains(tempDW)) && !(tempRA.getUnavailableNightDates().contains(tempDate)))
//				{
////					if (tempDutyNight.getRA1() == null)
////					{
////						tempDutyNight.setRA1(tempRA);
////					}
////					else if (tempDutyNight.getRA2() == null)
////					{
////						tempDutyNight.setRA2(tempRA);
////					}
////					else
////					{
////						tempDutyNight.getAlternateRAs().add(tempRA);
////					}
//					tempDutyNight.getAvailableRAs().add(tempRA);
//				}
//			}
//			dutyList.add(tempDutyNight);
//			tempDate = tempDate.plusDays(1);
		}
		
		return dutyList;
	}
	
	private static ArrayList<RAObject> getRAs (Connection c, String tableName) throws SQLException
	{
		ArrayList<RAObject> RAList = new ArrayList<RAObject>();
		
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
			tempRA.setUnavailableDayDates(obtainUnavailableDayDates(c, "unavailableDayDates", ID));
			tempRA.setUnavailableNights(obtainUnavailableNights(c, "unavailableNights", ID));
			tempRA.setUnavailableNightDates(obtainUnavailableNightDates(c, "unavailableNightDates", ID));
			RAList.add(tempRA);
		}
		
		return RAList;
	}
	
	public static void commit(Connection c) throws SQLException
	{
		if (RAs != null)
		{
			for (int i = 0; i < RAs.size(); i++)
			{
				RAObject tempRA = RAs.get(i);
				String tempName = tempRA.getName();
				int newWeekendsWorked = tempRA.getWeekendsWorked();
				int newWeekdaysWorked = tempRA.getWeekdaysWorked();
				
				Statement stmt = c.createStatement();
				
				String sql = ("UPDATE Resident_Assistants SET weekendsWorked = " + newWeekendsWorked + " WHERE name = '" + tempName + "';");  
				stmt.executeUpdate(sql);
				sql = ("UPDATE Resident_Assistants SET weekdaysWorked = " + newWeekdaysWorked + " WHERE name = '" + tempName + "';");
				stmt.executeUpdate(sql);
			}
		}
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
	
	private static ArrayList<LocalDate> obtainUnavailableDayDates (Connection c, String tableName, int ID) throws SQLException
	{
		
		ArrayList<LocalDate> unavailableDayDates = new ArrayList<LocalDate>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableDayDate FROM " + tableName + " where ID = " + ID + ";");
		
		while(rs.next())
		{
			LocalDate ld = RAObject.getLocalDateFromString(rs.getString("unavailableDayDate"));
			unavailableDayDates.add(ld);
		}
		
		return unavailableDayDates;
		
	}
	
	private static ArrayList<DayOfWeek> obtainUnavailableNights (Connection c, String tableName, int ID) throws SQLException
	{
		
		ArrayList<DayOfWeek> unavailableNights = new ArrayList<DayOfWeek>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableNight FROM " + tableName + " where ID = " + ID + ";");
		
		while(rs.next())
		{
			DayOfWeek dw = UtilityMethods.getDayOfWeekFromString(rs.getString("unavailableNight"));
			unavailableNights.add(dw);
		}
		
		return unavailableNights;
		
	}
	
	private static ArrayList<LocalDate> obtainUnavailableNightDates (Connection c, String tableName, int ID) throws SQLException
	{
		
		ArrayList<LocalDate> unavailableNightDates = new ArrayList<LocalDate>();
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT unavailableNightDate FROM " + tableName + " where ID = " + ID + ";");
		
		while(rs.next())
		{
			LocalDate ld = RAObject.getLocalDateFromString(rs.getString("unavailableNightDate"));
			unavailableNightDates.add(ld);
		}
		
		return unavailableNightDates;
		
	}
	
	private static int[] equalise()
	{

		int maxWeekends = recalculateMaxWeekends();
		int maxWeekdays = recalculateMaxWeekdays();
		
		int[] maxArray = new int[2];
		for (int i = 0; i < dutyNights.size(); i++)
		{
			DutyNight tempDN = dutyNights.get(i);
			DayOfWeek tempDW = tempDN.getDate().getDayOfWeek();
			ArrayList<RAObject> availableRAs = tempDN.getAvailableRAs();
			
			if(tempDW.equals(DayOfWeek.of(5)) || tempDW.equals(DayOfWeek.of(6)))
			{
				if (availableRAs.size() == 0)
				{
					continue;
				}
				
				if (availableRAs.size() == 1)
				{
					availableRAs.get(0).setWeekendsWorked(availableRAs.get(0).getWeekendsWorked() + 1);
					tempDN.setRA1(availableRAs.get(0));
					continue;
				}
				
				boolean ra1Found = false;
				boolean ra2Found = false;
				
				for (int j = 0; j < availableRAs.size(); j++)
				{
					RAObject tempRA = availableRAs.get(j);
					if (tempRA.getWeekendsWorked() < maxWeekends)
					{
						if (!(ra1Found))
						{
							tempRA.setWeekendsWorked(tempRA.getWeekendsWorked() + 1);
							tempDN.setRA1(tempRA);
							// tempDN.getAvailableRAs().remove(j); // could just set to null
							dutyNights.set(i, tempDN);
							ra1Found = true;
						}
						else if (!(ra2Found))
						{
							tempRA.setWeekendsWorked(tempRA.getWeekendsWorked() + 1);
							tempDN.setRA2(tempRA);
							// tempDN.getAvailableRAs().remove(j);
							dutyNights.set(i, tempDN);
							ra2Found = true;
						}
						else
						{
//							System.out.println("Alternate used");
							tempDN.addAlternateRA(tempRA);
							
							dutyNights.set(i, tempDN);
						}
					}
				}
				
				if (!(ra1Found))
				{
					Random r = new Random();
					int ra1Index = r.nextInt(availableRAs.size());
					int ra2Index = r.nextInt(availableRAs.size());
					while (ra2Index == ra1Index)
					{
						ra2Index = r.nextInt(availableRAs.size());
					}
					
					availableRAs.get(ra1Index).setWeekendsWorked(availableRAs.get(ra1Index).getWeekendsWorked() + 1);
					tempDN.setRA1(availableRAs.get(ra1Index));
					ra1Found = true;
					
					availableRAs.get(ra2Index).setWeekendsWorked(availableRAs.get(ra2Index).getWeekendsWorked() + 1);
					tempDN.setRA2(availableRAs.get(ra2Index));
					ra2Found = true;
					dutyNights.set(i, tempDN);
				}
				
				if (!(ra2Found))
				{
					Random r = new Random();
					int ra2Index = r.nextInt(availableRAs.size());
					while (availableRAs.get(ra2Index).getName().compareTo(tempDN.getRA1().getName()) == 0) // USE ID
					{
						ra2Index = r.nextInt(availableRAs.size());
					}
					
					availableRAs.get(ra2Index).setWeekendsWorked(availableRAs.get(ra2Index).getWeekendsWorked() + 1);
					tempDN.setRA2(availableRAs.get(ra2Index));
					ra2Found = true;
					dutyNights.set(i, tempDN);
				}
				
				for (int k = 0; k < availableRAs.size(); k++)
				{
					RAObject tempRA = availableRAs.get(k);
					if ((tempRA.getName().compareTo(tempDN.getRA1().getName()) != 0) && (tempRA.getName().compareTo(tempDN.getRA2().getName()) != 0) && !(tempDN.getAlternateRAs().contains(tempRA)))
					{
						tempDN.addAlternateRA(tempRA);
					}
				}
			
				maxWeekends = recalculateMaxWeekends();
				maxWeekdays = recalculateMaxWeekdays();
				
				System.out.println(tempDN.toString2());
				System.out.println("maxWeekends = " + maxWeekends);
				System.out.println("maxWeekdays = " + maxWeekdays);
				for (int j = 0; j < RAs.size(); j++)
				{
					System.out.print(RAs.get(j).getName() + " " + RAs.get(j).getWeekendsWorked() + " " + RAs.get(j).getWeekdaysWorked() + ", ");
				}
				System.out.println();
				System.out.println();
			}
			else
			{
				if (availableRAs.size() == 0)
				{
					continue;
				}
				
				if (availableRAs.size() == 1)
				{
					// availableRAs.get(0).setWeekendsWorked(availableRAs.get(0).getWeekendsWorked() + 1);
					availableRAs.get(0).setWeekdaysWorked(availableRAs.get(0).getWeekdaysWorked() + 1);
					tempDN.setRA1(availableRAs.get(0));
					continue;
				}
				
				boolean ra1Found = false;
				boolean ra2Found = false;
				
				for (int j = 0; j < availableRAs.size(); j++)
				{
					RAObject tempRA = availableRAs.get(j);
//					if (tempRA.getWeekendsWorked() < maxWeekends)
					if (tempRA.getWeekdaysWorked() < maxWeekdays)
					{
						if (!(ra1Found))
						{
//							tempRA.setWeekendsWorked(tempRA.getWeekendsWorked() + 1);
							tempRA.setWeekdaysWorked(tempRA.getWeekdaysWorked() + 1);
							tempDN.setRA1(tempRA);
							// tempDN.getAvailableRAs().remove(j); // could just set to null
							dutyNights.set(i, tempDN);
							ra1Found = true;
						}
						else if (!(ra2Found))
						{
//							tempRA.setWeekendsWorked(tempRA.getWeekendsWorked() + 1);
							tempRA.setWeekdaysWorked(tempRA.getWeekdaysWorked() + 1);
							tempDN.setRA2(tempRA);
							// tempDN.getAvailableRAs().remove(j);
							dutyNights.set(i, tempDN);
							ra2Found = true;
						}
						else
						{
//							System.out.println("Alternate used");
							tempDN.addAlternateRA(tempRA);
							
							dutyNights.set(i, tempDN);
						}
					}
				}
				
				if (!(ra1Found))
				{
					Random r = new Random();
					int ra1Index = r.nextInt(availableRAs.size());
					int ra2Index = r.nextInt(availableRAs.size());
					while (ra2Index == ra1Index)
					{
						ra2Index = r.nextInt(availableRAs.size());
					}
					
//					availableRAs.get(ra1Index).setWeekendsWorked(availableRAs.get(ra1Index).getWeekendsWorked() + 1);
					availableRAs.get(ra1Index).setWeekdaysWorked(availableRAs.get(ra1Index).getWeekdaysWorked() + 1);
					tempDN.setRA1(availableRAs.get(ra1Index));
					ra1Found = true;
					
//					availableRAs.get(ra2Index).setWeekendsWorked(availableRAs.get(ra2Index).getWeekendsWorked() + 1);
					availableRAs.get(ra2Index).setWeekdaysWorked(availableRAs.get(ra2Index).getWeekdaysWorked() + 1);
					tempDN.setRA2(availableRAs.get(ra2Index));
					ra2Found = true;
					dutyNights.set(i, tempDN);
				}
				
				if (!(ra2Found))
				{
					Random r = new Random();
					int ra2Index = r.nextInt(availableRAs.size());
					while (availableRAs.get(ra2Index).getName().compareTo(tempDN.getRA1().getName()) == 0) // USE ID
					{
						ra2Index = r.nextInt(availableRAs.size());
					}
					
//					availableRAs.get(ra2Index).setWeekendsWorked(availableRAs.get(ra2Index).getWeekendsWorked() + 1);
					availableRAs.get(ra2Index).setWeekdaysWorked(availableRAs.get(ra2Index).getWeekdaysWorked() + 1);
					tempDN.setRA2(availableRAs.get(ra2Index));
					ra2Found = true;
					dutyNights.set(i, tempDN);
				}
				
				for (int k = 0; k < availableRAs.size(); k++)
				{
					RAObject tempRA = availableRAs.get(k);
					if ((tempRA.getName().compareTo(tempDN.getRA1().getName()) != 0) && (tempRA.getName().compareTo(tempDN.getRA2().getName()) != 0) && !(tempDN.getAlternateRAs().contains(tempRA)))
					{
						tempDN.addAlternateRA(tempRA);
					}
				}
			
				maxWeekends = recalculateMaxWeekends();
				maxWeekdays = recalculateMaxWeekdays();
				
				System.out.println(tempDN.toString2());
				System.out.println("maxWeekends = " + maxWeekends);
				System.out.println("maxWeekdays = " + maxWeekdays);
				for (int j = 0; j < RAs.size(); j++)
				{
					System.out.print(RAs.get(j).getName() + " " + RAs.get(j).getWeekendsWorked() + " " + RAs.get(j).getWeekdaysWorked() + ", ");
				}
				System.out.println();
				System.out.println();
			}
		}
		
		maxArray[0] = maxWeekends;
		maxArray[1] = maxWeekdays;
		return maxArray;
	}
	
	private static int recalculateMaxWeekends()
	{
		int maxWeekends = 0;
		for (int i = 0; i < RAs.size(); i++)
		{
			if (RAs.get(i).getWeekendsWorked() > maxWeekends)
			{
				maxWeekends = RAs.get(i).getWeekendsWorked();
			}
		}
		return maxWeekends;
	}
	
	private static int recalculateMaxWeekdays()
	{
		int maxWeekdays = 0;
		for (int i = 0; i < RAs.size(); i++)
		{
			if (RAs.get(i).getWeekdaysWorked() > maxWeekdays)
			{
				maxWeekdays = RAs.get(i).getWeekdaysWorked();
			}
		}
		return maxWeekdays;
	}
	
	public static void run(Connection c, String sDate, String eDate, String outputFilePath) throws SQLException
	{
		
		System.out.println("running");
		
		RAs = getRAs(c, "Resident_Assistants"); // Remove hardcoding
		
		LocalDate startDate = RAObject.getLocalDateFromString(sDate);
		LocalDate endDate = RAObject.getLocalDateFromString(eDate);
		
		dutyNights = getDutyList(startDate, endDate);
		for (int i = 0; i < dutyNights.size(); i++)
		{
			DutyNight tempDN = dutyNights.get(i);
			//System.out.println(tempDN.getDate().toString() + " " + tempDN.getDate().getDayOfWeek().toString() + " " + tempDN.getShift());
			System.out.println(tempDN);
		}
		
		
		System.exit(0);
		
		
		for (int i = 0; i < dutyNights.size(); i++)
		{
			System.out.println(dutyNights.get(i));
		}
		
		for (int i = 0; i < RAs.size(); i++)
		{
			System.out.println(RAs.get(i));
		}
		
		int[] maxArray = equalise();
		
//		for (int i = 0; i < dutyNights.size(); i++)
//		{
//			System.out.println(dutyNights.get(i).toString2());
//			System.out.println("maxWeekends = " + maxWeekends);
//			for (int j = 0; j < RAs.size(); j++)
//			{
//				System.out.print(RAs.get(j).getName() + " " + RAs.get(j).getWeekendsWorked() + ", ");
//			}
//			System.out.println();
//		}
		
		for (int i = 0; i < RAs.size(); i++)
		{
			System.out.println(RAs.get(i));
		}
		
		System.out.println("maxWeekends = " + maxArray[0]);
		System.out.println("maxWeekdays = " + maxArray[1]);
		System.out.println("completed");
		
		// Output to file
		try
		{
			FileWriter fw = new FileWriter(outputFilePath, false);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.print("Date: ");
			pw.println(LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime());
			pw.println();
			pw.println("RA Duty Schedule from " + startDate + " to " + endDate);
			pw.println();
			
//			for (int i = 0; i < dutyNights.size(); i++)
//			{
//				pw.println(dutyNights.get(i).toString2());
//			}
			
			writeToFile(pw);
			
			for (int i = 0; i < RAs.size(); i++)
			{
				pw.println(RAs.get(i));
			}
			
			pw.close();
			fw.close();
			
		}
		catch (IOException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	private static void writeToFile(PrintWriter pw)
	{
		for (int i = 0; i < dutyNights.size(); i++)
		{
			DutyNight tempDN = dutyNights.get(i);
			RAObject ra1 = tempDN.getRA1();
			RAObject ra2 = tempDN.getRA2();
			LocalDate date = tempDN.getDate();
			ArrayList<RAObject> alternateRAs = tempDN.getAlternateRAs();
			
			if (ra1 == null)
			{
				pw.print(date + " " + date.getDayOfWeek().toString() + " ");
			}
			else if (ra2 == null)
			{
				pw.println(date + " " + date.getDayOfWeek().toString());
				pw.print(ra1.getName());
			}
			else if (alternateRAs.size() == 0)
			{
				pw.println(date + " " + date.getDayOfWeek().toString());
				pw.println(ra1.getName());
				pw.print(ra2.getName());
			}
			else
			{
				pw.println(date + " " + date.getDayOfWeek().toString());
				pw.println(ra1.getName());
				pw.println(ra2.getName());
				pw.print("Alternates:");
				for (int j = 0; j < alternateRAs.size(); j++)
				{
					pw.print(" " + alternateRAs.get(j).getName());  
				}
			}
			
			pw.println();
			pw.println();
		}
	}
	
	// Implement weekdays
	// To implement day duties, have special variables for every RA that determine whether they are available 
	// for day duty SAT SUN or not, then when iterating through the dates, if a day is SAT or SUN, treat it 
	// as 1 day shift (weekday) and 1 weekend shift instead of 1 total shift. The day shift looks at the RAs variables
	// (and not the unavailable weekdays). The weekend shift looks at the RAs unavailability as usual. 
	// Also add unavailable dates for day duty (for example 2015-09-02D) means they are unavailable for the day of 
	// 2nd September, not necessarily night.
}
