package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.CatalogListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Category;
import it.unisalento.magneto_shop._4_model.Department;
import it.unisalento.magneto_shop._4_model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Catalog_GUI extends JPanel{

    private ImageIcon imgRet = new ImageIcon(PathImmagini.returnPath()+"/icon/back.png");
    private JButton returnButton = new JButton(imgRet);

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGround = new JLabel(img);
    private JMenuBar barMenu = new JMenuBar();
    private JMenu catalogMenu = new JMenu("Visualizza Catalogo");
    private JMenu priceMenu = new JMenu("Prezzo");
    private JMenu categoryMenu = new JMenu("Categoria");
    private JMenu departmentMenu = new JMenu("Reparto");
    private JMenuItem alto = new JMenuItem("Alto");
    private JMenu fasciaMenu = new JMenu("Fascia");
    private JMenuItem fascia1 = new JMenuItem("0 - 50 EUR");
    private JMenuItem fascia2 = new JMenuItem("50 - 100 EUR");
    private JMenuItem fascia3 = new JMenuItem("100 - 200 EUR");
    private JMenuItem fascia4 = new JMenuItem("200 - 500 EUR");
    private JMenuItem fascia5 = new JMenuItem("Piu di 500 EUR");

    private JMenuItem basso = new JMenuItem("Basso");

    private String col[] = {"Foto","Prodotto","Produttore","Prezzo"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private JTable table ;


    private ArrayList<Category> categList = new ArrayList<>();
    private ArrayList<Department> departmentList = new ArrayList<>();

    private CatalogListener catalogListener =  new CatalogListener(this);
    private static ArrayList<Item> itemArrayList = new ArrayList<>();
    private  String username;


    public Catalog_GUI(String username) {

        this.username = username;
        this.setLayout(null);
        this.setBounds(0,0,800,600);



        /*DYNAMICALLI GENERATES THE CATEGORY MENU*/
        categList = CatalogBusiness.getInstance().getAllCategoryBusiness();

        for(int i=0; i<categList.size(); i++){

            JMenuItem menuItem = new JMenuItem(categList.get(i).getCategoryName());
           // menuItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
            categoryMenu.add(menuItem);
            menuItem.addActionListener(catalogListener);

        }


        /* DYNAMICALLY GENERATES THE DEPARTMENT MENU */
        departmentList = CatalogBusiness.getInstance().getAllDepartmentsBusiness();

        for(int i=0; i<departmentList.size(); i++){

            JMenuItem menuItem = new JMenuItem(departmentList.get(i).getDepartment());
           // menuItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
            departmentMenu.add(menuItem);
            menuItem.addActionListener(catalogListener);

        }

        /* COMPONENTS PLACEMENT SECTION */
        barMenu.setBounds(0,0,800, 30);
        backGround.setBounds(0,0,800,600);
        returnButton.setBounds(350,450,100,100);
        returnButton.setBorder(null);
        /* COMPONENTS FONT SECTION */
       // table.getTableHeader().setFont(fontpic);



        /* COMPONENTS ADD SECTION */
        add(barMenu);
        add(returnButton);
        priceMenu.add(alto);
        fasciaMenu.add(fascia1);
        fasciaMenu.add(fascia2);
        fasciaMenu.add(fascia3);
        fasciaMenu.add(fascia4);
        fasciaMenu.add(fascia5);
        priceMenu.add(fasciaMenu);
        priceMenu.add(basso);
        catalogMenu.add(categoryMenu);
        catalogMenu.add(departmentMenu);
        catalogMenu.add(priceMenu);
        barMenu.add(catalogMenu);
        add(backGround);

        /* ACTION LISTENERS SECTION */

        fascia1.addActionListener(catalogListener);
        fascia2.addActionListener(catalogListener);
        fascia3.addActionListener(catalogListener);
        fascia4.addActionListener(catalogListener);
        fascia5.addActionListener(catalogListener);
        alto.addActionListener(catalogListener);
        basso.addActionListener(catalogListener);
        returnButton.addActionListener(catalogListener);
        returnButton.setActionCommand("Indietro");
        if (username.equals("nonLogato")){
            returnButton.setActionCommand("<-");
        }else if (!username.equals("nonLogato")){
            returnButton.setActionCommand("IndietroMember");
        }

    }


    /**
     * Visualizza la lista degli articoli nel catalogo
     * @param byPriceByCategoryByDepartament il nome della categoria di prodotti da visualizzare
     * */

    public void showItemList(String byPriceByCategoryByDepartament){

        int i;
        int row ;

        itemArrayList.clear();//INIZIALIZZA


        if (byPriceByCategoryByDepartament.equals("Piu di 500 EUR")) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByRangePriceBusiness(500 ,10000,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else if (byPriceByCategoryByDepartament.equals("200 - 500 EUR")) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByRangePriceBusiness(200,500,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else if (byPriceByCategoryByDepartament.equals("100 - 200 EUR")) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByRangePriceBusiness(100, 200,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else if (byPriceByCategoryByDepartament.equals("50 - 100 EUR")) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByRangePriceBusiness(50,100,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else if (byPriceByCategoryByDepartament.equals("0 - 50 EUR")) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByRangePriceBusiness(0,50,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else if (!byPriceByCategoryByDepartament.equals("Basso") && !byPriceByCategoryByDepartament.equals("Alto") ) {

            itemArrayList = CatalogBusiness.getInstance().getItemListByCategoryByDepartmentBusiness(byPriceByCategoryByDepartament,1);//RICAVO LISTA DI ISTANZE DEI OGETTI

        }else {

            itemArrayList = CatalogBusiness.getInstance().getItemListBusinessByPrice(byPriceByCategoryByDepartament,1);

        }

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO IL CATALOGO */
        row = tableModel.getRowCount();


        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for ( i = 0; i < itemArrayList.size(); i++){

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row >0){
                for (int j = row-1; j>=0;j--){
                    tableModel.removeRow(j);
                }
                row =-1;
            }

            Image img1 = Toolkit.getDefaultToolkit().createImage(itemArrayList.get(i).getFotoItem());
            Icon imageIcon1 =new ImageIcon(img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            String itemName = itemArrayList.get(i).getItemName();
            String producer  = itemArrayList.get(i).getProducer();
            float price = itemArrayList.get(i).getPrice();
            float sales = itemArrayList.get(i).getSales();

            if (price > sales) price = sales;

            Object[] data = {imageIcon1,itemName, producer, String.valueOf(price)+"€"};

            tableModel.addRow(data);
        }

        table = new JTable(tableModel){
            @Override
            public Class getColumnClass(int column) {
                return  getValueAt(0, column).getClass();
            }
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        // to center a value in JTable cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=1;x<4;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }

        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD,10 ));
        table.setFont(new Font("SansSerif", Font.BOLD,12 ));
        /* ACTION LISTENERS SECTION */
        table.setAutoCreateRowSorter(true);
        table.addMouseListener(catalogListener);
        table.setRowHeight(100);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(80,80,640,350);
        add(scrollPane);


    }

    public static ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }
    public JTable getTable() {
        return table;
    }
    public ArrayList<Category> getCategList() { return categList;}
    public ArrayList<Department> getDepartmentList() { return departmentList; }
    public String getUsername() { return username; }

}
