package it.unisalento.magneto_shop._3_business;

import it.unisalento.magneto_shop._1_view.Catalog_GUI;
import it.unisalento.magneto_shop._4_model.*;

import java.util.ArrayList;

public class CatalogBusiness {

    private static CatalogBusiness instance;

    public CatalogBusiness() { }

    /* ITEM BUSINESS SECTION */
    public int getIdItemFromNameBusiness(String itemName){
        return Item.getIdItemFromNameModel(itemName);
    }
    public Item getItemFromArrayOfItem(String itemName){

        Item item = null;
        ArrayList<Item> itemArrayList = Catalog_GUI.getItemArrayList();

        for (Item anItemArrayList : itemArrayList) {
            if (itemName.equals(anItemArrayList.getItemName())) {
                item = anItemArrayList;
            }
        }
        return item;
    }
    public ArrayList<Item> getItemListByCategoryByDepartmentBusiness(String category , int avaiable){ return Item.getItemListByCategoryByDepartmentByPriceByProducerByDealerModel(category, avaiable); }
    public ArrayList<Item> getAllItemListBusiness(int avaiable){ return Item.getItemListByCategoryByDepartmentByPriceByProducerByDealerModel(null,avaiable); }
    public ArrayList<Item> getItemListByRangePriceBusiness(int min, int limit,int avaiable) {

        ArrayList<Item> itemArray = new ArrayList<Item>();
        ArrayList<Item> itemArrayList = Item.getItemListByCategoryByDepartmentByPriceByProducerByDealerModel(null,avaiable);


        for (Item anItemArrayList : itemArrayList) {

            float price = anItemArrayList.getPrice();
            float sale = anItemArrayList.getSales();
            if (price>sale){price=sale;}

            if (price < limit && price > min) {
                itemArray.add(anItemArrayList);
            }
        }
        return itemArray;
    }
    public ArrayList<Item> getItemListBusinessByPrice(String sorting,int avaiable){

        Item item ;
        ArrayList<Item> itemArrayList = Item.getItemListByCategoryByDepartmentByPriceByProducerByDealerModel(null,avaiable);


            for (int i = 0; i < (itemArrayList.size() - 1); i++) {
                int minimo = i; //Partiamo dall' i-esimo elemento
                for (int j = i + 1; j < itemArrayList.size(); j++) {

                    //Qui avviene la selezione, ogni volta
                    //che nell' iterazione troviamo un elemento piú piccolo di minimo
                    //facciamo puntare minimo all' elemento trovato
                    if (sorting.equals("Basso")) {
                        if (itemArrayList.get(minimo).getPrice() > itemArrayList.get(j).getPrice()) {
                            minimo = j;
                        }
                    }
                    //Qui avviene la selezione, ogni volta
                    //che nell' iterazione troviamo un elemento piú Grande di minimo
                    //facciamo puntare minimo all' elemento trovato
                    if (sorting.equals("Alto")) {
                        if (itemArrayList.get(minimo).getPrice() < itemArrayList.get(j).getPrice()) {
                            minimo = j;
                        }
                    }
                }

                //Se minimo e diverso dall' elemento di partenza
                //allora avviene lo scambio
                if (minimo != i) {

                    item = itemArrayList.get(minimo);
                    itemArrayList.set(minimo, itemArrayList.get(i));
                    itemArrayList.set(i, item);

                }
            }

        return itemArrayList;

    }
    public ArrayList<String> getIdFatherItemForIdItemBusiness(int idItem){ return Item.getIdFatherItemForIdItemModel(idItem); }

    public boolean itemNameControlBusiness(String userName){

        if (!Item.itemNameControlModel(userName)) { return false; }
        else return true;
    }
    public boolean uploadItemBusiness(String itemName, float itemPrice, float itemSalePrice, String itemDescription, String itemCategory, String itemDepartment, String itemProducer, String itemDealer, String itemFather, String itemPathImg, int avaiable) {

        boolean value = false;

        if (itemFather.equals("Nessuno")) {
            value = Item.uploadItemModel(itemName,itemPrice,itemSalePrice,itemDescription,itemCategory,itemDepartment,itemProducer,itemDealer,0,itemPathImg,avaiable);
        }else {
            int idItemFather =  getIdItemFromNameBusiness(itemFather);
            value = Item.uploadItemModel(itemName,itemPrice,itemSalePrice,itemDescription,itemCategory,itemDepartment,itemProducer,itemDealer,idItemFather,itemPathImg,avaiable);
        }

        return value;
    }
    public boolean deleteItemBusiness(String itemToDelete) { return Item.deleteItemModel(getIdItemFromNameBusiness(itemToDelete)); }
    public boolean deleteItemFatherBusiness(int idItemToEdit) { return Item.deleteItemFatherModel(idItemToEdit); }


