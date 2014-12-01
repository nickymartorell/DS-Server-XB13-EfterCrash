package JsonClasses;

public class createEvents {

	private String overallID = "createEvents";
	private int Type;
	private String Location;
	private String Createdby;
	private String startTime;
	private String endTime;
	private String Name;
	private String Text;
	private int active;
	
	public int getactive(){
		return active;
	}
	
	public void setactive(int active){
		this.active = active;
	}
	
	public String getendTime(){
		return endTime;
	}
	public void setendTime(String endTime){
		this.endTime = endTime;
	}
	
	public String getstartTime(){
		return startTime;
	}
	public void setstartTime(String startTime){
		this.startTime = startTime;
	}
	
	public String getoverallID(){
		return overallID;
	}
	public void setoverallID(String overallID){
		this.overallID = overallID;
	}
	
	public String getText(){
	return Text;
	}
	
	public void setText(String Text){
		this.Text = Text;
	}
	
	public String getName(){
		return Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	
	public int getType(){
		return Type;
	}
	
	public void setType(int Type){
		this.Type = Type;
	}
	
	
	public String getLocation(){
		return Location;
	}
	
	public void setLocation(String Location){
		this.Location = Location;
	}
	

	
	public String getCreatedby(){
		return Createdby;
	}
	
	public void setCreatedby(String Createdby){
		this.Createdby = Createdby;
}

}

