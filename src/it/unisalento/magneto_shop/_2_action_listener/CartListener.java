package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.Cart_GUI;
import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._3_business.CartBusiness;
import it.unisalento.magneto_shop._3_business.Session;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartListener implements ActionListener {

    private Cart_GUI cart_gui;

    public CartListener(Cart_GUI cart_gui) {
        super();
        this.cart_gui=cart_gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Procedi all'ordine":

                    goToOrder();

                    break;
                case "Indietro":

                    MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(cart_gui.getUsername()));

                    break;
                case "Aggiungi prodotti":

                    MainFrame.getInstance().showCatalog(cart_gui.getUsername());

                    break;
                case "Rimuovi prodotto":

                    deleteItemFromCart();


                    break;
                case "Aggiungi":

                    addCart();

                    break;
                case "Elimina":

                    deleteCart();

                    break;
                default:
                    System.out.println("Errore CartListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }

    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void goToOrder() {


        ArrayList<String> stringArrayList = new ArrayList<>();
        JTabbedPane pane = cart_gui.getPane();
        int pagina = pane.getSelectedIndex();
        int idCart = cart_gui.getCart(pagina).getIdCart();
        String username = cart_gui.getUsername();
        JTable table = cart_gui.getTableArrayList().get(pagina);

        ArrayList<Item> itemArrayList = Session.getInstance().mappaIdCartItem.get(idCart);
        ArrayList<Item> itemArray =  new ArrayList<>();


        //TROVO ITEM SELEZIONATI
        for (int i =0 ; i<table.getRowCount(); i++){
            if ((Boolean) table.getModel().getValueAt(i, 4)){

                stringArrayList.add((String) table.getModel().getValueAt(i,1));

            }
        }

        //CREO NUOVO ARRAY DI ITEM SELEZIONATI
        for (String aStringArrayList : stringArrayList) {

            for (Item anItemArrayList : itemArrayList) {
                if (anItemArrayList.getItemName().equals(aStringArrayList)) {

                    itemArray.add(anItemArrayList);
                }
            }
        }


        if (stringArrayList.size() == 0){
            JOptionPane.showMessageDialog(null, "Attenzione bisogna confermare prodotti!!");
        }else {

            //Ellimino vecchia lista
            Session.getInstance().mappaIdCartItem.clear();
            //SALVO ITEM  SELEZIONATI RELATIVI AL CARRELLO
            Session.getInstance().mappaIdCartItem.put(idCart,itemArray);

            MainFrame.getInstance().showOrderRequestPage(idCart,username);

        }


    }
    private void deleteItemFromCart() {

        ArrayList<String> stringArrayList = new ArrayList<>();
        String username = cart_gui.getUsername();

        JTabbedPane pane = cart_gui.getPane();
        int pagina = pane.getSelectedIndex();
        int idCart = cart_gui.getCart(pagina).getIdCart();

        JTable table = cart_gui.getTableArrayList().get(pagina);


        for (int i =0 ; i<table.getRowCount(); i++){
            if ((Boolean) table.getModel().getValueAt(i, 4)){
                stringArrayList.add((String) table.getModel().getValueAt(i,1));
            }
        }


        ArrayList<Item> itemArrayList = Session.getInstance().mappaIdCartItem.get(idCart);
        ArrayList<Item> itemArray =  new ArrayList<>();

        //CREO NUOVO ARRAY DI ITEM SELEZIONATI Per eliminarli
        for (String aStringArrayList : stringArrayList) {

            for (Item anItemArrayList : itemArrayList) {
                if (anItemArrayList.getItemName().equals(aStringArrayList)) {

                    itemArray.add(anItemArrayList);
                }
            }
        }



        if (stringArrayList.size() == 0){
            JOptionPane.showMessageDialog(null, "Attenzione bisogna confermare prodotti da eliminare!!");
        }else {
            if (CartBusiness.getInstance().deleteItemFromCartBusiness(cart_gui.getCart(pagina).getIdCart(),itemArray)){

                JOptionPane.showMessageDialog(null, "Prodotto eliminato!!");
                MainFrame.getInstance().showCartPage(username);
                cart_gui = Cart_GUI.getInstance();
                cart_gui.showDeleteSection(pagina);
            }
        }

    }
    private void deleteCart() {

        String username = cart_gui.getUsername();
        String cartToDelete =cart_gui.getCartToDelete();
        int index = cart_gui.getIndexOfCartSelected();
        JTabbedPane pane = cart_gui.getPane();



        if (!cartToDelete.isEmpty()){

            int pagina = pane.getSelectedIndex();
            int idCart = cart_gui.getCart(index).getIdCart();

            if (CartBusiness.getInstance().deleteCartFromMemberBusiness(idCart,username)){

                JOptionPane.showMessageDialog(null, "Carrello elliminato!!");
                MainFrame.getInstance().showCartPage(username);
                cart_gui = Cart_GUI.getInstance();
                cart_gui.showDeleteSection(pagina-1);
            }
        }else JOptionPane.showMessageDialog(null, "Attenzione selezionare carrello da eliminare!!");

    }
    private void addCart() {

        String newCart = cart_gui.getAddCartTextField().getText();
        String username = cart_gui.getUsername();
        int nrCarts = cart_gui.getNumberOfCarts();

        if (controlInfo(newCart,username,nrCarts)){
            if (CartBusiness.getInstance().addCartToMemberBusiness(newCart,username)){
                JTabbedPane pane = cart_gui.getPane();
                int pagina = pane.getSelectedIndex();
                JOptionPane.showMessageDialog(null, "Carrello aggiunto!!");
                MainFrame.getInstance().showCartPage(username);
                cart_gui = Cart_GUI.getInstance();
                cart_gui.showDeleteSection(pagina+1);
            }

        }


    }
    private boolean controlInfo(String newCart, String username, int nrCarts) {

        if (newCart.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione inserisci un nome!!");
            return false;
        } if (CartBusiness.getInstance().controlCartNameBusiness(newCart,username)){
            JOptionPane.showMessageDialog(null, "Attenzione nome carrello gia presente!!");
            return false;
        } if (nrCarts >= 4){
        JOptionPane.showMessageDialog(null, "Attenzione raggiunto numero massimo di carrelli!!");
        return false;
        }else return true;
    }
}
