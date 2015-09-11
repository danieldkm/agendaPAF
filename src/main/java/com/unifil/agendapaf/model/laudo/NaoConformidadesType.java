
package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NaoConformidadesType", propOrder = {
    "naoConformidade"
})
public class NaoConformidadesType {

    @XmlElement(name = "NaoConformidade")
    protected List<NaoConformidadeType> naoConformidade;

    /**
     * Gets the value of the naoConformidade property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the naoConformidade property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNaoConformidade().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NaoConformidadeType }
     * 
     * @return naoConformidade
     */
    public List<NaoConformidadeType> getNaoConformidade() {
        if (naoConformidade == null) {
            naoConformidade = new ArrayList<NaoConformidadeType>();
        }
        return this.naoConformidade;
    }

}
