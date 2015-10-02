package com.unifil.agendapaf.model;

/**
 *
 * @author danielmorita
 */
public class TabelaEmpresa {

    private Empresa empresa;
    private Contato contato;
    private Telefone telefone;
    private Endereco endereco;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "TabelaEmpresa{" + "empresa=" + empresa + ", contato=" + contato + ", telefone=" + telefone + ", endereco=" + endereco + '}';
    }

}
