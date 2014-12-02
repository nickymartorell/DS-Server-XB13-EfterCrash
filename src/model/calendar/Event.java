package model.calendar;





import java.util.ArrayList;
import java.util.Date;

import model.note.Note;

/**
 * Created by jesperbruun on 10/10/14.
 * Til hver specifik event bliver de defineret saaledes.
 */
public class Event {
    private String id;
    private String type;
    private String location;
    private String activityid;
    private String createdby;
    private ArrayList<String> start;
    private ArrayList<String> end;
    
    private transient Date dateStart;
    private transient Date dateEnd;
    
    private String strDateStart;
    private String strDateEnd;

    private String text;
    
//  private ArrayList<Note> noter;
//  private boolean customevent;

  // Settere og gettere for Event objektet 
    
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    } 
    public void setStart(ArrayList<String> start){
        this.start = start;
    }
    public ArrayList<String> getStart(){
        return start;
    }  
    public void setDateStart(Date start){this.dateStart = start;}
    public Date getDateStart(){return dateStart;}

    public void setDateEnd(Date end){this.dateEnd = end;}
    public Date getDateEnd(){return dateEnd;}

    public void setText(String text){this.text = text;}
    public String getText(){return text;}

    public void setEnd(ArrayList<String> end){
        this.end = end;
    }
    public ArrayList<String> getEnd(){
        return end;
    }
	public Event(String name, String activityId, String eventId, String type, String createdby,
			String location, ArrayList<String> start,
			ArrayList<String> end) {
		super();
		this.name = name;
		this.activityid = activityId;
		this.id = eventId;
		this.type = type;
		this.createdby = createdby;	
		this.location = location;
		this.start = start;
		this.end = end;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	
}
