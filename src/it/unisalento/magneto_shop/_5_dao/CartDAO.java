package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Cart;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class CartDAO {

    private static CartDAO instance;


    public ArrayList<Cart> getCartByMemberDAO(int idMember) {


        ArrayList<String[]> resultInfo = null;
        ArrayList<Cart> cartArrayList = new ArrayList<>();
        String query = null;

    try{

        query ="SELECT CART.idCART, CART.typeofcart FROM CART WHERE MEMBER_idMEMBER='"+idMember+"' ";

        resultInfo = DbConnection.getInstance().db_query(query);


        int i = 0 ;
        while (i < resultInfo.size()) {

            Cart cart = new Cart();

            cart.setIdCart(Integer.parseInt(resultInfo.get(i)[0]));
            cart.setTypeOfCart(resultInfo.get(i)[1]);

            cartArrayList.add(cart);
            i++;
        }
    }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return cartArrayList;
    }

    public int getQuantityForItemDAO(int idItem, int idCart) {


        ArrayList<String[]> resultInfo = null;
        String query = null;

        try{

            query ="SELECT CART_has_ITEM.quantity FROM CART_has_ITEM WHERE CART_idCART='"+idCart+"' AND ITEM_idITEM='"+idItem+"'";

            resultInfo = DbConnection.getInstance().db_query(query);


        }catch(NullPointerException e){ e.printStackTrace();}

        return Integer.parseInt(resultInfo.get(0)[0]);
    }

    public boolean createMemberCartDAO(int idMember) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`CART` (`typeofcart`,`MEMBER_idMEMBER`) VALUES ('Spesa','"+idMember+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public int getIdCartFromNameCartDAO(String typeOfCart) {

        int update = 0;
        ArrayList<String[]> resultInfo = null;
        String query = null;

        try{

            query ="SELECT CART.idCART FROM CART WHERE typeofcart='"+typeOfCart+"'";

            resultInfo = DbConnection.getInstance().db_query(query);
            update =  Integer.parseInt(resultInfo.get(0)[0]);

        }catch(NullPointerException e){ e.printStackTrace();}

        return update;


    }

    public boolean addItemToCartDAO(int idItem, int idCart, int quantity) {

        boolean update = false;
        ArrayList<String[]> resultInfo = null;

        try {


            String query ="SELECT CART_has_ITEM.quantity FROM CART_has_ITEM WHERE CART_idCART='"+idCart+"' AND ITEM_idITEM='"+idItem+"'";

            resultInfo = DbConnection.getInstance().db_query(query);

            if (resultInfo.size()==0){

                query = "INSERT INTO `dbMagneto`.`CART_has_ITEM` (`CART_idCART`,`ITEM_idITEM`,`quantity`) VALUES ('"+idCart+"','"+idItem+"','"+quantity+"')";
                update = DbConnection.getInstance().db_update(query);
            }else {
                int qnt = quantity+ Integer.parseInt(resultInfo.get(0)[0]);
                query = "UPDATE `dbMagneto`.`CART_has_ITEM` SET `quantity`='"+qnt+"' WHERE `CART_idCART`='"+idCart+"'AND ITEM_idITEM = '"+idItem+"'";
                update = DbConnection.getInstance().db_update(query);

            }



        } catch (Exception e) { return update; }
        return update;

    }

    public boolean controlCartNameDAO(String newCart, int memberID) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `CART` WHERE `typeofcart`='" +newCart+ "' AND MEMBER_idMEMBER='"+memberID+"' ";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(category)
            if (arrayList.size() == 0 ) {
                result = false; //non presente
            }
        } catch (Exception e) { return result; }

        return result;
    }

    public boolean controlPresenceOfItemInCartDAO(int idItem) {
        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `CART_has_ITEM` WHERE `ITEM_idITEM`='" +idItem+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(category)
            if (arrayList.size() == 0 ) {
                result = false; //non presente
            }
        } catch (Exception e) { return result; }

        return result;
    }

    public boolean addCartToMemberDAO(String newCart, int memberID) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`CART` (`typeofcart`, `MEMBER_idMEMBER`) VALUES ('"+newCart+"', '"+memberID+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }

        return update;
    }

    public boolean deleteCartFromMemberDAO(int idcartToDelete, int memberID) {

        boolean update = false;

        try {

            String query = "SELECT * FROM dbMagneto.CART_has_ITEM WHERE CART_idCART='"+idcartToDelete+"'";
            ArrayList<String[]> result = DbConnection.getInstance().db_query(query);

            String query1 ="DELETE FROM `dbMagneto`.`CART` WHERE `idCART`='"+idcartToDelete+"' and`MEMBER_idMEMBER`='"+memberID+"'";

            if(result.size() == 0) {

                update = DbConnection.getInstance().db_update(query1);

            }else{

                for (String[] aResult : result) {
                    String query2 = "DELETE FROM `dbMagneto`.`CART_has_ITEM` WHERE `CART_idCART`='" + idcartToDelete + "' and`ITEM_idITEM`='" + aResult[1] + "'";
                    update = DbConnection.getInstance().db_update(query2);
                }
                update = DbConnection.getInstance().db_update(query1);
            }
        } catch (Exception e) { return update; }
        return update;


    }

    public boolean deleteItemFromCartDAO(int idCart, ArrayList<Item> itemArrayList) {


        boolean update = false;

        try {

            for (Item anItemNameArrayList : itemArrayList) {

                String query = "DELETE FROM `dbMagneto`.`CART_has_ITEM` WHERE `CART_idCART`='" + idCart + "' and`ITEM_idITEM`='" + anItemNameArrayList.getIdItem() + "'";
                update = DbConnection.getInstance().db_update(query);

            }

        } catch (Exception e) { return update; }

        return update;



    }
    //SINGLETON
    public static CartDAO getInstance(){

        if( instance == null )
            instance = new CartDAO();
        return instance;
    }


}
