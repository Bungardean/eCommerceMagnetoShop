package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Catalog_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Category;
import it.unisalento.magneto_shop._4_model.Department;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CatalogListener implements ActionListener, MouseListener {

        private Catalog_GUI catalogGUI;

        public CatalogListener(Catalog_GUI catalogGUI) {
            super();
            this.catalogGUI = catalogGUI;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            String com = e.getActionCommand();
            ArrayList<Category> categList = catalogGUI.getCategList();
            ArrayList<Department> departmentList = catalogGUI.getDepartmentList();

            try {


                for (Category aCategList : categList) {
                    if (aCategList.getCategoryName().equals(com)) {
                        catalogGUI.showItemList(com);
                        return;

                    }
                }
                for (Department aDepartmentList : departmentList) {
                    if (aDepartmentList.getDepartment().equals(com)) {
                        catalogGUI.showItemList(com);
                        return;
                    }
                }
                if (com.equals("IndietroMember")) {

                    //Richiamo il metodo privato
                    MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(catalogGUI.getUsername()));

                }else if (com.equals("<-")) {

                    //Richiamo il metodo privato
                    returnToHomepage();

                }else if (com.equals("Alto") || com.equals("Basso")|| com.equals("0 - 50 EUR")|| com.equals("50 - 100 EUR")|| com.equals("100 - 200 EUR")|| com.equals("200 - 500 EUR")|| com.equals("Piu di 500 EUR")){

                    catalogGUI.showItemList(com);

                }
                else { System.out.println("Errore CatalogListener"); }
            } catch (Exception e1) { e1.printStackTrace(); }

        }


    /*PRIVATE METHODS FOR EACH COMPONENT*/

    private void returnToHomepage(){ MainFrame.getInstance().showHomePageView(); }

    @Override
    public void mouseClicked(MouseEvent e) {

            try {
                if (e.getClickCount() == 2) {

                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String com = target.getValueAt(row, 1).toString();
                  //  JOptionPane.showMessageDialog(null,"stai visualizzando il prodotto  "+com+"!");
                    MainFrame.getInstance().showItemPage(CatalogBusiness.getInstance().getItemFromArrayOfItem(com), catalogGUI.getUsername());

                }
            }catch (Exception e1 ) { e1.printStackTrace(); }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
