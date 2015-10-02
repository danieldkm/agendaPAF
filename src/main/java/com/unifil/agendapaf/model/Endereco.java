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

/**
 *
 * @author danielmorita
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "endereco")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByID", query = "SELECT e FROM Endereco e where e.id = :id"),
    @NamedQuery(name = "Endereco.findLast", query = "SELECT e FROM Endereco e ORDER BY e.id DESC")})
public class Endereco implements Externalizable {

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

    private ObjectProperty<Cidade> idCidade = new SimpleObjectProperty<Cidade>(this, "id");

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCidade", referencedColumnName = "id")
    public Cidade getIdCidade() {
        return idCidade.get();
    }

    public void setIdCidade(Cidade cidade) {
        this.idCidade.set(cidade);
    }

    public ObjectProperty<Cidade> idCidadeProperty() {
        return idCidade;
    }

    private StringProperty logradouro = new SimpleStringProperty(this, "logradouro");

    public String getLogradouro() {
        return logradouro.get();
    }

    public void setLogradouro(String logradouro) {
        this.logradouro.set(logradouro);
    }

    public StringProperty logradouroProperty() {
        return logradouro;
    }

    private StringProperty numero = new SimpleStringProperty(this, "Numero");

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    private StringProperty complemento = new SimpleStringProperty(this, "Numero");

    public String getComplemento() {
        return complemento.get();
    }

    public void setComplemento(String complemento) {
        this.complemento.set(complemento);
    }

    public StringProperty complementoProperty() {
        return complemento;
    }

    private StringProperty bairro = new SimpleStringProperty(this, "bairro");

    public String getBairro() {
        return bairro.get();
    }

    public void setBairro(String bairro) {
        this.bairro.set(bairro);
    }

    public StringProperty bairroProperty() {
        return bairro;
    }

    private StringProperty cep = new SimpleStringProperty(this, "cep");

    public String getCep() {
        return cep.get();
    }

    public void setCep(String cep) {
        this.cep.set(cep);
    }

    public StringProperty cepProperty() {
        return cep;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getIdCidade());
        out.writeObject(getLogradouro());
        out.writeObject(getNumero());
        out.writeObject(getComplemento());
        out.writeObject(getBairro());
        out.writeObject(getCep());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdCidade((Cidade) in.readObject());
        setLogradouro((String) in.readObject());
        setNumero((String) in.readObject());
        setComplemento((String) in.readObject());
        setBairro((String) in.readObject());
        setCep((String) in.readObject());
    }

}