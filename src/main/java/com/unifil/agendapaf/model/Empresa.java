package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import com.unifil.agendapaf.util.UtilConverter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
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
//id, descricao, nomeContato, telefone, observacao, dataCadastro, email, 
//estado, cidade, nomeFantasia, endereco, bairro, cep, fax, celular, cnpj, 
//IE, IM, cpf, Respo Teste, codInterno, categoria

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByID", query = "SELECT e FROM Empresa e where e.id = :id")})
public class Empresa implements Externalizable {

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

    private StringProperty nomeContato = new SimpleStringProperty(this, "nomeContato");

    public String getNomeContato() {
        return nomeContato.get();
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato.set(nomeContato);
    }

    public StringProperty nomeContatoProperty() {
        return nomeContato;
    }

    private StringProperty telefone = new SimpleStringProperty(this, "telefone");

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty telefoneProperty() {
        return telefone;
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

    public LocalDate getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDate> dataCadastroProperty() {
        return dataCadastro;
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

//    private StringProperty estado = new SimpleStringProperty(this, "estado");
//
//    public String getEstado() {
//        return estado.get();
//    }
//
//    public void setEstado(String estado) {
//        this.estado.set(estado);
//    }
//
//    public StringProperty estadoProperty() {
//        return estado;
//    }
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

    private StringProperty endereco = new SimpleStringProperty(this, "endereco");

    public String getEndereco() {
        return endereco.get();
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public StringProperty enderecoProperty() {
        return endereco;
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

    private StringProperty fax = new SimpleStringProperty(this, "fax");

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

    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public StringProperty celularProperty() {
        return celular;
    }

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

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal.get();
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal.set(inscricaoMunicipal);
    }

    public StringProperty inscricaoMunicipalProperty() {
        return inscricaoMunicipal;
    }

    private StringProperty cpf = new SimpleStringProperty(this, "cpf");

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

    private StringProperty categoria = new SimpleStringProperty(this, "categoria");

    public String getCategoria() {
        return categoria.get();
    }

//    public Integer getCategoriaInt() {
//        return categoria.get();
//    }
//
//    public String getCategoria() {
//        for (Categoria c : Controller.getCategorias()) {
//            if (c.getId() == this.categoria.get()) {
//                return c.getNome();
//            }
//        }
//        return null;
//    }
    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getIdCidade());
        out.writeObject(getDescricao());
        out.writeObject(getNomeContato());
        out.writeObject(getTelefone());
        out.writeObject(getObservacao());
        out.writeObject(getDataCadastro());
        out.writeObject(getEmail());
        out.writeObject(getNomeFantasia());
        out.writeObject(getEndereco());
        out.writeObject(getBairro());
        out.writeObject(getCep());
        out.writeObject(getFax());
        out.writeObject(getCelular());
        out.writeObject(getCnpj());
        out.writeObject(getInscricaoEstadual());
        out.writeObject(getInscricaoMunicipal());
        out.writeObject(getCpf());
        out.writeObject(getResponsavelTeste());
        out.writeObject(getCategoria());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setIdCidade((Cidade) in.readObject());
        setDescricao((String) in.readObject());
        setNomeContato((String) in.readObject());
        setTelefone((String) in.readObject());
        setObservacao((String) in.readObject());
        setDataCadastro((LocalDate) in.readObject());
        setEmail((String) in.readObject());
        setNomeFantasia((String) in.readObject());
        setEndereco((String) in.readObject());
        setBairro((String) in.readObject());
        setCep((String) in.readObject());
        setFax((String) in.readObject());
        setCelular((String) in.readObject());
        setCnpj((String) in.readObject());
        setInscricaoEstadual((String) in.readObject());
        setInscricaoMunicipal((String) in.readObject());
        setCpf((String) in.readObject());
        setResponsavelTeste((String) in.readObject());
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
        return id.get() + " " + idCidade.get().getNome() + " " + descricao.get() + " " + nomeContato.get() + " " + telefone.get() + " " + observacao.get() + " " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(dataCadastro.get()), "dd-MM-yyyy") + " " + email.get() + " " + nomeFantasia.get() + " " + endereco.get() + " " + bairro.get() + " " + cep.get() + " " + fax.get() + " " + celular.get() + " " + cnpj.get() + " " + inscricaoEstadual.get() + " " + inscricaoMunicipal.get() + " " + cpf.get() + " " + responsavelTeste.get() + " " + categoria.get();
    }

}
