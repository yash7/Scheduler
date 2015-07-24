package scheduler.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class DutyNight 
{
	
	private LocalDate date;
	private RAObject ra1;
	private RAObject ra2;
	private ArrayList<RAObject> alternateRAs;
	private ArrayList<RAObject> availableRAs;
	
	public DutyNight()
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
	
	public RAObject getRA2()
	{
		return ra2;
	}
	
	public ArrayList<RAObject> getAlternateRAs()
	{
		return alternateRAs;
	}
	
	public ArrayList<RAObject> getAvailableRAs()
	{
		return availableRAs;
	}
	
	public void setDate(LocalDate ld)
	{
		date = ld;
	}
	
	public void setRA1(RAObject ra)
	{
		ra1 = ra;
	}
	
	public void setRA2(RAObject ra)
	{
		ra2 = ra;
	}
	
	public void addAlternateRA(RAObject ra)
	{
		alternateRAs.add(ra);
	}
	
	public String toString() // check for nulls
	{
		String s;
		
//		if (ra1 == null)
//		{
//			s = (date + " " + date.getDayOfWeek().toString() + " ");
//			return s;
//		}
//		else if (ra2 == null)
//		{
//			s = (date + " " + date.getDayOfWeek().toString() + " " + ra1.getName());
//			return s;
//		}
//		else if (alternateRAs.size() == 0)
//		{
//			s = (date + " " + date.getDayOfWeek().toString() + " " + ra1.getName() + " " + ra2.getName());
//		}
//		else
//		{
//			s = (date + " " + date.getDayOfWeek().toString() + " " + ra1.getName() + " " + ra2.getName());
//			for (int i = 0; i < alternateRAs.size(); i++)
//			{
//				s = (s + " " + alternateRAs.get(i).getName());  
//			}
//		}
		
		s = (date + " " + date.getDayOfWeek().toString());
		for (int i = 0; i < availableRAs.size(); i++)
		{
			s = (s + " " + availableRAs.get(i).getName());  
		}
		
		return s;
	}
	
	public String toString2()
	{
		String s;
		
		if (ra1 == null)
		{
			s = (date + " " + date.getDayOfWeek().toString() + " ");
			return s;
		}
		else if (ra2 == null)
		{
			s = (date + " " + date.getDayOfWeek().toString() + "\n\n" + ra1.getName());
			return s;
		}
		else if (alternateRAs.size() == 0)
		{
			s = (date + " " + date.getDayOfWeek().toString() + "\n\n" + ra1.getName() + "\n\n" + ra2.getName());
		}
		else
		{
			s = (date + " " + date.getDayOfWeek().toString() + "\n\n" + ra1.getName() + "\n\n" + ra2.getName() + "\n\nAlternates:");
			for (int i = 0; i < alternateRAs.size(); i++)
			{
				s = (s + " " + alternateRAs.get(i).getName());  
			}
		}
		
		s = (s + "\n\n");
		
		return s;
	}

}
