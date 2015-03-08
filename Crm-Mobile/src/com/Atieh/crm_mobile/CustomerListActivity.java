package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import com.Atieh.crm_mobile.ProductServisesActivity.asyncTask;

import dataBase.database;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	private Cursor list;
	private database db;
	public ListView list_costomer;
	String[] array;

	// List<String> testlist = new ArrayList<String>(); //for listsade active it

	List<String> customerlist = new ArrayList<String>();

	public void initview() {
		btnhome = (ImageButton) findViewById(R.id.btn_home_customerlist);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthviewlist_customerlist);
		add = (ImageButton) findViewById(R.id.btn_add_customerlist);
		list_costomer = (ListView) findViewById(R.id.lv_tasks);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading_customerlist);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_list);

		initview();

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
			Toast.makeText(getApplicationContext(), "مجددا تلاش نمایید", 1)
					.show();
		}

		// db.close();
		// custom list

		// ==========liste sade
		// while (c.moveToNext()) {
		// testlist.add(c.getString(1));
		// }
		//
		// final ArrayAdapter<String> adapter = new ArrayAdapter(this,
		// android.R.layout.simple_list_item_1, testlist);
		//
		// list_costomer.setAdapter(adapter);
		// ======liste sade

		list_costomer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				TextView tv = (TextView) v.findViewById(R.id.tv_id_customer);
				String text = tv.getText().toString();

				Toast.makeText(getApplicationContext(),
						"selected Item id is === " + text, Toast.LENGTH_LONG)
						.show();

			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(CustomerListActivity.this,
						CustemerSendTestActivity.class));
				Toast.makeText(
						getApplicationContext(),
						"صفحۀایجادمشتری جدید موجود نیست \n" + "درحال آماده شدن",
						Toast.LENGTH_LONG).show();

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
			list_costomer.setAdapter(new customlistAadapter(
					CustomerListActivity.this, array));

		}

	}

}