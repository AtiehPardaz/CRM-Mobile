package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import singleTones.TempActivityID;
import com.Atieh.crm_mobile.R.color;
import dataBase.database;
import adapters.CmListFromAtcivityToProduct;
import adapters.CmListFromAtcivityToServices;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectProducteServicesActivity extends Activity {

	LinearLayout ll_loading;
	public ListView list_producte;
	public ListView list_services;
	Button khadamat;
	Button finish;;
	Button mahsolat;
	String[] arrayid;
	String[] arraytitle;
	String[] arrayid2;
	String[] arraytitle2;
	// ArrayAdapter<String> dataAdapter;
	CmListFromAtcivityToProduct as;
	CmListFromAtcivityToServices as2;
	public static String ids;
	public static String title;

	List<String> productelist = new ArrayList<String>();
	List<String> serviceslist = new ArrayList<String>();

	public void initview() {
		list_producte = (ListView) findViewById(R.id.lv_sel_product);
		list_services = (ListView) findViewById(R.id.lv_sel_servisec);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading_sel_productservices);
		khadamat = (Button) findViewById(R.id.btn_khadamat_sel_productservise);
		mahsolat = (Button) findViewById(R.id.btn_mahsolat_sel_productservise);
		finish = (Button) findViewById(R.id.btn_close_selmahsolvakhadamat);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_product_services);

		initview();

		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		
		
		database db;
		db = new database(this);
		db.database();
		db.open();

		Toast.makeText(SelectProducteServicesActivity.this,
				TempActivityID.getInstance().getTempActivityID(), Toast.LENGTH_LONG).show();

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

		// ==================
		final Cursor c2 = db.GetServices();

		// custom list
		arrayid2 = new String[c2.getCount()];
		arraytitle2 = new String[c2.getCount()];
		int i2 = 0;
		if (c2.moveToFirst()) {

			do {

				try {

					arrayid2[i2] = c2.getString(1); // 1 id
					arraytitle2[i2] = c2.getString(2); // 2 title
					i2++;

				} catch (Exception e) {

					e.printStackTrace();
				}

			} while (c2.moveToNext());
		}

		try {
			asyncTask as = new asyncTask();
			as.execute();

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "مجددا تلاش نمایید", Toast.LENGTH_LONG)
					.show();
		}

		
		khadamat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mahsolat.setBackgroundResource(color.bg_action_gray);
				khadamat.setBackgroundResource(color.bg_action_green);
				list_producte.setVisibility(View.GONE);
				list_services.setVisibility(View.VISIBLE);
				mahsolat.setTextColor(Color.BLACK);
				khadamat.setTextColor(Color.WHITE);
			}
		});

		mahsolat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mahsolat.setBackgroundResource(color.bg_action_green);
				khadamat.setBackgroundResource(color.bg_action_gray);
				list_services.setVisibility(View.GONE);
				list_producte.setVisibility(View.VISIBLE);

				mahsolat.setTextColor(Color.WHITE);
				khadamat.setTextColor(Color.BLACK);

			}
		});

		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result","mojtaba");
				setResult(RESULT_OK,returnIntent);
				finish();

			}
		});

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
				// Toast.makeText(getApplicationContext(),
				// as.getselected().get(0), 1).show();
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
			as = new CmListFromAtcivityToProduct(SelectProducteServicesActivity.this,
					arrayid, arraytitle, getApplicationContext());
			list_producte.setAdapter(as);

			as2 = new CmListFromAtcivityToServices(SelectProducteServicesActivity.this,
					arrayid2, arraytitle2, getApplicationContext());
			list_services.setAdapter(as2);
		}

	}

}
