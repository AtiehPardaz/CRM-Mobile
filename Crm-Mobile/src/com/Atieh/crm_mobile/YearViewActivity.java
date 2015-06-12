package com.Atieh.crm_mobile;

import singleTones.monthCounter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.Atieh.crm_mobile_calendar.CivilDate;
import com.Atieh.crm_mobile_calendar.DateConverter;
import com.Atieh.crm_mobile_calendar.DayOutOfRangeException;
import com.Atieh.crm_mobile_calendar.MainActivity;
import com.Atieh.crm_mobile_calendar.PersianDate;
import com.Atieh.crm_mobile_calendar.Range;
import com.Atieh.crm_mobile_calendar.Utils;

public class YearViewActivity extends Activity {

	private final Utils utils = Utils.getInstance();

	Context context;
	TextView txt_title;

	LinearLayout mainRoot1;
	LinearLayout mainRoot2;
	LinearLayout mainRoot3;
	LinearLayout mainRoot4;
	LinearLayout mainRoot5;
	LinearLayout mainRoot6;
	LinearLayout mainRoot7;
	LinearLayout mainRoot8;
	LinearLayout mainRoot9;
	LinearLayout mainRoot10;
	LinearLayout mainRoot11;
	LinearLayout mainRoot12;
	Button btn_mounthview_new_task,btn_mounthview_new_activity;
	ProgressBar progressBar1;

	ScrollView scrollView1_year;

