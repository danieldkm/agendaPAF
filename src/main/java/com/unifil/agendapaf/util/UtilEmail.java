package com.unifil.agendapaf.util;

import java.util.Collection;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author danielmorita
 */
public class UtilEmail {

    /**
     * envia email simples(somente texto)
     *
     * @param destinatario
     * @param mensagem
     * @param assunto
     */
    public void enviaEmailSimples(Collection<String> destinatario, String mensagem, String assunto) {
        try {

            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
//        email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("danielkeyti@gmail.com", "daniel@morita"));
            email.setSSLOnConnect(true);
            email.setFrom("danielkeyti@gmail.com");//remetente
            email.setSubject(assunto); //Assunto
            email.setMsg(mensagem); //corpo da mensagem
            for (String dest : destinatario) {
                email.addTo(dest);
            }
//        email.addTo("danielkeyti@gmail.com"); // destinatario
//        email.addTo("danielmorita@hotmail.com");;
            email.send();
        } catch (EmailException me) {
            me.printStackTrace();
        }

    }

}
