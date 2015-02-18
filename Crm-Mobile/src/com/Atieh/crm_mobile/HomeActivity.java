package com.Atieh.crm_mobile;

import singleTones.authInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

	Button btnmounthview;

	public void initview() {
		btnmounthview = (Button) findViewById(R.id.btn_monthview);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initview();
		Toast.makeText(this, authInfo.getInstance().getSalt(),
				Toast.LENGTH_LONG).show();

		btnmounthview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(HomeActivity.this,
						com.Atieh.crm_mobile_calendar.MainActivity.class);
				startActivity(intent);

			}
		});

	}
}
