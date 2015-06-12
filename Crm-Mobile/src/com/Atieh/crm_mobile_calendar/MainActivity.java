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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.Atieh.crm_mobile.HomeActivity;
import com.Atieh.crm_mobile.HomeWatcher;
import com.Atieh.crm_mobile.NewActivitiesActivity;
import com.Atieh.crm_mobile.NewTaskActivity;
import com.Atieh.crm_mobile.OnHomePressedListener;
import com.Atieh.crm_mobile.R;

public class MainActivity extends FragmentActivity {
	// I know, it is ugly, but user will not notify this and this will not have
	// a memory problem
	private static final int MONTHS_LIMIT = 1200;
	public Utils utils = Utils.getInstance();
	private ViewPager viewPager;
	TextView title;
	private TextView calendarInfo;
	ImageButton selectdate,btn_monthview_plus;
	public static TextView title_month,txt_mounth_title;
	public static int flag = 1;
	Button btn_mounthview_new_task,btn_mounthview_new_activity;

	private Button resetButton;
	private PrayTimeActivityHelper prayTimeActivityHelper;
	ImageButton next;
	ImageButton back;
	String newyear;
	String newmonth;
	public PersianDate persiandatee;
	int jari = 1;
	int monthnum = 1;

	// public static StringBuilder sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		utils.setTheme(this);
		super.onCreate(savedInstanceState);
		// for notification
		// startService(new Intent(this, ApplicationService.class));
		// persiandatee=new PersianDate();
		// Toast.makeText(getApplicationContext(), persiandatee.getMonth()+"",
		// 1)
		// .show();
		
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		
		
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
		
		// title=(TextView) findViewById(R.id.tv_title_monthview);
		calendarInfo = (TextView) findViewById(R.id.calendar_info);
		title_month = (TextView) findViewById(R.id.tv_title_monthview);
		next = (ImageButton) findViewById(R.id.imgbtn_next_month_year);
		back = (ImageButton) findViewById(R.id.imgbtn_back_month);
		selectdate = (ImageButton) findViewById(R.id.btn_monthview_calander);
		txt_mounth_title = (TextView) findViewById(R.id.txt_mounth_title);
		btn_monthview_plus = (ImageButton) findViewById(R.id.btn_monthview_plus);
		btn_mounthview_new_task = (Button) findViewById(R.id.btn_mounthview_new_task);
		btn_mounthview_new_activity = (Button) findViewById(R.id.btn_mounthview_new_activity);
		// Pray Time
		prayTimeActivityHelper = new PrayTimeActivityHelper(this);
		prayTimeActivityHelper.fillPrayTime();

		
		btn_mounthview_new_task.setVisibility(View.GONE);
		btn_mounthview_new_activity.setVisibility(View.GONE);
		
		
		
		// Load Holidays
		utils.loadHolidays(getResources().openRawResource(R.raw.holidays));
		utils.LoadTasksdays(this.getBaseContext());
		utils.LoadActivitiesdays(this.getBaseContext());
		
		
		
		
		
		
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
				
//				Toast.makeText(getApplicationContext(), MonthFragment.currentMonthTextView
//						.getText(), 1).show();
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
		});
		
		
		
		txt_mounth_title.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SelectdateActivity.flag = true;
				startActivity(new Intent(MainActivity.this,
						SelectdateActivity.class));
				
			}
		});
		
		btn_monthview_plus.setOnClickListener(new View.OnClickListener() {
			
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
				intent.setClass(MainActivity.this,NewTaskActivity.class);
				startActivity(intent);
				
			}
		});
		
		btn_mounthview_new_activity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,NewActivitiesActivity.class);
				startActivity(intent);
				
			}
		});
		
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				flag = 2;

				int i = viewPager.getCurrentItem();
				i++;
				viewPager.setCurrentItem(i);
				//txt_mounth_title.setText(MonthFragment.currentMonthTextView.getText().toString());

	
			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				flag =3;

				int i = viewPager.getCurrentItem();
				i--;
				viewPager.setCurrentItem(i);
				//txt_mounth_title.setText(MonthFragment.currentMonthTextView.getText().toString());

			}
		});
		selectdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				startActivity(new Intent(MainActivity.this,
//						SelectdateActivity.class));
			}
		});

		// Initializing the view
		fillCalendarInfo(new CivilDate());
		
		txt_mounth_title.setText(MonthFragment.mounthTitle);

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
		btn_mounthview_new_task.setVisibility(View.GONE);
		btn_mounthview_new_activity.setVisibility(View.GONE);
		
		Intent intent = getIntent();
		newmonth = intent.getStringExtra("month");

		if (newmonth != null) {
			int oldyear = MonthFragment.yearjari - 1300;
			int oldmonth = MonthFragment.monthjari;

			newyear = intent.getStringExtra("year");

			Toast.makeText(getApplicationContext(), newyear + " " + newmonth, 1)
					.show();
			int hal = (oldyear - Integer.parseInt(newyear)) * 12
					+ (oldmonth - Integer.parseInt(newmonth));
			jari = viewPager.getCurrentItem();
			// Toast.makeText(getApplicationContext(),
			// PersianDate.get" va jari "+jari, 1).show();

			int newdate = jari + hal;

			// if(month!=null){
			viewPager.setCurrentItem(newdate);
			//txt_mounth_title.setText(MonthFragment.currentMonthTextView.getText().toString());
		}

	}

	private void updateResetButtonState() {
		if (viewPager.getCurrentItem() == MONTHS_LIMIT / 2) {
			resetButton.setVisibility(View.GONE);
			fillCalendarInfo(new CivilDate());
		} else {
			resetButton.setVisibility(View.GONE);/////////////////////////////////////
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
			sb.append("ÇãÑæÒ:\n");
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
