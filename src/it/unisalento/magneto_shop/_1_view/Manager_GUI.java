package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.ManagerListener;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Manager_GUI extends JPanel {

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);

    private ImageIcon imgLogout = new ImageIcon(PathImmagini.returnPath()+"/icon/logout1.png");
    private JLabel managerNameSurnameLabel = new JLabel();
    private JLabel managerEmailLabel = new JLabel();
    private JLabel managerFotoLabel;

    private ImageIcon btnCatimg = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonCAT.png");
    private ImageIcon btnDepimg = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonDEP.png");
    private ImageIcon btnItemimg = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonPRODUCT.png");
    private ImageIcon btnProducerimg = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonPRODUCER.png");
    private ImageIcon btnDealerimg = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonDEALER.png");


    private JButton logoutButton = new JButton(imgLogout);
    private JButton addItemButton = new JButton(btnItemimg);
    private JButton categoryButton = new JButton(btnCatimg);
    private JButton departmentButton = new JButton(btnDepimg);
    private JButton producerButton = new JButton(btnProducerimg);
    private JButton dealerButton = new JButton(btnDealerimg);


    private ManagerListener managerListener = new ManagerListener();
    private static String username;
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private JScrollPane scrollPane;
    private String col[] = {"Foto","Prodotto","Descrizione","Produttore","Rivenditore","Categoria","Reparto","Sottoprodotto di","Prezzo", "Scontato"};
    private DefaultTableModel tableModel;
    private JTable table ;

    private Font fontpic = new Font("Impact", Font.BOLD,15 );

    Manager_GUI(Manager manager) {

        setUsername(manager.getUserName());
        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        Image img = Toolkit.getDefaultToolkit().createImage(manager.getFoto());
        ImageIcon imageIcon =new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        /* COMPONENTS SET SECTION */
        managerNameSurnameLabel.setText(manager.getName()+" "+manager.getSurname());
        managerEmailLabel.setText(manager.getE_mail());
        managerFotoLabel = new JLabel(imageIcon);

        /*ALL ITEM FROM DB*/
        itemArrayList.clear();
        itemArrayList = CatalogBusiness.getInstance().getItemListBusinessByPrice("Alto",0);

        tableModel = new DefaultTableModel(col, 0)// The 0 argument is number rows.
        {
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
        int row = tableModel.getRowCount();

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTORE DATA */
        for (Item anItemArrayList : itemArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }
            Image img1 = Toolkit.getDefaultToolkit().createImage(anItemArrayList.getFotoItem());
            Icon imageIcon1 = new ImageIcon(img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            String itemName = anItemArrayList.getItemName();
            String itemDescription = anItemArrayList.getDescriptionItem();
            String producer = anItemArrayList.getProducer();
            String dealer = anItemArrayList.getDealer();
            String category = CatalogBusiness.getInstance().getCategoryForItemB(anItemArrayList.getIdItem());
            String department = CatalogBusiness.getInstance().getDepartmentForItemB(anItemArrayList.getIdItem());
            String itemFather = anItemArrayList.getItemNameFather();

            float price = anItemArrayList.getPrice();
            float sales = anItemArrayList.getSales();

            Object[] data = {imageIcon1,itemName, itemDescription, producer, dealer, category, department, itemFather, String.valueOf(price)+"€", String.valueOf(sales)+"€" };
            tableModel.addRow(data);
        }
        table = new JTable(tableModel);

        // to center a value in JTable cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=1;x<10;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(100);
        table.setPreferredSize(table.getPreferredSize());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        managerFotoLabel.setBounds(25,25,150,150);
        managerNameSurnameLabel.setBounds(25,175,120 ,20);
        managerEmailLabel.setBounds(25,195,120,20);
        logoutButton.setBounds(140,180,30,30);


        addItemButton.setBounds(220,55,100,100);
        categoryButton.setBounds(320,55,100,100);
        departmentButton.setBounds(420,55,100,100);
        producerButton.setBounds(520,55,100,100);
        dealerButton.setBounds(620,55,100,100);
        scrollPane.setBounds(25,220,750,330);

        addItemButton.setBorder(null);
        departmentButton.setBorder(null);
        producerButton.setBorder(null);
        dealerButton.setBorder(null);
        categoryButton.setBorder(null);
        /* COMPONENTS FONT SECTION */
        managerNameSurnameLabel.setFont(fontpic);
        managerNameSurnameLabel.setForeground(Color.WHITE);
        managerEmailLabel.setFont(fontpic);
        managerEmailLabel.setForeground(Color.WHITE);
        logoutButton.setBorder(null);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD,10 ));
        table.setFont(new Font("SansSerif", Font.BOLD,12 ));
        /* COMPONENTS ADD SECTION */
        add(dealerButton);
        add(producerButton);
        add(departmentButton);
        add(scrollPane);
        add(categoryButton);
        add(addItemButton);
        add(managerEmailLabel);
        add(managerFotoLabel);
        add(logoutButton);
        add(managerNameSurnameLabel);
        add(backGroundLabel);

        /* ACTION LISTENERS SECTION */
        logoutButton.setActionCommand("Logout");
        logoutButton.addActionListener(managerListener);
        addItemButton.addActionListener(managerListener);
        addItemButton.setActionCommand("Aggiungi Prodotto");
        categoryButton.addActionListener(managerListener);
        categoryButton.setActionCommand("Gestione Categoria");
        departmentButton.addActionListener(managerListener);
        dealerButton.setActionCommand("Gestione Distributore");
        producerButton.addActionListener(managerListener);
        producerButton.setActionCommand("Gestione Produttore");
        dealerButton.addActionListener(managerListener);
        departmentButton.setActionCommand("Gestione Reparto");
    }
    public static String getUsername() { return username; }
    public void setUsername(String username) { Manager_GUI.username = username; }
}
