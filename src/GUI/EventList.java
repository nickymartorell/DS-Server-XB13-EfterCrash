	package GUI;

	import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

	import javax.swing.JTextField;
import javax.swing.JLabel;

	import java.awt.Font;

	import javax.swing.ImageIcon;

	import java.awt.Color;

	import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import databaseMethods.SwitchMethods;
import model.QueryBuild.QueryBuilder;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;


	@SuppressWarnings("unused")
	public class EventList extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Create the panel.
		 */
		private boolean DEBUG = false;
		private JButton btnAdd;
		private JButton btnDelete;
		private JButton btnLogout;
		private JButton btnMainMenu;
		private JButton btnActivate;
		private ResultSet rs;
		public static JFrame frame;
		public String urObjctInCell;
		SwitchMethods sm = new SwitchMethods();
		private JButton button;
		
		
		public EventList() {
			
			setSize(new Dimension(1366, 768));
			setLayout(null);
			
			btnActivate = new JButton("Activate");
			btnActivate.setBackground(Color.WHITE);
			btnActivate.setOpaque(true);
			btnActivate.setForeground(new Color(0, 0, 205));
			btnActivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
			btnActivate.setBounds(988, 234, 118, 29);
			add(btnActivate);
			btnActivate.addActionListener(new ActionListener() {
	        	
        		public void actionPerformed(ActionEvent arg0) {
        		
        	       try {   	        
  
        				sm.activateEventAdmin(urObjctInCell);
        						
        			} catch (SQLException e1) {
        					
        				e1.printStackTrace();
        			            }
        			            };
        			        } ); 

			JLabel lblEvents = new JLabel("Eventlist");
			lblEvents.setForeground(Color.WHITE);
			lblEvents.setFont(new Font("Arial", Font.BOLD, 78));
			lblEvents.setBounds(521, 90, 323, 90);
			add(lblEvents);

			//Laver tabellen inde i Eventlisten.
			String[] columnNames = { "id","Type", "Location", "Start", "End", "description", "aktiv" };

			Object[][] data = new Object[300][300];

	        try {
				QueryBuilder qb = new QueryBuilder();
				rs = qb.selectFrom("events").all().ExecuteQuery();
				
		        int count = 0;
		        while (rs.next()) {
		        	data[count][0] = rs.getString("id");
		        	data[count][1] = rs.getString("type");
		        	data[count][2] = rs.getString("location");
		        	data[count][3] = rs.getDate("start");
		        	data[count][4] = rs.getDate("end");
		        	data[count][5] = rs.getString("description");
		        	data[count][5] = rs.getBoolean("aktiv");
		        	count++;
		        }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}


			final JTable table = new JTable(data, columnNames);
			table.setSurrendersFocusOnKeystroke(true);
			table.setPreferredScrollableViewportSize(new Dimension(500, 100));
			table.setFillsViewportHeight(true);
			table.setRowSelectionAllowed(true);
			table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				final JTable target = (JTable)e.getSource();
			    int row = target.getSelectedRow();
			    //column sat til 0 for altid at bruge id
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
			
			btnActivate = new JButton("Activate");
			btnActivate.setBackground(Color.WHITE);
			btnActivate.setOpaque(true);
			btnActivate.setForeground(new Color(0, 0, 205));
			btnActivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
			btnActivate.setBounds(988, 234, 118, 29);
			add(btnActivate);
			btnActivate.addActionListener(new ActionListener() {
	        	
        		public void actionPerformed(ActionEvent arg0) {
        		
        	       try {   	        
  
        				sm.activateEventAdmin(urObjctInCell);
        						
        			} catch (SQLException e1) {
        					
        				e1.printStackTrace();
        			            }
        			            };
        			        } ); 

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
			scrollPane.setBounds(387, 194, 591, 361);

			// Add the scroll pane to this panel.
			add(scrollPane);
			
			btnMainMenu = new JButton("Main Menu");
			btnMainMenu.setForeground(Color.WHITE);
			btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
			btnMainMenu.setContentAreaFilled(false);
			btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
			btnMainMenu.setBounds(601, 612, 163, 43);
			add(btnMainMenu);
			
			btnLogout = new JButton("Log out");
			btnLogout.setForeground(Color.WHITE);
			btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
			btnLogout.setContentAreaFilled(false);
			btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
			btnLogout.setBounds(624, 667, 117, 43);
			add(btnLogout);
						
			btnDelete = new JButton("Delete");
			btnDelete.setOpaque(true);
			btnDelete.setForeground(new Color(0, 0, 205));
			btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
			btnDelete.setBounds(988, 194, 118, 29);
			add(btnDelete);
			btnDelete.addActionListener(new ActionListener() {
	        	
        		public void actionPerformed(ActionEvent arg0) {
        		
        	       try {   	        
  
        				sm.removeEventAdmin(urObjctInCell);
        						
        			} catch (SQLException e1) {
        					
        				e1.printStackTrace();
        			            }
        			            };
        			        } ); 
						
			btnAdd = new JButton("Add");
			btnAdd.setOpaque(true);
			btnAdd.setForeground(new Color(0, 0, 205));
			btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
			btnAdd.setBounds(988, 283, 118, 29);
			add(btnAdd);
						
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(EventList.class
								.getResource("/Images/MetalBackground.jpg")));
			label.setBounds(-26, -28, 1366, 768);
			add(label);
		

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
		public void addActionListener(ActionListener x) {
			btnAdd.addActionListener(x);
			btnDelete.addActionListener(x);
			btnLogout.addActionListener(x);
			btnMainMenu.addActionListener(x);
			btnActivate.addActionListener(x);
		}

		public JButton getBtnAdd() {
			return btnAdd;
		}

		public JButton getBtnDelete() {
			return btnDelete;
		}

		public JButton getBtnLogout() {
			return btnLogout;
		}

		public JButton getBtnMainMenu() {
			return btnMainMenu;
		}
	}

