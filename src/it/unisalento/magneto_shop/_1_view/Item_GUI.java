package it.unisalento.magneto_shop._1_view;

import it.unisalento.magneto_shop._2_action_listener.ItemListener;
import it.unisalento.magneto_shop._3_business.CartBusiness;
import it.unisalento.magneto_shop._3_business.CatalogBusiness;
import it.unisalento.magneto_shop._3_business.PathImmagini;
import it.unisalento.magneto_shop._4_model.Cart;
import it.unisalento.magneto_shop._4_model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Item_GUI extends JPanel {


    private  String username;


    private Item item;

    private ImageIcon img = new ImageIcon(PathImmagini.returnPath()+"/icon/foreground.jpg");
    private JLabel backGroundLabel = new JLabel(img);
    private JLabel itemNameLabel = new JLabel();
    private JLabel itemDescriptionLabel = new JLabel();
    private JLabel itemPriceLabel = new JLabel();
    private JLabel itemSaleLabel = new JLabel();

    private JLabel itemImageLabel;
    private ImageIcon imgRet = new ImageIcon(PathImmagini.returnPath()+"/icon/back.png");
    private JButton returnButton = new JButton(imgRet);
    private ItemListener itemListener = new ItemListener(this);
    private JEditorPane editorPane;
    private JScrollPane editorScrollPane;

    private JPanel panelLEFT = new JPanel(new GridLayout(2,1));
    private Font fontpic = new Font("Impact", Font.ITALIC,17 );

    private DefaultComboBoxModel<String> modelCart =new DefaultComboBoxModel<String>();
    private JComboBox<String> cartBox;

    private DefaultComboBoxModel<String> modelQuantity =new DefaultComboBoxModel<String>();
    private JComboBox<String> quantityBox;

    public Item_GUI(Item item, String username) {

        this.item=item;
        this.username=username;
        this.setLayout(null);
        this.setBounds(0,0,800,600);
        backGroundLabel.setLayout(null);
        panelLEFT.setOpaque(false);

        /*IMAGE SECTION*/
        Image img = Toolkit.getDefaultToolkit().createImage(item.getFotoItem());
        ImageIcon iconUnisalento3 = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        itemImageLabel = new JLabel(iconUnisalento3);
        //  ResizeImage(item);

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setText("Descrizione:\n\n"+item.getDescriptionItem()+"\n\nProduttore: "+item.getProducer()+"\n\nRivenditore: "+item.getDealer());
        editorPane.setLayout(null);
        //Put the editor pane in a scroll pane.
        editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setHorizontalScrollBar(null);

        itemNameLabel.setText(item.getItemName());
        itemDescriptionLabel.setText(item.getDescriptionItem());
        itemPriceLabel.setText("Prezzo: "+String.valueOf(item.getPrice())+"€");
        if (item.getPrice() > item.getSales()){
            itemSaleLabel.setText("Offerta: "+String.valueOf(item.getSales())+"€" );
        }
        /*CONTROL FOR PRESENCE OF FATHER PRODUCT*/
        ArrayList<String> stringArrayList = CatalogBusiness.getInstance().getIdFatherItemForIdItemBusiness(item.getIdItem());
        if (!stringArrayList.isEmpty()) {
            String delimiter = ", ";
            String result = String.join(delimiter, stringArrayList);
            editorPane.setText("Descrizione:\n\n"+item.getDescriptionItem()+"\n\nProduttore: "+item.getProducer()+"\n\nRivenditore: "+item.getDealer()+"\n\nSotto prodotto di: " + result);

        }
        /*CONTROL IF USER IS LOG TO SHOW COMPONENTS FOR BUY*/
        if (!username.equals("nonLogato")){
            showCart();
        }
        /* COMPONENTS PLACEMENT SECTION */
        backGroundLabel.setBounds(0,0,800,600);
        panelLEFT.setBounds(352,20,360,350);
        itemImageLabel.setBounds(20, 20 ,300,300);
        itemPriceLabel.setBounds(50,320,300,50);
        itemSaleLabel.setBounds(50,370,300,50);
        returnButton.setBounds(350,450,100,100);
        itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
        /* COMPONENTS DIMENSION SECTION */
        panelLEFT.setPreferredSize(panelLEFT.getPreferredSize());
        itemNameLabel.setPreferredSize(new Dimension(200,100));
        editorScrollPane.setPreferredSize(new Dimension(360,300));

        /* COMPONENTS FONT SECTION */
        editorPane.setFont(fontpic);
        itemNameLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,45 ));
        itemNameLabel.setForeground(Color.ORANGE);
        itemPriceLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,20 ));
        itemPriceLabel.setForeground(Color.WHITE);
        itemSaleLabel.setFont(new Font("Impact",Font.BOLD | Font.ITALIC,20 ));
        itemSaleLabel.setForeground(Color.red);
        returnButton.setBorder(null);
        /* COMPONENTS ADD SECTION */
        add(backGroundLabel);
        backGroundLabel.add(returnButton);
        backGroundLabel.add(panelLEFT);
        backGroundLabel.add(itemImageLabel);
        backGroundLabel.add(itemPriceLabel);
        backGroundLabel.add(itemSaleLabel);
        panelLEFT.add(itemNameLabel);
        panelLEFT.add(editorScrollPane);

        /* ACTION LISTENERS SECTION */
        returnButton.addActionListener(itemListener);
        returnButton.setActionCommand("Catalogo");
    }


    private void showCart(){

       ArrayList<Cart> cartArrayList = CartBusiness.getInstance().getCartByMemberBusiness(username);

       /*DYNAMICALLI GENERATES THE CART_LIST MENU*/
       modelCart.addElement("");
       for (Cart aCartArrayList : cartArrayList) {
           modelCart.addElement(aCartArrayList.getTypeOfCart());
       }
       cartBox = new JComboBox<>(modelCart);

        /*DYNAMICALLI GENERATES THE QUANTITY MENU*/
       modelQuantity.addElement("");
       for (int i = 0 ; i<10; i++){
           modelQuantity.addElement(String.valueOf(1+i));
       }
       quantityBox = new JComboBox<>(modelQuantity);


       JButton confirmButton = new JButton("Aggiungi");
       JLabel quantityLabel = new JLabel("Quantita");
       JLabel cartLabel = new JLabel("Carrello");

       quantityLabel.setBounds(352,370,110,50);
       quantityBox.setBounds(460,370,150,50);
       cartLabel.setBounds(352,420,110,50);
       cartBox.setBounds(460,420,150,50);
       confirmButton.setBounds(610,420,105,50);

       quantityLabel.setFont(fontpic);
       quantityLabel.setForeground(Color.WHITE);
       quantityBox.setFont(fontpic);
       cartLabel.setFont(fontpic);
       cartLabel.setForeground(Color.WHITE);
       cartBox.setFont(fontpic);
       confirmButton.setFont(fontpic);

       add(quantityLabel);
       add(cartLabel);
       add(quantityBox);
       add(confirmButton);
       add(cartBox);

       confirmButton.addActionListener(itemListener);
   }


    public String getUsername() { return username; }
    public String getCartBox() { return (String) cartBox.getSelectedItem(); }
    public String getQuantityBox() { return (String) quantityBox.getSelectedItem(); }
    public Item getItem() { return item; }

}
