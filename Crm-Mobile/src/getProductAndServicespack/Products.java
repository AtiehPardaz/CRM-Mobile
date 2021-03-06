package GetProductAndServicespack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Products {

private List<GetProductAndServicespack.Deleted> Deleted = new ArrayList<GetProductAndServicespack.Deleted>();
private List<GetProductAndServicespack.Inserted> Inserted = new ArrayList<GetProductAndServicespack.Inserted>();
private List<GetProductAndServicespack.Updated> Updated = new ArrayList<GetProductAndServicespack.Updated>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The Deleted
*/
public List<GetProductAndServicespack.Deleted> getDeleted() {
return Deleted;
}

/**
* 
* @param Deleted
* The Deleted
*/
public void setDeleted(List<GetProductAndServicespack.Deleted> Deleted) {
this.Deleted = Deleted;
}

/**
* 
* @return
* The Inserted
*/
public List<GetProductAndServicespack.Inserted> getInserted() {
return Inserted;
}

/**
* 
* @param Inserted
* The Inserted
*/
public void setInserted(List<GetProductAndServicespack.Inserted> Inserted) {
this.Inserted = Inserted;
}

/**
* 
* @return
* The Updated
*/
public List<GetProductAndServicespack.Updated> getUpdated() {
return Updated;
}

/**
* 
* @param Updated
* The Updated
*/
public void setUpdated(List<GetProductAndServicespack.Updated> Updated) {
this.Updated = Updated;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}