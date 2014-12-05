package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import databaseMethods.SwitchMethods;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class CreateCalendar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_Name;
	private JTextField txtForPublic;
	private JTextField textField_CreatedBy;
	private JButton btnSubmit;
	private JButton btnRemove;
	private JButton btnLogout;
	private JLabel lblCBSlogo;
	private JButton btnMainMenu;
	private JLabel lblUserInfo;
	public static JFrame frame;

	
	SwitchMethods sm = new SwitchMethods();
	

	/**
	 * Create the panel.
	 */
	public CreateCalendar() {
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		

		btnLogout = new JButton("Log out");
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(634, 522, 117, 43);
		add(btnLogout);

		textField_Name = new JTextField();
		textField_Name.setForeground(new Color(105, 105, 105));
		textField_Name.setName("");
		textField_Name.setBounds(723, 229, 218, 34);
		add(textField_Name);
		textField_Name.setColumns(10);

		JLabel lblUserID = new JLabel("Name:");
		lblUserID.setForeground(new Color(255, 255, 255));
		lblUserID.setFont(new Font("Arial", Font.BOLD, 26));
		lblUserID.setBounds(479, 226, 104, 30);
		add(lblUserID);
		
		txtForPublic = new JTextField();
		txtForPublic.setText("1 for public. 0 for private");
		txtForPublic.setForeground(new Color(105, 105, 105));
		txtForPublic.setName("");
		txtForPublic.setBounds(723, 319, 218, 34);
		add(txtForPublic);
		txtForPublic.setColumns(10);
		
		JLabel lblpubpriv = new JLabel("Public or private:");
		lblpubpriv.setFont(new Font("Arial", Font.BOLD, 20));
		lblpubpriv.setForeground(new Color(255, 255, 255));
		lblpubpriv.setBounds(479, 330, 164, 30);
		add(lblpubpriv);

		textField_CreatedBy = new JTextField();
		textField_CreatedBy.setForeground(new Color(105, 105, 105));
		textField_CreatedBy.setColumns(10);
		textField_CreatedBy.setBounds(723, 274, 218, 34);
		add(textField_CreatedBy);

		JLabel lblTeam = new JLabel("Created By:");
		lblTeam.setFont(new Font("Arial", Font.BOLD, 26));
		lblTeam.setForeground(new Color(255, 255, 255));
		lblTeam.setBounds(479, 274, 164, 30);
		add(lblTeam);

		btnSubmit = new JButton("Create Calendar");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 27));
		btnSubmit.setBounds(573, 413, 239, 43);
		add(btnSubmit);

		
				
				btnMainMenu = new JButton("Main menu");
				btnMainMenu.setForeground(Color.WHITE);
				btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
				btnMainMenu.setContentAreaFilled(false);
				btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
				btnMainMenu.setBounds(609, 467, 164, 44);
				add(btnMainMenu);
				
				lblUserInfo = new JLabel("Create calendar");
				lblUserInfo.setForeground(Color.WHITE);
				lblUserInfo.setFont(new Font("Arial", Font.BOLD, 78));
				lblUserInfo.setBounds(460, 93, 588, 90);
				add(lblUserInfo);
								
				JLabel lblBackground = new JLabel("");
				lblBackground.setSize(new Dimension(1366, 768));
				lblBackground.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/MetalBackground.jpg")));
				lblBackground.setBounds(0, 0, 1366, 768);
				add(lblBackground);
				
	}	
	public void addActionListener(ActionListener l) {
		btnSubmit.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
	}

	public JTextField getTextField_CreatedBy() {
		return textField_CreatedBy;
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
	public JTextField getTextField_pubpriv() {
		return txtForPublic;
	}
	public void setTextField_pubpriv(JTextField textField_pubpriv) {
		this.txtForPublic = textField_pubpriv;
	}
	public void setTextField_Name(JTextField textField_Name) {
		this.textField_Name = textField_Name;
	}
	public void setTextField_CreatedBy(JTextField textField_CreatedBy) {
		this.textField_CreatedBy = textField_CreatedBy;
	}
	public JTextField getTextField_Name() {
		return textField_Name;
	}
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
}
