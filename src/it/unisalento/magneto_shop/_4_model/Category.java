package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.CategoryDAO;

import java.util.ArrayList;

public class Category {

    private String categoryName;
    private int idCategory;

    /* CONSTRUCTOR OF CATEGORY CLASS */
    public Category() { }

    /* METHOD OF CATEGORY CLASS */
    public static ArrayList<Category> getAllCategories(){ return CategoryDAO.getInstance().getAllCategoriesDAO(); }
    public static String getCategoryForItemM(int idItem) { return CategoryDAO.getInstance().getCategoryForItemDAO(idItem); }
    public static boolean addCategoryModel(String category) { return CategoryDAO.getInstance().addCategoryDAO(category); }
    public static boolean editCategoryModel(String category, String oldCategory) { return CategoryDAO.getInstance().editCategoryDAO(category,oldCategory); }
    public static boolean deleteCategoryModel(String category) { return CategoryDAO.getInstance().deleteCategoryDAO(category); }
    public static boolean categoryNameControlModel(String category) { return CategoryDAO.getInstance().categoryNameControlDAO(category); }

    /* GETTER AND SETTER OF CATEGORY CLASS */

    //GETTERS
    public String getCategoryName() { return categoryName; }
    public int getIdCategory() { return idCategory; }

    //SETTERS
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }

}

