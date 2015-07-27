package scheduler.objects;

import java.util.Date;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ArrayList;

import scheduler.utilities.UtilityMethods;

public class RAObject 
{
	
//	private static int universalID = 0;
//	private int ID;
	private String name;
	private ArrayList<LocalDate> unavailableNightDates;
	private ArrayList<DayOfWeek> unavailableNights;
	private ArrayList<LocalDate> unavailableDayDates; 
	private ArrayList<DayOfWeek> unavailableDays;
	private int weekendsWorked;
	private int weekdaysWorked;
	// private Date[] availableDates;
	
	public RAObject() 
	{
//		universalID++;
//		ID = universalID;
		unavailableNightDates = new ArrayList<LocalDate>();
		unavailableNights = new ArrayList<DayOfWeek>();
		unavailableDayDates = new ArrayList<LocalDate>();
		unavailableDays = new ArrayList<DayOfWeek>();
		weekendsWorked = 0;
		weekdaysWorked = 0;
	}
	
//	public int getID()
//	{
//		return ID;
//	}
	
	public String getName() 
	{
		return name;
	}
	
	public ArrayList<LocalDate> getUnavailableNightDates() 
	{
		return unavailableNightDates;
	}
	
	public ArrayList<DayOfWeek> getUnavailableNights()
	{
		return unavailableNights;
	}
	
//	public ArrayList<LocalDate> getUnavailableDayDates()
//	{
//		return unavailableDayDates;
//	}
//	
//	public ArrayList<DayOfWeek> getUnavailableDays()
//	{
//		return unavailableDays;
//	}
//	
	public int getWeekendsWorked()
	{
		return weekendsWorked;
	}
	
	public int getWeekdaysWorked()
	{
		return weekdaysWorked;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setUnavailableNightDates(ArrayList<LocalDate> unNDates)
	{
		unavailableNightDates = unNDates;
	}
	
	public void setUnavailableNights(ArrayList<DayOfWeek> unNights)
	{
		unavailableNights = unNights;
	}
	
	public void addUnavailableNightDate(LocalDate ld)
	{
		unavailableNightDates.add(ld);
	}
	
	public void addUnavailableNightDates(ArrayList<LocalDate> ldList)
	{
		unavailableNightDates.addAll(ldList);
	}
	
	public void addUnavailableNight(String sdw)
	{
		DayOfWeek dw = UtilityMethods.getDayOfWeekFromString(sdw);
		
		if (!(unavailableNights.contains(dw)))
		{
			unavailableNights.add(dw);
		}
	}

	
	// Remember to uncomment
//	public void setUnavailableDayDates(ArrayList<LocalDate> unDDates)
//	{
//		unavailableDayDates = unDDates;
//	}
//	
//	public void setUnavailableDays(ArrayList<DayOfWeek> unDays)
//	{
//		unavailableDays = unDays;
//	}
//	
//	public void addUnavailableDayDate(LocalDate ld)
//	{
//		unavailableDayDates.add(ld);
//	}
//	
//	public void addUnavailableDayDates(ArrayList<LocalDate> ldList)
//	{
//		unavailableDayDates.addAll(ldList);
//	}
//	
//	public void addUnavailableDay(String sdw)
//	{
//		DayOfWeek dw = UtilityMethods.getDayOfWeekFromString(sdw);
//		
//		if (!(unavailableDays.contains(dw)))
//		{
//			unavailableDays.add(dw);
//		}
//	}
//	
	public static LocalDate getLocalDateFromString (String sdw)
	{
		
		String[] s = sdw.split("-");
		int year = Integer.parseInt(s[0]);
		int month = Integer.parseInt(s[1]);
		int day = Integer.parseInt(s[2]);
		
		LocalDate ld = LocalDate.of(year, month, day);
		
		return ld;
		
	}
	
	public void setWeekendsWorked(int ww)
	{
		weekendsWorked = ww;
	}
	
	public void setWeekdaysWorked(int ww)
	{
		weekdaysWorked = ww;
	}
	
	public String toString()
	{
		return (name + " weekends = " + weekendsWorked + " weekdays = " + weekdaysWorked);
	}
	
//	public ArrayList<LocalDate> getAvailableDates(LocalDate startDate, LocalDate endDate)
//	{
//		ArrayList<LocalDate> temp = new ArrayList<LocalDate>();
//
//		long i = ChronoUnit.DAYS.between(startDate, endDate);
//		LocalDate tempDate = startDate;
//		for (int j = 0; j <= i; j++)
//		{
//			
//			tempDate = tempDate.plusDays(1);
//		}
//		
//		return temp;
//	}
//	
//	public Date[] getAvailableDates(Date startDate, Date endDate)
//	{
//		Calendar ca1 = Calendar.getInstance();
//		ca1.setTime(startDate);
//		
//		for (int i = 0; i <)
//		
//	}
}
