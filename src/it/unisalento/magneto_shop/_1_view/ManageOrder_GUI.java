package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.AdministratorListener;
import it.unisalento.magneto_shop._3_business.OrderBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageOrder_GUI extends JPanel {


    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);

    private JLabel titleLabel = new JLabel("Gestione Ordine");
    private JLabel orderLabel = new JLabel("Seleziona Ordine");
    private JLabel statusLabel = new JLabel("Modifica Stato");

    private JButton confirmButton = new JButton("Conferma");
    private JButton returnButton = new JButton("Indietro");

    private Font font = new Font("Impact",  Font.ITALIC,20 );
    private Font fontpic = new Font("Impact", Font.ITALIC,15 );

    private String col[] = {"ID Ordine","Stato Ordine"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
    private JTable table ;
    private JScrollPane scrollPane;


    private DefaultComboBoxModel<String> modelOrder =new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> modelStatus =new DefaultComboBoxModel<>();


    private JComboBox<String> orderBoxEdit;
    private JComboBox<String> statusBox;

    private AdministratorListener administratorListener = new AdministratorListener(this);

    private String username;

    public ManageOrder_GUI(String username) {


        this.username =username;
        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        ArrayList<Order> orderArrayList = OrderBusiness.getInstance().getAllOrderNotCompletedBusiness();

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

            int idOrder = order.getIdOrder();
            String orderStatus = order.getOrderStatus();

            modelOrder.addElement(String.valueOf(idOrder));

            Object[] data = {idOrder, orderStatus};

            tableModel.addRow(data);
        }

        /* TABLE  SECTION */
        table = new JTable(tableModel){
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
        for(int x=0;x<2;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        table.setAutoCreateRowSorter (true);
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);

        orderBoxEdit = new JComboBox<>(modelOrder);

        modelStatus.addElement("");
        modelStatus.addElement(Order.PENDING);
        modelStatus.addElement(Order.SHIPPED);
        modelStatus.addElement(Order.DECLINED);
        modelStatus.addElement(Order.REFUNDED);
        modelStatus.addElement(Order.COMPLETED);

        statusBox = new JComboBox<>(modelStatus);


        /* COMPONENTS PLACEMENT SECTION */

        backGroundLabel.setBounds(0,0,800,600);
        titleLabel.setBounds(243,25, 390,50);

        orderLabel.setBounds(200 ,120,160,50);
        orderBoxEdit.setBounds(360,120,140,50);
        confirmButton.setBounds(500,170,100,50);
        statusLabel.setBounds(200,170,160,50);
        statusBox.setBounds(360,170,140,50);


        scrollPane.setBounds(200,240,400,250);
        returnButton.setBounds(350,500,100,50);

        /* COMPONENTS FONT SECTION */

        titleLabel.setFont(new Font("Impact",  Font.ITALIC,45 ));
        titleLabel.setForeground(Color.ORANGE);
        returnButton.setFont(fontpic);
        confirmButton.setFont(fontpic);
        statusLabel.setForeground(Color.WHITE);
        statusBox.setFont(font);
        statusLabel.setFont(font);
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        orderLabel.setFont(font);
        orderLabel.setForeground(Color.WHITE);
        orderBoxEdit.setFont(font);

        /* COMPONENTS ADD SECTION */

        backGroundLabel.add(confirmButton);
        backGroundLabel.add(statusBox);
        backGroundLabel.add(orderBoxEdit);
        backGroundLabel.add(scrollPane);
        backGroundLabel.add(statusLabel);
        backGroundLabel.add(orderLabel);
        backGroundLabel.add(titleLabel);
        backGroundLabel.add(returnButton);
        add(backGroundLabel);

        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(administratorListener);
        confirmButton.addActionListener(administratorListener);
    }
    public String getUsername() { return username; }
    public String getOrderBoxEdit() { return (String) orderBoxEdit.getSelectedItem(); }
    public String getStatusBox() { return (String) statusBox.getSelectedItem(); }

}
