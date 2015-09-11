package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SistemasPedNfeType", propOrder = {
    "sistemaPedNfe"
})
public class SistemasPedNfeType {

    @XmlElement(name = "SistemaPedNfe")
    protected List<SistemaPedNfeType> sistemaPedNfe;

    /**
     * Gets the value of the sistemaPedNfe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sistemaPedNfe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSistemaPedNfe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SistemaPedNfeType }
     * 
     * @return sistemaPedNfe
     */
    public List<SistemaPedNfeType> getSistemaPedNfe() {
        if (sistemaPedNfe == null) {
            sistemaPedNfe = new ArrayList<SistemaPedNfeType>();
        }
        return this.sistemaPedNfe;
    }

}
