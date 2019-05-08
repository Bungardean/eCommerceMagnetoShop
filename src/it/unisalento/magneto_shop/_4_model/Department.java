package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.DepartmentDAO;

import java.util.ArrayList;

public class Department {

    private int idDepartment;
    private String department;

    /* CONSTRUCTOR OF DEPARTMENT CLASS */
    public Department() { }


    /* METHOD OF DEPARTMENT CLASS */
    public static ArrayList<Department> getAllDepartments(){ return DepartmentDAO.getInstance().getAllDepartmentDAO(); }
    public static String getDepartmentForItemM(int idItem) { return DepartmentDAO.getInstance().getDepartmentForItemDAO(idItem); }
    public static boolean addDepartmentModel(String department) { return DepartmentDAO.getInstance().addDepartmentDAO(department); }
    public static boolean editDepartmentModel(String newDepartment, String oldDepartment) { return DepartmentDAO.getInstance().editDepartmentDAO(newDepartment,oldDepartment); }
    public static boolean deleteDepartmentModel(String department) { return DepartmentDAO.getInstance().deleteDepartmentDAO(department); }
    public static boolean departmentNameControlModel(String newDepartment) { return DepartmentDAO.getInstance().departmentNameControlDAO(newDepartment); }

    /* GETTER AND SETTER OF DEPARTMENT CLASS */

    //GETTERS
    public int getIdDepartment() { return idDepartment; }
    public String getDepartment() { return department; }

    //SETTERS
    public void setIdDepartment(int idDepartment) { this.idDepartment = idDepartment; }
    public void setDepartment(String department) { this.department = department; }


}
