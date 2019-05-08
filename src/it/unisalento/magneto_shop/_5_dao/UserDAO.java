package it.unisalento.magneto_shop._5_dao;


import it.unisalento.magneto_shop._4_model.User;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class UserDAO {

	private static UserDAO instance;

	public UserDAO() { }

	/*ASK DATABASE LOGIN IF FOR THE USERNAME AND PASSWORD THERE IS A ID USER*/
	public int getUserDAO(User user) {

		return search(user);

	}

	private int search(User user){

		//ricavo le credenziali dal itanza della classe user
		String userName = user.getUserName();
		String password = user.getPassword();

		//query per selletionare ID di un user
		String queryMember = "SELECT `idMEMBER` FROM `MEMBER` WHERE `userName`='" + userName + "'";
		String queryManager = "SELECT `idMANAGER` FROM `MANAGER` WHERE `userName`='" + userName + "'";
		String queryAdmin = "SELECT `idADMINISTRATOR` FROM `ADMINISTRATOR` WHERE `userName`='" + userName + "'";


		ArrayList<String[]> resultMember = DbConnection.getInstance().db_query(queryMember);
		ArrayList<String[]> resultManager = DbConnection.getInstance().db_query(queryManager);
		ArrayList<String[]> resultAdmin = DbConnection.getInstance().db_query(queryAdmin);

		//SE LA QUERY DA RISULTATO > 1 NELLA TABELLA DEL MEMBER ENTRA
		if (resultMember.size() > 0) { return MemberDAO.getInstance().getMember((Integer.valueOf(resultMember.get(0)[0])), password, userName);}


		//SE LA QUERY DA RISULTATO > 1 NELLA TABELLA DEL MANAGER ENTRA
		if (resultManager.size() > 0) { return ManagerDAO.getInstance().getManager((Integer.valueOf(resultManager.get(0)[0])), password, userName);}


		//SE LA QUERY DA RISULTATO > 1 NELLA TABELLA DEL ADMINISTRATOR ENTRA
		if(resultAdmin.size() > 0){ return AdministratorDAO.getInstance().getAdministrator((Integer.valueOf(resultAdmin.get(0)[0])),password,userName);}

		//NON TROVA NULLA NELLE TABELLE
		return -1; //login fallito
	}




	/* ASK DATABASE IF FOR THE USERNAME THERE IS A ID USER */
	public Boolean userNameControlDAO(String userName){

		boolean result = true;

		try {

			String queryMember = "SELECT `idMEMBER` FROM `MEMBER` WHERE `userName`='" + userName + "'";
			String queryManager = "SELECT `idMANAGER` FROM `MANAGER` WHERE `userName`='" + userName + "'";
			String queryAdmin = "SELECT `idADMINISTRATOR` FROM `ADMINISTRATOR` WHERE `userName`='" + userName + "'";


			ArrayList<String[]> resultMember = DbConnection.getInstance().db_query(queryMember);
			ArrayList<String[]> resultManager = DbConnection.getInstance().db_query(queryManager);
			ArrayList<String[]> resultAdmin = DbConnection.getInstance().db_query(queryAdmin);

			if(resultMember.size() == 0 && resultManager.size() == 0 &&resultAdmin.size() == 0){result = false;}

		}catch(NullPointerException e){ e.printStackTrace(); return result; }
		return result;
	}

	//SINGLETON

	public static UserDAO getInstance() {
		if(instance == null)
			instance = new UserDAO();
		return instance;
	}
}
