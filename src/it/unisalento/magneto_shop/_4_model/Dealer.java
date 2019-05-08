package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.DealerDAO;

import java.util.ArrayList;

public class Dealer {

    private int idDealer;
    private String dealer;

    /* CONSTRUCTOR OF DEALER CLASS */
    public Dealer() { }


    /* METHOD OF DEALER CLASS */
    public static ArrayList<Dealer> getAllDealers(){ return DealerDAO.getInstance().getAllDealersDAO(); }
    public static boolean addDealerModel(String newDealer) { return DealerDAO.getInstance().addDealerDAO(newDealer); }
    public static boolean editDealerModel(String newDealer, String oldDealer) { return DealerDAO.getInstance().editDealerDAO(newDealer,oldDealer);}
    public static boolean deleteDealerModel(String dealerBoxDel) { return DealerDAO.getInstance().deleteDealerDAO(dealerBoxDel); }
    public static boolean dealerNameControl(String dealer) { return DealerDAO.getInstance().dealerNameControlModel(dealer); }


    /* GETTER AND SETTER OF DEALER CLASS */

    //GETTERS
    public int getIdDealer() { return idDealer; }
    public void setIdDealer(int idDealer) { this.idDealer = idDealer; }

    //SETTERS
    public String getDealer() { return dealer; }
    public void setDealer(String dealer) { this.dealer = dealer; }


}

