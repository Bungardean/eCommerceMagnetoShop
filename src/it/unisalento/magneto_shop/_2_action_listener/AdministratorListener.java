package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Administrator_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.ManageOrder_GUI;
import it.unisalento.magneto_shop._3_business.*;
import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorListener implements ActionListener {

    private Administrator_GUI administratorGUI;
    private ManageOrder_GUI manageOrder_gui;



    public AdministratorListener(Administrator_GUI administratorGUI) {
        super();
        this.administratorGUI = administratorGUI; }



        public AdministratorListener(ManageOrder_GUI manageOrder_gui) {
        super();
        this.manageOrder_gui =manageOrder_gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();
        try {

            switch (com) {
                case "Logout":

                    logout();//Richiamo il metodo privato per tornare al catalogo


                    break;
                case "Gestione Utente":

                    MainFrame.getInstance().showManageMemberPage(administratorGUI.getUsername());

                    break;
                case "Gestione Ordine":

                    MainFrame.getInstance().showManageOrderPage(administratorGUI.getUsername());

                    break;
                case "Indietro":

                    MainFrame.getInstance().showAdministratorPage((Administrator) Session.getInstance().mappa.get(manageOrder_gui.getUsername()));

                    break;
                case "Conferma":

                    editOrder();

                    break;
                default:
                    System.out.println("Errore AdministratorListener");
                    break;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void editOrder() {

        String idOrder = manageOrder_gui.getOrderBoxEdit();
        String status = manageOrder_gui.getStatusBox();

        if (controlInfo(idOrder,status)){


            int id_Order = Integer.parseInt(idOrder);
            if (OrderBusiness.getInstance().editStatusOrderBusiness(id_Order,status)) {

               int idMember = OrderBusiness.getInstance().getIdMemberFromIdOrderBusiness(id_Order);

               Member member = CoreSistemBusiness.getInstance().getMemberByIdBusiness(idMember);

                Mailer.send(member.getE_mail(),"Ordine MagnetoShop","Gentile "+member.getName()+" "+member.getSurname()+"\nIl suo ordine e' stato "+status+"!!", id_Order);

                JOptionPane.showMessageDialog(null, "Modifica Aggiunta!!");
                MainFrame.getInstance().showManageOrderPage(manageOrder_gui.getUsername());
            }
        }
    }
    private boolean controlInfo(String idOrder, String status) {

        if (idOrder.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione scegli Id ordine!!");
            return false;
        } if (status.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione scegli nuovo stato!!");
            return false;
        }else return true;

    }
    private void logout(){
        UserBusiness.getInstance().logout();
        MainFrame.getInstance().showHomePageView();
    }


}
