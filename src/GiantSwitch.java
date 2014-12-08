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
		//VIRKER
		//LAV MED IF ACTIVE
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
		
		//VIRKER - ER TIL CLIENT
		case "shareCalendar":
			userevents sa = gson.fromJson(jsonString, userevents.class);
			answer = SW.shareCalendars(sa.getEmail(),sa.getName(),sa.getReceiver());
			break;
			
	    //VIRKER - ER TIL CLIENT
		case "subscribeCalendar":
			userevents uevents = gson.fromJson(jsonString, userevents.class);
			answer = SW.subscribeCalendars(uevents.getEmail(), uevents.getCalendarid());
			break;
			
		//VIRKER
		case "unSubscribeCalendar":
			userevents uee = gson.fromJson(jsonString, userevents.class);
			answer = SW.unSubscribeCalendars(uee.getEmail(), uee.getCalendarid());
			break;			
			
		//VIRKER
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			answer = SW.createNewCalendar(CC.getName(), CC.getCreatedBy(), CC.getPublicOrPrivate());
			break;
		
		//VIRKER
		case "removeCalendar":
			removeCalendar rc = (removeCalendar)gson.fromJson(jsonString, removeCalendar.class);
			System.out.println("SERVER SIDEN" + rc.getName()+rc.getCreatedBy());
			answer = SW.removeCalendar(rc.getCreatedBy(),rc.getName());
			break;
			
		//VIRKER
		case "activateCalendar":
			removeCalendar ra = (removeCalendar)gson.fromJson(jsonString, removeCalendar.class);
			System.out.println("SERVER SIDEN" + ra.getName()+ra.getCreatedBy());
			answer = SW.activateCalendar(ra.getCreatedBy(),ra.getName());
			break;
				
		//VIRKER
		case "getAllCalendar":
			ArrayList<getCalendar> gCal = SW.getAllCalendar();
			System.out.println("Recieved getCalendar");
			answer = gson.toJson(gCal);
			break;

		/*************
		 ** EVENTS **
		 *************/

		//VIRKER 	
		case "getCustomEvents":
			ArrayList<getEvents> cusevents = SW.getCustomEvents();			
			answer = gson.toJson(cusevents);
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;
			
		//VIRKER 	
		case "getSubEvents":
			userevents ge = (userevents)gson.fromJson(jsonString, userevents.class);
			ArrayList<getEvents> subEvents = SW.getSubEvents(ge.getEmail());			
			answer = gson.toJson(subEvents);
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;

		//VIRKER
		case "createEvents":
			createEvents CE = (createEvents)gson.fromJson(jsonString, createEvents.class);
			answer = SW.addNewEvent(CE.getLocation(),CE.getCreatedby(), CE.getStart(),CE.getEnd(), CE.getDescription(),
					                CE.getType(), CE.getCustomevent(),CE.getAktiv(),CE.getCalendarid());
			break; 
			
		//VIRKER - KUN ADMIN
		case "removeEvent":
			removeEvent DE = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			System.out.println("Recieved removeEvent");
			answer = SW.removeEvent(DE.getDescription());		
			break;
			
		//VIRKER
		case "removeEventUser":
			removeEvent eg = (removeEvent)gson.fromJson(jsonString, removeEvent.class);
			System.out.println("Recieved removeEvent");
			answer = SW.removeEventUser(eg.getDescription(),eg.getCreatedby());		
			break;
		
			
		/*************
		 ** NOTES **
		*************/
			
		//VIRKER
		case "createNote":
			createNote SN = (createNote)gson.fromJson(jsonString, createNote.class);
			System.out.println(SN.getEventid()+SN.getCreatedby()+SN.getNote());
			answer = SW.createNote(SN.getEventid(),SN.getCreatedby(),SN.getNote());
			break;
			
		//VIRKER
		case "getNote":
			getNote GN =(getNote)gson.fromJson(jsonString, getNote.class);
			ArrayList<getNote> getnotes = SW.GetNote(GN.getEventid());
			answer = gson.toJson(getnotes);
			System.out.println("ANSWER FRA CLIENT:"+answer);
			break;
					
		//VIRKER
		case "removeNote":
			removeNote DN = (removeNote)gson.fromJson(jsonString, removeNote.class);
			answer = SW.removeNote(DN.getNoteID(), DN.getCreatedby());
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
