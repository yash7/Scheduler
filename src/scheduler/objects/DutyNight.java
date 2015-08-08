package scheduler.objects;

public class DutyNight extends DutyShift
{
	
//	private LocalDate date;
//	private RAObject ra1;
	private RAObject ra2;
//	private ArrayList<RAObject> alternateRAs;
//	private ArrayList<RAObject> availableRAs;
//	private int shift;
	
	public DutyNight() // remember to remove argument
	{
		super();
//		alternateRAs = new ArrayList<RAObject>();
//		availableRAs = new ArrayList<RAObject>();
//		this.shift = shift;
		setShiftType(ShiftType.NIGHT);
	}
	
//	public LocalDate getDate()
//	{
//		return date;
//	}
//	
//	public RAObject getRA1()
//	{
//		return ra1;
//	}
	
	public RAObject getRA2()
	{
		return ra2;
	}
	
//	public ArrayList<RAObject> getAlternateRAs()
//	{
//		return alternateRAs;
//	}
//	
//	public ArrayList<RAObject> getAvailableRAs()
//	{
//		return availableRAs;
//	}
//	
//	public int getShift()
//	{
//		return shift;
//	}
	
//	public void setDate(LocalDate ld)
//	{
//		date = ld;
//	}
//	
//	public void setRA1(RAObject ra)
//	{
//		ra1 = ra;
//	}
	
	public void setRA2(RAObject ra)
	{
		ra2 = ra;
	}
	
//	public void addAlternateRA(RAObject ra)
//	{
//		alternateRAs.add(ra);
//	}
//	
//	public void setShift(int s)
//	{
//		shift = s;
//	}
	
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
		
		s = (getDate() + " " + getDate().getDayOfWeek().toString());
		for (int i = 0; i < getAvailableRAs().size(); i++)
		{
			s = (s + " " + getAvailableRAs().get(i).getName());  
		}
		
//		return s + " " + shift;
		return s;
	}
	
	public String toString2()
	{
		String s;
		
		if (getRA1() == null)
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString());
		}
		else if (ra2 == null)
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString() + "\n" + getRA1().getName());
		}
		else if (getAlternateRAs().size() == 0)
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString() + "\n" + getRA1().getName() + "\n" + ra2.getName());
		}
		else
		{
			s = (getDate() + " " + getDate().getDayOfWeek().toString() + " " + getShiftType().toString() + "\n" + getRA1().getName() + "\n" + ra2.getName() + "\nAlternates:");
			for (int i = 0; i < getAlternateRAs().size(); i++)
			{
				s = (s + " " + getAlternateRAs().get(i).getName());  
			}
		}
		
		// s = (s + "\n");
		
//		return s + " " + shift;
		return s;
	}

//	@Override
//	public String testPrint()
//	{
//		return "dutyNight";
//	}
}
