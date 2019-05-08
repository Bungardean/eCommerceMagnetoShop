package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Producer;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class ProducerDAO {

    private static ProducerDAO instance;

    public ProducerDAO() { }

    public ArrayList<Producer> getAllProducersDAO(){

        ArrayList<String[]> producerListInfo = null;
        ArrayList<Producer> producerArrayList = new ArrayList<>();

        String queryAllProducers = "SELECT * FROM `PRODUCER`";

        producerListInfo = DbConnection.getInstance().db_query(queryAllProducers);

        int i=0;

        while(i<producerListInfo.size()){

            Producer producer = new Producer();

            producer.setProducer(producerListInfo.get(i)[1]);
            producer.setIdProducer(Integer.parseInt(producerListInfo.get(i)[0]));
            producerArrayList.add(producer);

            i++;

        }

        return producerArrayList;
    }
    public int getIdfromProducer(String producer){

        String queryProducer = "SELECT `idPRODUCER` FROM `PRODUCER` WHERE `producer`='" + producer + "'";

        ArrayList<String[]> result = DbConnection.getInstance().db_query(queryProducer);

        if (result.size()>0) return Integer.parseInt(result.get(0)[0]);
        else return 0;
    }
    public boolean producerNameControlDAO(String producer) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `PRODUCER` WHERE `producer`='" +producer+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(producer)
            if (arrayList.size() == 0 ) {
                result = false;
            }
            return result;
        } catch (Exception e) { return result; }
    }
    public boolean addProducerDAO(String newProducer) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`PRODUCER` (`producer`) VALUES ('"+newProducer+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update;}
        return update;
    }

    public boolean editProducerDAO(String newProducer, String oldProducer) {
        boolean update = false;

        try {

            int idfromProducer = getIdfromProducer(oldProducer);

            String query = "UPDATE `dbMagneto`.`PRODUCER` SET `producer`='"+newProducer+"' WHERE `idPRODUCER`='"+idfromProducer+"'";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean deleteProducerDAO(String producer) {
        boolean update = false;

        try {

            ArrayList<Item> itemArrayList = ItemDAO.getInstance().getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(producer,0);


            if (itemArrayList.size() == 0) {

                int idProducer = getIdfromProducer(producer);

                String query1 = "DELETE FROM `dbMagneto`.`PRODUCER` WHERE `idPRODUCER`='" +idProducer+ "'";
                update = DbConnection.getInstance().db_update(query1);

            }else{

                for (int i = 0 ; i <itemArrayList.size();i++ ){
                    ItemDAO.getInstance().deleteItemDAO(itemArrayList.get(i).getIdItem());
                }
                String query = "DELETE FROM `dbMagneto`.`PRODUCER` WHERE `idPRODUCER`='" + itemArrayList.get(0).getIdProducer()+ "'";

                update = DbConnection.getInstance().db_update(query);
            }
        } catch (Exception e) { return update; }
        return update;
    }

    //SINGLETON

    public static ProducerDAO getInstance() {
        if(instance == null)
            instance = new ProducerDAO();
        return instance;
    }

}
