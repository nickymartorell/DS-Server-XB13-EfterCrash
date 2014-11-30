package model.Forecast;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Model;
import model.QueryBuild.QueryBuilder;

public class ForecastTest extends Model {

	//smider den friske data i db
    public void Forecast2db() {

    	try{
            ForecastModel fm = new ForecastModel();
            QueryBuilder qb = new QueryBuilder();
            ArrayList<ForecastArray> forecastList = fm.requestForecast();

            String[] fields = {"date", "des", "cels"};
            
            for(int i = 0; i < forecastList.size(); i++) {
            	
            String date = forecastList.get(i).getDate();
            String des = forecastList.get(i).getDesc();
            String cels = forecastList.get(i).getCelsius();
            String[] values = {date,des,cels};
         		
            qb.insertInto("forecast",fields).values(values).Execute();         
            }
          }
        catch (Exception e){
        }
    }
    public void refreshForecast(){
        try{
        	//rydder gammelt data
            PreparedStatement ps = doQuery("TRUNCATE TABLE forecast;");
            ps.execute();
            Forecast2db();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}