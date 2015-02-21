package com.Atieh.crm_mobile;

import com.Atieh.crm_mobile.R.color;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ProductServisesActivity extends Activity {
	ImageButton btnsearch;
	ImageButton btnhome;
	ImageButton btnmonthview;
	ImageButton btnclearsearchtext;
	Button btnproduct;
	Button btnservisec;
	LinearLayout ll_loading;
	LinearLayout ll_hidesearch;
	ListView lv_product;
	EditText et_search;

	public void initview() {
		btnsearch = (ImageButton) findViewById(R.id.btn_search_productservices);
		btnhome = (ImageButton) findViewById(R.id.btn_home_productservices);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthview_productservices);
		btnclearsearchtext = (ImageButton) findViewById(R.id.imgbtn_clearsearch_productservises);
		btnproduct = (Button) findViewById(R.id.btn_product_productservices);
		btnservisec = (Button) findViewById(R.id.btn_servisec_productservices);
		lv_product = (ListView) findViewById(R.id.lv_product_servisec);
		et_search = (EditText) findViewById(R.id.et_search_productservices);
		ll_hidesearch = (LinearLayout) findViewById(R.id.ll_search);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_services);

		initview();
		// ll_hidesearch.setVisibility(View.GONE);

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

				btnproduct.setBackgroundResource(color.bg_action_green);
				btnservisec.setBackgroundResource(color.bg_action_gray);
				Toast.makeText(getApplicationContext(), "�������", 1).show();
			}
		});
		btnservisec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				btnproduct.setBackgroundResource(color.bg_action_gray);
				btnservisec.setBackgroundResource(color.bg_action_green);
//				btnservisec.setTextColor(color.first_row_background_color);
				Toast.makeText(getApplicationContext(), "�����", 1).show();
			}
		});

	}

}
