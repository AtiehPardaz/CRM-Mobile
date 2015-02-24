package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomerListActivity extends Activity {

	Button add;

	public void initview() {
		add = (Button) findViewById(R.id.btn_add_customerlist);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_list);
		initview();
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(CustomerListActivity.this,
						CustemerSendTestActivity.class));

			}
		});

	}

}
