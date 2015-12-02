package com.unifil.agendapaf.exemplos;

import com.unifil.agendapaf.model.aux.FerramentaEmail;
import com.unifil.agendapaf.util.Criptografia;
import com.unifil.agendapaf.util.UtilFile;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author danielmorita
 */
public class CommonsMail {

    public CommonsMail() throws EmailException, MalformedURLException {
        enviaEmailSimples();
//        enviaEmailComAnexo();
//        enviaEmailFormatoHtml();
    }

    /**
     * envia email simples(somente texto)
     *
     * @throws EmailException
     */
    public void enviaEmailSimples() throws EmailException {

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
//        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("danielkeyti@gmail.com", "daniel@morita"));
        email.setSSLOnConnect(true);
        email.setFrom("danielkeyti@gmail.com");//remetente
        email.setSubject("TestMail"); //Assunto
        email.setMsg("This is a test mail ... :-)"); //corpo da mensagem
        email.addTo("danielkeyti@gmail.com"); // destinatario
        email.addTo("danielmorita@hotmail.com");;
        email.send();

    }

    /**
     * envia email com arquivo anexo
     *
     * @throws EmailException
     */
    public void enviaEmailComAnexo() throws EmailException {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("mypictures/john.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("John");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();

//        // cria o anexo 1.  
//        EmailAttachment anexo1 = new EmailAttachment();
//        anexo1.setPath("email/conexaoLocal.json"); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)  
//        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
//        anexo1.setDescription("Exemplo de arquivo anexo");
//        anexo1.setName("conexaoLocal.json");
//
//        // cria o anexo 2.  
//        EmailAttachment anexo2 = new EmailAttachment();
//        anexo2.setPath("email/Marca e modelo.csv"); //caminho do arquivo (RAIZ_PROJETO/teste/teste2.jsp)  
//        anexo2.setDisposition(EmailAttachment.ATTACHMENT);
//        anexo2.setDescription("Exemplo de arquivo anexo");
//        anexo2.setName("Marca e modelo.csv");
//
//        // configura o email  
//        MultiPartEmail email = new MultiPartEmail();
//        email.setHostName("smtp.googlemail.com"); // o servidor SMTP para envio do e-mail  
//        email.addTo("danielkeyti@gmail.com", "danielkeyti"); //destinatário  
//        email.setFrom("danielkeyti@gmail.com", "Eu"); // remetente  
//        email.setSubject("Teste -> Email com anexos"); // assunto do e-mail  
//        email.setMsg("Teste de Email utilizando commons-email"); //conteudo do e-mail  
//        email.setAuthentication("danielkeyti", "daniel@morita");
////        email.setSmtpPort(465);
////        email.setSSL(true);
////        email.setTLS(true);
//
//        // adiciona arquivo(s) anexo(s)  
//        email.attach(anexo1);
//        email.attach(anexo2);
//        // envia o email  
//        email.send();
    }

    /**
     * Envia email no formato HTML
     *
     * @throws EmailException
     * @throws MalformedURLException
     */
    public void enviaEmailFormatoHtml() throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();

        // adiciona uma imagem ao corpo da mensagem e retorna seu id  
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");

        // configura a mensagem para o formato HTML  
        email.setHtmlMsg("<html>Logo do Apache - <img ></html>");

        // configure uma mensagem alternativa caso o servidor não suporte HTML  
        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

        email.setHostName("smtp.googlemail.com"); // o servidor SMTP para envio do e-mail  
        email.addTo("danielkeyti@gmail.com", "danielkeyti"); //destinatário  
        email.setFrom("danielkeyti@gmail.com", "Eu"); // remetente  
        email.setSubject("Teste -> Html Email"); // assunto do e-mail  
        email.setMsg("Teste de Email HTML utilizando commons-email"); //conteudo do e-mail  
        email.setAuthentication("danielkeyti", "daniel@morita");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
//        email.setSSL(true);
//        email.setTLS(true);
        // envia email  
        email.send();
    }

    /**
     * @param args
     * @throws EmailException
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws EmailException, MalformedURLException {
        CommonsMail c = new CommonsMail();
        c.enviaEmailFormatoHtml();
//        new CommonsMail();
//        UtilFile uf = new UtilFile();
//        FerramentaEmail fe = new FerramentaEmail();
//        fe.setHostName("smtp.googlemail.com");
//        fe.setPortaSMTP(465);
//        fe.setEmail("daniel@teste.com");
//        fe.setSenha("qwe123");
//        uf.salvarArquivo(new File("FerramentaEmail.xml"), uf.marshal(fe));

//        System.out.println("criptografia " + uf.criptografar("daniel"));
//        for (int i = 0; i <= 255; i++) {
//            System.out.println(i + " : " + (char) i);
//        }
//        System.out.println("a" + " : " + ((char) "a"));
//        int numeroDigitado = 1;
//        String valorAscII = char(numeroDigitado);  
//        System.out.println("??? " + (char) 123);
//        System.out.println("??? " + (char) 124);
//        System.out.println("??? " + (char) 125);
//        System.out.println("??? " + (char) 126);
//        System.out.println("??? " + (char) 127);
//        System.out.println("??? " + (char) 128);
//        System.out.println("??? " + (char) 129);
//        System.out.println("??? " + (char) 97);
//        char character = 'a';
//        int ascii = (int) character;
//        System.out.println("ascii " + ascii);
//        Criptografia c = new Criptografia();
//        System.out.println(c.cifrarVigenere("123@qwe", "pafecf7326"));
//        System.out.println(c.decifrarVigenere("123@fwj", "pafecf7326"));

//        daniuhh
    }

}
