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

	// public MyAdapter(Context context, String[] values, Cursor curall) {
	public customlistAadapter(Context context, String[] values) {

		super(context, R.layout.row, values);
		this.context = context;
		this.values = values;
		b = (Context) context;
		this.curall = curall;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row, parent, false);

		TextView id = (TextView) rowView.findViewById(R.id.tv_id_customer); // id
		TextView title = (TextView) rowView.findViewById(R.id.tv_customer); // title

		// final Cursor cur = curall;
		final Cursor cur = database.mydb.rawQuery(
				"select * from custemers WHERE id='" + values[position] + "'",
				null);

		if (cur != null)
			cur.moveToFirst();

		id.setVisibility(View.GONE);

		id.setText(cur.getString(0));

		title.setText(cur.getString(1));

		// Toast.makeText(b,id.getText().toString(), 1).show();

		return rowView;
	}
}