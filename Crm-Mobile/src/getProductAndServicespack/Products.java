package getProductAndServicespack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Products {

private List<getProductAndServicespack.Deleted> Deleted = new ArrayList<getProductAndServicespack.Deleted>();
private List<getProductAndServicespack.Inserted> Inserted = new ArrayList<getProductAndServicespack.Inserted>();
private List<getProductAndServicespack.Updated> Updated = new ArrayList<getProductAndServicespack.Updated>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The Deleted
*/
public List<getProductAndServicespack.Deleted> getDeleted() {
return Deleted;
}

/**
* 
* @param Deleted
* The Deleted
*/
public void setDeleted(List<getProductAndServicespack.Deleted> Deleted) {
this.Deleted = Deleted;
}

/**
* 
* @return
* The Inserted
*/
public List<getProductAndServicespack.Inserted> getInserted() {
return Inserted;
}

/**
* 
* @param Inserted
* The Inserted
*/
public void setInserted(List<getProductAndServicespack.Inserted> Inserted) {
this.Inserted = Inserted;
}

/**
* 
* @return
* The Updated
*/
public List<getProductAndServicespack.Updated> getUpdated() {
return Updated;
}

/**
* 
* @param Updated
* The Updated
*/
public void setUpdated(List<getProductAndServicespack.Updated> Updated) {
this.Updated = Updated;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}