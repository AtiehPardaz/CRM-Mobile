package GetProductAndServicespack;


import retrofit.http.GET;
import retrofit.http.Query;

public interface GetProductAndServicesInterface {

		@GET("/GetProductsAndServices")
		public GetProductsAndServices getps(@Query("token") String token);

}
