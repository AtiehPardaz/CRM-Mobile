package com.Atieh.crm_mobile_calendar;

import android.R.color;
import android.content.Context;
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

import com.Atieh.crm_mobile.R;

public class MonthFragment extends Fragment {

	public static int monthjari;
	public static int yearjari;
	private final Utils utils = Utils.getInstance();
	public static TextView currentMonthTextView;

	int width, height;
	public static String mounthTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Preparing Calendar Month View
		Context context = getActivity();
		LinearLayout root = new LinearLayout(context);

		root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 100));
		root.setOrientation(LinearLayout.VERTICAL);

		Display display = (getActivity()).getWindowManager()
				.getDefaultDisplay();

		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		// ImageButton imgnextmounth=new ImageButton(context);
		// imgnextmounth.setLayoutParams(new LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		// root.addView(imgnextmounth);
		// imgnextmounth.setGravity(Gravity.CENTER);
		// currentMonthTextView
		currentMonthTextView = new TextView(context);

		currentMonthTextView.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		currentMonthTextView.setGravity(Gravity.CENTER);
		currentMonthTextView.setPadding(0, 0, 10, 2);
		currentMonthTextView.setTextSize(22);
		utils.prepareTextView(currentMonthTextView);

		root.addView(currentMonthTextView);
		// end

		// table
		TableLayout table = new TableLayout(context);
		table.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		table.setPadding(0, 0, 0, 0);

		// **********************************************************************************
		TextView[][] daysTextViews = new TextView[7][7];
		for (int i : new Range(0, 7)) {
			TableRow row = new TableRow(context);
			row.setGravity(Gravity.CENTER_HORIZONTAL);

			if (i == 0) {
				row.setBackgroundResource(R.drawable.calendar_firstrow);
				// row.setPadding(0, 0, 0, 2);// satr aval
				//
				// TableRow.LayoutParams relativeParamsDayOfWeek = new
				// TableRow.LayoutParams(width,40);
				// row.setLayoutParams(relativeParamsDayOfWeek);
			}
			for (int j : new Range(0, 7)) {

				RelativeLayout l1 = new RelativeLayout(context);

				// l1.setPadding(0, 0, 0, 0);

				l1.setGravity(Gravity.CENTER);
				RelativeLayout.LayoutParams relativeParams;

				if (i == 0) {
					relativeParams = new RelativeLayout.LayoutParams(width / 7,
							height / 9 - 20);
				} else {
					relativeParams = new RelativeLayout.LayoutParams(width / 7,
							height / 9);

				}
				relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
				relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				l1.setBackgroundResource(R.drawable.table_back);

				TextView tv = new TextView(context);
				// tv.setPadding(0, 5, 0, 5);

				utils.prepareTextView(tv);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);

				// tv.setLayoutParams(new
				// RelativeLayout.LayoutParams(width/7-10,40));

				tv.setTextSize(15);
				if (i == 0) {
					tv.setBackgroundResource(R.color.first_row_background_color);
					tv.setTextColor(getResources().getColor(
							R.color.first_row_text_color));
					tv.setTextSize(12);
					tv.setGravity(Gravity.CENTER);
					// tv.setId(i*10+j );

				}

				daysTextViews[i][j] = tv;
				l1.addView(tv, relativeParams);
				row.addView(l1);
			}

			table.addView(row);
		}

		table.setShrinkAllColumns(true);
		table.setStretchAllColumns(true);
		root.addView(table);
		// end

		// Calendar Logic
		int offset = getArguments().getInt("offset");
		PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());
		PersianDate persianDate1 = DateConverter
				.civilToPersian(new CivilDate());

		monthjari = persianDate.getMonth();
		yearjari = persianDate.getYear();
		int month = persianDate.getMonth() - offset;
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

		int flag = MainActivity.flag;
		int mounth2 = month;
		int year2 = year;

		if (flag == 1 && month == 12) {

			mounth2 = 1;

		}

		if (flag == 1 && month != 12) {

			mounth2 = month + 1;

		}
		
		if (flag == 3 && month != 1) {

			mounth2 = month - 1;
			

		}
		
		if (flag == 3 && month == 1) {

			mounth2 = 12;
			year2 = year - 1;

		}
		
		if (flag == 2 && month != 12) {

			mounth2 = month + 1;
			

		}
		
		if (flag == 2 && month == 12) {

			mounth2 = 1;
			year2 = year + 1;

		}
		
		

