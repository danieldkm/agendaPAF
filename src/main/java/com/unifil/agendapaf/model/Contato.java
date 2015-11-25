package com.unifil.agendapaf.model;

import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
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

    public Contato clone() {
        try {
            Contato c = new Contato();
            c.setCpf(getCpf());
            c.setEmail(getEmail());
            c.setId(getId());
            c.setIdEmpresa(getIdEmpresa());
            c.setNome(getNome());
            c.setResponsavelTeste(getResponsavelTeste());
            c.setRg(getRg());
            c.setSelecionado(getSelecionado());
            return c;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
        setSelecionado((Integer) in.readObject());
    }

    public void validate() {
        Mensagem mensagem = new Mensagem(null);
        if (nome.get() == null || nome.get().equals("")) {
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroNomeContato.getTitulo());
            throw new IllegalArgumentException("Nome cannot be null or empty");
        } else if (email.get() == null || email.get().equals("") || !email.get().contains("@") || email.get().split("@").length > 2) {
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroEmailContato.getTitulo());
            throw new IllegalArgumentException("E-mail cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return nome.get() + " " + email.get() + " " + cpf.get() + " " + responsavelTeste.get() + " " + rg.get();
    }

}
