package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.CartDAO;

import java.util.ArrayList;

public class Cart {

    private int idCart;
    private String typeOfCart;

    /* CONSTRUCTOR OF CART CLASS */
    public Cart() { }

    /* METHOD OF CART CLASS */
    public static ArrayList<Cart> getCartByMemberBusiness(int idMember) { return CartDAO.getInstance().getCartByMemberDAO(idMember); }
    public static int getQuantityForItemModel(int idItem,int idCart) { return CartDAO.getInstance().getQuantityForItemDAO(idItem, idCart); }
    public static boolean createMemberCartModel(int idMember) { return CartDAO.getInstance().createMemberCartDAO(idMember); }
    public static boolean addItemToCartModel(int idItem, int idCart, int quantity) { return CartDAO.getInstance().addItemToCartDAO(idItem,idCart,quantity); }
    public static boolean controlCartNameModel(String newCart, int memberID) { return CartDAO.getInstance().controlCartNameDAO(newCart,memberID); }
    public static boolean addCartToMemberModel(String newCart, int memberID) { return CartDAO.getInstance().addCartToMemberDAO(newCart,memberID); }
    public static boolean deleteCartFromMemberModel(int cartToDelete, int memberID) { return CartDAO.getInstance().deleteCartFromMemberDAO(cartToDelete,memberID); }
    public static boolean deleteItemFromCartDAO(int idCart, ArrayList<Item> itemNameArrayList) { return CartDAO.getInstance().deleteItemFromCartDAO(idCart,itemNameArrayList); }
    public static boolean controlPresenceOfItemInCartModel(int idItem) { return CartDAO.getInstance().controlPresenceOfItemInCartDAO(idItem);}

    /* GETTER AND SETTER OF CART CLASS */

    //GETTERS
    public int getIdCart() { return idCart; }
    public String getTypeOfCart() { return typeOfCart; }

    //SETTERS
    public void setIdCart(int idCart) { this.idCart = idCart; }
    public void setTypeOfCart(String typeOfCart) { this.typeOfCart = typeOfCart; }

}
