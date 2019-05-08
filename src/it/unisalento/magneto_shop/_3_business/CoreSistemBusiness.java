package it.unisalento.magneto_shop._3_business;


import it.unisalento.magneto_shop._4_model.Cart;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.Order;
import it.unisalento.magneto_shop._5_dao.UserDAO;

import java.util.ArrayList;

public class CoreSistemBusiness {

    private static CoreSistemBusiness instance;

    public CoreSistemBusiness() { }

    public Boolean memberRegistration(String userName, String password, String name, String surname, String email, String address, String phone) {

        Member.uploadMemberModel(userName,password,name,surname,email,address,phone);

        int idMember = getIdMemberFromUsername(userName);

        return Cart.createMemberCartModel(idMember);

    }
    private int getIdMemberFromUsername(String userName) {
        return Member.getIdMemberFromUsernameModel(userName);
    }
    public boolean userNameControlBussines(String userName){

        if (!UserDAO.getInstance().userNameControlDAO(userName)){ return false; } //THERE IS NOT PRESENT
        else  return true;  //THERE IS PRESENT
    }

    public ArrayList<Member> getMemberNotActiveBusiness() {
        return Member.getMemberNotActiveModel();
    }
    public ArrayList<Member> getMemberActiveBusiness() { return Member.getMemberActiveModel(); }
    public Member getMemberByIdBusiness(int idMember) { return Member.getMemberByIdModel(idMember); }


    public boolean disableMemberBusiness(int idMember) {
        return Member.disabelMemberMemberModel(idMember);
    }
    public boolean enableMemberBusiness(int idMember) {

        return Member.enableMemberModel(idMember);

    }

    public boolean addOrderBusiness(String name, String surname, String address, String username, String payment, String shipping, int idCart, float total) {

        String reciver = name+" "+surname;
        Member member  = (Member) Session.getInstance().mappa.get(username);
        int idMember =  member.getMemberID();
        ArrayList<Item> itemArrayList = Session.getInstance().mappaIdCartItem.get(idCart);

        return Order.addOrderModel(idMember,reciver,address,payment,shipping,itemArrayList,total,idCart);

    }

    //SINGLETON
    public static CoreSistemBusiness getInstance() {
        if (instance == null)
            instance = new CoreSistemBusiness();
        return instance;

    }
}