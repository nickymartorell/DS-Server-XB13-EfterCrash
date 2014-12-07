package JsonClasses;

import java.util.ArrayList;

public class getEvents implements java.io.Serializable
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
	private String calendarid;
	private String aktiv;
	private ArrayList<String> startTime;
    private ArrayList<String> endTime;
	
	public getEvents(){
		
	}
	
	public getEvents(String eventId,String type,String activityId,String location, String createdby,
			 String start,
			String end,String description,String customevent) {
		super();
		//this.name = name;
		this.id = eventId;
		this.type = type;	
		this.location = location;		
		this.createdby = createdby;		
		this.start = start;
		this.end = end;
		this.description = description;
		this.customevent =customevent;
	}
	
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

	public String getAktiv() {
		return aktiv;
	}

	public void setAktiv(String aktiv) {
		this.aktiv = aktiv;
	}

	public String getCalendarid() {
		return calendarid;
	}

	public void setCalendarid(String calendarid) {
		this.calendarid = calendarid;
	}

	public ArrayList<String> getStartTime() {
		return startTime;
	}

	public void setStartTime(ArrayList<String> startTime) {
		this.startTime = startTime;
	}

	public ArrayList<String> getEndTime() {
		return endTime;
	}

	public void setEndTime(ArrayList<String> endTime) {
		this.endTime = endTime;
	}



}