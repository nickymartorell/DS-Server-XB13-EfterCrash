package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.awt.CardLayout;

@SuppressWarnings("unused")
public class Screen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String LOGIN = "LOGIN";
	public static final String MAINMENU = "MAINMENU";
	public static final String USERINFO = "USERINFO";
	public static final String ADDUSERGUI = "ADDUSERGUI";
	public static final String NOTELIST = "NOTELIST";
	public static final String USERLIST = "USERLIST";
	public static final String EVENTLIST = "EVENTLIST";
	public static final String ADDUSER = "ADDUSER";
	public static final String ADDEVENT = "ADDEVENT";
	public static final String ADDNOTE = "ADDNOTE";
	public static final String CALENDARS ="CALENDARS";
	public static final String CREATECALENDAR = "CREATECALENDAR";
	
	
	private JPanel contentPane;
	public Login login;
	private  MainMenu mainMenu;
	private  UserInfo userInfo;
	private  NoteList noteList;
	private  UserList userList;	
	private  EventList eventList;
	private  AddUser addUser; 
	private showCalendar calendars;
	private AddEvent addEvent;
	private AddEvent addCourseForm;
	private CreateCalendar createCalendar;
	private AddNote addNote;
	
	CardLayout c;

	public Screen() {
		setTitle("CBS Calendar Server");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		
		login = new Login();
		contentPane.add(login, LOGIN);
		
		mainMenu = new MainMenu();
		contentPane.add(mainMenu, MAINMENU);
		
		userInfo = new UserInfo();
		contentPane.add(userInfo, USERINFO);
		
		noteList = new NoteList();
		contentPane.add(noteList, NOTELIST);
	
        eventList = new EventList();
        contentPane.add(eventList, EVENTLIST);
		
		addUser = new AddUser();
		contentPane.add(addUser, ADDUSER);
		
		addEvent = new AddEvent();
		contentPane.add(addEvent, ADDEVENT);
		
		calendars = new showCalendar();
		contentPane.add(calendars, CALENDARS);
		
		createCalendar = new CreateCalendar();
		contentPane.add(createCalendar, CREATECALENDAR);
		
		addNote = new AddNote();
		contentPane.add(addNote, ADDNOTE);
		
		c = (CardLayout) getContentPane().getLayout();
		
		
	}
		
		

	public Login getLogin() {
		return login;
	}
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public NoteList getNoteList() {
		return noteList;
	}
	public UserList getUserList() {
		return userList;
	}
	public void show(String card) {
		c.show(getContentPane(),  card);
	}
	public EventList getEventlist() {
		return eventList;
	}
	public AddUser getAddUser() {
		return addUser;
	}
	public AddEvent getAddEvent() {
		return addEvent;
	}
	public showCalendar getCalendars() {
		return calendars;
	}
	public CreateCalendar getCreateCalendar() {
		return createCalendar;
	}
	public AddNote getAddNote(){
		return addNote;
		
	}
}
