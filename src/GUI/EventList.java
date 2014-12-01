	package GUI;

	import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	import javax.swing.JTextField;
import javax.swing.JLabel;

	import java.awt.Font;

	import javax.swing.ImageIcon;

	import java.awt.Color;

	import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import java.awt.Dimension;


	@SuppressWarnings("unused")
	public class EventList extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Create the panel.
		 */
		
		private JButton btnAdd;
		private JButton btnDelete;
		private JButton btnLogout;
		private JButton btnMainMenu;
		
		
		public EventList() {
			setSize(new Dimension(1366, 768));
			setLayout(null);

			JLabel lblEvents = new JLabel("Eventlist");
			lblEvents.setForeground(Color.WHITE);
			lblEvents.setFont(new Font("Arial", Font.BOLD, 78));
			lblEvents.setBounds(521, 90, 323, 90);
			add(lblEvents);

			JLabel lblUpcomingEvent = new JLabel("Upcomming Events:");
			lblUpcomingEvent.setFont(new Font("Arial", Font.BOLD, 27));
			lblUpcomingEvent.setForeground(Color.WHITE);
			lblUpcomingEvent.setBounds(51, 140, 309, 33);
			add(lblUpcomingEvent);

			
			//Laver tabellen inde i Eventlisten.
			String[] columnNames = { "Event", "Date", "Note", "" };

			Object[][] data = {

					{ "D�K Julefrokost", "11.11.2022", "Game on!", new Boolean(false) },
					{ "D�K Julefrokost", "11.11.2022", "Game on!", new Boolean(true) },
					{ "D�K Julefrokost", "11.11.2022", "Game on!", new Boolean(false) },
					{ "D�K Julefrokost", "11.11.2022", "Game on!", new Boolean(true) },
					{ "D�K Julefrokost", "11.11.2022", "Game on!", new Boolean(false) } };

			final JTable table = new JTable(data, columnNames);
			table.setSurrendersFocusOnKeystroke(true);
			table.setPreferredScrollableViewportSize(new Dimension(500, 100));
			table.setFillsViewportHeight(true);
			table.setRowSelectionAllowed(true);

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
			
			JButton btnMainMenu = new JButton("Main Menu");
			btnMainMenu.setForeground(Color.WHITE);
			btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
			btnMainMenu.setContentAreaFilled(false);
			btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
			btnMainMenu.setBounds(601, 612, 163, 43);
			add(btnMainMenu);
			
			JButton btnLogout = new JButton("Log out");
			btnLogout.setForeground(Color.WHITE);
			btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
			btnLogout.setContentAreaFilled(false);
			btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
			btnLogout.setBounds(624, 667, 117, 43);
			add(btnLogout);
						
						JButton btnDelete = new JButton("Delete");
						btnDelete.setOpaque(true);
						btnDelete.setForeground(new Color(0, 0, 205));
						btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
						btnDelete.setBounds(988, 194, 118, 29);
						add(btnDelete);
						
						JButton btnAdd = new JButton("Add");
						btnAdd.setOpaque(true);
						btnAdd.setForeground(new Color(0, 0, 205));
						btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
						btnAdd.setBounds(988, 234, 118, 29);
						add(btnAdd);
						//btnAdd.addActionListener();
						JLabel label = new JLabel("");
						label.setIcon(new ImageIcon(EventList.class
								.getResource("/Images/MetalBackground.jpg")));
						label.setBounds(-26, -28, 1366, 768);
						add(label);

		}
		
		public void addActionListener(ActionListener x) {
			btnAdd.addActionListener(x);
			btnDelete.addActionListener(x);
			btnLogout.addActionListener(x);
			btnMainMenu.addActionListener(x);
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

