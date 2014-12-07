package GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import model.QueryBuild.QueryBuilder;
import databaseMethods.SwitchMethods;


@SuppressWarnings("unused")
public class NoteList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private boolean DEBUG = false;
	private final JLabel lblBackground = new JLabel("");
	private JLabel lblHeader;
	private JButton btnDelete;
	private JButton btnMainMenu;
	private JButton btnLogout;
	private JButton btnAdd;
	private JLabel label;
	public  int urObjctInCell;
	private ResultSet rs;
	SwitchMethods sm = new SwitchMethods();
	private JButton btnActivate;

	/**
	 * Create the panel.
	 */
	public NoteList() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		buildTable();
	}
	public void buildTable() {
		String[] columnNames = {"NoteID", "EventID", "Text", "Created by", "Active"};
		
		Object[][] data = new Object[200][200];
		
		try {
			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("notes").all().ExecuteQuery();
			
		int count = 0;
		while (rs.next()) {
			data[count][0] = rs.getInt("noteid");
			data[count][1] = rs.getInt("eventid");
			data[count][2] = rs.getString("note");
			data[count][3] = rs.getString("createdby");
			data[count][4] = rs.getBoolean("isActive");
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
		     urObjctInCell = (int)target.getValueAt(row, 0);
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
		
		btnActivate = new JButton("Activate");
		btnActivate.setOpaque(true);
		btnActivate.setForeground(new Color(0, 0, 205));
		btnActivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnActivate.setBounds(1221, 267, 118, 29);
		add(btnActivate);
		btnActivate.addActionListener(new ActionListener() {
        	
     		public void actionPerformed(ActionEvent arg0) {
     		
     					try {   	    	  
     						sm.activateNoteAdmin(urObjctInCell);
     						
     						} catch (SQLException e1) {
     					
     							e1.printStackTrace();
     			             }
     			            };
     			        } ); 
	
		 //Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(149, 171, 1062, 376);

		// Add the scroll pane to this panel.
		add(scrollPane);
		


		lblHeader = new JLabel("NoteList");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(527, 90, 312, 90);
		add(lblHeader);
		
		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnDelete.setBounds(1222, 227, 118, 29);
		btnDelete.addActionListener(new ActionListener() {
	        	
     		public void actionPerformed(ActionEvent arg0) {
     		
     					try {   	    	  
     						sm.removeNoteAdmin(urObjctInCell);
     						
     						} catch (SQLException e1) {
     					
     							e1.printStackTrace();
     			             }
     			            };
     			        } ); 
		
		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnAdd.setBounds(1221, 187, 118, 29);
		add(btnAdd);
		add(btnDelete);
		
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnMainMenu.setBounds(601, 553, 163, 43);
		add(btnMainMenu);
		
		btnLogout = new JButton("Log out");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogout.setBounds(624, 627, 117, 43);
		add(btnLogout);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(NoteList.class.getResource("/Images/CBSLogo3.png")));
		label.setBounds(10, 698, 250, 59);
		add(label);
		lblBackground.setIcon(new ImageIcon(NoteList.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		
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
	public void addActionListener(ActionListener l) {
		btnDelete.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnAdd.addActionListener(l);
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}
}

	
