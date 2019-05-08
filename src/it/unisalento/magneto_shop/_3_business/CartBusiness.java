package it.unisalento.magneto_shop._3_business;

import it.unisalento.magneto_shop._4_model.Cart;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;

import java.util.ArrayList;

public class CartBusiness {

    private static CartBusiness instance;

    public CartBusiness() { }

    public int getQuantityForItemBusiness(int idItem, int idCart) { return Cart.getQuantityForItemModel(idItem,idCart); }
    public ArrayList<Cart> getCartByMemberBusiness(String username) {
        Member member = (Member) Session.getInstance().mappa.get(username);
        return Cart.getCartByMemberBusiness(member.getMemberID());
    }
    public ArrayList<Item> getItemListByIdCart(int idCart) {
        return Item.getItemListByIdCartModel(idCart);
    }
    public boolean controlCartNameBusiness(String newCart , String userName) {


        Member member = (Member) Session.getInstance().mappa.get(userName);
        return Cart.controlCartNameModel(newCart,member.getMemberID());
    }

    public boolean addItemToCartBusiness(int idItem, String userName, String typeOfCart, int quantity) {

        boolean update =false;

        ArrayList<Cart> arrayList = getCartByMemberBusiness(userName);

        for (Cart anArrayList : arrayList) {
            if (typeOfCart.equals(anArrayList.getTypeOfCart())) {
                int idCart = anArrayList.getIdCart();
                update = Cart.addItemToCartModel(idItem, idCart, quantity);

            }
        }

        return update;
    }
    public boolean addCartToMemberBusiness(String newCart, String username) {

        Member member = (Member) Session.getInstance().mappa.get(username);
        return Cart.addCartToMemberModel(newCart,member.getMemberID());

    }
    public boolean deleteCartFromMemberBusiness(int idCartToDelete, String username) {

        boolean update =false;

                Member member = (Member) Session.getInstance().mappa.get(username);
                update = Cart.deleteCartFromMemberModel(idCartToDelete,member.getMemberID());

        return update;

    }
    public boolean deleteItemFromCartBusiness(int idCart, ArrayList<Item> itemNameArrayList) { return Cart.deleteItemFromCartDAO(idCart,itemNameArrayList); }

    public boolean controlPresenceOfItemInCartBusiness(String itemToDelete) {


        int idItem = CatalogBusiness.getInstance().getIdItemFromNameBusiness(itemToDelete);
        return Cart.controlPresenceOfItemInCartModel(idItem);
    }

    //SINGLETON

    public static CartBusiness getInstance(){

        if( instance == null )
            instance = new CartBusiness();
        return instance;
    }
}
