package model.calendar;

import java.util.ArrayList;

/**
 * Created by jesperbruun on 10/10/14.
 * Til hver specifik event bliver de defineret saaledes.
 */
public class Event {
    private String activityId;
    private String eventId;
    private String type;
    private String title;
    private String description;
    private String location;
    private String createdby;
    private ArrayList<String> start;
    private ArrayList<String> end;

    // Settere og gettere for Event objektet 
    public void setActivityId(String activityId){
        this.activityId = activityId;
    }
    public String getActivityId(){
        return activityId;
    }

    public void setEventId(String eventId){
        this.eventId = eventId;
    }
    public String getEventId(){
        return eventId;
    }

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public void setCreatedby(String createdby){
        this.createdby = createdby;
    }
    public String getCreatedby(){
        return createdby;
    }
    
    public void setStart(ArrayList<String> start){
        this.start = start;
    }
    public ArrayList<String> getStart(){
        return start;
    }

    public void setEnd(ArrayList<String> end){
        this.end = end;
    }
    public ArrayList<String> getEnd(){
        return end;
    }
	public Event(String activityId, String eventId, String type, String title,
			String description, String location, String createdby, ArrayList<String> start,
			ArrayList<String> end) {
		super();
		this.activityId = activityId;
		this.eventId = eventId;
		this.type = type;
		this.title = title;
		this.description = description;
		this.location = location;
		this.createdby = createdby;
		this.start = start;
		this.end = end;
	}
	
	
}
