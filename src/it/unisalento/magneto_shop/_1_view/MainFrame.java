package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._4_model.Administrator;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Manager;
import it.unisalento.magneto_shop._4_model.Member;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private static MainFrame instance;

    public MainFrame() throws HeadlessException {

        setBounds(0, 0, 800, 600); //dimensioni del fame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Magneto Shop");
        setLayout(null);//specifica come devono essere organizzati i componenti in questo caso sono assolutte
        setLocationRelativeTo(null); //centra il frame al centro dello schermo
        setResizable(false); //non permette di ridimensionare il frame
        setVisible(true);        //rende visibile il frame

    }

   public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.showHomePageView();
            }
        });
    }

    public  void showHomePageView(){
        HomePage_GUI homePageGUI =  new HomePage_GUI();
        this.setTitle("Magneto Shop");
        MainFrame.getInstance().setContentPane(homePageGUI); //chiede un istanza di main frame su cui lavorera
    }

    public void showRegisterPage() {

        Registration_GUI registrationGUI = new Registration_GUI();
        this.setTitle("Registrazione");
        this.setContentPane(registrationGUI);
    }

    public void showCatalog(String username){

        Catalog_GUI catalog =  new Catalog_GUI(username);
        this.setTitle("Catalogo");
        this.setContentPane(catalog);

    }

    public void showItemPage(Item item, String username){

        Item_GUI itemGUI = new Item_GUI(item,username);
        this.setTitle("Prodotto "+item.getItemName());
        this.setContentPane(itemGUI);

    }
    public void showManagerPage(Manager manager){

        Manager_GUI managerGUI = new Manager_GUI(manager);
        this.setTitle("Manager "+manager.getSurname());
        this.setContentPane(managerGUI);

    }
    public void showMemberPage(Member member) {
        Member_GUI memberGUI = new Member_GUI(member);
        this.setTitle("User "+member.getUserName());
        this.setContentPane(memberGUI);
    }

    public void showCartPage(String username) {
        Cart_GUI cart_gui = new Cart_GUI(username);
        Cart_GUI.setInstance(cart_gui);
        this.setTitle("Carrello");
        this.setContentPane(cart_gui);
    }

    public void showAdministratorPage(Administrator administrator) {
        Administrator_GUI administratorGUI = new Administrator_GUI(administrator);
        this.setTitle("Administratore "+administrator.getSurname());
        this.setContentPane(administratorGUI);

    }
    public void showManageMemberPage(String username) {
        ManageMember_GUI manageMemberGUI = new ManageMember_GUI(username);
        this.setTitle("Gestione Utente");
        this.setContentPane(manageMemberGUI);

    }
    public void showAddItemView() {
        ManageItem_GUI manageItemGUI = new ManageItem_GUI();
        ManageItem_GUI.setInstance(manageItemGUI);
        this.setTitle("Gestione Prodotto");
        this.setContentPane(manageItemGUI);
    }
    public void showCategoryPage() {
        Category_GUI categoryGUI = new Category_GUI();
        Category_GUI.setInstance(categoryGUI);
        this.setTitle("Gestione Categorie");
        this.setContentPane(categoryGUI);
    }
    public void showDepartmentView() {
        DepartmentGUI departmentGUI = new DepartmentGUI();
        DepartmentGUI.setInstance(departmentGUI);
        this.setTitle("Gestione Reparto");
        this.setContentPane(departmentGUI);
    }
    public void showProducerGUI() {
        Producer_GUI producerGUI = new Producer_GUI();
        Producer_GUI.setInstance(producerGUI);
        this.setTitle("Gestione Produttore");
        this.setContentPane(producerGUI);
    }
    public void showDealerGUI() {
        Dealer_GUI dealerGUI = new Dealer_GUI();
        Dealer_GUI.setInstance(dealerGUI);
        this.setTitle("Gestione Distributore");
        this.setContentPane(dealerGUI);
    }

    public void showOrderRequestPage(int idCart, String username) {
        OrderRequest_GUI orderRequestGui = new OrderRequest_GUI(idCart,username);
        this.setTitle("Riepilogo ordine");
        this.setContentPane(orderRequestGui);
    }

    public void showManageOrderPage(String username) {
        ManageOrder_GUI manageOrderGui =  new ManageOrder_GUI(username);
        this.setTitle("Gestione Ordine");
        this.setContentPane(manageOrderGui);
    }
    public void showManageOrderInfoPage(String username) {
        OrderSatus_GUI orderSatusGui =  new OrderSatus_GUI(username);
        this.setTitle("Informazioni Ordini");
        this.setContentPane(orderSatusGui);
    }

    //SINGLETON
    public static MainFrame getInstance() {
        if (instance == null)
            instance = new MainFrame();
        return instance;
    }
}