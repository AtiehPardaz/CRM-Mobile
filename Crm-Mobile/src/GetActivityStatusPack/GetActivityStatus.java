
package GetActivityStatusPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetActivityStatus {

	private List<GetActivityStatusPack.Deleted> Deleted = new ArrayList<GetActivityStatusPack.Deleted>();
	private List<GetActivityStatusPack.Inserted> Inserted = new ArrayList<GetActivityStatusPack.Inserted>();
	private List<GetActivityStatusPack.Updated> Updated = new ArrayList<GetActivityStatusPack.Updated>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Deleted
	 */
	public List<GetActivityStatusPack.Deleted> getDeleted() {
		return Deleted;
	}

	/**
	 * 
	 * @param Deleted
	 * The Deleted
	 */
	public void setDeleted(List<GetActivityStatusPack.Deleted> Deleted) {
		this.Deleted = Deleted;
	}

	/**
	 * 
	 * @return
	 * The Inserted
	 */
	public List<GetActivityStatusPack.Inserted> getInserted() {
		return Inserted;
	}

	/**
	 * 
	 * @param Inserted
	 * The Inserted
	 */
	public void setInserted(List<GetActivityStatusPack.Inserted> Inserted) {
		this.Inserted = Inserted;
	}

	/**
	 * 
	 * @return
	 * The Updated
	 */
	public List<GetActivityStatusPack.Updated> getUpdated() {
		return Updated;
	}

	/**
	 * 
	 * @param Updated
	 * The Updated
	 */
	public void setUpdated(List<GetActivityStatusPack.Updated> Updated) {
		this.Updated = Updated;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}