package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Dealer_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Manager_GUI;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealerListener implements ActionListener{

    private Dealer_GUI dealerGUI;

    public DealerListener(Dealer_GUI dealerGUI) {
        super();
        this.dealerGUI = dealerGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi":

                    //Richiamo il metodo privato
                    addDealer();

                    break;
                case "Modifica":

                    editDealer();

                    break;
                case "Elimina":

                    deleteDealer();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showManagerPage((Manager) Session.getInstance().mappa.get(Manager_GUI.getUsername()));

                    break;
                default:
                    System.out.println("Errore DealerListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }

    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void addDealer() {
        String newDealer = dealerGUI.getNewDealerTextField().getText();

        if (controlInfo(newDealer)){
            if(CatalogBusiness.getInstance().addDealerBusiness(newDealer)) {
                JOptionPane.showMessageDialog(null, "Nuovo distributore aggiunto!!");
                MainFrame.getInstance().showDealerGUI();
            }
        }
    }
    private void editDealer() {

        String newDealer = dealerGUI.getEditDealerTextField().getText();
        String oldDealer = dealerGUI.getDealerBoxEdit();

        if (controlInfoMod(newDealer,oldDealer)){
            if (CatalogBusiness.getInstance().editDealerBusiness(newDealer,oldDealer)) {
                JOptionPane.showMessageDialog(null, "Modifica distributore aggiunta!!");
                MainFrame.getInstance().showDealerGUI();
                dealerGUI = Dealer_GUI.getInstance();
                dealerGUI.showEditSection();
            }
        }
    }
    private void deleteDealer() {

        String dealerBoxDel = dealerGUI.getDealerBoxDel();

        if (!dealerBoxDel.isEmpty()){
            if (CatalogBusiness.getInstance().deleteDealerBusiness(dealerBoxDel)){
                JOptionPane.showMessageDialog(null, "Distributore eliminato!!");
                MainFrame.getInstance().showDealerGUI();
                dealerGUI = Dealer_GUI.getInstance();
                dealerGUI.showDeleteSection();
            }

        }else JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un distributore!");

    }
    private boolean controlInfo(String dealer) {

        if (dealer.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci un distributore!!");
            return false;
        } if (CatalogBusiness.getInstance().dealerNameControlBusiness(dealer)){
            JOptionPane.showMessageDialog(null, "Distributore gia presente!!");
            return false;
        }else return true;

    }
    private boolean controlInfoMod(String newDealer, String oldDealer) {

        if (oldDealer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un distributore da modificare!");
            return false;
        }if (newDealer.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci nuovo nome!!");
            return false;
        }if (newDealer.equals(oldDealer)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un distributore con nome diverso dal vecchio!");
            return false;
        }if (CatalogBusiness.getInstance().dealerNameControlBusiness(newDealer)){
            JOptionPane.showMessageDialog(null, "Distributore gia presente!!");
            return false;
        }
        else return true;

    }
}
