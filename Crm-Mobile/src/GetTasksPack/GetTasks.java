
package GetTasksPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetTasks {

	private List<GetTasksPack.Deleted> Deleted = new ArrayList<GetTasksPack.Deleted>();
	private List<GetTasksPack.Inserted> Inserted = new ArrayList<GetTasksPack.Inserted>();
	private List<GetTasksPack.Updated> Updated = new ArrayList<GetTasksPack.Updated>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Deleted
	 */
	public List<GetTasksPack.Deleted> getDeleted() {
		return Deleted;
	}

	/**
	 * 
	 * @param Deleted
	 * The Deleted
	 */
	public void setDeleted(List<GetTasksPack.Deleted> Deleted) {
		this.Deleted = Deleted;
	}

	/**
	 * 
	 * @return
	 * The Inserted
	 */
	public List<GetTasksPack.Inserted> getInserted() {
		return Inserted;
	}

	/**
	 * 
	 * @param Inserted
	 * The Inserted
	 */
	public void setInserted(List<GetTasksPack.Inserted> Inserted) {
		this.Inserted = Inserted;
	}

	/**
	 * 
	 * @return
	 * The Updated
	 */
	public List<GetTasksPack.Updated> getUpdated() {
		return Updated;
	}

	/**
	 * 
	 * @param Updated
	 * The Updated
	 */
	public void setUpdated(List<GetTasksPack.Updated> Updated) {
		this.Updated = Updated;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}