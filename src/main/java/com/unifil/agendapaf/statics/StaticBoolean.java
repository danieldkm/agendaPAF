package com.unifil.agendapaf.statics;

/**
 *
 * @author danielmorita
 */
public class StaticBoolean {

    private static boolean reagendamento = false;
    private static boolean cancelamento = false;
    private static boolean tabelaAgenda = false;
    private static boolean agenda = false;
    private static boolean empresa = false;
    private static boolean tabelaHistorico = false;
    private static boolean consulta = false;
    private static boolean tabelaEmpresaHomologada = false;
    private static boolean relatorio = false;
    private static boolean update = false;
    private static boolean tabelaEmpresa;
    private static boolean local;
    private static boolean servidor;
    private static boolean selectedLocal = false;
    private static boolean tabelaEmpresaToFinanceiro = false;
    private static boolean dbLocal = false;
    private static boolean dbServidor = false;

    public static boolean isReagendamento() {
        return reagendamento;
    }

    public static void setReagendamento(boolean reagendamento) {
        StaticBoolean.reagendamento = reagendamento;
    }

    public static boolean isCancelamento() {
        return cancelamento;
    }

    public static void setCancelamento(boolean cancelamento) {
        StaticBoolean.cancelamento = cancelamento;
    }

    public static boolean isTabelaAgenda() {
        return tabelaAgenda;
    }

    public static void setTabelaAgenda(boolean tabelaAgenda) {
        StaticBoolean.tabelaAgenda = tabelaAgenda;
    }

    public static boolean isAgenda() {
        return agenda;
    }

    public static void setAgenda(boolean agenda) {
        StaticBoolean.agenda = agenda;
    }

    public static boolean isEmpresa() {
        return empresa;
    }

    public static void setEmpresa(boolean empresa) {
        StaticBoolean.empresa = empresa;
    }

    public static boolean isTabelaHistorico() {
        return tabelaHistorico;
    }

    public static void setTabelaHistorico(boolean tabelaHistorico) {
        StaticBoolean.tabelaHistorico = tabelaHistorico;
    }

    public static boolean isConsulta() {
        return consulta;
    }

    public static void setConsulta(boolean consulta) {
        StaticBoolean.consulta = consulta;
    }

    public static boolean isTabelaEmpresaHomologada() {
        return tabelaEmpresaHomologada;
    }

    public static void setTabelaEmpresaHomologada(boolean tabelaEmpresaHomologada) {
        StaticBoolean.tabelaEmpresaHomologada = tabelaEmpresaHomologada;
    }

    public static boolean isRelatorio() {
        return relatorio;
    }

    public static void setRelatorio(boolean relatorio) {
        StaticBoolean.relatorio = relatorio;
    }

    public static boolean isUpdate() {
        return update;
    }

    public static void setUpdate(boolean update) {
        StaticBoolean.update = update;
    }

    public static boolean isTabelaEmpresa() {
        return tabelaEmpresa;
    }

    public static void setTabelaEmpresa(boolean tabelaEmpresa) {
        StaticBoolean.tabelaEmpresa = tabelaEmpresa;
    }

    public static boolean isLocal() {
        return local;
    }

    public static void setLocal(boolean local) {
        StaticBoolean.local = local;
    }

    public static boolean isServidor() {
        return servidor;
    }

    public static void setServidor(boolean servidor) {
        StaticBoolean.servidor = servidor;
    }

    public static boolean isSelectedLocal() {
        return selectedLocal;
    }

    public static void setSelectedLocal(boolean selectedLocal) {
        StaticBoolean.selectedLocal = selectedLocal;
    }

    public static boolean isTabelaEmpresaToFinanceiro() {
        return tabelaEmpresaToFinanceiro;
    }

    public static void setTabelaEmpresaToFinanceiro(boolean tabelaEmpresaToFinanceiro) {
        StaticBoolean.tabelaEmpresaToFinanceiro = tabelaEmpresaToFinanceiro;
    }

    public static boolean isDbLocal() {
        return dbLocal;
    }

    public static void setDbLocal(boolean dbLocal) {
        StaticBoolean.dbLocal = dbLocal;
    }

    public static boolean isDbServidor() {
        return dbServidor;
    }

    public static void setDbServidor(boolean dbServidor) {
        StaticBoolean.dbServidor = dbServidor;
    }

}
