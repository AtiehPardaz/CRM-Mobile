package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Atieh.crm_mobile.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customlistAadapter extends BaseAdapter {

	Context context;
	private Activity activity;
	private List<List<String>> data;

	private static LayoutInflater inflater = null;
	TextView title;

	public customlistAadapter(Activity a, List<List<String>> ll) {
		activity = a;
		data = ll;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.row, null);

		TextView id = (TextView) vi.findViewById(R.id.tv_id_customer); // id
		title = (TextView) vi.findViewById(R.id.tv_customer); // title

		// ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); //
		// thumb image

		Typeface face = Typeface.createFromAsset(this.activity
				.getApplicationContext().getAssets(), "nazanin.ttf");
		title.setTypeface(face);



		id.setText(data.get(position).get(0));
		title.setText(data.get(position).get(1));

		return vi;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position).get(2);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}