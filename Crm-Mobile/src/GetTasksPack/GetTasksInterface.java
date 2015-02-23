package GetTasksPack;

import retrofit.http.GET;
import retrofit.http.Query;

public interface GetTasksInterface {
	@GET("/GetTasks")
	public GetTasks gettasks(@Query("token") String token);
}
