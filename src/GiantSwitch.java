import java.sql.SQLException;

import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.note.Note;
import model.user.*;
import JsonClasses.AuthUser;
import JsonClasses.CalendarInfo;
import JsonClasses.CreateCalendar;
import JsonClasses.DeleteCalendar;
import JsonClasses.createEvents;
import JsonClasses.deleteEvent;
import JsonClasses.deleteNote;
import JsonClasses.getCalendar;
import JsonClasses.getEvents;
import JsonClasses.getNote;
import JsonClasses.saveNote;

import com.google.gson.*;

import databaseMethods.SwitchMethods;

@SuppressWarnings("unused")
public class GiantSwitch {
	
	
	
	public String GiantSwitchMethod(String jsonString) throws Exception {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		Note noteKlasse = new Note();
		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		SwitchMethods SW = new SwitchMethods();
		
		Gson gson = new GsonBuilder().create();
		String answer = "";	

		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/************
		 ** COURSES **
		 ************/

		case "importCalendar":
			System.out.println("Recieved importCourse");
			break;

		/**********
		 ** LOGIN FAA DEN LIGE FIKSET ** 
		 **********/
//		case "logIn":
//			AuthenticateUser AU = (AuthenticateUser)gson.fromJson(jsonString, AuthenticateUser.class);
//			System.out.println("Recieved logIn");
//			try{
//			answer = SW.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsAdmin());
//			} catch (SQLException e){
//				e.printStackTrace();
//			}
//			break;
			
		case "logIn": // DONE
			
			AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
			System.out.println("Recieved logIn");
			try {
				answer = SW.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsAdmin());
			} catch (Exception e) {
				answer = "Error";
				e.printStackTrace();
			}

		case "logOut":
			System.out.println("Recieved logOut");
			break;
		/*************
		 ** CALENDAR **
		 *************/
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			System.out.println(CC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.createNewCalendar(CC.getUserName(), CC.getCalendarName(), CC.getPublicOrPrivate());
			break;
		
		case "deleteCalendar":
			DeleteCalendar DC = (DeleteCalendar)gson.fromJson(jsonString, DeleteCalendar.class);
			System.out.println(DC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.deleteCalendar(DC.getUserName(), DC.getCalendarName());
			break;
		
		case "saveImportedCalendar":// this is not necessary since it should save automathically 
			break;
			
		case "getCalendar":
			getCalendar GC = (getCalendar)gson.fromJson(jsonString, getCalendar.class);
			System.out.println("Recieved getCalendar");
			answer = SW.getCalendar(GC.getUserName());
			break;

		case "getEvents":
			getEvents GE = (getEvents)gson.fromJson(jsonString, getEvents.class);
			System.out.println("Recieved getEvents");
			answer = SW.getAllEvents(GE.getType());
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;

//		case "createEvent":
//			createEvents CE = (createEvents)gson.fromJson(jsonString, createEvents.class);
//			System.out.println("Recieved saveEvent");
//			answer = SW.createEvents(CE.getCreatedby(), CE.getstartTime(), CE.getendTime(), CE.getName(), CE.getText(), CE.getactive());
//			break; 
			
		case "deleteEvent":
			deleteEvent DE = (deleteEvent)gson.fromJson(jsonString, deleteEvent.class);
			System.out.println("Recieved deleteEvent");
			answer = SW.deleteEvent(DE.getName());
			//tjekke op på metode
			
			break;
		
		case "saveNote":
			saveNote SN = (saveNote)gson.fromJson(jsonString, saveNote.class);
			System.out.println("Recieved saveNote");
			//mangler metode i SwitchMethods
			break;
			

		case "getNote":
			getNote GN =(getNote)gson.fromJson(jsonString, getNote.class);
			System.out.println("Recieved getNote");
			answer = SW.GetNote(GN.geteventID);
			break;
			
			
		case "deleteNote":
			deleteNote DN = (deleteNote)gson.fromJson(jsonString, deleteNote.class);
			System.out.println("Recieved deleteNote");
			//tjekke metode i SwitchMethods
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":

//		AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
//		System.out.println("Recieved logIn");
		System.out.println("Recived getQuote");
		System.out.println(jsonString);	
		answer = SW.getQuote();
		System.out.println(answer);
			
			break;

		/************
		 ** WEATHER **
		 ************/

		case "getClientForecast":
			System.out.println("Recieved getClientForecast");
			break;
		
		default:
			System.out.println("Error");
			break;
		}
		return answer;
		
	}

	//Creates a long else if statement, which checks the JSon string which keyword it contains, and returns the following 
	//keyword if
	public String Determine(String ID) {
		if (ID.contains("getEvents")) {
			return "getEvents"; 
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("saveImportedCalendar")) {
			return "saveImportedCalendar";
		}else if (ID.contains("importCourse")) {
			return "importCourse";
		} else if (ID.contains("exportCourse")) {
			return "exportCourse";
		} else if (ID.contains("getQuote")) {
			return "getQuote";
		} else if (ID.contains("logIn")) {
			return "logIn";
		} else if (ID.contains("logOut")) {
			return "logOut";
		} else if (ID.contains("getCalendar")) {
			return "getCalendar";
		} else if (ID.contains("createEvent")) {
			return "createEvent";
		} else if (ID.contains("deleteEvent")) {
			return "deleteEvent"; 
		} else if (ID.contains("createCalendar")) {
			return "createCalendar";
		}
		else
			return "error";
	}
}
