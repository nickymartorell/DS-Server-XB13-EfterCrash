package databaseMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.resolver.readers.XCatalogReader;

import JsonClasses.getCalendar;
import JsonClasses.Quote;
import JsonClasses.getEvents;
import JsonClasses.getForecast;
import JsonClasses.createEvents;
import JsonClasses.userevents;
import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import model.calendar.Event;

public class SwitchMethods extends Model {
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	Gson gson = new Gson();
	Quote qotd = new Quote();
	getEvents getEv = new getEvents();
	getCalendar gc = new getCalendar();
	createEvents ev = new createEvents();
	ArrayList<String> subEvents = new ArrayList<String>();
	ResultSet rs;

	// VIRKER!
	public String subscribeCalendars(String email, String Name)
			throws SQLException {

		String stringToBeReturned = "";
		String getCalendarId = "";
		String getUserId = "";
		System.out.println("fra switch methods: " + email + Name);
		String[] key = { "calendarid", "userid" };
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			getCalendarId = resultSet.getString("KalId");
		}
		if (!getCalendarId.equals("")) {
			resultSet = qb.selectFrom("users").where("email", "=", email)
					.ExecuteQuery();
		}
		while (resultSet.next()) {
			getUserId = resultSet.getString("userid");
			System.out.println("getUserId id:" + getUserId);
		}
		if (!getUserId.equals("")) {
			String[] values = { getCalendarId, getUserId };
			qb.insertInto("userevents", key).values(values).Execute();
			stringToBeReturned = "You have now subscribed!";
		} else {
			stringToBeReturned = "Please only add public or request share";
		}
		return stringToBeReturned;
	}
	
	// TESTER!
	public String shareCalendars(String email, String Name, String receiver)
			throws SQLException {

		String stringToBeReturned = "";
		String getCalendarId = "";
		String getUserId = "";
		System.out.println("fra switch methods: " + email + Name);
		String[] key = { "calendarid", "userid" };
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			getCalendarId = resultSet.getString("KalId");
		}
		if (!getCalendarId.equals("")) {
			resultSet = qb.selectFrom("users").where("email", "=", email)
					.ExecuteQuery();
		}
		while (resultSet.next()) {
			getUserId = resultSet.getString("userid");
			System.out.println("getUserId id:" + receiver);
		}
		if (!getUserId.equals("")) {
			String[] values = { getCalendarId, receiver };
			qb.insertInto("userevents", key).values(values).Execute();
			stringToBeReturned = "You have now subscribed your friend!";
		} else {
			stringToBeReturned = "Please only add public or request share";
		}
		return stringToBeReturned;
	}

	// VIRKER
	public ArrayList<getEvents> getSubEvents(String email) throws SQLException {

		String who = "";
		ArrayList<getEvents> subEventsArray = new ArrayList<getEvents>();
		resultSet = qb.selectFrom("users").where("email", "=", email)
				.ExecuteQuery();
		while (resultSet.next()) {
			who = resultSet.getString("userid");
		}
		if (!who.equals("")) {
			resultSet = qb.selectFrom("userevents").where("userid", "=", who)
					.ExecuteQuery();
		}
		while (resultSet.next()) {
			subEvents.add(resultSet.getString("calendarid"));
		}
		if (!subEvents.equals("")) {
			for (int i = 0; i < subEvents.size(); i++) {
				resultSet = qb.selectFrom("events")
						.where("calendarid", "=", subEvents.get(i))
						.ExecuteQuery();
				while (resultSet.next()) {
					getEvents subEvent = new getEvents();
					subEvent.setId(resultSet.getString("id"));
					subEvent.setType(resultSet.getString("type"));
					subEvent.setLocation(resultSet.getString("location"));
					subEvent.setCreatedby(resultSet.getString("createdby"));
					subEvent.setStart(resultSet.getString("start"));
					subEvent.setEnd(resultSet.getString("end"));
					subEvent.setDescription(resultSet.getString("description"));
					subEvent.setCalendarid(resultSet.getString("calendarid"));
					subEvent.setCustomevent(resultSet.getString("customevent"));
					subEvent.setAktiv(resultSet.getString("aktiv"));
					subEventsArray.add(subEvent);
				}
			}
		}
		resultSet.close();
		return subEventsArray;
	}

	// VIRKER
	public String getQuote() throws SQLException {
		qotd = new Quote();
		ResultSet rs;
		String strReturn = "";
		// qm.refreshQuote();
		qm.saveQuote();
		// qm.getQuote();
		rs = qb.selectFrom("quote").all().ExecuteQuery();

		while (rs.next()) {
			qotd.setQuote(rs.getString("quote"));
			qotd.setAuthor(rs.getString("author"));
			qotd.setTopic(rs.getString("topic"));
		}
		strReturn = gson.toJson(qotd);
		return strReturn;
	}

	// VIRKER
	public boolean newUser(String eMail, String password) throws SQLException {
		String[] field = { "email", "active", "password", "admin" };
		String[] values = { eMail, "1", password, "0" };
		qb.insertInto("users", field).values(values).Execute();
		return true;
	}

	// VIRKER KUN ADMIN
	// LAV LIGE IF SAA ADMIN IKKE SAETTER SIG SELV INAKTIV
	public boolean deleteUser(String eMail) throws SQLException {
		String[] keys = { "active" };
		String[] values = { "0" };
		qb.update("users", keys, values).where("email", "=", eMail).Execute();
		System.out.println("Succesfully deactivated: " + eMail);
		return true;
	}

	// VIRKER KUN ADMIN
	public boolean activateUser(String eMail) throws SQLException {

		String[] keys = { "active" };
		String[] values = { "1" };
		qb.update("users", keys, values).where("email", "=", eMail).Execute();
		System.out.println("Succesfully activated: " + eMail);
		return true;
	}

	// VIRKER
	public String createNewCalendar(String Name, String CreatedBy,
			String privatePublic) throws SQLException {
		String stringToBeReturned = "";
		testConnection();
		if (authenticateNewCalendar(Name) == false) {
			addNewCalendar(Name, CreatedBy, privatePublic);
			stringToBeReturned = "The new calendar has been created!";
		} else {
			stringToBeReturned = "The new calendar has not been created!";
		}
		return stringToBeReturned;
	}

	// VIRKER
	public boolean authenticateNewCalendar(String Name) throws SQLException {
		getConn();
		boolean authenticate = false;
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();

		while (resultSet.next()) {
			authenticate = true;
		}
		return authenticate;
	}

	// VIRKER
	public void addNewCalendar(String newCalendarName, String userName,
			String publicOrPrivate) throws SQLException {
		String[] keys = { "Name", "Active", "CreatedBy", "PrivatePublic" };
		String[] values = { newCalendarName, "1", userName, publicOrPrivate };
		qb.insertInto("calendar", keys).values(values).Execute();
	}

	// VIRKER
	public String removeCalendar(String CreatedBy, String Name)
			throws SQLException {
		String stringToBeReturned = "";
		String usernameOfCreator = "";
		String calendarExists = "";
		System.out.println("DETTE ER NAME" +Name);
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			calendarExists = resultSet.toString();
		}
		if (!calendarExists.equals("")) {
			String[] value = { "CreatedBy" };
			resultSet = qb.selectFrom(value, "calendar")
					.where("CreatedBy", "=", CreatedBy).ExecuteQuery();
			while (resultSet.next()) {
				usernameOfCreator = resultSet.getString("CreatedBy");
				System.out.println(usernameOfCreator);
			}
			if (!usernameOfCreator.equals(CreatedBy)) {
				stringToBeReturned = "Only the creator of the calendar is able to delete it.";
			} else {
				String[] keys = { "Active" };
				String[] values = { "0" };
				qb.update("calendar", keys, values).where("Name", "=", Name)
						.Execute();
				stringToBeReturned = "Calendar has been set inactive";
			}
			stringToBeReturned = resultSet.toString();
		} else {
			stringToBeReturned = "The calendar you are trying to delete, does not exists.";
		}
		return stringToBeReturned;
		}
	
		// TESTER
		public String activateCalendar(String CreatedBy, String Name)
				throws SQLException {
			String stringToBeReturned = "";
			String usernameOfCreator = "";
			String calendarExists = "";
			System.out.println("DETTE ER NAME" +Name);
			resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
					.ExecuteQuery();
			while (resultSet.next()) {
				calendarExists = resultSet.toString();
			}
			if (!calendarExists.equals("")) {
				String[] value = { "CreatedBy" };
				resultSet = qb.selectFrom(value, "calendar")
						.where("CreatedBy", "=", CreatedBy).ExecuteQuery();
				while (resultSet.next()) {
					usernameOfCreator = resultSet.getString("CreatedBy");
					System.out.println(usernameOfCreator);
				}
				if (!usernameOfCreator.equals(CreatedBy)) {
					stringToBeReturned = "Only the creator of the calendar is able to activate it.";
				} else {
					String[] keys = { "Active" };
					String[] values = { "1" };
					qb.update("calendar", keys, values).where("Name", "=", Name)
							.Execute();
					stringToBeReturned = "Calendar has been set active";
				}
				stringToBeReturned = resultSet.toString();
			} else {
				stringToBeReturned = "The calendar you are trying to activate, does not exists.";
			}
			return stringToBeReturned;
		}
	// KUN TIL ADMIN
	//VIRKER
	public String removeCalendarAdmin(String Name) throws SQLException {
		String stringToBeReturned = "";
		String calendarExists = "";
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			calendarExists = resultSet.toString();
		}
		if (!calendarExists.equals("")) {
			String[] keys = { "Active" };
			String[] values = { "0" };
			qb.update("calendar", keys, values).where("Name", "=", Name)
					.Execute();
			stringToBeReturned = "Calender has been set inactive";
		}
		stringToBeReturned = resultSet.toString();

		return stringToBeReturned;
	}

	// VIRKER KUN TIL ADMIN
	public String removeEventAdmin(String uid) throws SQLException {
		String stringToBeReturned = "";

		resultSet = qb.selectFrom("events")
				.where("id", "=", uid).ExecuteQuery();
		while (resultSet.next()) {
			
			String y = resultSet.getString("id");
				String[] keys = { "aktiv" };
				String[] values = { "0" };
				System.out.println(y);
				qb.update("Events", keys, values).where("id", "=", y).Execute();
		}
		stringToBeReturned = "The event has been set inactive";
		return stringToBeReturned;
	}

	// VIRKER KUN TIL ADMIN
	public String activateEventAdmin(String description) throws SQLException {
		createEvents ev = new createEvents();
		String stringToBeReturned = "";
		String x = "1";

		resultSet = qb.selectFrom("events")
				.where("description", "=", description).ExecuteQuery();
		if (resultSet.next()) {
			ev.setCustomevent(resultSet.getString("customevent"));
			String y = resultSet.getString("customevent");
			if (y.equals(x)) {
				String[] keys = { "aktiv" };
				String[] values = { "1" };
				qb.update("Events", keys, values)
						.where("description", "=", description).Execute();
			}
		} else {
			System.out.println("Only activate your own! and custom events!!!");
		}
		stringToBeReturned = "The event has been set active";
		return stringToBeReturned;
	}

	// Hent alle kalendere til visning
	//VIRKER
	public ArrayList<getCalendar> getAllCalendar() {
		try {
			qb = new QueryBuilder();
			gson = new Gson();

			ResultSet rs = qb.selectFrom("calendar").all().ExecuteQuery();
			ArrayList<getCalendar> calendarList = new ArrayList<getCalendar>();
			while (rs.next()) {
				getCalendar getCal = new getCalendar();
				getCal.setName(rs.getString("Name"));
				getCal.setCreatedBy(rs.getString("CreatedBy"));
				getCal.setActive(rs.getString("Active"));
				getCal.setPublicOrPrivate(rs.getString("PrivatePublic"));
				calendarList.add(getCal);
			}
			rs.close();
			return calendarList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// VIRKER
	public ArrayList<getEvents> getCustomEvents() {
		try {
			qb = new QueryBuilder();
			gson = new Gson();

			ResultSet rs = qb.selectFrom("events")
					.where("customevent", "=", "1").ExecuteQuery();
			ArrayList<getEvents> eventList = new ArrayList<getEvents>();
			while (rs.next()) {
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

	// VIRKER
	public ArrayList<getForecast> getForecast() {
		try {
			qb = new QueryBuilder();
			gson = new Gson();

			ResultSet rs = qb.selectFrom("forecast").all().ExecuteQuery();
			ArrayList<getForecast> fca = new ArrayList<getForecast>();
			while (rs.next()) {
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

	// VIRKER MEN BRUGER IKKE ENDNU
	public String GetNote(String eventId) throws SQLException {
		String stringToBeReturned = "";

		resultSet = qb.selectFrom("notes").where("eventId", "=", eventId)
				.ExecuteQuery();

		while (resultSet.next()) {
			stringToBeReturned += resultSet.toString();
		}
		return stringToBeReturned;
	}

	/**
	 * Allows the client to log in
	 * 
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @return
	 * @throws Exception
	 */
	// VIRKER
	public String authenticate(String email, String password, boolean isAdmin)
			throws Exception {

		String[] keys = { "userid", "email", "active", "password" };

		qb = new QueryBuilder();

		// Henter info om bruger fra database via querybuilder
		resultSet = qb.selectFrom(keys, "users").where("email", "=", email)
				.ExecuteQuery();

		// Hvis en bruger med forespurgt email findes
		if (resultSet.next()) {

			// Hvis brugeren er aktiv
			if (resultSet.getInt("active") == 1) {
				// Hvis passwords matcher
				if (resultSet.getString("password").equals(password)) {
					return resultSet.getString("userid");
				} else {
					return "3"; // returnerer fejlkoden "3" hvis password ikke
								// matcher
				}
			} else {
				return "2"; // returnerer fejlkoden "2" hvis bruger er sat som
							// inaktiv
			}
		} else {
			return "1"; // returnerer fejlkoden "1" hvis email ikke findes
		}
	}

	// EVENT TING
	// VIRKER
	public String createEvents(String location, String cb, String start,
			String end, String description, String type, String customevent,
			String aktiv, String calendarid) throws SQLException {
		String stringToBeReturned = "";
		testConnection();
		if (autenticateNewEvent(description) == false) {
			addNewEvent(location, cb, start, start, description, type,
					customevent, aktiv, calendarid);
			stringToBeReturned = "The new event has been created!!";
		} else {
			stringToBeReturned = "The new event has not been created :( ";
		}
		return stringToBeReturned;
	}

	// VIRKER
	public String addNewEvent(String location, String cb, String start,
			String end, String description, String type, String customevent,
			String aktiv, String calendarid) throws SQLException {
		String hehe = "";
		String[] keys = { "location", "createdby", "type", "description",
				"start", "end", "customevent", "aktiv", "calendarid" };
		String[] values = { location, cb, type, description, start, end,
				customevent, aktiv, calendarid };
		qb.insertInto("events", keys).values(values).Execute();
		return hehe;
	}

	// VIRKER
	private boolean autenticateNewEvent(String desription) throws SQLException {
		getConn();
		boolean authenticate = false;
		resultSet = qb.selectFrom("events")
				.where("description", "=", desription).ExecuteQuery();

		while (resultSet.next()) {
			String x = resultSet.getString("calendarid");
			int y = Integer.parseInt(x);
			// saa vi ikke kan add til cbs calendar
			// kun til egen oprettede
			if (y <= 9) {
				authenticate = true;
			} else {
				System.out
						.println("Please do not add to Cbs official calendar");
			}
		}
		return authenticate;
	}
	// VIRKER KUN BRUGES AF ADMIN
	public String removeEvent(String description) throws SQLException {
		String stringToBeReturned = "";
		String[] keys = { "aktiv" };
		String[] values = { "0" };
		qb.update("Events", keys, values)
				.where("description", "=", description).Execute();
		stringToBeReturned = "The event has been set inactive";
		return stringToBeReturned;
	}
	//VIRKER
	public String removeEventUser(String description, String createdby) throws SQLException {
		String stringToBeReturned = "";
		String userNameOfCreator = "";
		String eventExist = "";
		resultSet = qb.selectFrom("events").where("description", "=", description)
				.ExecuteQuery();

		while (resultSet.next()) {
			eventExist = resultSet.toString();

			if (!eventExist.equals("")) {
				String[] value = { "createdby" };
				resultSet = qb.selectFrom(value, "events")
						.where("createdby", "=", createdby).ExecuteQuery();
				while (resultSet.next()) {
					userNameOfCreator = resultSet.toString();				
				}
				if (!userNameOfCreator.equals(createdby)) {
					stringToBeReturned = "Only the creator of the note is able to delete it!";
				} else {
					String[] keys = { "aktiv" };
					String[] values = { "0" };
					qb.update("events", keys, values)
							.where("description", "=", description).Execute();
					stringToBeReturned = "The event has been deleted"; 															
				}
				stringToBeReturned = resultSet.toString();
			} else {
				stringToBeReturned = "The event you are trying to delete does not exist!";
			}
		}
		return stringToBeReturned;
	}
	// NOTE TING
	// denne her skal dobbelttjekkes!!!!!
	public String removeNote(String noteID, String UserName)
			throws SQLException {
		String stringToBeReturned = "";
		String userNameOfCreator = "";
		String NoteExists = "";
		resultSet = qb.selectFrom("notes").where("noteID", "=", noteID)
				.ExecuteQuery();

		while (resultSet.next()) {
			NoteExists = resultSet.toString();

			if (!NoteExists.equals("")) {
				String[] value = { "CreatedBy" };
				resultSet = qb.selectFrom(value, "notes")
						.where("noteID", "=", noteID).ExecuteQuery();
				while (resultSet.next()) {
					userNameOfCreator = resultSet.toString();
					System.out.println(userNameOfCreator);
				}
				if (!userNameOfCreator.equals(UserName)) {
					stringToBeReturned = "Only the creator of the note is able to delete it!";
				} else {
					String[] keys = { "Active" };
					String[] values = { "2" };
					qb.update("notes", keys, values)
							.where("noteID", "=", noteID).Execute();
					stringToBeReturned = "The note has been deleted"; // overvej
																		// omformulering
				}
				stringToBeReturned = resultSet.toString();
			} else {
				stringToBeReturned = "The note you are trying to delete does not exist!";
			}
		}
		return stringToBeReturned;
	}
	
	// NOTE TING
	
	
	// KUN TIL ADMIN
	// VIRKER
	public String removeNoteAdmin(int noteid)
			throws SQLException {
		String stringToBeReturned = "";
		String NoteExists = "";
		String id = String.valueOf(noteid);
		resultSet = qb.selectFrom("notes").where("noteid", "=", id)
				.ExecuteQuery();

		while (resultSet.next()) {
			NoteExists = resultSet.toString();

			if (!NoteExists.equals("")) {
				
					String[] keys = { "isActive" };
					String[] values = { "0" };
					qb.update("notes", keys, values)
							.where("noteid", "=", id).Execute();
					stringToBeReturned = "The note has been deleted"; 	
			} else {
			stringToBeReturned = "The note you are trying to delete does not exist!";
		}
	}
	return stringToBeReturned;
	}
	
	// VIRKER
	// PAA CLIENT SIDE LAV EN IF DE HAR EVENTET
	public String createNote(String eventid, String note, String createdby) throws SQLException {
			String stringToBeReturned = "";
			String[]keys = {"eventid","note","CreatedBy"};
			String[] values = {eventid,note,createdby};
			qb.insertInto("notes", keys).values(values).Execute();
			return stringToBeReturned;
	}

	// public String removeEvent (String description) throws SQLException{
	// String stringToBeReturned ="";
	// String userNameOfCreator ="";
	// String EventExists = "";
	// resultSet = qb.selectFrom("events").where("description", "=",
	// description).ExecuteQuery();
	//
	// while(resultSet.next()){
	// EventExists = resultSet.toString();
	// }
	// if(!EventExists.equals("")){
	// String [] value = {"createdBy"};
	// resultSet = qb.selectFrom(value, "events").where("name", "=",
	// name).ExecuteQuery();
	// while(resultSet.next()){
	// userNameOfCreator = resultSet.toString();
	// System.out.println(userNameOfCreator);
	// }
	//
	// if(!userNameOfCreator.equals(createdBy)){
	// stringToBeReturned =
	// "Only the creator of the event is able to delete it!";
	// }
	// else{
	// String [] keys = {"Active"};
	// String [] values ={"2"};
	// qb.update("Events", keys, values).where("Title", "=", name).Execute();
	// stringToBeReturned = "The event has been set inactive"; //overvej
	// omformulering
	// }
	// stringToBeReturned = resultSet.toString();
	// }
	// else{
	// stringToBeReturned =
	// "The event you are trying to delete does not exist!";
	// }
	// return stringToBeReturned;
	// }
//	public String getAllEvents(String type) {
//		try {
//			qb = new QueryBuilder();
//			gson = new Gson();
//			getEv = new getEvents();
//			ResultSet rs = qb.selectFrom("events").where("type", "=", type)
//					.ExecuteQuery();
//			// List<Event> eventList = new ArrayList<>();
//			String eventList = "";
//			while (rs.next()) {
//				// Event event = new Event();
//				// event.setActivityid(rs.getString("id"));
//				getEv.setType(rs.getString("type"));
//				// event.setActivityid(rs.getString("activityid"));
//				getEv.setLocation(rs.getString("location"));
//				// event.setCreatedby(rs.getString("createdby"));
//				// event.setDateStart(rs.getDate("start"));
//				// event.setStrDateStart(rs.getString("start"));
//				// event.setStrDateEnd(rs.getString("end"));
//				eventList += rs.toString();
//			}
//			// rs.close();
//			eventList = gson.toJson(getEv);
//			System.out.println("FRA CLIENT GET EVENTS: " + getEv
//					+ "HER ER EVENTLISTEN" + eventList);
//			return gson.toJson(eventList);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ArrayList<Event> getEvents() {
//		try {
//			qb = new QueryBuilder();
//			gson = new Gson();
//
//			ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
//			ArrayList<Event> eventList = new ArrayList<Event>();
//
//			while (rs.next()) {
//				Event event = new Event();
//				event.setActivityid(rs.getString("id"));
//				event.setType(rs.getString("type"));
//
//				// HUSK RET 2X ACTIVITY ID
//				event.setActivityid(rs.getString("activityid"));
//				event.setLocation(rs.getString("location"));
//				// event.setCreatedby(rs.getString("createdby"));
//				// event.setDateStart(rs.getDate("start"));
//				event.setStrDateStart(rs.getString("start"));
//				event.setStrDateEnd(rs.getString("end"));
//				eventList.add(event);
//			}
//			rs.close();
//			return eventList;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}