package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.ItemDAO;

import java.util.ArrayList;

public class Item extends Producer{

    private static final int NON_DISPONIBILE= 0;
    private static final int DISPONIBILE= 1;


    private int idItem;
    private int idItemFather;

    private String itemNameFather;
    private String itemName;
    private String descriptionItem;
    private float price;
    private float sales;
    private byte[] fotoItem;

    /* CONSTRUCTOR OF ITEM CLASS */
    public Item() { }


    /* METHOD OF ITEM CLASS */

    //UPLOAD ITEM SECTION STATICS METHODS
    public static boolean uploadItemModel(String itemName, float itemPrice, float itemSalePrice, String itemDescription, String itemCategory, String itemDepartment, String itemProducer, String itemDealer,int idItemFather ,String itemPathImg, int avaiable) { return ItemDAO.getInstance().uploadItemDAO(itemName,itemPrice,itemSalePrice,itemDescription,itemCategory,itemDepartment,itemProducer,itemDealer,idItemFather,itemPathImg,avaiable); }

    //EDIT ITEM SECTION STATICS METHODS
    public static boolean editItemNameModel(String editedItemName, int idItemToEdit) { return ItemDAO.getInstance().editItemNameDAO(editedItemName,idItemToEdit); }
    public static boolean editItemPriceModel(float price, int idItemToEdit) { return ItemDAO.getInstance().editItemPriceDAO(price,idItemToEdit); }
    public static boolean editItemSalePriceModel(float salePrice, int idItemToEdit) { return ItemDAO.getInstance().editItemSalePriceDAO(salePrice,idItemToEdit); }
    public static boolean editItemDescriptionModel(String editItemDescription, int idItemToEdit) { return ItemDAO.getInstance().editItemDescriptionDAO(editItemDescription,idItemToEdit); }
    public static boolean editItemCategoryModel(String editItemCategory, int idItemToEdit) { return ItemDAO.getInstance().editItemCategoryDAO(editItemCategory,idItemToEdit); }
    public static boolean editItemDepartmentModel(String editItemDepartment, int idItemToEdit) { return ItemDAO.getInstance().editItemDepartmentDAO(editItemDepartment,idItemToEdit); }
    public static boolean editItemProducerModel(String editItemProducer, int idItemToEdit) { return ItemDAO.getInstance().editItemProducerDAO(editItemProducer,idItemToEdit); }
    public static boolean editItemDealerModel(String editItemDealer, int idItemToEdit) { return ItemDAO.getInstance().editItemDealerDAO(editItemDealer,idItemToEdit); }
    public static boolean editItemFatherModel(int editIdItemFather, int idItemToEdit) { return ItemDAO.getInstance().editItemFatherDAO(editIdItemFather,idItemToEdit); }
    public static boolean editItemPathImgModel(String editItemPathImg, int idItemToEdit) { return ItemDAO.getInstance().editItemPathImgDAO(editItemPathImg,idItemToEdit); }
    public static boolean editItemStatusModel(int disponibile, int idItemToEdit) { return ItemDAO.getInstance().editItemStatusDAO(disponibile,idItemToEdit); }

    //DELETE ITEM SECTION STATICS METHODS
    public static boolean deleteItemModel(int itemToDelete) { return ItemDAO.getInstance().deleteItemDAO(itemToDelete); }
    public static boolean deleteItemFatherModel(int idItemToEdit) { return ItemDAO.getInstance().deleteItemFatherDAO(idItemToEdit); }

    //Lista di item presenti nel db
    public static ArrayList<Item> getItemListByIdOrderModel(int idOrder) { return  ItemDAO.getInstance().getItemListByIdOrderDAO(idOrder); }
    public static ArrayList<Item> getItemListByIdCartModel(int idCart) { return ItemDAO.getInstance().getItemListByIdCartDAO(idCart); }
    public static ArrayList<Item> getItemListByCategoryByDepartmentByPriceByProducerByDealerModel(String byCategoryByDepartmentByPrice , int avaiable){ return ItemDAO.getInstance().getItemListByCategoryByDepartmentByPriceByProducerByDealerDAO(byCategoryByDepartmentByPrice,avaiable); }
    public static boolean itemNameControlModel(String itemName){

        if (!ItemDAO.getInstance().itemNameControlDB(itemName)){ return false; } //THERE IS NOT PRESENT
        else { return true; } //THERE IS PRESENT
    }
    public static int getIdItemFromNameModel(String itemName) { return ItemDAO.getInstance().getIdItemFromNameDAO(itemName); }
    public static ArrayList<String> getIdFatherItemForIdItemModel(int idItem){ return ItemDAO.getInstance().getIdFatherItemForIdItemDAO(idItem); }

    /* GETTER AND SETTER OF ITEM CLASS */

    //GETTERS
    public int getIdItem() { return idItem; }
    public String getItemName() { return itemName; }
    public String getDescriptionItem() { return descriptionItem; }
    public float getPrice() { return price; }
    public float getSales() { return sales; }
    public byte[] getFotoItem() { return fotoItem; }
    public int getIdItemFather() { return idItemFather; }
    public String getItemNameFather() { return itemNameFather; }

    //SETTERS
    public void setItemNameFather(String itemNameFather) { this.itemNameFather = itemNameFather; }
    public void setIdItemFather(int idItemFather) { this.idItemFather = idItemFather; }
    public void setIdItem(int idItem) { this.idItem = idItem; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setDescriptionItem(String descriptionItem) { this.descriptionItem = descriptionItem; }
    public void setPrice(float price) { this.price = price; }
    public void setSales(float sales) { this.sales = sales; }
    public void setFotoItem(byte[] fotoItem) { this.fotoItem = fotoItem; }

}
