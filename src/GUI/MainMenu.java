package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;

@SuppressWarnings("unused")
public class MainMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel lblMainMenu;
	private JButton btnLogOut;
	private JButton btnUserlist;
	private JButton btnEventlist;
	private JButton btnNotelist;
	private JLabel lblCBSlogo;
	private JButton btnCalendars;
	
	

	//Sync
	public MainMenu() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setForeground(Color.WHITE);
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 78));
		lblMainMenu.setBounds(481, 90, 404, 90);
		add(lblMainMenu);
		
		btnUserlist = new JButton("Users");
		btnUserlist.setContentAreaFilled(false);
	
		btnUserlist.setForeground(Color.WHITE);
		btnUserlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnUserlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnUserlist.setBackground(Color.WHITE);
		btnUserlist.setBounds(610, 281, 145, 50);
		add(btnUserlist);
		
		btnEventlist = new JButton("Events");
		btnEventlist.setContentAreaFilled(false);
		btnEventlist.setForeground(Color.WHITE);
		btnEventlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnEventlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnEventlist.setBackground(Color.WHITE);
		btnEventlist.setBounds(610, 342, 145, 50);
		add(btnEventlist);
		
		btnNotelist = new JButton("Notes");
		btnNotelist.setContentAreaFilled(false);
		btnNotelist.setForeground(Color.WHITE);
		btnNotelist.setFont(new Font("Arial", Font.BOLD, 30));
		btnNotelist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnNotelist.setBackground(Color.WHITE);
		btnNotelist.setBounds(610, 220, 145, 50);
		add(btnNotelist);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogOut.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(610, 583, 145, 50);
		add(btnLogOut);
		
		btnCalendars = new JButton("Calendars");
		btnCalendars.setForeground(Color.WHITE);
		btnCalendars.setFont(new Font("Arial", Font.BOLD, 28));
		btnCalendars.setContentAreaFilled(false);
		btnCalendars.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnCalendars.setBackground(Color.WHITE);
		btnCalendars.setBounds(610, 403, 145, 50);
		add(btnCalendars);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);

	}
	public void addActionListener(ActionListener X) {
		btnLogOut.addActionListener(X);
		btnEventlist.addActionListener(X);
		btnCalendars.addActionListener(X);
		btnNotelist.addActionListener(X);
		btnUserlist.addActionListener(X);
	
		
		
	}
	public JButton getBtnUserlist() {
		return btnUserlist;
	}
	public JButton getBtnEventlist() {
		return btnEventlist;
	}
	public JButton getBtnNotelist() {
		return btnNotelist;
	}
	public JButton getBtnLogOut() {
		return btnLogOut;
	}
	public JButton getBtnCalendars() {
		return btnCalendars;
	}
}
