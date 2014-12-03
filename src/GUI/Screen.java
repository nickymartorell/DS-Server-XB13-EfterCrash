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
	public static final String ADDCOURSE = "ADDCOURSE";
	public static final String ADDUSER = "ADDUSER";
	public static final String ADDCOURSEFORM = "ADDCOURSEFORM";
	
	private JPanel contentPane;
	public Login login;
	private  MainMenu mainMenu;
	private  UserInfo userInfo;
	private  NoteList noteList;
	private  UserList userList;	
	private  EventList eventList;
	private AddCourse addCourse;
	private  AddUser addUser; 
	private AddEvent addCourseForm;
	
	CardLayout c;


	
//	  //Launch the application.
//	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Screen frame = new Screen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		
		//userList = new UserList();
		//contentPane.add(userList, USERLIST);
	
        eventList = new EventList();
        contentPane.add(eventList, EVENTLIST);
		
		addCourse = new AddCourse();
		contentPane.add(addCourse, ADDCOURSE);
		
		addUser = new AddUser();
		contentPane.add(addUser, ADDUSER);
		
		addCourseForm = new AddEvent();
		contentPane.add(addCourseForm, ADDCOURSEFORM);
		
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
	public AddCourse getAddCourse() {
		return addCourse;
	}
	public AddUser getAddUser() {
		return addUser;
	}
	public AddEvent getAddEvent() {
		return addCourseForm;
	}
}
