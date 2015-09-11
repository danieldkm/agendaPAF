package com.unifil.agendapaf.model.aux;

public class Anual {

    private int mes;
    private double valor;
    private String servico;

    public Anual() {
    }

    public Anual(int mes, String servico) {
        this.mes = mes;
        this.servico = servico;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

}
