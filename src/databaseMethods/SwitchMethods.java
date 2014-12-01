package databaseMethods;
import java.sql.SQLException;

import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;

public class SwitchMethods extends Model
{
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	

	
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calenderName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

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


	public String getEvents(String createdBy) throws SQLException{
		String stringToBeReturned ="";
		
		// er det den rigtige database?
		resultSet = qb.selectFrom("events").where("CreatedBy", "=", createdBy).ExecuteQuery();
		
		while(resultSet.next()){
			stringToBeReturned += resultSet.toString();
		}
		
		
		return stringToBeReturned;
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
					int userID = resultSet.getInt("userid");

					String[] key = {"type"};

					resultSet = qb.selectFrom(key, "roles").where("userid", "=", new Integer(userID).toString()).ExecuteQuery();

					// Hvis brugeren baade logger ind og er registreret som admin, eller hvis brugeren baade logger ind og er registreret som bruger
					if((resultSet.getString("type").equals("admin") && isAdmin) || (resultSet.getString("type").equals("user") && !isAdmin))
					{
						return "0"; // returnerer "0" hvis bruger/admin er godkendt
					} else {
						return "4"; // returnerer fejlkoden "4" hvis brugertype ikke stemmer overens med loginplatform
					}
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
	

	
	public String createEvents(String createdBy, String startTime, String endTime, String name, String text, int active) throws SQLException{
		String stringToBeReturned = "";
		testConnection();
		
		if(autenticateNewEvent(name) ==false){
			addNewEvent(name, startTime, endTime, text, createdBy, active);
			stringToBeReturned = "The new event has been created!!";
		}
		else{
			stringToBeReturned = "The new event has not been created :( ";
		}
		return stringToBeReturned;
	}

//IKKE SIKKER PÅ AT DET ER KORREKT DET HER, tjek databasen!! 

	private void addNewEvent(String createdBy, String startTime, String endTime, String name, String text, int active) throws SQLException {
		String [] keys = {"Name", "title", "type", "description", "location","createdby","activityID"};
		String [] values = {name, startTime, endTime, text, createdBy};
		qb.insertInto("cbsCalendar", keys).values(values).Execute();
		
	}

//IKKE SIKKER PÅ AT DET ER KORREKT

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
				
				//! operator “re- verses” the meaning of a condition. så hvis IKKE brugernavnet er lig med brugernavnet på creator, bliver denne besked printet
				
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



	
	


	
		
	


	
	

	


	
	
	


	