//		else if (month == 12) {
//
//			mounth2 = month;
//
//		} else {
//			mounth2 = month - 1;
//		}

		persianDate1.setMonth(mounth2);
		persianDate1.setYear(year2);
		persianDate1.setDayOfMonth(1);
		persianDate1.setDari(utils.isDariVersion(context));

		char[] digits = utils.preferredDigits(getActivity());

		int weekOfMonth = 1;
		int dayOfWeek = DateConverter.persianToCivil(persianDate)
				.getDayOfWeek() % 7;

		currentMonthTextView.setText(utils.getMonthYearTitle(persianDate,
				digits));
		currentMonthTextView.setVisibility(View.GONE);

		MainActivity.txt_mounth_title.setText(utils.getMonthYearTitle(
				persianDate1, digits));

		// Toast.makeText(getActivity(), currentMonthTextView.getText(),
		// 1).show();
		// MainActivity.title_month.setText(currentMonthTextView.getText());

		for (int i : new Range(0, 7)) {
			TextView textView = daysTextViews[0][6 - i];
			textView.setText(utils.firstCharOfDaysOfWeekName[i]);
			// RelativeLayout rowNamesOfWeek = (RelativeLayout)
			// textView.getParent();
			// RelativeLayout.LayoutParams relativeParamsDayOfWeek = new
			// RelativeLayout.LayoutParams(width/7,40);
			// rowNamesOfWeek.setLayoutParams(relativeParamsDayOfWeek);

		}
		try {
			PersianDate today = DateConverter.civilToPersian(new CivilDate());
			for (int i : new Range(1, 31)) {
				persianDate.setDayOfMonth(i);

				TextView textView = daysTextViews[weekOfMonth][6 - dayOfWeek];
				textView.setText(utils.formatNumber(i, digits));
				textView.setBackgroundResource(R.drawable.days);

				// ****************************************************************************************************
				String holidayTitle = utils.getHolidayTitle(persianDate);
				String tasksTitle = utils.getTasksdayTitle(persianDate);
				String activitiesTitle = utils
						.getActivitiessdayTitle(persianDate);

				int tasknumbers;
				int activitynumbers;
				String tasksString = "";
				String activitiesString = "";

				if (tasksTitle != null || activitiesTitle != null) {

					if (tasksTitle != null) {
						tasknumbers = Integer.parseInt(tasksTitle
								.split("mojtaba")[0]);
					} else {
						tasknumbers = 0;
					}

					if (activitiesTitle != null) {
						activitynumbers = Integer.parseInt(activitiesTitle
								.split("mojtaba")[0]);
					} else {
						activitynumbers = 0;
					}

					for (int s = 0; s < activitynumbers; s++) {
						activitiesString = activitiesString + "▪";
					}

					for (int s1 = 0; s1 < tasknumbers; s1++) {
						tasksString = tasksString + "▪";
					}

					RelativeLayout row1 = (RelativeLayout) textView.getParent();

					RelativeLayout.LayoutParams relativeParamTop = new RelativeLayout.LayoutParams(
							width / 7, 20);
					relativeParamTop.addRule(RelativeLayout.CENTER_HORIZONTAL);
					relativeParamTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
					relativeParamTop.setMargins(0, height / 9 / 3, 0, 0);

					RelativeLayout.LayoutParams relativeParamDown = new RelativeLayout.LayoutParams(
							width / 7, 20);
					relativeParamDown.addRule(RelativeLayout.CENTER_HORIZONTAL);
					relativeParamDown.addRule(RelativeLayout.ALIGN_PARENT_TOP);
					relativeParamDown.setMargins(0, height / 9 / 2, 0, 0);

					TextView txtTask = new TextView(context);
					txtTask.setLayoutParams(new RelativeLayout.LayoutParams(
							width / 7 - 10, LayoutParams.WRAP_CONTENT));

					txtTask.setText(TextUtils.htmlEncode(tasksString));

					txtTask.setGravity(Gravity.CENTER_HORIZONTAL);
					txtTask.setTextSize(TypedValue.COMPLEX_UNIT_PX, 20);
					// txtTask.setLineSpacing(-42f, 2f);
					txtTask.setTextColor(getResources().getColor(
							color.holo_blue_bright));
					txtTask.setSingleLine(false);
					txtTask.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);

					TextView txtTask1 = new TextView(context);
					txtTask1.setLayoutParams(new RelativeLayout.LayoutParams(
							width / 7 - 10, LayoutParams.WRAP_CONTENT));

					txtTask1.setText(TextUtils.htmlEncode(activitiesString));

					txtTask1.setGravity(Gravity.CENTER_HORIZONTAL);
					txtTask1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 20);
					// txtTask1.setLineSpacing(-42f, 2f);
					txtTask1.setTextColor(getResources().getColor(
							color.holo_blue_dark));
					txtTask1.setSingleLine(false);
					txtTask1.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);

					row1.addView(txtTask, relativeParamTop);
					row1.addView(txtTask1, relativeParamDown);

				}

				if (holidayTitle != null) {
					textView.setBackgroundResource(R.drawable.holidays);

				}
				if (holidayTitle != null || dayOfWeek == 6) {
					textView.setTextColor(getResources().getColor(
							R.color.holidays_text_color));
				}

				// *********************************************************************

				ClickDayListener listener = new ClickDayListener(holidayTitle,
						persianDate.clone(), (MainActivity) getActivity());
				textView.setOnClickListener(listener);
				textView.setOnLongClickListener(listener);

				if (persianDate.equals(today)) {
					textView.setBackgroundResource(R.drawable.today_background);
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
		return root;
	}

}
