package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.MemberDAO;

import java.util.ArrayList;


public class Member extends User {

	private int memberID;
	private String name;
	private String surname;
	private String e_mail;
	private String address;



	private String phone;
	private int registrationStatus;

	public Member(){ }

    //SEZIONE METODI

	public static ArrayList<Member> getMemberActiveModel() { return MemberDAO.getInstance().getMemberActiveDAO();}
	public static ArrayList<Member> getMemberNotActiveModel() { return MemberDAO.getInstance().getMemberNotActiveDAO(); }
	public static boolean disabelMemberMemberModel(int idMember) { return MemberDAO.getInstance().disableMemberMemberDAO(idMember); }
	public static boolean enableMemberModel(int idMember) {
		return MemberDAO.getInstance().enableMemberDAO(idMember);
	}
	public static int getIdMemberFromUsernameModel(String userName) { return MemberDAO.getInstance().getIdMemberFromUsernameDAO(userName); }
	public static Boolean uploadMemberModel(String userName, String password, String name, String surname, String email, String address, String phone) { return MemberDAO.getInstance().uploadMemberDAO(userName,password,name,surname,email,address,phone); }
	public static Member getMemberByIdModel(int idMember) {
		return MemberDAO.getInstance().getMemberByIdDAO(idMember);
	}

	//METODI GETTERS

	public int getMemberID() { return memberID; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public String getE_mail() { return e_mail; }
	public int getRegistrationStatus() { return registrationStatus; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }


	//METODI SETTERS

	public void setPhone(String phone) { this.phone = phone; }
	public void setAddress(String address) { this.address = address; }
	public void setMemberID(int memberID) { this.memberID = memberID;}
	public void setName(String name) { this.name = name; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setE_mail(String e_mail) { this.e_mail = e_mail; }
	public void setRegistrationStatus(int registrationStatus) { this.registrationStatus = registrationStatus; }

}
