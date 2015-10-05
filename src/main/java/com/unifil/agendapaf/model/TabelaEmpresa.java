package com.unifil.agendapaf.model;

/**
 *
 * @author danielmorita
 */
public class TabelaEmpresa {

    private Empresa empresa;
    private String nomeContato;
    private String email;
    private String telefone;
    private String estado;
    private String cidade;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return empresa + " " + nomeContato + " " + email + " " + telefone + " " + estado + " " + cidade;
    }

}
