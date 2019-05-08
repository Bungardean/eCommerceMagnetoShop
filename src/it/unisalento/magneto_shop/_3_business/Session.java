package it.unisalento.magneto_shop._3_business;


import java.util.ArrayList;
import java.util.HashMap;

public class Session {

    private static Session instance;

    public HashMap<String, Object> mappa = new HashMap<String, Object>();
    public HashMap<Integer, ArrayList<it.unisalento.magneto_shop._4_model.Item>> mappaIdCartItem = new HashMap<Integer, ArrayList<it.unisalento.magneto_shop._4_model.Item>>();
    public HashMap<String, ArrayList<? extends it.unisalento.magneto_shop._4_model.Order>> mappaOrders = new HashMap<>();

    public static Session getInstance() {
        if(instance == null)
            instance = new Session();
        return instance;
    }

    public void svuotaSessione() {
        mappa = new HashMap<String, Object>();
    }


}
