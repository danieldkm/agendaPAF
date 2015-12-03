package com.unifil.agendapaf.model.email;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Email")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Email", propOrder = {
    "destinatario",
    "assunto",
    "conteudo",
    "remente"
})
public class Email {

    @XmlElement(name = "destinatario", required = true)
    private String destinatario;
    @XmlElement(name = "assunto", required = true)
    private String assunto;
    @XmlElement(name = "conteudo", required = true)
    private String conteudo;
    @XmlElement(name = "remente", required = true)
    private FerramentaEmail remente;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public FerramentaEmail getRemente() {
        return remente;
    }

    public void setRemente(FerramentaEmail remente) {
        this.remente = remente;
    }

}
