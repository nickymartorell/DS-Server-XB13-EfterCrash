package JsonClasses;

public class getNote {



private int noteID;
private String text;
private String dateTime;
private String eventID;
public String geteventID;


//getters and setters

public int getnoteID(){
return noteID;

}

public void setnoteID(int noteID){
	this.noteID = noteID;
}

public String gettext(){
	return text;
	
}

public void settext(String text){
	this.text = text;
}

public String getdateTime(){
	return dateTime;
}

public void dateTime(String dateTime){
	this.dateTime = dateTime;
}

public void setcreatedBy(String createdBy){
}

public void setisActive(int isActive){
}

@SuppressWarnings("unused")
private String geteventID(){
	return eventID;
}

public void seteventID(String eventID){
	this.eventID = eventID;
	
}

}
/*

public String getOverallID(){
	return overallID;
		}

public void setOverallID(String overallID){
	this.overallID = overallID;
}

public String getCalendarName(){
	return calendarName;
}

public void setCalendarName(String calendarName){
	this.calendarName = calendarName;
	}

public String getUserName(){
	return userName;
}

public void setUserName(String userName){
	this.userName = userName;
}*/