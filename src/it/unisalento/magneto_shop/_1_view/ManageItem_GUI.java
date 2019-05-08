package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.ManageItemListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageItem_GUI extends JPanel {

    private static ManageItem_GUI instance;

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private ImageIcon imageIcon = new ImageIcon(PathImmagini.returnPath()+"/icon/imageFolderIcon.png");
    private JLabel backGroundLabel = new JLabel(img);

    private JLabel itemTitleLabel = new JLabel("Gestione Prodotto");
    private JLabel newItemNameLabel = new JLabel("Nome");
    private JLabel newItemDescriptionLabel = new JLabel("Descrizione");
    private JLabel newItemPriceLabel = new JLabel("Prezzo");
    private JLabel newItemSalesPriceLabel = new JLabel("Prezzo scontato");
    private JLabel newItemFotoLabel = new JLabel("Foto");
    private JLabel newItemCategoryLabel = new JLabel("Categoria");
    private JLabel newItemDepartmentLabel = new JLabel("Reparto");
    private JLabel newItemDealerLabel = new JLabel("Rivenditore");
    private JLabel newItemProducerLabel = new JLabel("Produttore");
    private JLabel newItemOfItemLabel = new JLabel("Sotto prodotto di");

    private JLabel editItemNameSelectionLabel = new JLabel("Modifica prodotto");
    private JLabel editItemNameLabel = new JLabel("Nome");
    private JLabel editItemDescriptionLabel = new JLabel("Descrizione");
    private JLabel editItemPriceLabel = new JLabel("Prezzo");
    private JLabel editItemSalesPriceLabel = new JLabel("Prezzo scontato");
    private JLabel editItemFotoLabel = new JLabel("Foto");
    private JLabel editItemCategoryLabel = new JLabel("Categoria");
    private JLabel editItemDepartmentLabel = new JLabel("Reparto");
    private JLabel editItemDealerLabel = new JLabel("Rivenditore");
    private JLabel editItemProducerLabel = new JLabel("Produttore");
    private JLabel editItemOfItemLabel = new JLabel("Sotto prodotto di");

    private JEditorPane newEditorPane = new JEditorPane();
    private JScrollPane newEditorScrollPane = new JScrollPane(newEditorPane);

    private JEditorPane editEditorPane = new JEditorPane();
    private JScrollPane editEditorScrollPane = new JScrollPane(editEditorPane);

    private JTextField newItemNameTextField = new JTextField();
    private JTextField newItemPriceTextField = new JTextField();
    private JTextField newItemSalesPriceTextField = new JTextField();

    private JTextField editItemNameTextField = new JTextField();
    private JTextField editItemPriceTextField = new JTextField();
    private JTextField editItemSalesPriceTextField = new JTextField();

    private DefaultComboBoxModel<String> newItemOfItemModel =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> newModelCategory =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> newModelDepartment =new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> newModelProducer = new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> newModelDealer = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> newModelAvaiable = new DefaultComboBoxModel<>();


    private DefaultComboBoxModel<String> editItemSelectionModel =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editItemOfItemModel =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editModelCategory =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editModelDepartment =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editModelProducer = new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editModelDealer = new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> editModelAvaiable = new DefaultComboBoxModel<>();



    private JComboBox<String> newCategoryBox;
    private JComboBox<String> newItemBox;
    private JComboBox<String> newDepartmentBox;
    private JComboBox<String> newProducerBox;
    private JComboBox<String> newDealerBox;
    private JComboBox<String> newAvaiableBox;


    private JComboBox<String> editItemSelectionBox;
    private JComboBox<String> editCategoryBox;
    private JComboBox<String> editItemBox;
    private JComboBox<String> editDepartmentBox;
    private JComboBox<String> editProducerBox;
    private JComboBox<String> editDealerBox;
    private JComboBox<String> editAvaiableBox;


    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel1 = new JPanel(null);
    private JPanel  panel2 = new JPanel(null);
    private JPanel  panel3 = new JPanel(null);

    private JButton newItemFotoButton = new JButton(imageIcon);
    private JButton editItemFotoButton = new JButton(imageIcon);

    private JButton returnButton = new JButton("Indietro");
    private JButton addItemButton = new JButton("Aggiungi");
    private JButton editItemButton = new JButton("Modifica");
    private JButton deleteItemButton = new JButton("Elimina");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact",Font.PLAIN | Font.ITALIC,15 );
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Department> departmentArrayList;
    private ArrayList<Producer> producerArrayList;
    private ArrayList<Dealer> dealerArrayList;
    private static ArrayList<Item> itemArrayList = new ArrayList<>();
    private String path;
    private String editpath;


    private Box group = Box.createHorizontalBox();
    private Box editgroup = Box.createHorizontalBox();

    private JPanel labels = new JPanel(new GridLayout(8, 1));
    private JPanel fields = new JPanel(new GridLayout(8, 1));

    private JPanel editlabels = new JPanel(new GridLayout(9, 1));
    private JPanel editfields = new JPanel(new GridLayout(9, 1));


    private ManageItemListener manageItemListener = new ManageItemListener(this);

    private DefaultComboBoxModel<String> modelDealerDel =new DefaultComboBoxModel<String>();

    private JComboBox<String> itemBoxDel;
    private JLabel deleteItemLabel = new JLabel("Elimina prodotto");


    private String col[] = {"Prodotti"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.

    public ManageItem_GUI() {

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);
        pane.add("Nuovo Prodotto", panel1);
        pane.add("Modifica Prodotto", panel2);
        pane.add("Elimina Prodotto", panel3);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        createMenuDynamically();
        setPanel1();
        setPanel2();
        setPanel3();
        fields.setOpaque(false);
        labels.setOpaque(false);
        editfields.setOpaque(false);
        editlabels.setOpaque(false);

        /* COMPONENTS PLACEMENT SECTION */
        pane.setBounds(5,60,790,470);
        itemTitleLabel.setBounds(230,0,360,60);
        backGroundLabel.setBounds(0,0,800,600);
        returnButton.setBounds(310,530,180,50);

        /* COMPONENTS FONT SECTION */
        pane.setFont(fontpic);
        itemTitleLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,45 ));
        itemTitleLabel.setForeground(Color.ORANGE);

        /* COMPONENTS ADD SECTION */
        backGroundLabel.add(pane);
        backGroundLabel.add(itemTitleLabel);
        backGroundLabel.add(returnButton);
        add(backGroundLabel);

        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(manageItemListener);
    }

    private void setPanel1() {

        //Put the editor pane in a scroll pane.
        newEditorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        newEditorScrollPane.setHorizontalScrollBar(null);


        newModelAvaiable.addElement("");
        newModelAvaiable.addElement("No");
        newModelAvaiable.addElement("Si");
        newAvaiableBox =  new JComboBox<>(newModelAvaiable);
        JLabel avaiableLabel = new JLabel("Disponibile");

        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        group.setBounds(0,10, 350,400);
        newItemDescriptionLabel.setBounds(405,10,130,50);
        newEditorScrollPane.setBounds(405,62, 360, 193);
        newItemFotoLabel.setBounds(405,260,150,50);
        newItemFotoButton.setBounds(595,260,100,50);
        avaiableLabel.setBounds(405,310,120,50);
        newAvaiableBox.setBounds(545,310,180,50);

        addItemButton.setBounds(545,370,180,50);
        newItemFotoButton.setBorder(null);

        /* COMPONENTS FONT SECTION */
        avaiableLabel.setFont(font);
        avaiableLabel.setForeground(Color.WHITE);
        newAvaiableBox.setFont(fontpic);
        newItemNameLabel.setFont(font);
        newItemDescriptionLabel.setForeground(Color.WHITE);
        newItemDescriptionLabel.setFont(font);
        newItemNameLabel.setForeground(Color.WHITE);
        newItemPriceLabel.setFont(font);
        newItemPriceLabel.setForeground(Color.WHITE);
        newItemSalesPriceLabel.setFont(font);
        newItemSalesPriceLabel.setForeground(Color.WHITE);
        newItemCategoryLabel.setFont(font);
        newItemCategoryLabel.setForeground(Color.WHITE);
        newItemDepartmentLabel.setFont(font);
        newItemDepartmentLabel.setForeground(Color.WHITE);
        newItemProducerLabel.setFont(font);
        newItemProducerLabel.setForeground(Color.WHITE);
        newItemDealerLabel.setFont(font);
        newItemDealerLabel.setForeground(Color.WHITE);
        newItemOfItemLabel.setFont(font);
        newItemOfItemLabel.setForeground(Color.WHITE);
        newItemFotoLabel.setFont(font);
        newItemFotoLabel.setForeground(Color.WHITE);


        newEditorPane.setFont(fontpic);
        newItemNameTextField.setFont(fontpic);
        newItemPriceTextField.setFont(fontpic);
        newItemSalesPriceTextField.setFont(fontpic);
        newCategoryBox.setFont(fontpic);
        newDepartmentBox.setFont(fontpic);
        newProducerBox.setFont(fontpic);
        newDealerBox.setFont(fontpic);
        newItemBox.setFont(fontpic);
        newItemFotoButton.setFont(fontpic);
        returnButton.setFont(fontpic);
        addItemButton.setFont(fontpic);

        /* COMPONENTS ADD SECTION */
        panel1.add(newAvaiableBox);
        panel1.add(avaiableLabel);
        panel1.add(addItemButton);
        panel1.add(newItemDescriptionLabel);
        panel1.add(newEditorScrollPane);
        panel1.add(newItemFotoLabel);
        panel1.add(newItemFotoButton);

        panel1.add(group);
        group.add(labels);
        group.add(fields);
        fields.add(newItemNameTextField);
        fields.add(newItemPriceTextField);
        fields.add(newItemSalesPriceTextField);
        fields.add(newCategoryBox);
        fields.add(newDepartmentBox);
        fields.add(newProducerBox);
        fields.add(newDealerBox);
        fields.add(newItemBox);

        labels.add(newItemNameLabel);
        labels.add(newItemPriceLabel);
        labels.add(newItemSalesPriceLabel);
        labels.add(newItemCategoryLabel);
        labels.add(newItemDepartmentLabel);
        labels.add(newItemProducerLabel);
        labels.add(newItemDealerLabel);
        labels.add(newItemOfItemLabel);
        /* ACTION LISTENERS SECTION */
        newItemFotoButton.addActionListener(manageItemListener);
        newItemFotoButton.setActionCommand("Browse");
        addItemButton.addActionListener(manageItemListener);
    }
    private void setPanel2(){
        editEditorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editEditorScrollPane.setHorizontalScrollBar(null);



        editModelAvaiable.addElement("");
        editModelAvaiable.addElement("No");
        editModelAvaiable.addElement("Si");
        editAvaiableBox =  new JComboBox<>(editModelAvaiable);
        JLabel avaiableLabel = new JLabel("Disponibile");

        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        editgroup.setBounds(0,10, 350,400);
        editItemDescriptionLabel.setBounds(405,10,130,50);
        editEditorScrollPane.setBounds(405,62, 360, 193);
        editItemFotoLabel.setBounds(405,260,150,50);
        editItemFotoButton.setBounds(595,260,100,50);
        avaiableLabel.setBounds(405,310,120,50);
        editAvaiableBox.setBounds(545,310,180,50);

        editItemButton.setBounds(545,370,180,50);
        editItemFotoButton.setBorder(null);

        /* COMPONENTS FONT SECTION */
        avaiableLabel.setFont(font);
        avaiableLabel.setForeground(Color.WHITE);
        editAvaiableBox.setFont(fontpic);
        editItemNameLabel.setFont(font);
        editItemDescriptionLabel.setForeground(Color.WHITE);
        editItemDescriptionLabel.setFont(font);
        editItemNameLabel.setForeground(Color.WHITE);
        editItemPriceLabel.setFont(font);
        editItemPriceLabel.setForeground(Color.WHITE);
        editItemSalesPriceLabel.setFont(font);
        editItemSalesPriceLabel.setForeground(Color.WHITE);
        editItemCategoryLabel.setFont(font);
        editItemCategoryLabel.setForeground(Color.WHITE);
        editItemDepartmentLabel.setFont(font);
        editItemDepartmentLabel.setForeground(Color.WHITE);
        editItemProducerLabel.setFont(font);
        editItemProducerLabel.setForeground(Color.WHITE);
        editItemDealerLabel.setFont(font);
        editItemDealerLabel.setForeground(Color.WHITE);
        editItemOfItemLabel.setFont(font);
        editItemOfItemLabel.setForeground(Color.WHITE);
        editItemFotoLabel.setFont(font);
        editItemFotoLabel.setForeground(Color.WHITE);
        editItemNameSelectionLabel.setFont(font);
        editItemNameSelectionLabel.setForeground(Color.WHITE);

        editEditorPane.setFont(fontpic);
        editItemNameTextField.setFont(fontpic);
        editItemPriceTextField.setFont(fontpic);
        editItemSalesPriceTextField.setFont(fontpic);
        editItemSelectionBox.setFont(fontpic);
        editCategoryBox.setFont(fontpic);
        editDepartmentBox.setFont(fontpic);
        editProducerBox.setFont(fontpic);
        editDealerBox.setFont(fontpic);
        editItemBox.setFont(fontpic);
        editItemFotoButton.setFont(fontpic);
        returnButton.setFont(fontpic);
        editItemButton.setFont(fontpic);

        /* COMPONENTS ADD SECTION */

        panel2.add(avaiableLabel);
        panel2.add(editAvaiableBox);
        panel2.add(editItemButton);
        panel2.add(editItemDescriptionLabel);
        panel2.add(editEditorScrollPane);
        panel2.add(editItemFotoLabel);
        panel2.add(editItemFotoButton);

        panel2.add(editgroup);

        editgroup.add(editlabels);
        editgroup.add(editfields);

        editfields.add(editItemSelectionBox);
        editfields.add(editItemNameTextField);
        editfields.add(editItemPriceTextField);
        editfields.add(editItemSalesPriceTextField);
        editfields.add(editCategoryBox);
        editfields.add(editDepartmentBox);
        editfields.add(editProducerBox);
        editfields.add(editDealerBox);
        editfields.add(editItemBox);

        editlabels.add(editItemNameSelectionLabel);
        editlabels.add(editItemNameLabel);
        editlabels.add(editItemPriceLabel);
        editlabels.add(editItemSalesPriceLabel);
        editlabels.add(editItemCategoryLabel);
        editlabels.add(editItemDepartmentLabel);
        editlabels.add(editItemProducerLabel);
        editlabels.add(editItemDealerLabel);
        editlabels.add(editItemOfItemLabel);
        /* ACTION LISTENERS SECTION */
        editItemFotoButton.addActionListener(manageItemListener);
        editItemFotoButton.setActionCommand("editBrowse");
        editItemButton.addActionListener(manageItemListener);
    }
    private void setPanel3() {
        int row ;


        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        row = tableModel.getRowCount();

        transferData(row);

        JTable table = new JTable(tableModel) {
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        /* TABLE SECTION */
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        JScrollPane scrollPane = new JScrollPane(table);

        /*DYNAMICALLI GENERATES THE ITEM MENU*/
        modelDealerDel.addElement("");
        for (Item anItemArrayList : itemArrayList) {
            modelDealerDel.addElement(anItemArrayList.getItemName());
        }
        itemBoxDel = new JComboBox<String>(modelDealerDel);

        deleteItemLabel.setBounds(190,60,160,50);
        itemBoxDel.setBounds(350,60,140,50);
        deleteItemButton.setBounds(490,60,100,50);

        scrollPane.setBounds(190,130,398,220);
        /* COMPONENTS FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        itemBoxDel.setFont(fontpic);
        deleteItemLabel.setFont(font);
        deleteItemLabel.setForeground(Color.WHITE);
        deleteItemButton.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        panel3.add(deleteItemLabel);
        panel3.add(itemBoxDel);
        panel3.add(deleteItemButton);
        panel3.add(scrollPane);
        /* ACTION LISTENERS SECTION */
        deleteItemButton.addActionListener(manageItemListener);
    }

    private void transferData(int row){
        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Item anItemArrayList : itemArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String department = anItemArrayList.getItemName();

            Object[] data = {department};

            tableModel.addRow(data);
        }
    }
    private void createMenuDynamically(){

        /*DYNAMICALLI GENERATES THE ITEM_OF_ITEM_LIST MENU*/
        itemArrayList.clear();
        itemArrayList = CatalogBusiness.getInstance().getAllItemListBusiness(0);

        newItemOfItemModel.addElement("");
        newItemOfItemModel.addElement("Nessuno");
        editItemOfItemModel.addElement("");
        editItemOfItemModel.addElement("Nessuno");
        editItemSelectionModel.addElement("");

        for (Item anItemArrayList : itemArrayList) {
            newItemOfItemModel.addElement(anItemArrayList.getItemName());
            editItemOfItemModel.addElement(anItemArrayList.getItemName());
            editItemSelectionModel.addElement(anItemArrayList.getItemName());

        }
        newItemBox = new JComboBox<String>(newItemOfItemModel);
        editItemBox = new JComboBox<>(editItemOfItemModel);
        editItemSelectionBox = new JComboBox<>(editItemSelectionModel);


        /*DYNAMICALLI GENERATES THE CATEGORY_LIST MENU*/
        categoryArrayList = CatalogBusiness.getInstance().getAllCategoryBusiness();

        newModelCategory.addElement("");
        editModelCategory.addElement("");

        for (Category aCategoryArrayList : categoryArrayList) {
            newModelCategory.addElement(aCategoryArrayList.getCategoryName());
            editModelCategory.addElement(aCategoryArrayList.getCategoryName());
        }
        newCategoryBox = new JComboBox<>(newModelCategory);
        editCategoryBox = new JComboBox<>(editModelCategory);

        /* DYNAMICALLY GENERATES THE DEPARTMENT_LIST MENU */
        departmentArrayList = CatalogBusiness.getInstance().getAllDepartmentsBusiness();

        newModelDepartment.addElement("");
        editModelDepartment.addElement("");

        for (Department aDepartmentArrayList : departmentArrayList) {
            newModelDepartment.addElement(aDepartmentArrayList.getDepartment());
            editModelDepartment.addElement(aDepartmentArrayList.getDepartment());
        }
        newDepartmentBox = new JComboBox<>(newModelDepartment);
        editDepartmentBox = new JComboBox<String>(editModelDepartment);

        /* DYNAMICALLY GENERATES THE PRODUCER_LIST MENU */
        producerArrayList = CatalogBusiness.getInstance().getAllProducers();

        newModelProducer.addElement("");
        editModelProducer.addElement("");

        for (Producer aProducerArrayList : producerArrayList) {
            newModelProducer.addElement(aProducerArrayList.getProducer());
            editModelProducer.addElement(aProducerArrayList.getProducer());

        }
        newProducerBox = new JComboBox<String>(newModelProducer);
        editProducerBox = new JComboBox<String>(editModelProducer);


        /* DYNAMICALLY GENERATES THE DEALERS_LIST MENU */
        dealerArrayList = CatalogBusiness.getInstance().getAllDealers();

        newModelDealer.addElement("");
        editModelDealer.addElement("");

        for (Dealer aDealerArrayList : dealerArrayList) {
            newModelDealer.addElement(aDealerArrayList.getDealer());
            editModelDealer.addElement(aDealerArrayList.getDealer());
        }
        newDealerBox = new JComboBox<String>(newModelDealer);
        editDealerBox = new JComboBox<>(editModelDealer);


    }

    //GETTERS FOR DELETE ITEM
    public String getItemBoxDel() { return (String) itemBoxDel.getSelectedItem(); }

    //GETTERS FOR ADD ITEM
    public void setPath(String path) { this.path = path; }
    public String getItemCategory(){ return (String) newCategoryBox.getSelectedItem(); }
    public String getItemDepartment(){ return (String) newDepartmentBox.getSelectedItem(); }
    public String getItemProducer(){ return (String) newProducerBox.getSelectedItem(); }
    public String getItemDealer(){ return (String) newDealerBox.getSelectedItem(); }
    public String getItemOfItem(){ return  (String) newItemBox.getSelectedItem(); }
    public JTextField getNewItemNameTextField() { return newItemNameTextField; }
    public JTextField getNewItemPriceTextField() {
        return newItemPriceTextField;
    }
    public JTextField getNewItemSalesPriceTextField() {
        return newItemSalesPriceTextField;
    }
    public String getPath() { return path; }
    public String getItemDescription() { return newEditorPane.getText(); }
    public static ArrayList<Item> getItemArrayList() { return itemArrayList; }
    public String getNewAvaiableBox() {return (String) newAvaiableBox.getSelectedItem(); }
    public JComboBox<String> getAvaiableBox() {return newAvaiableBox; }


    //GETTERS FOR EDIT ITEM
    public JTextField getEditItemNameTextField() { return editItemNameTextField; }
    public JTextField getEditItemPriceTextField() { return editItemPriceTextField; }
    public JTextField getEditItemSalesPriceTextField() { return editItemSalesPriceTextField; }
    public String getEditItemSelectionBox() { return (String) editItemSelectionBox.getSelectedItem(); }
    public String getEditCategoryBox() { return (String) editCategoryBox.getSelectedItem(); }
    public String getEditItemBox() { return (String) editItemBox.getSelectedItem(); }
    public String getEditDepartmentBox() { return (String) editDepartmentBox.getSelectedItem(); }
    public String getEditProducerBox() { return (String) editProducerBox.getSelectedItem(); }
    public String getEditDealerBox() { return (String) editDealerBox.getSelectedItem(); }
    public String getEditpath() { return editpath; }
    public void setEditpath(String editpath) { this.editpath = editpath; }
    public String getEditEditorPane() { return editEditorPane.getText(); }
    public String getEditAvaiableBoxSELECTED() { return (String) editAvaiableBox.getSelectedItem(); }
    public JComboBox<String> getEditAvaiableBox() {return editAvaiableBox; }


    public void showDeleteSection() { pane.setSelectedIndex(2); }
    public void showEditSection() { pane.setSelectedIndex(1); }
    public static ManageItem_GUI getInstance() { return instance; }
    public static void setInstance(ManageItem_GUI instance) { ManageItem_GUI.instance = instance; }
}
