package com.unifil.agendapaf.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Telefone")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Telefone", propOrder = {
    "fixo",
    "celular",
    "fax"
})
public class Telefone {

    @XmlElement(name = "Fixo")
    private String fixo;
    @XmlElement(name = "Celular")
    private String celular;
    @XmlElement(name = "FAX")
    private String fax;

    public Telefone() {
    }

    public Telefone(String fixo, String celular, String fax) {
        this.fixo = fixo;
        this.celular = celular;
        this.fax = fax;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
