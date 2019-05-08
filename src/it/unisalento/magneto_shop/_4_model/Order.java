package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.OrderDAO;

import java.util.ArrayList;

public class Order {

    public static final String PENDING = "IN ATTESA";
    public static final String SHIPPED = "SPEDITO";
    public static final String DECLINED = "RIFIUTATO";
    public static final String CANCELLED = "ANNULLATO";
    public static final String REFUNDED = "RIMBORSATO";
    public static final String COMPLETED = "COMPLETATO";

    private int idOrder;
    private String orderData;
    private String orderStatus;
    private float orderCost;
    private int idMember;
    private String reciver;
    private String address;
    private String payment;
    private String shippingCompany;

    public Order() { }

    public static boolean addOrderModel(int idMember, String reciver, String address, String payment, String shipping, ArrayList<Item> itemArrayList, float total, int idCart) { return OrderDAO.getInstance().addOrderDAO(idMember,reciver,address,payment,shipping,itemArrayList,total,idCart); }
    public static ArrayList<Order> getAllOrderNotCompletedModel() { return OrderDAO.getInstance().getAllOrderNotCompletedDAO(); }
    public static boolean editStatusOrderModel(int idOrder, String status) { return OrderDAO.getInstance().editStatusOrderDAO(idOrder,status); }
    public static ArrayList<Order> getOrderByIdMemberModel(int memberID) { return OrderDAO.getInstance().getOrderByIdMemberDAO(memberID); }
    public static int getQuantityForItemModel(int idItem, int idOrder) { return OrderDAO.getInstance().getQuantityForItemDAO(idItem,idOrder); }
    public static int getIdMemberFromIdOrderModel(int idOrder) { return OrderDAO.getInstance().getIdMemberFromIdOrderDAO(idOrder); }
    public static Order getOrderByIdOrderModel(int idOrder) { return OrderDAO.getInstance().getOrderByIdOrderDAO(idOrder); }
    public static boolean controlPresenceOfItemInOrderModel(int idItem) { return OrderDAO.getInstance().controlPresenceOfItemInOrderDAO(idItem); }


    public int getIdOrder() { return idOrder; }
    public String getOrderData() { return orderData; }
    public String getOrderStatus() { return orderStatus; }
    public float getOrderCost() { return orderCost; }
    public int getIdMember() { return idMember; }
    public String getReciver() { return reciver; }
    public String getAddress() { return address; }
    public String getPayment() { return payment; }
    public String getShippingCompany() { return shippingCompany; }


    public void setIdOrder(int idOrder) { this.idOrder = idOrder; }
    public void setOrderData(String orderData) { this.orderData = orderData; }
    public void setOrderStatus(String orderStatus) {this.orderStatus =orderStatus;}
    public void setOrderCost(float orderCost) { this.orderCost = orderCost; }
    public void setIdMember(int idMember) { this.idMember = idMember; }
    public void setReciver(String reciver) { this.reciver = reciver; }
    public void setAddress(String address) { this.address = address; }
    public void setPayment(String payment) { this.payment = payment;}
    public void setShippingCompany(String shippingCompany) { this.shippingCompany = shippingCompany; }

}
