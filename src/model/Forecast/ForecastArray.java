package model.Forecast;

import java.sql.Date;

/**
 * Created by danielfranch on 16/10/14.
 * Constructor til ForecastModel Arraylist
 */
public class ForecastArray {

    private String date;
    private String celsius;
    private String desc;
    private Date tidspunkt;

    // Funktion som setter dato, grader og beskrivelse til Forecast objektet
    public ForecastArray(String date, String celsius, String desc) {
        super();
    	this.date = date;
        this.celsius = celsius;
        this.desc = desc;
    }
    
    // Settere og gettere for Forecast klassen
    public String getDate() {
        return date;
    }

    public ForecastArray() {
    	
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public Date getTidspunkt() {
        return tidspunkt;
    }

    public void setDateDate(Date tidspunkt) {
    	
    	this.tidspunkt = tidspunkt;
    }
   
    public String getDato() {
        return date;
    }
    
    // Returnere vejrudsigten som en json tekststreng
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", celsius='" + celsius + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
