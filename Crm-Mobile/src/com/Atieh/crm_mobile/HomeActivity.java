package com.Atieh.crm_mobile;

import singleTones.authInfo;
import GetActivitiesPack.GetActivities;
import GetActivitiesPack.GetActivitiesInterface;
import GetActivityStatusPack.GetActivityStatus;
import GetActivityStatusPack.GetActivityStatusInterface;
import GetCustomersPack.GetCustomers;
import GetCustomersPack.GetCustomersInterface;
import GetProductAndServicespack.GetProductAndServicesInterface;
import GetProductAndServicespack.GetProductsAndServices;
import GetRelationRolesPack.GetRelationRoles;
import GetRelationRolesPack.GetRelationRolesInterface;
import GetTasksPack.GetTasks;
import GetTasksPack.GetTasksInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.Atieh.crm_mobile.CustemerSendTestActivity;

import com.Atieh.crm_mobile_webService.ServiceGenerator;

import dataBase.database;

public class HomeActivity extends Activity {

	Button btnmounthview;
	Button btnproductservises;

	public void initview() {
		btnmounthview = (Button) findViewById(R.id.btn_monthview);
		btnproductservises = (Button) findViewById(R.id.btn_productservises);

	}

	database db;

	public static final String baseURL = "http://webservice.atiehpardaz.com/CrmService/CrmService.svc";

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
		btnproductservises.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(HomeActivity.this,
						ProductServisesActivity.class));

			}
		});

		Button btn = (Button) findViewById(R.id.btn_customer);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent sendCustomer = new Intent();
				sendCustomer.setClass(getApplicationContext(),
						CustomerListActivity.class);
				startActivity(sendCustomer);
			}
		});

		db = new database(this);

		asyncGetProductAndService gps = new asyncGetProductAndService();
		gps.execute("4");

	}

	public class asyncGetProductAndService extends
	AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			return updateAll("4");
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
		}

	}

	public String updateAll(String token) {

		GetProductsAndServices PandSs = null;
		db.database();
		db.open();

		// get products and swervices
		GetProductAndServicesInterface PS = ServiceGenerator.createService(
				GetProductAndServicesInterface.class, baseURL);
		PandSs = new GetProductsAndServices();

		try {
			PandSs = PS.getps(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE",
					Toast.LENGTH_LONG).show();
		}

		// GetCustomers
		GetCustomersInterface CustomersAdapter = ServiceGenerator
				.createService(GetCustomersInterface.class, baseURL);
		GetCustomers customers = new GetCustomers();
		try {
			customers = CustomersAdapter.getCustomers(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE",
					Toast.LENGTH_LONG).show();
		}

		// GetRelationsRoles
		GetRelationRolesInterface RelationsAdapter = ServiceGenerator
				.createService(GetRelationRolesInterface.class, baseURL);
		GetRelationRoles relations = RelationsAdapter.getRelationRoles(token);

		// getActivityStatus
		GetActivityStatusInterface ActivityStatusAdapter = ServiceGenerator
				.createService(GetActivityStatusInterface.class, baseURL);
		GetActivityStatus activitystatus = new GetActivityStatus();
		activitystatus = ActivityStatusAdapter.getActivityStatus(token);

		//GetTasks
		GetTasksInterface TasksAdapter = ServiceGenerator
				.createService(GetTasksInterface.class, baseURL);
		GetTasks gettask = new GetTasks();
		gettask = TasksAdapter.gettasks(token);


		//Get Activities
		GetActivitiesInterface ActivitiesAdapter = ServiceGenerator
				.createService(GetActivitiesInterface.class, baseURL);
		GetActivities activities = new GetActivities();
		activities = ActivitiesAdapter.getActivities(token);


		// insert products
		if (PandSs.getProducts().getInserted() != null) {
			for (int i = 0; i < PandSs.getProducts().getInserted().size(); i++) {

				db.InsertProduct(PandSs.getProducts().getInserted().get(i)
						.getId(), PandSs.getProducts().getInserted().get(i)
						.getName(), 0);
			}
		}

		// insert services
		if (PandSs.getServices().getInserted() != null) {
			for (int i = 0; i < PandSs.getServices().getInserted().size(); i++) {

				db.InsertService(PandSs.getServices().getInserted().get(i)
						.getId(), PandSs.getServices().getInserted().get(i)
						.getName(), 0);
			}
		}

		// update products
		if (PandSs.getProducts().getUpdated() != null) {
			for (int i = 0; i < PandSs.getProducts().getUpdated().size(); i++) {

				db.UpdateProduct(PandSs.getProducts().getUpdated().get(i)
						.getId(), PandSs.getProducts().getUpdated().get(i)
						.getName(), 0);
			}
		}

		// update services
		if (PandSs.getServices().getUpdated() != null) {
			for (int i = 0; i < PandSs.getServices().getUpdated().size(); i++) {

				db.UpdateService(PandSs.getServices().getUpdated().get(i)
						.getId(), PandSs.getServices().getUpdated().get(i)
						.getName(), 0);
			}
		}

		// delete products
		if (PandSs.getProducts().getDeleted() != null) {
			for (int i = 0; i < PandSs.getProducts().getDeleted().size(); i++) {

				db.DeleteProduct(PandSs.getProducts().getDeleted().get(i)
						.getId());
			}
		}

		// delete services
		if (PandSs.getServices().getDeleted() != null) {
			for (int i = 0; i < PandSs.getServices().getDeleted().size(); i++) {

				db.DeleteService(PandSs.getServices().getDeleted().get(i)
						.getId());
			}
		}

		// insert customer
		if (customers.getInserted() != null) {

			for (int i = 0; i < customers.getInserted().size(); i++) {

				db.InsertCustomer(customers.getInserted().get(i).getId(),
						customers.getInserted().get(i).getTitle(), customers
						.getInserted().get(i).getDescription(),
						customers.getInserted().get(i).isIsLegal() ? 1 : 0,
								customers.getInserted().get(i).getAddress(), customers
								.getInserted().get(i).getTel());
				if (customers.getInserted().get(i).getPersonRelations() != null) {
					for (int j = 0; j < customers.getInserted().get(i)
							.getPersonRelations().size(); j++) {
						db.InsertPersonRelations(customers.getInserted().get(i)
								.getPersonRelations().get(j).getCustomerId(),
								customers.getInserted().get(i)
								.getPersonRelations().get(j).getId(),
								customers.getInserted().get(i)
								.getPersonRelations().get(j)
								.getRelationRoleId(), customers
								.getInserted().get(i)
								.getPersonRelations().get(j).getTitle());
					}

				}
			}

		}

		// update customers
		if (customers.getUpdated() != null) {

			for (int i = 0; i < customers.getUpdated().size(); i++) {

				db.UpdateCustomer(customers.getUpdated().get(i).getId(),
						customers.getUpdated().get(i).getTitle(), customers
						.getUpdated().get(i).getDescription(),
						customers.getUpdated().get(i).isIsLegal() ? 1 : 0,
								customers.getUpdated().get(i).getAddress(), customers
								.getUpdated().get(i).getTel());

				if (customers.getUpdated().get(i).getPersonRelations() != null) {
					for (int j = 0; j < customers.getUpdated().get(i)
							.getPersonRelations().size(); j++) {
						db.UpdatePersonRelations(customers.getUpdated().get(i)
								.getPersonRelations().get(j).getCustomerId(),
								customers.getUpdated().get(i)
								.getPersonRelations().get(j).getId(),
								customers.getUpdated().get(i)
								.getPersonRelations().get(j)
								.getRelationRoleId(), customers
								.getUpdated().get(i)
								.getPersonRelations().get(j).getTitle());
					}

				}
			}

		}

		// DeletedPersonRelation
		if (customers.getDeletedPersonRelation() != null) {
			for (int i = 0; i < customers.getDeletedPersonRelation().size(); i++) {
				db.DeletePersonRelations(customers.getDeletedPersonRelation()
						.get(i).getCustomerId(), customers
						.getDeletedPersonRelation().get(i).getId());
			}
		}

		// delete Customers
		if (customers.getDeleted() != null) {
			for (int i = 0; i < customers.getDeleted().size(); i++) {
				db.DeleteCustomer(customers.getDeleted().get(i).getId());
			}

		}

		// insert RelationRoles
		if (relations.getInserted() != null) {
			for (int i = 0; i < relations.getInserted().size(); i++) {
				db.InsertRelationRoles(relations.getInserted().get(i).getId(),
						relations.getInserted().get(i).getTitle());
			}
		}

		// update RelationRoles
		if (relations.getUpdated() != null) {
			for (int i = 0; i < relations.getUpdated().size(); i++) {
				db.UpdateRelationRoles(relations.getUpdated().get(i).getId(),
						relations.getUpdated().get(i).getTitle());
			}
		}

		// delete relationRoles
		if (relations.getDeleted() != null) {
			for (int i = 0; i < relations.getDeleted().size(); i++) {
				db.DeleteRelationRoles(relations.getDeleted().get(i).getId());
			}
		}

		// insert activitystatus
		if (activitystatus.getInserted() != null) {
			for (int i = 0; i < activitystatus.getInserted().size(); i++) {
				db.InsertActivityStatus(activitystatus.getInserted().get(i)
						.getId(), activitystatus.getInserted().get(i)
						.getTitle());
			}
		}

		// update activitystatus
		if (activitystatus.getUpdated() != null) {
			for (int i = 0; i < activitystatus.getUpdated().size(); i++) {
				db.UpdateActivityStatus(activitystatus.getUpdated().get(i)
						.getId(), activitystatus.getUpdated().get(i).getTitle());
			}
		}

		// delete activitystatus
		if (activitystatus.getDeleted() != null) {
			for (int i = 0; i < activitystatus.getDeleted().size(); i++) {
				db.DeleteActivityStatus(activitystatus.getDeleted().get(i)
						.getId());
			}
		}


//		//insert Tasks
//		if (gettask.getInserted() != null) {
//			for (int i = 0; i < gettask.getInserted().size(); i++) {
//				db.InsertTasks(gettask.getInserted().get(i).getId(),
//						gettask.getInserted().get(i).getCustomerId(),
//						gettask.getInserted().get(i).getDescription(), 
//						gettask.getInserted().get(i).getFromDateTime(),
//						gettask.getInserted().get(i).isIsAm() == null ? "null":gettask.getInserted().get(i).isIsAm().toString(),
//								gettask.getInserted().get(i).getParentActivityId() == null ? "": gettask.getInserted().get(i).getParentActivityId().toString(),
//										gettask.getInserted().get(i).getParentTaskId()== null ? "": gettask.getInserted().get(i).getParentTaskId().toString() , 
//												gettask.getInserted().get(i).getPersonRelationId()== null ? "": gettask.getInserted().get(i).getPersonRelationId().toString(),
//														gettask.getInserted().get(i).getTemporaryCustomerId()== null ? "": gettask.getInserted().get(i).getTemporaryCustomerId().toString(), 
//																gettask.getInserted().get(i).getTemporaryCustomerPersonRelationsId()== null ? "": gettask.getInserted().get(i).getTemporaryCustomerPersonRelationsId().toString(),
//																		gettask.getInserted().get(i).getTitle(), 
//																		gettask.getInserted().get(i).getToDateTime()== null ? "": gettask.getInserted().get(i).getToDateTime().toString() );
//
//				if(gettask.getInserted().get(i).getProductsIds() != null){
//
//					for (int j = 0; j < gettask.getInserted().get(i).getProductsIds().size(); j++) {
//
//						db.InsertTasksProducts(gettask.getInserted().get(i).getId(),
//								gettask.getInserted().get(i).getProductsIds().get(j).getId());	
//					}
//				}
//
//				if(gettask.getInserted().get(i).getServicesIds() != null){
//
//					for (int j = 0; j < gettask.getInserted().get(i).getServicesIds().size(); j++) {
//
//						db.InsertTasksServices(gettask.getInserted().get(i).getId(),
//								gettask.getInserted().get(i).getServicesIds().get(j).getId());	
//					}
//				}
//
//
//			}
//
//		}
//
//
//
//		//update Tasks
//		if (gettask.getUpdated() != null) {
//			for (int i = 0; i < gettask.getUpdated().size(); i++) {
//				db.UpdateTasks(gettask.getUpdated().get(i).getId(),
//						gettask.getUpdated().get(i).getCustomerId(),
//						gettask.getUpdated().get(i).getDescription(), 
//						gettask.getUpdated().get(i).getFromDateTime(),
//						gettask.getInserted().get(i).isIsAm() == null ? "null":gettask.getInserted().get(i).isIsAm().toString(),
//								gettask.getUpdated().get(i).getParentActivityId() == null ? "": gettask.getUpdated().get(i).getParentActivityId().toString(),
//										gettask.getUpdated().get(i).getParentTaskId()== null ? "": gettask.getUpdated().get(i).getParentTaskId().toString() , 
//												gettask.getUpdated().get(i).getPersonRelationId()== null ? "": gettask.getUpdated().get(i).getPersonRelationId().toString(),
//														gettask.getUpdated().get(i).getTemporaryCustomerId()== null ? "": gettask.getUpdated().get(i).getTemporaryCustomerId().toString(), 
//																gettask.getUpdated().get(i).getTemporaryCustomerPersonRelationsId()== null ? "": gettask.getUpdated().get(i).getTemporaryCustomerPersonRelationsId().toString(),
//																		gettask.getUpdated().get(i).getTitle(), 
//																		gettask.getUpdated().get(i).getToDateTime()== null ? "": gettask.getUpdated().get(i).getToDateTime().toString() );
//
//				if(gettask.getUpdated().get(i).getProductsIds() != null){
//
//					for (int j = 0; j < gettask.getUpdated().get(i).getProductsIds().size(); j++) {
//
//						db.InsertTasksProducts(gettask.getUpdated().get(i).getId(),
//								gettask.getUpdated().get(i).getProductsIds().get(j).getId());	
//					}
//				}
//
//				if(gettask.getUpdated().get(i).getServicesIds() != null){
//
//					for (int j = 0; j < gettask.getUpdated().get(i).getServicesIds().size(); j++) {
//
//						db.InsertTasksServices(gettask.getUpdated().get(i).getId(),
//								gettask.getUpdated().get(i).getServicesIds().get(j).getId());	
//					}
//				}
//
//			}
//		}
//
//
//		//delete Tasks
//		if (gettask.getDeleted() != null) {
//			for (int i = 0; i < gettask.getDeleted().size(); i++) {
//				db.DeleteTasks(gettask.getDeleted().get(i).getId());
//			}
//		}
//

		//insert activities
		if(activities.getInserted() != null){
			for (int i = 0; i < activities.getInserted().size(); i++) {
				db.InsertActivities(activities.getInserted().get(i).getId(),
						activities.getInserted().get(i).getActivityStatusId(),
						activities.getInserted().get(i).getCustomerId()== null ? "": activities.getInserted().get(i).getCustomerId().toString(),
						activities.getInserted().get(i).getDescription(),
						activities.getInserted().get(i).getFromDateTime(),
						activities.getInserted().get(i).isHasNextTask() ? "1" : "0",
						activities.getInserted().get(i).getParentActivityId()== null ? "": activities.getInserted().get(i).getParentActivityId().toString(), 
						activities.getInserted().get(i).getPersonRelationId()== null ? "": activities.getInserted().get(i).getPersonRelationId().toString(), 
						activities.getInserted().get(i).getTaskId()== null ? "": activities.getInserted().get(i).getTaskId().toString(),
						activities.getInserted().get(i).getTemporaryCustomerId()== null ? "": activities.getInserted().get(i).getTemporaryCustomerId().toString(), 
						activities.getInserted().get(i).getToDateTime()== null ? "": activities.getInserted().get(i).getToDateTime().toString()
								);
				
			}
		}
		
		//update activities
		if(activities.getUpdated() != null){
			for (int i = 0; i < activities.getUpdated().size(); i++) {
				db.UpdateActivities(activities.getUpdated().get(i).getId(),
						activities.getUpdated().get(i).getActivityStatusId(),
						activities.getUpdated().get(i).getCustomerId()== null ? "": activities.getUpdated().get(i).getCustomerId().toString(),
						activities.getUpdated().get(i).getDescription(),
						activities.getUpdated().get(i).getFromDateTime(),
						activities.getUpdated().get(i).isHasNextTask() ? "1" : "0",
						activities.getUpdated().get(i).getParentActivityId()== null ? "": activities.getUpdated().get(i).getParentActivityId().toString(), 
						activities.getUpdated().get(i).getPersonRelationId()== null ? "": activities.getUpdated().get(i).getPersonRelationId().toString(), 
						activities.getUpdated().get(i).getTaskId()== null ? "": activities.getUpdated().get(i).getTaskId().toString(),
						activities.getUpdated().get(i).getTemporaryCustomerId()== null ? "": activities.getUpdated().get(i).getTemporaryCustomerId().toString(), 
						activities.getUpdated().get(i).getToDateTime()== null ? "": activities.getUpdated().get(i).getToDateTime().toString()
								);
				
			}
		}
			
		//delete activities
		if(activities.getDeleted() != null){
			for (int i = 0; i < activities.getUpdated().size(); i++) {
				db.DeleteActivities(activities.getDeleted().get(i).getId());
				
			}
		}


		db.close();

		return "succeed";
	}

}