    public boolean editItemNameBusiness(String editedItemName, int idItemToEdit) { return Item.editItemNameModel(editedItemName,idItemToEdit); }
    public boolean editItemPriceBusiness(float price, int idItemToEdit) { return Item.editItemPriceModel(price,idItemToEdit); }
    public boolean editItemSalePriceBusiness(float salePrice, int idItemToEdit) { return Item.editItemSalePriceModel(salePrice,idItemToEdit); }
    public boolean editItemDescriptionBusiness(String editItemDescription, int idItemToEdit) { return Item.editItemDescriptionModel(editItemDescription,idItemToEdit); }
    public boolean editItemCategoryBusiness(String editItemCategory, int idItemToEdit) { return Item.editItemCategoryModel(editItemCategory,idItemToEdit); }
    public boolean editItemDepartmentBusiness(String editItemDepartment, int idItemToEdit) { return Item.editItemDepartmentModel(editItemDepartment,idItemToEdit); }
    public boolean editItemProducerBusiness(String editItemProducer, int idItemToEdit) { return Item.editItemProducerModel(editItemProducer,idItemToEdit); }
    public boolean editItemDealerBusiness(String editItemDealer, int idItemToEdit) { return Item.editItemDealerModel(editItemDealer,idItemToEdit); }
    public boolean editItemFatherBusiness(int editIdItemFather, int idItemToEdit) { return Item.editItemFatherModel(editIdItemFather,idItemToEdit); }
    public boolean editItemPathImgBusiness(String editItemPathImg, int idItemToEdit) { return Item.editItemPathImgModel(editItemPathImg,idItemToEdit); }
    public boolean editItemStatusBusiness(int disponibile, int idItemToEdit) {

        return Item.editItemStatusModel(disponibile,idItemToEdit);
    }



    /* CATEGORY BUSINESS SECTION */
    public ArrayList<Category> getAllCategoryBusiness(){ return  Category.getAllCategories(); }
    public String getCategoryForItemB(int idItem) { return Category.getCategoryForItemM(idItem); }
    public boolean addCategoryBusiness(String category){

        return Category.addCategoryModel(category);
    }
    public boolean editCategoryBussines(String category, String oldCategory) { return Category.editCategoryModel(category,oldCategory); }
    public boolean deleteCategoryBusiness(String category) { return Category.deleteCategoryModel(category); }
    public boolean categoryNameControlBusiness(String category) {
        if (!Category.categoryNameControlModel(category)) { return false; }
        else return true; }

    /* DEPARTMENT BUSINESS SECTION */
    public ArrayList<Department> getAllDepartmentsBusiness(){ return Department.getAllDepartments(); }
    public String getDepartmentForItemB(int idItem) { return Department.getDepartmentForItemM(idItem); }
    public boolean addDepartmentBusiness(String department) { return Department.addDepartmentModel(department); }
    public boolean editDepartmentBusiness(String newDepartment, String oldDepartment) { return Department.editDepartmentModel(newDepartment,oldDepartment); }
    public boolean deleteDepartmentBusiness(String department) { return Department.deleteDepartmentModel(department); }
    public boolean departmentNameControlBusiness(String newDepartment) {
        if (!Department.departmentNameControlModel(newDepartment)) { return false; }
        else return true;
    }

    /* PRODUCER BUSINESS SECTION */
    public ArrayList<Producer> getAllProducers(){ return  Producer.getAllproducers(); }
    public boolean addProducerBusiness(String newProducer) { return Producer.addProducerModel(newProducer); }
    public boolean editProducerBusiness(String newProducer, String oldProducer) { return Producer.editProducerModel(newProducer,oldProducer); }
    public boolean deleteProducerBusiness(String producer) { return Producer.deleteProducerModel(producer); }
    public boolean producerNameControlBusiness(String producer) { if (!Producer.producerNameControlModel(producer)) { return false; } else return true; }

    /* DEALER BUSINESS SECTION */
    public ArrayList<Dealer> getAllDealers(){ return  Dealer.getAllDealers(); }
    public boolean addDealerBusiness(String newDealer) { return Dealer.addDealerModel(newDealer); }
    public boolean editDealerBusiness(String newDealer, String oldDealer) { return Dealer.editDealerModel(newDealer,oldDealer); }
    public boolean deleteDealerBusiness(String dealerBoxDel) { return Dealer.deleteDealerModel(dealerBoxDel); }
    public boolean dealerNameControlBusiness(String dealer) { return Dealer.dealerNameControl(dealer); }

    //SINGLETON
    public static CatalogBusiness getInstance(){

        if( instance == null )
            instance = new CatalogBusiness();
        return instance;
    }
}
