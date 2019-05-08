package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.DepartmentListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DepartmentGUI extends JPanel {

    private static DepartmentGUI instance;

    private ArrayList<Department> departmentArrayList =  new ArrayList<>();

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");

    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Gestione Reparto");
    private JLabel newDepartmentLabel = new JLabel("Nuovo reparto");
    private JLabel editDepartmentLabel = new JLabel("Modifica reparto");
    private JLabel deleteDepartmentLabel = new JLabel("Elimina reparto");
    private JLabel editNameDepartmentLabel = new JLabel("Nuovo nome");

    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel1 = new JPanel(null);
    private JPanel  panel2 = new JPanel(null);
    private JPanel  panel3 = new JPanel(null);

    private JTextField departmentTextField =  new JTextField();
    private JTextField editDepartmentTextField = new JTextField();

    private JButton addDepartmentButton = new JButton("Aggiungi");
    private JButton editDepartmentButton = new JButton("Modifica");
    private JButton deleteDepartmentButton = new JButton("Elimina");


    private JButton returnButton = new JButton("Indietro");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );
    private DepartmentListener departmentListener = new DepartmentListener(this);

    private String col[] = {"Reparti"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private DefaultComboBoxModel<String> modelDepartment =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> modelDepartmentDel =new DefaultComboBoxModel<>();

    private JTable table ;
    private JComboBox<String> departmentBoxEdit;
    private JComboBox<String> departmentBoxDel;
    private JScrollPane scrollPane;


    public DepartmentGUI() {

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        pane.add("Nuovo Reparto", panel1);
        pane.add("Modifica Reparto", panel2);
        pane.add("Elimina Reparto", panel3);
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
        titleLabel.setBounds(230,10,390,50);
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
        returnButton.addActionListener(departmentListener);
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
        table.setRowHeight(41);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        newDepartmentLabel.setBounds(190,10,140,50);
        departmentTextField.setBounds(340,10,150,50);
        addDepartmentButton.setBounds(490,10,100,50);
        scrollPane.setBounds(190,80,398,250);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        newDepartmentLabel.setFont(font);
        newDepartmentLabel.setForeground(Color.WHITE);
        departmentTextField.setFont(font);
        addDepartmentButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel1.add(scrollPane);
        panel1.add(addDepartmentButton);
        panel1.add(departmentTextField);
        panel1.add(newDepartmentLabel);
        /* ACTION LISTENERS SECTION */
        addDepartmentButton.addActionListener(departmentListener);
        departmentTextField.addActionListener(departmentListener);
        departmentTextField.setActionCommand("Aggiungi");
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
        modelDepartment.addElement("");
        for (Department aDepartmentArrayList : departmentArrayList) {
            modelDepartment.addElement(aDepartmentArrayList.getDepartment());
        }
        departmentBoxEdit = new JComboBox<String>(modelDepartment);

        editDepartmentLabel.setBounds(190,10,160,50);
        departmentBoxEdit.setBounds(350,13,140,50);
        editDepartmentButton.setBounds(490,60,100,50);
        editNameDepartmentLabel.setBounds(190,60,160,50);
        editDepartmentTextField.setBounds(350,60,140,50);
        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        departmentBoxEdit.setFont(fontpic);
        editDepartmentLabel.setFont(font);
        editDepartmentTextField.setFont(font);
        editDepartmentLabel.setForeground(Color.WHITE);
        editNameDepartmentLabel.setFont(font);
        editNameDepartmentLabel.setForeground(Color.WHITE);
        editDepartmentButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel2.add(editDepartmentTextField);
        panel2.add(editNameDepartmentLabel);
        panel2.add(departmentBoxEdit);
        panel2.add(scrollPane);
        panel2.add(editDepartmentButton);
        panel2.add(editDepartmentLabel);
        /* ACTION LISTENERS SECTION */
        editDepartmentButton.addActionListener(departmentListener);
        editDepartmentTextField.setActionCommand("Modifica");
        editDepartmentTextField.addActionListener(departmentListener);
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
        modelDepartmentDel.addElement("");
        for (Department aDepartmentArrayList : departmentArrayList) {
            modelDepartmentDel.addElement(aDepartmentArrayList.getDepartment());
        }
        departmentBoxDel = new JComboBox<String>(modelDepartmentDel);

        deleteDepartmentLabel.setBounds(190,60,160,50);
        departmentBoxDel.setBounds(350,60,140,50);
        deleteDepartmentButton.setBounds(490,60,100,50);

        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        departmentBoxDel.setFont(fontpic);
        deleteDepartmentLabel.setFont(font);
        deleteDepartmentLabel.setForeground(Color.WHITE);
        deleteDepartmentButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel3.add(deleteDepartmentLabel);
        panel3.add(departmentBoxDel);
        panel3.add(deleteDepartmentButton);
        panel3.add(scrollPane);
        /* ACTION LISTENERS SECTION */
        deleteDepartmentButton.addActionListener(departmentListener);
    }
    private void transferDataToTable(){

        departmentArrayList.clear();//INIZIALIZZA

        departmentArrayList = CatalogBusiness.getInstance().getAllDepartmentsBusiness();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        int row = tableModel.getRowCount();
        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Department aDepartmentArrayList : departmentArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String department = aDepartmentArrayList.getDepartment();

            Object[] data = {department};

            tableModel.addRow(data);
        }
    }

    public String getDepartmentEdit(){ return (String) departmentBoxEdit.getSelectedItem(); }
    public JTextField getEditDepartmentTextField() { return editDepartmentTextField; }
    public String getDepartmentBoxDel() { return (String) departmentBoxDel.getSelectedItem();}
    public JTextField getDepartmentTextField() {
        return departmentTextField;
    }

    public void showDeleteSection() { pane.setSelectedIndex(2); }
    public void showEditSection() { pane.setSelectedIndex(1); }
    public static DepartmentGUI getInstance() { return instance; }
    public static void setInstance(DepartmentGUI instance) { DepartmentGUI.instance = instance; }
}
