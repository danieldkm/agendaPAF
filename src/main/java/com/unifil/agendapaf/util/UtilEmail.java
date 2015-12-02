package com.unifil.agendapaf.util;

import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.aux.FerramentaEmail;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.ObservableList;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 *
 * @author danielmorita
 */
public class UtilEmail {

//    private static HtmlEmail email;
    private Mensagem mensagem;
    private ArrayList<EmailAttachment> anexos = new ArrayList<>();

    /**
     * instanciar HtmlEmail
     *
     */
    public UtilEmail() {
        try {
            mensagem = new Mensagem(null);
//            email = new HtmlEmail();
//            email.setHostName(ferramentaEmail.getHostName());
//            email.setSmtpPort(ferramentaEmail.getPortaSMTP());
//            email.setAuthentication(ferramentaEmail.getEmail(), ferramentaEmail.getSenha());
//            email.setFrom(ferramentaEmail.getEmail(), "PAF-ECF");
//            email.setAuthentication("danielkeyti", "daniel@morita");
        } catch (Exception e) {
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao criar e-mail", e);
            e.printStackTrace();
        }
    }

//    /**
//     * Setar todos os destinatarios
//     *
//     * @param para
//     */
//    public void setDestinatario(ObservableList<Object> para) {
//        try {
//            for (Object o : para) {
//                if (o instanceof Usuario) {
//                    Usuario u = (Usuario) o;
//                    email.addTo(u.getEmail(), u.getNome());
//                } else if (o instanceof Empresa) {
//                    Empresa e = (Empresa) o;
//                    for (Contato c : StaticLista.getListaGlobalContato()) {
//                        if (c.getIdEmpresa().getId().equals(e.getId())) {
//                            email.addTo(c.getEmail(), c.getNome());
//                        }
//                    }
//                } else {
//                    String s = (String) o;
//                    System.out.println("DESTINATARIO " + s);
//                    email.addTo(s);
//                }
//            }
//        } catch (Exception e) {
//            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao setar o destinatario", e);
//            e.printStackTrace();
//        }
//    }
//    public void setMensagem(String html) {
//        try {
//            // set the html message
//            email.setHtmlMsg(html);
//            // set the alternative message
//            email.setTextMsg("Your email client does not support HTML messages");
//        } catch (EmailException ex) {
//            Logger.getLogger(UtilEmail.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    /**
//     * Setar o assunto
//     *
//     * @param assunto
//     */
//    public void setAssunto(String assunto) {
//        email.setSubject(assunto);
//    }
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

    /**
     * Anexar arquivo
     *
     * @param caminhoArquivo caminho absoluto do arquivo
     */
    public void anexar(String caminhoArquivo) {
        try {
            EmailAttachment anexar = new EmailAttachment();
            anexar.setPath(caminhoArquivo);
            anexar.setDisposition(EmailAttachment.ATTACHMENT);
//        anexar.setDescription("Picture of John");
//        anexar.setName("John");
            anexos.add(anexar);
        } catch (Exception e) {
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao anexar", e);
            e.printStackTrace();
        }
    }

    /**
     * enviar email em formato html
     *
     * @param ferramentaEmail
     * @param html
     * @param para
     * @param assunto
     */
    public void enviarEmail(FerramentaEmail ferramentaEmail, String html, ObservableList<Object> para, String assunto) {
        try {
            System.out.println("ferramentaEmail " + ferramentaEmail);
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(new File("").toURI().toURL()));
//            // adiciona uma imagem ao corpo da mensagem e retorna seu id  
//            URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//            String cid = email.embed(url, "Apache logo");
            // configura a mensagem para o formato HTML  
            email.setHtmlMsg(html);
            // configure uma mensagem alternativa caso o servidor não suporte HTML  
            email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

            email.setHostName(ferramentaEmail.getHostName()); // o servidor SMTP para envio do e-mail  
            for (Object o : para) {
                if (o instanceof Usuario) {
                    Usuario u = (Usuario) o;
                    email.addTo(u.getEmail(), u.getNome());
                } else if (o instanceof Empresa) {
                    Empresa e = (Empresa) o;
                    for (Contato c : StaticLista.getListaGlobalContato()) {
                        if (c.getIdEmpresa().getId().equals(e.getId())) {
                            email.addTo(c.getEmail(), c.getNome());
                        }
                    }
                } else {
                    String s = (String) o;
//                    System.out.println("DESTINATARIO " + s);
                    email.addTo(s);
                }
            }
            email.setFrom(ferramentaEmail.getEmail(), "PAF-ECF"); // remetente  
            email.setSubject(assunto); // assunto do e-mail  
//            email.setMsg("???"); //conteudo do e-mail  
            System.out.println("ferramentaEmail.getEmail().substring(0, ferramentaEmail.getEmail().indexOf(\"@\")) " + ferramentaEmail.getEmail().substring(0, ferramentaEmail.getEmail().indexOf("@")));
            Criptografia cri = new Criptografia();
            email.setAuthentication(ferramentaEmail.getEmail(), cri.decifrarVigenere(ferramentaEmail.getSenha()));
            email.setSmtpPort(ferramentaEmail.getPortaSMTP());
            email.setSSLOnConnect(true);
//        email.setSSL(true);
//        email.setTLS(true);

            if (anexos.size() > 0) {
                for (EmailAttachment anexo : anexos) {
                    email.attach(anexo);
                }
            }
            // envia email  
            email.send();
        } catch (Exception e) {
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao criar e-mail", e);
            e.printStackTrace();
        }
    }

}
