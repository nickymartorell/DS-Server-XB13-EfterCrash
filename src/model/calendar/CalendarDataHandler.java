package model.calendar;

import java.sql.SQLException;

import model.QueryBuild.QueryBuilder;

public class CalendarDataHandler {
	
	QueryBuilder qb = new QueryBuilder();
	GetCalendarData gc = new GetCalendarData();
	EventCreator ec = new EventCreator();

	
	public void export2Database(int userId, String cbsUserId) {
		
		//long startTime = System.nanoTime();
		
		for ( int i = 0; i < ec.getEvents().size(); i++) { 
			
			//String cbs_event_id = ec.getEvents().get(i).getEventId();
				
		String[] fields = {"event_id", " createdby", "start", "end", "title", "description", "location", "calendarId", "active"};
		String[] values = {ec.getEvents().get(i).getEventId(), String.valueOf(userId)};
		
		try {
			qb.insertInto("events", fields).values(values).Execute();
			
			System.out.println("Event created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
}
	
