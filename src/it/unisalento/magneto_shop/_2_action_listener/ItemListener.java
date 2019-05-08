package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Item_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._3_business.CartBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemListener implements ActionListener {

    private Item_GUI itemGUI;

    public ItemListener(Item_GUI itemGUI) {
        super();
        this.itemGUI = itemGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();

        try {


            switch (com) {
                case "Catalogo":


                    catalog(); //Richiamo il metodo privato per tornare al catalogo


                    break;
                case "Aggiungi":


                    addItemToCart(); //Richiamo il metodo privato per tornare al catalogo


                    break;
                default:
                    System.out.println("Errore ItemListener");
                    break;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/

    private void addItemToCart() {

        String quantity = itemGUI.getQuantityBox();
        String typeOfCart = itemGUI.getCartBox();


        if (controlInfo(quantity,typeOfCart)){

            String userName = itemGUI.getUsername();

            if (CartBusiness.getInstance().addItemToCartBusiness(itemGUI.getItem().getIdItem(),userName,typeOfCart,Integer.parseInt(quantity))){
                JOptionPane.showMessageDialog(null, "Prodotto aggiunto al carrello "+typeOfCart+"!!");
                MainFrame.getInstance().showCatalog(userName);
            }

        }

    }
    private boolean controlInfo(String quantity, String typeOfCart) {

        if (quantity.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione scegli la quantita!!");
            return false;
        }else if (typeOfCart.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione scegli un carrello dove aggiungere il prodotto!!");
            return false;
        }else return true;

    }
    private void catalog(){ MainFrame.getInstance().showCatalog(itemGUI.getUsername()); }
}
