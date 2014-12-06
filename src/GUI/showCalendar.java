package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import model.QueryBuild.QueryBuilder;
import databaseMethods.SwitchMethods;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class showCalendar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false;
	private JButton btnSubmit;
	private JButton btnLogout;
	private JButton btnRemove;
	private JLabel lblCBSlogo;
	private JButton btnMainMenu;
	private JLabel lblUserInfo;
	public static JFrame frame;
	public String urObjctInCell;
	private ResultSet rs;

	SwitchMethods sm = new SwitchMethods();
	private JTable table;

	/**
	 * Create the panel.
	 */
	public showCalendar() {
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		buildTable();

		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(UserInfo.class
				.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);

		JLabel lblBackground = new JLabel("");
		lblBackground.setSize(new Dimension(1366, 768));
		lblBackground.setIcon(new ImageIcon(UserInfo.class
				.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);
	}

	public void buildTable() {
		String[] columnNames = { "Name", "CreatedBy", "PublicPrivate",
				"Active", };

		Object[][] data = new Object[200][200];

		try {
			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("calendar").all().ExecuteQuery();

			int count = 0;
			while (rs.next()) {
				data[count][0] = rs.getString("Name");
				data[count][1] = rs.getString("CreatedBy");
				data[count][2] = rs.getBoolean("PrivatePublic");
				data[count][3] = rs.getBoolean("Active");
				count++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		table = new JTable(data, columnNames);
		table.setBounds(-31, 32, 725, 456);
		add(table);
		table.addMouseListener(new MouseAdapter() { 
		public void mouseClicked(final MouseEvent e) {
			final JTable target = (JTable)e.getSource();
		    int row = target.getSelectedRow();
		    //column sat til 1 for altid at bruge email
		    System.out.println("SKER DER NOGET HER");
		     urObjctInCell = (String)target.getValueAt(row, 0);
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

		btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Arial", Font.BOLD, 30));
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnRemove.setBounds(1155, 504, 153, 43);
		add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
	        	
      		public void actionPerformed(ActionEvent arg0) {
      		
      	       try {   	    
      				sm.removeCalendarAdmin(urObjctInCell);
      						
      			} catch (SQLException e1) {
      					
      				e1.printStackTrace();
      			            }
      			            };
      			        } ); 
		

		btnSubmit = new JButton("Add");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));

		btnSubmit.setBounds(1155, 450, 153, 43);
		add(btnSubmit);

		btnLogout = new JButton("Log out");
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(607, 698, 117, 43);
		add(btnLogout);

		btnMainMenu = new JButton("Main menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnMainMenu.setBounds(552, 644, 217, 43);
		add(btnMainMenu);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(204, 171, 941, 376);

		// Add the scroll pane to this panel.
		add(scrollPane);

		lblUserInfo = new JLabel("Calendars");
		lblUserInfo.setForeground(Color.WHITE);
		lblUserInfo.setFont(new Font("Arial", Font.BOLD, 78));
		lblUserInfo.setBounds(477, 25, 464, 90);
		add(lblUserInfo);
	}

	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

	}

	public void addActionListener(ActionListener X) {
		btnRemove.addActionListener(X);
		btnSubmit.addActionListener(X);
		btnLogout.addActionListener(X);
		btnMainMenu.addActionListener(X);

	}

	public JTextField getTextField_Type() {
		return getTextField_Type();
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
}
