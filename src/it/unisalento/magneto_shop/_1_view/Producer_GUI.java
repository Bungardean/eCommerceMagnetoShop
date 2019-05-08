package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.ProducerListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Producer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Producer_GUI extends JPanel{

    private static Producer_GUI instance;

    private ArrayList<Producer> producerArrayList =  new ArrayList<>();

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");

    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Gestione Produttore");
    private JLabel newProducerLabel = new JLabel("Nuovo produttore");
    private JLabel editProducerLabel = new JLabel("Modifica produttore");
    private JLabel deleteProducerLabel = new JLabel("Elimina produttore");
    private JLabel editNameProducerLabel = new JLabel("Nuovo nome");

    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel1 = new JPanel(null);
    private JPanel  panel2 = new JPanel(null);
    private JPanel  panel3 = new JPanel(null);

    private JTextField newProducerTextField = new JTextField();
    private JTextField editProducerTextField =  new JTextField();

    private JButton addProducerButton = new JButton("Aggiungi");
    private JButton editProducerButton = new JButton("Modifica");
    private JButton deleteProducerButton = new JButton("Elimina");


    private JButton returnButton = new JButton("Indietro");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );
    private ProducerListener producerListener = new ProducerListener(this);

    private String col[] = {"Produttori"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private DefaultComboBoxModel<String> modelProducerEdit =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> modelProducerDel =new DefaultComboBoxModel<>();

    private JTable table ;
    private JComboBox<String> producerBoxEdit;
    private JComboBox<String> producerBoxDel;
    private JScrollPane scrollPane;


    public Producer_GUI() {

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        pane.add("Nuovo Produttore", panel1);
        pane.add("Modifica Produttore", panel2);
        pane.add("Elimina Produttore", panel3);
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
        titleLabel.setBounds(210,10,390,50);
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
        returnButton.addActionListener(producerListener);
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
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        newProducerLabel.setBounds(190,10,150,50);
        newProducerTextField.setBounds(340,10,150,50);
        addProducerButton.setBounds(490,10,100,50);
        scrollPane.setBounds(190,80,398,250);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        newProducerLabel.setFont(font);
        newProducerLabel.setForeground(Color.WHITE);
        newProducerTextField.setFont(font);
        addProducerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel1.add(scrollPane);
        panel1.add(addProducerButton);
        panel1.add(newProducerTextField);
        panel1.add(newProducerLabel);
        /* ACTION LISTENERS SECTION */
        addProducerButton.addActionListener(producerListener);
        newProducerTextField.setActionCommand("Aggiungi");
        newProducerTextField.addActionListener(producerListener);
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
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelProducerEdit.addElement("");
        for (Producer aProducerArrayList : producerArrayList) {
            modelProducerEdit.addElement(aProducerArrayList.getProducer());
        }
        producerBoxEdit = new JComboBox<String>(modelProducerEdit);

        editProducerLabel.setBounds(190,10,163,50);
        producerBoxEdit.setBounds(355,13,140,50);
        editProducerButton.setBounds(495,60,95,50);
        editNameProducerLabel.setBounds(190,60,160,50);
        editProducerTextField.setBounds(355,60,140,50);
        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        producerBoxEdit.setFont(fontpic);
        editProducerLabel.setFont(font);
        editProducerTextField.setFont(font);
        editProducerLabel.setForeground(Color.WHITE);
        editNameProducerLabel.setFont(font);
        editNameProducerLabel.setForeground(Color.WHITE);
        editProducerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel2.add(editProducerTextField);
        panel2.add(editNameProducerLabel);
        panel2.add(producerBoxEdit);
        panel2.add(scrollPane);
        panel2.add(editProducerButton);
        panel2.add(editProducerLabel);
        /* ACTION LISTENERS SECTION */
        editProducerButton.addActionListener(producerListener);
        editProducerTextField.addActionListener(producerListener);
        editProducerTextField.setActionCommand("Modifica");
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
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelProducerDel.addElement("");
        for (Producer aProducerArrayList : producerArrayList) {
            modelProducerDel.addElement(aProducerArrayList.getProducer());
        }
        producerBoxDel = new JComboBox<>(modelProducerDel);

        deleteProducerLabel.setBounds(190,60,160,50);
        producerBoxDel.setBounds(350,60,140,50);
        deleteProducerButton.setBounds(490,60,100,50);

        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        producerBoxDel.setFont(fontpic);
        deleteProducerLabel.setFont(font);
        deleteProducerLabel.setForeground(Color.WHITE);
        deleteProducerButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel3.add(deleteProducerLabel);
        panel3.add(producerBoxDel);
        panel3.add(deleteProducerButton);
        panel3.add(scrollPane);
        /* ACTION LISTENERS SECTION */
        deleteProducerButton.addActionListener(producerListener);
    }
    private void transferDataToTable(){

        producerArrayList.clear();//INIZIALIZZA

        producerArrayList = CatalogBusiness.getInstance().getAllProducers();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        int row = tableModel.getRowCount();
        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Producer aProducerArrayList : producerArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String department = aProducerArrayList.getProducer();

            Object[] data = {department};

            tableModel.addRow(data);
        }
    }

    public String getProducerBoxEdit(){ return (String) producerBoxEdit.getSelectedItem(); }
    public JTextField getEditProducerTextField() { return editProducerTextField; }
    public String getProducerBoxDel() { return (String) producerBoxDel.getSelectedItem();}
    public JTextField getNewProducerTextField() {
        return newProducerTextField;
    }

    public void showDeleteSection() { pane.setSelectedIndex(2); }
    public void showEditSection() { pane.setSelectedIndex(1); }
    public static Producer_GUI getInstance() { return instance; }
    public static void setInstance(Producer_GUI instance) { Producer_GUI.instance = instance; }
}
