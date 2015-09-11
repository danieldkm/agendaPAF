package com.unifil.agendapaf.statics;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Usuario;

/**
 *
 * @author danielmorita
 */
public class StaticObject {

    private static Usuario usuarioLogado;
    private static Agenda agendaEncontrada;
    private static Empresa empresaEncontrada;
    private static Feriado feriadoEncontrada;
    private static Financeiro financeiroEncontrada;
    private static Usuario usuarioEncontrada;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        StaticObject.usuarioLogado = usuarioLogado;
    }

    public static Agenda getAgendaEncontrada() {
        return agendaEncontrada;
    }

    public static void setAgendaEncontrada(Agenda agendaEncontrada) {
        StaticObject.agendaEncontrada = agendaEncontrada;
    }

    public static Empresa getEmpresaEncontrada() {
        return empresaEncontrada;
    }

    public static void setEmpresaEncontrada(Empresa empresaEncontrada) {
        StaticObject.empresaEncontrada = empresaEncontrada;
    }

    public static Feriado getFeriadoEncontrada() {
        return feriadoEncontrada;
    }

    public static void setFeriadoEncontrada(Feriado feriadoEncontrada) {
        StaticObject.feriadoEncontrada = feriadoEncontrada;
    }

    public static Financeiro getFinanceiroEncontrada() {
        return financeiroEncontrada;
    }

    public static void setFinanceiroEncontrada(Financeiro financeiroEncontrada) {
        StaticObject.financeiroEncontrada = financeiroEncontrada;
    }

    public static Usuario getUsuarioEncontrada() {
        return usuarioEncontrada;
    }

    public static void setUsuarioEncontrada(Usuario usuarioEncontrada) {
        StaticObject.usuarioEncontrada = usuarioEncontrada;
    }

}
