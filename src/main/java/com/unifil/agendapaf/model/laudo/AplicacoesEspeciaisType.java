package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AplicacoesEspeciaisType", propOrder = {
    "aplicacaoEspecial"
})
public class AplicacoesEspeciaisType {

    @XmlElement(name = "AplicacaoEspecial", required = true)
    protected List<String> aplicacaoEspecial;

    /**
     * Gets the value of the aplicacaoEspecial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aplicacaoEspecial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAplicacaoEspecial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * @return apliacaoEspecial
     */
    public List<String> getAplicacaoEspecial() {
        if (aplicacaoEspecial == null) {
            aplicacaoEspecial = new ArrayList<String>();
        }
        return this.aplicacaoEspecial;
    }

}
