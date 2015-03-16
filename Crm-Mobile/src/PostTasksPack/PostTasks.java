package PostTasksPack;

import java.util.List;

public class PostTasks {

	List<GetTasksPack.Inserted> localTasks ;
	String token;
	
	public List<GetTasksPack.Inserted> getLocalTasks() {
		return localTasks;
	}
	public void setLocalTasks(List<GetTasksPack.Inserted> localTasks) {
		this.localTasks = localTasks;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
