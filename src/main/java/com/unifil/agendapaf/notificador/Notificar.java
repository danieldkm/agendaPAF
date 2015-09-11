package com.unifil.agendapaf.notificador;

import com.unifil.agendapaf.model.Usuario;
import java.util.Properties;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author danielmorita
 */
public class Notificar {

//    private static String USER_NAME = "danielkeyti@gmail.com";  // GMail user name (just the part before "@gmail.com")
//    private static String PASSWORD = "daniel@morita"; // GMail password
//    private static String RECIPIENT = "'danielkeyti@gmail.com, danielmorita@hotmail.com, williansferr@gmail.com";

//    public static void main(String[] args) {
//        String from = USER_NAME;
//        String pass = PASSWORD;
////        String[] to = { RECIPIENT }; // list of recipient email addresses
//        String[] to = {"danielkeyti@gmail.com", "danielmorita@hotmail.com", "williansferr@gmail.com"};
//        String subject = "Teste assunto do email";
//        String body = "Teste teste teste teste";
//
////        sendFromGMail(from, pass, to, subject, body);
//    }

    public Notificar(String from, String pass, ObservableList<Usuario> listaUsuario, String assunto, String corpo) {
        this.from = from;
        this.pass = pass;
        this.subject = assunto;
        this.body = corpo;
        this.to = createCC(listaUsuario);
    }

    private String from;
    private String pass;
    private String[] to;
    private String subject;
    private String body;

    public void sendFromGMail() {
        if (to != null) {
            System.out.println("Enviando notificação");
            Properties props = System.getProperties();
            String host = "smtp.gmail.com";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[to.length];

                // To get the array of addresses
                for (int i = 0; i < to.length; i++) {
                    toAddress[i] = new InternetAddress(to[i]);
                }

                for (int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                }

                message.setSubject(subject);
                message.setText(body);
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (AddressException ae) {
                ae.printStackTrace();
            } catch (MessagingException me) {
                me.printStackTrace();
            }
        } else {
            System.out.println("Existe usuário sem e-mail cadastrado, Atualize para realizar a notificação");
        }
    
    }

    private boolean verificarUsuarioEmail(ObservableList<Usuario> listaUsuario) {
        for (Usuario u : listaUsuario) {
            if (u.getEmail() == null || u.getEmail().equals("")) {
                return false;
            }
        }
        return true;
    }

    private String[] createCC(ObservableList<Usuario> listaUsuario) {
        String[] to = new String[listaUsuario.size()];
        int i = 0;
        if (verificarUsuarioEmail(listaUsuario)) {
            for (Usuario u : listaUsuario) {
                if (u.getEmail() != null) {
                    to[i++] = u.getEmail();
                } else {
                    to[i++] = "";
                }
            }
            return to;
        } else {
            return null;
        }
    }

}
