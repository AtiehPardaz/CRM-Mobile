package com.Atieh.crm_mobile;

import dataBase.database;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.storage.OnObbStateChangeListener;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.widget.TextView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customlistAadapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	Context b;

	public customlistAadapter(Context context, String[] values) {
		super(context, R.layout.row, values);
		this.context = context;
		this.values = values;
		b = (Context) context;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row, parent, false);
		database db;
		db = new database(rowView.getContext());
		db.database();
		db.open();
		//
		// Cursor products = db.GetPrudoctsNames();
		// Cursor services = db.GetServicesNames();

		final ImageButton showditails_btn = (ImageButton) rowView
				.findViewById(R.id.imgbtn_showdetails);
		final ImageButton mark_btn = (ImageButton) rowView
				.findViewById(R.id.imgbtn_tik);

		TextView tv_customer = (TextView) rowView
				.findViewById(R.id.tv_customer);
		TextView tv_id = (TextView) rowView.findViewById(R.id.tv_id_customer);

		final Cursor cur = db.GetCustomers();
		// if (cur != null)
		// cur.moveToFirst();

		tv_id.setText(String.valueOf(cur.getString(cur.getInt(0))));
		tv_id.setVisibility(View.GONE);

		// Toast.makeText(b,id.getText().toString(), 1).show();

		// tv_customer.setText(cur.getString(cur.getColumnIndex("name")));

		tv_customer.setText(cur.getString(cur.getInt(1)));

		showditails_btn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Intent sendsms = new Intent(Intent.ACTION_VIEW);
				// sendsms.setData(Uri.parse("smsto:"));
				// sendsms.setType("vnd.android-dir/mms-sms");
				// sendsms.putExtra("address", new String("0123456789"));
				// sendsms.putExtra("sms_body",
				// cur.getString(cur.getColumnIndex("name")));
				// b.startActivity(sendsms);
			}
		});

		mark_btn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				// b.startActivity(intent);

			}
		});

		return rowView;
	}
}
