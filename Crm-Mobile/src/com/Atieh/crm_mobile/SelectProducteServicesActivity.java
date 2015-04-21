package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import singleTones.TempActivityID;

import com.Atieh.crm_mobile.ProductServisesActivity.asyncTask;
import com.Atieh.crm_mobile_calendar.ArabicShaping;

import dataBase.database;

import adapters.CmListSelProduct;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectProducteServicesActivity extends Activity {

	LinearLayout ll_loading;
	private Cursor list;
	private database db;
	public ListView list_producte;
	public ListView list_services;
	String[] arrayid;
	String[] arraytitle;
	// ArrayAdapter<String> dataAdapter;
	CmListSelProduct as;
	public static String ids;
	public static String title;

	List<String> productelist = new ArrayList<String>();
	List<String> serviceslist = new ArrayList<String>();

	public void initview() {
		list_producte = (ListView) findViewById(R.id.lv_sel_product);
		list_services = (ListView) findViewById(R.id.lv_sel_servisec);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading_sel_productservices);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_product_services);

		initview();

		database db;
		db = new database(this);
		db.database();
		db.open();
		
		
		
		Toast.makeText(SelectProducteServicesActivity.this,TempActivityID.getInstance().getTempActivityID(),1).show();
		
		final Cursor c = db.GetPrudocts();

		// custom list
		arrayid = new String[c.getCount()];
		arraytitle = new String[c.getCount()];
		int i = 0;
		if (c.moveToFirst()) {

			do {

				try {

					arrayid[i] = c.getString(1); // 1 id
					arraytitle[i] = c.getString(2); // 2 title
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

		db.close();
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

		list_producte.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
//
//				Toast.makeText(getApplicationContext(),
//						as.getselected().get(0), 1).show();
				TextView tv = (TextView) v
						.findViewById(R.id.tv_id_selproductservices);
				ids = tv.getText().toString();

				TextView tvtitle = (TextView) v
						.findViewById(R.id.tv_title_selproductservices);
				title = tvtitle.getText().toString();

				// startActivity(new Intent(SelectProducteServicesActivity.this,
				// CustomerDetailsActivity.class));

			}
		});

	}// End Oncreate

	public class asyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {

			// TODO Auto-generated method stub
			super.onPreExecute();
			ll_loading.setVisibility(View.VISIBLE);
			// list_producte.setVisibility(View.GONE);
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
			list_producte.setVisibility(View.VISIBLE);

			// dataAdapter = new CmListSelProducteServices(
			// SelectProducteServicesActivity.this, arrayid,arraytitle);
			as = new CmListSelProduct(
					SelectProducteServicesActivity.this, arrayid, arraytitle,getApplicationContext());
			list_producte.setAdapter(as);

		}

	}

}
