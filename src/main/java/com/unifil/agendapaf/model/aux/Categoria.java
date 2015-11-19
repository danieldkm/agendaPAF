package com.unifil.agendapaf.model.aux;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//@XmlRootElement(name = "Categoria")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Categoria", propOrder = {
    "id", "nome", "porcento"
})
public class Categoria {

    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "Nome", required = true)
    private String nome;
    @XmlElement(name = "Porcento", required = true)
    private int porcento;

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

    public int getPorcento() {
        return porcento;
    }

    public void setPorcento(int porcento) {
        this.porcento = porcento;
    }

    @Override
    public String toString() {
        return nome;
    }

}
