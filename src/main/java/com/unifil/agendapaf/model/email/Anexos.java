package com.unifil.agendapaf.model.email;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Anexos")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Anexos", propOrder = {
    "anexos"
})
public class Anexos {

    @XmlElement(name = "Anexo", required = true)
    private List<String> anexos;

    public List<String> getAnexos() {
        if (anexos == null) {
            anexos = new ArrayList<String>();
        }
        return this.anexos;
    }

}
