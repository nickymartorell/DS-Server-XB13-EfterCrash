package model.note;
import java.sql.SQLException;

public class Testnote {
	public static void main (String [] args) throws SQLException{
		int nID = 3;
		//int ia = 1;
		int eID = 11;
		String text = "penis";
		String createdby = "test note";
		//String date = "1000-01-01 00:00:00";
		int cb = 1;
	
		
		Note note = new Note();
		note.CreateNote(nID,eID,text,createdby,cb);
		
		//note.DeleteNote(nID);
	}
}
