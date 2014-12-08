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
import JsonClasses.getNote;
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

	/**
	 * subscribe til kalender. tjekker om kalenderen er public
	 * ellers kan brugeren ikke subscribe
	 * @param email
	 * @param Name
	 * @return
	 * @throws SQLException
	 */
	public String subscribeCalendars(String email, String Name)
			throws SQLException {
		String stringToBeReturned = "";
		String getCalendarId = "";
		boolean pub = false;
		String getUserId = "";
		String[] key = { "calendarid", "userid" };

		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			getCalendarId = resultSet.getString("calendarId");
			pub = resultSet.getBoolean("PrivatePublic");
		}
		if (!getCalendarId.equals("") && pub == true) {
			resultSet = qb.selectFrom("users").where("email", "=", email)
					.ExecuteQuery();
		}
		while (resultSet.next()) {
			getUserId = resultSet.getString("userid");
			System.out.println("getUserId id:" + getUserId);
		
		}
		if(!getUserId.equals("")){
			String[] values = { getCalendarId, getUserId };
			qb.insertInto("userevents", key).values(values).Execute();
			stringToBeReturned = "You have now subscribed!";
		
		} else {			
			stringToBeReturned = "Please don't subscribe twice to the same event";
		}
		return stringToBeReturned;
	}	
	/**
	 * unsubscribe til kalender igen.
	 * kun owner kan goere det
	 * @param email
	 * @param calendarid
	 * @return
	 * @throws SQLException
	 */
	public String unSubscribeCalendars(String email , String calendarid)
			throws SQLException {

		String stringToBeReturned = "";
		String getUserId = "";
		String softRemove = "0";
		String[] key = { "calendarid", "userid" };
		
			resultSet = qb.selectFrom("users").where("email", "=", email)
					.ExecuteQuery();
		
		while (resultSet.next()) {
			getUserId = resultSet.getString("userid");
		}
		if (!getUserId.equals("")) {
			String[] values = { softRemove, softRemove };
			qb.update("userevents", key,values).where("calendarid","=",calendarid).Execute();
		}
		return stringToBeReturned;
	}
	/**
	 * en bruger deler sin kalender. der bliver tjekket om deleren er creator
	 * hvis det er sandt, vil den nedskrevne user som er receiver kunne faa kalenderen
	 * indsaetter userid og calendarid i userevents
	 * @param email
	 * @param Name
	 * @param receiver
	 * @return
	 * @throws SQLException
	 */
	public String shareCalendars(String email, String Name, String receiver)
			throws SQLException {

		String stringToBeReturned = "";
		String getCalendarId = "";
		String getUserId = "";
		String[] key = { "calendarid", "userid" };
		resultSet = qb.selectFrom("calendar").where("Name", "=", Name)
				.ExecuteQuery();
		while (resultSet.next()) {
			getCalendarId = resultSet.getString("calendarId");
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

	/**
	 * denne metode henter klient brugerens subscribede events.
	 * Foerst ser den hvad brugerens userid er ved at kigger paa hans email
	 * naar der er fundet et match paa email, kan der lavet et selectFrom paa userid.
	 * Saa er der et if statement som tjekker at resultset returnerede en bruger
	 * dernaest finder den calendarid fra userevents som holder paa userid og calendarid
	 * hvis der return paa calendarid, vil der blive lavet et arraylist som holder
	 * de events brugeren er tilmeldt
	 * @param email
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * henter quote
	 * @return
	 * @throws SQLException
	 */
	public String getQuote() throws SQLException {
		qotd = new Quote();
		ResultSet rs;
		String strReturn = "";
		qm.refreshQuote();
		qm.saveQuote();
		rs = qb.selectFrom("quote").all().ExecuteQuery();

		while (rs.next()) {
			qotd.setQuote(rs.getString("quote"));
			qotd.setAuthor(rs.getString("author"));
			qotd.setTopic(rs.getString("topic"));
		}
		strReturn = gson.toJson(qotd);
		return strReturn;
	}

	/**
	 * admin kan lave ny bruger
	 * skal bruge 2 parametre
	 * @param eMail
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean newUser(String eMail, String password) throws SQLException {
		String[] field = { "email", "active", "password", "admin" };
		String[] values = { eMail, "1", password, "0" };
		qb.insertInto("users", field).values(values).Execute();
		return true;
	}

	/**
	 * admin sletter brugere
	 * der er en if statement der sikrer at admin
	 * ikke sletter sig selv
	 * @param eMail
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteUser(String eMail) throws SQLException {
		if(!eMail.equals("admin@admin.dk")){
		String[] keys = { "active" };
		String[] values = { "0" };
		qb.update("users", keys, values).where("email", "=", eMail).Execute();
		System.out.println("Succesfully deactivated: " + eMail);
		}
		else{
			System.out.println("come on admin...");
		}
		return true;
	}

	/**
	 * admins metode til at aktivere users igen
	 * kun admin kan aktivere brugere
	 * @param eMail
	 * @return
	 * @throws SQLException
	 */
	public boolean activateUser(String eMail) throws SQLException {

		String[] keys = { "active" };
		String[] values = { "1" };
		qb.update("users", keys, values).where("email", "=", eMail).Execute();
		System.out.println("Succesfully activated: " + eMail);
		return true;
	}

	/**
	 * bruger adnewcalendar og authenticatenewcalendar
	 * til at lave ny calendar
	 * @param Name
	 * @param CreatedBy
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * godkender at kalenderen ikke ligger i databasen
	 * @param Name
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * laver ny kalender
	 * @param newCalendarName
	 * @param userName
	 * @param publicOrPrivate
	 * @throws SQLException
	 */
	public void addNewCalendar(String newCalendarName, String userName,
			String publicOrPrivate) throws SQLException {
		String[] keys = { "Name", "Active", "CreatedBy", "PrivatePublic" };
		String[] values = { newCalendarName, "1", userName, publicOrPrivate };
		qb.insertInto("calendar", keys).values(values).Execute();
	}

	/**
	 * users metode til at slette kalender
	 * skal vaere creator
	 * @param CreatedBy
	 * @param Name
	 * @return
	 * @throws SQLException
	 */
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
	
		/**
		 * klienters metode til at aktivere kalender
		 * kun klienter som har lavet kalenderen kan aktivere
		 * @param CreatedBy
		 * @param Name
		 * @return
		 * @throws SQLException
		 */
		public String activateCalendar(String CreatedBy, String Name)
				throws SQLException {
			String stringToBeReturned = "";
			String usernameOfCreator = "";
			String calendarExists = "";
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
	/**
	 * admins metode til at slette kalendere
	 * @param Name
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * admins metode til at slette events.
	 * han skal ikke bruge creator som parameter
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * admins metode til at aktivere events
	 * @param description
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * henter alle kalendere som
	 * ligger i databasen
	 * @return
	 */
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
				getCal.setCalendarid(rs.getString("calendarId"));
				calendarList.add(getCal);
			}
			rs.close();
			return calendarList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * henter alle events som er lavet
	 * af klienter
	 * @return
	 */
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

	/**
	 * laver arraylist af forecast data
	 * hiver data fra databasen
	 * @return
	 */
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
			}
			rs.close();
			return fca;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * laver arraylist af de noter med det
	 * soegte eventid
	 * @param eventId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<getNote> GetNote(String eventId) throws SQLException {
		resultSet = qb.selectFrom("notes").where("eventid", "=", eventId)
				.ExecuteQuery();
		ArrayList<getNote> noter = new ArrayList<getNote>();
		while (resultSet.next()) {
			getNote gn = new getNote();
			gn.setNoteID(resultSet.getInt("noteid"));
			gn.setEventid(resultSet.getString("eventid"));
			gn.setNote(resultSet.getString("note"));
			gn.setCreatedby(resultSet.getString("createdby"));
			gn.setIsActive(resultSet.getString("isActive"));
			noter.add(gn);
		}
		return noter;
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
					
					return "1";
					
				} else {
					return "3"; // returnerer fejlkoden "3" hvis password ikke
								// matcher
				}
			} else {
				return "2"; // returnerer fejlkoden "2" hvis bruger er sat som
							// inaktiv
			}
		} else {
			return "0"; // returnerer fejlkoden "1" hvis email ikke findes DEN RETURNERE 0 FORDI JEG IKK ER OPRETTET
		}
	}

	/**
	 * bruger metoden autenticatenewevent til
	 * at se om eventet allerede findes
	 * @param location
	 * @param cb
	 * @param start
	 * @param end
	 * @param description
	 * @param type
	 * @param customevent
	 * @param aktiv
	 * @param calendarid
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * indsaetter i databasen.
	 * skal godkendes foerst
	 * @param location
	 * @param cb
	 * @param start
	 * @param end
	 * @param description
	 * @param type
	 * @param customevent
	 * @param aktiv
	 * @param calendarid
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * tjekker om event findes
	 * kan ikke oprette med calendar id
	 * 1-9 da det er CBS og admins kalendere 
	 * @param desription
	 * @return
	 * @throws SQLException
	 */
	private boolean autenticateNewEvent(String desription) throws SQLException {
		getConn();
		boolean authenticate = false;
		resultSet = qb.selectFrom("events")
				.where("description", "=", desription).ExecuteQuery();

		while (resultSet.next()) {
			String x = resultSet.getString("calendarid");
			int y = Integer.parseInt(x);
			if (y <= 9) {
				authenticate = true;
			} else {
				System.out.println("Please do not add to Cbs official calendar");
			}
		}
		return authenticate;
	}

	/**
	 * saetter event inaktivt
	 * kun admin.
	 * @param description
	 * @return
	 * @throws SQLException
	 */
	public String removeEvent(String description) throws SQLException {
		String stringToBeReturned = "";
		String[] keys = { "aktiv" };
		String[] values = { "0" };
		qb.update("Events", keys, values)
				.where("description", "=", description).Execute();
		stringToBeReturned = "The event has been set inactive";
		return stringToBeReturned;
	}

	/**
	 * brugerens remove event
	 * her er en parameter mere som tjekker om det er brugeren som er creator
	 * @param description
	 * @param createdby
	 * @return
	 * @throws SQLException
	 */
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
					stringToBeReturned = "Only the creator of the event is able to delete it!";
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


	/**
	 * fjerner note igen. tjekker om creator er samme
	 * som den der requester at fjerne en note
	 * @param noteid
	 * @param createdby
	 * @return
	 * @throws SQLException
	 */
	public String removeNote(int noteid, String createdby)
			throws SQLException {
		String stringToBeReturned = "";
		String userNameOfCreator = "";
		String NoteExists = "";
		String id = String.valueOf(noteid);
		resultSet = qb.selectFrom("notes").where("noteid", "=", id)
				.ExecuteQuery();

		while (resultSet.next()) {
			NoteExists = resultSet.toString();

			if (!NoteExists.equals("")) {
				String[] value = { "CreatedBy" };
				resultSet = qb.selectFrom(value, "notes")
						.where("noteid", "=", id).ExecuteQuery();
				while (resultSet.next()) {
					userNameOfCreator = resultSet.toString();
					System.out.println(userNameOfCreator);
				}
				if (!userNameOfCreator.equals(createdby)) {
					stringToBeReturned = "Only the creator of the note is able to delete it!";
				} else {
					String[] keys = { "isActive" };
					String[] values = { "0" };
					qb.update("notes", keys, values)
							.where("noteid", "=", id).Execute();
					stringToBeReturned = "The note has been deleted"; 																	
				}
				stringToBeReturned = resultSet.toString();
			} else {
				stringToBeReturned = "The note you are trying to delete does not exist!";
			}
		}
		return stringToBeReturned;
	}
	

	/**
	 * admin har direkte adgang til at slette note
	 * @param noteid
	 * @return
	 * @throws SQLException
	 */
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
	
	/**
	 * admin kan aktivere en note
	 * @param noteid
	 * @return
	 * @throws SQLException
	 */
	public String activateNoteAdmin(int noteid)
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
					String[] values = { "1" };
					qb.update("notes", keys, values)
							.where("noteid", "=", id).Execute();
					stringToBeReturned = "The note has been activated"; 	
			} else {
			stringToBeReturned = "The note you are trying to activate does not exist!";
		}
	}
	return stringToBeReturned;
	}	

	/**
	 * create note
	 * @param eventid
	 * @param note
	 * @param createdby
	 * @return
	 * @throws SQLException
	 */
	public String createNote(String eventid, String note, String createdby) throws SQLException {
			String stringToBeReturned = "";
			String yes = "1";
			String[]keys = {"eventid","note","CreatedBy","isActive"};
			String[]values = {eventid,note,createdby,yes};
			qb.insertInto("notes", keys).values(values).Execute();
			return stringToBeReturned;
	}
}