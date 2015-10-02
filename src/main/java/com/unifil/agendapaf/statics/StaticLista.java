package com.unifil.agendapaf.statics;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.Estado;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.Usuario;
import javafx.collections.ObservableList;

/**
 *
 * @author danielmorita
 */
public class StaticLista {

    private static ObservableList<Agenda> listaGlobalAgenda;
    private static ObservableList<Historico> listaGlobalHistorico;
    private static ObservableList<Feriado> listaGlobalFeriado;
    private static ObservableList<Cidade> listaGlobalCidade;
    private static ObservableList<Estado> listaGlobalEstado;
    private static ObservableList<Usuario> listaGlobalUsuario;
    private static ObservableList<Financeiro> listaGlobalFinanceiro;
    private static ObservableList<Empresa> listaGlobalEmpresa;
    private static ObservableList<EmpresasHomologadas> listaGlobalEmpresasHomologadas;
    private static ObservableList<Contato> listaGlobalContato;
    private static ObservableList<Endereco> listaGlobalEndereco;
    private static ObservableList<Telefone> listaGlobalTelefone;

    public static ObservableList<Agenda> getListaGlobalAgenda() {
        return listaGlobalAgenda;
    }

    public static void setListaGlobalAgenda(ObservableList<Agenda> listaGlobalAgenda) {
        StaticLista.listaGlobalAgenda = listaGlobalAgenda;
    }

    public static ObservableList<Historico> getListaGlobalHistorico() {
        return listaGlobalHistorico;
    }

    public static void setListaGlobalHistorico(ObservableList<Historico> listaGlobalHistorico) {
        StaticLista.listaGlobalHistorico = listaGlobalHistorico;
    }

    public static ObservableList<Feriado> getListaGlobalFeriado() {
        return listaGlobalFeriado;
    }

    public static void setListaGlobalFeriado(ObservableList<Feriado> listaGlobalFeriado) {
        StaticLista.listaGlobalFeriado = listaGlobalFeriado;
    }

    public static ObservableList<Cidade> getListaGlobalCidade() {
        return listaGlobalCidade;
    }

    public static void setListaGlobalCidade(ObservableList<Cidade> listaGlobalCidade) {
        StaticLista.listaGlobalCidade = listaGlobalCidade;
    }

    public static ObservableList<Estado> getListaGlobalEstado() {
        return listaGlobalEstado;
    }

    public static void setListaGlobalEstado(ObservableList<Estado> listaGlobalEstado) {
        StaticLista.listaGlobalEstado = listaGlobalEstado;
    }

    public static ObservableList<Usuario> getListaGlobalUsuario() {
        return listaGlobalUsuario;
    }

    public static void setListaGlobalUsuario(ObservableList<Usuario> listaGlobalUsuario) {
        StaticLista.listaGlobalUsuario = listaGlobalUsuario;
    }

    public static ObservableList<Financeiro> getListaGlobalFinanceiro() {
        return listaGlobalFinanceiro;
    }

    public static void setListaGlobalFinanceiro(ObservableList<Financeiro> listaGlobalFinanceiro) {
        StaticLista.listaGlobalFinanceiro = listaGlobalFinanceiro;
    }

    public static ObservableList<Empresa> getListaGlobalEmpresa() {
        return listaGlobalEmpresa;
    }

    public static void setListaGlobalEmpresa(ObservableList<Empresa> listaGlobalEmpresa) {
        StaticLista.listaGlobalEmpresa = listaGlobalEmpresa;
    }

    public static ObservableList<EmpresasHomologadas> getListaGlobalEmpresasHomologadas() {
        return listaGlobalEmpresasHomologadas;
    }

    public static void setListaGlobalEmpresasHomologadas(ObservableList<EmpresasHomologadas> listaGlobalEmpresasHomologadas) {
        StaticLista.listaGlobalEmpresasHomologadas = listaGlobalEmpresasHomologadas;
    }

    public static ObservableList<Contato> getListaGlobalContato() {
        return listaGlobalContato;
    }

    public static void setListaGlobalContato(ObservableList<Contato> listaGlobalContato) {
        StaticLista.listaGlobalContato = listaGlobalContato;
    }

    public static ObservableList<Endereco> getListaGlobalEndereco() {
        return listaGlobalEndereco;
    }

    public static void setListaGlobalEndereco(ObservableList<Endereco> listaGlobalEndereco) {
        StaticLista.listaGlobalEndereco = listaGlobalEndereco;
    }

    public static ObservableList<Telefone> getListaGlobalTelefone() {
        return listaGlobalTelefone;
    }

    public static void setListaGlobalTelefone(ObservableList<Telefone> listaGlobalTelefone) {
        StaticLista.listaGlobalTelefone = listaGlobalTelefone;
    }

}
