package scheduler.objects;

public class DutyDay extends DutyShift
{
	public DutyDay()
	{
		super();
		setShiftType(ShiftType.DAY);
	}
	
	public String toString()
	{
		String s;
		
		if(getRA1() == null)
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString());
			return s;
		}
		else if(getAlternateRAs().size() == 0)
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString() + "\n" + getRA1().getName());	
		}
		else
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString() + "\n" + getRA1().getName() + "\nAlternates:");
			for (int i = 0; i < getAlternateRAs().size(); i++)
			{
				s = (s + " " + getAlternateRAs().get(i).getName());  
			}
		}
		
		return s;
	}
	
//	@Override
//	public String testPrint()
//	{
//		return "dutyDay";
//	}
}
