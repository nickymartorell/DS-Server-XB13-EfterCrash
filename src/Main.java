import model.Forecast.ForecastModel;
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
	}
}
