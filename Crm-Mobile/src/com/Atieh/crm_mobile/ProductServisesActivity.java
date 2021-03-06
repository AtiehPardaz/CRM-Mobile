package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import dataBase.database;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ProductServisesActivity extends Activity {
	ImageButton btnsearch;
	ImageButton btnhome;
	ImageButton btnmonthview;
	ImageButton btnclearsearchtext;
	Button btnproduct;
	Button btnservisec;
	LinearLayout ll_loading;
	LinearLayout ll_hidesearch;
	EditText et_search;
	ListView listservicesproduct;
	int showproductorservicelist = 0; // 0 for product and 1 for services

	List<String> productslist = new ArrayList<String>();
	List<String> serviceslist = new ArrayList<String>();

	public void initview() {
		btnsearch = (ImageButton) findViewById(R.id.btn_search_productservices);
		btnhome = (ImageButton) findViewById(R.id.btn_home_productservices);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthview_productservices);
		btnclearsearchtext = (ImageButton) findViewById(R.id.imgbtn_clearsearch_productservises);
		btnproduct = (Button) findViewById(R.id.btn_product_productservices);
		btnservisec = (Button) findViewById(R.id.btn_servisec_productservices);
		et_search = (EditText) findViewById(R.id.et_search_productservices);
		ll_hidesearch = (LinearLayout) findViewById(R.id.ll_search);
		listservicesproduct = (ListView) findViewById(R.id.lv_product_servisec);
		ll_loading=(LinearLayout) findViewById(R.id.ll_loading_productservices);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_services);

		initview();
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
		
		ll_hidesearch.setVisibility(View.GONE);
		database db;
		db = new database(this);
		db.database();
		db.open();

		Cursor products = db.GetPrudoctsNames();
		Cursor services = db.GetServicesNames();

		while (products.moveToNext()) {
			productslist.add(products.getString(0));
		}

		while (services.moveToNext()) {
			serviceslist.add(services.getString(0));
		}

		asyncTask as = new asyncTask(); 
		as.execute();
		
		btnsearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ll_hidesearch.setVisibility(View.VISIBLE);

			}
		});
 
		btnclearsearchtext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ll_hidesearch.setVisibility(View.GONE);
				et_search.setText("");

			}
		});
		btnhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ProductServisesActivity.this,
						HomeActivity.class));

			}
		});
		btnmonthview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ProductServisesActivity.this,
						com.Atieh.crm_mobile_calendar.MainActivity.class));

			}
		});
		btnproduct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				btnproduct.setBackgroundColor(Color.parseColor("#3BB18E"));
				btnservisec.setBackgroundColor(Color.parseColor("#dddddd"));
				
				showproductorservicelist = 0;
				asyncTask as = new asyncTask();
				as.execute();

			}
		});
		btnservisec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				btnproduct.setBackgroundColor(Color.parseColor("#dddddd"));
				btnservisec.setBackgroundColor(Color.parseColor("#3BB18E"));
				// btnservisec.setTextColor(color.first_row_background_color);
				
				showproductorservicelist = 1;

				asyncTask as = new asyncTask();
				as.execute();

			}
		});

	}// end oncreate

	public void productorservices() {
		if (showproductorservicelist == 0) {
			final ArrayAdapter<String> adapter = new ArrayAdapter(this,
					android.R.layout.simple_list_item_1, productslist);
			listservicesproduct.setAdapter(adapter);
		} else if (showproductorservicelist == 1) {
			final ArrayAdapter<String> adapter = new ArrayAdapter(this,
					android.R.layout.simple_list_item_1, serviceslist);
			listservicesproduct.setAdapter(adapter);
		}
	}

	public class asyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			
			// TODO Auto-generated method stub
			super.onPreExecute();
			ll_loading.setVisibility(View.VISIBLE);
			listservicesproduct.setVisibility(View.GONE);
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
			listservicesproduct.setVisibility(View.VISIBLE);
			productorservices();
		}

	}

}
