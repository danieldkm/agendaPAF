package com.unifil.agendapaf.view.util.enums;

import java.io.File;

/**
 *
 * @author danielmorita
 */
public enum EnumCaminho {

    FXMLInicial("/fxml/Inicial.fxml"),
    FXMLAgenda("/fxml/Agendar.fxml"),
    FXMLAlerta("/fxml/Alerta.fxml"),
    FXMLCalendarioSemestral("/fxml/CalendarioSemestral.fxml"),
    FXMLEmpresa("/fxml/Empresa.fxml"),
    FXMLFeriado("/fxml/Feriado.fxml"),
    FXMLFerramentaBD("/fxml/FerramentaBD.fxml"),
    FXMLFinanceiro("/fxml/Financeiro.fxml"),
    FXMLLaudo("/fxml/Laudo.fxml"),
    FXMLFerramentaLaudo("/fxml/FerramentaLaudo.fxml"),
    FXMLLogin("/fxml/Login.fxml"),
    FXMLNewLogin("/fxml/NewLogin.fxml"),
    FXMLEmail("/fxml/Email.fxml"),
    FXMLMotivoReagendamento("/fxml/MotivoReagendamento.fxml"),
    FXMLFerramentaFinanceiro("/fxml/FerramentaFinanceiro.fxml"),
    FXMLFerramentaEmail("/fxml/FerramentaEmail.fxml"),
    FXMLPopUp("/fxml/PopUp.fxml"),
    FXMLPrincipal("/fxml/Principal.fxml"),
    FXMLRelatorio("/fxml/Relatorio.fxml"),
    FXMLRelatorioFinanceiro("/fxml/RelatorioFinanceiro.fxml"),
    FXMLTabelaAgenda("/fxml/TabelaAgenda.fxml"),
    FXMLTabelaEmpresa("/fxml/TabelaEmpresa.fxml"),
    FXMLTabelaEmpresasHomologadas("/fxml/TabelaEmpresasHomologadas.fxml"),
    FXMLTabelaFeriados("/fxml/TabelaFeriados.fxml"),
    FXMLTabelaFinanceiro("/fxml/TabelaFinanceiro.fxml"),
    FXMLTabelaHistorico("/fxml/TabelaHistorico.fxml"),
    FXMLTabelaUsuario("/fxml/TabelaUsuario.fxml"),
    FXMLUsuario("/fxml/Usuario.fxml"),
    FXMLVisualizadorMotivo("/fxml/VisualizarMotivo.fxml"),
    FXMLEscolherDosc("/fxml/EscolherDocs.fxml"),
    DocxsModelo(System.getProperty("user.dir") + "/word/modelo_docxs"),
    DocxsDocumentos(System.getProperty("user.dir") + "/word/modelo_docxs/Documentos.json"),
    CSS("/styles/calendar_styles.css"),
    CSSCalendar("/styles/calendar.css"),
    CSSMaterial("/styles/material-fx-v0_3.css"),
    DiretorioXML("xml/LaudoFerramenta.xml"),
    DiretorioXMLLaudoFerramenta("xml/LaudoFerramenta.xml"),
    DiretorioEmail("email/"),
    DiretorioEmailEnviadas("email/enviadas"),
    XMLFerramentaEmail("FerramentaEmail.xml"),
    XMLEmails("Emails.xml"),
    ImgLogoPAFECFUniFil("/image/logoPAFECFUNIFIL.png"),
    ImgFerramentaBlack("/image/ferramenta32px.png"),
    ImgFerramentaRed("/image/ferramenta32pxR.png");

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
