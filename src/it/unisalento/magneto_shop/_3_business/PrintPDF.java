package it.unisalento.magneto_shop._3_business;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import it.unisalento.magneto_shop._4_model.Item;
import it.unisalento.magneto_shop._4_model.Member;
import it.unisalento.magneto_shop._4_model.Order;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class PrintPDF {

    private static PrintPDF instance;
    private static String DEST = "/Users/paulbungardean/Documents/Principi_Software2017/Paul_Stefan_Bungardean_20011702/documents/";
    private static Font bigFont  = new Font(Font.FontFamily.TIMES_ROMAN, 18,  Font.BOLD);
    private static Font redFont  = new Font(Font.FontFamily.TIMES_ROMAN, 12,  Font.NORMAL, BaseColor.RED);
    private static Font subFont  = new Font(Font.FontFamily.TIMES_ROMAN, 13,  Font.BOLD);
    private static Font smallBold  = new Font(Font.FontFamily.TIMES_ROMAN, 12,  Font.BOLD);

    public PrintPDF() { }

    public void order_receipt(Order order){



        try {
            Document document = new Document();
            String file = DEST + "distinta_"+order.getReciver()+"_" + order.getIdOrder() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            Paragraph prefazione = new Paragraph();
            prefazione.setAlignment(Element.ALIGN_CENTER);

            // Aggiungiamo una linea vuota
            aggiungiLineaVuota(prefazione, 1);

            // Aggiungiamo il titolo

            prefazione.add(new Paragraph("Fattura ordine", bigFont));

            aggiungiLineaVuota(prefazione, 2);

            // Questa linea scrive "Documento generato da: nome utente, data"
            //prefazione.add(new Paragraph("Documento generato da: " + System.getProperty("user.name") + ", " + new Date(), smallBold));


            aggiungiLineaVuota(prefazione, 3);

            Member member = CoreSistemBusiness.getInstance().getMemberByIdBusiness(order.getIdMember());

            prefazione.add(new Paragraph("OrdineID: " + order.getIdOrder() + "   User: " + member.getName()+" "+member.getSurname() + "  Data ordine: " +order.getOrderData()+ "  Importo: " +order.getOrderCost()+"€",subFont));

            aggiungiLineaVuota(prefazione, 3);


            PdfPTable tabella = new PdfPTable(4);

            // tabella.setBorderColor(BaseColor.GRAY);
            // tabella.setPadding(4);
            // tabella.setSpacing(4);
            // tabella.setBorderWidth(1);

            PdfPCell c1 = new PdfPCell(new Phrase("Codice Prodotto"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setGrayFill(0.8f);
            tabella.addCell(c1);

            c1 = new PdfPCell(new Phrase("Prodotto"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setGrayFill(0.8f);
            tabella.addCell(c1);

            c1 = new PdfPCell(new Phrase("Costo"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setGrayFill(0.8f);
            tabella.addCell(c1);
            c1 = new PdfPCell(new Phrase("Quantita"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setGrayFill(0.8f);
            tabella.addCell(c1);
            tabella.setHeaderRows(1);


            ArrayList<Item> itemArrayList = OrderBusiness.getInstance().getItemByIdOrderBusiness(order.getIdOrder());


            for (Item anItemArrayList : itemArrayList) {

                tabella.addCell(String.valueOf(anItemArrayList.getIdItem()));
                tabella.addCell(anItemArrayList.getItemName());
                float price = anItemArrayList.getPrice();
                float sales = anItemArrayList.getSales();

                if (price > sales) price = sales;
                tabella.addCell(String.valueOf(price+"€"));

                int quantity = OrderBusiness.getInstance().getQuantityForItemBusiness(anItemArrayList.getIdItem(),order.getIdOrder());
                tabella.addCell(String.valueOf(quantity));

            }

            prefazione.add(tabella);


            // Aggiunta al documento
            document.add(prefazione);

            // Nuova pagina
            document.newPage();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void aggiungiLineaVuota(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    //SINGLETON
    public static PrintPDF getInstance(){

        if( instance == null )
            instance = new PrintPDF();
        return instance;
    }

}
