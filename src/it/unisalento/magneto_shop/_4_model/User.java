package it.unisalento.magneto_shop._4_model;

import it.unisalento.magneto_shop._5_dao.UserDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    //CONSTANT COMPANYROLES
    public static final int MEMBER = 1;
    public static final int MANAGER = 2;
    public static final int ADMINISTRATOR = 3;

    //VARIABLES OF GENERIC USER
    private String userName;
    private String password;

    /* CONSTRUCTOR OF USER CLASS */
    public User(){ }


    /* METHOD OF USER CLASS */
    public int getCompanyRoleAndLogin() {
        int companyRole = UserDAO.getInstance().getUserDAO(this);
        return companyRole;
    }

    /* GETTER AND SETTER OF USER CLASS */

    //GETTERS
    public  String getUserName() { return userName; }
    public String getPassword() { return password; }

    //SETTERS
    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }

    public static Date getFromStringToDate(String dateString){

        SimpleDateFormat parseFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        try {
            date = parseFormatter.parse(dateString);
            String dateS = nFormatter.format(date);
            date = nFormatter.parse(dateS);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
