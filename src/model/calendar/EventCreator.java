package model.calendar;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import JsonClasses.Users;
import model.Model;
import model.QueryBuild.QueryBuilder;

/**
 * Created by jesperbruun on 10/10/14.
 */
public class EventCreator extends Model {
    ArrayList<Event> events = new ArrayList<Event>();
    ArrayList<Users> users = new ArrayList<Users>();
    
    public ArrayList<Event> getEvents() {
    	QueryBuilder qb = new QueryBuilder();
    	try {
			ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
			while (rs.next())
			{
				//String values from SQL database (must be created)
				int id = rs.getInt("id");
				String type = rs.getString("type");
				String ai = rs.getString("activityid");
				String location = rs.getString("location");
				String cb = rs.getString("createdby");
				Date startDate = rs.getDate("start");
				Time startTime = rs.getTime("start");			
				Date endDate = rs.getDate("end");
				Time endTime = rs.getTime("end");				
				String desc = rs.getString("description");
				int calendarId = rs.getInt("calendarid");
				
				
				
				String stringEventID = String.valueOf(id);
				String stringType = String.valueOf(type);
				String StringaId = String.valueOf(ai);
				String stringLocation = String.valueOf(location);				
				String StringCb = String.valueOf(cb);
					
				
				String stringStartDate = String.valueOf(startDate);
				String stringStartTime = String.valueOf(startTime);				
				String stringEndDate = String.valueOf(endDate);
				String stringEndTime = String.valueOf(endTime);
				
				ArrayList<String> alStart = new ArrayList<String>();
				alStart.add(stringStartDate + "" + stringStartTime);
				
				ArrayList<String> alEnd = new ArrayList<String>();
				alEnd.add(stringEndDate + "" + stringEndTime);
		
				String Stringdesc = String.valueOf(desc);
				
				String Stringcalid = String.valueOf(calendarId);
						
				events.add(new Event(stringEventID,stringType,StringaId,stringLocation,StringCb,alStart,alEnd,Stringdesc,Stringcalid));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return events;
    }

    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }
		
    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }
    
    // Konverterer array events til en tekst streng
    @Override
    public String toString() {
        return Arrays.toString(events.toArray());
    }
    public String toStringUsers() {
        return Arrays.toString(users.toArray());
    }
}