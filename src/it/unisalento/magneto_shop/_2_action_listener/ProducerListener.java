package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Manager_GUI;
import it.unisalento.magneto_shop._1_view.Producer_GUI;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProducerListener implements ActionListener {

    private Producer_GUI producerGUI;

    public ProducerListener(Producer_GUI producerGUI) {
        super();
        this.producerGUI = producerGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi":

                    //Richiamo il metodo privato
                    addProducer();

                    break;
                case "Modifica":

                    editProducer();

                    break;
                case "Elimina":

                    deleteProducer();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showManagerPage((Manager) Session.getInstance().mappa.get(Manager_GUI.getUsername()));

                    break;
                default:
                    System.out.println("Errore ProducerListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }

    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void addProducer() {
        String newProducer = producerGUI.getNewProducerTextField().getText();

        if (controlInfo(newProducer)){
            if(CatalogBusiness.getInstance().addProducerBusiness(newProducer)) {
                JOptionPane.showMessageDialog(null, "Nuovo produttore aggiunto!!");
                MainFrame.getInstance().showProducerGUI();
            }
        }
    }
    private void editProducer() {

        String newProducer = producerGUI.getEditProducerTextField().getText();
        String oldProducer = producerGUI.getProducerBoxEdit();

        if (controlInfoMod(newProducer,oldProducer)){
            if (CatalogBusiness.getInstance().editProducerBusiness(newProducer,oldProducer)) {
                JOptionPane.showMessageDialog(null, "Modifica produttore aggiunta!!");
                MainFrame.getInstance().showProducerGUI();
                producerGUI = Producer_GUI.getInstance();
                producerGUI.showEditSection();
            }
        }
    }
    private void deleteProducer() {

        String producer = producerGUI.getProducerBoxDel();

        if (!producer.isEmpty()){
            if (CatalogBusiness.getInstance().deleteProducerBusiness(producer)){
                JOptionPane.showMessageDialog(null, "Produttore eliminato!!");
                MainFrame.getInstance().showProducerGUI();
                producerGUI = Producer_GUI.getInstance();
                producerGUI.showDeleteSection();

            }

        }else JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un produttore!");

    }
    private boolean controlInfo(String producer) {

        if (producer.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci un produttore!!");
            return false;
        } if (CatalogBusiness.getInstance().producerNameControlBusiness(producer)){
            JOptionPane.showMessageDialog(null, "Produttore gia presente!!");
            return false;
        }else return true;

    }
    private boolean controlInfoMod(String newProducer, String oldProducer) {

        if (oldProducer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un produttore da modificare!");
            return false;
        }if (newProducer.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inserisci nuovo nome!!");
            return false;
        }if (newProducer.equals(oldProducer)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un produttore con nome diverso dal vecchio!");
            return false;
        }if (CatalogBusiness.getInstance().producerNameControlBusiness(newProducer)){
            JOptionPane.showMessageDialog(null, "Produttore gia presente!!");
            return false;
        }
        else return true;

    }
}
