package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.DepartmentGUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Manager_GUI;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentListener implements ActionListener {
        
    private DepartmentGUI departmentGUI;
    
    public DepartmentListener(DepartmentGUI departmentGUI) {
        super();
        this.departmentGUI = departmentGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi":

                    //Richiamo il metodo privato
                    addDepartment();

                    break;
                case "Modifica":

                    editDepartment();

                    break;
                case "Elimina":

                    deleteDepartment();

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
    private void addDepartment() {

        String department = departmentGUI.getDepartmentTextField().getText();

        if (controlInfo(department)){
            if(CatalogBusiness.getInstance().addDepartmentBusiness(department)) {
                JOptionPane.showMessageDialog(null, "Nuovo reparto aggiunto!!");
                MainFrame.getInstance().showDepartmentView();
            }
        }

    }
    private void editDepartment() {

        String oldDepartment = departmentGUI.getDepartmentEdit();
        String newDepartment = departmentGUI.getEditDepartmentTextField().getText();
        if (controlInfoEdit(oldDepartment,newDepartment)){
            if (CatalogBusiness.getInstance().editDepartmentBusiness(newDepartment,oldDepartment)) {
                JOptionPane.showMessageDialog(null, "Modifica reparto aggiunta!!");
                MainFrame.getInstance().showDepartmentView();
                departmentGUI = DepartmentGUI.getInstance();
                departmentGUI.showEditSection();
            }
        }

    }
    private void deleteDepartment() {

        String department = departmentGUI.getDepartmentBoxDel();

        if (!department.isEmpty()){
            if (CatalogBusiness.getInstance().deleteDepartmentBusiness(department)){

                JOptionPane.showMessageDialog(null, "Reparto eliminato!!");
                MainFrame.getInstance().showDepartmentView();
                departmentGUI = DepartmentGUI.getInstance();
                departmentGUI.showDeleteSection();

            }

        }else JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un reparto!");

    }
    private boolean controlInfo(String department) {

        if (department.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci un reparto!!");
            return false;
        }if (CatalogBusiness.getInstance().departmentNameControlBusiness(department)){
            JOptionPane.showMessageDialog(null, "Reparto gia presente!!");
            return false;
        }else return true;
    }
    private boolean controlInfoEdit(String oldDepartment, String newDepartment) {
        if (oldDepartment.isEmpty()){
            JOptionPane.showMessageDialog(null, "Seleziona un reparto da Modificare!!");
            return false;
        }if (newDepartment.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire nuovo nome!");
            return false;
        }if (newDepartment.equals(oldDepartment)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un reparto con nome diverso dal vecchio!");
            return false;
        }if (CatalogBusiness.getInstance().departmentNameControlBusiness(newDepartment)){
            JOptionPane.showMessageDialog(null, "Reparto gia presente!!");
            return false;
        }
        else return true;

    }
}
