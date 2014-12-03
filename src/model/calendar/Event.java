package model.calendar;
import java.util.ArrayList;
import java.util.Date;

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
    private String description;
    private String calendarid;

	private ArrayList<String> start;
    private ArrayList<String> end;
	private String name;
    private transient Date dateStart;
    private transient Date dateEnd;
    
    private String strDateStart;
    private String strDateEnd;

    private String text;
    
//  private ArrayList<Note> noter;
//  private boolean customevent;

  // Settere og gettere for Event objektet 
    
    public Event(){  	
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
    public String getStrDateStart() {
        return strDateStart;
    }
    public void setStrDateStart(String strDateStart) {
        this.strDateStart = strDateStart;
    }
    public String getStrDateEnd() {
        return strDateEnd;
    }
    public void setStrDateEnd(String strDateEnd) {
        this.strDateEnd = strDateEnd;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
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
    public String getCalendarid() {
		return calendarid;
	}
	public void setCalendarid(String calendarid) {
		this.calendarid = calendarid;
	}
	public Event(String eventId,String type,String activityId,String location, String createdby,
			 ArrayList<String> start,
			ArrayList<String> end,String description,String calendarid) {
		super();
		//this.name = name;
		this.id = eventId;
		this.type = type;
		this.activityid = activityId;	
		this.location = location;		
		this.createdby = createdby;		
		this.start = start;
		this.end = end;
		this.description = description;
		this.calendarid = calendarid;
	}
}
