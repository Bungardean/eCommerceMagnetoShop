package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.OrderRequestListener;
import it.unisalento.magneto_shop._3_business.OrderBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class OrderSatus_GUI extends JPanel {


    private String username;

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);

    private JLabel titleLabel = new JLabel("Informazioni Ordini");
    private JLabel orderLabel = new JLabel("Ordine");


    private JButton returnButton = new JButton("Indietro");
    private JButton printButton = new JButton("Stampa");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );

    private String col[] = {"ID Ordine","Stato Ordine","Pagamento","Costo","Corriere","Destinatario","Indirizzo","Data"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.


    private DefaultComboBoxModel<String> modelOrder =new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> modelStatus =new DefaultComboBoxModel<>();



    private JComboBox<String> orderBoxEdit;
    private JComboBox<String> statusBox;

    private OrderRequestListener orderRequestListener = new OrderRequestListener(this);


    public OrderSatus_GUI(String username) {
        this.username = username;

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        ArrayList<Order> orderArrayList = OrderBusiness.getInstance().getOrderByIdMemberBusiness(username);

        int row = tableModel.getRowCount();
        modelOrder.addElement("");

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Order order : orderArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            int idorder = order.getIdOrder();
            String orderStatus = order.getOrderStatus();
            String payment = order.getPayment();
            float orderCost = order.getOrderCost();
            String shippingCompany =order.getShippingCompany();
            String reciver = order.getReciver();
            String address = order.getAddress();
            String date = order.getOrderData();
            String st2 = date.substring(0, date.length() - 9);


            modelOrder.addElement(String.valueOf(idorder));

            Object[] data = {idorder,orderStatus,payment,orderCost,shippingCompany,reciver,address,st2};

            tableModel.addRow(data);
        }

        orderBoxEdit = new JComboBox<>(modelOrder);


        /* TABLE  SECTION */
        JTable table = new JTable(tableModel) {
            @Override
            /*RENDE NON EDITABILE LA TABELLA*/
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // to center a value in JTable cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<8;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        JScrollPane scrollPane = new JScrollPane(table);


        /* COMPONENTS PLACEMENT SECTION */

        backGroundLabel.setBounds(0,0,800,600);
        titleLabel.setBounds(243,25, 390,50);
        printButton.setBounds(500,120,100,50);
        orderLabel.setBounds(200,120,120,50);

        orderBoxEdit.setBounds(320,120,180,50);
        scrollPane.setBounds(20,170,760,300);
        returnButton.setBounds(350,500,100,50);

        /* COMPONENTS FONT SECTION */

        titleLabel.setFont(new Font("Impact",  Font.ITALIC,45 ));
        titleLabel.setForeground(Color.ORANGE);
        orderLabel.setFont(font);
        orderLabel.setForeground(Color.WHITE);
        returnButton.setFont(fontpic);
        printButton.setFont(fontpic);
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        orderBoxEdit.setFont(fontpic);
        /* COMPONENTS ADD SECTION */
        backGroundLabel.add(orderLabel);
        backGroundLabel.add(orderBoxEdit);
        backGroundLabel.add(printButton);
        backGroundLabel.add(scrollPane);
        backGroundLabel.add(titleLabel);
        backGroundLabel.add(returnButton);
        add(backGroundLabel);

        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(orderRequestListener);
        returnButton.setActionCommand("Indietro1");
        printButton.addActionListener(orderRequestListener);
    }

    public String getUsername() { return username; }
    public String getOrderBoxEdit() { return (String) orderBoxEdit.getSelectedItem(); }

}
