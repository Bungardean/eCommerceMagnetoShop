package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.ManageMember_GUI;
import it.unisalento.magneto_shop._3_business.CoreSistemBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageMemberListener implements ActionListener{

    private ManageMember_GUI manageMemberGUI;

    public ManageMemberListener(ManageMember_GUI manageMemberGUI) {
        super();
        this.manageMemberGUI = manageMemberGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Attiva":

                    //Richiamo il metodo privato
                    enableMember();

                    break;
                case "Disattiva":

                    disableMember();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showAdministratorPage((Administrator) Session.getInstance().mappa.get(manageMemberGUI.getUsername()));

                    break;
                default:
                    System.out.println("Errore ManageMemberListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void disableMember() {

        String member = manageMemberGUI.getMemberBox();

        if (!member.isEmpty()) {

            int idMember = 0;
            int status = -1;

            ArrayList<Member> firstArrayList =manageMemberGUI.getMemberArrayListNotActive();
            ArrayList<Member> secondArrayList =manageMemberGUI.getMemberArrayListActive();

            ArrayList<Member> combined = new ArrayList<Member>();
            combined.addAll(firstArrayList);
            combined.addAll(secondArrayList);

            for (Member anArrayList : combined) {
                if (anArrayList.getUserName().equals(member)) {
                    idMember = anArrayList.getMemberID();
                    status = anArrayList.getRegistrationStatus();

                }
            }
            if (status==1){

                if (CoreSistemBusiness.getInstance().disableMemberBusiness(idMember)){

                    JOptionPane.showMessageDialog(null, "Utente "+member+" disattivato!!");
                    MainFrame.getInstance().showManageMemberPage(manageMemberGUI.getUsername());
                }
            }else JOptionPane.showMessageDialog(null, "Attenzione utente gia disattivo!!");

        }else JOptionPane.showMessageDialog(null, "Attenzione bisogna selezionare un utente!!");

    }
    private void enableMember() {
        String member = manageMemberGUI.getMemberBox();

        if (!member.isEmpty()) {

            int idMember = 0;
            int status = -1;
            ArrayList<Member> firstArrayList =manageMemberGUI.getMemberArrayListNotActive();
            ArrayList<Member> secondArrayList =manageMemberGUI.getMemberArrayListActive();

            ArrayList<Member> combined = new ArrayList<Member>();
            combined.addAll(firstArrayList);
            combined.addAll(secondArrayList);

            for (Member anArrayList : combined) {
                if (anArrayList.getUserName().equals(member)) {
                    idMember = anArrayList.getMemberID();
                    status = anArrayList.getRegistrationStatus();
                }
            }
            if (status==0){

                if (CoreSistemBusiness.getInstance().enableMemberBusiness(idMember)){

                    JOptionPane.showMessageDialog(null, "Utente "+member+" attivato!!");
                    MainFrame.getInstance().showManageMemberPage(manageMemberGUI.getUsername());

                }
            }else JOptionPane.showMessageDialog(null, "Attenzione utente gia attivo!!");
        }else JOptionPane.showMessageDialog(null, "Attenzione bisogna selezionare un utente!!");
    }
}
