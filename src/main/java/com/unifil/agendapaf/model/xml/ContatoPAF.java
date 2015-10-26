package com.unifil.agendapaf.model.xml;

import com.unifil.agendapaf.model.Contato;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "ContatoPAF")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ContatoPAF extends Contato {

    private final StringProperty cargo;

    public ContatoPAF() {
        this.cargo = new SimpleStringProperty();
    }

    @XmlElement(name = "Cargo")
    public String getCargo() {
        return cargo.get();
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    public StringProperty cargoProperty() {
        return cargo;
    }

}
