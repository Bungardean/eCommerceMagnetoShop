package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.CategoryListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Category_GUI extends JPanel {

    private static Category_GUI instance;


    private ArrayList<Category> categoryArrayList =  new ArrayList<>();

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");

    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Gestione Categorie");
    private JLabel newCategoryLabel = new JLabel("Nuova categoria");
    private JLabel editCategoryLabel = new JLabel("Modifica categoria");
    private JLabel deleteCategoryLabel = new JLabel("Elimina categoria");
    private JLabel editNameCategoryLabel = new JLabel("Nuovo nome");


    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel1 = new JPanel(null);
    private JPanel  panel2 = new JPanel(null);
    private JPanel  panel3 = new JPanel(null);

    private JTextField categoryTextField = new JTextField();
    private JTextField editCategoryTextField= new JTextField();

    private JButton addCategoryButton = new JButton("Aggiungi");
    private JButton editCategoryButton = new JButton("Modifica");
    private JButton deleteCategoryButton = new JButton("Elimina");



    private JButton returnButton = new JButton("Indietro");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );
    private CategoryListener categoryListener = new CategoryListener(this);

    private String col[] = {"Categorie"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private DefaultComboBoxModel<String> modelCategory =new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> modelCategoryDel =new DefaultComboBoxModel<>();

    private JTable table ;
    private JComboBox<String> categoryBoxEdit;
    private JComboBox<String> categoryBoxDel;
    private JScrollPane scrollPane;


    public Category_GUI() {

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        pane.add("Nuova Categoria", panel1);
        pane.add("Modifica Categoria", panel2);
        pane.add("Elimina Categoria", panel3);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        transferData2Table();
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
        returnButton.addActionListener(categoryListener);
    }

    private void setPanel1(){

        /* TABLE  SECTION */
        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        newCategoryLabel.setBounds(190,10,140,50);
        categoryTextField.setBounds(340,10,150,50);
        addCategoryButton.setBounds(490,10,100,50);
        scrollPane.setBounds(190,80,398,250);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        newCategoryLabel.setFont(font);
        newCategoryLabel.setForeground(Color.WHITE);
        categoryTextField.setFont(font);
        addCategoryButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel1.add(scrollPane);
        panel1.add(addCategoryButton);
        panel1.add(categoryTextField);
        panel1.add(newCategoryLabel);
        /* ACTION LISTENERS SECTION */
        addCategoryButton.addActionListener(categoryListener);
        categoryTextField.addActionListener(categoryListener);
        categoryTextField.setActionCommand("Aggiungi");
    }
    private void setPanel2(){


        /* ACTION LISTENERS SECTION */
        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        modelCategory.removeAllElements();
        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelCategory.addElement("");
        for (Category aCategoryArrayList : categoryArrayList) {
            modelCategory.addElement(aCategoryArrayList.getCategoryName());
        }
        categoryBoxEdit = new JComboBox<>(modelCategory);

        editCategoryLabel.setBounds(190,10,160,50);
        categoryBoxEdit.setBounds(350,13,140,50);
        editCategoryButton.setBounds(490,60,100,50);
        editNameCategoryLabel.setBounds(190,60,160,50);
        editCategoryTextField.setBounds(350,60,140,50);
        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        categoryBoxEdit.setFont(fontpic);
        editCategoryLabel.setFont(font);
        editCategoryTextField.setFont(font);
        editCategoryLabel.setForeground(Color.WHITE);
        editNameCategoryLabel.setFont(font);
        editNameCategoryLabel.setForeground(Color.WHITE);
        editCategoryButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel2.add(editCategoryTextField);
        panel2.add(editNameCategoryLabel);
        panel2.add(categoryBoxEdit);
        panel2.add(scrollPane);
        panel2.add(editCategoryButton);
        panel2.add(editCategoryLabel);
        /* ACTION LISTENERS SECTION */
        editCategoryButton.addActionListener(categoryListener);
        editCategoryTextField.addActionListener(categoryListener);
        editCategoryTextField.setActionCommand("Modifica");
    }
    private void setPanel3(){

        /* TABLE SECTION */
        table = new JTable(tableModel){
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        modelCategoryDel.removeAllElements();
        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        modelCategoryDel.addElement("");
        for (Category aCategoryArrayList : categoryArrayList) {
            modelCategoryDel.addElement(aCategoryArrayList.getCategoryName());
        }
        categoryBoxDel = new JComboBox<String>(modelCategoryDel);

        deleteCategoryLabel.setBounds(190,60,160,50);
        categoryBoxDel.setBounds(350,60,140,50);
        deleteCategoryButton.setBounds(490,60,100,50);

        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        categoryBoxDel.setFont(fontpic);
        deleteCategoryLabel.setFont(font);
        deleteCategoryLabel.setForeground(Color.WHITE);
        deleteCategoryButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel3.add(deleteCategoryLabel);
        panel3.add(categoryBoxDel);
        panel3.add(deleteCategoryButton);
        panel3.add(scrollPane);
        /* ACTION LISTENERS SECTION */
        deleteCategoryButton.addActionListener(categoryListener);
    }
    private void transferData2Table(){

        categoryArrayList.clear();//INIZIALIZZA

        categoryArrayList = CatalogBusiness.getInstance().getAllCategoryBusiness();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
         int row = tableModel.getRowCount();
        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Category aCategoryArrayList : categoryArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String category = aCategoryArrayList.getCategoryName();

            Object[] data = {category};

            tableModel.addRow(data);
        }

    }

    public String getItemCategoryEdit(){ return (String) categoryBoxEdit.getSelectedItem(); }
    public JTextField getEditCategoryTextField() { return editCategoryTextField; }
    public String getCategoryBoxDel() { return (String) categoryBoxDel.getSelectedItem();}
    public JTextField getCategoryTextField() { return categoryTextField; }

    public void showDeleteSection() { pane.setSelectedIndex(2); }
    public void showEditSection() { pane.setSelectedIndex(1); }
    public static Category_GUI getInstance() { return instance; }
    public static void setInstance(Category_GUI instance) { Category_GUI.instance = instance; }
}
