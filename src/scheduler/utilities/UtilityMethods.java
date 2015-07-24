package scheduler.utilities;

import java.time.DayOfWeek;

public class UtilityMethods 
{
	public static DayOfWeek getDayOfWeekFromString(String sdw)  // Make Global method
	{
		String temp = sdw.toLowerCase();
		DayOfWeek dw;
		
		switch(temp)
		{
			case "monday":
			{
				dw = DayOfWeek.of(1);
				break;
			}
			case "tuesday":
			{
				dw = DayOfWeek.of(2);
				break;
			}
			case "wednesday":
			{
				dw = DayOfWeek.of(3);
				break;
			}
			case "thursday":
			{
				dw = DayOfWeek.of(4);
				break;
			}
			case "friday":
			{
				dw = DayOfWeek.of(5);
				break;
			}
			case "saturday":
			{
				dw = DayOfWeek.of(6);
				break;
			}
			case "sunday":
			{
				dw = DayOfWeek.of(7);
				break;
			}
			default:
			{
				dw = null;
				break;
			}
		}
		
		return dw;
	}
	
}
