package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.AdministratorListener;
import it.unisalento.magneto_shop._3_business.CoreSistemBusiness;
import it.unisalento.magneto_shop._3_business.OrderBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Administrator_GUI extends JPanel {

    private  String username;
    private static Administrator_GUI instance;


    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);

    private ImageIcon imgLogout = new ImageIcon(PathImmagini.returnPath()+"/icon/logout1.png");
    private JButton logoutButton = new JButton(imgLogout);

    private ImageIcon imgOrder = new ImageIcon(PathImmagini.returnPath()+"/icon/bottonORD.png");
    private JButton orderButton = new JButton(imgOrder);

    private ImageIcon imgM = new ImageIcon(PathImmagini.returnPath()+"/icon/ButtonMEMBER.png");
    private JButton manageMemberButton = new JButton(imgM);

    private JLabel managerNameSurnameLabel = new JLabel();
    private JLabel managerEmailLabel = new JLabel();
    private JLabel managerFotoLabel;

    private String col[] = {"Utenti non attivi"};
    private DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    private JTable table ;
    private JScrollPane scrollPane;

    private String col1[] = {"ID Ordine","Stato Ordine","Pagamento","Costo","Corriere","Destinatario","Indirizzo","Utente","Data"};
    private DefaultTableModel tableModelOrder = new DefaultTableModel(col1, 0);
    private JTable orderTable ;
    private JScrollPane scrollPaneOrder;

    private ArrayList<Member> memberArrayList = new ArrayList<>();
    private ArrayList<Order> orderArrayList = new ArrayList<>();

    private Font font = new Font("SansSerif", Font.BOLD,20 );
    private Font fontpic = new Font("SansSerif", Font.BOLD,12 );




    private AdministratorListener administratorListener = new AdministratorListener(this);

    public Administrator_GUI(Administrator administrator) {

        username = administrator.getUserName();

        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);

        Image img = Toolkit.getDefaultToolkit().createImage(administrator.getFoto());
        ImageIcon imageIcon =new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        orderArrayList.clear();//INIZIALIZZA

        orderArrayList = OrderBusiness.getInstance().getAllOrderNotCompletedBusiness();

        //SALVO LISTA ORDINI IN UN HASMAP
        Session.getInstance().mappaOrders.put(username,orderArrayList);


        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        int row = tableModelOrder.getRowCount();

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Order order : orderArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModelOrder.removeRow(j);
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
            Member user = CoreSistemBusiness.getInstance().getMemberByIdBusiness(order.getIdMember());
            String date = order.getOrderData();
            String st2 = date.substring(0, date.length() - 9);

            Object[] data = {idorder,orderStatus,payment,orderCost,shippingCompany,reciver,address,user.getUserName(),st2};

            tableModelOrder.addRow(data);
        }

        /* TABLE  SECTION */
        orderTable = new JTable(tableModelOrder){
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
        for(int x=0;x<9;x++){
            orderTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        orderTable.setAutoCreateRowSorter (true);
        orderTable.setRowHeight(40);
        orderTable.setPreferredScrollableViewportSize(orderTable.getPreferredSize());
        orderTable.setFillsViewportHeight(true);
        orderTable.setIntercellSpacing(new Dimension(0,5));

        scrollPaneOrder = new JScrollPane(orderTable);




        memberArrayList.clear();//INIZIALIZZA

        memberArrayList = CoreSistemBusiness.getInstance().getMemberNotActiveBusiness();

        /* SERVE IN CASO DEVE ESSERE AGGIORNATO */
        row = tableModel.getRowCount();

        /* TRASFERIMENTO DEI DATI DELLE SINGOLE ISTANZE DI CLASSE ITEM IN UN VETTOE */
        for (Member aCategoryArrayList : memberArrayList) {

            /*INIZIALIZZA LA TABELLA SE PRECEDENTEMENTE RIEMPITA*/
            if (row > 0) {
                for (int j = row - 1; j >= 0; j--) {
                    tableModel.removeRow(j);
                }
                row = -1;
            }

            String userName = aCategoryArrayList.getUserName();

            Object[] data = {userName};

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
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0,5));

        scrollPane = new JScrollPane(table);


        /* COMPONENTS SET SECTION */
        managerNameSurnameLabel.setText(administrator.getName()+" "+administrator.getSurname());
        managerEmailLabel.setText(administrator.getE_mail());
        managerFotoLabel = new JLabel(imageIcon);

        manageMemberButton.setBorder(null);
        orderButton.setBorder(null);
        /* COMPONENTS PLACEMENT SECTION */
        scrollPane.setBounds(25,230,120,320);
        scrollPaneOrder.setBounds(155,230,630,320);
        backGroundLabel.setBounds(0,0,800,600);
        managerFotoLabel.setBounds(25,25,150,150);
        managerNameSurnameLabel.setBounds(25,175,120 ,20);
        managerEmailLabel.setBounds(25,195,120,20);
        logoutButton.setBounds(150,180,30,30);
        manageMemberButton.setBounds(250,50,100,100);
        orderButton.setBounds(360,50,100,100);

        /* FONT SECTION */
        table.setFont(fontpic);
        table.getTableHeader().setFont(fontpic);
        orderTable.setFont(fontpic);
        orderTable.getTableHeader().setFont(fontpic);

        managerNameSurnameLabel.setFont(new Font("Impact",Font.BOLD,15 ));
        managerNameSurnameLabel.setForeground(Color.WHITE);
        managerEmailLabel.setFont(new Font("Impact",Font.BOLD,12 ));
        managerEmailLabel.setForeground(Color.WHITE);
        logoutButton.setBorder(null);
        /* COMPONENTS ADD SECTION */
        add(orderButton);
        add(scrollPaneOrder);
        add(manageMemberButton);
        add(scrollPane);
        add(managerEmailLabel);
        add(managerFotoLabel);
        add(logoutButton);
        add(managerNameSurnameLabel);
        add(backGroundLabel);
        /* ACTION LISTENERS SECTION */
        orderButton.addActionListener(administratorListener);
        orderButton.setActionCommand("Gestione Ordine");
        logoutButton.addActionListener(administratorListener);
        logoutButton.setActionCommand("Logout");
        manageMemberButton.setActionCommand("Gestione Utente");
        manageMemberButton.addActionListener(administratorListener);
    }

    public  String getUsername() { return username; }

}
