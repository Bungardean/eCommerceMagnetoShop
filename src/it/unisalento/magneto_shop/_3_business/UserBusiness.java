package it.unisalento.magneto_shop._3_business;

import it.unisalento.magneto_shop._4_model.User;

public class UserBusiness {

    private static UserBusiness instance;

    public UserBusiness() { }

    public void logout() {
        Session.getInstance().svuotaSessione();
    }
    public int  loginBusiness(String userName, String pwd) {

        User user = new User();
        user.setUserName(userName);
        user.setPassword(pwd);

        int companyRole = -1 ;

        //ricavo il ruolo dell'utente nell'azienda a partire dal suo USERNAME
        companyRole = user.getCompanyRoleAndLogin();

        //se l'utente non ha un corrispettivo nel DB il loginBusiness fallisce -1
        return companyRole;
    }

    //SINGLETON
    public static UserBusiness getInstance(){

        if( instance == null )
            instance = new UserBusiness();
        return instance;
    }
}