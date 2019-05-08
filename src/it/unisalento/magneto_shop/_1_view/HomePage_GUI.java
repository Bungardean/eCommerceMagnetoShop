package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.HomePageListener;
import it.unisalento.magneto_shop._3_business.PathImmagini;

import javax.swing.*;
import java.awt.*;

public class HomePage_GUI extends JPanel {


    private static HomePage_GUI instance;
    //ISTANZIAMENTO VARIABILI GRAFICHE

    private ImageIcon imgLabel = new ImageIcon(PathImmagini.returnPath()+"/icon/homeYello.jpg");
    private ImageIcon imgLog   = new ImageIcon(PathImmagini.returnPath()+"/icon/login.png");
    private ImageIcon imgReg   = new ImageIcon(PathImmagini.returnPath()+"/icon/reg.png");

    private JLabel  background = new JLabel(imgLabel);

    private JLabel usernameLabel = new JLabel("Username");
    private JLabel passwordLabel = new JLabel("Password");

    private JTextField usernameTextfield =  new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton loginButton = new JButton(imgLog);
    private JButton registerButton = new JButton(imgReg);
    private JButton catalogButton = new JButton("Catalogo");
    private Font font = new Font("Impact", Font.ITALIC, 20);
    private Font font1 = new Font("SansSerif", Font.ITALIC, 40);

    private HomePageListener homePageListener = new HomePageListener(this);


    /**
     * Create the frame.
     */
    public HomePage_GUI() {

        //SEZIONE INTERFACCIA GRAFICA DIPENDENTE
        //**************************************************************
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);
        this.setBounds(0,0,800,600);

        /* COMPONENTS PLACEMENT SECTION */
        background.setBounds(0,0,800,600);
        usernameLabel.setBounds( 120,15,100,40);
        usernameTextfield.setBounds(220,15, 130,40 );
        passwordLabel.setBounds(370,15,120,40);
        passwordField.setBounds(475,15,130,40);
        loginButton.setBounds(620, 20,30,30);
        registerButton.setBounds(660, 20,30,30);
        catalogButton.setBounds(302,276,185,70);

        /* COMPONENTS FONT SECTION */
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.ORANGE);
        usernameTextfield.setFont(font);
        passwordField.setFont(font);
        passwordLabel.setForeground(Color.ORANGE);
        passwordLabel.setFont(font);
        registerButton.setFont(font);
        catalogButton.setForeground(Color.ORANGE);
        catalogButton.setFont(font1);
        catalogButton.setBorder(null);
        loginButton.setBorder(null);
        registerButton.setBorder(null);
        /* COMPONENTS ADD SECTION */
        background.add(usernameLabel);
        background.add(usernameTextfield);
        background.add(passwordLabel);
        background.add(passwordField);
        background.add(loginButton);
        background.add(registerButton);
        background.add(catalogButton);
        add(background);

        /* ACTION LISTENERS SECTION */
        loginButton.addActionListener(homePageListener);
        loginButton.setActionCommand("Login");
        registerButton.addActionListener(homePageListener);
        registerButton.setActionCommand("Register");
        passwordField.addActionListener(homePageListener);
        passwordField.setActionCommand("Password");//serve per quando si preme invio nel campo passwordfield
        catalogButton.addActionListener(homePageListener);

    }

    public JTextField getUsernameTextfield() {
        return usernameTextfield;
    }
    public JPasswordField getPasswordField() { return passwordField; }

}
