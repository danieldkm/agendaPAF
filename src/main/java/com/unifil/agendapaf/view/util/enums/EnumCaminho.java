package com.unifil.agendapaf.view.util.enums;

/**
 *
 * @author danielmorita
 */
public enum EnumCaminho {

    Inicial("/fxml/Inicial.fxml"),
    Agenda("/fxml/Agendar.fxml"),
    Alerta("/fxml/Alerta.fxml"),
    CalendarioSemestral("/fxml/CalendarioSemestral.fxml"),
    Empresa("/fxml/Empresa.fxml"),
    Feriado("/fxml/Feriado.fxml"),
    FerramentaBD("/fxml/FerramentaBD.fxml"),
    Financeiro("/fxml/Financeiro.fxml"),
    Laudo("/fxml/Laudo.fxml"),
    Login("/fxml/Login.fxml"),
    NewLogin("/fxml/NewLogin.fxml"),
    MotivoReagendamento("/fxml/MotivoReagendamento.fxml"),
    Parametro("/fxml/Parametro.fxml"),
    PopUp("/fxml/PopUp.fxml"),
    Principal("/fxml/Principal.fxml"),
    Relatorio("/fxml/Relatorio.fxml"),
    RelatorioFinanceiro("/fxml/RelatorioFinanceiro.fxml"),
    TabelaAgenda("/fxml/TabelaAgenda.fxml"),
    TabelaEmpresa("/fxml/TabelaEmpresa.fxml"),
    TabelaEmpresasHomologadas("/fxml/TabelaEmpresasHomologadas.fxml"),
    TabelaFeriados("/fxml/TabelaFeriados.fxml"),
    TabelaFinanceiro("/fxml/TabelaFinanceiro.fxml"),
    TabelaHistorico("/fxml/TabelaHistorico.fxml"),
    TabelaUsuario("/fxml/TabelaUsuario.fxml"),
    Usuario("/fxml/Usuario.fxml"),
    VisualizadorMotivo("/fxml/VisualizarMotivo.fxml"),
    CssCalendar("/styles/calendar.css"),
    CSS("/styles/calendar_styles.css"),
    CSSMaterial("/styles/material-fx-v0_3.css"),
    LogoPAFECFUniFil("/image/logoPAFECFUNIFIL.png");

    private String caminho;
    private String padrao = "/com/unifil/agendapaf/";

    private EnumCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}
