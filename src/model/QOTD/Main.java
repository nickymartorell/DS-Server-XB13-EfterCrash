package model.QOTD;

import model.QOTD.QOTDModel;
import GUI.GUILogic;
import model.Forecast.*;

public class Main {
	
	public QOTDModel qm;
	public ForecastTest ft;

	public static void main(String[] args) throws Exception {
		
		GUILogic gl = new GUILogic();
		gl.run();
		
	
		/**
		//Forecast test
		ForecastTest ft = new ForecastTest();
		ft.refreshForecast();
			
		//QOTD GET test 
		QOTDModel qd = new QOTDModel();
		String quote = qd.getQuote();
		
		//QOTD SAVE test 
		qd.saveQuote();
		
		//QOTD PRINT test
		System.out.println(quote);
		
		*/
		
		
		
	
	}

}
