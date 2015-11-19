package com.unifil.agendapaf.model.aux;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlRootElement(name = "Servico")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Servico", propOrder = {
    "id", "nome", "valor"
})
public class Servico {

    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "Nome", required = true)
    private String nome;
    @XmlElement(name = "Valor", required = true)
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome;
    }

}
