package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.Member_GUI;
import it.unisalento.magneto_shop._3_business.UserBusiness;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberListener implements ActionListener {

    private Member_GUI memberGUI;

    public MemberListener(Member_GUI memberGUI) {
        super();
        this.memberGUI = memberGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();

        try {
            switch (com) {

                case "Ordine" :

                    MainFrame.getInstance().showManageOrderInfoPage(memberGUI.getUsername());

                    break;

                case "Catalogo":

                    MainFrame.getInstance().showCatalog(memberGUI.getUsername());

                    break;
                case "Logout":

                    logout();//Richiamo il metodo privato per tornare al catalogo


                    break;
                case "Carrello":

                    MainFrame.getInstance().showCartPage(memberGUI.getUsername());

                    break;
                default:
                    System.out.println("Errore MemberListener");
                    break;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void logout() {
        UserBusiness.getInstance().logout();
        MainFrame.getInstance().showHomePageView();
    }

}