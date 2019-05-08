package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Registration_GUI;
import it.unisalento.magneto_shop._3_business.CoreSistemBusiness;
import it.unisalento.magneto_shop._3_business.EncryptDecryptWhitDES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationListener implements ActionListener {

    private Registration_GUI registrationGUI;

    public RegistrationListener(Registration_GUI registrationGUI) {
        super();
        this.registrationGUI = registrationGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Conferma":

                    userRegistration();//Richiamo il metodo privato


                    break;
                case "Indietro":

                    cancel();//Richiamo il metodo privato


                    break;
                default:
                    System.out.println("Errore RegistrationListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }

    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void userRegistration(){

        String givenNames = registrationGUI.getGivenNamesTextField().getText();
        String surname = registrationGUI.getSurnameTextField().getText();
        String address = registrationGUI.getAddressTextField().getText();
        String phone = registrationGUI.getPhoneTextField().getText();
        String email = registrationGUI.getEmailTextField().getText();
        String userName = registrationGUI.getUserNameTextField().getText();
        String password1 = registrationGUI.getPasswordTextField1().getText();
        String password2 = registrationGUI.getPasswordTextField2().getText();


        if (infoControl(givenNames,surname,address,email,userName,password1,password2,phone)){

            String pwd = EncryptDecryptWhitDES.getInstance().encrypt(password2);

            if(CoreSistemBusiness.getInstance().memberRegistration(userName,pwd,givenNames,surname,email,address,phone)){
                JOptionPane.showMessageDialog(null,"Caro "+userName+" sei stato registrato, devi attendere solo l'abilitazione!");
                MainFrame.getInstance().showHomePageView();
            }

        }

    }

    private Boolean infoControl(String givenNames, String surname, String address, String email, String userName, String password1, String password2, String phone){

        if (givenNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un nome!");
            return false;
        }
        if (surname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un cognome!");
            return false;

        }if (address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un indirizzo!");
            return false;
        }if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un numero di telefono!");
            return false;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un indirizzo email!");
            return false;
        }
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un username!");
            return false;

        }
        if (password1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire una password!");
            return false;

        }
        if (password2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga reinserire la password!");
            return false;

        }
        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Le due password non sono uguali!");
            return false;
        }
        if (CoreSistemBusiness.getInstance().userNameControlBussines(userName)) {
            JOptionPane.showMessageDialog(null, "Attenzione! Username gia presente!!");
            return false;

        }
        else return true;
    }
    private void cancel(){

        MainFrame.getInstance().showHomePageView();

    }

}
