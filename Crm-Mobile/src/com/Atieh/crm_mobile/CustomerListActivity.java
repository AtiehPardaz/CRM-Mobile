package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import dataBase.database;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerListActivity extends Activity {

	ImageButton add;

	private Cursor list;
	private database db;
	public ListView list_costomer;

	// ArrayList<ArrayList<String>> customerlist = new
	// ArrayList<ArrayList<String>>();
	List<String> productslist = new ArrayList<String>();
	List<List<String>> ll = new ArrayList<List<String>>();

	public void initview() {
		add = (ImageButton) findViewById(R.id.btn_add_customerlist);
		list_costomer = (ListView) findViewById(R.id.lv_customers);
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
		int i = 0;

		// while (c.moveToNext()) {
		if (c.moveToFirst()) {

			do {

				productslist.clear();

				productslist.add(c.getString(0));
				productslist.add(c.getString(1));

				ll.add(i, productslist);
//				i++;
			} while (c.moveToNext());
		}

		Toast.makeText(getApplicationContext(), ll.get(2).get(1), 1).show();

		// final ArrayAdapter<String> adapter = new ArrayAdapter(this,
		// android.R.layout.simple_list_item_1, productslist);

		// list_costomer.setAdapter(adapter);

		// String[] array = new String[c.getCount()];

		// int i = 0;
		// if (c.moveToFirst()) {
		//
		// do {
		//
		// try {
		//
		// customerlist.get(i).add(c.getString(0));
		// customerlist.get(i).add(c.getString(1));
		// customerlist.get(i).add(c.getString(2));
		// customerlist.get(i).add(c.getString(3));
		// customerlist.get(i).add(c.getString(4));
		// customerlist.get(i).add(c.getString(5));
		// customerlist.get(i).add(c.getString(6));
		//
		// // Toast.makeText(getApplicationContext(),
		// // "" + customerlist.get(0).get(6), 1).show()
		// i++;
		//
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		// } while (c.moveToNext());
		// }

		list_costomer.setAdapter(new customlistAadapter(this, ll));

		db.close();
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(CustomerListActivity.this,
						CustemerSendTestActivity.class));

			}
		});

	}// End Oncreate

}
