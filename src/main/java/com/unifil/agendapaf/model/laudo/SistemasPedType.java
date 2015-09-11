package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SistemasPedType", propOrder = {
    "sistemaPed"
})
public class SistemasPedType {

    @XmlElement(name = "SistemaPed")
    protected List<SistemaPedType> sistemaPed;

    /**
     * Gets the value of the sistemaPed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sistemaPed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSistemaPed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SistemaPedType }
     * 
     * @return sistemaPed
     */
    public List<SistemaPedType> getSistemaPed() {
        if (sistemaPed == null) {
            sistemaPed = new ArrayList<SistemaPedType>();
        }
        return this.sistemaPed;
    }

}
