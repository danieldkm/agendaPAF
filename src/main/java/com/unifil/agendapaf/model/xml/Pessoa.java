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
@XmlRootElement(name = "Pessoa")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pessoa", propOrder = {
    "nome",
    "cpf",
    "rg",
    "cargo"
})
public class Pessoa {

    @XmlElement(name = "Nome")
    private String nome;
    @XmlElement(name = "CPF")
    private String cpf;
    @XmlElement(name = "RG")
    private String rg;
    @XmlElement(name = "Cargo")
    private String cargo;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String rg, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
