package it.unisalento.magneto_shop._4_model;

public class Manager extends User{

	private int managerID;
	private String name;
	private String surname;
	private String e_mail;
	private byte[] foto;
	
	public Manager(){ }


	//SEZIONE METODI
	
	
	//METODI GETTERS
	public int getManagerID() { return managerID; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public String getE_mail() { return e_mail; }
	public byte[] getFoto() { return foto; }



	//METODI SETTERS
	public void setManagerID(int managerID) { this.managerID = managerID; }
	public void setName(String name) { this.name = name; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setE_mail(String e_mail) { this.e_mail = e_mail; }
	public void setFoto(byte[] foto) { this.foto = foto; }
}
