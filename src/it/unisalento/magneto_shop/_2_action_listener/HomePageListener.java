package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.HomePage_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._3_business.EncryptDecryptWhitDES;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._3_business.UserBusiness;
import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.Manager;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePageListener implements ActionListener {

	private HomePage_GUI homePageGUI;

	public HomePageListener(HomePage_GUI homePageGUI) {
		super();
		this.homePageGUI = homePageGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String com = e.getActionCommand();

		try {

			switch (com) {
				case "Login":
				case "Password":

					log(); //Richiamo il metodo privato


					break;
				case "Register":

					reg(); //Richiamo il metodo privato


					break;
				case "Catalogo":

					catalog();

					break;
				default:
					System.out.println("Errore HomePageListener");
					break;
			}
		} catch (Exception e1) { e1.printStackTrace(); }

	}

	/*PRIVATE METHODS FOR EACH COMPONENT*/
	private void log(){

		String userName = homePageGUI.getUsernameTextfield().getText();
		char[] pass = homePageGUI.getPasswordField().getPassword();

		String pwd = EncryptDecryptWhitDES.getInstance().encrypt(new String(pass)); //ENCRYPTION OF PASSWORD

		//VENGONO SETTATE USERNAME E PASSWORD
		//userBusiness.setUserIDandPass_Business(userName,pwd);

		//CONTROLLO CHE I CAMPI USERNAME E PASSWORD NON SIANO VUOTI e che non ci sia gia un altra sessione attiva
		if(!userName.isEmpty() && pass.length != 0) {

			//TENTA IL LOGIN
			UserBusiness userBusiness = UserBusiness.getInstance();
			int companyRole = userBusiness.loginBusiness(userName, pwd);

			if (companyRole == User.MEMBER){

				MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(userName));

			}else if(companyRole == User.MANAGER) {

				MainFrame.getInstance().showManagerPage((Manager) Session.getInstance().mappa.get(userName));

			}else if (companyRole == User.ADMINISTRATOR){

				MainFrame.getInstance().showAdministratorPage((Administrator) Session.getInstance().mappa.get(userName));

			}
			if (companyRole == -2) JOptionPane.showMessageDialog(null, "Caro utente non sei ancora Attivo!");
			if (companyRole == -1) JOptionPane.showMessageDialog(null, "Caro utente non esisti, registrati!");


		}else if (userName.isEmpty()) JOptionPane.showMessageDialog(null, "Inserisci un user !");
		else if(pass.length == 0 )	JOptionPane.showMessageDialog(null, "Inserisci una password !");


	}
	private void catalog(){ MainFrame.getInstance().showCatalog("nonLogato"); }
    private void reg() { MainFrame.getInstance().showRegisterPage(); }

}
