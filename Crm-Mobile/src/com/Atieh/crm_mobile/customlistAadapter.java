package com.Atieh.crm_mobile;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import dataBase.database;

public class customlistAadapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	Context b;
	Cursor curall;
	database db;

	// public MyAdapter(Context context, String[] values, Cursor curall) {
	public customlistAadapter(Context context, String[] values) {

		super(context, R.layout.row, values);
		this.context = context;
		this.values = values;

		b = (Context) context;

		// this.curall = curall;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row, parent, false);

		TextView id = (TextView) rowView.findViewById(R.id.tv_id_customer); // id
		TextView title = (TextView) rowView.findViewById(R.id.tv_customer); // title
		TextView adres = (TextView) rowView.findViewById(R.id.tv_customeradress); // title
		TextView haghighi = (TextView) rowView.findViewById(R.id.tv_customerhaghighi); // title
		TextView tel = (TextView) rowView.findViewById(R.id.tv_customertel); // title
//		TextView semat = (TextView) rowView.findViewById(R.id.tv_customersemat); // title
//		TextView mortabet = (TextView) rowView.findViewById(R.id.tv_customermortabet); // title

		database db = new database(b);
		db.database();
		db.open();
		// final Cursor cur = curall;
		final Cursor cur = database.mydb.rawQuery(
				"select * from custemers WHERE id='" + values[position] + "' and  IsDeleted != 1",
				null);

		if (cur != null)
			cur.moveToFirst();
		id.setText(cur.getString(0));
		id.setVisibility(View.GONE);
		//(Id Title Description IsLegal Address Tel IsDeleted)
		title.setText(cur.getString(1));
		haghighi.setText(cur.getString(3));
		adres.setText(cur.getString(4));
		tel.setText(cur.getString(5));
//		semat.setText(cur.getString(1));
//		mortabet.setText(cur.getString(1));

		// Toast.makeText(b,id.getText().toString(), 1).show();
		db.close();
		return rowView;
	}
}