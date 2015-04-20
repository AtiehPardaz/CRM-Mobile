package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivitiesActivity extends Activity {

	EditText et_title;
	EditText et_sharh;

	TextView date;
	Button seldate;
	Button selprodecservices;
	ImageView save;
	ImageView savenew;
	ImageView close;

	Spinner spnr_customer;
	Spinner spnr_rabet;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;
	Spinner spnr_vazife;
	Spinner spnr_mahsolvakhadamat;
	Spinner spnr_vaziat;
	Spinner spnr_ghararbadi;

	public void initview() {
		et_title = (EditText) findViewById(R.id.et_newactivitytitle);
		et_sharh = (EditText) findViewById(R.id.et_sharh_newactivity);
		spnr_customer = (Spinner) findViewById(R.id.spnr_newtask_customer);
		spnr_rabet = (Spinner) findViewById(R.id.spnr_newactivity_shakhsrabet);
		spnr_azsaat = (Spinner) findViewById(R.id.spnr_newtask_azsaat);
		spnr_tasaat = (Spinner) findViewById(R.id.spnr_newtask_tasaat);
		spnr_vazife = (Spinner) findViewById(R.id.spnr_newactivity_task);
		spnr_mahsolvakhadamat = (Spinner) findViewById(R.id.spnr_newactivity_mahsolkhadamat);
		spnr_vaziat = (Spinner) findViewById(R.id.spnr_newactivity_vaziat);
		spnr_ghararbadi = (Spinner) findViewById(R.id.spnr_newactivity_ghararbadi);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask);
		seldate = (Button) findViewById(R.id.btn__newactivity_seldate);
		selprodecservices = (Button) findViewById(R.id.btn_newactivity_mahsolvakhadamat);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities_new);

		initview();

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		selprodecservices.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(NewActivitiesActivity.this,
						SelectProducteServicesActivity.class));

			}
		});
		seldate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(NewActivitiesActivity.this,
						DatepickerActivity.class));

			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (DatepickerActivity.myDay != null) {
			Toast.makeText(
					getApplicationContext(),
					DatepickerActivity.myDay + "/" + DatepickerActivity.myMonth
							+ "/" + DatepickerActivity.myYear, 1).show();

		}
	}

}
