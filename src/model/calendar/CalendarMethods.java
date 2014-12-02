package model.calendar;




import model.calendar.EventCreator;
import model.Forecast.ForecastArray;
import model.Forecast.ForecastTest;
import model.QueryBuild.QueryBuilder;
import JsonClasses.Users;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.sun.rowset.CachedRowSetImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Model;

/**
 * Created by jesperbruun on 13/10/14.
 */

public class CalendarMethods extends Model {
	
	
	   private static Gson gson;
	   private static QueryBuilder qb;
	 
	

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    
    public void export2Database () {
    	try {
    		String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserId.getUserId() + "/" + EncryptUserId.getKey() + ".json");
    		gson = new Gson();
            EventCreator events = gson.fromJson(json, EventCreator.class);
            qb = new QueryBuilder();
            
            //Field i data
            String[] fields = {"id","location","start","end","type","activityid","createdby"};
            
            //formatering af datetime
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
            //Simpelt svar på index 1 size 1 error med 120
            
            //formatere vores datetime korrekt
            for (int i = 0; i < 120 ; i++)             
            	{
               
               String startMonthStart = events.getEvents().get(i).getStart().get(1);
               String startMonthEnd = events.getEvents().get(i).getEnd().get(1);
               int realMonthStart = Integer.parseInt(startMonthStart)+1;
               int realMonthEnd = Integer.parseInt(startMonthEnd)+1;
               
               String begin =
            		   			events.getEvents().get(i).getStart().get(2) + "-" +
                        		realMonthStart + "-" +
                                events.getEvents().get(i).getStart().get(0) + " " +
                                events.getEvents().get(i).getStart().get(3) + ":" +
                                events.getEvents().get(i).getStart().get(4);

               Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(begin);
               String strStart = sdf.format(startDate);

              
                String end =
                        		events.getEvents().get(i).getEnd().get(2) + "-" +
                        		realMonthEnd + "-" +
                                events.getEvents().get(i).getEnd().get(0) + " " +
                                events.getEvents().get(i).getEnd().get(3) + ":" +
                                events.getEvents().get(i).getEnd().get(4);
                
                Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(end);
                String strEnd = sdf.format(endDate);


               String[] values = {  events.getEvents().get(i).getId(), //INT
            		   				events.getEvents().get(i).getLocation(), //VARCHAR
            		   				strStart, //DATETIME
            		   				strEnd, //DATETIME
            		   				events.getEvents().get(i).getCreatedby(),//VARCHAR
            		   				events.getEvents().get(i).getType(), //VARCHAR 
            		   				events.getEvents().get(i).getActivityid(), //VARCHAR
            		   				
            };
               qb.insertInto("events", fields).values(values).Execute();	             		       
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
    public String getEvents() {
    	try {
    		qb = new QueryBuilder();
    		gson = new Gson();
    		    		
    		ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
    		List<Event> eventList = new ArrayList<>();  
    		
    		while(rs.next()){
    			Event event = new Event();
    			event.setActivityid(rs.getString("id"));
    			event.setType(rs.getString("type"));
    			event.setActivityid(rs.getString("activityid"));
    			event.setLocation(rs.getString("location"));
    			event.setCreatedby(rs.getString("createdby"));
    		  //event.setDateStart(rs.getDate("start"));
    			event.setStrDateStart(rs.getString("start"));
    			event.setStrDateEnd(rs.getString("end"));
    			
    		    eventList.add(event);
    		}
    		rs.close();
    		return gson.toJson(eventList);
    		
    	} catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
    public String getUsers() {
		
    	try {
    		qb = new QueryBuilder();
    		gson = new Gson();
    		    		
    		ResultSet rs = qb.selectFrom("users").all().ExecuteQuery();
    		List<Users> userList = new ArrayList<>();      		
    		while(rs.next()){  			
    		Users user = new Users();
    		user.setUserId(rs.getInt("userid"));
    		user.setEmail(rs.getString("email"));
    		user.setPassword(rs.getString("password"));
    		user.setAdmin(rs.getBoolean("admin"));
    		user.setActive(rs.getBoolean("active"));
    		userList.add(user);    		
    		
    		return gson.toJson(userList);
    		}} catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
    
//public String getForecast() {
//		
//    	try {
//    		qb = new QueryBuilder();
//    		gson = new Gson();
//    		    		
//    		ResultSet rs = qb.selectFrom("forecast").all().ExecuteQuery();
//    		
//    		List<ForecastTest> fcast = new ArrayList<>();      		
//    		while(rs.next()){  			
//    		ForecastArray farray = new ForecastArray();
//    		farray.setDesc(rs.getString("des"));
//    		farray.setCelsius(rs.getString("cels"));
//    		farray.setDateDate(rs.getDate("date"));
//    		fcast.add(farray);    		
//    		
//    		
//    		return gson.toJson(fcast);
//    		}} catch (Exception e) {
//             e.printStackTrace();
//         }
//         return null;
//     }
    
    public static void main (String[]args) throws Exception{
  
    	new CalendarMethods().export2Database();
    }
}
