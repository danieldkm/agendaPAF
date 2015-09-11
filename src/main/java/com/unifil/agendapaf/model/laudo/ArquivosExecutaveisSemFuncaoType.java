package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArquivosExecutaveisSemFuncaoType", propOrder = {
    "arquivoExecutavel"
})
public class ArquivosExecutaveisSemFuncaoType {

    @XmlElement(name = "ArquivoExecutavel", required = true)
    protected List<ArquivoExecutavelSemFuncaoType> arquivoExecutavel;

    /**
     * Gets the value of the arquivoExecutavel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arquivoExecutavel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArquivoExecutavel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArquivoExecutavelSemFuncaoType }
     * 
     * @return arquivoExecutavel
     */
    public List<ArquivoExecutavelSemFuncaoType> getArquivoExecutavel() {
        if (arquivoExecutavel == null) {
            arquivoExecutavel = new ArrayList<ArquivoExecutavelSemFuncaoType>();
        }
        return this.arquivoExecutavel;
    }

}
