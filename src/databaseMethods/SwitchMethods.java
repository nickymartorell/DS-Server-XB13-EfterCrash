package databaseMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import JsonClasses.Quote;
import JsonClasses.getEvents;
import JsonClasses.getForecast;
import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import model.calendar.Event;

public class SwitchMethods extends Model
{
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	Gson gson = new Gson();
	Quote qotd = new Quote();
	getEvents getEv = new getEvents();
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calenderName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */
	
	//VIRKER
	public String getQuote() throws SQLException {
		qotd = new Quote();
		ResultSet rs;
		String strReturn = "";	
		//qm.refreshQuote();
		qm.saveQuote();
		//qm.getQuote();
		rs = qb.selectFrom("quote").all().ExecuteQuery();	

		while(rs.next()){
			qotd.setQuote(rs.getString("quote"));
			qotd.setAuthor(rs.getString("author"));
			qotd.setTopic(rs.getString("topic"));		
		}
		strReturn = gson.toJson(qotd);
		return strReturn;		
	}
	
	//TILFOEJ NY USER
	//VIRKER
	public boolean newUser(String eMail, String password) throws SQLException 
	{
		String[] field = {"email", "active", "password","admin"};
		String[] values = {eMail, "1", password, "0"};
		qb.insertInto("users", field).values(values).Execute();
		return true;
	}
	
	//VIRKER
	public boolean deleteUser(String eMail) throws SQLException {
		
		String [] keys = {"active"};
		String [] values = {"0"};	
		qb.update("users", keys, values).where("email", "=", eMail).Execute();	
		System.out.println("Succesfully deactivated: "+ eMail);
		return true;
	}	
	
	//VIRKER
	public boolean activateUser(String eMail) throws SQLException {
		
		String [] keys = {"active"};
		String [] values = {"1"};	
		qb.update("users", keys, values).where("email", "=", eMail).Execute();	
		System.out.println("Succesfully activated: "+ eMail);
		return true;
	}
	
