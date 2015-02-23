package GetActivitiesPack;

import java.util.HashMap;
import java.util.Map;

public class Deleted {

	private String Id;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 * The Id
	 */
	public void setId(String Id) {
		this.Id = Id;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}