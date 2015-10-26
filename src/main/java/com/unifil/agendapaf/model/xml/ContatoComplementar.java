package com.unifil.agendapaf.model.xml;

import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Telefone;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
@XmlRootElement(name = "ContatoComplementar")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ContatoComplementar extends Contato {

    private final ObjectProperty<Telefone> telefone;

    public ContatoComplementar() {
        this.telefone = new SimpleObjectProperty<Telefone>();
    }

    @XmlElement(name = "Telefone")
    public Telefone getTelefone() {
        return telefone.get();
    }

    public void setTelefone(Telefone telefone) {
        this.telefone.set(telefone);
    }

    public ObjectProperty<Telefone> telefoneProperty() {
        return telefone;
    }

}
