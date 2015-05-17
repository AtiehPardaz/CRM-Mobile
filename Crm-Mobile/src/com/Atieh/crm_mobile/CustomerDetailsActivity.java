package com.Atieh.crm_mobile;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import dataBase.database;

public class CustomerDetailsActivity extends Activity {

	TextView title;
	TextView adres;
	TextView haghighi;
	TextView tel;
	TextView semat;
	TextView ashkhas;
	ImageView close;
	ImageView delete;
	ImageView edit;

	public void initview() {
		title = (TextView) findViewById(R.id.tv_customerTitle);
		adres = (TextView) findViewById(R.id.tv_customeradress);
		haghighi = (TextView) findViewById(R.id.tv_haghighicustomer);
		tel = (TextView) findViewById(R.id.tv_telcustomer);
		ashkhas = (TextView) findViewById(R.id.tv_ashkhasmortabet);
		semat = (TextView) findViewById(R.id.tv_customersemat);
		close = (ImageView) findViewById(R.id.img_customerdetails_close);

		delete = (ImageView) findViewById(R.id.img_customerdetails_delete);

		edit = (ImageView) findViewById(R.id.img_customerdetails_edit);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_details);

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
		
		title.setText(CustomerListActivity.title);
		adres.setText(CustomerListActivity.adress);
		// haghighi.setText(CustomerListActivity.title);
		tel.setText(CustomerListActivity.tel);
		database db = new database(this);
		db.database();
		db.open();
		ArrayList<String> rel = new ArrayList<>();
		ArrayList<String> semat2 = new ArrayList<>();
		String sematString = "";
		
		Cursor c = db.GetPersonRelationsByCustomerId(CustomerListActivity.text);
		while (c.moveToNext()) {
			
			rel.add(c.getString(3));
			semat2.add(c.getString(2));
		}
		
		Cursor c2 = db.getRelationRolesByID(semat2.get(0));
		while (c2.moveToNext()) {
			sematString = c2.getString(0);
		}
		
		ashkhas.setText(rel.get(0));
		semat.setText(sematString);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "delete", 1).show();
			}
		});
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "صفحه ویرایش موجود نیست", 1).show();
				
			}
		});

	}

}
