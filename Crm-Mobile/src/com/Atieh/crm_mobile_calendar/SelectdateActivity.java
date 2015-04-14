package com.Atieh.crm_mobile_calendar;

import com.Atieh.crm_mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectdateActivity extends Activity {
	TextView month;
	TextView monthnum;
	TextView year;
	ImageButton nextyear;
	ImageButton nextmonth;
	ImageButton backyear;
	ImageButton backmonth;
	Button gotodate;
	public static int gotomonth;
	public static int gotoyear;
	TextView title_yearhide;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectdate);

//		final String[] MonthName = {  " ", "بهمن", "دی", "آذر",
//				"آبان", "مهر", "شهریور", "مرداد","تیر" , "خرداد","اردیبهشت", "فروردین","اسفند" };
		final String[] MonthName = { "", "فروردین", "اردیبهشت", "خرداد", "تیر",
				"مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند" };
		
		gotodate = (Button) findViewById(R.id.btn_gotodate);
		year = (TextView) findViewById(R.id.tv_year);
		month = (TextView) findViewById(R.id.tv_month);
		monthnum = (TextView) findViewById(R.id.tv_numberofmonth);
		nextyear = (ImageButton) findViewById(R.id.imgbtn_nextyear_picker);
		nextmonth = (ImageButton) findViewById(R.id.imgbtn_nextmonth_picker);
		backyear = (ImageButton) findViewById(R.id.imgbtn_backyear_picker);
		backmonth = (ImageButton) findViewById(R.id.imgbtn_backmonth_picker);
		title_yearhide=(TextView) findViewById(R.id.tv_yeartitle);
		
		
		title_yearhide.setVisibility(View.GONE);
		year.setVisibility(View.VISIBLE);
		nextyear.setOnClickListener(new OnClickListener() {

			

			@Override
			public void onClick(View arg0) {
				int i = Integer.parseInt(year.getText().toString());
				i++;
//				Toast.makeText(getApplicationContext(), i + "", 1).show();

				year.setText(i + "");

			}
		});
		backyear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = Integer.parseInt(year.getText().toString());
				i--;
//				Toast.makeText(getApplicationContext(), i + "", 1).show();

				year.setText(i + "");

			}

		});
		nextmonth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = Integer.parseInt(monthnum.getText().toString());

				if (i == 12) {
					i = 1;
					monthnum.setText(i + "");
					month.setText(MonthName[i] + "");
				} else {
					i++;
					monthnum.setText(i + "");
					month.setText(MonthName[i] + "");
				}

			}
		});

		backmonth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = Integer.parseInt(monthnum.getText().toString());

				if (i == 1) {
					i = 12;
					monthnum.setText(i + "");
					month.setText(MonthName[i] + "");
				} else {
					i--;
					monthnum.setText(i + "");
					month.setText(MonthName[i] + "");
				}

			}
		});
		gotodate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = Integer.parseInt(monthnum.getText().toString());
				
				Intent yearmonth = new Intent();
				yearmonth.setClass(SelectdateActivity.this, MainActivity.class);
				yearmonth.putExtra("year", year.getText());
				yearmonth.putExtra("month", monthnum.getText());
				startActivity(yearmonth);
				
			}
		});

	}

}
