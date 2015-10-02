package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import com.unifil.agendapaf.util.UtilConverter;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
//id, descricao, nomeContato, telefone, observacao, dataCadastro, email, 
//estado, cidade, nomeFantasia, endereco, bairro, cep, fax, celular, cnpj, 
//IE, IM, cpf, Respo Teste, codInterno, categoria

@XmlRootElement(name = "Empresa")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Empresa", propOrder = {
    "id",
    "descricao",
    "idContato",
    "idTelefone", "observacao", "dataCadastro", "nomeFantasia", "idEndereco",
    "cnpj", "inscricaoEstadual", "inscricaoMunicipal", "categoria"
})
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByID", query = "SELECT e FROM Empresa e where e.id = :id")})
public class Empresa implements Externalizable {

    @XmlElement(name = "Id")
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

    @XmlElement(name = "Descricao")
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

    @XmlElement(name = "IdContato")
    private ObjectProperty<Contato> idContato = new SimpleObjectProperty<Contato>(this, "id");

    @ManyToOne(optional = false)
    @JoinColumn(name = "idContato", referencedColumnName = "id")
    public Contato getIdContato() {
        return idContato.get();
    }

    public void setIdContato(Contato contato) {
        this.idContato.set(contato);
    }

    public ObjectProperty<Contato> idContatoProperty() {
        return idContato;
    }

    @XmlElement(name = "IdTelefone")
    private ObjectProperty<Telefone> idTelefone = new SimpleObjectProperty<Telefone>(this, "id");

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTelefone", referencedColumnName = "id")
    public Telefone getIdTelefone() {
        return idTelefone.get();
    }

    public void setIdTelefone(Telefone telefone) {
        this.idTelefone.set(telefone);
    }

    public ObjectProperty<Telefone> idTelefoneProperty() {
        return idTelefone;
    }

    @XmlElement(name = "Observacao")
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

    @XmlElement(name = "DataCadastro")
    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataCadastro = new SimpleObjectProperty<>();

    public LocalDate getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDate> dataCadastroProperty() {
        return dataCadastro;
    }

    @XmlElement(name = "NomeFantasia")
    private StringProperty nomeFantasia = new SimpleStringProperty(this, "nomeFantasia");

    public String getNomeFantasia() {
        return nomeFantasia.get();
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia.set(nomeFantasia);
    }

    public StringProperty nomeFantasiaProperty() {
        return nomeFantasia;
    }

    @XmlElement(name = "IdEndereco")
    private ObjectProperty<Endereco> idEndereco = new SimpleObjectProperty<Endereco>(this, "id");

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    public Endereco getIdEndereco() {
        return idEndereco.get();
    }

    public void setIdEndereco(Endereco endereco) {
        this.idEndereco.set(endereco);
    }

    public ObjectProperty<Endereco> idEnderecoProperty() {
        return idEndereco;
    }
    @XmlElement(name = "Cnpj")
    private StringProperty cnpj = new SimpleStringProperty(this, "cnpj");

    public String getCnpj() {
        return cnpj.get();
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }

    public StringProperty tipoProperty() {
        return cnpj;
    }

    @XmlElement(name = "InscricaoEstadual")
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

    @XmlElement(name = "InscricaoMunicipal")
    private StringProperty inscricaoMunicipal = new SimpleStringProperty(this, "inscricaoMunicipal");

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal.get();
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal.set(inscricaoMunicipal);
    }

    public StringProperty inscricaoMunicipalProperty() {
        return inscricaoMunicipal;
    }

    @XmlElement(name = "Categoria")
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
        out.writeObject(getIdEndereco());
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
        setIdEndereco((Endereco) in.readObject());
        setCnpj((String) in.readObject());
        setInscricaoEstadual((String) in.readObject());
        setInscricaoMunicipal((String) in.readObject());
        setCategoria((String) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return descricao.get();
    }

    public String toString2() {
        return id.get() + " " + idEndereco.get().getIdCidade().getNome() + " "
                + idEndereco.get().getIdCidade().getUf() + " " + descricao.get() + " "
                + idContato.get() + " " + idTelefone.get().getFixo() + " " + observacao.get() + " "
                + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(dataCadastro.get()), "dd-MM-yyyy") + " "
                + idContato.get().getEmail() + " " + nomeFantasia.get() + " "
                + getIdEndereco().getLogradouro() + " " + getIdEndereco().getBairro() + " "
                + getIdEndereco().getCep() + " " + idTelefone.get().getFax() + " "
                + idTelefone.get().getCelular() + " " + cnpj.get() + " " + inscricaoEstadual.get() + " "
                + inscricaoMunicipal.get() + " " + idContato.get().getCpf() + " " + idContato.get().getResponsavelTeste() + " "
                + categoria.get() + " " + idContato.get().getRg();
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
