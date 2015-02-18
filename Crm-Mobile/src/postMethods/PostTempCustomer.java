package postMethods;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface PostTempCustomer {

	@GET("/SendTemporaryCustomer")
	public void postCustomer(@QueryMap Map<String, String> queryMap , Callback<String> callback);
}