	public static String mounthTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year_view);

		context = this;

		scrollView1_year = (ScrollView) findViewById(R.id.scrollView1_year);
		utils.loadHolidays(getResources().openRawResource(R.raw.holidays));
		utils.LoadTasksdays(this.getBaseContext());
		utils.LoadActivitiesdays(this.getBaseContext());

		PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());
		monthCounter.getInstance().setYear(persianDate.getYear());

		txt_title = (TextView) findViewById(R.id.txt_year_title);
		txt_title.setText(monthCounter.getInstance().getYear() + "");
		ImageButton next_year = (ImageButton) findViewById(R.id.imgbtn_next_year);
		ImageButton previous_year = (ImageButton) findViewById(R.id.imgbtn_back_year);
		ImageButton btn_home_calander_year = (ImageButton) findViewById(R.id.btn_home_calander_year);
		ImageButton btn_plus_year = (ImageButton) findViewById(R.id.btn_plus_year);
		btn_mounthview_new_task = (Button) findViewById(R.id.btn_mounthview_new_task);
		btn_mounthview_new_activity = (Button) findViewById(R.id.btn_mounthview_new_activity);
		btn_mounthview_new_task.setVisibility(View.GONE);
		btn_mounthview_new_activity.setVisibility(View.GONE);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		
		progressBar1.setVisibility(View.GONE);
		
		btn_plus_year.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btn_mounthview_new_task.setVisibility(View.VISIBLE);
				btn_mounthview_new_activity.setVisibility(View.VISIBLE);
			}
		});
		
		btn_mounthview_new_task.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(YearViewActivity.this,NewTaskActivity.class);
				startActivity(intent);
				
			}
		});
		
		btn_mounthview_new_activity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(YearViewActivity.this,NewActivitiesActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		btn_home_calander_year.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(YearViewActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		});

		next_year.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				progressBar1.setVisibility(View.VISIBLE);

				monthCounter.getInstance().setYear(
						monthCounter.getInstance().getYear() + 1);
				mainRoot1.removeAllViews();
				mainRoot2.removeAllViews();
				mainRoot3.removeAllViews();
				mainRoot4.removeAllViews();
				mainRoot5.removeAllViews();
				mainRoot6.removeAllViews();
				mainRoot7.removeAllViews();
				mainRoot8.removeAllViews();
				mainRoot9.removeAllViews();
				mainRoot10.removeAllViews();
				mainRoot11.removeAllViews();
				mainRoot12.removeAllViews();
				txt_title.setText(Integer.valueOf(txt_title.getText()
						.toString()) + 1 + "");
				inflateMonthes();
				
				progressBar1.setVisibility(View.GONE);

			}
		});

		previous_year.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				progressBar1.setVisibility(View.VISIBLE);

				monthCounter.getInstance().setYear(
						monthCounter.getInstance().getYear() - 1);
				mainRoot1.removeAllViews();
				mainRoot2.removeAllViews();
				mainRoot3.removeAllViews();
				mainRoot4.removeAllViews();
				mainRoot5.removeAllViews();
				mainRoot6.removeAllViews();
				mainRoot7.removeAllViews();
				mainRoot8.removeAllViews();
				mainRoot9.removeAllViews();
				mainRoot10.removeAllViews();
				mainRoot11.removeAllViews();
				mainRoot12.removeAllViews();
				txt_title.setText(Integer.valueOf(txt_title.getText()
						.toString()) - 1 + "");

				inflateMonthes();
				progressBar1.setVisibility(View.GONE);

			}
		});

		mainRoot1 = (LinearLayout) findViewById(R.id.l1);

		mainRoot2 = (LinearLayout) findViewById(R.id.l2);

		mainRoot3 = (LinearLayout) findViewById(R.id.l3);

		mainRoot4 = (LinearLayout) findViewById(R.id.l4);

		mainRoot5 = (LinearLayout) findViewById(R.id.l5);

		mainRoot6 = (LinearLayout) findViewById(R.id.l6);

		mainRoot7 = (LinearLayout) findViewById(R.id.l7);

		mainRoot8 = (LinearLayout) findViewById(R.id.l8);

		mainRoot9 = (LinearLayout) findViewById(R.id.l9);

		mainRoot10 = (LinearLayout) findViewById(R.id.l10);

		mainRoot11 = (LinearLayout) findViewById(R.id.l11);

		mainRoot12 = (LinearLayout) findViewById(R.id.l12);

		inflateMonthes();
		HomeActivity.progressBar1.setVisibility(View.GONE);
	}

	private void inflateMonthes() {
		mounthCalculator(mainRoot3);
		mounthCalculator(mainRoot2);
		mounthCalculator(mainRoot1);
		mounthCalculator(mainRoot6);
		mounthCalculator(mainRoot5);
		mounthCalculator(mainRoot4);
		mounthCalculator(mainRoot9);
		mounthCalculator(mainRoot8);
		mounthCalculator(mainRoot7);
		mounthCalculator(mainRoot12);
		mounthCalculator(mainRoot11);
		mounthCalculator(mainRoot10);
	}

	private void mounthCalculator(LinearLayout mainRoot) {

		// TextView[][] daysTextViews = new TextView[7][7];
		// LinearLayout root = new LinearLayout(context);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.month, null);

		PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());

		int month = monthCounter.getInstance().getMounth();
		monthCounter.getInstance().setMounth(month + 1);

		if (month == 12) {
			monthCounter.getInstance().setMounth(1);
		}

		month -= 1;
		int year = monthCounter.getInstance().getYear();

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

		currentMonthTextView
				.setText(utils.getMonthYearTitleEdited(persianDate));

		for (int i : new Range(0, 7)) {
			// TextView textView = ;
			gettextView(v, 0, 6 - i).setText(
					utils.firstCharOfDaysOfWeekNameBrif[i]);
			// gettextView(v, 0, 6 - i).setBackgroundColor(
			// Color.parseColor("#DDDDDD"));
			gettextView(v, 0, 6 - i).setGravity(Gravity.CENTER_HORIZONTAL);

		}
		try {
			PersianDate today = DateConverter.civilToPersian(new CivilDate());
			for (int i : new Range(1, 31)) {
				persianDate.setDayOfMonth(i);

				gettextView(v, weekOfMonth, 6 - dayOfWeek).setText(
						utils.formatNumber(i, digits));
				// gettextView(v, weekOfMonth, 6 - dayOfWeek)
				// .setBackgroundResource(R.drawable.days);

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
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		btn_mounthview_new_task.setVisibility(View.GONE);
		btn_mounthview_new_activity.setVisibility(View.GONE);
		progressBar1.setVisibility(View.GONE);

	}
}
