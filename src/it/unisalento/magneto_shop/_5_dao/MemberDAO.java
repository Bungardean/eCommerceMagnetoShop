package it.unisalento.magneto_shop._5_dao;

import it.unisalento.magneto_shop._3_business.EncryptDecryptWhitDES;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.User;
import it.unisalento.magneto_shop._6_dbinterface.DbConnection;

import java.util.ArrayList;

public class MemberDAO {
	
	private static MemberDAO instance;
	
	public MemberDAO(){ }
	
	protected int getMember(int idMember, String password, String userName) {

        String queryMember = "SELECT `*` FROM `MEMBER` WHERE `idMEMBER`='" +idMember+ "'";

		try {

			ArrayList<String[]> resultMember = DbConnection.getInstance().db_query(queryMember);
			String pwd = EncryptDecryptWhitDES.getInstance().encrypt(resultMember.get(0)[2]);

			if (password.equals(pwd) && userName.equals(resultMember.get(0)[1])) {

				Member member = new Member();
				Session.getInstance().mappa.put(userName, member);

				member.setMemberID(Integer.valueOf(resultMember.get(0)[0]));
				member.setUserName(resultMember.get(0)[1]);
				member.setPassword(pwd);
				member.setName(resultMember.get(0)[3]);
				member.setSurname(resultMember.get(0)[4]);
				member.setE_mail(resultMember.get(0)[5]);
				member.setRegistrationStatus(Integer.valueOf(resultMember.get(0)[6]));
				member.setAddress(resultMember.get(0)[8]);
				member.setPhone(resultMember.get(0)[9]);
				//member.setFoto(DbConnection.getInstance().getBlobData().get(0)); //elemento [6]

				if(Integer.valueOf(resultMember.get(0)[6]) == 1){ return User.MEMBER;}
				return -2; //MEMBER NOT YET ACTIVE
			}
		}catch(NullPointerException e){ e.printStackTrace(); }

		return -1; //Errore
    }






	/* INSERT A NEW USER IN DATABASE */
	public boolean uploadMemberDAO(String userName, String password, String givenNames, String surname, String email, String address, String phone){

		boolean result = false;
		try {

			EncryptDecryptWhitDES.getInstance();
			String pwd = EncryptDecryptWhitDES.decrypt(password);
			String query = "INSERT INTO `dbMagneto`.`MEMBER` (`userName`, `password`, `name`, `surname`, `email`, `registration_status`, `ADMINISTRATOR_idADMINISTRATOR`,`address`,`phone`) VALUES ('"+userName+"', '"+pwd+"', '"+givenNames+"', '"+surname+"', '"+email+"', '0', '1','"+address+"','"+phone+"')";
			result = DbConnection.getInstance().db_update(query);

		} catch (Exception e) { return result; }
		return result;
	}

	

	


    public ArrayList<Member> getMemberNotActiveDAO() {


		ArrayList<String[]> resultMember = null;
		ArrayList<Member> memberArrayList = new ArrayList<>();

		String queryMember = "SELECT MEMBER.idMEMBER,MEMBER.userName,MEMBER.registration_status FROM `MEMBER` WHERE `registration_status`='0'";

		try {

			resultMember = DbConnection.getInstance().db_query(queryMember);

			for (String[] aResultMember : resultMember) {

				Member member = new Member();

				member.setMemberID(Integer.valueOf(aResultMember[0]));
				member.setUserName(aResultMember[1]);
				member.setRegistrationStatus(Integer.parseInt(aResultMember[2]));

				memberArrayList.add(member);
			}

		}catch(NullPointerException e){ e.printStackTrace(); }
		return memberArrayList; //Errore
	}


    public ArrayList<Member> getMemberActiveDAO() {


		ArrayList<String[]> resultMember = null;
		ArrayList<Member> memberNotActiveArrayList = new ArrayList<>();

		String queryMember = "SELECT MEMBER.idMEMBER,MEMBER.userName,MEMBER.registration_status FROM `MEMBER` WHERE `registration_status`='1'";

		try {

			resultMember = DbConnection.getInstance().db_query(queryMember);

			for (String[] aResultMember : resultMember) {

				Member member = new Member();

				member.setMemberID(Integer.valueOf(aResultMember[0]));
				member.setUserName(aResultMember[1]);
				member.setRegistrationStatus(Integer.parseInt(aResultMember[2]));

				memberNotActiveArrayList.add(member);
			}

		}catch(NullPointerException e){ e.printStackTrace(); }

		return memberNotActiveArrayList;

	}

	public boolean disableMemberMemberDAO(int idMember) {

		boolean result = false;

		try {

			String query = "UPDATE `dbMagneto`.`MEMBER` SET `registration_status`='0' WHERE `idMEMBER`='"+idMember+"'";
			result = DbConnection.getInstance().db_update(query);

		} catch (Exception e) { return result; }
		return result;
	}

	public boolean enableMemberDAO(int idMember) {

		boolean result = false;

		try {

			String query = "UPDATE `dbMagneto`.`MEMBER` SET `registration_status`='1' WHERE `idMEMBER`='"+idMember+"'";
			result = DbConnection.getInstance().db_update(query);

		} catch (Exception e) { return result; }
		return result;
	}

    public int getIdMemberFromUsernameDAO(String userName) {

		int update = 0;

		ArrayList<String[]> resultMember = null;

		try {
			String queryMember = "SELECT `idMEMBER` FROM `MEMBER` WHERE `userName`='" +userName+ "'";
			resultMember = DbConnection.getInstance().db_query(queryMember);
			update= Integer.parseInt(resultMember.get(0)[0]);
		} catch (Exception e) { return update; }
		return update;
    }

    public Member getMemberByIdDAO(int idMember) {


		String queryMember = "SELECT `*` FROM `MEMBER` WHERE `idMEMBER`='" + idMember + "'";

		Member member = null;
		try {

			ArrayList<String[]> resultMember = DbConnection.getInstance().db_query(queryMember);
			String pwd = EncryptDecryptWhitDES.getInstance().encrypt(resultMember.get(0)[2]);


			member = new Member();

			member.setMemberID(Integer.valueOf(resultMember.get(0)[0]));
			member.setUserName(resultMember.get(0)[1]);
			member.setPassword(pwd);
			member.setName(resultMember.get(0)[3]);
			member.setSurname(resultMember.get(0)[4]);
			member.setE_mail(resultMember.get(0)[5]);
			member.setRegistrationStatus(Integer.valueOf(resultMember.get(0)[6]));
			member.setAddress(resultMember.get(0)[8]);
			member.setPhone(resultMember.get(0)[9]);
			//member.setFoto(DbConnection.getInstance().getBlobData().get(0)); //elemento [6]


		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return member;
	}

	//SINGLETON
	public static MemberDAO getInstance(){

		if( instance == null)
			instance = new MemberDAO();
		return instance;
	}
}