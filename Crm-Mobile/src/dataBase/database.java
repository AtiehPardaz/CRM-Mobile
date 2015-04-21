package dataBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {


	public final String path="data/data/com.Atieh.crm_mobile/databases/";
	public final String Name="db";
	public static SQLiteDatabase mydb;
	private final Context mycontext;
	

	public database(Context context) {
		super(context, "db", null, 1);
		mycontext=context;

	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void database(){

		boolean checkdb=checkdb();

		if(checkdb){

		}else{

			this.getReadableDatabase();

			try{
				copydatabase();
			}catch(IOException e){

			}			
		}		
	}

	public void open(){

		mydb=SQLiteDatabase.openDatabase(path+Name, null, SQLiteDatabase.OPEN_READWRITE);

	}

	public void close(){
		mydb.close();
	}

	public boolean checkdb(){

		SQLiteDatabase db=null;
		try{	
			db=SQLiteDatabase.openDatabase(path+Name, null, SQLiteDatabase.OPEN_READONLY);
		}
		
		catch(SQLException e)
		{

		}
		return db !=null ? true:false ;

	}

	public void copydatabase() throws IOException{
		OutputStream myOutput = new FileOutputStream(path+Name);
		byte[] buffer = new byte[1024];
		int length;

		InputStream myInput = mycontext.getAssets().open("db");
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}



	public long InsertProduct (String productGUID ,String productName, int isDeleted){//address choose

		ContentValues values = new ContentValues();
		values.put("productGUID", productGUID);
		values.put("productName", productName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.insert("products", null, values);
		return ID ;
	}

	public long UpdateProduct (String productGUID ,String productName, int isDeleted){//address choose

		String strFilter = "productGUID =" +"'"+ productGUID+"'";
		ContentValues values = new ContentValues();
		values.put("productGUID", productGUID);
		values.put("productName", productName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.update("products",values,strFilter,null);
		return ID ;
	}

	public long DeleteProduct (String productGUID){

		String strFilter = "productGUID =" +"'"+ productGUID+"'";
		ContentValues values = new ContentValues();
		values.put("isDeleted", 1);
		long ID = mydb.update("products",values,strFilter,null);
		return ID ;
	}

	public Cursor GetPrudoctsNames (){

		Cursor cu= mydb.rawQuery("select p.productname from products p where p.[isDeleted] == 1", null); //temp == 1 in real must be  !=1
		return cu ;
	}

	public Cursor GetPrudocts  (){

		Cursor cu= mydb.rawQuery("select * from products p where p.[isDeleted] == 1", null); //temp == 1 in real must be  !=1
		return cu ;
	}





	public Cursor GetServicesNames (){

		Cursor cu= mydb.rawQuery("select s.serviceName from services s where s.[isDeleted] == 1", null); //temp == 1 in real must be  !=1
		return cu ;
	}

	public long InsertService (String ServiceGUID ,String ServiceName, int isDeleted){//address choose

		ContentValues values = new ContentValues();
		values.put("serviceGUID", ServiceGUID);
		values.put("serviceName", ServiceName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.insert("services", null, values);
		return ID ;
	}

	public long UpdateService (String ServiceGUID ,String ServiceName, int isDeleted){//address choose

		String strFilter = "serviceGUID =" +"'"+ ServiceGUID+"'";
		ContentValues values = new ContentValues();
		values.put("serviceGUID", ServiceGUID);
		values.put("serviceName", ServiceName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.update("services", values, strFilter,null);
		return ID ;
	}

	public long DeleteService (String ServiceGUID){

		String strFilter = "serviceGUID =" +"'"+ ServiceGUID+"'";
		ContentValues values = new ContentValues();

		values.put("isDeleted", 1);
		long ID = mydb.update("services", values, strFilter,null);
		return ID ;
	}





	public void InsertCustomer(String Id,String Title , String Description , int IsLegal,String Address,String Tel,int IsUplodedToServer){

		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title",Title );
		values.put("Description", Description);
		values.put("IsLegal", IsLegal);
		values.put("Address",Address );
		values.put("Tel", Tel);
		values.put("IsDeleted", 0);
		values.put("IsUplodedToServer", IsUplodedToServer);

		mydb.insert("custemers", null, values);
	}

	public void UpdateCustomer(String Id,String Title , String Description , int IsLegal,String Address,String Tel){

		String strFilter = "Id =" +"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title",Title );
		values.put("Description", Description);
		values.put("IsLegal", IsLegal);
		values.put("Address",Address );
		values.put("Tel", Tel);
		values.put("IsDeleted", 0);
		long ID = mydb.update("custemers", values, strFilter,null);

	}

	public void DeleteCustomer(String Id){

		String strFilter = "Id =" +"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("custemers", values, strFilter,null);

	}

	public Cursor GetCustomers (){

		Cursor cu= mydb.rawQuery("	select * from custemers where IsDeleted != 1", null); 
		return cu ;
		// (Id Title Description IsLegal Address Tel IsDeleted)
	}

	public Cursor GetCustomersByID (String id){

		Cursor cu= mydb.rawQuery("select Title from custemers where Id = '"+id+"' limit 1", null); 
		return cu ;
		// (Id Title Description IsLegal Address Tel IsDeleted)
	}






	public void InsertPersonRelations(String CustomerId,String Id,String RelationRoleId,String Title,int IsUplodedToServer){
		ContentValues values = new ContentValues();
		values.put("CustomerId", CustomerId);
		values.put("Id", Id);
		values.put("RelationRoleId", RelationRoleId);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		values.put("IsUplodedToServer", IsUplodedToServer);
		
		mydb.insert("personRelations", null, values);
	}

	public void UpdatePersonRelations(String CustomerId,String Id,String RelationRoleId,String Title){

		String strFilter = "CustomerId =" +"'"+ CustomerId+"' and Id ="+"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("RelationRoleId", RelationRoleId);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("personRelations", values, strFilter,null);

	}

	public void DeletePersonRelations(String CustomerId,String Id){

		String strFilter = "CustomerId = '"+CustomerId+"' AND Id ='"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("personRelations", values, strFilter,null);

	}

	public Cursor GetPersonRelationsByCustomerIdAndID (String CustomerId , String Id){

		Cursor cu= mydb.rawQuery("select Title from personRelations where CustomerId = '"+CustomerId+"' and Id = '"+Id+ "' limit 1", null); 
		return cu ;
		// (Id Title Description IsLegal Address Tel IsDeleted)
	}


	

	public void InsertRelationRoles(String Id,String Title){
		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		mydb.insert("RelationRoles", null, values);
	}

	public void UpdateRelationRoles(String Id,String Title){

		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("RelationRoles", values, strFilter,null);
	}

	public void DeleteRelationRoles(String Id){

		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("RelationRoles", values, strFilter,null);
	}

	public Cursor getRelationRoles(){

		Cursor cu= mydb.rawQuery("select * from RelationRoles where IsDeleted = '0'", null); 
		return cu ;
	}




	public void InsertActivityStatus(String Id,String Title){
		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		mydb.insert("activityStatus", null, values);
	}

	public void UpdateActivityStatus(String Id,String Title){

		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("activityStatus", values, strFilter,null);
	}

	public void DeleteActivityStatus(String Id){

		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("activityStatus", values, strFilter,null);
	}

	public Cursor GetActivityStatusByID (String id){
		Cursor cu= mydb.
				rawQuery("select Title from activityStatus  where Id = '"+id+"'", null); 
		return cu ;

	}

	


	public void InsertTasks(String Id,
			String CustomerId,
			String Description,
			String FromDateTime,
			String IsAm,
			String ParentActivityId,
			String ParentTaskId,
			String PersonRelationId,
			String TemporaryCustomerId,
			String TemporaryCustomerPersonRelationsId,
			String Title,
			String ToDateTime){

		ContentValues values = new ContentValues();

		values.put("Id",Id);
		values.put("CustomerId",CustomerId);
		values.put("Description",Description);
		values.put("FromDateTime",FromDateTime);
		values.put("IsAm",IsAm);
		values.put("ParentActivityId",ParentActivityId);
		values.put("ParentTaskId",ParentTaskId);
		values.put("PersonRelationId",PersonRelationId);
		values.put("TemporaryCustomerId",TemporaryCustomerId);
		values.put("TemporaryCustomerPersonRelationsId",TemporaryCustomerPersonRelationsId);
		values.put("Title",Title);
		values.put("ToDateTime",ToDateTime);
		values.put("IsDeleted",0);

		mydb.insert("tasks", null, values);
	}

	public void UpdateTasks(String Id,
			String CustomerId,
			String Description,
			String FromDateTime,
			String IsAm,
			String ParentActivityId,
			String ParentTaskId,
			String PersonRelationId,
			String TemporaryCustomerId,
			String TemporaryCustomerPersonRelationsId,
			String Title,
			String ToDateTime){

		String strFilter = "Id = '"+Id+"'";		
		ContentValues values = new ContentValues();

		values.put("CustomerId",CustomerId);
		values.put("Description",Description);
		values.put("FromDateTime",FromDateTime);
		values.put("IsAm",IsAm);
		values.put("ParentActivityId",ParentActivityId);
		values.put("ParentTaskId",ParentTaskId);
		values.put("PersonRelationId",PersonRelationId);
		values.put("TemporaryCustomerId",TemporaryCustomerId);
		values.put("TemporaryCustomerPersonRelationsId",TemporaryCustomerPersonRelationsId);
		values.put("Title",Title);
		values.put("ToDateTime",ToDateTime);
		values.put("IsDeleted",0);

		long ID = mydb.update("tasks", values, strFilter,null);
	}

	public void DeleteTasks(String Id){

		String strFilter = "Id = '"+Id+"'";		
		ContentValues values = new ContentValues();
		values.put("IsDeleted",1);

		long ID = mydb.update("tasks", values, strFilter,null);
	}

	public void InsertTasksProducts(String TaskGUID,String ProductGUID){
		ContentValues values = new ContentValues();
		values.put("TaskGUID", TaskGUID);
		values.put("ProductGUID", ProductGUID);
		values.put("IsDeleted",0);
		mydb.insert("TasksProducts", null, values);
	}

//	public void InsertActivitiesProducts(String ActivityGUID,String ProductGUID){
//		ContentValues values = new ContentValues();
//		values.put("TaskGUID", ActivityGUID);
//		values.put("ProductGUID", ProductGUID);
//		values.put("IsDeleted",0);
//		mydb.insert("ActivitiesProducts", null, values);
//	}
//
//	public void DeleteActivitiesProducts(String ActivityGUID,String ProductGUID){
//		
//		String strFilter = "ActivityGUID =" +"'"+ ActivityGUID+"' and ProductGUID ="+"'"+ ProductGUID+"'";
//
//		ContentValues values = new ContentValues();
//		values.put("TaskGUID", ActivityGUID);
//		values.put("ProductGUID", ProductGUID);
//		values.put("IsDeleted",0);
//		mydb.insert("ActivitiesProducts", null, values);
//	}
	
	public void DeleteTasksProducts(String TaskGUID,String ProductGUID){

		String strFilter = "TaskGUID =" +"'"+ TaskGUID+"' and ProductGUID ="+"'"+ ProductGUID+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted",1);
		long ID = mydb.update("TasksProducts", values, strFilter,null);

	}

	public void InsertTasksServices(String TaskGUID,String ServiceGUID){
		ContentValues values = new ContentValues();
		values.put("TaskGUID", TaskGUID);
		values.put("ServiceGUID", ServiceGUID);
		values.put("IsDeleted",0);
		mydb.insert("TasksServices", null, values);
	}

	public void DeleteTasksServices(String TaskGUID,String ServiceGUID){

		String strFilter = "TaskGUID =" +"'"+ TaskGUID+"' and ServiceGUID ="+"'"+ ServiceGUID+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted",1);
		long ID = mydb.update("TasksServices", values, strFilter,null);

	}

	public Cursor GetTasks (String date){

		Cursor cu= mydb.rawQuery("select * from tasks where IsDeleted = '0' and FromDateTime like '"+date+" %'", null); 
		return cu ;

		// Id CustomerId Description FromDateTime IsAm ParentActivityId ParentTaskId PersonRelationId TemporaryCustomerId
		// TemporaryCustomerPersonRelationsId Title ToDateTime IsDeleted
	}

	public Cursor GetTaskByID (String id){

		Cursor cu= mydb.rawQuery("select * from tasks where Id = '"+id+"'", null); 
		return cu ;

		// Id CustomerId Description FromDateTime IsAm ParentActivityId ParentTaskId PersonRelationId TemporaryCustomerId
		// TemporaryCustomerPersonRelationsId Title ToDateTime IsDeleted
	}

	public Cursor GetTaskProductsByID (String id){
		Cursor cu= mydb.
				rawQuery("select productname from tasksproducts t inner join products p on t.[ProductGUID] = p.[productGUID] where t.[TaskGUID] = '"+id+"'", null); 
		return cu ;

	}

	public Cursor GetTaskDetailsByID (String id){
		Cursor cu= mydb.
				rawQuery("select Description from tasks where id = '"+id+"'", null); 
		return cu ;

	}




	public void InsertActivities(
			String Id,
			String Title,

			String ActivityStatusId,
			String CustomerId,
			String Description,
			String FromDateTime,
			String HasNextTask,
			String ParentActivityId,
			String PersonRelationId,
			String TaskId,
			String TemporaryCustomerId,
			String ToDateTime
			) {

		ContentValues values = new ContentValues();

		values.put("Id",Id);
		values.put("Title",Title);

		values.put("ActivityStatusId",ActivityStatusId);
		values.put("CustomerId",CustomerId);
		values.put("Description",Description);
		values.put("FromDateTime",FromDateTime);
		values.put("HasNextTask",HasNextTask);
		values.put("ParentActivityId",ParentActivityId);
		values.put("PersonRelationId",PersonRelationId);
		values.put("TaskId",TaskId);
		values.put("TemporaryCustomerId",TemporaryCustomerId);
		values.put("ToDateTime",ToDateTime);
		values.put("IsDeleted",0);


		mydb.insert("activities", null, values);

	}

	public void UpdateActivities(
			String Id,
			String Title,
			String ActivityStatusId,
			String CustomerId,
			String Description,
			String FromDateTime,
			String HasNextTask,
			String ParentActivityId,
			String PersonRelationId,
			String TaskId,
			String TemporaryCustomerId,
			String ToDateTime
			) {

		String strFilter = "Id = '"+Id+"'";		
		ContentValues values = new ContentValues();
		
		values.put("Title",Title);
		values.put("ActivityStatusId",ActivityStatusId);
		values.put("CustomerId",CustomerId);
		values.put("Description",Description);
		values.put("FromDateTime",FromDateTime);
		values.put("HasNextTask",HasNextTask);
		values.put("ParentActivityId",ParentActivityId);
		values.put("PersonRelationId",PersonRelationId);
		values.put("TaskId",TaskId);
		values.put("TemporaryCustomerId",TemporaryCustomerId);
		values.put("ToDateTime",ToDateTime);
		values.put("IsDeleted",0);


		long ID = mydb.update("activities", values, strFilter,null);

	}

	public void DeleteActivities(String Id) {

		String strFilter = "Id = '"+Id+"'";		
		ContentValues values = new ContentValues();

		values.put("IsDeleted",1);

		long ID = mydb.update("activities", values, strFilter,null);

	}

	public Cursor getActivity (String date){

		Cursor cu= mydb.rawQuery("select a.[Id]  , a.[Title] ,a.[FromDateTime],a.[ToDateTime] from activities a where IsDeleted = '0' and FromDateTime like '"+date+" %'", null); 
		return cu ;
	}

	public Cursor GetActivityByID (String id){

		Cursor cu= mydb.rawQuery("select * from activities where Id = '"+id+"'", null); 
		return cu ;

	}
	



}
