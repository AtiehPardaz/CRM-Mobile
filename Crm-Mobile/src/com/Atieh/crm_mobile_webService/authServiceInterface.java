package com.Atieh.crm_mobile_webService;

import java.util.Map;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface authServiceInterface {

		@GET("/AuthenticateUser")
		public authJSONClass authorize(@QueryMap Map <String , String> querymap);		
}


