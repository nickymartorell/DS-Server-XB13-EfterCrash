package databaseMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import JsonClasses.Quote;
import JsonClasses.getEvents;
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
	
	public String getQuote() throws SQLException {
		System.out.println("Mathias");
		qotd = new Quote();
		ResultSet rs;
		String strReturn = "";	
		//qm.refreshQuote();
		qm.saveQuote();
		//qm.getQuote();
		rs = qb.selectFrom("quote").all().ExecuteQuery();	
		System.out.println("her til1");

		while(rs.next()){
			qotd.setQuote(rs.getString("quote"));
			qotd.setAuthor(rs.getString("author"));
			qotd.setTopic(rs.getString("topic"));
			System.out.println("virker2");
		
		}
		System.out.println("KOM NU HER");
		strReturn = gson.toJson(qotd);
		System.out.println(qotd+"se nu her");
		return strReturn;		
	}
	
	//TILFOEJ NY USER
	//nej er for doven til at lave klasse hvor admin er true.
	//Vi skal kun have 1 admin
	public boolean newUser(String eMail, String password) throws SQLException 
	{
		String[] field = {"email", "active", "password","admin"};
		String[] values = {eMail, "1", password, "0"};
		qb.insertInto("users", field).values(values).Execute();
		return true;
	}
	
	public boolean deleteUser(String eMail) throws SQLException {
		
		String [] keys = {"active"};
		String [] values = {"0"};	
		qb.update("users", keys, values).where("email", "=", eMail).Execute();	
		System.out.println("Succesfully deactivated: "+ eMail);
		return true;
	}	
	
	public boolean activateUser(String eMail) throws SQLException {
		
		String [] keys = {"active"};
		String [] values = {"1"};	
		qb.update("users", keys, values).where("email", "=", eMail).Execute();	
		System.out.println("Succesfully activated: "+ eMail);
		return true;
	}
	
	public String createNewCalendar (String userName, String calendarName, int privatePublic) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		if(authenticateNewCalendar(calendarName) == false)
		{
			addNewCalendar(calendarName, userName, privatePublic);
			stringToBeReturned = "The new calender has been created!";
		}
		else
		{
			stringToBeReturned = "The new calender has not been created!";
		}	
		return stringToBeReturned;
	}

	public boolean authenticateNewCalendar(String newCalendarName) throws SQLException
	{
		getConn();
		boolean authenticate = false;
		
		resultSet= qb.selectFrom("cbscalendar").where("name", "=", newCalendarName).ExecuteQuery();
			
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	public void addNewCalendar (String newCalendarName, String userName, int publicOrPrivate) throws SQLException
	{
		String [] keys = {"Name","active","CreatedBy","PrivatePublic"};
		String [] values = {newCalendarName,"1",userName, Integer.toString(publicOrPrivate)};
			qb.insertInto("cbscalendar", keys).values(values).Execute();
//		doUpdate("insert into test.calender (Name, Active, CreatedBy, PrivatePublic) VALUES ('"+newCalenderName+"', '1', '"+userName+"', '"+publicOrPrivate+"');");
	}
	/**
	 * Allows the client to delete a calendar 
	 * @param userName
	 * @param calenderName
	 * @return
	 */
	public String deleteCalendar (String userName, String calendarName) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		stringToBeReturned = removeCalendar(userName, calendarName);

		return stringToBeReturned;
	}
	
	
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
	
	
	
	
	
	
	
	
	
	
//	public String getEvents(String type) throws SQLException{
//		ResultSet rs;
//		String stringToBeReturned ="";
//		
//		try{
//			rs = qb.selectFrom("events").where("type", "=", type).ExecuteQuery();
//		while(rs.next())
//		{
//			
//		}
//			stringToBeReturned += rs.toString();
//			System.out.println("PAA CLIENTEN:"+resultSet);
//		}
//		return stringToBeReturned;
//	}
	
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
	
	public String removeCalendar (String userName, String calendarName) throws SQLException
	{
		String stringToBeReturned = "";
		String usernameOfCreator ="";
		String calendarExists = "";
		resultSet = qb.selectFrom("Calender").where("Name", "=", calendarName).ExecuteQuery();
				
//				("select * from calender where Name = '"+calenderName+"';");
		while(resultSet.next())
		{
			calendarExists = resultSet.toString();
		}
		if(!calendarExists.equals(""))
		{
			String [] value = {"CreatedBy"};
			resultSet = qb.selectFrom(value, "Calender").where("Name", "=", calendarName).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if(!usernameOfCreator.equals(userName))
			{
				stringToBeReturned = "Only the creator of the calender is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"2"};
				qb.update("Calendar", keys, values).where("Name", "=", calendarName).Execute();
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
//					int userID = resultSet.getInt("userid");
//
//					String[] key = {"type"};
//
//					resultSet = qb.selectFrom(key, "roles").where("userid", "=", new Integer(userID).toString()).ExecuteQuery();
//
//					// Hvis brugeren baade logger ind og er registreret som admin, eller hvis brugeren baade logger ind og er registreret som bruger
//					if((resultSet.getString("type").equals("admin") && isAdmin) || (resultSet.getString("type").equals("user") && !isAdmin))
//					{
//						return "0"; // returnerer "0" hvis bruger/admin er godkendt
//					} else {
//						return "4"; // returnerer fejlkoden "4" hvis brugertype ikke stemmer overens med loginplatform
//					}
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

	public String createEvents(String location,String  cb,String  start,String  end,String  name,String  type) throws SQLException{
		String stringToBeReturned = "";
		testConnection();
		
		if(autenticateNewEvent(name) ==false){
			addNewEvent(location, cb, start, start, name, type);
			stringToBeReturned = "The new event has been created!!";
		}
		else{
			stringToBeReturned = "The new event has not been created :( ";
		}
		return stringToBeReturned;
		
		
		
		
		
		
		
		
		
		
		
		//EVENT TING
		
		
	}
	public void addNewEvent(String location,String  cb,String  start,String  end,String  name,String  type) throws SQLException {
		String [] keys = {"location", "createdby", "type", "description", "start","end"};
		String [] values = {type,location,cb,name, start, start};
		qb.insertInto("events", keys).values(values).Execute();
		
	}



	private boolean autenticateNewEvent(String eventName) throws SQLException {
		getConn();
		boolean authenticate = false;
		resultSet = qb.selectFrom("cbscalendar").where("name","=", eventName).ExecuteQuery();
		
		while(resultSet.next()){
			authenticate = true;
		}
		
		return authenticate;
	}
	
	
	public String removeEvent (String name, String createdBy) throws SQLException{
		String stringToBeReturned ="";
		String userNameOfCreator ="";
		String EventExists = "";
		resultSet = qb.selectFrom("events").where("title", "=", name).ExecuteQuery();
		
		while(resultSet.next()){
			EventExists = resultSet.toString();
		}
		if(!EventExists.equals("")){
			String [] value = {"createdBy"};
			resultSet = qb.selectFrom(value, "events").where("name", "=", name).ExecuteQuery();
			while(resultSet.next()){
				userNameOfCreator = resultSet.toString();
				System.out.println(userNameOfCreator);
			}
			
			//så hvis IKKE brugernavnet er lig med brugernavnet på creator, bliver denne besked printet
			
			if(!userNameOfCreator.equals(createdBy)){
				stringToBeReturned = "Only the creator of the event is able to delete it!";
			}
			else{
				String [] keys = {"Active"};
				String [] values ={"2"};
				qb.update("Events", keys, values).where("Title", "=", name).Execute();
				stringToBeReturned = "The event has been set inactive"; //overvej omformulering
			}
			stringToBeReturned = resultSet.toString();
		}
		else{
			stringToBeReturned = "The event you are trying to delete does not exist!";
		}
		return stringToBeReturned;
	}
	
	
	public String deleteEvent(String Name) throws SQLException{
		String stringToBeReturned="";
		testConnection();
		stringToBeReturned = deleteEvent(Name);
		
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
	
	public String deleteNote(String userName, String noteID) throws SQLException{
		String stringToBeReturned="";
		testConnection();
		
		//String 
		stringToBeReturned = deleteNote(userName, noteID);
		
		return stringToBeReturned; //tjekke om event ID 
	}
	}