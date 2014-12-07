package JsonClasses;

public class getCalendar implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallID = "getCalendar";
	private String Name;
	private String CreatedBy;
	private String publicOrPrivate;
	private String Active;
	private String calendarid;
	
	public getCalendar(){
		
	}
	
	public getCalendar(String Name, String CreatedBy, String publicOrPrivate) {
		super();
		//this.name = name;
		this.Name = Name;
		this.CreatedBy = CreatedBy;
		this.publicOrPrivate = publicOrPrivate;

	}
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
	public String getPublicOrPrivate() {
		return publicOrPrivate;
	}
	public void setPublicOrPrivate(String publicOrPrivate) {
		this.publicOrPrivate = publicOrPrivate;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

	public String getCalendarid() {
		return calendarid;
	}

	public void setCalendarid(String calendarid) {
		this.calendarid = calendarid;
	}

	
}
