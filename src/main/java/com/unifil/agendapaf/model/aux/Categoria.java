package com.unifil.agendapaf.model.aux;

public class Categoria {

    private int id;
    private String nome;
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
