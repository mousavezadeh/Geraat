package mousavi.date.unit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mydate {
	
public mydate() {
	// TODO Auto-generated constructor stub
}


public String current_date_to_fasridate()
{
	 Date cDate = new Date();
	 String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
	 CalendarTool ct = new CalendarTool(Integer.parseInt(fDate.substring(0,4)),Integer.parseInt(fDate.substring(5,7)),Integer.parseInt(fDate.substring(8,10)));
	 return ct.getIranianDate();
	}


public String current_date_to_fasridate(Date date_to_convert)
{
	 
	 String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date_to_convert);
	 CalendarTool ct = new CalendarTool(Integer.parseInt(fDate.substring(0,4)),Integer.parseInt(fDate.substring(5,7)),Integer.parseInt(fDate.substring(8,10)));
	 return ct.getIranianDate();
	}

}
