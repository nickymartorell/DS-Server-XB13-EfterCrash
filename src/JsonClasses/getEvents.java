package JsonClasses;



public class getEvents implements java.io.Serializable {
//only necessary to have the things in this the methods searches for when looking for events
	private static final long serialVersionUID = -3269894274812108796L;//true??
	private String overallID = "getEvents";
	private String Createdby;
	
	//gettere og settere
	public String getOverallID(){
		return overallID;
	}
	
	public void setOverallID(String overallID){
		this.overallID = overallID;
	}
	

	
	public String Createdby(){
		return Createdby;
	}
	
	public void setCreatedby(String createdby){
		this.Createdby = createdby;
	}
	
	
}
