package com.Atieh.crm_mobile;

import GetActivityStatusPack.GetActivityStatus;
import GetActivityStatusPack.GetActivityStatusInterface;
import GetCustomersPack.GetCustomers;
import GetCustomersPack.GetCustomersInterface;
import GetProductAndServicespack.GetProductAndServicesInterface;
import GetProductAndServicespack.GetProductsAndServices;
import GetRelationRolesPack.GetRelationRoles;
import GetRelationRolesPack.GetRelationRolesInterface;
import android.app.Activity;
import android.content.Intent;
<<<<<<< HEAD
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

=======
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;

import dataBase.database;


public class HomeActivity extends Activity{

	database db;
	public static final String baseURL = "http://webservice.atiehpardaz.com/CrmService/CrmService.svc";

>>>>>>> 30afd3a7df613eff7251fbb2b7f1d95fe6ee8248
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
<<<<<<< HEAD
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
=======
		
		Button btn = (Button) findViewById(R.id.btn_customer);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent sendCustomer=new Intent();
				sendCustomer.setClass(getApplicationContext(), CustemerSendTestActivity.class);
				startActivity(sendCustomer);
			}
		});
		
		db = new database(this);

		asyncGetProductAndService gps = new asyncGetProductAndService();
		gps.execute("4");

	}


	public class asyncGetProductAndService extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			return updateAll("4");
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
		}
