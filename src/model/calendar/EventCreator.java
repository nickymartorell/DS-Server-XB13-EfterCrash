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
				String location = rs.getString("location");
					
				
				Date startDate = rs.getDate("start");
				Time startTime = rs.getTime("start");
				
				Date endDate = rs.getDate("end");
				Time endTime = rs.getTime("end");
								
				String stringEventID = String.valueOf(id);
				String stringType = String.valueOf(type);
				String stringLocation = String.valueOf(location);
		
				String stringStartDate = String.valueOf(startDate);
				String stringStartTime = String.valueOf(startTime);				
				String stringEndDate = String.valueOf(endDate);
				String stringEndTime = String.valueOf(endTime);
				
				ArrayList<String> alStart = new ArrayList<String>();
				alStart.add(stringStartDate + "" + stringStartTime);
				
				ArrayList<String> alEnd = new ArrayList<String>();
				alEnd.add(stringEndDate + "" + stringEndTime);
				
				
				System.out.println(String.valueOf(startDate.getTime()));
				
				events.add(new Event(stringEventID, stringEventID, stringType, stringType, stringLocation, stringLocation, alStart, alEnd));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return events;
    }

    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }

//    public ArrayList<Users> getUsers() {
//    
//    	QueryBuilder qb = new QueryBuilder();
//    	try {
//			ResultSet rs = qb.selectFrom("users").all().ExecuteQuery();
//			while (rs.next())
//			{
//				int id = rs.getInt("userid");
//				String email = rs.getString("email");
//				String password = rs.getString("password");		
//				boolean isAdmin = rs.getBoolean("admin");
//				boolean isActive = rs.getBoolean("active");	
//				
//				
//				String stringID = String.valueOf(id);
//				String stringEmail = String.valueOf(email);
//				String stringPassword = String.valueOf(password);
//				String StringIsAdmin = String.valueOf(isAdmin);
//				String StringActive = String.valueOf(isActive);
//				
//				users.add(new Users(stringID, stringEmail, stringPassword, StringIsAdmin, StringActive));				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	
//    	return users;
//    }
		
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
				
	
//    public static void main(String []args){
//    	EventCreator Hej = new EventCreator();
//    	
//    	Hej.getUsers();
//    	System.out.println(Hej.toStringUsers());
//    }
}