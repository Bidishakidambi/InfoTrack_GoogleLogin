package infoTrack.testcase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

	public String getTime() {
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
    	return sdf.format(cal.getTime());		
	}

}
