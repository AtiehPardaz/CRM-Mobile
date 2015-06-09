package singleTones;

import com.Atieh.crm_mobile_calendar.CivilDate;
import com.Atieh.crm_mobile_calendar.DateConverter;
import com.Atieh.crm_mobile_calendar.PersianDate;

public class mountCounter {
	private static mountCounter mInstance = null;

	private int Mounth;
	private int Year;
	
	PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());
	PersianDate persianDate1 = DateConverter
			.civilToPersian(new CivilDate());
	

	private mountCounter(){
		Mounth = 1;
		Year = persianDate.getYear();
	}

	public static mountCounter getInstance(){
		if(mInstance == null)
		{
			mInstance = new mountCounter();
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

	public void setYear(int mounth) {
		Year = mounth;
	}
	

}

