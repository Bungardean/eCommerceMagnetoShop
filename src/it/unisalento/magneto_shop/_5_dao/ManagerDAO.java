package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._3_business.EncryptDecryptWhitDES;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Manager;
import it.unisalento.magneto_shop._4_model.User;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;


public class ManagerDAO {
	
	private static ManagerDAO instance;
	
	public ManagerDAO(){ }


	protected int getManager(int idManager, String password, String userName) {

		String queryMember = "SELECT `*` FROM `MANAGER` WHERE `idMANAGER`='" +idManager+ "'";

		try {

			ArrayList<String[]> resultManager = DbConnection.getInstance().db_query(queryMember);
            String pwd = EncryptDecryptWhitDES.getInstance().encrypt(resultManager.get(0)[2]);

            if (password.equals(pwd) && userName.equals(resultManager.get(0)[1])) {

                Manager manager = new Manager();
                Session.getInstance().mappa.put(userName, manager);

				manager.setManagerID(Integer.valueOf(resultManager.get(0)[0]));
				manager.setUserName(resultManager.get(0)[1]);
				manager.setPassword(resultManager.get(0)[2]);
				manager.setName(resultManager.get(0)[3]);
				manager.setSurname(resultManager.get(0)[4]);
				manager.setE_mail(resultManager.get(0)[5]);
				manager.setFoto(DbConnection.getInstance().getBlobData().get(0)); //elemento [6]

				return User.MANAGER;
			}
		}catch(NullPointerException e){ e.printStackTrace(); }

		return -1;//Errore
	}

	/**
	 * Prepara e invia una chiamata a DB per l'inserimento di una nuova tupla all'interno della tabella magazziniere
	 * @param mag il magazziniere da aggiungere
	 * @return flag di avvenuto inserimento
	 * */
	
	
	public boolean addMagazziniere(Manager mag){

		boolean inserito=false;

		try{


			//String insMag = "INSERT INTO `magazziniere` (MagazziniereID, nome, cognome, password, sesso, dataAssunzione, foto, sede_posizioneSede) VALUES ('"+mag.getMagID()+"','"+mag.getNome()+"','"+mag.getCognome()+"','"+mag.getPassword()+"','"+mag.getSesso()+"','"+FormatAndConversion.getInstance().dateDBConversion(mag.getDataAssunzione())+"','"+mag.getFoto()+"','"+mag.getPosizioneSede()+"');";

			//inserito = DbConnection.getInstance().db_update(insMag);

			return inserito;
		}catch(Exception e){return inserito;}
	}


	//SINGLETON

	public static ManagerDAO getInstance(){

		if( instance == null)
			instance = new ManagerDAO();
		return instance;
	}

}
