package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Category_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Manager_GUI;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {

    private Category_GUI categoryGUI;

    public CategoryListener(Category_GUI categoryGUI) {
        super();
        this.categoryGUI = categoryGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi":

                    //Richiamo il metodo privato
                    addCategory();

                    break;
                case "Modifica":

                    editCategory();

                    break;
                case "Elimina":

                    deleteCategory();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showManagerPage((Manager) Session.getInstance().mappa.get(Manager_GUI.getUsername()));

                    break;
                default:
                    System.out.println("Errore CategoryListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }

    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void addCategory() {
        String category = categoryGUI.getCategoryTextField().getText();

        if (controlInfo(category)){
            if(CatalogBusiness.getInstance().addCategoryBusiness(category)) {
                JOptionPane.showMessageDialog(null, "Nuova categoria aggiunta!!");
                MainFrame.getInstance().showCategoryPage();

            }
        }
    }
    private void editCategory() {

        String category = categoryGUI.getEditCategoryTextField().getText();
        String oldCategory = categoryGUI.getItemCategoryEdit();

        if (controlInfoMod(category,oldCategory)){
            if (CatalogBusiness.getInstance().editCategoryBussines(category,oldCategory)) {
                JOptionPane.showMessageDialog(null, "Modifica categoria aggiunta!!");
                MainFrame.getInstance().showCategoryPage();
                categoryGUI = Category_GUI.getInstance();
                categoryGUI.showEditSection();

            }
        }
    }
    private void deleteCategory() {

        String category = categoryGUI.getCategoryBoxDel();

        if (!category.isEmpty()){
            if (CatalogBusiness.getInstance().deleteCategoryBusiness(category)){
                JOptionPane.showMessageDialog(null, "Categoria eliminata!!");
                MainFrame.getInstance().showCategoryPage();
                categoryGUI = Category_GUI.getInstance();
                categoryGUI.showDeleteSection();

            }

        }else JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere una categoria!");

    }
    private boolean controlInfo(String category) {

        if (category.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci una categoria!!");
            return false;
        } if (CatalogBusiness.getInstance().categoryNameControlBusiness(category)){
            JOptionPane.showMessageDialog(null, "Categoria gia presente!!");
            return false;
        }else return true;

    }
    private boolean controlInfoMod(String category, String oldCategory) {

        if (oldCategory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere una categoria!");
            return false;
        } if (category.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci nuovo nome!");
            return false;
        }if (category.equals(oldCategory)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire una categoria con nome diverso dal vecchio!");
            return false;
        }if (CatalogBusiness.getInstance().categoryNameControlBusiness(category)){
            JOptionPane.showMessageDialog(null, "Categoria gia presente!!");
            return false;
        }
        else return true;

    }
}
