package com.Atieh.crm_mobile;

import singleTones.authInfo;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class HomeActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Toast.makeText(this, authInfo.getInstance().getSalt(), Toast.LENGTH_LONG).show();
	}
}
