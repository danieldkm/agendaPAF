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
@XmlRootElement(name = "Categorias")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Categorias", propOrder = {
    "categorias"
})
public class Categorias {

    @XmlElement(name = "Categorias", required = true)
    protected List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }
}
