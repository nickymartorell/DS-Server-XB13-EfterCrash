package GUI;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import databaseMethods.SwitchMethods;
import model.QueryBuild.QueryBuilder;
 
public class UserList extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ADD = "add";
	private boolean DEBUG = false;
	private JButton btnAdd;
	private JButton btnDelete; 
	private JButton btnLogout;
	private JButton btnMainMenu;
	private ResultSet rs;
	private GUILogic gl;
	public static JFrame frame;
	public String urObjctInCell;
	SwitchMethods sm = new SwitchMethods();
	
    public UserList() {
    	setLayout(null);
    	gl = new GUILogic();
    	setSize(new Dimension(1366, 768));
    	buildTable();
    }
        public void buildTable() {
        	String[] columnNames = {"UserID",
                    "Email",
                    "Active",
                    "Admin",
                    "Password"};

Object[][] data = new Object[200][200];

try {
QueryBuilder qb = new QueryBuilder();
rs = qb.selectFrom("users").all().ExecuteQuery();

int count = 0;
while (rs.next()) {
	data[count][0] = rs.getInt("userid");
	data[count][1] = rs.getString("email");
	data[count][4] = rs.getString("password");
	data[count][2] = rs.getBoolean("active");
	data[count][3] = rs.getBoolean("admin");
	count++;
}
} catch (SQLException e1) {
e1.printStackTrace();
}

final JTable table = new JTable(data, columnNames);
table.setPreferredScrollableViewportSize(new Dimension(500, 70));
table.setFillsViewportHeight(true);
table.setRowSelectionAllowed(true);
table.addMouseListener(new MouseAdapter() {     
public void mouseClicked(final MouseEvent e) {
	final JTable target = (JTable)e.getSource();
    int row = target.getSelectedRow();
    //column sat til 1 for altid at bruge email
    System.out.println("SKER DER NOGET HER");
     urObjctInCell = (String)target.getValueAt(row, 1);
     System.out.println(urObjctInCell);                         
}
});      
if (DEBUG) {
table.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        printDebugData(table);
    }
});
}     

        JButton btnDelete = new JButton("Delete");
        btnDelete.setOpaque(true);
        btnDelete.setForeground(new Color(0, 0, 205));
        btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnDelete.setBounds(1019, 515, 118, 29);
        add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
        	
        		public void actionPerformed(ActionEvent arg0) {
        		
        	       try {   	    
        	    	   System.out.println("mutter!");
        				sm.deleteUser(urObjctInCell);
        						
        			} catch (SQLException e1) {
        					
        				e1.printStackTrace();
        			            }
        			            };
        			        } ); 
    
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
        scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), null));

        scrollPane.setBounds(417, 225, 590, 360);
        scrollPane.setBounds(388, 225, 591, 361);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		  gl.runAddUser();
        	}
        });	  
        
        JButton btnActivate = new JButton("Activate");
        btnActivate.setOpaque(true);
        btnActivate.setForeground(new Color(0, 0, 205));
        btnActivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnActivate.setBounds(1019, 475, 118, 29);
        add(btnActivate);
        btnActivate.addActionListener(new ActionListener() { 	
    		public void actionPerformed(ActionEvent arg0) {
    		
    	       try {   	    	  
    							sm.activateUser(urObjctInCell);  							
    						} catch (SQLException e1) {
    					
    							e1.printStackTrace();
    			             }
    			            };
    			        } ); 
        
        
        
        btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnAdd.setForeground(new Color(0, 0, 205));
        btnAdd.setOpaque(true);
        btnAdd.setBounds(1019, 556, 118, 29);
        add(btnAdd);
        
        JButton btnLogout = new JButton("Log out");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		  gl.runLogin();
        	}
        });	  
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
        btnLogout.setContentAreaFilled(false);
        btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnLogout.setBounds(642, 688, 152, 44);
        add(btnLogout);
        
        
        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		  gl.runMenu();
        	}
        });
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnMainMenu.setBounds(622, 646, 194, 44);
        add(btnMainMenu);
        
        JLabel lblUserlist = new JLabel("Userlist");
        lblUserlist.setForeground(Color.WHITE);
        lblUserlist.setFont(new Font("Arial", Font.BOLD, 78));

        lblUserlist.setBounds(549, 118, 298, 90);

        lblUserlist.setBounds(534, 90, 298, 90);

        add(lblUserlist);
        

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(UserList.class.getResource("/Images/CBSLogo3.png")));
        lblNewLabel.setBounds(36, 695, 223, 67);
        add(lblNewLabel);
    
        JLabel lblBackground = new JLabel("Background");
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        lblBackground.setBounds(0, 0, 1376, 768);
        add(lblBackground);
    }
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
    	frame = new JFrame("CBS-Calendar-Userlist");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        UserList newContentPane = new UserList();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setVisible(true);
    }
    public static void lukNed(){
    	frame.dispose(); 	
    }
    public void addActionListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnAdd.setActionCommand(ADD);
    	btnDelete.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);	
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}	
}