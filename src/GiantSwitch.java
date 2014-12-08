import java.sql.SQLException;
import java.util.ArrayList;

import model.Forecast.ForecastModel;
import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.user.*;
import JsonClasses.AuthUser;
import JsonClasses.CreateCalendar;
import JsonClasses.removeCalendar;
import JsonClasses.createEvents;
import JsonClasses.removeEvent;
import JsonClasses.removeNote;
import JsonClasses.getCalendar;
import JsonClasses.getEvents;
import JsonClasses.getForecast;
import JsonClasses.getNote;
import JsonClasses.createNote;
import JsonClasses.userevents;

import com.google.gson.*;

import databaseMethods.SwitchMethods;

@SuppressWarnings("unused")
public class GiantSwitch {
	
	public String GiantSwitchMethod(String jsonString) throws Exception {

		ForecastTest ft = new ForecastTest();
		ForecastModel fm = new ForecastModel();
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
		
		/**
		 * bruger jsonklassen authuser til at sette vaerdier
		 * koerer authenticate metoden i SM
		 */
		case "logIn": 		
			AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
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
		
		
		/**
		 * bruges af clienten og er metoden til at share kalendere
		 */	
		case "shareCalendar":
			userevents sa = gson.fromJson(jsonString, userevents.class);
			answer = SW.shareCalendars(sa.getEmail(),sa.getName(),sa.getReceiver());
			break;
			
		/**
		 *subscribe til kalender
		 */
		case "subscribeCalendar":
			userevents uevents = gson.fromJson(jsonString, userevents.class);
			answer = SW.subscribeCalendars(uevents.getEmail(), uevents.getCalendarid());
			break;
			
			/**
			 * unsubscriber igen
			 */
		case "unSubscribeCalendar":
			userevents uee = gson.fromJson(jsonString, userevents.class);
			answer = SW.unSubscribeCalendars(uee.getEmail(), uee.getCalendarid());
			break;			
			
			/**
			 * lav ny kalender
			 */
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			answer = SW.createNewCalendar(CC.getName(), CC.getCreatedBy(), CC.getPublicOrPrivate());
			break;
		
			/**
			 * set kalender inactive
			 * 
			 */
		case "removeCalendar":
			removeCalendar rc = (removeCalendar)gson.fromJson(jsonString, removeCalendar.class);
			System.out.println("SERVER SIDEN" + rc.getName()+rc.getCreatedBy());
			answer = SW.removeCalendar(rc.getCreatedBy(),rc.getName());
			break;
			
			/**
			 * aktivere kalender
			 */
		case "activateCalendar":
			removeCalendar ra = (removeCalendar)gson.fromJson(jsonString, removeCalendar.class);
			answer = SW.activateCalendar(ra.getCreatedBy(),ra.getName());
			break;
				
			/**
			 * henter alle kalendere til visning
			 * bliver lagt i arraylist
			 */
		case "getAllCalendar":
			ArrayList<getCalendar> gCal = SW.getAllCalendar();
			answer = gson.toJson(gCal);
			break;

		/*************
		 ** EVENTS **
		 *************/

			/**
			 * henter custom events.
			 * events lavet af klienter
			 */
		case "getCustomEvents":
			ArrayList<getEvents> cusevents = SW.getCustomEvents();			
			answer = gson.toJson(cusevents);
			break;
			
			/**
			 * henter subscribede events
			 */
		case "getSubEvents":
			userevents ge = (userevents)gson.fromJson(jsonString, userevents.class);
			ArrayList<getEvents> subEvents = SW.getSubEvents(ge.getEmail());			
			answer = gson.toJson(subEvents);
			break;

			/**
			 * laver events
			 * kreaver en lang raekke parametre for at 
			 * kunne lave nyt event
			 * location, createdby, start tid, slut tid, fagets navn
			 * om det er forelaesning eller oevelsestime
			 * customevent og active er sat true default
			 * calendarid
			 */
		case "createEvents":
			createEvents CE = (createEvents)gson.fromJson(jsonString, createEvents.class);
			answer = SW.addNewEvent(CE.getLocation(),CE.getCreatedby(), CE.getStart(),CE.getEnd(), CE.getDescription(),
					                CE.getType(), CE.getCustomevent(),CE.getAktiv(),CE.getCalendarid());
			break; 
			
			/**
			 * kun admin. fjern event. 
			 * kan fjerne alle
			 */
		case "removeEvent":
			removeEvent DE = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			answer = SW.removeEvent(DE.getDescription());		
			break;
			
			/**
			 * brugers fjern event
			 * kraever han er creator af det
			 */
		case "removeEventUser":
			removeEvent eg = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			answer = SW.removeEventUser(eg.getDescription(),eg.getCreatedby());		
			break;
		
			
		/*************
		 ** NOTES **
		*************/
			
			/**
			 * laver ny note
			 * note skal bruge eventid
			 * createdby og en tekst besked
			 */
		case "createNote":
			createNote SN = (createNote)gson.fromJson(jsonString, createNote.class);
			answer = SW.createNote(SN.getEventid(),SN.getCreatedby(),SN.getNote());
			break;
			
			/**
			 * laver arraylist af de adspurgte notes
			 * bruger eventid til at soege i dem
			 */
		case "getNote":
			getNote GN =(getNote)gson.fromJson(jsonString, getNote.class);
			ArrayList<getNote> getnotes = SW.GetNote(GN.getEventid());
			answer = gson.toJson(getnotes);
			break;
					
			/**
			 * fjerner note med 
			 * note id (PK)
			 */
		case "removeNote":
			removeNote DN = (removeNote)gson.fromJson(jsonString, removeNote.class);
			answer = SW.removeNote(DN.getNoteID(), DN.getCreatedby());
			break;

		/**********
		 ** QUOTE **
		 **********/
			
			/**
			 * henter quote
			 */
		case "getQuote":
		answer = SW.getQuote();
		break;

		/************
		 ** WEATHER **
		 ************/
		/**
		 * refresh sletter gammel data
		 * og ligger ny ind i databasen
		 * bliver lagt i et arraylist
		 */
		case "getForecast":	
			ft.refreshForecast();
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
		} else if (ID.contains("getSubEvents")) {
			return "getSubEvents";
		} else if (ID.contains("getAllCalendar")) {
			return "getAllCalendar";
		} else if (ID.contains("createNote")) {
			return "createNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
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
		} else if (ID.contains("subscribeCalendar")) {
			return "subscribeCalendar";
		} else if (ID.contains("activateCalendar")) {
			return "activateCalendar";
		} else if (ID.contains("shareCalendar")) {
			return "shareCalendar";
		} else if (ID.contains("removeEventUser")) {
			return "removeEventUser";
		} else if (ID.contains("removeNote")) {
			return "removeNote";
		} else if (ID.contains("unSubscribeCalendar")) {
			return "unSubscribeCalendar";
		}
		else
			return "error";
	}
}
