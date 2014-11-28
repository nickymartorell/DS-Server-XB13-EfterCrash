public class CalendarInfo implements java.io.Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUId = 1L;
		private String overallId = "createCourse";
		private String activityId;
		private  String eventId;
		private  String type;
		private  String title;
		private  String description;
		private  String start;
		private  String end;
		private  String location;
		
		
		//Getters and setters
		public String getActivityId() {
			return activityId;
		}
		public  void setActivityId(String activityId) {
			this.activityId = activityId;
		}
		public  String getEventId() {
			return eventId;
		}
		public  void setEventId(String eventId) {
			this.eventId = eventId;
		}
		public  String getType() {
			return type;
		}
		public  void setType(String type) {
			this.type = type;
		}
		public  String getTitle() {
			return title;
		}
		public  void setTitle(String title) {
			this.title = title;
		}
		public  String getDescription() {
			return description;
		}
		public  void setDescription(String description) {
			this.description = description;
		}
		public  String getStart() {
			return start;
		}
		public  void setStart(String start) {
			this.start = start;
		}
		public  String getEnd() {
			return end;
		}
		public  void setEnd(String end) {
			this.end = end;
		}
		public  String getLocation() {
			return location;
		}
		public  void setLocation(String location) {
		this.location = location;
		}
		public String getOverallId() {
			return overallId;
		}
		public void setOverallId(String overallId) {
			this.overallId = overallId;
		}
}

