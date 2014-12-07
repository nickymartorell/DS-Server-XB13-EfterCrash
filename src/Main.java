import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import GUI.GUILogic;
import GUI.Screen;
import config.Configurations;

public class Main {
	public QOTDModel qm;
	public ForecastTest ft;
	private GUILogic gl;
	private Screen screen;
	
	public static void main(String[] args) {
		
		GUILogic gl = new GUILogic();
		gl.run();
//		Configurations cf = new Configurations();
//		
//		cf.ReadFile();
//		
//		System.out.println(cf.getPassword());
//		
//		new GUILogic().run();
	}

}
////Forecast test
//ForecastTest ft = new ForecastTest();
//ft.refreshForecast();
//
//ForecastModel fm = new ForecastModel();
//
//
//	
////QOTD GET test 
//QOTDModel qd = new QOTDModel();
//String quote = qd.getQuote();
//
////QOTD SAVE test 
//qd.saveQuote();
//
////QOTD PRINT test
//System.out.println(quote);