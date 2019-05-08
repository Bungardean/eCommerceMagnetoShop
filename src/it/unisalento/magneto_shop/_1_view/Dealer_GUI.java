package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.DealerListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Dealer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Dealer_GUI extends JPanel {

    private static Dealer_GUI instance;

    private ArrayList<Dealer> dealerArrayList =  new ArrayList<>();

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");

    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Gestione Distributore");
    private JLabel newDealerLabel = new JLabel("Nuovo distributore");
    private JLabel editDealerLabel = new JLabel("Modifica distributore");
    private JLabel deleteDealerLabel = new JLabel("Elimina distributore");
    private JLabel editNameDealerLabel = new JLabel("Nuovo nome");

    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel1 = new JPanel(null);
    private JPanel  panel2 = new JPanel(null);
    private JPanel  panel3 = new JPanel(null);

    private JTextField newDealerTextField = new JTextField();
    private JTextField editDealerTextField = new JTextField();

    private JButton addDealerButton = new JButton("Aggiungi");
    private JButton editDealerButton = new JButton("Modifica");
    private JButton deleteDealerButton = new JButton("Elimina");


    private JButton returnButton = new JButton("Indietro");

    private Font font = new Font("Impact", Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );
    private DealerListener dealerListener = new DealerListener(this);

    private String col[] = {"Distributori"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private JTable table ;
    private JScrollPane scrollPane;

    private DefaultComboBoxModel<String> modelDealerEdit =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> modelDealerDel =new DefaultComboBoxModel<String>();

    private JComboBox<String> dealerBoxEdit;
    private JComboBox<String> dealerBoxDel;


    public Dealer_GUI() {

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        pane.add("Nuovo Distributore", panel1);
        pane.add("Modifica Distributore", panel2);
        pane.add("Elimina Distributore", panel3);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        transferDataToTable();
        setPanel1();
        setPanel2();
        setPanel3();
        /* COMPONENTS PLACEMENT SECTION */
        pane.setBounds(0,70,800,400);
        backGroundLabel.setBounds(0,0,800,600);
        titleLabel.setBounds(205,10,410,50);
        returnButton.setBounds(350,500,150,50);
        /* COMPONENTS FONT SECTION */
        titleLabel.setFont(new Font("Impact",  Font.ITALIC,45 ));
        titleLabel.setForeground(Color.ORANGE);
        pane.setFont(fontpic);
        returnButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        backGroundLabel.add(pane);
        backGroundLabel.add(titleLabel);
        backGroundLabel.add(returnButton);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(dealerListener);
    }

    private void setPanel1(){


        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };


        /* ACTION LISTENERS SECTION */
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        newDealerLabel.setBounds(190,10,150,50);
        newDealerTextField.setBounds(340,10,150,50);
        addDealerButton.setBounds(490,10,100,50);
        scrollPane.setBounds(190,80,398,250);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        newDealerLabel.setFont(font);
        newDealerLabel.setForeground(Color.WHITE);
        newDealerTextField.setFont(font);
        addDealerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel1.add(scrollPane);
        panel1.add(addDealerButton);
        panel1.add(newDealerTextField);
        panel1.add(newDealerLabel);
        /* ACTION LISTENERS SECTION */
        addDealerButton.addActionListener(dealerListener);
        newDealerTextField.addActionListener(dealerListener);
        newDealerTextField.setActionCommand("Aggiungi");

    }
    private void setPanel2(){

        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        /* ACTION LISTENERS SECTION */
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelDealerEdit.addElement("");
        for (Dealer aDealerArrayList : dealerArrayList) {
            modelDealerEdit.addElement(aDealerArrayList.getDealer());
        }
        dealerBoxEdit = new JComboBox<String>(modelDealerEdit);

        editDealerLabel.setBounds(190,10,172,50);
        dealerBoxEdit.setBounds(370,13,125,50);
        editDealerButton.setBounds(495,60,95,50);
        editNameDealerLabel.setBounds(190,60,160,50);
        editDealerTextField.setBounds(355,60,140,50);
        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        dealerBoxEdit.setFont(fontpic);
        editDealerLabel.setFont(font);
        editDealerTextField.setFont(font);
        editDealerLabel.setForeground(Color.WHITE);
        editNameDealerLabel.setFont(font);
        editNameDealerLabel.setForeground(Color.WHITE);
        editDealerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel2.add(editDealerTextField);
        panel2.add(editNameDealerLabel);
        panel2.add(dealerBoxEdit);
        panel2.add(scrollPane);
        panel2.add(editDealerButton);
        panel2.add(editDealerLabel);
        /* ACTION LISTENERS SECTION */
        editDealerButton.addActionListener(dealerListener);
        editDealerTextField.addActionListener(dealerListener);
        editDealerTextField.setActionCommand("Modifica");
    }
    private void setPanel3(){

        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };


        /* TABLE SECTION */
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelDealerDel.addElement("");
        for (Dealer aDealerArrayList : dealerArrayList) {
            modelDealerDel.addElement(aDealerArrayList.getDealer());
        }
        dealerBoxDel = new JComboBox<>(modelDealerDel);

        deleteDealerLabel.setBounds(190,60,160,50);
        dealerBoxDel.setBounds(350,60,140,50);
        deleteDealerButton.setBounds(490,60,100,50);

        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        dealerBoxDel.setFont(fontpic);
        deleteDealerLabel.setFont(font);
        deleteDealerLabel.setForeground(Color.WHITE);
        deleteDealerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel3.add(deleteDealerLabel);
        panel3.add(dealerBoxDel);
        panel3.add(deleteDealerButton);
        panel3.add(scrollPane);
        /* ACTION LISTENERS SECTION */
        deleteDealerButton.addActionListener(dealerListener);
    }
    private void transferDataToTable(){

        dealerArrayList.clear();//INIZIALIZZA

        dealerArrayList = CatalogBusiness.getInstance().getAllDealers();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        int row = tableModel.getRowCount();
        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Dealer aDealerArrayList : dealerArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String department = aDealerArrayList.getDealer();

            Object[] data = {department};

            tableModel.addRow(data);
        }
    }

    public String getDealerBoxEdit(){ return (String) dealerBoxEdit.getSelectedItem(); }
    public JTextField getEditDealerTextField() { return editDealerTextField; }
    public String getDealerBoxDel() { return (String) dealerBoxDel.getSelectedItem();}
    public JTextField getNewDealerTextField() {
        return newDealerTextField;
    }

    public void showDeleteSection() { pane.setSelectedIndex(2); }
    public void showEditSection() { pane.setSelectedIndex(1); }
    public static Dealer_GUI getInstance() { return instance; }
    public static void setInstance(Dealer_GUI instance) { Dealer_GUI.instance = instance; }
}
