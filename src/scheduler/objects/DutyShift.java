package scheduler.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class DutyShift 
{
	private LocalDate date;
	private RAObject ra1;
	private ArrayList<RAObject> alternateRAs;
	private ArrayList<RAObject> availableRAs;
	private ShiftType shiftType;
	
	public enum ShiftType
	{
		DAY, NIGHT
	}
	
	public DutyShift()
	{
		alternateRAs = new ArrayList<RAObject>();
		availableRAs = new ArrayList<RAObject>();
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public RAObject getRA1()
	{
		return ra1;
	}
	
	public ArrayList<RAObject> getAlternateRAs()
	{
		return alternateRAs;
	}
	
	public ArrayList<RAObject> getAvailableRAs()
	{
		return availableRAs;
	}

	public ShiftType getShiftType()
	{
		return shiftType;
	}
	
	public void setDate(LocalDate ld)
	{
		date = ld;
	}
	
	public void setRA1(RAObject ra)
	{
		ra1 = ra;
	}
	
	public void setShiftType(ShiftType st)
	{
		shiftType = st;
	}
	
	public void addAlternateRA(RAObject ra)
	{
		alternateRAs.add(ra);
	}
	
	public String toString()
	{
		return (date + " " + date.getDayOfWeek().toString());
	}
	
//	public String testPrint()
//	{
//		return "dutyShift";
//	}
}
