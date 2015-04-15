package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewTaskActivity extends Activity {

	EditText et_title;
	EditText et_sharh;
	EditText et_ghabl;

	TextView date;
	
	ImageButton datepicker;
	ImageView save;
	ImageView savenew;
	ImageView close;

	Spinner spnr_customer;
	Spinner spnr_rabet;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;

	Spinner spnr_mahsolvakhadamat;

	RadioButton rdbtn_hamanlahze;
	RadioButton rdbtn_ghabl;

	public void initview() {
		et_title = (EditText) findViewById(R.id.et_newtasktitle);
		et_sharh = (EditText) findViewById(R.id.et_sharh_newtask);
		et_ghabl = (EditText) findViewById(R.id.et_daghigheghabl_newtask);
		spnr_customer = (Spinner) findViewById(R.id.spnr_newtask_customer);
		spnr_rabet = (Spinner) findViewById(R.id.spnr_newtask_shakhsrabet);
		spnr_azsaat = (Spinner) findViewById(R.id.spnr_newtask_azsaat);
		spnr_tasaat = (Spinner) findViewById(R.id.spnr_newtask_tasaat);
		spnr_mahsolvakhadamat = (Spinner) findViewById(R.id.spnr_newtask_mahsolkhadamat);

		datepicker=(ImageButton) findViewById(R.id.img_datenewtask);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask);


		rdbtn_ghabl = (RadioButton) findViewById(R.id.rdbtn_newtask_ghabl);
		rdbtn_hamanlahze = (RadioButton) findViewById(R.id.rdbtn_remember_newtask);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities_new);

		initview();

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				startActivity(new Intent(NewTaskActivity.this,
						DatepickerActivity.class));
			}
		});

		
//		datepicker.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				
//				
//			}
//		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		if (DatepickerActivity.myDay != null) {
//			Toast.makeText(
//					getApplicationContext(),
//					DatepickerActivity.myDay + "/" + DatepickerActivity.myMonth
//							+ "/" + DatepickerActivity.myYear, 1).show();
//
//		}
	}

}
