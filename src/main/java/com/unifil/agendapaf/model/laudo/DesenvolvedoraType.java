package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesenvolvedoraType", propOrder = {
    "razaoSocial",
    "cnpj",
    "ie",
    "endereco",
    "contato",
    "responsavelAcompanhamentoTestes"
})
public class DesenvolvedoraType {

    @XmlElement(name = "RazaoSocial", required = true)
    protected String razaoSocial;
    @XmlElement(name = "Cnpj", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String cnpj;
    @XmlElement(name = "Ie", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ie;
    @XmlElement(name = "Endereco", required = true)
    protected EnderecoType endereco;
    @XmlElement(name = "Contato", required = true)
    protected ContatoType contato;
    @XmlElement(name = "ResponsavelAcompanhamentoTestes", required = true)
    protected String responsavelAcompanhamentoTestes;

    /**
     * Gets the value of the razaoSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Sets the value of the razaoSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazaoSocial(String value) {
        this.razaoSocial = value;
    }

    /**
     * Gets the value of the cnpj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets the value of the cnpj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpj(String value) {
        this.cnpj = value;
    }

    /**
     * Gets the value of the ie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIe() {
        return ie;
    }

    /**
     * Sets the value of the ie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIe(String value) {
        this.ie = value;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * @return
     *     possible object is
     *     {@link EnderecoType }
     *     
     */
    public EnderecoType getEndereco() {
        return endereco;
    }

    /**
     * Sets the value of the endereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnderecoType }
     *     
     */
    public void setEndereco(EnderecoType value) {
        this.endereco = value;
    }

    /**
     * Gets the value of the contato property.
     * 
     * @return
     *     possible object is
     *     {@link ContatoType }
     *     
     */
    public ContatoType getContato() {
        return contato;
    }

    /**
     * Sets the value of the contato property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContatoType }
     *     
     */
    public void setContato(ContatoType value) {
        this.contato = value;
    }

    /**
     * Gets the value of the responsavelAcompanhamentoTestes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsavelAcompanhamentoTestes() {
        return responsavelAcompanhamentoTestes;
    }

    /**
     * Sets the value of the responsavelAcompanhamentoTestes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsavelAcompanhamentoTestes(String value) {
        this.responsavelAcompanhamentoTestes = value;
    }

}
