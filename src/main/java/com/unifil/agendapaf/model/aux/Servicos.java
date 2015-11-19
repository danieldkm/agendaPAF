package com.unifil.agendapaf.model.aux;

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
@XmlRootElement(name = "Servicos")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Servicos", propOrder = {
    "servicos"
})
public class Servicos {

    @XmlElement(name = "Servicos", required = true)
    protected List<Servico> servicos;

    public List<Servico> getServicos() {
        if (servicos == null) {
            servicos = new ArrayList<>();
        }
        return this.servicos;
    }
}
