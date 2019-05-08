package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._4_model.Department;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class DepartmentDAO {

    private static DepartmentDAO instance;

    public DepartmentDAO() { }

    public int getIdFromDepartment(String department){

        String queryDepartment = "SELECT `idDEPARTMENT` FROM `DEPARTMENT` WHERE `department`='" +department+ "'";

        ArrayList<String[]> result = DbConnection.getInstance().db_query(queryDepartment);

        if (result.size()>0) return Integer.parseInt(result.get(0)[0]);
        else return 0;
    }

    /* ASK DATABASE IF FOR THE NAME INSERT FOR ITEM THERE IS PRESENT */
    public Boolean departmentNameControlDAO(String department) {

        boolean result = true;
        ArrayList<String[]> arrayList;

        try {

            String query = "SELECT * FROM `DEPARTMENT` WHERE `department`='" +department+ "'";
            arrayList = DbConnection.getInstance().db_query(query);
            //|| !arrayList.get(0)[1].equals(department)
            if (arrayList.size() == 0 ) {
                result = false;
            }
            return result;
        } catch (Exception e) { return result; }
    }

    public ArrayList<Department> getAllDepartmentDAO(){

        ArrayList<String[]> arrayList;
        ArrayList<Department> departmentArrayList = new ArrayList<>();

        try {

            String queryAllDepartments = "SELECT * FROM `DEPARTMENT`";
            arrayList = DbConnection.getInstance().db_query(queryAllDepartments);

            int i=0;

            while(i<arrayList.size()){

            Department  department = new Department();

            department.setDepartment(arrayList.get(i)[1]);
            department.setIdDepartment(Integer.parseInt(arrayList.get(i)[0]));
            departmentArrayList.add(department); //crea un array delle classi istanziate con i dipartimenti

            i++;
            }



        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return departmentArrayList;
    }


    public String getDepartmentForItemDAO(int idItem) {

        ArrayList<String[]> department;
        ArrayList<String[]> result;

        try {
            String queryItem ="SELECT ITEM.DEPARTMENT_idDEPARTMENT FROM ITEM WHERE idITEM='"+idItem+"'";

            result = DbConnection.getInstance().db_query(queryItem);

            String queryDepartment = "SELECT `department` FROM `DEPARTMENT` WHERE `idDEPARTMENT`='" +Integer.parseInt(result.get(0)[0])+ "'";

            department = DbConnection.getInstance().db_query(queryDepartment);

        }catch(NullPointerException e){ e.printStackTrace(); return null; }

        return department.get(0)[0];
    }

    public boolean addDepartmentDAO(String department) {

        boolean update = false;

        try {

            String query = "INSERT INTO `dbMagneto`.`DEPARTMENT` (`department`) VALUES ('"+department+"')";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update;}
        return update;
    }

    public boolean editDepartmentDAO(String newDepartment, String oldDepartment) {

        boolean update = false;

        try {

            int idDepartment = getIdFromDepartment(oldDepartment);

            String query = "UPDATE `dbMagneto`.`DEPARTMENT` SET `department`='"+newDepartment+"' WHERE `idDEPARTMENT`='"+idDepartment+"'";
            update = DbConnection.getInstance().db_update(query);

        } catch (Exception e) { return update; }
        return update;
    }

    public boolean deleteDepartmentDAO(String department) {

        boolean update = false;

        try {

            int idDepartment = getIdFromDepartment(department);
            String query = "DELETE FROM `dbMagneto`.`DEPARTMENT` WHERE `idDEPARTMENT`='" + idDepartment + "'";

            ArrayList<Item> itemArrayList = ItemDAO.getInstance().getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(department,0);

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
    public static DepartmentDAO getInstance() {
        if(instance == null)
            instance = new DepartmentDAO();
        return instance;
    }
}

