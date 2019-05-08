package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.ManageMemberListener;
import it.unisalento.magneto_shop._3_business.CoreSistemBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageMember_GUI extends JPanel {

    private  String username;

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");

    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Gestione Utente");
    private JLabel memberlabel = new JLabel("Modifica status");

    private JButton returnButton = new JButton("Indietro");
    private JButton enableButton = new JButton("Attiva");
    private JButton disableButton = new JButton("Disattiva");

    private String col[] = {"Utenti Non Attivi"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    private JTable table ;
    private JScrollPane scrollPane;

    private String col1[] = {"Utenti Attivi"};
    private DefaultTableModel tableModelActive = new DefaultTableModel(col1, 0);
    private JTable tableActive ;
    private JScrollPane scrollPaneActive;

    private DefaultComboBoxModel<String> modelMember =new DefaultComboBoxModel<String>();
    private JComboBox<String> memberBox;

    private ArrayList<Member> memberArrayListActive = new ArrayList<>();
    private ArrayList<Member> memberArrayListNotActive = new ArrayList<>();

    private Font font = new Font("SansSerif", Font.BOLD,20 );
    private Font fontpic = new Font("SansSerif", Font.BOLD,15 );

    private ManageMemberListener manageMemberListener = new ManageMemberListener(this);

    public ManageMember_GUI(String username) {

        this.username = username;
        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);


        /* TABLE OF MEMBERS NOT ACTIVE*/
        memberArrayListNotActive.clear();//INIZIALIZZA

        memberArrayListNotActive = CoreSistemBusiness.getInstance().getMemberNotActiveBusiness();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        int row = tableModel.getRowCount();

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Member aMemberArrayListNotActive : memberArrayListNotActive) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String userName = aMemberArrayListNotActive.getUserName();

            Object[] data = {userName};

            tableModel.addRow(data);
        }

        /* TABLE  SECTION */
        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));
        table.getTableHeader().setFont(fontpic);

        scrollPane = new JScrollPane(table);



        /* TABLE OF MEMBERS ACTIVE*/
        memberArrayListActive.clear();//INIZIALIZZA

        memberArrayListActive = CoreSistemBusiness.getInstance().getMemberActiveBusiness();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        row = tableModelActive.getRowCount();

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Member aMemberArrayListActive : memberArrayListActive) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModelActive.removeRow(j);
                }
                row = -1;
            }

            String userName = aMemberArrayListActive.getUserName();

            Object[] data = {userName};

            tableModelActive.addRow(data);
        }

        /* TABLE  SECTION */
        tableActive = new JTable(tableModelActive){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        tableActive.setRowHeight(40);
        tableActive.setPreferredScrollableViewportSize(table.getPreferredSize());
        tableActive.setFillsViewportHeight(true);
        tableActive.setIntercellSpacing(new Dimension(0,5));
        tableActive.getTableHeader().setFont(fontpic);

        scrollPaneActive = new JScrollPane(tableActive);

        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelMember.addElement("");
        for (Member aMemberArrayListNotActive : memberArrayListNotActive) {
            modelMember.addElement(aMemberArrayListNotActive.getUserName());
        }
        for (Member aMemberArrayListNotActive : memberArrayListActive) {
            modelMember.addElement(aMemberArrayListNotActive.getUserName());
        }

        memberBox = new JComboBox<>(modelMember);

        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        titleLabel.setBounds(245,15,410,50);
        returnButton.setBounds(350,500,100,50);
        scrollPane.setBounds(230,200,150,250);
        scrollPaneActive.setBounds(420,200,150,250);
        memberlabel.setBounds(225,95,162,50);
        memberBox.setBounds(420,100,152,50);
        enableButton.setBounds(230,150,150,40);
        disableButton.setBounds(420,150,150,40);
        /* FONT SECTION */
        titleLabel.setFont(new Font("Impact",  Font.ITALIC,45 ));
        titleLabel.setForeground(Color.ORANGE);
        memberlabel.setFont(font);
        memberlabel.setForeground(Color.WHITE);
        table.setFont(fontpic);
        tableActive.setFont(fontpic);
        returnButton.setFont(fontpic);
        enableButton.setFont(fontpic);
        disableButton.setFont(fontpic);
        memberBox.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        add(memberlabel);
        add(enableButton);
        add(disableButton);
        add(memberBox);
        add(scrollPaneActive);
        add(returnButton);
        add(scrollPane);
        add(titleLabel);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(manageMemberListener);
        enableButton.addActionListener(manageMemberListener);
        disableButton.addActionListener(manageMemberListener);

    }

    public String getMemberBox(){ return (String) memberBox.getSelectedItem(); }
    public ArrayList<Member> getMemberArrayListNotActive() { return memberArrayListNotActive; }
    public ArrayList<Member> getMemberArrayListActive() { return memberArrayListActive; }
    public String getUsername() { return username; }
}
