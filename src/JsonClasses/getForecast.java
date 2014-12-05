package JsonClasses;

public class getForecast implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallID = "getForecast";
	private String date;
	private String desc;
	private String cels;
	
	public getForecast(){
		
	}
	public getForecast(String date, String desc, String cels) {
		super();
		this.date = date;
		this.desc = desc;
		this.cels = cels;
	}
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCels() {
		return cels;
	}
	public void setCels(String cels) {
		this.cels = cels;
	}	
}
