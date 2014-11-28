package JsonClasses;

public class getEvents {
	
	
	private String userName;
	private String overallID = "getEvents";
	private String Title;
	private String Type;
	private String Description;
	private String Location;
	private String Createdby;
	private String ActivityID;
	
//getters and setters
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getOverallID(){
		return overallID;
	}
	
	public void setOverallID(String overallID){
		this.overallID = overallID;
	}
	
	public String getTitle(){
		return Title;
	}
	public void setTitle(String Title){
		this.Title = Title;
	}
	
	public String getType(){
		return Type;
	}
	
	public void setType(String type){
	this.Type = type;
	}
	
	public String getDescription(){
		return Description;
	}
	
	public void setDescription(String description){
		this.Description = description;
	}
	
	public String getLocation(){
		return Location;
	}
	
	public void setLocation(String location){
		this.Location = location;
	}
	
	public String Createdby(){
		return Createdby;
	}
	
	public void setCreatedby(String createdby){
		this.Createdby = createdby;
	}
	
	public String ActivityID(){
		return ActivityID;
	}
	
	public void setActivityID(String activityID){
		this.ActivityID = activityID;
	}
}
