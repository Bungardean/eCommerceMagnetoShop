package it.unisalento.magneto_shop._1_view;


import it.unisalento.magneto_shop._2_action_listener.RegistrationListener;
import it.unisalento.magneto_shop._3_business.PathImmagini;

import javax.swing.*;
import java.awt.*;

public class Registration_GUI extends JPanel{

	//private static Registration_GUI instance;

	private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel background = new JLabel(img);
	private JLabel titleLabel = new JLabel("Registrazione");
	private JLabel givenNamesLabel =new JLabel("Nome*");
	private JLabel surnameLabel = new JLabel("Cognome*");
	private JLabel addressLabel = new JLabel("Indirizzo*");
	private JLabel phoneLabel = new JLabel("Telefono*");
	private JLabel emaiLabel = new JLabel("Email*");
	private JLabel userNameLabel = new JLabel("Username*");
	private JLabel password1Label = new JLabel("Password*");
	private JLabel password2Label = new JLabel("Password*");
	private JLabel campiobLabel = new JLabel("* Campi obbligatori");

	private JTextField givenNamesTextField = new JTextField();
	private JTextField surnameTextField = new JTextField();
	private JTextField addressTextField =new JTextField();
	private JTextField phoneTextField =new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField userNameTextField = new JTextField();
	private JTextField passwordTextField1 = new JTextField();
	private JTextField passwordTextField2 = new JTextField();

	private JButton confirmButton = new JButton("Conferma");
	private JButton cancelButton = new JButton("Indietro");


	private Font fontTitle = new Font("Impact",Font.BOLD | Font.ITALIC,45 );
	private Font font = new Font ("Impact", Font.BOLD | Font.ITALIC,20);
	private Font smallFont = new Font("Impact",Font.ITALIC,15);

	private RegistrationListener registerListener = new RegistrationListener(this);

    /* COSTRUCTOR */

    public Registration_GUI(){

		this.setLayout(null);
		this.setBounds(0,0,800,600);


		/* COMPONENTS PLACEMENT SECTION */

		background.setBounds(0,0,800,600);

		titleLabel.setBounds(270,25,800,50);

		givenNamesLabel.setBounds(50,110,150,30);
		surnameLabel.setBounds(50,150,100,30);
		addressLabel.setBounds(50,200,100,30);
		phoneLabel.setBounds(50,250,100,30);
		emaiLabel.setBounds(50,300,150,30);
		userNameLabel.setBounds(50,350,100,30);
		password1Label.setBounds(50,400,100,30);
		password2Label.setBounds(50,450,150,30);
		campiobLabel.setBounds(50,530,150,30);

		givenNamesTextField.setBounds(160,110,220,30);
		surnameTextField.setBounds(160,150,220,30);
		addressTextField.setBounds(160,200,220,30);
		phoneTextField.setBounds(160,250,220,30);
		emailTextField.setBounds(160,300,220,30);
		userNameTextField.setBounds(160,350,220,30);
		passwordTextField1.setBounds(160,400,220,30);
		passwordTextField2.setBounds(160,450,220,30);

		cancelButton.setBounds(245,500,150,50);
		confirmButton.setBounds(425,500,150,50);

		/* COMPONENTS FONT SECTION */

		titleLabel.setFont(fontTitle);
		titleLabel.setForeground(Color.ORANGE);

		givenNamesLabel.setFont(font);
		givenNamesLabel.setForeground(Color.WHITE);
		surnameLabel.setFont(font);
		surnameLabel.setForeground(Color.WHITE);
		emaiLabel.setFont(font);
		emaiLabel.setForeground(Color.WHITE);
		phoneLabel.setFont(font);
		phoneLabel.setForeground(Color.WHITE);
		addressLabel.setFont(font);
		addressLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(font);
		userNameLabel.setForeground(Color.WHITE);
		password1Label.setFont(font);
		password1Label.setForeground(Color.WHITE);
		password2Label.setFont(font);
		password2Label.setForeground(Color.WHITE);
		campiobLabel.setFont(smallFont);
		campiobLabel.setForeground(Color.ORANGE);

		givenNamesTextField.setFont(font);
		surnameTextField.setFont(font);
		addressTextField.setFont(font);
		phoneTextField.setFont(font);
		emailTextField.setFont(font);
		userNameTextField.setFont(font);
		passwordTextField1.setFont(font);
		passwordTextField2.setFont(font);

		confirmButton.setFont(smallFont);
		cancelButton.setFont(smallFont);

		/* COMPONENTS ADD SECTION*/

		background.add(phoneLabel);
		background.add(addressLabel);
		background.add(titleLabel);
		background.add(givenNamesLabel);
		background.add(surnameLabel);
		background.add(emaiLabel);
		background.add(userNameLabel);
		background.add(password1Label);
		background.add(password2Label);
		background.add(campiobLabel);

		background.add(phoneTextField);
		background.add(givenNamesTextField);
		background.add(surnameTextField);
		background.add(addressTextField);
		background.add(emailTextField);
		background.add(userNameTextField);
		background.add(passwordTextField1);
		background.add(passwordTextField2);

		add(background);
		background.add(titleLabel);
		background.add(confirmButton);
		background.add(cancelButton);

		/* ACTION LISTENER MODELING */

		confirmButton.addActionListener(registerListener);
		confirmButton.setActionCommand(confirmButton.getText());
		cancelButton.addActionListener(registerListener);
		cancelButton.setActionCommand(cancelButton.getText());
		passwordTextField2.addActionListener(registerListener);
		passwordTextField2.setActionCommand(confirmButton.getText());

	}

	/* METHODS USED TO EXTRACT DATA FORM THE TEXT FIELDS */
    public JTextField getGivenNamesTextField() { 	 return givenNamesTextField; }
    public JTextField getSurnameTextField() {		 return surnameTextField; }
    public JTextField getEmailTextField() {    		 return emailTextField; }
    public JTextField getUserNameTextField() { 		 return userNameTextField; }
    public JTextField getPasswordTextField1() { 	 return passwordTextField1; }
    public JTextField getPasswordTextField2() {		 return passwordTextField2; }
	public JTextField getAddressTextField() { return addressTextField; }
	public JTextField getPhoneTextField() { return phoneTextField; }


}
