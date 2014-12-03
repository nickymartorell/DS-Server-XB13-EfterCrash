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

	

	public GUILogic(){
		screen = new Screen();


		screen.getLogin().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		//screen.getUserInfo().addActionListener(new UserInfoActionListener());
		screen.getNoteList().addActionListener(new NoteListActionListener());
		//screen.getUserList().addActionListener(new UserListActionListener());
		//screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddCourse().addActionListener(new AddCourseActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());
		

		
		
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
	public void runMenuEventList() {

		screen.show(Screen.MAINMENU);
		screen.setVisible(true);
		EventList.lukNed();
		
	}
	public void runLoginEventList() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
		EventList.lukNed();
		
	}
	public void runAddUser() {

		screen.show(Screen.ADDUSER);
		screen.setVisible(true);
		UserList.lukNed();
		
	}

	
	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				screen.show(Screen.MAINMENU);
			}
			
		}
	
	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String knap = e.getActionCommand();
			
			if(knap.equals(UserList.ADD)) {
				
				
				
			    String firstName = JOptionPane.showInputDialog(null, "UserID", null);
		        String lastName = JOptionPane.showInputDialog(null, "Email", null);
		        String eMail = JOptionPane.showInputDialog(null, "Date", null);
		        String password = JOptionPane.showInputDialog(null, "Write your password", null);
		        
		       
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
				EventList.ShowGUI();
				screen.setVisible(false);
				screen.dispose();
			}
			if (e.getSource() == screen.getMainMenu().getAddCourse()){
				screen.show(Screen.ADDCOURSE);
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
		
			}
		}
	private class AddCourseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddCourse().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddCourse().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
		}
	}
}
	


