package JsonClasses;

public class getNote implements java.io.Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String overallID = "getNote";
private int noteID;
private String notes;
private String createdby;
private String eventid;
private String isActive;

public String getOverallID() {
	return overallID;
}
public void setOverallID(String overallID) {
	this.overallID = overallID;
}
public int getNoteID() {
	return noteID;
}
public void setNoteID(int noteID) {
	this.noteID = noteID;
}
public String getNotes() {
	return notes;
}
public void setNote(String notes) {
	this.notes = notes;
}
public String getCreatedby() {
	return createdby;
}
public void setCreatedby(String createdby) {
	this.createdby = createdby;
}
public String getEventid() {
	return eventid;
}
public void setEventid(String eventid) {
	this.eventid = eventid;
}
public String getIsActive() {
	return isActive;
}
public void setIsActive(String isActive) {
	this.isActive = isActive;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}


}
