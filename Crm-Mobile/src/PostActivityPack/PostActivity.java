package PostActivityPack;

import java.util.List;

public class PostActivity {

	List<GetActivitiesPack.Inserted> localActivities;
	String token ;
	
	public List<GetActivitiesPack.Inserted> getLocalActivities() {
		return localActivities;
	}
	public void setLocalActivities(List<GetActivitiesPack.Inserted> localActivities) {
		this.localActivities = localActivities;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
