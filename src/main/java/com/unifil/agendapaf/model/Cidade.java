package com.unifil.agendapaf.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Cidade")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "cidade")
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByID", query = "SELECT c FROM Cidade c where c.id = :id")})
public class Cidade implements Externalizable {

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

    private ObjectProperty<Estado> idEstado = new SimpleObjectProperty<Estado>(this, "idEstado");

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEstado", referencedColumnName = "id")
    public Estado getIdEstado() {
        return idEstado.get();
    }

    public void setIdEstado(Estado idEmpresa) {
        this.idEstado.set(idEmpresa);
    }

    public ObjectProperty<Estado> idEstadoProperty() {
        return idEstado;
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
        out.writeObject(getIdEstado());
        out.writeObject(getUf());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setNome((String) in.readObject());
        setIdEstado((Estado) in.readObject());
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
