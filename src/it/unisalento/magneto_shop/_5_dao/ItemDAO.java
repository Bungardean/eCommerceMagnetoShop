package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class ItemDAO {

    private static ItemDAO instance;


    public ItemDAO() { }

    public ArrayList<Item> getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(String byCategoryByDepartmentByPrice, int available) {

        ArrayList<String[]> prodListInfo = null;
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ArrayList<String[]> arrayList =new ArrayList<>();
        String itemListQuery = null;
        int idCategory = 0;
        int idDepartment = 0;
        int idProducer = 0;
        int idDealer = 0;
        try {


            if (available==1) {
                if (byCategoryByDepartmentByPrice != null) {


                    idCategory = CategoryDAO.getInstance().getIdfromCategory(byCategoryByDepartmentByPrice);


                    if (idCategory != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE CATEGORY_idCATEGORY='" + idCategory + "' AND available='" + available + "'";

                    } else if ((idDepartment = DepartmentDAO.getInstance().getIdFromDepartment(byCategoryByDepartmentByPrice)) != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE DEPARTMENT_idDEPARTMENT='" + idDepartment + "' AND available='" + available + "'";

                    } else if ((idProducer = ProducerDAO.getInstance().getIdfromProducer(byCategoryByDepartmentByPrice)) != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE PRODUCER_idPRODUCER='" + idProducer + "' AND available='" + available + "'";

                    } else if ((idDealer = DealerDAO.getInstance().getIdFromDealer(byCategoryByDepartmentByPrice)) != 0) {
                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE DEALER_idDEALER='" + idDealer + "' AND available='" + available + "'";

                    }

                } else {

                    /*QUERY IMPORTANTE RESTUTUISCE TUTTI GLI ITEM E LE INFO SUL PRODUTTORE E SUL DEALER RIVENDITORE E SUI SOTTO */
                    itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE available='" + available + "'";
                    //   itemListQuery = "SELECT * FROM ITEM";
                }
            }else {

                if (byCategoryByDepartmentByPrice != null) {


                    idCategory = CategoryDAO.getInstance().getIdfromCategory(byCategoryByDepartmentByPrice);


                    if (idCategory != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE CATEGORY_idCATEGORY='" + idCategory + "'";

                    } else if ((idDepartment = DepartmentDAO.getInstance().getIdFromDepartment(byCategoryByDepartmentByPrice)) != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE DEPARTMENT_idDEPARTMENT='" + idDepartment + "'";

                    } else if ((idProducer = ProducerDAO.getInstance().getIdfromProducer(byCategoryByDepartmentByPrice)) != 0) {

                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE PRODUCER_idPRODUCER='" + idProducer + "'";

                    } else if ((idDealer = DealerDAO.getInstance().getIdFromDealer(byCategoryByDepartmentByPrice)) != 0) {
                        itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 WHERE DEALER_idDEALER='" + idDealer + "'";

                    }

                } else {

                    /*QUERY IMPORTANTE RESTUTUISCE TUTTI GLI ITEM E LE INFO SUL PRODUTTORE E SUL DEALER RIVENDITORE E SUI SOTTO */
                    itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1";
                    //   itemListQuery = "SELECT * FROM ITEM";
                }

            }
            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);

          //  System.out.println(prodListInfo.size());

            int i = 0;

            for (i = 0 ; i < prodListInfo.size(); i++){
                Item item = new Item();

                item.setIdItem(Integer.valueOf(prodListInfo.get(i)[0]));
                item.setItemName(prodListInfo.get(i)[1]);
                item.setDescriptionItem(prodListInfo.get(i)[2]);
                item.setPrice(Float.parseFloat(prodListInfo.get(i)[3]));
                item.setSales(Float.parseFloat(prodListInfo.get(i)[4]));
                item.setFotoItem(DbConnection.getInstance().getBlobData().get(i));
                item.setIdProducer(Integer.valueOf(prodListInfo.get(i)[10]));
                item.setProducer(prodListInfo.get(i)[11]);
                item.setIdDealer(Integer.valueOf(prodListInfo.get(i)[12]));
                item.setDealer(prodListInfo.get(i)[13]);
                if (prodListInfo.get(i)[14] != null){
                    int idItemFather = Integer.parseInt(prodListInfo.get(i)[14]);
                    item.setIdItemFather(idItemFather);
                    String query = "SELECT ITEM.product FROM `ITEM` WHERE `idITEM`='"+ idItemFather + "'";
                    arrayList = DbConnection.getInstance().db_query_NO_FOTO(query);
                    item.setItemNameFather(arrayList.get(0)[0]);
                }else{
                    item.setItemNameFather("Nessuno");
                }
                itemArrayList.add(item);
            }
    }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return itemArrayList;
    }
    /* ASK DATABASE IF FOR THE NAME INSERT FOR ITEM THERE IS PRESENT */
    public Boolean itemNameControlDB(String itemName) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String queryMember = "SELECT * FROM `ITEM` WHERE `product`='" + itemName + "'";
            arrayList = DbConnection.getInstance().db_query(queryMember);
            //|| !arrayList.get(0)[1].equals(itemName)
            if (arrayList.size() == 0 ) {
                result = false;
            }
            return result;
        } catch (Exception e) { return result; }
    }
    public ArrayList<Item> getItemListByIdCartDAO(int idCart) {
        ArrayList<String[]> prodListInfo = null;
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ArrayList<String[]> arrayList =new ArrayList<>();
        String itemListQuery = null;


        try {


            itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 INNER JOIN CART_has_ITEM ON ITEM.idITEM = CART_has_ITEM.ITEM_idITEM WHERE CART_idCART='"+idCart+"'";

            // itemListQuery = "SELECT * FROM ITEM INNER JOIN CART_has_ITEM ON ITEM.idITEM = CART_has_ITEM.CART_idCART WHERE CART_idCART='"+idCart+"'";
            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);

            int i = 0;

            for (i = 0 ; i < prodListInfo.size(); i++){
                Item item = new Item();

                item.setIdItem(Integer.valueOf(prodListInfo.get(i)[0]));
                item.setItemName(prodListInfo.get(i)[1]);
                item.setDescriptionItem(prodListInfo.get(i)[2]);
                item.setPrice(Float.parseFloat(prodListInfo.get(i)[3]));
                item.setSales(Float.parseFloat(prodListInfo.get(i)[4]));
                item.setFotoItem(DbConnection.getInstance().getBlobData().get(i));
                item.setIdProducer(Integer.valueOf(prodListInfo.get(i)[10]));
                item.setProducer(prodListInfo.get(i)[11]);
                item.setIdDealer(Integer.valueOf(prodListInfo.get(i)[12]));
                item.setDealer(prodListInfo.get(i)[13]);
               /* if (prodListInfo.get(i)[14] != null){
                    int idItemFather = Integer.parseInt(prodListInfo.get(i)[13]);
                    item.setIdItemFather(idItemFather);
                    String query = "SELECT ITEM.product FROM `ITEM` WHERE `idITEM`='"+ idItemFather + "'";
                    arrayList = DbConnection.getInstance().db_query_NO_FOTO(query);
                    item.setItemNameFather(arrayList.get(0)[0]);
                }else{
                    item.setItemNameFather("Nessuno");
                }*/
                itemArrayList.add(item);
            }
        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return itemArrayList;
    }
    public  int getIdItemFromNameDAO(String itemName){

        ArrayList<String[]> prodListInfo = null;
        int result = 0;

        try {
            String itemListQuery = "SELECT ITEM.idITEM FROM ITEM WHERE product='"+itemName+"'";

            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);

            result = Integer.parseInt(prodListInfo.get(0)[0]);

        }catch(NullPointerException e){ e.printStackTrace(); return result; }

        return result;

    }
    public ArrayList<String> getIdFatherItemForIdItemDAO(int idItem) {

        ArrayList<String[]> prodListInfo = null;
        ArrayList<String> product = new ArrayList<>();

        try {
            String itemListQuery = "SELECT ITEM_has_ITEM.ITEM_idITEM FROM ITEM_has_ITEM WHERE ITEM_idITEM1='"+idItem+"'";

            //SELEZIONO ID PRODOTTI PADRE
            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);
            for (int i=0 ; i < prodListInfo.size(); i++){

                ArrayList<String[]> itemFather = DbConnection.getInstance().db_query("SELECT ITEM.product FROM ITEM WHERE idITEM="+Integer.valueOf(prodListInfo.get(i)[0])+" ");
                product.add(itemFather.get(0)[0]);
            }

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return product;
    }

    public boolean uploadItemDAO(String itemName, float itemPrice, float itemSalePrice, String itemDescription, String itemCategory, String itemDepartment, String itemProducer, String itemDealer, int idItemFather, String itemPathImg, int avaiable) {

        boolean update = false;
        try {

            int idCategory = CategoryDAO.getInstance().getIdfromCategory(itemCategory);
            int idDepartment = DepartmentDAO.getInstance().getIdFromDepartment(itemDepartment);
            int idProducer = ProducerDAO.getInstance().getIdfromProducer(itemProducer);
            int idDealer = DealerDAO.getInstance().getIdFromDealer(itemDealer);

            if (DbConnection.getInstance().db_update_Item(itemName, itemPrice, itemSalePrice, itemDescription, idCategory, idDepartment, 1, itemPathImg,avaiable)) {

                ArrayList<String[]> result = DbConnection.getInstance().db_query("SELECT idITEM FROM `ITEM` WHERE `product`='" + itemName + "'");
                int idItem = Integer.parseInt(result.get(0)[0]);

                String querryPRODUCER_has_ITEM = "insert into PRODUCER_has_ITEM(PRODUCER_idPRODUCER,ITEM_idITEM) values('" + idProducer + "','" + idItem + "')";
                String querryITEM_has_DEALER = "insert into ITEM_has_DEALER(ITEM_idITEM,DEALER_idDEALER) values('" + idItem + "','" + idDealer + "')";
                String querryITEM_has_ITEM = "INSERT INTO ITEM_has_ITEM(ITEM_idITEM,ITEM_idITEM1) VALUES('" + idItemFather + "','" + idItem + "')";

                update = DbConnection.getInstance().db_update(querryPRODUCER_has_ITEM);
                update = DbConnection.getInstance().db_update(querryITEM_has_DEALER);

                if (idItemFather != 0){
                    update = DbConnection.getInstance().db_update(querryITEM_has_ITEM);
                }

            }

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean editItemNameDAO(String editedItemName, int idItemToEdit) {

        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ITEM` SET `product`='"+editedItemName+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemPriceDAO(float price, int idItemToEdit) {

        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ITEM` SET `price`='"+price+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemSalePriceDAO(float salePrice, int idItemToEdit) {

        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ITEM` SET `sales`='"+salePrice+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemDescriptionDAO(String editItemDescription, int idItemToEdit) {
        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ITEM` SET `description`='"+editItemDescription+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemCategoryDAO(String editItemCategory, int idItemToEdit) {
        boolean update = false;

        try {

            int idCategory = CategoryDAO.getInstance().getIdfromCategory(editItemCategory);

            String query = "UPDATE `dbMagneto`.`ITEM` SET `CATEGORY_idCATEGORY`='"+idCategory+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemDepartmentDAO(String editItemDepartment, int idItemToEdit) {
        boolean update = false;

        try {

            int idDepartment = DepartmentDAO.getInstance().getIdFromDepartment(editItemDepartment);

            String query = "UPDATE `dbMagneto`.`ITEM` SET `DEPARTMENT_idDEPARTMENT`='"+idDepartment+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemProducerDAO(String editItemProducer, int idItemToEdit) {

        boolean update = false;

        try {

            int idProducer = ProducerDAO.getInstance().getIdfromProducer(editItemProducer);

            String query = "UPDATE `dbMagneto`.`PRODUCER_has_ITEM` SET `PRODUCER_idPRODUCER`='"+idProducer+"' WHERE `ITEM_idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemDealerDAO(String editItemDealer, int idItemToEdit) {

        boolean update = false;

        try {

            int idDealer = DealerDAO.getInstance().getIdFromDealer(editItemDealer);

            String query = "UPDATE `dbMagneto`.`ITEM_has_DEALER` SET `DEALER_idDEALER`='"+idDealer+"' WHERE `ITEM_idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemStatusDAO(int disponibile, int idItemToEdit) {

        boolean update = false;

        try {

            String query = "UPDATE `dbMagneto`.`ITEM` SET `available`='"+disponibile+"' WHERE `idITEM`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;


    }
    public boolean editItemFatherDAO(int editIdItemFather, int idItemToEdit) {

        boolean update = false;
        ArrayList<String[]> arrayList;

        try {

            //TRY TO SEE IF IS PRESENT A ROW WHIT ITEM_idITEM1 ELSE CREATE ONE OR UPDATE
            String queryMember = "SELECT * FROM `ITEM_has_ITEM` WHERE `ITEM_idITEM1`='" +idItemToEdit+ "'";
            arrayList = DbConnection.getInstance().db_query(queryMember);
            //|| !arrayList.get(0)[1].equals(itemName)

            if (arrayList.size() == 0 ) {

                String querryITEM_has_ITEM = "INSERT INTO ITEM_has_ITEM(ITEM_idITEM,ITEM_idITEM1) VALUES('" + editIdItemFather + "','" + idItemToEdit + "')";
                update = DbConnection.getInstance().db_update(querryITEM_has_ITEM);

            }else {

                String query = "UPDATE `dbMagneto`.`ITEM_has_ITEM` SET `ITEM_idITEM`='"+editIdItemFather+"' WHERE `ITEM_idITEM1`='"+idItemToEdit+"'";
                update = DbConnection.getInstance().db_update(query);

            }

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean editItemPathImgDAO(String editItemPathImg, int idItemToEdit) {

        return DbConnection.getInstance().db_update_Item_Foto(editItemPathImg,idItemToEdit);

    }

    public boolean deleteItemFatherDAO(int idItemToEdit) {

        boolean update = false;

        try {

            String query = "DELETE FROM `dbMagneto`.`ITEM_has_ITEM` WHERE `ITEM_idITEM1`='"+idItemToEdit+"'";

            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }
    public boolean deleteItemDAO(int idItem) {

        boolean update = false;

        try {

            String query = "DELETE FROM `dbMagneto`.`ITEM` WHERE `idITEM`='"+idItem+"'";
            String query1 = "DELETE FROM `dbMagneto`.`ITEM_has_DEALER` WHERE `ITEM_idITEM`='"+idItem+"'";
            String query2 =" DELETE FROM `dbMagneto`.`ITEM_has_ITEM` WHERE `ITEM_idITEM`='"+idItem+"' OR ITEM_idITEM1='"+idItem+"' ";
            String query3 ="DELETE FROM `dbMagneto`.`PRODUCER_has_ITEM` WHERE `ITEM_idITEM`='"+idItem+"'";

            update = DbConnection.getInstance().db_update(query3);
            update = DbConnection.getInstance().db_update(query2);
            update = DbConnection.getInstance().db_update(query1);
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public ArrayList<Item> getItemListByIdOrderDAO(int idOrder) {
        ArrayList<String[]> prodListInfo = null;
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ArrayList<String[]> arrayList =new ArrayList<>();
        String itemListQuery = null;


        try {


            itemListQuery = "SELECT ITEM.*,PRODUCER.idPRODUCER,PRODUCER.producer,DEALER.idDEALER,DEALER.dealer,ITEM_has_ITEM.ITEM_idITEM FROM ITEM INNER JOIN PRODUCER_has_ITEM ON ITEM.idITEM=PRODUCER_has_ITEM.ITEM_idITEM INNER JOIN PRODUCER ON PRODUCER_has_ITEM.PRODUCER_idPRODUCER=PRODUCER.idPRODUCER INNER JOIN ITEM_has_DEALER ON ITEM.idITEM=ITEM_has_DEALER.ITEM_idITEM INNER JOIN DEALER ON ITEM_has_DEALER.DEALER_idDEALER=DEALER.idDEALER LEFT JOIN ITEM_has_ITEM ON ITEM.idITEM = ITEM_has_ITEM.ITEM_idITEM1 INNER JOIN ORDER_has_ITEM ON ITEM.idITEM = ORDER_has_ITEM.ITEM_idITEM WHERE ORDER_idORDER='"+idOrder+"'";

            // itemListQuery = "SELECT * FROM ITEM INNER JOIN CART_has_ITEM ON ITEM.idITEM = CART_has_ITEM.CART_idCART WHERE CART_idCART='"+idCart+"'";
            prodListInfo = DbConnection.getInstance().db_query(itemListQuery);

            int i = 0;

            for (i = 0 ; i < prodListInfo.size(); i++){
                Item item = new Item();

                item.setIdItem(Integer.valueOf(prodListInfo.get(i)[0]));
                item.setItemName(prodListInfo.get(i)[1]);
                item.setDescriptionItem(prodListInfo.get(i)[2]);
                item.setPrice(Float.parseFloat(prodListInfo.get(i)[3]));
                item.setSales(Float.parseFloat(prodListInfo.get(i)[4]));
                item.setFotoItem(DbConnection.getInstance().getBlobData().get(i));
                item.setIdProducer(Integer.valueOf(prodListInfo.get(i)[10]));
                item.setProducer(prodListInfo.get(i)[11]);
                item.setIdDealer(Integer.valueOf(prodListInfo.get(i)[12]));
                item.setDealer(prodListInfo.get(i)[13]);
                if (prodListInfo.get(i)[14] != null){
                    int idItemFather = Integer.parseInt(prodListInfo.get(i)[13]);
                    item.setIdItemFather(idItemFather);
                    String query = "SELECT ITEM.product FROM `ITEM` WHERE `idITEM`='"+ idItemFather + "'";
                    arrayList = DbConnection.getInstance().db_query_NO_FOTO(query);
                    item.setItemNameFather(arrayList.get(0)[0]);
                }else{
                    item.setItemNameFather("Nessuno");
                }
                itemArrayList.add(item);
            }
        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return itemArrayList;

    }

    //SINGLETON
    public static ItemDAO getInstance() {
        if (instance == null)
            instance = new ItemDAO();
        return instance;
    }
}
