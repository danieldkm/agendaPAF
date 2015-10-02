package com.unifil.agendapaf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "LaudoComplementar")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "MensagemType", propOrder = {
@XmlType(name = "LaudoComplementar", propOrder = {
    "nomeFantasia", "im", "rg", "possuiSGDB", "bytesExePrincipal",
    "ripmedExePrincipal", "ripmedTxtRelacao", "celular", "fax", "razaoSocial",
    "cnpj", "ie", "logradouro", "numero", "complemento", "bairro", "cep", "uf",
    "cidade", "responsavelTeste", "nomeContato", "cpf", "telefone", "email",
    "responsavelEnsaio", "idEmpresa"
})

public class LaudoComplementar {

    @XmlElement(name = "IdEmpresa")
    private Long idEmpresa;
    @XmlElement(name = "NomeFantasia")
    private String nomeFantasia;
    @XmlElement(name = "IM")
    private String im;
    @XmlElement(name = "RG")
    private String rg;
    @XmlElement(name = "Celular")
    private String celular;
    @XmlElement(name = "FAX")
    private String fax;
    @XmlElement(name = "RazaoSocial")
    private String razaoSocial;
    @XmlElement(name = "CNPJ")
    private String cnpj;
    @XmlElement(name = "IE")
    private String ie;
    @XmlElement(name = "Logradouro")
    private String logradouro;
    @XmlElement(name = "Numero")
    private String numero;
    @XmlElement(name = "Complemento")
    private String complemento;
    @XmlElement(name = "Bairro")
    private String bairro;
    @XmlElement(name = "CEP")
    private String cep;
    @XmlElement(name = "UF")
    private String uf;
    @XmlElement(name = "Cidade")
    private String cidade;
    @XmlElement(name = "ResponsavelTeste")
    private String responsavelTeste;
    @XmlElement(name = "NomeContato")
    private String nomeContato;
    @XmlElement(name = "CPF")
    private String cpf;
    @XmlElement(name = "Telefone")
    private String telefone;
    @XmlElement(name = "Email")
    private String email;
    @XmlElement(name = "ResponsavelEnsaio")
    private String responsavelEnsaio;
    @XmlElement(name = "PossuiSGDB", required = true)
    private Boolean possuiSGDB;
    @XmlElement(name = "BytesExePrincipal", required = true)
    private String bytesExePrincipal;
    @XmlElement(name = "RipmedExePrincipal", required = true)
    private String ripmedExePrincipal;
    @XmlElement(name = "RipmedTxtRelacao", required = true)
    private String ripmedTxtRelacao;

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getResponsavelTeste() {
        return responsavelTeste;
    }

    public void setResponsavelTeste(String responsavelTeste) {
        this.responsavelTeste = responsavelTeste;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsavelEnsaio() {
        return responsavelEnsaio;
    }

    public void setResponsavelEnsaio(String responsavelEnsaio) {
        this.responsavelEnsaio = responsavelEnsaio;
    }

    public Boolean getPossuiSGDB() {
        return possuiSGDB;
    }

    public void setPossuiSGDB(Boolean possuiSGDB) {
        this.possuiSGDB = possuiSGDB;
    }

    public String getBytesExePrincipal() {
        return bytesExePrincipal;
    }

    public void setBytesExePrincipal(String bytesExePrincipal) {
        this.bytesExePrincipal = bytesExePrincipal;
    }

    public String getRipmedExePrincipal() {
        return ripmedExePrincipal;
    }

    public void setRipmedExePrincipal(String ripmedExePrincipal) {
        this.ripmedExePrincipal = ripmedExePrincipal;
    }

    public String getRipmedTxtRelacao() {
        return ripmedTxtRelacao;
    }

    public void setRipmedTxtRelacao(String ripmedTxtRelacao) {
        this.ripmedTxtRelacao = ripmedTxtRelacao;
    }

}
