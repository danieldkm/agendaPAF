
package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerfisRequisitosType", propOrder = {
    "perfilRequisito"
})
public class PerfisRequisitosType {

    @XmlElement(name = "PerfilRequisito", required = true)
    protected List<String> perfilRequisito;

    /**
     * Gets the value of the perfilRequisito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perfilRequisito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerfilRequisito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * @return perfilRequisito
     */
    public List<String> getPerfilRequisito() {
        if (perfilRequisito == null) {
            perfilRequisito = new ArrayList<String>();
        }
        return this.perfilRequisito;
    }

}
