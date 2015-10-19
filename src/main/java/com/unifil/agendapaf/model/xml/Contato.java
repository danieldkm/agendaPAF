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
@XmlRootElement(name = "Contato")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contato", propOrder = {
    "responsavelTeste",
    "telefone",
    "email"
})
public class Contato extends Pessoa {

    @XmlElement(name = "ResponsavelTeste")
    private String responsavelTeste;
    @XmlElement(name = "Telefone")
    private Telefone telefone;
    @XmlElement(name = "Email")
    private String email;

    public Contato() {
    }

    public Contato(String nome, String cpf, String rg, String cargo) {
        super(nome, cpf, rg, cargo);
    }

    public Contato(String responsavelTeste, Telefone telefone, String email, String nome, String cpf, String rg, String cargo) {
        super(nome, cpf, rg, cargo);
        this.responsavelTeste = responsavelTeste;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato(String responsavelTeste, Telefone telefone, String email) {
        this.responsavelTeste = responsavelTeste;
        this.telefone = telefone;
        this.email = email;
    }

    public String getResponsavelTeste() {
        return responsavelTeste;
    }

    public void setResponsavelTeste(String responsavelTeste) {
        this.responsavelTeste = responsavelTeste;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
