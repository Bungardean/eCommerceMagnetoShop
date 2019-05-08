package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Order;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAO {

    private static OrderDAO instance;

    public OrderDAO() { }

    public boolean addOrderDAO(int idMember, String reciver, String address, String payment, String shipping, ArrayList<Item> itemArrayList, float total, int idCart) {



        boolean update = false;

        try {

           //  Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
           // Date today = calendar.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String data = dateFormat.format(date);



            String query = "INSERT INTO `dbMagneto`.`ORDER` (`sale_data`,status,order_cost,MEMBER_idMEMBER,ADMINISTRATOR_idADMINISTRATOR,reciver,address,payment,shipping) VALUES ('"+data+"','"+Order.PENDING+"', '"+total+"','"+idMember+"','1','"+reciver+"','"+address+"','"+payment+"','"+shipping+"')";
            update = DbConnection.getInstance().db_update(query);
            if (update){

                int idOrder = getIdOrderDAO(idMember,data);

                for (Item anItemArrayList : itemArrayList) {
                    int quantita = CartDAO.getInstance().getQuantityForItemDAO(anItemArrayList.getIdItem(), idCart);
                    String query1 = "INSERT INTO `dbMagneto`.`ORDER_has_ITEM` (ORDER_idORDER,`ITEM_idITEM`,quantity) VALUES ('" + idOrder + "','" + anItemArrayList.getIdItem() + "','" + quantita + "')";
                    update = DbConnection.getInstance().db_update(query1);
                }
            }



        } catch (Exception e) { return update; }
        return update;

    }


    public int getIdOrderDAO(int idMember, String data){


        ArrayList<String[]> prodListInfo = null;
        int result = 0;

        try {
            String itemListQuery = "SELECT ORDER.idORDER FROM dbMagneto.ORDER WHERE sale_data='"+data+"' AND MEMBER_idMEMBER= '"+idMember+"'";

            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);

            result = Integer.parseInt(prodListInfo.get(0)[0]);

        }catch(NullPointerException e){ e.printStackTrace(); return result; }

        return result;
    }
    public boolean controlPresenceOfItemInOrderDAO(int idItem) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `ORDER_has_ITEM` WHERE `ITEM_idITEM`='" +idItem+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(category)
            if (arrayList.size() == 0 ) {
                result = false; //non presente
            }
        } catch (Exception e) { return result; }

        return result;
    }

    public ArrayList<Order> getAllOrderNotCompletedDAO() {


        ArrayList<String[]> arrayList = null;
        ArrayList<Order> orderArrayList = new ArrayList<>();

        try {

            String queryAllCategories = "SELECT * FROM `ORDER`";
            arrayList = DbConnection.getInstance().db_query(queryAllCategories);


            for (String[] anArrayList : arrayList) {


                if (!anArrayList[2].equals(Order.COMPLETED)) {

                    Order order = new Order();

                    order.setOrderData(anArrayList[1]);
                    order.setIdOrder(Integer.parseInt(anArrayList[0]));
                    order.setOrderStatus(anArrayList[2]);
                    order.setOrderCost(Float.parseFloat(anArrayList[3]));
                    order.setIdMember(Integer.parseInt(anArrayList[4]));
                    order.setReciver(anArrayList[6]);
                    order.setAddress(anArrayList[7]);
                    order.setPayment(anArrayList[8]);
                    order.setShippingCompany(anArrayList[9]);

                    orderArrayList.add(order);
                }
            }

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return orderArrayList;

    }

    public boolean editStatusOrderDAO(int idOrder, String status) {

        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ORDER` SET `status`='"+status+"' WHERE `idORDER`='"+idOrder+"'";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;

    }

    public ArrayList<Order> getOrderByIdMemberDAO(int memberID) {





        ArrayList<String[]> arrayList = null;
        ArrayList<Order> orderArrayList = new ArrayList<>();

        try {

            String queryAllCategories = "SELECT * FROM `ORDER` WHERE MEMBER_idMEMBER='"+memberID+"'";
            arrayList = DbConnection.getInstance().db_query(queryAllCategories);


            for (String[] anArrayList : arrayList) {


                    Order order = new Order();

                    order.setOrderData(anArrayList[1]);
                    order.setIdOrder(Integer.parseInt(anArrayList[0]));
                    order.setOrderStatus(anArrayList[2]);
                    order.setOrderCost(Float.parseFloat(anArrayList[3]));
                    order.setIdMember(Integer.parseInt(anArrayList[4]));
                    order.setReciver(anArrayList[6]);
                    order.setAddress(anArrayList[7]);
                    order.setPayment(anArrayList[8]);
                    order.setShippingCompany(anArrayList[9]);

                    orderArrayList.add(order);

            }

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return orderArrayList;
    }

    public int getQuantityForItemDAO(int idItem, int idOrder) {

        ArrayList<String[]> resultInfo = null;
        String query = null;

        try{

            query ="SELECT ORDER_has_ITEM.quantity FROM ORDER_has_ITEM WHERE ORDER_idORDER='"+idOrder+"' AND ITEM_idITEM='"+idItem+"'";

            resultInfo = DbConnection.getInstance().db_query(query);


        }catch(NullPointerException e){ e.printStackTrace();}

        return Integer.parseInt(resultInfo.get(0)[0]);
    }

    public int getIdMemberFromIdOrderDAO(int idOrder) {

        ArrayList<String[]> resultInfo = null;
        String query = null;

        try{

            query ="SELECT `ORDER`.MEMBER_idMEMBER FROM `ORDER` WHERE idORDER='"+idOrder+"'";

            resultInfo = DbConnection.getInstance().db_query(query);


        }catch(NullPointerException e){ e.printStackTrace();}

        return Integer.parseInt(resultInfo.get(0)[0]);
    }

    public Order getOrderByIdOrderDAO(int idOrder) {

        ArrayList<String[]> arrayList = null;

        Order order = null;
        try {

            String queryAllCategories = "SELECT * FROM `ORDER` WHERE idORDER='" + idOrder + "'";
            arrayList = DbConnection.getInstance().db_query(queryAllCategories);


            for (String[] anArrayList : arrayList) {


                order = new Order();

                order.setOrderData(anArrayList[1]);
                order.setIdOrder(Integer.parseInt(anArrayList[0]));
                order.setOrderStatus(anArrayList[2]);
                order.setOrderCost(Float.parseFloat(anArrayList[3]));
                order.setIdMember(Integer.parseInt(anArrayList[4]));
                order.setReciver(anArrayList[6]);
                order.setAddress(anArrayList[7]);
                order.setPayment(anArrayList[8]);
                order.setShippingCompany(anArrayList[9]);

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

        return order;


    }
    //SINGLETON
    public static OrderDAO getInstance(){

        if( instance == null)
            instance = new OrderDAO();
        return instance;
    }


}
