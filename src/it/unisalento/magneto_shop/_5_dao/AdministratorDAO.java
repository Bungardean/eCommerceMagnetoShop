package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._3_business.EncryptDecryptWhitDES;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.User;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;


public class AdministratorDAO {
	
	private static AdministratorDAO instance;
	
	public AdministratorDAO(){ }

	protected int getAdministrator(int idAdmin, String password, String userName){

		String queryAdmin = "SELECT `*` FROM `ADMINISTRATOR` WHERE `idADMINISTRATOR`='" +idAdmin+ "'";

		try {

			ArrayList<String[]> resultAdministrator = DbConnection.getInstance().db_query(queryAdmin);
			String pwd = EncryptDecryptWhitDES.getInstance().encrypt(resultAdministrator.get(0)[2]);

			if (password.equals(pwd) && userName.equals(resultAdministrator.get(0)[1])) {

				Administrator administrator = new Administrator();
				Session.getInstance().mappa.put(userName, administrator);

				administrator.setAdminID(Integer.valueOf(resultAdministrator.get(0)[0]));
				administrator.setUserName(resultAdministrator.get(0)[1]);
				administrator.setPassword(resultAdministrator.get(0)[2]);
				administrator.setName(resultAdministrator.get(0)[3]);
				administrator.setSurname(resultAdministrator.get(0)[4]);
				administrator.setE_mail(resultAdministrator.get(0)[5]);
				administrator.setFoto(DbConnection.getInstance().getBlobData().get(0)); //elemento [6]

				return User.ADMINISTRATOR;
			}
		}catch(NullPointerException e){ e.printStackTrace(); }

		return -1;//Errore
		
		
	}
	
	
	
	/**
	 * Prepara e invia una chiamata a DB per l'inserimento di una
 	 * tupla all'interno della tabella capoProgetto
	 * @param cp il capoProgetto da aggiungere
	 * @return flag di avvenuto inserimento
	 * */
	
	
	public boolean addCapoProgetto(Administrator cp){
		
		boolean inserito=false;
		
		try{

			//String insCP = "INSERT INTO `capoprogetto` (CapoProgettoID, nome, cognome, password, sesso, dataAssunzione, foto, sede_posizioneSede) VALUES ('"+cp.getCPID()+"','"+cp.getNome()+"','"+cp.getCognome()+"','"+cp.getPassword()+"','"+cp.getSesso()+"','"+FormatAndConversion.getInstance().dateDBConversion(cp.getDataAssunzione())+"','"+cp.getFoto()+"','"+cp.getPosizioneSede()+"');";
			
			//inserito = DbConnection.getInstance().db_update(insCP);
			
			return inserito;
		}catch(Exception e){return inserito;}
	}
	//SINGLETON

	public static AdministratorDAO getInstance(){

		if( instance == null)
			instance = new AdministratorDAO();
		return instance;
	}
}
