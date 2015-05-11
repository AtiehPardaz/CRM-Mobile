package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;
import dataBase.database;

import adapters.customlistAadapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerListActivity extends Activity {

	ImageButton add;
	ImageButton btnhome;
	ImageButton btnmonthview;
	LinearLayout ll_loading;
	public ListView list_costomer;
	String[] array;
	ArrayAdapter<String> dataAdapter;
	String Enterstat;
	public static String text;
	public static String adress;
	public static String haghighi;
	public static String tel;
	public static String title;
	public static String RelCustomerID = "";
	public static String RelCustomerName = "";
	public static String RelID = "";
	public static String RelName = "";
	CheckBox chk;

	List<String> customerlist = new ArrayList<String>();

	public void initview() {
		btnhome = (ImageButton) findViewById(R.id.btn_home_customerlist);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthviewlist_customerlist);
		add = (ImageButton) findViewById(R.id.btn_add_customerlist);
		list_costomer = (ListView) findViewById(R.id.lv_customers);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading_customerlist);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_list);

		initview();

		if (getIntent() != null) {

			if ((getIntent().getExtras().getString("RelCustomerID")) != null) {

				RelCustomerID = getIntent().getExtras().getString(
						"RelCustomerID");
				RelCustomerName = getIntent().getExtras().getString(
						"RelCustomerName");
				RelID = getIntent().getExtras().getString("RelID");
				RelName = getIntent().getExtras().getString("RelName");

				Intent returnIntent = new Intent();
				returnIntent.putExtra("result2", "mojtaba");
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		}

		Enterstat = getIntent().getExtras().getString(
				HomeActivity.EnterCustomersListStat);
		if (Enterstat != null) {
			if (!Enterstat.equals("mostaghim")) {
				add.setVisibility(View.GONE);
				btnhome.setVisibility(View.GONE);
				btnmonthview.setVisibility(View.GONE);
			}
		}

		database db;
		db = new database(this);
		db.database();
		db.open();

		final Cursor c = db.GetCustomers();

		// custom list
		array = new String[c.getCount()];
		int i = 0;
		if (c.moveToFirst()) {

			do {

				try {

					array[i] = c.getString(0);
					i++;

				} catch (Exception e) {

					e.printStackTrace();
				}

			} while (c.moveToNext());
		}

		try {
			asyncTask as = new asyncTask();
			as.execute();

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "مجددا تلاش نمایید", Toast.LENGTH_LONG)
					.show();
		}

		db.close();

		list_costomer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				TextView tv = (TextView) v.findViewById(R.id.tv_id_customer);
				text = tv.getText().toString();
				tv.setVisibility(View.GONE);
				TextView tvtitle = (TextView) v.findViewById(R.id.tv_customer);
				title = tvtitle.getText().toString();

				TextView tvadress = (TextView) v
						.findViewById(R.id.tv_customeradress);
				adress = tvadress.getText().toString();

				TextView tvhaghighi = (TextView) v
						.findViewById(R.id.tv_customerhaghighi);
				haghighi = tvhaghighi.getText().toString();

				TextView tvtel = (TextView) v.findViewById(R.id.tv_customertel);
				tel = tvtel.getText().toString();

				// Toast.makeText(getApplicationContext(),
				// "selected Item id is === " + text+" "+adress+haghighi+tel,
				// Toast.LENGTH_LONG)
				// .show();
				startActivity(new Intent(CustomerListActivity.this,
						CustomerDetailsActivity.class));

			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(CustomerListActivity.this,
						NewCustomerActivity.class));

			}
		});

		btnhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(CustomerListActivity.this,
						HomeActivity.class));

			}
		});
		btnmonthview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(CustomerListActivity.this,
						com.Atieh.crm_mobile_calendar.MainActivity.class));

			}
		});
	}// End Oncreate

	public class asyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {

			// TODO Auto-generated method stub
			super.onPreExecute();
			ll_loading.setVisibility(View.VISIBLE);
			list_costomer.setVisibility(View.GONE);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ll_loading.setVisibility(View.GONE);
			list_costomer.setVisibility(View.VISIBLE);

			dataAdapter = new customlistAadapter(CustomerListActivity.this,
					array, Enterstat);

			list_costomer.setAdapter(dataAdapter);

		}

	}

}
