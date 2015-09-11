
package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoteiroAnaliseType", propOrder = {
    "versaoRoteiro",
    "mes",
    "ano"
})
public class RoteiroAnaliseType {

    @XmlElement(name = "VersaoRoteiro", required = true)
    protected String versaoRoteiro;
    @XmlElement(name = "Mes", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mes;
    @XmlElement(name = "Ano", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ano;

    /**
     * Gets the value of the versaoRoteiro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersaoRoteiro() {
        return versaoRoteiro;
    }

    /**
     * Sets the value of the versaoRoteiro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersaoRoteiro(String value) {
        this.versaoRoteiro = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMes(String value) {
        this.mes = value;
    }

    /**
     * Gets the value of the ano property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAno() {
        return ano;
    }

    /**
     * Sets the value of the ano property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAno(String value) {
        this.ano = value;
    }

}
