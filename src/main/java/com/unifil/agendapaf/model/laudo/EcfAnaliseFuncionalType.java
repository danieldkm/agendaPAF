
package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EcfAnaliseFuncionalType", propOrder = {
    "marcaModelo"
})
public class EcfAnaliseFuncionalType {

    @XmlElement(name = "MarcaModelo", required = true)
    protected List<MarcaModeloType> marcaModelo;

    /**
     * Gets the value of the marcaModelo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marcaModelo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarcaModelo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarcaModeloType }
     * 
     * @return marcaModelo
     */
    public List<MarcaModeloType> getMarcaModelo() {
        if (marcaModelo == null) {
            marcaModelo = new ArrayList<MarcaModeloType>();
        }
        return this.marcaModelo;
    }

}
