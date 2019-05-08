package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.CartListener;
import it.unisalento.magneto_shop._3_business.CartBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Cart;
import it.unisalento.magneto_shop._4_model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Cart_GUI extends JPanel {


    private static Cart_GUI instance;
    private String username;

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);


    private ImageIcon imgRet = new ImageIcon(PathImmagini.returnPath()+"/icon/back.png");
    private JButton returnButton = new JButton(imgRet);

    private  JTabbedPane pane = new JTabbedPane();
    private JPanel  panel2 = new JPanel(null);


    private JLabel titleLabel = new JLabel("Carrello");

    private String col[] = {"Foto","Prodotto","Prezzo","Quantita","Conferma prodotto"};
    private DefaultTableModel tableModel ;
    private JScrollPane scrollPane;


    private CartListener cartListener = new CartListener(this);

    private Font font = new Font("Impact", Font.ITALIC, 16);
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );


    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ArrayList<Cart> cartArrayList = new ArrayList<>();
    private ArrayList<JPanel> panelArrayList = new ArrayList<JPanel>();
    private ArrayList<JButton> confirmButtonArrayList = new ArrayList<JButton>();
    private ArrayList<JButton> catalogButtonArrayList = new ArrayList<JButton>();
    private ArrayList<JButton> delItemButtonArrayList = new ArrayList<JButton>();

    private ArrayList<JTable> tableArrayList = new ArrayList<JTable>();

    private JLabel addCartLabel = new JLabel("Aggiungi nuovo carrello");
    private JLabel editCartLabel = new JLabel("Modifica carrello");
    private JLabel edit1CartLabel = new JLabel("Nuovo nome");
    private JLabel deleteCartLabel = new JLabel("Ellimnina carrello");

    private JTextField addCartTextField = new JTextField();
    private JTextField editCartTextField = new JTextField();

    private DefaultComboBoxModel<String> editModelCart =new DefaultComboBoxModel<String>();
    private JComboBox<String> editCartBox;

    private DefaultComboBoxModel<String> deleteModelCart =new DefaultComboBoxModel<String>();
    private JComboBox<String> deletecCartBox;

    private Box group = Box.createHorizontalBox();
    private JPanel labels = new JPanel(new GridLayout(2, 1));
    private JPanel fields = new JPanel(new GridLayout(2, 1));
    private JPanel buttons = new JPanel(new GridLayout(2, 1));

    private JButton addCartButton = new JButton("Aggiungi");
    private JButton deleteCartButton = new JButton("Elimina");


    public Cart_GUI(String username) {

        this.username=username;

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);


        /*PERSONAL CART SECTION*/

        tableArrayList.clear();
        itemArrayList.clear();
        cartArrayList.clear();
        //delItemButtonArrayList.clear();
        /*ALL PERSONAL CART*/
        cartArrayList = CartBusiness.getInstance().getCartByMemberBusiness(username);
        for (int k = 0 ; k<cartArrayList.size();k++){
            panelArrayList.add(new JPanel(null));
            confirmButtonArrayList.add(new JButton("Procedi all'ordine"));
            catalogButtonArrayList.add(new JButton("Aggiungi prodotti"));
            delItemButtonArrayList.add(new JButton("Rimuovi prodotto"));
        }

        for (int i = 0; i <cartArrayList.size(); i++){




            pane.add(cartArrayList.get(i).getTypeOfCart(),panelArrayList.get(i));
            panelArrayList.get(i).setOpaque(false);

            panelArrayList.get(i).add(delItemButtonArrayList.get(i));
            panelArrayList.get(i).add(confirmButtonArrayList.get(i));
            panelArrayList.get(i).add(catalogButtonArrayList.get(i));

            catalogButtonArrayList.get(i).setBounds(605,123,155,50);
            delItemButtonArrayList.get(i).setBounds(605,73,155,50);
            confirmButtonArrayList.get(i).setBounds(605,23,155,50);

            catalogButtonArrayList.get(i).setFont(fontpic);
            delItemButtonArrayList.get(i).setFont(fontpic);
            confirmButtonArrayList.get(i).setFont(fontpic);



            confirmButtonArrayList.get(i).addActionListener(cartListener);
            delItemButtonArrayList.get(i).addActionListener(cartListener);
            catalogButtonArrayList.get(i).addActionListener(cartListener);

            /*ALL ITEM FROM PERSONAL CART*/
            itemArrayList = CartBusiness.getInstance().getItemListByIdCart(cartArrayList.get(i).getIdCart());

            //SALVO ID CARRELLO E ARRAY DI ITEM RELATIVO AL CARRELLO
            Session.getInstance().mappaIdCartItem.put(cartArrayList.get(i).getIdCart(),itemArrayList);

            tableModel = new DefaultTableModel(col, 0)// The 0 argument is number rows.
            {
                @Override
                public Class getColumnClass(int column) {

                    switch (column) {
                        case 0:
                            return Icon.class;
                        case 1:
                            return String.class;
                        case 2:
                            return Float.class;
                        case 3:
                            return Integer.class;
                        case 4:
                            return Boolean.class;
                        default:
                            return Boolean.class;
                    }


                   // return  getValueAt(0, column).getClass();
                }
                @Override
                /*RENDE NON EDITABILE LA TABELLA*/
                public boolean isCellEditable(int row, int column)
                {
                    switch (column) {
                        case 0:
                            return false;
                        case 1:
                            return false;
                        case 2:
                            return false;
                        case 3:
                            return false;
                        case 4:
                            return true;
                        default:
                            return true;
                    }
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
                int quantity = CartBusiness.getInstance().getQuantityForItemBusiness(anItemArrayList.getIdItem(),cartArrayList.get(i).getIdCart());

                float price = anItemArrayList.getPrice();
                float sales = anItemArrayList.getSales();
                if (price > sales) price = sales;

                Object[] data = {imageIcon1, itemName, String.valueOf(price)+"€", quantity, Boolean.FALSE};
                tableModel.addRow(data);
            }
            tableArrayList.add(new JTable(tableModel));


            // to center a value in JTable cell
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int x=1;x<4;x++){
                tableArrayList.get(i).getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
            }

            tableArrayList.get(i).setAutoCreateRowSorter(true);
            tableArrayList.get(i).setRowHeight(100);
            tableArrayList.get(i).setPreferredSize(tableArrayList.get(i).getPreferredSize());
            tableArrayList.get(i).setPreferredScrollableViewportSize(tableArrayList.get(i).getPreferredSize());
            tableArrayList.get(i).setFillsViewportHeight(true);
            tableArrayList.get(i).setIntercellSpacing(new Dimension(0,5));

            scrollPane = new JScrollPane(tableArrayList.get(i));
            scrollPane.setBounds(0,25,600,390);

            panelArrayList.get(i).add(scrollPane);
            tableArrayList.get(i).getTableHeader().setFont(new Font("SansSerif", Font.BOLD,10 ));
            tableArrayList.get(i).setFont(new Font("SansSerif", Font.BOLD,12 ));
        }

        pane.add("Gestione Carrello", panel2);
        setPanel2();


        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        titleLabel.setBounds(320,20,200 ,50);
        returnButton.setBounds(350,487,100,100);
        pane.setBounds(10,90,780,420);
        /* COMPONENTS FONT SECTION */
        titleLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,45 ));
        titleLabel.setForeground(Color.ORANGE);
        returnButton.setBorder(null);

        /* COMPONENTS ADD SECTION */
        add(pane);
        add(returnButton);
        add(titleLabel);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(cartListener);
        returnButton.setActionCommand("Indietro");
    }

    public void setPanel2(){

        panel2.setOpaque(false);
        labels.setOpaque(false);
        fields.setOpaque(false);
        buttons.setOpaque(false);
        /*DYNAMICALLI GENERATES THE CART MENU*/
        deleteModelCart.addElement("");
        for (Cart aCartArrayList : cartArrayList) {
            deleteModelCart.addElement(aCartArrayList.getTypeOfCart());
        }
        deleteModelCart.removeElementAt(1);
        deletecCartBox = new JComboBox<>(deleteModelCart);


        /* COMPONENTS PLACEMENT SECTION */
        group.setBounds(190,100,400,100);

        /* COMPONENTS FONT SECTION */
        addCartLabel.setFont(font);
        addCartLabel.setForeground(Color.WHITE);
        deleteCartLabel.setFont(font);
        deleteCartLabel.setForeground(Color.WHITE);
        addCartTextField.setFont(font);
        addCartButton.setFont(font);
        deletecCartBox.setFont(font);
        deleteCartButton.setFont(font);
        pane.setFont(fontpic);

        /* COMPONENTS ADD SECTION */
       panel2.add(group);

       group.add(labels);
       group.add(fields);
       group.add(buttons);

       buttons.add(addCartButton);
       buttons.add(deleteCartButton);
       fields.add(addCartTextField);
       fields.add(deletecCartBox);
       labels.add(addCartLabel);
       labels.add(deleteCartLabel);

        /* ACTION LISTENERS SECTION */
        addCartButton.addActionListener(cartListener);
        deleteCartButton.addActionListener(cartListener);

    }



    public Cart getCart(int i) { return cartArrayList.get(i); }
    public String getCartToDelete(){return (String) deletecCartBox.getSelectedItem(); }
    public int getIndexOfCartSelected(){return deletecCartBox.getSelectedIndex();}
    public String getUsername() { return username; }
    public JTabbedPane getPane() { return pane; }
    public ArrayList<JTable> getTableArrayList() { return tableArrayList; }
    public JTextField getAddCartTextField() { return addCartTextField; }
    public void showDeleteSection(int page) { pane.setSelectedIndex(page); }
    public int getNumberOfCarts(){return cartArrayList.size();}

    public static Cart_GUI getInstance() { return instance; }
    public static void setInstance(Cart_GUI instance) { Cart_GUI.instance = instance; }

}
