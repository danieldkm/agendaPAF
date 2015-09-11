package com.unifil.agendapaf.util.huffman;

public class Node {

    private int quantidade;
    private String data;
    private double valor;
    private String bin = "";
    private int bit;

    private Node direita;
    private Node esquerda;

    public Node(int quantidade, String data, double valor, String bin) {
        this.quantidade = quantidade;
        this.data = data;
        this.valor = valor;
        this.bin = bin;
    }

    public Node(double novo) {
        valor = novo;
        direita = null;
        esquerda = null;
        bin = "";
    }

    public Node(int quantidade, String data, double valor) {
        super();
        this.quantidade = quantidade;
        this.data = data;
        this.valor = valor;
        this.bin = "";
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

}
