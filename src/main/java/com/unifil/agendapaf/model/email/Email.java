package com.unifil.agendapaf.model.email;

import com.unifil.agendapaf.adapter.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Email")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Email", propOrder = {
    "id",
    "destinatario",
    "assunto",
    "conteudo",
    "remente",
    "data",
    "anexos"
})
public class Email {

    @XmlElement(name = "ID", required = true)
    private Integer id;
    @XmlElement(name = "Destinatario", required = true)
    private String destinatario;
    @XmlElement(name = "Assunto", required = true)
    private String assunto;
    @XmlElement(name = "Conteudo", required = true)
    private String conteudo;
    @XmlElement(name = "Remente", required = true)
    private FerramentaEmail remente;
    @XmlElement(name = "Data", required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime data;
    @XmlElement(name = "Anexos", required = false)
    private Anexos anexos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Anexos getAnexos() {
        if (anexos == null) {
            anexos = new Anexos();
        }
        return anexos;
    }

    public void setAnexos(Anexos anexos) {
        this.anexos = anexos;
    }

}
