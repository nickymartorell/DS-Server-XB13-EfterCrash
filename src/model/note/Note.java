package model.note;

import java.sql.SQLException;

import model.Model;
import model.QueryBuild.*;

public class Note extends Model{
	
	NoteModel notes = new NoteModel(0, null, null, 0, 0, null);
	QueryBuilder qb = new QueryBuilder(); 
	
	
	//LAVER EN NY NOTE
		public void CreateNote(
			int noteid,
			int eventid,
			String text,
			String createdby,
			int isActive)
	
				{
			
			String nId = String.valueOf(noteid);
			String eId = String.valueOf(eventid);
			
			String[] fields = {"noteId", "eventid", "text", "createdby","isActive"};
			String[] values = {nId, eId, text,createdby, String.valueOf(isActive)};
			try {
				qb.insertInto("notes", fields).values(values).Execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//SAETTER NOTE INAKTIV
		public void DeleteNote (int noteID) throws SQLException {			
					notes = GetNote(noteID);
					notes.setActive(0);
					SaveNote(notes);					
				}
		
		//HENTER NOTE
		public NoteModel GetNote (int noteID) throws SQLException{
			
			try {
				resultSet = qb.selectFrom("notes").where("noteID", "= ", String.valueOf(noteID)).ExecuteQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				while(resultSet.next()){
					notes = new NoteModel(
							resultSet.getInt("noteID"), 
							resultSet.getString("text"), 
							resultSet.getString("dateTime"), 
							noteID, 
							resultSet.getInt("Active"), 
							resultSet.getString("createdBy"));
				}
					return notes;
		}	
		
		//OPDATERE EN ALLEREDE GENERET NOTE
		public void SaveNote (NoteModel note){
			
			String text = note.getText();
			String dateTime = note.getDateTime();
			//String createdBy = note.getCreatedBy();
			int isActive = note.isActive();

			int eventID = note.getEventID();
			int noteID = note.getNoteID();
			
			String[] fields = {"eventID", "createdBy", "text", "dateTime", "Active"};
			String[] values = {String.valueOf(noteID), text, dateTime, String.valueOf(isActive)};
			qb.update("notes", fields, values).where("noteID", "=", String.valueOf(noteID));
				
		}
}
