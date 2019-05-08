package it.unisalento.magneto_shop._3_business;

import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.Order;

import java.util.ArrayList;

public class OrderBusiness {

    private static OrderBusiness instance;


    public OrderBusiness() {}

    public ArrayList<Order> getAllOrderNotCompletedBusiness() {
        return Order.getAllOrderNotCompletedModel();
    }
    public boolean editStatusOrderBusiness(int idOrder, String status) { return Order.editStatusOrderModel(idOrder,status); }
    public ArrayList<Order> getOrderByIdMemberBusiness(String username) {

        Member member = (Member) Session.getInstance().mappa.get(username);

        return Order.getOrderByIdMemberModel(member.getMemberID());

    }
    public ArrayList<Item> getItemByIdOrderBusiness(int idOrder) {

        return Item.getItemListByIdOrderModel(idOrder);
    }
    public int getQuantityForItemBusiness(int idItem, int idOrder) {
        return Order.getQuantityForItemModel(idItem,idOrder);
    }
    public int getIdMemberFromIdOrderBusiness(int idOrder) {

        return Order.getIdMemberFromIdOrderModel(idOrder);
    }
    public Order getOrderByIdOrderBusiness(int idOrder) {
        return Order.getOrderByIdOrderModel(idOrder);
    }
    public boolean controlPresenceOfItemInOrderBusiness(String itemToDelete) {
        int idItem = CatalogBusiness.getInstance().getIdItemFromNameBusiness(itemToDelete);
        return Order.controlPresenceOfItemInOrderModel(idItem);
    }


    //SINGLETON
    public static OrderBusiness getInstance(){

        if( instance == null )
            instance = new OrderBusiness();
        return instance;
    }

}
