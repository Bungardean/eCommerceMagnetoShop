package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.ManageItem_GUI;
import it.unisalento.magneto_shop._1_view.Manager_GUI;
import it.unisalento.magneto_shop._3_business.CartBusiness;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.OrderBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ManageItemListener implements ActionListener {

    private ManageItem_GUI manageItemGUI;

    public ManageItemListener(ManageItem_GUI manageItemGUI) {
        super();
        this.manageItemGUI = manageItemGUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi":

                    uploadItem();

                    break;
                case "Modifica":

                    editItem();

                    break;
                case "Elimina":

                    deleteItem();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showManagerPage((Manager) Session.getInstance().mappa.get(Manager_GUI.getUsername()));

                    break;
                case "Browse":

                    browse(1);

                    break;
                case "editBrowse":

                    browse(2);

                    break;
                default:
                    System.out.println("Errore ItemListener");
                    break;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void editItem() {

        boolean update = false;
        String itemToEdit = manageItemGUI.getEditItemSelectionBox();

        String editedItemName = manageItemGUI.getEditItemNameTextField().getText();
        String editItemPrice = manageItemGUI.getEditItemPriceTextField().getText();
        String editItemSalePrice = manageItemGUI.getEditItemSalesPriceTextField().getText();
        String editItemDescription = manageItemGUI.getEditEditorPane();
        String editItemCategory = manageItemGUI.getEditCategoryBox();
        String editItemDepartment = manageItemGUI.getEditDepartmentBox();
        String editItemProducer = manageItemGUI.getEditProducerBox();
        String editItemDealer = manageItemGUI.getEditDealerBox();
        String editItemFather = manageItemGUI.getEditItemBox();
        String editItemPathImg = manageItemGUI.getEditpath();
        String avaiable = manageItemGUI.getEditAvaiableBoxSELECTED();

        if (!itemToEdit.equals("")) {

            int idItemToEdit = CatalogBusiness.getInstance().getIdItemFromNameBusiness(itemToEdit);


            if (!avaiable.isEmpty()) {

                int disponibile = manageItemGUI.getEditAvaiableBox().getSelectedIndex();

                update = CatalogBusiness.getInstance().editItemStatusBusiness(disponibile-1, idItemToEdit);


            }

            if (!editedItemName.isEmpty()) {

                if (!CatalogBusiness.getInstance().itemNameControlBusiness(editedItemName)) {

                    update = CatalogBusiness.getInstance().editItemNameBusiness(editedItemName, idItemToEdit);

                } else JOptionPane.showMessageDialog(null, "Attenzione! Prodotto gia presente con questo nome!!");
            }

            if (!editItemPrice.isEmpty()) {

                float price = Float.parseFloat(editItemPrice);

                update = CatalogBusiness.getInstance().editItemPriceBusiness(price, idItemToEdit);


            }

            if (!editItemSalePrice.isEmpty()) {

                float salePrice = Float.parseFloat(editItemSalePrice);

                update = CatalogBusiness.getInstance().editItemSalePriceBusiness(salePrice, idItemToEdit);


            }
            if (!editItemDescription.isEmpty()) {


                update = CatalogBusiness.getInstance().editItemDescriptionBusiness(editItemDescription, idItemToEdit);


            }
            if (!editItemCategory.isEmpty()) {


                update = CatalogBusiness.getInstance().editItemCategoryBusiness(editItemCategory, idItemToEdit);


            }
            if (!editItemDepartment.isEmpty()) {


                update = CatalogBusiness.getInstance().editItemDepartmentBusiness(editItemDepartment, idItemToEdit);


            }
            if (!editItemProducer.isEmpty()) {


                update = CatalogBusiness.getInstance().editItemProducerBusiness(editItemProducer, idItemToEdit);


            }
            if (!editItemDealer.isEmpty()) {


                update = CatalogBusiness.getInstance().editItemDealerBusiness(editItemDealer, idItemToEdit);


            }
            if (!editItemFather.isEmpty() && !editItemFather.equals("Nessuno") && !editItemFather.equals(itemToEdit)) {


                int editIdItemFather = CatalogBusiness.getInstance().getIdItemFromNameBusiness(editItemFather);

                ArrayList<String> st = CatalogBusiness.getInstance().getIdFatherItemForIdItemBusiness(editIdItemFather);

                for (String aSt : st) {

                    if (itemToEdit.equals(aSt)) {

                        JOptionPane.showMessageDialog(null, "Attenzione non puo essere sottoprodotto di un suo stesso sottoprodotto!!");
                        return;
                    }
                }
                update = CatalogBusiness.getInstance().editItemFatherBusiness(editIdItemFather, idItemToEdit);


            }
            if (!editItemFather.isEmpty() && editItemFather.equals("Nessuno") && !editItemFather.equals(itemToEdit)) {


                update = CatalogBusiness.getInstance().deleteItemFatherBusiness(idItemToEdit);


            }
            if (editItemFather.equals(itemToEdit)) {


                JOptionPane.showMessageDialog(null, "Attenzione non puo essere sottoprodotto di se stesso!!");
                return;
            }
            if (editItemPathImg != null) {


                update = CatalogBusiness.getInstance().editItemPathImgBusiness(editItemPathImg, idItemToEdit);


            }

            if (update) {
                JOptionPane.showMessageDialog(null, "Prodotto modificato!!");
                MainFrame.getInstance().showAddItemView();
                manageItemGUI = ManageItem_GUI.getInstance();
                manageItemGUI.showEditSection();
            }

        }
        else JOptionPane.showMessageDialog(null,"Attenzione scegliere un prodotto da modificare!!");


    }
    private void deleteItem() {

        String itemToDelete = manageItemGUI.getItemBoxDel();

        if (!itemToDelete.isEmpty()){

            if (!CartBusiness.getInstance().controlPresenceOfItemInCartBusiness(itemToDelete) && !OrderBusiness.getInstance().controlPresenceOfItemInOrderBusiness(itemToDelete)){

                if (CatalogBusiness.getInstance().deleteItemBusiness(itemToDelete)){

                    JOptionPane.showMessageDialog(null, "Prodotto eliminato!!");
                    MainFrame.getInstance().showAddItemView();
                    manageItemGUI = ManageItem_GUI.getInstance();
                    manageItemGUI.showDeleteSection();
                }
            }else JOptionPane.showMessageDialog(null, "Attenzione il prodotto non si puo elliminare!!");


    }else JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un prodotto!");
    }
    private void uploadItem() {

        String itemName = manageItemGUI.getNewItemNameTextField().getText();
        String itemPrice = manageItemGUI.getNewItemPriceTextField().getText();
        String itemSalePrice = manageItemGUI.getNewItemSalesPriceTextField().getText();
        String itemDescription = manageItemGUI.getItemDescription();
        String itemCategory = manageItemGUI.getItemCategory();
        String itemDepartment = manageItemGUI.getItemDepartment();
        String itemProducer = manageItemGUI.getItemProducer();
        String itemDealer = manageItemGUI.getItemDealer();
        String itemFather = manageItemGUI.getItemOfItem();
        String itemPathImg = manageItemGUI.getPath();
        String avaiable = manageItemGUI.getNewAvaiableBox();


        if (controlInfo(itemName,itemPrice,itemSalePrice,itemDescription,itemCategory,itemDepartment,itemProducer,itemDealer,itemFather,itemPathImg,avaiable)){

            float price = Float.parseFloat(itemPrice);
            float salePrice = Float.parseFloat(itemSalePrice);
            int disponibile = manageItemGUI.getAvaiableBox().getSelectedIndex();

            if (CatalogBusiness.getInstance().uploadItemBusiness(itemName,price,salePrice,itemDescription,itemCategory,itemDepartment,itemProducer,itemDealer,itemFather,itemPathImg,disponibile-1)) {
                JOptionPane.showMessageDialog(null, "Nuovo prodotto e' stato aggiunto!!");
                MainFrame.getInstance().showAddItemView();
            }


        }

    }
    private boolean controlInfo(String itemName, String itemPrice, String itemSalePrice, String itemDescription, String itemCategory, String itemDepartment, String itemProducer, String itemDealer, String itemOfItem, String itemPathImg, String avaiable) {
        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un nome per il prodotto!");
            return false;
        }
        if (itemPrice.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un prezzo!");
            return false;

        }
        if (itemSalePrice.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un sconto!");
            return false;
        }
        if (itemDescription.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire una descrizione!");
            return false;

        }
        if (itemCategory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere una categoria!");
            return false;
        }
        if (itemDepartment.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un reparto!");
            return false;
        }
        if (itemProducer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un produttore!");
            return false;
        }if (itemDealer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un rivenditore!");
            return false;
        }if(itemPathImg == null){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire una foto png !");
            return false;
        }if(itemPathImg != null){

            File f = new File(itemPathImg);
            if(f.length()>240000){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire una foto png con dimensioni minori di 240Kbyte !");
            return false;
            }
        }if (itemOfItem.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un sotto prodotto!");
            return false;
        }
        if (avaiable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere se disponibile!");
            return false;
        }
        if (CatalogBusiness.getInstance().itemNameControlBusiness(itemName)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Prodotto gia presente!!");
            return false;
        }
        else return true;
    }
    private void browse( int val) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.PNG", "png");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(fileChooser);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            if (val == 1){ manageItemGUI.setPath(path);}
            else if(val == 2){ manageItemGUI.setEditpath(path);}

        }
        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No Data");
        }
    }

}
