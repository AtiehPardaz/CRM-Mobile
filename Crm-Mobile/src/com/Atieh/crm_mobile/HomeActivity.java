package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

	Button btnyear;
	Button btnmonth;
	Button btnday;
	Button btndownload;
	Button btnupload;
	Button btnactivity;
	Button btntask;
	Button btncustomer;
	Button btnorder;

	public void initview() {
		btnyear = (Button) findViewById(R.id.btn_yearview);
		btnmonth = (Button) findViewById(R.id.btn_monthview);
		btnday = (Button) findViewById(R.id.btn_dayview);
		btndownload = (Button) findViewById(R.id.btn_download);
		btnupload = (Button) findViewById(R.id.btn_upload);
		btnactivity = (Button) findViewById(R.id.btn_newactivity);
		btntask = (Button) findViewById(R.id.btn_newtask);
		btncustomer = (Button) findViewById(R.id.btn_customer);
		btnorder = (Button) findViewById(R.id.btn_order);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initview();

		btnyear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		btnmonth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		btnday.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});

		btndownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				checknetwork();
			}
		});
		btnupload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				checknetwork();
			}
		});
		btnactivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		btntask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		btncustomer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		btnorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
	}// end of onCreate

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	public void checknetwork() {
		if (isOnline() == true) {
			Toast.makeText(getApplicationContext(), "network is on", 1).show();
		} else {
			Toast.makeText(getApplicationContext(), "network is off", 1).show();
		}
	}
}
