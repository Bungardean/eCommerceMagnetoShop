package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._3_business.UserBusiness;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Aggiungi Prodotto":

                    MainFrame.getInstance().showAddItemView(); //Richiamo il metodo privato


                    break;
                case "Gestione Categoria":

                    MainFrame.getInstance().showCategoryPage();

                    break;
                case "Gestione Reparto":

                    MainFrame.getInstance().showDepartmentView();

                    break;
                case "Gestione Produttore":

                    MainFrame.getInstance().showProducerGUI();

                    break;
                case "Gestione Distributore":

                    MainFrame.getInstance().showDealerGUI();

                    break;
                case "Logout":

                    logout();

                    break;
                default:
                    System.out.println("Errore ManagerListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }

    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void logout(){
        UserBusiness.getInstance().logout();
        MainFrame.getInstance().showHomePageView();
    }

}
