
package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NaoConformidadeType", propOrder = {
    "requisito",
    "item",
    "descricao"
})
public class NaoConformidadeType {

    @XmlElement(name = "Requisito", required = true)
    protected String requisito;
    @XmlElement(name = "Item", required = true)
    protected String item;
    @XmlElement(name = "Descricao", required = true)
    protected String descricao;

    /**
     * Gets the value of the requisito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequisito() {
        return requisito;
    }

    /**
     * Sets the value of the requisito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequisito(String value) {
        this.requisito = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItem(String value) {
        this.item = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

}