>>>>>>> 30afd3a7df613eff7251fbb2b7f1d95fe6ee8248

	}



	public String updateAll(String token){

		GetProductsAndServices PandSs = null;
		db.database();
		db.open();

		//get products and swervices
		GetProductAndServicesInterface PS = ServiceGenerator.createService(GetProductAndServicesInterface.class, baseURL);		
		PandSs = new GetProductsAndServices();

		try {
			PandSs = PS.getps(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE", Toast.LENGTH_LONG).show();
		}

		// GetCustomers	
		GetCustomersInterface CustomersAdapter = ServiceGenerator.createService(GetCustomersInterface.class, baseURL);		
		GetCustomers customers = new GetCustomers();
		try {
			customers = CustomersAdapter.getCustomers(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE", Toast.LENGTH_LONG).show();
		}


		//GetRelationsRoles
		GetRelationRolesInterface RelationsAdapter = ServiceGenerator.createService(GetRelationRolesInterface.class, baseURL);		
		GetRelationRoles relations = RelationsAdapter.getRelationRoles(token);


		//getActivityStatus 
		GetActivityStatusInterface ActivityStatusAdapter = ServiceGenerator.createService(GetActivityStatusInterface.class, baseURL);
		GetActivityStatus activitystatus = new GetActivityStatus();
		activitystatus = ActivityStatusAdapter.getActivityStatus(token);


		//insert products
		if(PandSs.getProducts().getInserted() !=null){
			for (int i = 0; i <PandSs.getProducts().getInserted().size(); i++) {

				db.InsertProduct(PandSs.getProducts().getInserted().get(i).getId(),
						PandSs.getProducts().getInserted().get(i).getName(), 0);
			}
		}

		//insert services
		if(PandSs.getServices().getInserted() != null){
			for (int i = 0; i <PandSs.getServices().getInserted().size(); i++) {

				db.InsertService(PandSs.getServices().getInserted().get(i).getId(),
						PandSs.getServices().getInserted().get(i).getName(), 0);
			}
		}


		//update products
		if(PandSs.getProducts().getUpdated() != null){
			for (int i = 0; i <PandSs.getProducts().getUpdated().size(); i++) {

				db.UpdateProduct(PandSs.getProducts().getUpdated().get(i).getId(),
						PandSs.getProducts().getUpdated().get(i).getName(), 0);
			}
		}

		//update services
		if(PandSs.getServices().getUpdated()!=null){
			for (int i = 0; i <PandSs.getServices().getUpdated().size(); i++) {

				db.UpdateService(PandSs.getServices().getUpdated().get(i).getId(),
						PandSs.getServices().getUpdated().get(i).getName(), 0);
			}
		}

		//delete products
		if(PandSs.getProducts().getDeleted() != null){
			for (int i = 0; i <PandSs.getProducts().getDeleted().size(); i++) {

				db.DeleteProduct(PandSs.getProducts().getDeleted().get(i).getId());
			}
		}

		//delete services
		if(PandSs.getServices().getDeleted() != null){
			for (int i = 0; i <PandSs.getServices().getDeleted().size(); i++) {

				db.DeleteService(PandSs.getServices().getDeleted().get(i).getId());
			}
		}


		//insert customer
		if(customers.getInserted() != null){

			for (int i = 0; i <customers.getInserted().size(); i++) {

				db.InsertCustomer(customers.getInserted().get(i).getId(),
						customers.getInserted().get(i).getTitle(),
						customers.getInserted().get(i).getDescription(),
						customers.getInserted().get(i).isIsLegal() ? 1 : 0,
								customers.getInserted().get(i).getAddress(),
								customers.getInserted().get(i).getTel());
				if(customers.getInserted().get(i).getPersonRelations() !=null)
				{
					for (int j = 0; j <customers.getInserted().get(i).getPersonRelations().size(); j++) {
						db.InsertPersonRelations(
								customers.getInserted().get(i).getPersonRelations().get(j).getCustomerId(), 
								customers.getInserted().get(i).getPersonRelations().get(j).getId(), 
								customers.getInserted().get(i).getPersonRelations().get(j).getRelationRoleId(), 
								customers.getInserted().get(i).getPersonRelations().get(j).getTitle());
					}

				}
			}

		}

		//update customers
		if(customers.getUpdated() != null){

			for (int i = 0; i <customers.getUpdated().size(); i++) {

				db.UpdateCustomer(customers.getUpdated().get(i).getId(),
						customers.getUpdated().get(i).getTitle(),
						customers.getUpdated().get(i).getDescription(),
						customers.getUpdated().get(i).isIsLegal() ? 1 : 0,
								customers.getUpdated().get(i).getAddress(),
								customers.getUpdated().get(i).getTel());

				if(customers.getUpdated().get(i).getPersonRelations() !=null)
				{
					for (int j = 0; j <customers.getUpdated().get(i).getPersonRelations().size(); j++) {
						db.UpdatePersonRelations(
								customers.getUpdated().get(i).getPersonRelations().get(j).getCustomerId(), 
								customers.getUpdated().get(i).getPersonRelations().get(j).getId(), 
								customers.getUpdated().get(i).getPersonRelations().get(j).getRelationRoleId(), 
								customers.getUpdated().get(i).getPersonRelations().get(j).getTitle());
					}

				}
			}

		}

		//DeletedPersonRelation
		if(customers.getDeletedPersonRelation() != null){
			for (int i = 0; i <customers.getDeletedPersonRelation().size(); i++) {
				db.DeletePersonRelations(
						customers.getDeletedPersonRelation().get(i).getCustomerId(),
						customers.getDeletedPersonRelation().get(i).getId());
			}
		}

		//delete Customers
		if (customers.getDeleted() != null) {
			for (int i = 0; i <customers.getDeleted().size(); i++) {
				db.DeleteCustomer(customers.getDeleted().get(i).getId());
			}

		}


		//insert RelationRoles
		if(relations.getInserted() !=null){
			for (int i = 0; i < relations.getInserted().size(); i++) {
				db.InsertRelationRoles(
						relations.getInserted().get(i).getId(),
						relations.getInserted().get(i).getTitle());
			}
		}

		//update RelationRoles
		if(relations.getUpdated() !=null){
			for (int i = 0; i < relations.getUpdated().size(); i++) {
				db.UpdateRelationRoles(
						relations.getUpdated().get(i).getId(),
						relations.getUpdated().get(i).getTitle());
			}
		}

		//delete relationRoles
		if(relations.getDeleted() !=null){
			for (int i = 0; i < relations.getDeleted().size(); i++) {
				db.DeleteRelationRoles(relations.getDeleted().get(i).getId());
			}
		}
		
		

		//insert activitystatus
		if(activitystatus.getInserted() !=null){
			for (int i = 0; i < activitystatus.getInserted().size(); i++) {
				db.InsertActivityStatus(
						activitystatus.getInserted().get(i).getId(),
						activitystatus.getInserted().get(i).getTitle());
			}
		}

		//update activitystatus
		if(activitystatus.getUpdated() !=null){
			for (int i = 0; i < activitystatus.getUpdated().size(); i++) {
				db.UpdateActivityStatus(
						activitystatus.getUpdated().get(i).getId(),
						activitystatus.getUpdated().get(i).getTitle());
			}
		}

		//delete activitystatus
		if(activitystatus.getDeleted() !=null){
			for (int i = 0; i < activitystatus.getDeleted().size(); i++) {
				db.DeleteActivityStatus(activitystatus.getDeleted().get(i).getId());
			}
		}

		

		db.close();
		return "succeed";
	}


}
