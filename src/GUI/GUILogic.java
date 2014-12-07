package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GUI.UserInformation;

import javax.swing.JOptionPane;

import databaseMethods.SwitchMethods;
import model.QueryBuild.*;
import GUI.Screen;

@SuppressWarnings("unused")

public class GUILogic {
	private Screen screen;
	private boolean u;
	private boolean full = false;
	String tjek = "";
	SwitchMethods sm = new SwitchMethods();

	public GUILogic(){
		screen = new Screen();
		screen.getLogin().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		screen.getNoteList().addActionListener(new NoteListActionListener());
		screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());
		screen.getAddEvent().addActionListener(new AddEventActionListener());
		screen.getCreateCalendar().addActionListener(new CreateCalendarActionListener());
		screen.getCalendars().addActionListener(new CalendarsActionListener());
		screen.getAddNote().addActionListener(new AddNoteActionListener());
		
	}
	
	public void run() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
		
	}
	public void runMenu() {

		screen.show(Screen.MAINMENU);
		screen.setVisible(true);
		UserList.lukNed();
		
	}
	public void runLogin() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
		UserList.lukNed();
		
	}
	public void runAddUser() {

		screen.show(Screen.ADDUSER);
		screen.setVisible(true);
		UserList.lukNed();
		
	}

	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String user =	screen.getLogin().getTextFieldUsername().getText();
			String pass =	screen.getLogin().getTextFieldPassword().getText();
			boolean isAdmin = true;
			String tjek = null;
			try {
				tjek = sm.authenticate(user, pass, isAdmin);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(tjek.equals("1")){
				screen.show(Screen.MAINMENU);
			}
			else {
				JOptionPane.showMessageDialog (null, "Are you sure you are admin ?!", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			}		
		}

	private class MainMenuActionListener implements ActionListener {
		

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getMainMenu().getBtnLogOut()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()){
				UserList.createAndShowGUI();
				screen.setVisible(false);
				screen.dispose();
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()){
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()){
				screen.show(Screen.EVENTLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnCalendars()){
				screen.show(Screen.CALENDARS);
			}
		}
	}
	private class AddUserActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddUser().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddUser().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
		}
	}
	private class NoteListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getNoteList().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getNoteList().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getNoteList().getBtnAdd()){
				screen.show(Screen.ADDNOTE);
			}
		
			}
		}
	private class AddEventActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddEvent().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddEvent().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddEvent().getBtnSubmit()){
				String location = screen.getAddEvent().getTextField_Location().getText();
				String cb = screen.getAddEvent().getTextField_Createdby().getText();
				String start = screen.getAddEvent().getTextField_Start().getText();
				String end = screen.getAddEvent().getTextField_End().getText();
				String name = screen.getAddEvent().getTextField_Name().getText();
				String type = screen.getAddEvent().getTextField_Type().getText();
				String customevent = "1";
				String aktiv = "1";
				String calendarid = "9"; //ADMINS KALENDER
				try {
					sm.addNewEvent(cb,type , start, end, name,location,customevent,aktiv, calendarid);
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				screen.show(Screen.MAINMENU);
			}
		}
	}
	private class EventListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getEventlist().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getEventlist().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getEventlist().getBtnAdd()){
				screen.show(Screen.ADDEVENT);
			}
			if (e.getSource() == screen.getEventlist().getBtnDelete()){
				screen.show(Screen.MAINMENU);
			}
		}
	}
	
	private class CreateCalendarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getCreateCalendar().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCreateCalendar().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getCreateCalendar().getBtnSubmit()){
				
			String Name =screen.getCreateCalendar().getTextField_Name().getText();
			String CreatedBy = screen.getCreateCalendar().getTextField_CreatedBy().getText();
			String privatePublic =	screen.getCreateCalendar().getTextField_pubpriv().getText();
				try {
					sm.createNewCalendar(Name, CreatedBy, privatePublic);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			screen.show(Screen.MAINMENU);
			}
	}
	}
	private class CalendarsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getCalendars().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCalendars().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getCalendars().getBtnSubmit()){
			screen.show(Screen.CREATECALENDAR);
			//if (e.getSource() == screen.getCalendars().getBtnRemove()){
			//Metode til Remove 
			}
		
			}
		}
	private class AddNoteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddNote().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
		}
			if (e.getSource() == screen.getAddNote().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getCalendars().getBtnSubmit()){
				//Metode til add note 
		}
			
	}
}
}	

	
	
	
	



	


