package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.MemberListener;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.*;

public class Member_GUI extends JPanel {

    private  String username;

    private ImageIcon imgLabel = new ImageIcon(PathImmagini.returnPath()+"/icon/homeYello.jpg");

    private JLabel backGroundLabel = new JLabel(imgLabel);

    private ImageIcon imgLogout = new ImageIcon(PathImmagini.returnPath()+"/icon/logout1.png");
    private JButton logoutButton = new JButton(imgLogout);

    private ImageIcon imgCart = new ImageIcon(PathImmagini.returnPath()+"/icon/cart.png");
    private JButton cartButton = new JButton(imgCart);

    private ImageIcon imgOrder = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonORD.png");
    private JButton orderButton = new JButton(imgOrder);

    private JLabel memberNameLabel = new JLabel();

    private MemberListener memberListener = new MemberListener(this);
    private JButton catalogButton = new JButton("Catalogo");

    private Font font = new Font("SansSerif", Font.ITALIC, 20);
    private Font font1 = new Font("SansSerif", Font.ITALIC, 40);


    public Member_GUI(Member member) {

        username = member.getUserName();

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);


        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        memberNameLabel.setBounds(240,25,400 ,50);
        logoutButton.setBounds(630,27,100,50);
        catalogButton.setBounds(302,276,185,70);
        cartButton.setBounds(30,0,100,100);
        orderButton.setBounds(38,110,100,100);

        /* COMPONENTS FONT SECTION */
        memberNameLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,45 ));
        memberNameLabel.setForeground(Color.ORANGE);
        memberNameLabel.setText("Benvenuto "+member.getName());
        catalogButton.setForeground(Color.ORANGE);
        catalogButton.setFont(font1);
        catalogButton.setBorder(null);
        logoutButton.setBorder(null);
        cartButton.setBorder(null);
        orderButton.setBorder(null);
        /* COMPONENTS ADD SECTION */
        add(orderButton);
        add(cartButton);
        add(catalogButton);
        add(logoutButton);
        add(memberNameLabel);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        logoutButton.addActionListener(memberListener);
        logoutButton.setActionCommand("Logout");
        catalogButton.addActionListener(memberListener);
        cartButton.setActionCommand("Carrello");
        cartButton.addActionListener(memberListener);
        orderButton.addActionListener(memberListener);
        orderButton.setActionCommand("Ordine");
    }
    public String getUsername() { return username; }

}
