package com.unifil.agendapaf.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author danielmorita
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "telefone")
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByID", query = "SELECT t FROM Telefone t where t.id = :id"),
    @NamedQuery(name = "Telefone.findLast", query = "SELECT t FROM Telefone t ORDER BY t.id DESC")})
public class Telefone implements Externalizable {

    private LongProperty id = new SimpleLongProperty(this, "id");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    private StringProperty fixo = new SimpleStringProperty(this, "fixo");

    public String getFixo() {
        return fixo.get();
    }

    public void setFixo(String fixo) {
        this.fixo.set(fixo);
    }

    public StringProperty fixotelefoneProperty() {
        return fixo;
    }

    private StringProperty fax = new SimpleStringProperty(this, "fax");

    public String getFax() {
        return fax.get();
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }

    public StringProperty faxProperty() {
        return fax;
    }

    private StringProperty celular = new SimpleStringProperty(this, "celular");

    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public StringProperty celularProperty() {
        return celular;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getFixo());
        out.writeObject(getFax());
        out.writeObject(getCelular());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setFixo((String) in.readObject());
        setFax((String) in.readObject());
        setCelular((String) in.readObject());
    }

}
