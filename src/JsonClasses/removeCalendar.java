package JsonClasses;

public class removeCalendar implements java.io.Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallID = "removeCalendar";
	private String Name;
	private String CreatedBy;
	private String Active;
	
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}

}
