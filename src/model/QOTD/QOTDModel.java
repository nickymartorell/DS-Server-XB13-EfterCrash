package model.QOTD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;






import java.util.Date;

import model.Model;
import model.Forecast.ForecastArray;
import model.QueryBuild.QueryBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QOTDModel extends Model {

	private ArrayList<QOTD> qotdlist = new ArrayList<>();
	
	QOTD qotdlist2 = new QOTD(null, null, null);
    QueryBuilder qb = new QueryBuilder();
    
    private ResultSet resultSet;
     
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
    	public void saveQuote() {

            /**
             * getting text from website and putiing into string
             * Making a new object of JSON, and prints out quote
             */
            String json;
			try {
				json = readUrl("http://dist-sso.it-kartellet.dk/quote/");
			
            
    			JSONParser jsonParser = new JSONParser();
    			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
    			
    			String quote = (String) jsonObject.get("quote");
    			String author = (String) jsonObject.get("author");
    			String topic = (String) jsonObject.get("topic");

    			
    			String[] fields = {"quote","author","topic"};
    			
    			String[] values = { quote, author , topic };
    	
       			qb.insertInto("quote", fields).values(values).Execute();
    	
			} catch (Exception e) {
			
				e.printStackTrace();
			}		
    }
     
    /**
     * Retrieve Quote from a website and put it into a String, 
     * Afterwards we will make it into a json object so it can be printed out to the client.
     */
  	public String getQuote(){
  		String q = "";
  		String[] key = {"quote"};
  		try {
  			resultSet = qb.selectFrom("quote").all().ExecuteQuery();
			while(resultSet.next()) {
				q = resultSet.getString("quote");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;
  	}	
  	 public void updateQuote(){
	     	Date date = new Date(); // Current date & time
	     	long maxTimeNoUpdate = 86400; // Maximum one day with no update
	     	
	     	long date1 = date.getTime();
	     	long date2 = date.getTime() - maxTimeNoUpdate; // minus 1 hour -- should be fetched from database
	     	
	     	long timeSinceUpdate = date1 - date2; 
	     	
	     	
	     	// if more than 1 hour ago, do update
	     	if(timeSinceUpdate > 864000){
	     		// return fresh weather data
	     		saveQuote();	
	     	} 
	     } 	
  	 
  	public void refreshQuote(){
        try{
        	//rydder gammelt data
            PreparedStatement ps = doQuery("TRUNCATE TABLE quote;");
            ps.execute();        
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

