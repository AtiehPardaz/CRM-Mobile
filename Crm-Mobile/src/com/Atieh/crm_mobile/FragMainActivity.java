package com.Atieh.crm_mobile;

import java.util.zip.Inflater;

import singleTones.mountCounter;
import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import singleTones.mountCounter;

import com.Atieh.crm_mobile_calendar.CivilDate;
import com.Atieh.crm_mobile_calendar.DateConverter;
import com.Atieh.crm_mobile_calendar.DayOutOfRangeException;
import com.Atieh.crm_mobile_calendar.MainActivity;
import com.Atieh.crm_mobile_calendar.PersianDate;
import com.Atieh.crm_mobile_calendar.Range;
import com.Atieh.crm_mobile_calendar.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class FragMainActivity extends Activity {

	private final Utils utils = Utils.getInstance();

	Context context;

	int width, height;
	public static String mounthTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frag_main);

		context = this;

		utils.loadHolidays(getResources().openRawResource(R.raw.holidays));
		utils.LoadTasksdays(this.getBaseContext());
		utils.LoadActivitiesdays(this.getBaseContext());

		LinearLayout mainRoot1 = (LinearLayout) findViewById(R.id.l1);

		mounthCalculator(mainRoot1);

		LinearLayout mainRoot2 = (LinearLayout) findViewById(R.id.l2);

		mounthCalculator(mainRoot2);

		LinearLayout mainRoot3 = (LinearLayout) findViewById(R.id.l3);
		mounthCalculator(mainRoot3);

		LinearLayout mainRoot4 = (LinearLayout) findViewById(R.id.l4);
		mounthCalculator(mainRoot4);

		LinearLayout mainRoot5 = (LinearLayout) findViewById(R.id.l5);
		mounthCalculator(mainRoot5);

		LinearLayout mainRoot6 = (LinearLayout) findViewById(R.id.l6);
		mounthCalculator(mainRoot6);

		LinearLayout mainRoot7 = (LinearLayout) findViewById(R.id.l7);
		mounthCalculator(mainRoot7);

		LinearLayout mainRoot8 = (LinearLayout) findViewById(R.id.l8);
		mounthCalculator(mainRoot8);

		LinearLayout mainRoot9 = (LinearLayout) findViewById(R.id.l9);
		mounthCalculator(mainRoot9);

		LinearLayout mainRoot10 = (LinearLayout) findViewById(R.id.l10);
		mounthCalculator(mainRoot10);

		LinearLayout mainRoot11 = (LinearLayout) findViewById(R.id.l11);
		mounthCalculator(mainRoot11);

		LinearLayout mainRoot12 = (LinearLayout) findViewById(R.id.l12);
		mounthCalculator(mainRoot12);

	}

	
	public void mounthCalculator(LinearLayout mainRoot) {

		// TextView[][] daysTextViews = new TextView[7][7];
		// LinearLayout root = new LinearLayout(context);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.month, null);

		PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());

		int month = mountCounter.getInstance().getMounth();
		mountCounter.getInstance().setMounth(month + 1);

		if (month == 12) {
			mountCounter.getInstance().setMounth(1);
		}

		month -= 1;
		int year = persianDate.getYear();

		year = year + (month / 12);
		month = month % 12;
		if (month < 0) {
			year -= 1;
			month += 12;
		}
		month += 1;

		persianDate.setMonth(month);
		persianDate.setYear(year);
		persianDate.setDayOfMonth(1);
		persianDate.setDari(utils.isDariVersion(context));

		char[] digits = utils.preferredDigits(context);

		int weekOfMonth = 1;
		int dayOfWeek = DateConverter.persianToCivil(persianDate)
				.getDayOfWeek() % 7;

		TextView currentMonthTextView = (TextView) v
				.findViewById(R.id.currentMonthTextView);
		currentMonthTextView.setText(utils.getMonthYearTitle(persianDate,
				digits));

		for (int i : new Range(0, 7)) {
			// TextView textView = ;
			gettextView(v, 0, 6 - i).setText(
					utils.firstCharOfDaysOfWeekNameBrif[i]);
//			gettextView(v, 0, 6 - i).setBackgroundColor(
//					Color.parseColor("#DDDDDD"));
			gettextView(v, 0, 6 - i).setGravity(Gravity.CENTER_HORIZONTAL);

		}
		try {
			PersianDate today = DateConverter.civilToPersian(new CivilDate());
			for (int i : new Range(1, 31)) {
				persianDate.setDayOfMonth(i);

				gettextView(v, weekOfMonth, 6 - dayOfWeek).setText(
						utils.formatNumber(i, digits));
//				gettextView(v, weekOfMonth, 6 - dayOfWeek)
//						.setBackgroundResource(R.drawable.days);
				
				gettextView(v, weekOfMonth, 6 - dayOfWeek).setGravity(
						Gravity.CENTER_HORIZONTAL);

				String tasksTitle = utils.getTasksdayTitle(persianDate);
				String activitiesTitle = utils
						.getActivitiessdayTitle(persianDate);

				if (tasksTitle != null || activitiesTitle != null) {

					gettextView(v, weekOfMonth, 6 - dayOfWeek)
							.setBackgroundColor(Color.parseColor("#DADAFF"));

				}

				dayOfWeek++;
				if (dayOfWeek == 7) {
					weekOfMonth++;
					dayOfWeek = 0;
				}
			}
		} catch (DayOutOfRangeException e) {
			// okay, it was expected
		}
		mainRoot.addView(v);
	}

	private TextView gettextView(View v, int x, int y) {

		switch (x) {
		case 0:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t01);
			case 1:
				return (TextView) v.findViewById(R.id.t02);
			case 2:
				return (TextView) v.findViewById(R.id.t03);
			case 3:
				return (TextView) v.findViewById(R.id.t04);
			case 4:
				return (TextView) v.findViewById(R.id.t05);
			case 5:
				return (TextView) v.findViewById(R.id.t06);
			case 6:
				return (TextView) v.findViewById(R.id.t07);

			default:
				break;
			}
			break;

		case 1:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t11);
			case 1:
				return (TextView) v.findViewById(R.id.t12);
			case 2:
				return (TextView) v.findViewById(R.id.t13);
			case 3:
				return (TextView) v.findViewById(R.id.t14);
			case 4:
				return (TextView) v.findViewById(R.id.t15);
			case 5:
				return (TextView) v.findViewById(R.id.t16);
			case 6:
				return (TextView) v.findViewById(R.id.t17);

			default:
				break;
			}
			break;

		case 2:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t21);
			case 1:
				return (TextView) v.findViewById(R.id.t22);
			case 2:
				return (TextView) v.findViewById(R.id.t23);
			case 3:
				return (TextView) v.findViewById(R.id.t24);
			case 4:
				return (TextView) v.findViewById(R.id.t25);
			case 5:
				return (TextView) v.findViewById(R.id.t26);
			case 6:
				return (TextView) v.findViewById(R.id.t27);

			default:
				break;
			}
			break;

		case 3:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t31);
			case 1:
				return (TextView) v.findViewById(R.id.t32);
			case 2:
				return (TextView) v.findViewById(R.id.t33);
			case 3:
				return (TextView) v.findViewById(R.id.t34);
			case 4:
				return (TextView) v.findViewById(R.id.t35);
			case 5:
				return (TextView) v.findViewById(R.id.t36);
			case 6:
				return (TextView) v.findViewById(R.id.t37);

			default:
				break;
			}

		case 4:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t41);
			case 1:
				return (TextView) v.findViewById(R.id.t42);
			case 2:
				return (TextView) v.findViewById(R.id.t43);
			case 3:
				return (TextView) v.findViewById(R.id.t44);
			case 4:
				return (TextView) v.findViewById(R.id.t45);
			case 5:
				return (TextView) v.findViewById(R.id.t46);
			case 6:
				return (TextView) v.findViewById(R.id.t47);

			default:
				break;
			}
			break;

		case 5:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t51);
			case 1:
				return (TextView) v.findViewById(R.id.t52);
			case 2:
				return (TextView) v.findViewById(R.id.t53);
			case 3:
				return (TextView) v.findViewById(R.id.t54);
			case 4:
				return (TextView) v.findViewById(R.id.t55);
			case 5:
				return (TextView) v.findViewById(R.id.t56);
			case 6:
				return (TextView) v.findViewById(R.id.t57);

			default:
				break;
			}
			break;

		case 6:
			switch (y) {
			case 0:
				return (TextView) v.findViewById(R.id.t61);
			case 1:
				return (TextView) v.findViewById(R.id.t62);
			case 2:
				return (TextView) v.findViewById(R.id.t63);
			case 3:
				return (TextView) v.findViewById(R.id.t64);
			case 4:
				return (TextView) v.findViewById(R.id.t65);
			case 5:
				return (TextView) v.findViewById(R.id.t66);
			case 6:
				return (TextView) v.findViewById(R.id.t67);

			default:
				break;
			}
			break;

		default:
			break;
		}
		return null;

	}
}
