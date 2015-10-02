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
@Table(name = "contato")
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c"),
    @NamedQuery(name = "Contato.findByID", query = "SELECT c FROM Contato c WHERE c.id = :id"),
    @NamedQuery(name = "Contato.findByIDEmpresa", query = "SELECT c FROM Contato c WHERE c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Contato.findLast", query = "SELECT c FROM Contato c ORDER BY c.id DESC")})
public class Contato implements Externalizable {

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

    private StringProperty nome = new SimpleStringProperty(this, "nome");

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    private StringProperty email = new SimpleStringProperty(this, "email");

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    private StringProperty cpf = new SimpleStringProperty(this, "cpf");

    @Column(length = 14, nullable = true)
    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    private StringProperty responsavelTeste = new SimpleStringProperty(this, "responsavelTeste");

    public String getResponsavelTeste() {
        return responsavelTeste.get();
    }

    public void setResponsavelTeste(String responsavelTeste) {
        this.responsavelTeste.set(responsavelTeste);
    }

    public StringProperty responsavelTesteProperty() {
        return responsavelTeste;
    }

    private StringProperty rg = new SimpleStringProperty(this, "rg");

    @Column(length = 12, nullable = true)
    public String getRg() {
        return rg.get();
    }

    public void setRg(String rg) {
        this.rg.set(rg);
    }

    public StringProperty rgProperty() {
        return rg;
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
        out.writeObject(getNome());
        out.writeObject(getEmail());
        out.writeObject(getCpf());
        out.writeObject(getResponsavelTeste());
        out.writeObject(getRg());
        out.writeObject(getSelecionado());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdEmpresa((Empresa) in.readObject());
        setNome((String) in.readObject());
        setEmail((String) in.readObject());
        setCpf((String) in.readObject());
        setResponsavelTeste((String) in.readObject());
        setRg((String) in.readObject());
        setSelecionado((Boolean) in.readObject());
    }

    @Override
    public String toString() {
        return nome.get() + " " + email.get() + " " + cpf.get() + " " + responsavelTeste.get() + " " + rg.get();
    }

}
