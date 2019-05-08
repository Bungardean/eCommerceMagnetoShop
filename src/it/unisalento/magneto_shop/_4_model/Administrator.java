package it.unisalento.magneto_shop._4_model;

public class Administrator extends User {

	private int adminID;
	private String name;
	private String surname;
	private String e_mail;
	private byte[] foto;


	/* CONSTRUCTOR OF ADMINISTRATOR CLASS */
	public Administrator(){ }



	/* METHOD OF ADMINISTRATOR CLASS */

	/* GETTER AND SETTER OF ADMINISTRATOR CLASS */

	//GETTERS
	public int getAdminID() { return adminID; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public String getE_mail() { return e_mail; }
	public byte[] getFoto() { return foto; }


	//SETTERS
	public void setAdminID(int adminID) { this.adminID = adminID; }
	public void setName(String name) { this.name = name; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setE_mail(String e_mail) { this.e_mail = e_mail; }
	public void setFoto(byte[] foto) { this.foto = foto; }
}
