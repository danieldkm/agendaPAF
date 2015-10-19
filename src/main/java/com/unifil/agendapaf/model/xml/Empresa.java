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
@XmlRootElement(name = "Empresa")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Empresa", propOrder = {
    "razaoSocial",
    "nomeFantasia",
    "cnpj",
    "ie",
    "im",
    "versaoER"
})
public class Empresa {

    @XmlElement(name = "RazaoSocial")
    private String razaoSocial;
    @XmlElement(name = "NomeFantasia")
    private String nomeFantasia;
    @XmlElement(name = "CNPJ")
    private String cnpj;
    @XmlElement(name = "IE")
    private String ie;
    @XmlElement(name = "IM")
    private String im;
    @XmlElement(name = "VersaoER")
    private String versaoER;

    public Empresa() {
    }

    public Empresa(String razaoSocial, String nomeFantasia, String cnpj, String ie, String im, String versaoER) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.ie = ie;
        this.im = im;
        this.versaoER = versaoER;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getVersaoER() {
        return versaoER;
    }

    public void setVersaoER(String versaoER) {
        this.versaoER = versaoER;
    }

}
