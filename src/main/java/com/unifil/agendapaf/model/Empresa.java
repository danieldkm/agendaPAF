package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import com.unifil.agendapaf.util.LocalDateAdapter;
import com.unifil.agendapaf.util.UtilConverter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
//id, descricao, nomeContato, telefone, observacao, dataCadastro, email, 
//estado, cidade, nomeFantasia, endereco, bairro, cep, fax, celular, cnpj, 
//IE, IM, cpf, Respo Teste, codInterno, categoria

@XmlRootElement(name = "Empresa")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByID", query = "SELECT e FROM Empresa e where e.id = :id"),
    @NamedQuery(name = "Empresa.findLast", query = "SELECT e FROM Empresa e ORDER BY e.id DESC")})
public class Empresa implements Externalizable {

    public Empresa clone() {
        try {
            Empresa e = new Empresa();
            e.setCategoria(getCategoria());
            e.setCnpj(getCnpj());
            e.setDataCadastro(getDataCadastro());
            e.setDescricao(getDescricao());
            e.setId(getId());
            e.setInscricaoEstadual(getInscricaoEstadual());
            e.setInscricaoMunicipal(getInscricaoMunicipal());
            e.setNomeFantasia(getNomeFantasia());
            e.setObservacao(getObservacao());
            return e;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Empresa() {
        descricao.set("");
        observacao.set("");
        nomeFantasia.set("");
        cnpj.set("");
        inscricaoEstadual.set("");
        inscricaoMunicipal.set("");
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

    private StringProperty descricao = new SimpleStringProperty(this, "descricao");

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    private StringProperty observacao = new SimpleStringProperty(this, "observacao");

    public String getObservacao() {
        return observacao.get();
    }

    public void setObservacao(String observacao) {
        this.observacao.set(observacao);
    }

    public StringProperty observacaoProperty() {
        return observacao;
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

    private StringProperty nomeFantasia = new SimpleStringProperty(this, "nomeFantasia");

    @XmlElement(name = "NomeFantasia")
    public String getNomeFantasia() {
        return nomeFantasia.get();
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia.set(nomeFantasia);
    }

    public StringProperty nomeFantasiaProperty() {
        return nomeFantasia;
    }

    private StringProperty cnpj = new SimpleStringProperty(this, "cnpj");

    @Column(length = 18, nullable = false)
    public String getCnpj() {
        return cnpj.get();
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }

    public StringProperty cnpjProperty() {
        return cnpj;
    }

    private StringProperty inscricaoEstadual = new SimpleStringProperty(this, "inscricaoEstadual");

    public String getInscricaoEstadual() {
        return inscricaoEstadual.get();
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual.set(inscricaoEstadual);
    }

    public StringProperty inscricaoEstadualProperty() {
        return inscricaoEstadual;
    }

    private StringProperty inscricaoMunicipal = new SimpleStringProperty(this, "inscricaoMunicipal");

    @XmlElement(name = "InscricaoMunicipal")
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal.get();
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal.set(inscricaoMunicipal);
    }

    public StringProperty inscricaoMunicipalProperty() {
        return inscricaoMunicipal;
    }

    private StringProperty categoria = new SimpleStringProperty(this, "categoria");

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getDescricao());
        out.writeObject(getObservacao());
        out.writeObject(getDataCadastro());
        out.writeObject(getNomeFantasia());
        out.writeObject(getCnpj());
        out.writeObject(getInscricaoEstadual());
        out.writeObject(getInscricaoMunicipal());
        out.writeObject(getCategoria());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setDescricao((String) in.readObject());
        setObservacao((String) in.readObject());
        setDataCadastro((LocalDate) in.readObject());
        setNomeFantasia((String) in.readObject());
        setCnpj((String) in.readObject());
        setInscricaoEstadual((String) in.readObject());
        setInscricaoMunicipal((String) in.readObject());
        setCategoria((String) in.readObject());
    }

    public void validate() {
        if (inscricaoEstadual.get().toLowerCase().contains("isento")) {
            inscricaoEstadual.set("Isento");
        }
    }

    @Override
    public String toString() {
        return descricao.get();
    }

//    idEndereco.get().getIdCidade().getNome() + " "
//    idEndereco.get().getIdCidade().getUf() + " "
//    + idContato.get() + " "
//    idTelefone.get().getFixo() + " " + 
//    + idContato.get().getEmail() + " " 
//    + getIdEndereco().getLogradouro() + " " 
//    + getIdEndereco().getBairro() + " "
//    + getIdEndereco().getCep() + " " 
//    + idTelefone.get().getFax() + " "
//    + idTelefone.get().getCelular() + " " 
//    + idContato.get().getCpf() + " " 
//    + idContato.get().getResponsavelTeste() + " "
//     + " " + idContato.get().getRg()
    public String toString2() {
        return id.get() + " "
                + descricao.get() + " "
                + observacao.get() + " "
                + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(dataCadastro.get()), "dd-MM-yyyy") + " "
                + nomeFantasia.get() + " "
                + cnpj.get() + " " + inscricaoEstadual.get() + " "
                + inscricaoMunicipal.get() + " "
                + categoria.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        return true;
    }

}
