

package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtcType", propOrder = {
    "razaoSocial",
    "cnpj",
    "ie",
    "endereco",
    "periodoAnalise",
    "versaoEspecificacaoRequisitos"
})
public class OtcType {

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
    @XmlElement(name = "PeriodoAnalise", required = true)
    protected PeriodoAnaliseType periodoAnalise;
    @XmlElement(name = "VersaoEspecificacaoRequisitos", required = true)
    protected String versaoEspecificacaoRequisitos;

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
     * Gets the value of the periodoAnalise property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoAnaliseType }
     *     
     */
    public PeriodoAnaliseType getPeriodoAnalise() {
        return periodoAnalise;
    }

    /**
     * Sets the value of the periodoAnalise property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoAnaliseType }
     *     
     */
    public void setPeriodoAnalise(PeriodoAnaliseType value) {
        this.periodoAnalise = value;
    }

    /**
     * Gets the value of the versaoEspecificacaoRequisitos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersaoEspecificacaoRequisitos() {
        return versaoEspecificacaoRequisitos;
    }

    /**
     * Sets the value of the versaoEspecificacaoRequisitos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersaoEspecificacaoRequisitos(String value) {
        this.versaoEspecificacaoRequisitos = value;
    }

}
