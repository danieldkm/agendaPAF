package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import com.unifil.agendapaf.util.LocalDateAdapter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Convert;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Usuario")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByID", query = "SELECT u FROM Usuario u where u.id = :id"),
    @NamedQuery(name = "Usuario.Login", query = "SELECT u FROM Usuario u where u.nomeLogin = :login and u.senha = :senha")})
public class Usuario implements Externalizable {

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

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    private StringProperty nomeLogin = new SimpleStringProperty(this, "nomeLogin");

    public String getNomeLogin() {
        return nomeLogin.get();
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin.set(nomeLogin);
    }

    public StringProperty nomeLoginProperty() {
        return nomeLogin;
    }

    private StringProperty senha = new SimpleStringProperty(this, "senha");

    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataCadastro = new SimpleObjectProperty<>();

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDate> dataCadastroProperty() {
        return dataCadastro;
    }

    private StringProperty tipo = new SimpleStringProperty(this, "tipo");

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public StringProperty tipoProperty() {
        return tipo;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getNome());
        out.writeObject(getSenha());
        out.writeObject(getDataCadastro());
        out.writeObject(getTipo());
        out.writeObject(getEmail());

    }
//    id, nome, login, senha, dataCadastro, tipo, email

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setNome((String) in.readObject());
        setNomeLogin((String) in.readObject());
        setSenha((String) in.readObject());
        setDataCadastro((LocalDate) in.readObject());
        setTipo((String) in.readObject());
        setEmail((String) in.readObject());
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
