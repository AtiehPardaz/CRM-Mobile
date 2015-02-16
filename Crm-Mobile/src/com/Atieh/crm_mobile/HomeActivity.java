package com.Atieh.crm_mobile;

import getProductAndServicespack.GetProductAndServicesInterface;
import getProductAndServicespack.GetProductsAndServices;
import singleTones.authInfo;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;


public class HomeActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		asyncGetProductAndService gps = new asyncGetProductAndService();
		gps.execute("4");
		
	}
	

	public class asyncGetProductAndService extends AsyncTask<String, String, String> {
		GetProductsAndServices PandSs = null;
	
		@Override
		protected String doInBackground(String... arg0) {

			GetProductAndServicesInterface PS = ServiceGenerator.createService(GetProductAndServicesInterface.class, "http://webservice.atiehpardaz.com/CrmService/CrmService.svc");
		
			PandSs = new GetProductsAndServices();
			PandSs = PS.getps(arg0[0]);
			
			return PandSs.getProducts().getDeleted().get(0).getId();
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
		}

	}

}
