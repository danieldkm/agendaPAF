package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SistemasGestaoType", propOrder = {
    "sistemaGestao"
})
public class SistemasGestaoType {

    @XmlElement(name = "SistemaGestao")
    protected List<SistemaGestaoType> sistemaGestao;

    /**
     * Gets the value of the sistemaGestao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sistemaGestao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSistemaGestao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SistemaGestaoType }
     * 
     * @return sistemaGestao
     */
    public List<SistemaGestaoType> getSistemaGestao() {
        if (sistemaGestao == null) {
            sistemaGestao = new ArrayList<SistemaGestaoType>();
        }
        return this.sistemaGestao;
    }

}
