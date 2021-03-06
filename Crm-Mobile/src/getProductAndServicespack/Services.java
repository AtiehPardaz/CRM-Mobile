package GetProductAndServicespack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Services {

	private List<Deleted_> Deleted = new ArrayList<Deleted_>();
	private List<Inserted_> Inserted = new ArrayList<Inserted_>();
	private List<Updated_> Updated = new ArrayList<Updated_>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Deleted
	 */
	public List<Deleted_> getDeleted() {
		return Deleted;
	}

	/**
	 * 
	 * @param Deleted
	 * The Deleted
	 */
	public void setDeleted(List<Deleted_> Deleted) {
		this.Deleted = Deleted;
	}

	/**
	 * 
	 * @return
	 * The Inserted
	 */
	public List<Inserted_> getInserted() {
		return Inserted;
	}

	/**
	 * 
	 * @param Inserted
	 * The Inserted
	 */
	public void setInserted(List<Inserted_> Inserted) {
		this.Inserted = Inserted;
	}

	/**
	 * 
	 * @return
	 * The Updated
	 */
	public List<Updated_> getUpdated() {
		return Updated;
	}

	/**
	 * 
	 * @param Updated
	 * The Updated
	 */
	public void setUpdated(List<Updated_> Updated) {
		this.Updated = Updated;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}