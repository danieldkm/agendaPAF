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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Estado")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "estado")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByID", query = "SELECT e FROM Estado e where e.id = :id")})
public class Estado implements Externalizable {

    private LongProperty id = new SimpleLongProperty(this, "id");

    @XmlElement(name = "Id")
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
    private StringProperty nome = new SimpleStringProperty(this, "nome");

    @XmlElement(name = "Nome")
    public String getNome() {
        return nome.get();
    }

    public void setNome(String responsavel) {
        this.nome.set(responsavel);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    private StringProperty uf = new SimpleStringProperty(this, "uf");

    @XmlElement(name = "Uf")
    public String getUf() {
        return uf.get();
    }

    public void setUf(String statusAgenda) {
        this.uf.set(statusAgenda);
    }

    public StringProperty ufProperty() {
        return uf;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getNome());
        out.writeObject(getUf());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setNome((String) in.readObject());
        setUf((String) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return nome.get();
    }

}