	//VIRKER
	public String createNewCalendar (String Name, String CreatedBy, String privatePublic) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		if(authenticateNewCalendar(Name) == false)
		{
			addNewCalendar(Name, CreatedBy, privatePublic);
			stringToBeReturned = "The new calender has been created!";
		}
		else
		{
			stringToBeReturned = "The new calender has not been created!";
		}	
		return stringToBeReturned;
	}

	//VIRKER
	public boolean authenticateNewCalendar(String Name) throws SQLException
	{
		getConn();
		boolean authenticate = false;		
		resultSet= qb.selectFrom("calendar").where("Name", "=", Name).ExecuteQuery();
			
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	//VIRKER
	public void addNewCalendar (String newCalendarName, String userName, String publicOrPrivate) throws SQLException
	{
		String [] keys = {"Name","Active","CreatedBy","PrivatePublic"};
		String [] values = {newCalendarName,"1",userName, publicOrPrivate};
		qb.insertInto("calendar", keys).values(values).Execute();
	}

	//TESTER
	public String removeCalendar (String CreatedBy, String Name) throws SQLException
	{
		String stringToBeReturned = "";
		String usernameOfCreator ="";
		String calendarExists = "";
		resultSet = qb.selectFrom("calender").where("Name", "=", Name).ExecuteQuery();
		while(resultSet.next())
		{
			calendarExists = resultSet.toString();
		}
		if(!calendarExists.equals(""))
		{
			String [] value = {"CreatedBy"};
			resultSet = qb.selectFrom(value, "calendar").where("CreatedBy", "=", CreatedBy).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if(!usernameOfCreator.equals(CreatedBy))
			{
				stringToBeReturned = "Only the creator of the calender is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"0"};
				qb.update("calendar", keys, values).where("Name", "=", Name).Execute();
				stringToBeReturned = "Calender has been set inactive";
			}
			stringToBeReturned = resultSet.toString();
		}
		else
		{
			stringToBeReturned = "The calender you are trying to delete, does not exists.";
		}
		return stringToBeReturned;
	}
	
	//SAET DEN HER LIGE MED USERID
	public String getCalendar(String userName) throws SQLException
	{
		String stringToBeReturned ="";

		resultSet = qb.selectFrom("Calendar").where("Name", "=", userName).ExecuteQuery();
		
		while(resultSet.next())
		{
			stringToBeReturned += resultSet.toString();
		}
		return stringToBeReturned;
	}
	
	public String getAllEvents(String type) {
    	try {
    		qb = new QueryBuilder();
    		gson = new Gson(); 	
    		getEv = new getEvents();
    		ResultSet rs = qb.selectFrom("events").where("type", "=", type).ExecuteQuery();
    	//	List<Event> eventList = new ArrayList<>();     		
    		String eventList = "";  		
    		while(rs.next()){
    			//Event event = new Event();
//    			event.setActivityid(rs.getString("id"));
    			getEv.setType(rs.getString("type"));
//    			event.setActivityid(rs.getString("activityid"));
    			getEv.setLocation(rs.getString("location"));
//    			event.setCreatedby(rs.getString("createdby"));
//    			event.setDateStart(rs.getDate("start"));
//    			event.setStrDateStart(rs.getString("start"));
//    			event.setStrDateEnd(rs.getString("end")); 			
    		    eventList += rs.toString();
    		}
//    		rs.close();
    		eventList = gson.toJson(getEv);
    		System.out.println("FRA CLIENT GET EVENTS: "+getEv+"HER ER EVENTLISTEN"+eventList);
    		return gson.toJson(eventList);
    		
    	} catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
	
	 public ArrayList<Event> getEvents() {
	    	try {
	    		qb = new QueryBuilder();
	    		gson = new Gson();
	    		    		
	    		ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
	    		ArrayList<Event> eventList = new ArrayList<Event>();  
	    		
	    		while(rs.next()){
	    			Event event = new Event();
	    			event.setActivityid(rs.getString("id"));
	    			event.setType(rs.getString("type"));
	    			
	    			//HUSK RET 2X ACTIVITY ID
	    			event.setActivityid(rs.getString("activityid"));
	    			event.setLocation(rs.getString("location"));
	    			//event.setCreatedby(rs.getString("createdby"));
	    			//event.setDateStart(rs.getDate("start"));
	    			event.setStrDateStart(rs.getString("start"));
	    			event.setStrDateEnd(rs.getString("end"));			
	    		    eventList.add(event);
	    		}
	    		rs.close();
	    		return eventList;
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    } 
	 //VIRKER
	 public ArrayList<getEvents> getCustomEvents() {
	    	try {
	    		qb = new QueryBuilder();
	    		gson = new Gson();
	    	
	    		ResultSet rs = qb.selectFrom("events").where("customevent", "=", "1").ExecuteQuery();
	    		ArrayList<getEvents> eventList = new ArrayList<getEvents>();  	    	
	    		while(rs.next()){
	    			getEvents cusevent = new getEvents();
	    			cusevent.setId(rs.getString("id"));
	    			cusevent.setType(rs.getString("type"));    		
	    			cusevent.setLocation(rs.getString("location"));
	    			cusevent.setStart(rs.getString("start"));
	    			cusevent.setEnd(rs.getString("end"));
	    			cusevent.setCreatedby(rs.getString("createdby"));
	    			cusevent.setCustomevent(rs.getString("customevent"));
	    		    eventList.add(cusevent);
	    		}
	    		rs.close();
	    		return eventList;
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    } 
	 //VIRKER
	 public ArrayList<getForecast> getForecast() {
	    	try {
	    		qb = new QueryBuilder();
	    		gson = new Gson();
	    	
	    		ResultSet rs = qb.selectFrom("forecast").all().ExecuteQuery();
	    		ArrayList<getForecast> fca = new ArrayList<getForecast>();  	    	
	    		while(rs.next()){
	    			getForecast gfc = new getForecast();
	    			gfc.setDate(rs.getString("date"));
	    			gfc.setCels(rs.getString("des"));
	    			gfc.setDesc(rs.getString("cels"));
	    			fca.add(gfc);
	    			System.out.println(gfc);
	    		}
	    		rs.close();
	    		return fca;
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    } 

	//VIRKER MEN BRUGER IKKE ENDNU
	public String GetNote(String eventId) throws SQLException
	{
		String stringToBeReturned ="";
		
		resultSet = qb.selectFrom("notes").where("eventId", "=", eventId).ExecuteQuery();
		
		while(resultSet.next())
		{
			stringToBeReturned += resultSet.toString();
		}
		return stringToBeReturned;
	}

	//AUTHENTICATER
	// Metoden faar email og password fra switchen (udtrukket fra en json) samt en boolean der skal saettes til true hvis det er serveren der logger paa, og false hvis det er en klient
	/**
	 * Allows the client to log in
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @return
	 * @throws Exception
	 */
	//VIRKER
	public String authenticate(String email, String password, boolean isAdmin) throws Exception {

		String[] keys = {"userid", "email", "active", "password"};

		qb = new QueryBuilder();

		// Henter info om bruger fra database via querybuilder
		resultSet = qb.selectFrom(keys, "users").where("email", "=", email).ExecuteQuery();

		// Hvis en bruger med forespurgt email findes
		if (resultSet.next()){

			// Hvis brugeren er aktiv
			if(resultSet.getInt("active")==1)
			{					
				// Hvis passwords matcher
				if(resultSet.getString("password").equals(password))
				{
					return resultSet.getString("userid");
				} else {
					return "3"; // returnerer fejlkoden "3" hvis password ikke matcher
				}
			} else {
				return "2"; // returnerer fejlkoden "2" hvis bruger er sat som inaktiv
			}
		} else {
			return "1"; // returnerer fejlkoden "1" hvis email ikke findes
		}
	}

	//EVENT TING
	//VIRKER
	public String createEvents(String location, String  cb, String  start, String  end, String  description, String  type, String customevent,String aktiv) throws SQLException{
		String stringToBeReturned = "";
		testConnection();
//		String active = "1";
		System.out.println("MORS LUDER"+aktiv);
		if(autenticateNewEvent(description) ==false){
			addNewEvent(location, cb, start, start, description, type, customevent,aktiv);
			stringToBeReturned = "The new event has been created!!";
		}
		else{
			stringToBeReturned = "The new event has not been created :( ";
		}
		return stringToBeReturned;		
	}
	//VIRKER
	public String addNewEvent(String location,String  cb,String  start,String  end,String  description,String  type, String customevent,String aktiv) throws SQLException {
		String hehe = "";
		String [] keys = {"location", "createdby", "type", "description", "start","end","customevent","aktiv"};
		String [] values = {location,cb,type,description, start, end,customevent,aktiv};
		qb.insertInto("events", keys).values(values).Execute();
		return hehe;
	}
	//VIRKER
	private boolean autenticateNewEvent(String desription) throws SQLException {
		getConn();
		boolean authenticate = false;
		resultSet = qb.selectFrom("events").where("description","=", desription).ExecuteQuery();
		
		while(resultSet.next()){
			authenticate = true;
		}	
		return authenticate;
	}
	//VIRKER
	public String removeEvent (String description) throws SQLException{
		String stringToBeReturned ="";
		String [] keys = {"aktiv"};
		String [] values ={"0"};
		System.out.println(description+"SÅDAN HER SER DESCRIPTION UD");
		qb.update("Events", keys, values).where("description", "=", description).Execute();
		stringToBeReturned = "The event has been set inactive"; //overvej omformulering
		return stringToBeReturned;
	}
	
	
	//NOTE TING
	//denne her skal dobbelttjekkes!!!!! 
	public String removeNote (String noteID, String UserName) throws SQLException{
		String stringToBeReturned ="";
		String userNameOfCreator ="";
		String NoteExists = "";
		resultSet = qb.selectFrom("notes").where("noteID", "=", noteID).ExecuteQuery();
		
		while(resultSet.next()){
			NoteExists = resultSet.toString();
			
			if(!NoteExists.equals("")){
				String [] value = {"CreatedBy"};
				resultSet = qb.selectFrom(value, "notes").where("noteID", "=", noteID).ExecuteQuery();
				while(resultSet.next()){
					userNameOfCreator = resultSet.toString();
					System.out.println(userNameOfCreator);
				}
				if(!userNameOfCreator.equals(UserName)){
					stringToBeReturned = "Only the creator of the note is able to delete it!";
				}
				else{
					String [] keys = {"Active"};
					String [] values ={"2"};
					qb.update("notes", keys, values).where("noteID", "=", noteID).Execute();
					stringToBeReturned = "The note has been deleted"; //overvej omformulering
				}
				stringToBeReturned = resultSet.toString();
			}
			else{
				stringToBeReturned = "The note you are trying to delete does not exist!";
			}
		}	
		return stringToBeReturned;
	}
	
//	public String removeEvent (String description) throws SQLException{
//		String stringToBeReturned ="";
//		String userNameOfCreator ="";
//		String EventExists = "";
//		resultSet = qb.selectFrom("events").where("description", "=", description).ExecuteQuery();
//		
//		while(resultSet.next()){
//			EventExists = resultSet.toString();
//		}
//		if(!EventExists.equals("")){
//			String [] value = {"createdBy"};
//			resultSet = qb.selectFrom(value, "events").where("name", "=", name).ExecuteQuery();
//			while(resultSet.next()){
//				userNameOfCreator = resultSet.toString();
//				System.out.println(userNameOfCreator);
//			}
//			
//			if(!userNameOfCreator.equals(createdBy)){
//				stringToBeReturned = "Only the creator of the event is able to delete it!";
//			}
//			else{
//				String [] keys = {"Active"};
//				String [] values ={"2"};
//				qb.update("Events", keys, values).where("Title", "=", name).Execute();
//				stringToBeReturned = "The event has been set inactive"; //overvej omformulering
//			}
//			stringToBeReturned = resultSet.toString();
//		}
//		else{
//			stringToBeReturned = "The event you are trying to delete does not exist!";
//		}
//		return stringToBeReturned;
//	}
	}