package model.calendar;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import model.Model;

/**
 * Created by jesperbruun on 13/10/14.
 */

public class GetCalendarData {
	
	EncryptUserId e = new EncryptUserId();


	//henter data fra URL og l??er ind til en string
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    //Nu har vi alle data liggende i en string (JSON). 
    //Saa bruger vi Google's udviklede library Json string. den kan lave det om til java objekter
    //Events laver en arraylist af Event
    
    /**
     * Allows client to retrieve CBS's calendar and then access it.
     * @throws Exception
     */
    public void getDataFromCalendar() throws Exception {

        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String userId = "mamu13ag";
        String json = readUrl("http://calendar.cbs.dk/events.php/"+userId+"/"+e.getKey(userId)+".json");
        //String json = readUrl("http://calendar.cbs.dk/events.php/caha13ag/02a24d4e002e6e3571227c39e2f63784.json");
        

        Gson gson = new Gson();
        EventCreator events = gson.fromJson(json, EventCreator.class); 

        //tester events activityId's
        for (int i = 0; i < events.getEvents().size(); i++){
              System.out.println(events.getEvents().get(i).getActivityId());
              System.out.println(events.getEvents().get(i).getStart());
              System.out.println(events.getEvents().get(i).getEnd());
              System.out.println(events.getEvents().get(i).getLocation());         
        }
    }
       
    public static void main (String[]args) throws Exception{
    	new GetCalendarData().getDataFromCalendar();
    	
    }
}
