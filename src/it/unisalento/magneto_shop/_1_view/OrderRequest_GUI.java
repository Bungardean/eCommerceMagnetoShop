package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.OrderRequestListener;
import it.unisalento.magneto_shop._3_business.CartBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class OrderRequest_GUI extends JPanel {

    private static OrderRequest_GUI instance;


    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private String username;


    private JLabel backGroundLabel = new JLabel(img);
    private JLabel titleLabel = new JLabel("Riepilogo ordine");
    private JLabel reciverLabel = new JLabel("Destinatario");
    private JLabel addressLabel = new JLabel("Indirizzo di consegna");
    private JLabel shippingLabel = new JLabel("Modalita spedizione");
    private JLabel paymentLabel = new JLabel("Modalita pagamento");
    private JLabel totalpriceLabel = new JLabel();
    private JLabel nameLabel = new JLabel("Nome");
    private JLabel surnameLabel = new JLabel("Cognome");
    private JLabel infoOrderLabel = new JLabel("Dettagli ordine");
    private JLabel infoShipLabel = new JLabel("con spedizione 10 € ");
    private JLabel shipLabel = new JLabel("Spedizione");


    private JLabel addressUserLabel = new JLabel("Indirizzo");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel phoneLabel = new JLabel("Telefono");
    private JLabel paymentModeLabel = new JLabel("Pagamento");


    private JTextField reciverNameTextField = new JTextField();
    private JTextField reciverSurnameTextField = new JTextField();

    private JTextField emailTextField = new JTextField();
    private JTextField addressTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();

    private JButton addDealerButton = new JButton("Aggiungi");
    private JButton editDealerButton = new JButton("Modifica");
    private JButton deleteDealerButton = new JButton("Elimina");


    private JButton returnButton = new JButton("Indietro");
    private JButton confirmButton = new JButton("Conferma ordine");


    private Font font = new Font("Impact", Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );
    private OrderRequestListener orderRequestListener = new OrderRequestListener(this);

    private String col[] = {"Foto", "Prodotto","Quantita","Parziale"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private JTable table ;

    private DefaultComboBoxModel<String> modelShipping =new DefaultComboBoxModel<String>();
    private DefaultComboBoxModel<String> modelpayment =new DefaultComboBoxModel<String>();

    private JComboBox<String> shippingBox;
    private JComboBox<String> paymentBox;

    private int idCart;

    private float total = 0;

    public OrderRequest_GUI(int idCart, String username) {

        this.idCart = idCart;
        this.username = username;
        this.setLayout(null);
        this.setBounds(0, 0, 800, 600);
        backGroundLabel.setLayout(null);


        ArrayList<Item> itemArray =  Session.getInstance().mappaIdCartItem.get(idCart);

        int row = tableModel.getRowCount();


        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Item anItemArray1 : itemArray) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            float partialprice = 0;

            Image img1 = Toolkit.getDefaultToolkit().createImage(anItemArray1.getFotoItem());
            Icon imageIcon1 = new ImageIcon(img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

            String itemName = anItemArray1.getItemName();
            int quantity = CartBusiness.getInstance().getQuantityForItemBusiness(anItemArray1.getIdItem(), idCart);
            float price = anItemArray1.getPrice();
            float saleprice = anItemArray1.getSales();
            if (price > saleprice) {
                price = saleprice;
            }

            partialprice = quantity * price;
            total = total + partialprice;


            Object[] data = {imageIcon1, itemName, quantity, String.valueOf(partialprice)+"€"};

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
         table.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // to center a value in JTable cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=1;x<4;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }

        /* ACTION LISTENERS SECTION */
      //  table.addMouseListener(catalogListener);
        table.setRowHeight(100);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        JScrollPane scrollPane = new JScrollPane(table);
        infoOrderLabel.setBounds(460,90,300,30);
        scrollPane.setBounds(460,140,320,230);
        add(scrollPane);

        total = (float) (total + 10);

        totalpriceLabel.setText("Totale ordine  " + total+ "€");

        modelpayment.addElement("");
        modelpayment.addElement("Paypal");
        modelpayment.addElement("Bonifico");
        modelpayment.addElement("Postepay");
        modelpayment.addElement("Contrassegno");
        paymentBox = new JComboBox<>(modelpayment);

        modelShipping.addElement("");
        modelShipping.addElement("DHL");
        modelShipping.addElement("GLS");
        modelShipping.addElement("SDA");
        shippingBox = new JComboBox<>(modelShipping);



        Member member = (Member) Session.getInstance().mappa.get(username);
        reciverNameTextField.setText(member.getName());
        emailTextField.setText(member.getE_mail());
        emailTextField.setEnabled(false);
        addressTextField.setText(member.getAddress());
        phoneTextField.setText(member.getPhone());
        reciverSurnameTextField.setText(member.getSurname());
        phoneTextField.setEnabled(false);
        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0, 0, 800, 600);
        titleLabel.setBounds(245, 20, 410, 50);

        reciverLabel.setBounds(50, 100, 150, 30);
        nameLabel.setBounds(50, 140, 60, 30);
        reciverNameTextField.setBounds(110, 140, 100, 30);
        surnameLabel.setBounds(220, 140, 80, 30);
        reciverSurnameTextField.setBounds(305, 140, 100, 30);

        addressLabel.setBounds(50, 180, 280, 30);
        addressUserLabel.setBounds(50, 220, 90, 30);
        addressTextField.setBounds(140, 220, 265, 30);
        emailLabel.setBounds(50, 250, 60, 30);
        emailTextField.setBounds(140, 250, 265, 30);
        phoneLabel.setBounds(50, 280, 90, 30);
        phoneTextField.setBounds(140, 280, 265, 30);

        paymentLabel.setBounds(50, 320, 280, 30);
        paymentModeLabel.setBounds(50, 360, 100, 30);
        paymentBox.setBounds(144, 363, 150, 30);
        shippingLabel.setBounds(50,400,280,30);
        shippingBox.setBounds(144, 440, 150, 30);
        shipLabel.setBounds(50, 440, 280, 30);

        totalpriceLabel.setBounds(460, 380, 380, 30);
        infoShipLabel.setBounds(480,410,200,20);

        returnButton.setBounds(240, 500, 150, 50);
        confirmButton.setBounds(410,500,150,50);

        /* COMPONENTS FONT SECTION */
        titleLabel.setFont(new Font("Impact", Font.ITALIC, 45));
        titleLabel.setForeground(Color.ORANGE);
        totalpriceLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        totalpriceLabel.setForeground(Color.red);
        paymentModeLabel.setFont(font);
        paymentModeLabel.setForeground(Color.WHITE);
        shipLabel.setFont(font);
        shipLabel.setForeground(Color.WHITE);
        paymentBox.setFont(font);
        shippingBox.setFont(font);
        reciverSurnameTextField.setFont(font);
        phoneLabel.setFont(font);
        phoneLabel.setForeground(Color.WHITE);
        emailLabel.setFont(font);
        emailLabel.setForeground(Color.WHITE);
        addressUserLabel.setFont(font);
        addressUserLabel.setForeground(Color.WHITE);
        surnameLabel.setFont(font);
        surnameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        phoneTextField.setFont(font);
        reciverNameTextField.setFont(font);
        emailTextField.setFont(font);
        addressTextField.setFont(font);
        shippingLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        shippingLabel.setForeground(Color.ORANGE);
        reciverLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        reciverLabel.setForeground(Color.ORANGE);
        addressLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        addressLabel.setForeground(Color.ORANGE);
        paymentLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        paymentLabel.setForeground(Color.ORANGE);
        infoOrderLabel.setFont(new Font("Impact", Font.ITALIC, 28));
        infoOrderLabel.setForeground(Color.ORANGE);
        returnButton.setFont(fontpic);
        confirmButton.setFont(fontpic);
        infoShipLabel.setFont(new Font("Impact", Font.ITALIC, 12));
        infoShipLabel.setForeground(Color.red);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD,10 ));
        table.setFont(new Font("SansSerif", Font.BOLD,12 ));
        /* COMPONENTS ADD SECTION */
        add(shipLabel);
        add(shippingBox);
        add(shippingLabel);
        add(infoShipLabel);
        add(confirmButton);
        add(infoOrderLabel);
        add(totalpriceLabel);
        add(paymentModeLabel);
        add(paymentBox);
        add(phoneLabel);
        add(emailLabel);
        add(addressUserLabel);
        add(reciverSurnameTextField);
        add(surnameLabel);
        add(nameLabel);
        backGroundLabel.add(paymentLabel);
        backGroundLabel.add(phoneTextField);
        backGroundLabel.add(addressTextField);
        backGroundLabel.add(addressLabel);
        backGroundLabel.add(emailTextField);
        backGroundLabel.add(reciverNameTextField);
        backGroundLabel.add(reciverLabel);
        backGroundLabel.add(titleLabel);
        backGroundLabel.add(returnButton);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(orderRequestListener);
        confirmButton.addActionListener(orderRequestListener);
    }

    public int getIdCart() { return idCart; }
    public String getUsername() {
        return username;
    }
    public JTextField getReciverNameTextField() { return reciverNameTextField; }
    public JTextField getReciverSurnameTextField() { return reciverSurnameTextField; }
    public JTextField getAddressTextField() { return addressTextField; }
    public String getPaymentBox() { return (String) paymentBox.getSelectedItem(); }
    public String getShippingBox() { return (String) shippingBox.getSelectedItem(); }
    public float getTotal() { return total; }

}
