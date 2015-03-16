package com.Atieh.crm_mobile_calendar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Atieh.crm_mobile.HomeActivity;
import com.Atieh.crm_mobile.R;
import com.Atieh.crm_mobile_calendar.MonthFragment;

public class MainActivity extends FragmentActivity {
	// I know, it is ugly, but user will not notify this and this will not have
	// a memory problem
	private static final int MONTHS_LIMIT = 1200;
	public Utils utils = Utils.getInstance();
	private ViewPager viewPager;
	private TextView calendarInfo;
	ImageButton selectdate;
	public static TextView title_month;
	private Button resetButton;
	private PrayTimeActivityHelper prayTimeActivityHelper;
	ImageButton next;
	ImageButton back;
	String year;
	String month;
	int j = 1;
	int monthnum = 1;

	// public static StringBuilder sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		utils.setTheme(this);
		super.onCreate(savedInstanceState);
		// for notification
		// startService(new Intent(this, ApplicationService.class));

		boolean removeTitle = true;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			if (!hasPermanentMenuKey()) {
				removeTitle = false;
			}
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
				&& Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR2) {
			// if a tablet version is installed
			removeTitle = false;
		}
		if (removeTitle) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		setContentView(R.layout.calendar);

		calendarInfo = (TextView) findViewById(R.id.calendar_info);
		title_month = (TextView) findViewById(R.id.tv_title_monthview);
		next = (ImageButton) findViewById(R.id.imgbtn_next_month);
		back = (ImageButton) findViewById(R.id.imgbtn_back_month);
		selectdate = (ImageButton) findViewById(R.id.imgbtn_selectcal_calander);
		// Pray Time
		prayTimeActivityHelper = new PrayTimeActivityHelper(this);
		prayTimeActivityHelper.fillPrayTime();

		// Load Holidays
		utils.loadHolidays(getResources().openRawResource(R.raw.holidays));

		// Reset button
		resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setText(utils.today);
		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				bringTodayYearMonth();
				setFocusedDay(DateConverter.civilToPersian(new CivilDate()));
			}
		});

		// get intent from select date activiy

		// Initializing the viewPager
		viewPager = (ViewPager) findViewById(R.id.calendar_pager);
		viewPager.setAdapter(createCalendarAdaptor());
		viewPager.setCurrentItem(MONTHS_LIMIT / 2);
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				updateResetButtonState();
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
		});
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = viewPager.getCurrentItem();
				i++;
				viewPager.setCurrentItem(i);

			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = viewPager.getCurrentItem();
				i--;
				viewPager.setCurrentItem(i);

			}
		});
		selectdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,
						SelectdateActivity.class));
			}
		});

		// Initializing the view
		fillCalendarInfo(new CivilDate());

	}// end onCreate

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(MainActivity.this, HomeActivity.class));
	}

	@Override
	protected void onResume() {

		super.onResume();

		Intent intent = getIntent();
		year = intent.getStringExtra("year");
		month = intent.getStringExtra("month");
		if (month != null) {
			Toast.makeText(getApplicationContext(), year + " " + month, 1)
					.show();
			j = viewPager.getCurrentItem();
			Toast.makeText(getApplicationContext(), j + " " + month, 1).show();
			monthnum = j +Integer.parseInt(month);

			// if(month!=null){
			viewPager.setCurrentItem(monthnum);
		}

	}

	private void updateResetButtonState() {
		if (viewPager.getCurrentItem() == MONTHS_LIMIT / 2) {
			resetButton.setVisibility(View.GONE);
			fillCalendarInfo(new CivilDate());
		} else {
			resetButton.setVisibility(View.VISIBLE);
			clearInfo();
		}
	}

	private void clearInfo() {
		calendarInfo.setText("");

		prayTimeActivityHelper.clearInfo();
	}

	private void bringTodayYearMonth() {
		if (viewPager.getCurrentItem() != MONTHS_LIMIT / 2) {
			viewPager.setCurrentItem(MONTHS_LIMIT / 2);
			fillCalendarInfo(new CivilDate());
		}
	}

	public void setFocusedDay(PersianDate persianDate) {
		fillCalendarInfo(DateConverter.persianToCivil(persianDate));
	}

	private PagerAdapter createCalendarAdaptor() {
		return (PagerAdapter) new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return MONTHS_LIMIT;
			}

			@Override
			public Fragment getItem(int position) {
				MonthFragment fragment = new MonthFragment();
				Bundle args = new Bundle();
				args.putInt("offset", position - MONTHS_LIMIT / 2);
				fragment.setArguments(args);
				return fragment;
			}
		};
	}

	private boolean isToday(CivilDate civilDate) {
		CivilDate today = new CivilDate();
		return today.getYear() == civilDate.getYear()
				&& today.getMonth() == civilDate.getMonth()
				&& today.getDayOfMonth() == civilDate.getDayOfMonth();
	}

	private void fillCalendarInfo(CivilDate civilDate) {
		char[] digits = utils.preferredDigits(this);
		utils.prepareTextView(calendarInfo);
		StringBuilder sb = new StringBuilder();

		if (isToday(civilDate)) {
			sb.append("�����:\n");
			resetButton.setVisibility(View.GONE);
		} else {
			resetButton.setVisibility(View.VISIBLE);
		}
		sb.append(utils.infoForSpecificDay(civilDate, digits));
		calendarInfo.setText(utils.textShaper(sb.toString()));

		prayTimeActivityHelper.setDate(civilDate.getYear(),
				civilDate.getMonth() - 1, civilDate.getDayOfMonth());
		prayTimeActivityHelper.fillPrayTime();
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private boolean hasPermanentMenuKey() {
		return ViewConfiguration.get(this).hasPermanentMenuKey();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Restart activity
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
}
