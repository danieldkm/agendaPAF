package com.unifil.agendapaf.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Endereco")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Endereco", propOrder = {
    "logradouro",
    "numero",
    "complemento",
    "bairro",
    "cep",
    "uf",
    "cidade"
})
public class Endereco {

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

    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, String uf, String cidade) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
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

}
