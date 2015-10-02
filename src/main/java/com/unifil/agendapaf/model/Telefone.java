package com.unifil.agendapaf.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Telefone.findByIDEmpresa", query = "SELECT t FROM Telefone t WHERE t.idEmpresa = :idEmpresa"),
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
    private ObjectProperty<Empresa> idEmpresa = new SimpleObjectProperty<Empresa>(this, "id");

    @ManyToOne(optional = true)
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    public Empresa getIdEmpresa() {
        return idEmpresa.get();
    }

    public void setIdEmpresa(Empresa empresa) {
        this.idEmpresa.set(empresa);
    }

    public ObjectProperty<Empresa> idEmpresaProperty() {
        return idEmpresa;
    }

    private StringProperty fixo = new SimpleStringProperty(this, "fixo");

    @Column(length = 14, nullable = true)
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

    @Column(length = 14, nullable = true)
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

    @Column(length = 14, nullable = true)
    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public StringProperty celularProperty() {
        return celular;
    }

    private BooleanProperty selecionado = new SimpleBooleanProperty(this, "selecionado");

    public Boolean getSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado.set(selecionado);
    }

    public BooleanProperty selecionadoProperty() {
        return selecionado;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getIdEmpresa());
        out.writeObject(getFixo());
        out.writeObject(getFax());
        out.writeObject(getCelular());
        out.writeObject(getSelecionado());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdEmpresa((Empresa) in.readObject());
        setFixo((String) in.readObject());
        setFax((String) in.readObject());
        setCelular((String) in.readObject());
        setSelecionado((Boolean) in.readObject());
    }

    @Override
    public String toString() {
        return fixo.get() + " " + fax.get() + " " + celular.get();
    }

}
