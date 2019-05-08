package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.ProducerDAO;

import java.util.ArrayList;

public class Producer extends Dealer {

    private int idProducer;
    private String producer;

    /* CONSTRUCTOR OF PRODUCER CLASS */
    public Producer() { }

    /* METHOD OF PRODUCER CLASS */
    public static ArrayList<Producer> getAllproducers(){ return ProducerDAO.getInstance().getAllProducersDAO(); }
    public static boolean addProducerModel(String newProducer) { return ProducerDAO.getInstance().addProducerDAO(newProducer); }
    public static boolean editProducerModel(String newProducer, String oldProducer) { return ProducerDAO.getInstance().editProducerDAO(newProducer,oldProducer); }
    public static boolean deleteProducerModel(String producer) { return ProducerDAO.getInstance().deleteProducerDAO(producer); }
    public static boolean producerNameControlModel(String producer) { return ProducerDAO.getInstance().producerNameControlDAO(producer); }


    /* GETTER AND SETTER OF PRODUCER CLASS */

    //GETTERS
    public int getIdProducer() { return idProducer; }
    public void setIdProducer(int idProducer) { this.idProducer = idProducer; }

    //SETTERS
    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

}
