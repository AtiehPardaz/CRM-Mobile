package GetActivitiesPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetActivities {

	private List<GetActivitiesPack.Deleted> Deleted = new ArrayList<GetActivitiesPack.Deleted>();
	private List<GetActivitiesPack.Inserted> Inserted = new ArrayList<GetActivitiesPack.Inserted>();
	private List<GetActivitiesPack.Updated> Updated = new ArrayList<GetActivitiesPack.Updated>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Deleted
	 */
	public List<GetActivitiesPack.Deleted> getDeleted() {
		return Deleted;
	}

	/**
	 * 
	 * @param Deleted
	 * The Deleted
	 */
	public void setDeleted(List<GetActivitiesPack.Deleted> Deleted) {
		this.Deleted = Deleted;
	}

	/**
	 * 
	 * @return
	 * The Inserted
	 */
	public List<GetActivitiesPack.Inserted> getInserted() {
		return Inserted;
	}

	/**
	 * 
	 * @param Inserted
	 * The Inserted
	 */
	public void setInserted(List<GetActivitiesPack.Inserted> Inserted) {
		this.Inserted = Inserted;
	}

	/**
	 * 
	 * @return
	 * The Updated
	 */
	public List<GetActivitiesPack.Updated> getUpdated() {
		return Updated;
	}

	/**
	 * 
	 * @param Updated
	 * The Updated
	 */
	public void setUpdated(List<GetActivitiesPack.Updated> Updated) {
		this.Updated = Updated;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}