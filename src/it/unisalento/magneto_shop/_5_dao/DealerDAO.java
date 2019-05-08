package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Dealer;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class DealerDAO {

    private static DealerDAO instance;

    public DealerDAO() { }

    public ArrayList<Dealer> getAllDealersDAO(){

        ArrayList<String[]> dealersListInfo = null;
        ArrayList<Dealer> dealerArrayList = new ArrayList<>();

        String queryAllProducers = "SELECT * FROM `DEALER`";

        dealersListInfo = DbConnection.getInstance().db_query(queryAllProducers);

        int i=0;

        while(i<dealersListInfo.size()){

            Dealer dealer = new Dealer();

            dealer.setDealer(dealersListInfo.get(i)[1]);
            dealer.setIdDealer(Integer.parseInt(dealersListInfo.get(i)[0]));
            dealerArrayList.add(dealer);

            i++;

        }

        return dealerArrayList;
    }

    public boolean dealerNameControlModel(String dealer) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `DEALER` WHERE `dealer`='" +dealer+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(dealer)
            if (arrayList.size() == 0 ) {
                result = false;
            }
            return result;
        } catch (Exception e) { return result; }
    }

    public int getIdFromDealer(String itemDealer) {

            String queryDealer = "SELECT `idDEALER` FROM `DEALER` WHERE `dealer`='" + itemDealer + "'";

            ArrayList<String[]> result = DbConnection.getInstance().db_query(queryDealer);

            if (result.size()>0) return Integer.parseInt(result.get(0)[0]);
            else return 0;
    }

    public boolean addDealerDAO(String newDealer) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`DEALER` (`dealer`) VALUES ('"+newDealer+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update;}
        return update;
    }

    public boolean editDealerDAO(String newDealer, String oldDealer) {
        boolean update = false;

        try {

            int idFromDealer = getIdFromDealer(oldDealer);

            String query = "UPDATE `dbMagneto`.`DEALER` SET `dealer`='"+newDealer+"' WHERE `idDEALER`='"+idFromDealer+"'";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean deleteDealerDAO(String dealerBoxDel) {

        boolean update = false;

        try {

            ArrayList<Item> itemArrayList = ItemDAO.getInstance().getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(dealerBoxDel,0);


            if (itemArrayList.size() == 0) {

                int idFromDealer = getIdFromDealer(dealerBoxDel);

                String query1 = "DELETE FROM `dbMagneto`.`DEALER` WHERE `idDEALER`='" +idFromDealer+ "'";
                update = DbConnection.getInstance().db_update(query1);

            }else{

                for (Item anItemArrayList : itemArrayList) {
                    ItemDAO.getInstance().deleteItemDAO(anItemArrayList.getIdItem());
                }
                String query = "DELETE FROM `dbMagneto`.`DEALER` WHERE `idDEALER`='" + itemArrayList.get(0).getIdProducer()+ "'";

                update = DbConnection.getInstance().db_update(query);
            }
        } catch (Exception e) { return update; }
        return update;
    }

    //SINGLETON
    public static DealerDAO getInstance() {
        if(instance == null)
            instance = new DealerDAO();
        return instance;
    }
}

