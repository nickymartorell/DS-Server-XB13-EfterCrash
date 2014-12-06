import java.sql.SQLException;
import java.util.ArrayList;

import model.Forecast.ForecastModel;
import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.note.Note;
import model.user.*;
import JsonClasses.AuthUser;

import JsonClasses.CreateCalendar;
import JsonClasses.removeCalendar;
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


		/**********
		 ** LOGIN ** 
		 **********/		
		//VIRKER
		case "logIn": 		
			AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
			System.out.println("Recieved logIn");
			try {
				answer = SW.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsAdmin());
			} catch (Exception e) {
				answer = "Error";
				e.printStackTrace();
			}
			
		//BRUGER BARE SYSTEM.EXIT
		case "logOut":
			System.out.println("Recieved logOut");
			break;
			
		/*************
		 ** CALENDAR **
		 *************/
		//VIRKER på serverside
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			System.out.println("Den har lagt det nye ind i klassen");
			answer = SW.createNewCalendar(CC.getName(), CC.getCreatedBy(), CC.getPublicOrPrivate());
			break;
		
		//TESTER
		case "removeCalendar":
			removeCalendar rc = (removeCalendar)gson.fromJson(jsonString, removeCalendar.class);
			System.out.println("Den har lagt det nye ind i klassen");
			answer = SW.removeCalendar(rc.getCreatedBy(), rc.getName());
			break;
				
		case "getCalendar":
			getCalendar GC = (getCalendar)gson.fromJson(jsonString, getCalendar.class);
			System.out.println("Recieved getCalendar");
			answer = SW.getCalendar(GC.getName());
			break;
			
		case "getAllCalendar":
			//getCalendar gac = (getCalendar)gson.fromJson(jsonString, getCalendar.class);
			ArrayList<getCalendar> gCal = SW.getAllCalendar();
			System.out.println("Recieved getCalendar");
			answer = gson.toJson(gCal);
			break;

		/*************
		 ** EVENTS **
		 *************/
		//VIRKER skal lige rettes til
		case "getEvents":
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
			answer = SW.addNewEvent(CE.getLocation(),CE.getCreatedby(), CE.getStart(),CE.getEnd(), CE.getDescription(), CE.getType(), CE.getCustomevent(),CE.getAktiv());
			break; 
			
		//TESTER
		case "removeEvent":
			removeEvent DE = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			System.out.println("Recieved removeEvent");
			answer = SW.removeEvent(DE.getDescription());		
			break;
		
			
		/*************
		 ** NOTES **
		*************/
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
		//VIRKER
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
		} else if (ID.contains("getAllCalendar")) {
			return "getAllCalendar";
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("removeCalendar")){
			return "removeCalendar";
		} else if (ID.contains("getForecast")) {
			return "getForecast";
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
