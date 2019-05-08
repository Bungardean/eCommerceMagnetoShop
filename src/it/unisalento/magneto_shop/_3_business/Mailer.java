package it.unisalento.magneto_shop._3_business;

import it.unisalento.magneto_shop._4_model.Order;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
public class Mailer{


    private static final String from ="magneto.shop.unisalento@gmail.com";
    private static final String password ="00000";



    public static void send(String to, String sub, String msg, int idOrder){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        javax.mail.Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {


            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(sub);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(msg);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            if (idOrder != 0) {

                Order order = OrderBusiness.getInstance().getOrderByIdOrderBusiness(idOrder);


                // Part two is attachment
                if (order.getOrderStatus().equals("COMPLETATO")) {

                    PrintPDF.getInstance().order_receipt(order);

                    // Part two is attachment
                    messageBodyPart = new MimeBodyPart();
                    String filename = PathImmagini.returnPath() + "/documents/distinta_"+order.getReciver()+"_" + order.getIdOrder() + ".pdf";;
                    DataSource source = new FileDataSource(filename);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(filename);
                    multipart.addBodyPart(messageBodyPart);
                }
            }
                // Send the complete message parts
                message.setContent(multipart);

            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

}