package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArquivosOutrosType", propOrder = {
    "arquivoOutro"
})
public class ArquivosOutrosType {

    @XmlElement(name = "ArquivoOutro")
    protected List<ArquivoOutroType> arquivoOutro;

    /**
     * Gets the value of the arquivoOutro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arquivoOutro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArquivoOutro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArquivoOutroType }
     * 
     * @return arquivoOutro
     */
    public List<ArquivoOutroType> getArquivoOutro() {
        if (arquivoOutro == null) {
            arquivoOutro = new ArrayList<ArquivoOutroType>();
        }
        return this.arquivoOutro;
    }

}
