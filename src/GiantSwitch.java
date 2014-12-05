import java.sql.SQLException;
import java.util.ArrayList;

import model.Forecast.ForecastModel;
import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.note.Note;
import model.user.*;
import JsonClasses.AuthUser;
import JsonClasses.CalendarInfo;
import JsonClasses.CreateCalendar;
import JsonClasses.DeleteCalendar;
import JsonClasses.createEvents;
import JsonClasses.removeEvent;
import JsonClasses.deleteNote;
import JsonClasses.getCalendar;
import JsonClasses.getEvents;
import JsonClasses.getForecast;
import JsonClasses.getNote;
import JsonClasses.saveNote;

import com.google.gson.*;

import databaseMethods.SwitchMethods;

@SuppressWarnings("unused")
public class GiantSwitch {
	
	
	
	public String GiantSwitchMethod(String jsonString) throws Exception {

		ForecastTest ft = new ForecastTest();
		ForecastModel fm = new ForecastModel();
		
		
		Note noteKlasse = new Note();
		QOTDModel qm = new QOTDModel();
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

		//VIRKER 
		case "getEvents":
//			getEvents GE = (getEvents)gson.fromJson(jsonString, getEvents.class);
			ArrayList<Event> events = SW.getEvents();			
			answer = gson.toJson(events);
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;
			
		//VIRKER 	
		case "getCustomEvents":
//			getEvents GE = (getEvents)gson.fromJson(jsonString, getEvents.class);
			ArrayList<getEvents> cusevents = SW.getCustomEvents();			
			answer = gson.toJson(cusevents);
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;

		//VIRKER
		case "createEvents":
			createEvents CE = (createEvents)gson.fromJson(jsonString, createEvents.class);
			System.out.println("Recieved saveEvent @@@@@@@@@@@@@@@@@@@@@");
			System.out.println(CE.getAktiv());
			answer = SW.addNewEvent(CE.getLocation(),CE.getCreatedby(), CE.getStart(),CE.getEnd(), CE.getDescription(), CE.getType(), CE.getCustomevent(),CE.getAktiv());
			break; 
			
		//TESTER
		case "removeEvent":
			removeEvent DE = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			System.out.println("Recieved removeEvent");
			answer = SW.removeEvent(DE.getDescription());		
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
			
		//VIRKER
		case "getQuote":
		System.out.println("Recived getQuote");
		System.out.println(jsonString);	
		answer = SW.getQuote();
		System.out.println(answer);
		break;

		/************
		 ** WEATHER **
		 ************/
		case "getForecast":
			//Sletter og henter ny
			ft.refreshForecast();
			//Ligger som arraylist
			ArrayList<getForecast> gfc = SW.getForecast();	
			answer = gson.toJson(gfc);
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
		} else if (ID.contains("getCustomEvents")) {
			return "getCustomEvents";
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		} else if (ID.contains("getForecast")) {
			return "getForecast";
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
		} else if (ID.contains("createEvents")) {
			return "createEvents";
		} else if (ID.contains("removeEvent")) {
			return "removeEvent"; 
		} else if (ID.contains("createCalendar")) {
			return "createCalendar";
		}
		else
			return "error";
	}
}
