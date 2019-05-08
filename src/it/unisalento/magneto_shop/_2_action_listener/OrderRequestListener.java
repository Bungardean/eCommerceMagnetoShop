package it.unisalento.magneto_shop._2_action_listener;

import it.unisalento.magneto_shop._1_view.MainFrame;
import it.unisalento.magneto_shop._1_view.OrderRequest_GUI;
import it.unisalento.magneto_shop._1_view.OrderSatus_GUI;
import it.unisalento.magneto_shop._3_business.*;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderRequestListener implements ActionListener {

    private OrderRequest_GUI orderRequest_gui;
    private OrderSatus_GUI orderSatus_gui;

    public OrderRequestListener(OrderRequest_GUI orderRequest_gui) {
        super();
        this.orderRequest_gui = orderRequest_gui;
    }

    public OrderRequestListener(OrderSatus_GUI orderSatus_gui) {
        super();
        this.orderSatus_gui = orderSatus_gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();

        try {

            switch (com) {
                case "Indietro1":

                    MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(orderSatus_gui.getUsername()));

                    break;

                case "Indietro":

                    MainFrame.getInstance().showCartPage(orderRequest_gui.getUsername());


                    break;

                case "Stampa":

                    String string = orderSatus_gui.getOrderBoxEdit();
                    if (!string.isEmpty()) {
                        int idOrder = Integer.parseInt(string);
                        ArrayList<Order> orderArrayList = OrderBusiness.getInstance().getOrderByIdMemberBusiness(orderSatus_gui.getUsername());


                        for (Order anOrderArrayList : orderArrayList) {


                            if (anOrderArrayList.getIdOrder() == idOrder) {

                                PrintPDF.getInstance().order_receipt(anOrderArrayList);

                            }
                        }
                        JOptionPane.showMessageDialog(null, "Distinta d'ordine salvata e pronta per essere stampata!");
                        MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(orderSatus_gui.getUsername()));
                    }else {
                        JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere id del ordine da stampare!");

                    }

                    break;
                case "Conferma ordine":

                    addOrder();

                    break;
                default:
                    System.out.println("Errore OrderRequestListener");
                    break;
            }
        } catch (Exception e1) { e1.printStackTrace(); }
    }
    /*PRIVATE METHODS FOR EACH COMPONENT*/
    private void addOrder() {

        int idCart = orderRequest_gui.getIdCart();
        String username = orderRequest_gui.getUsername();
        String name = orderRequest_gui.getReciverNameTextField().getText();
        String surname = orderRequest_gui.getReciverSurnameTextField().getText();
        String address = orderRequest_gui.getAddressTextField().getText();
        String payment = orderRequest_gui.getPaymentBox();
        String shipping = orderRequest_gui.getShippingBox();
        float total = orderRequest_gui.getTotal();

        if (controlInfo(name,surname,address,payment,shipping)){

            if (CoreSistemBusiness.getInstance().addOrderBusiness(name,surname,address,username,payment,shipping,idCart,total)) {

                CartBusiness.getInstance().deleteItemFromCartBusiness(idCart, Session.getInstance().mappaIdCartItem.get(idCart));
                Member member = (Member) Session.getInstance().mappa.get(username);
                Mailer.send(member.getE_mail(),"Ordine","Gentile"+member.getName()+" "+member.getSurname()+"\nOrdine effettuato!!", 0);
                JOptionPane.showMessageDialog(null, "Ordine effettuato con conferma su email!!");
                MainFrame.getInstance().showMemberPage((Member) Session.getInstance().mappa.get(username));
            }

        }

    }
    private boolean controlInfo(String name, String surname, String address, String payment, String shipping) {


        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un nome!");
            return false;
        } if (surname.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un cognome!");
            return false;
        }if (address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga inserire un indirizzo!");
            return false;
        }if (payment.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un metodo di pagamento!");
            return false;
        }if (shipping.isEmpty()){
            JOptionPane.showMessageDialog(null, "Attenzione! Bisonga scegliere un metodo di spedizione!");
            return false;
        }
        else return true;
    }
}
