package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Category;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class CategoryDAO {

    private static CategoryDAO instance;

    public CategoryDAO() { }

    public int getIdfromCategory(String category){

        String queryCategory = "SELECT `idCATEGORY` FROM `CATEGORY` WHERE `category`='" + category + "'";

        ArrayList<String[]> result = DbConnection.getInstance().db_query(queryCategory);

        if (result.size()>0) return Integer.parseInt(result.get(0)[0]);
        else return 0;

    }
    /* ASK DATABASE IF FOR THE NAME INSERT FOR CATEGORY THERE IS PRESENT */
    public Boolean categoryNameControlDAO(String category) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `CATEGORY` WHERE `category`='" +category+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(category)
            if (arrayList.size() == 0 ) {
                result = false;
            }
            return result;
        } catch (Exception e) { return result; }
    }

    public ArrayList<Category> getAllCategoriesDAO(){

        ArrayList<String[]> categListInfo = null;
        ArrayList<Category> categList = new ArrayList<>();

        try {

            String queryAllCategories = "SELECT * FROM `CATEGORY`";
            categListInfo = DbConnection.getInstance().db_query(queryAllCategories);

            int i=0;

            while(i<categListInfo.size()){

            Category  categ = new Category();

            categ.setCategoryName(categListInfo.get(i)[1]);
            categ.setIdCategory(Integer.parseInt(categListInfo.get(i)[0]));
            categList.add(categ);
            i++;
            }

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return categList;
    }


    public String getCategoryForItemDAO(int idItem) {

        ArrayList<String[]> category ;
        ArrayList<String[]> result ;

        try {
            String queryItem ="SELECT ITEM.CATEGORY_idCATEGORY FROM ITEM WHERE idITEM='"+idItem+"'";

            result = DbConnection.getInstance().db_query(queryItem);

            String queryCategory = "SELECT `category` FROM `CATEGORY` WHERE `idCATEGORY`='" +Integer.parseInt(result.get(0)[0])+ "'";

            category = DbConnection.getInstance().db_query(queryCategory);

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return category.get(0)[0];
    }

    public boolean addCategoryDAO(String category) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`CATEGORY` (`category`) VALUES ('"+category+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean editCategoryDAO(String category, String oldCategory) {

        boolean update = false;

        try {

            int idCategory = getIdfromCategory(oldCategory);

            String query = "UPDATE `dbMagneto`.`CATEGORY` SET `category`='"+category+"' WHERE `idCATEGORY`='"+idCategory+"'";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean deleteCategoryDAO(String category) {

        boolean update = false;

        try {

            int idCategory = getIdfromCategory(category);
            String query = "DELETE FROM `dbMagneto`.`CATEGORY` WHERE `idCATEGORY`='" + idCategory + "'";
            ArrayList<Item> itemArrayList = ItemDAO.getInstance().getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(category,0);

            if (itemArrayList.size() == 0) {

                update = DbConnection.getInstance().db_update(query);
            }else{

                for (int i = 0 ; i <itemArrayList.size();i++ ){
                    ItemDAO.getInstance().deleteItemDAO(itemArrayList.get(i).getIdItem());
                }
                update = DbConnection.getInstance().db_update(query);
            }
        } catch (Exception e) { return update; }
        return update;

    }

    //SINGLETON
    public static CategoryDAO getInstance() {
        if(instance == null)
            instance = new CategoryDAO();
        return instance;
    }
}
