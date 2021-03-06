package com.unifil.agendapaf.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Telefone")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "telefone")
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByID", query = "SELECT t FROM Telefone t where t.id = :id"),
    @NamedQuery(name = "Telefone.findByIDEmpresa", query = "SELECT t FROM Telefone t WHERE t.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Telefone.findLast", query = "SELECT t FROM Telefone t ORDER BY t.id DESC")})
public class Telefone implements Externalizable {

    public Telefone clone() {
        try {
            Telefone t = new Telefone();
            t.setCelular(getCelular());
            t.setFax(getFax());
            t.setFixo(getFixo());
            t.setId(getId());
            t.setIdEmpresa(getIdEmpresa());
            t.setSelecionado(getSelecionado());
            return t;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
    private ObjectProperty<Empresa> idEmpresa = new SimpleObjectProperty<Empresa>(this, "id");

    @XmlElement(name = "IdEmpresa")
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

    public StringProperty fixoProperty() {
        return fixo;
    }

    private StringProperty fax = new SimpleStringProperty(this, "fax");

    @XmlElement(name = "Fax")
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

    @XmlElement(name = "Celular")
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

    private IntegerProperty selecionado = new SimpleIntegerProperty(this, "selecionado");

    public Integer getSelecionado() {
        return selecionado.get();
    }

    public Boolean selecionadoBoolean() {
        if (getSelecionado() == 1) {
            return true;
        }
        return false;
    }

    public void setSelecionado(Integer selecionado) {
        this.selecionado.set(selecionado);
    }

    public IntegerProperty selecionadoProperty() {
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
        setSelecionado((Integer) in.readObject());
    }

    @Override
    public String toString() {
        return fixo.get() + " " + fax.get() + " " + celular.get();
    }

}
