package singleTones;

import com.Atieh.crm_mobile_calendar.CivilDate;
import com.Atieh.crm_mobile_calendar.DateConverter;
import com.Atieh.crm_mobile_calendar.PersianDate;

public class monthCounter {
	private static monthCounter mInstance = null;

	private int Mounth;
	private int Year;
	

	

	private monthCounter(){
		Mounth = 1;
		Year = 1;
	}

	public static monthCounter getInstance(){
		if(mInstance == null)
		{
			mInstance = new monthCounter();
		}
		return mInstance;
	}

	public int getMounth() {
		return Mounth;
	}

	public void setMounth(int mounth) {
		Mounth = mounth;
	}
	
	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}
	

}

