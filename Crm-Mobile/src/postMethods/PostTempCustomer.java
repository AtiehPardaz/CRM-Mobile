package postMethods;

import postCustomersPack.PostCustomers;
import GetCustomersPack.sendCustomer;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface PostTempCustomer {

	@Headers("Content-Type: application/json")
	@POST ("/SendTemporaryCustomersPost")
	public void postCustomer(@Body PostCustomers sc  , Callback<String> callback);
}
