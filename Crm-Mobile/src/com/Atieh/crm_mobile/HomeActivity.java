package com.Atieh.crm_mobile;

import GetCustomersPack.GetCustomers;
import GetCustomersPack.GetCustomersInterface;
import GetProductAndServicespack.GetProductAndServicesInterface;
import GetProductAndServicespack.GetProductsAndServices;
import GetRelationRolesPack.GetRelationRoles;
import GetRelationRolesPack.GetRelationRolesInterface;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;

import dataBase.database;


public class HomeActivity extends Activity{

	database db;
	public static final String baseURL = "http://webservice.atiehpardaz.com/CrmService/CrmService.svc";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		db = new database(this);

		asyncGetProductAndService gps = new asyncGetProductAndService();
		gps.execute("4");

	}


	public class asyncGetProductAndService extends AsyncTask<String, String, String> {
		GetProductsAndServices PandSs = null;

		@Override
		protected String doInBackground(String... arg0) {

			db.database();
			db.open();

			GetProductAndServicesInterface PS = ServiceGenerator.createService(GetProductAndServicesInterface.class, baseURL);		
			PandSs = new GetProductsAndServices();
			
			try {
				PandSs = PS.getps(arg0[0]);
				
			} catch (Exception e) {
				Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE", Toast.LENGTH_LONG).show();
			}
			

			//insert products
			for (int i = 0; i <PandSs.getProducts().getInserted().size(); i++) {

				db.InsertProduct(PandSs.getProducts().getInserted().get(i).getId(),
						PandSs.getProducts().getInserted().get(i).getName(), 0);
			}
			
			//insert services
			for (int i = 0; i <PandSs.getServices().getInserted().size(); i++) {

				db.InsertService(PandSs.getServices().getInserted().get(i).getId(),
						PandSs.getServices().getInserted().get(i).getName(), 0);
			}
			
			
			//update products
			for (int i = 0; i <PandSs.getProducts().getUpdated().size(); i++) {

				db.UpdateProduct(PandSs.getProducts().getUpdated().get(i).getId(),
						PandSs.getProducts().getUpdated().get(i).getName(), 0);
			}

			//update services
			for (int i = 0; i <PandSs.getServices().getUpdated().size(); i++) {

				db.UpdateService(PandSs.getServices().getUpdated().get(i).getId(),
						PandSs.getServices().getUpdated().get(i).getName(), 0);
			}

			//delete products
			for (int i = 0; i <PandSs.getProducts().getDeleted().size(); i++) {

				db.DeleteProduct(PandSs.getProducts().getDeleted().get(i).getId());
			}
			
			//delete services
			for (int i = 0; i <PandSs.getServices().getDeleted().size(); i++) {

				db.DeleteService(PandSs.getServices().getDeleted().get(i).getId());
			}


			
			// GetCustomers	
			GetCustomersInterface CustomersAdapter = ServiceGenerator.createService(GetCustomersInterface.class, baseURL);		
			GetCustomers customers = new GetCustomers();
			try {
				customers = CustomersAdapter.getCustomers(arg0[0]);
				
			} catch (Exception e) {
				Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE", Toast.LENGTH_LONG).show();
			}
			
			
			
			//GetRelations
			GetRelationRolesInterface RelationsAdapter = ServiceGenerator.createService(GetRelationRolesInterface.class, baseURL);		
			GetRelationRoles relations = RelationsAdapter.getRelationRoles("1");
			
			
			db.close();
			 
			

			return Integer.toString(relations.getInserted().size());
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
		}

	}

}
