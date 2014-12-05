package JsonClasses;

public class removeEvent implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallID = "";
	private String id;
	private String type;
	private String location;
	private String start;
	private String end;
	private String description;
	private String createdby;
	private String customevent;
	private String active;
	
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCustomevent() {
		return customevent;
	}
	public void setCustomevent(String customevent) {
		this.customevent = customevent;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}	
}