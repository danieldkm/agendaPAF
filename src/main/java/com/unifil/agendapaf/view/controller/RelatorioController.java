package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.aux.Anual;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.GerarRelatorios;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class RelatorioController {

    @FXML
    public void initialize() {
        try {
            mensagem = new Mensagem(stage);
            sceneManager = SceneManager.getInstance();
            isAnual = false;
//        setRelatorioAnual();
            txtInfo = new TextField("Click em Buscar!");
            txtInfo.setPromptText("Informe o nome da Empresa");
            txtInfo.setEditable(false);

//            dataPicker = new FXCalendar();
            dataPicker = new DatePicker();

            btnBuscar = new Button("Buscar Empresa");
            btnBuscar.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    actionBtnBuscar(t);
                }
            });

            cbRelatorio.setOnAction(new EventHandler() {
                @Override
                public void handle(Event t) {
                    actionCbRelatorio(t);
                }
            });

            cbGrupo.setOnAction(new EventHandler() {

                @Override
                public void handle(Event t) {
//                    System.out.println("handle on cbGrupo");
                    actionCbGrupo(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar relatorio", e);
        }

    }

    @FXML
    private ComboBox cbRelatorio;
    @FXML
    private ComboBox cbGrupo;
    @FXML
    private ComboBox cbExtensao;
    @FXML
    private HBox vb3;
    @FXML
    private HBox vb4;
    @FXML
    private Button btnGerar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnBuscar;
    @FXML
    private VBox mainRelatorio;

    private static TextField txtInfo;
//    private FXCalendar 
    private DatePicker dataPicker;
    private Stage stage;
    ObservableList stringFiltro = FXCollections.observableArrayList();
    private GerarRelatorios relatorio = new GerarRelatorios();
    private Map<String, Object> parametros = new HashMap<String, Object>();

    private String caminhoAbsoluto = System.getProperty("user.dir") + "/relatorios";
    private DatePicker dataInicial;
    private DatePicker dataFinal;
    private int ano;
    private boolean isAnual = false;
    private SceneManager sceneManager;
    private Mensagem mensagem;

    @FXML
    private void actionBtnGerar() {
        if (cbRelatorio.getValue() != null) {
            // criando o datasource com os dados criados
            if (cbRelatorio.getValue().equals("Agenda")) {
//            ds = new JRBeanCollectionDataSource(Controller.getAgendasOrderBy());
                if (cbGrupo.getValue().equals("Empresa")) {
//                System.out.println("Relatorio de agenda1 \nGrupo: " + cbGrupo.getValue() + "\nInfo: " + empresaEncontrada.getCodEmpresa() + "\nExtensao: " + cbExtensao.getValue());
                    relatorio.gerarRelatorio(stage, getJasperFile("Agenda", cbGrupo.getValue().toString(), sceneManager.getEmpresaEncontrada().getId()), parametros, /*cbGrupo.getValue().toString(), empresaEncontrada.getCodEmpresa(), */ cbExtensao.getValue().toString()/*, "Agenda"*/, false, listaAnual);
                } else if (cbGrupo.getValue().equals("Período")) {
//                System.out.println("Relatorio de agenda2 \nGrupo: " + cbGrupo.getValue() + "\nInfo: " + dataPicker.getValue() + "\nExtensao: " + cbExtensao.getValue());
//                relatorio.gerarRelatorio(stage, cbGrupo.getValue().toString(), dataPicker.getValue(), cbExtensao.getValue().toString(), "Agenda");
//                relatorio.gerarRelatorio(stage, getJasperFile("Agenda", cbGrupo.getValue().toString(), converterLocalDateToUtilDate(dataPicker.getValue())), parametros, cbExtensao.getValue().toString());
                    relatorio.gerarRelatorio(stage, getJasperFile("Agenda", cbGrupo.getValue().toString(), ""), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                } else {
//                System.out.println("Relatorio de agenda3 \nGrupo: " + cbGrupo.getValue() + "\nInfo: " + txtInfo.getText() + "\nExtensao: " + cbExtensao.getValue());
//                relatorio.gerarRelatorio(stage, "", "", cbExtensao.getValue().toString(), "Agenda");
                    relatorio.gerarRelatorio(stage, getJasperFile("Agenda", "", ""), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                }
            } else if (cbRelatorio.getValue().equals("Empresa")) {
//            relatorio.gerarRelatorio(stage, "", "", cbExtensao.getValue().toString(), "Empresa");
                relatorio.gerarRelatorio(stage, getJasperFile("Empresa", "", ""), parametros, cbExtensao.getValue().toString(), false, listaAnual);
            } else if (cbRelatorio.getValue().equals("Historico")) {
                if (cbGrupo.getValue().equals("Empresa")) {
//                relatorio.gerarRelatorio(stage, cbGrupo.getValue().toString(), empresaEncontrada.getCodEmpresa(), cbExtensao.getValue().toString(), "Historico");
                    relatorio.gerarRelatorio(stage, getJasperFile("Historico", cbGrupo.getValue().toString(), sceneManager.getEmpresaEncontrada().getId()), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                } else if (cbGrupo.getValue().equals("Data")) {
//                relatorio.gerarRelatorio(stage, cbGrupo.getValue().toString(), dataPicker.getValue(), cbExtensao.getValue().toString(), "Historico");
                    relatorio.gerarRelatorio(stage, getJasperFile("Historico", cbGrupo.getValue().toString(), UtilConverter.converterLocalDateToUtilDate(dataPicker.getValue())), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                } else {
//                relatorio.gerarRelatorio(stage, "", "", cbExtensao.getValue().toString(), "Historico");
                    relatorio.gerarRelatorio(stage, getJasperFile("Historico", "", ""), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                }
            } else if (cbRelatorio.getValue().equals("Empresas Homologas")) {
                if (cbGrupo.getValue().equals("Empresa")) {
//                relatorio.gerarRelatorio(stage, cbGrupo.getValue().toString(), empresaEncontrada.getCodEmpresa(), cbExtensao.getValue().toString(), "Empresas Homologadas");
                    relatorio.gerarRelatorio(stage, getJasperFile("Empresas Homologadas", cbGrupo.getValue().toString(), sceneManager.getEmpresaEncontrada().getId()), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                } else if (cbGrupo.getValue().equals("Data")) {
//                relatorio.gerarRelatorio(stage, cbGrupo.getValue().toString(), dataPicker.getValue(), cbExtensao.getValue().toString(), "Empresas Homologadas");
                    relatorio.gerarRelatorio(stage, getJasperFile("Empresas Homologadas", cbGrupo.getValue().toString(), UtilConverter.converterLocalDateToUtilDate(dataPicker.getValue())), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                } else {
//                relatorio.gerarRelatorio(stage, "", "", cbExtensao.getValue().toString(), "Empresas Homologadas");
                    relatorio.gerarRelatorio(stage, getJasperFile("Empresas Homologadas", "", ""), parametros, cbExtensao.getValue().toString(), false, listaAnual);
                }
            } else if (cbRelatorio.getValue().equals("Financeiro")) {
                relatorio.gerarRelatorio(stage, getJasperFile("Financeiro", cbGrupo.getValue().toString(), ""), parametros, cbExtensao.getValue().toString(), isAnual, listaAnual);
            }
        }
    }

    @FXML
    public void actionBtnLimpar() {
        cbRelatorio.getSelectionModel().clearSelection();
        cbExtensao.getSelectionModel().clearSelection();
        cbGrupo.getSelectionModel().clearSelection();
        vb3.getChildren().remove(dataPicker);
        vb3.getChildren().remove(txtInfo);
        vb4.getChildren().remove(btnBuscar);
    }

    private void actionCbRelatorio(Event t) {
        try {
            stringFiltro.clear();
            vb3.getChildren().remove(dataPicker);
            vb3.getChildren().remove(txtInfo);
            vb4.getChildren().remove(btnBuscar);
            if (cbRelatorio.getValue().equals("Agenda")) {
                stringFiltro.addAll("", "Período", "Empresa");
                cbGrupo.setItems(stringFiltro);
            } else if (cbRelatorio.getValue().equals("Empresa")) {
                stringFiltro.addAll("");
                cbGrupo.setItems(stringFiltro);
            } else if (cbRelatorio.getValue().equals("Historico")) {
                stringFiltro.addAll("", "Data", "Empresa");
                cbGrupo.setItems(stringFiltro);
            } else if (cbRelatorio.getValue().equals("Empresas Homologas")) {
                stringFiltro.addAll("", "Data", "Empresa");
                cbGrupo.setItems(stringFiltro);
            } else if (cbRelatorio.getValue().equals("Financeiro")) {
                stringFiltro.addAll("", "Mensal", "Anual", "Período");
                cbGrupo.setItems(stringFiltro);
            }
            cbExtensao.getSelectionModel().selectFirst();
            cbGrupo.getSelectionModel().selectFirst();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private void actionCbGrupo(Event t) {
        try {
            vb3.getChildren().remove(dataPicker);
            vb3.getChildren().remove(txtInfo);
            vb4.getChildren().remove(btnBuscar);
            if (cbGrupo != null && cbGrupo.getValue() != null) {
                if (cbGrupo.getValue().equals("Data")) {
                    vb3.getChildren().add(dataPicker);
                } else if (cbGrupo.getValue().equals("Empresa")) {
                    vb3.getChildren().add(txtInfo);
                    vb4.getChildren().add(btnBuscar);
                } else if (cbGrupo.getValue().equals("Mensal") || cbGrupo.getValue().equals("Anual") || cbGrupo.getValue().equals("Período")) {
                    if (cbRelatorio.getSelectionModel().getSelectedItem().toString().equals("Financeiro")) {
                        iniciarRelatorioFinanceiro(cbGrupo.getValue().toString(), "Relatório Financeiro");
                    } else if (cbRelatorio.getSelectionModel().getSelectedItem().toString().equals("Agenda")) {
                        iniciarRelatorioFinanceiro(cbGrupo.getValue().toString(), "Relatório Agenda");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("ERRO METODO ACTIONCBGRUPO");
            e.printStackTrace();
        }
    }

    private void actionBtnBuscar(ActionEvent t) {
        sceneManager.showTabelaEmpresa(false, false, false, true, false, false, false);
    }

    public void setCampos(Stage stage) {
        txtInfo.setText(sceneManager.getEmpresaEncontrada().getDescricao());
        stage.close();
    }

    public void setCampos(Stage stage, DatePicker dataInicial, DatePicker dataFinal, int ano) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.ano = ano;
        stage.close();
    }

    private void iniciarRelatorioFinanceiro(String tipo, String titulo) {
        sceneManager.showRelatorioFinanceiro(titulo, tipo);
    }

    /**
     * Verifica o tipo do relat�rio se e; agenda, empresa, historico e empresas
     * homologas. E escolhe o filtro caso empresa, data ou vazio, quando
     * escolhido ser� setado o caminho do arquivo jasper (o relat�rio)
     *
     *
     * @param tipo uma String para verificar o tipo do relat�rio
     * @param agrupadoPor uma String para verificar o agrupando desejado p/
     * gerar relat�rio
     * @param dados � um objeto que pode ser util.Date ou Integer, que ir�o
     * determinar no parametros o filtro dos relat�rios
     * @return caso esncontre o tipo e o agrupadoPor retorna o caminho do
     * arquivo jasper
     */
    public String getJasperFile(String tipo, String agrupadoPor, Object dados) {
        BufferedImage gto = null;
        try {
            gto = ImageIO.read(getClass().getResource(EnumCaminho.LogoPAFECFUniFil.getCaminho()));
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
        parametros.put("logo", gto);
        switch (tipo) {
            case "Agenda":
                switch (agrupadoPor) {
                    case "Empresa":
                        parametros.put("id", dados);
                        return caminhoAbsoluto + "/RelatorioAgendaPorEmpresa.jasper";
                    case "Período":
//                        parametros.put("data", dados);
                        parametros.put("dataInicial", UtilConverter.converterLocalDateToUtilDate(dataInicial.getValue()));
                        parametros.put("dataFinal", UtilConverter.converterLocalDateToUtilDate(dataFinal.getValue()));
                        return caminhoAbsoluto + "/RelatorioAgendaPorData.jasper";
                    case "":
                        return caminhoAbsoluto + "/RelatorioAgenda.jasper";
                }
                break;
            case "Empresa":
                return caminhoAbsoluto + "/RelatorioEmpresa.jasper";
            case "Historico":
                switch (agrupadoPor) {
                    case "Empresa":
                        parametros.put("id", dados);
                        return caminhoAbsoluto + "/RelatorioHistoricoEmpresa.jasper";
                    case "Data":
                        parametros.put("data", dados);
                        return caminhoAbsoluto + "/RelatorioHistoricoData.jasper";
                    case "":
                        return caminhoAbsoluto + "/RelatorioHistorico.jasper";
                }
                break;
            case "Empresas Homologadas":
                switch (agrupadoPor) {
                    case "Empresa":
                        parametros.put("id", dados);
                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadasPorEmpresa.jasper";
                    case "Data":
                        parametros.put("data", dados);
                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadasPorData.jasper";
                    case "":
//                        System.out.println("arquivo empresas homologadas geral e agrupadoPor " + agrupadoPor);
                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadas.jasper";
                }
                break;
            case "Financeiro":
                switch (agrupadoPor) {
                    case "Mensal":
                        parametros.put("dataInicial", UtilConverter.converterLocalDateToUtilDate(dataInicial.getValue()));
                        parametros.put("dataFinal", UtilConverter.converterLocalDateToUtilDate(dataFinal.getValue()));
                        return caminhoAbsoluto + "/RelatorioFinanceiro.jasper";
                    case "Anual":
                        limpar();
                        isAnual = true;
                        setRelatorioAnual();
                        setParametrosAnual();
                        return caminhoAbsoluto + "/RelatorioFinanceiroAnual.jasper";
                    case "Período":
                        parametros.put("dataInicial", UtilConverter.converterLocalDateToUtilDate(dataInicial.getValue()));
                        parametros.put("dataFinal", UtilConverter.converterLocalDateToUtilDate(dataFinal.getValue()));
                        return caminhoAbsoluto + "/RelatorioFinanceiro.jasper";
                }
                break;
        }
        System.out.println("caminho " + caminhoAbsoluto);
        return "erro";
    }

    public void setAnual(Financeiro fi, int mes) {
        if (fi.getTipoServico().equals(EnumServico.PreAvaliacao.getServico()) || fi.getTipoServico().equals(EnumServico.PreAvaliacaoIntinerante.getServico()) || fi.getTipoServico().equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            switch (mes) {
                case 1:
                    preJanQTD++;
                    break;
                case 2:
                    preFevQTD++;
                    break;
                case 3:
                    preMarQTD++;
                    break;
                case 4:
                    preAbrQTD++;
                    break;
                case 5:
                    preMaiQTD++;
                    break;
                case 6:
                    preJunQTD++;
                    break;
                case 7:
                    preJulQTD++;
                    break;
                case 8:
                    preAgoQTD++;
                    break;
                case 9:
                    preSetQTD++;
                    break;
                case 10:
                    preOutQTD++;
                    break;
                case 11:
                    preNovQTD++;
                    break;
                case 12:
                    preDezQTD++;
                    break;
            }
            pre.setValor(pre.getValor() + fi.getValorPago());
        } else if (fi.getTipoServico().equals(EnumServico.Avaliacao.getServico()) || fi.getTipoServico().equals(EnumServico.AvaliacaoIntinerante.getServico())) {
            switch (mes) {
                case 1:
                    avaJanQTD++;
                    break;
                case 2:
                    avaFevQTD++;
                    break;
                case 3:
                    avaMarQTD++;
                    break;
                case 4:
                    avaAbrQTD++;
                    break;
                case 5:
                    avaMaiQTD++;
                    break;
                case 6:
                    avaJunQTD++;
                    break;
                case 7:
                    avaJulQTD++;
                    break;
                case 8:
                    avaAgoQTD++;
                    break;
                case 9:
                    avaSetQTD++;
                    break;
                case 10:
                    avaOutQTD++;
                    break;
                case 11:
                    avaNovQTD++;
                    break;
                case 12:
                    avaDezQTD++;
                    break;
            }
            ava.setValor(ava.getValor() + fi.getValorPago());
        } else if (fi.getTipoServico().equals(EnumServico.HoraAdicional.getServico())) {
            hor.setValor(hor.getValor() + fi.getValorPago());
        }
    }

    public void addListaAnual(int mes) {
        switch (mes) {
            case 1:
                preJan = pre.getValor();
                break;
            case 2:
                preFev = pre.getValor();
                break;
            case 3:
                preMar = pre.getValor();
                break;
            case 4:
                preAbr = pre.getValor();
                break;
            case 5:
                preMai = pre.getValor();
                break;
            case 6:
                preJun = pre.getValor();
                break;
            case 7:
                preJul = pre.getValor();
                break;
            case 8:
                preAgo = pre.getValor();
                break;
            case 9:
                preSet = pre.getValor();
                break;
            case 10:
                preOut = pre.getValor();
                break;
            case 11:
                preNov = pre.getValor();
                break;
            case 12:
                preDez = pre.getValor();
                break;
        }
        listaAnual.add(pre);
        switch (mes) {
            case 1:
                avaJan = ava.getValor();
                break;
            case 2:
                avaFev = ava.getValor();
                break;
            case 3:
                avaMar = ava.getValor();
                break;
            case 4:
                avaAbr = ava.getValor();
                break;
            case 5:
                avaMai = ava.getValor();
                break;
            case 6:
                avaJun = ava.getValor();
                break;
            case 7:
                avaJul = ava.getValor();
                break;
            case 8:
                avaAgo = ava.getValor();
                break;
            case 9:
                avaSet = ava.getValor();
                break;
            case 10:
                avaOut = ava.getValor();
                break;
            case 11:
                avaNov = ava.getValor();
                break;
            case 12:
                avaDez = ava.getValor();
                break;
        }
        listaAnual.add(ava);
        switch (mes) {
            case 1:
                horJan = hor.getValor();
                break;
            case 2:
                horFev = hor.getValor();
                break;
            case 3:
                horMar = hor.getValor();
                break;
            case 4:
                horAbr = hor.getValor();
                break;
            case 5:
                horMai = hor.getValor();
                break;
            case 6:
                horJun = hor.getValor();
                break;
            case 7:
                horJul = hor.getValor();
                break;
            case 8:
                horAgo = hor.getValor();
                break;
            case 9:
                horSet = hor.getValor();
                break;
            case 10:
                horOut = hor.getValor();
                break;
            case 11:
                horNov = hor.getValor();
                break;
            case 12:
                horDez = hor.getValor();
                break;
        }
        listaAnual.add(hor);
        double total = pre.getValor() + ava.getValor() + hor.getValor();
        if (total != 0) {
            Anual a = new Anual();
            a.setValor(total);
            listaTotal.add(a);
        }
    }
    private Anual pre = new Anual(1, EnumServico.PreAvaliacao.getServico());
    private Anual ava = new Anual(1, EnumServico.Avaliacao.getServico());
    private Anual hor = new Anual(1, EnumServico.HoraAdicional.getServico());
    public static List<Anual> listaAnual = new ArrayList<Anual>();
    private List<Anual> listaTotal = new ArrayList<Anual>();
    private int preJanQTD = 0, preFevQTD, preMarQTD, preAbrQTD, preMaiQTD, preJunQTD, preJulQTD, preAgoQTD, preSetQTD, preOutQTD, preNovQTD, preDezQTD;
    private int avaJanQTD, avaFevQTD, avaMarQTD, avaAbrQTD, avaMaiQTD, avaJunQTD, avaJulQTD, avaAgoQTD, avaSetQTD, avaOutQTD, avaNovQTD, avaDezQTD;
    private double preJan, preFev, preMar, preAbr, preMai, preJun, preJul, preAgo, preSet, preOut, preNov, preDez;
    private double avaJan, avaFev, avaMar, avaAbr, avaMai, avaJun, avaJul, avaAgo, avaSet, avaOut, avaNov, avaDez;
    private double horJan, horFev, horMar, horAbr, horMai, horJun, horJul, horAgo, horSet, horOut, horNov, horDez;

    public void setRelatorioAnual() {
        parametros.put("ano", ano);
        ano = ano - 1900;
        System.out.println("ANO - " + ano);
//        ObservableList<Financeiro> listaFinanceiro = Controller.getFinanceiros();
        ObservableList<Financeiro> listaFinanceiro = StaticLista.getListaGlobalFinanceiro();
        for (Financeiro fi : listaFinanceiro) {
//            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).getMonth() == 0) {
//                System.out.println("FIIII " + fi.getValorPago());
//                System.out.println("FINANCEIRO mes de janeiro " + fi);
//            }
            if (UtilConverter.converterLocalDateToUtilDate(
                    fi.getDataInicial()).after(new java.sql.Date(ano, 0, 0))
                    && UtilConverter.converterLocalDateToUtilDate(
                            fi.getDataInicial()).before(new java.sql.Date(ano, 0, 32))) {
                setAnual(fi, 1);
            }
        }
        addListaAnual(1);
        String preAvaliacao = EnumServico.PreAvaliacao.getServico();
        String avaliacao = EnumServico.Avaliacao.getServico();
        String horaAdicional = EnumServico.HoraAdicional.getServico();
        pre = new Anual(2, preAvaliacao);
        ava = new Anual(2, avaliacao);
        hor = new Anual(2, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 1, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 1, 29))) {
                setAnual(fi, 2);
            }
        }
        addListaAnual(2);
        pre = new Anual(3, preAvaliacao);
        ava = new Anual(3, avaliacao);
        hor = new Anual(3, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 2, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 2, 32))) {
                setAnual(fi, 3);
            }
        }
        addListaAnual(3);
        pre = new Anual(4, preAvaliacao);
        ava = new Anual(4, avaliacao);
        hor = new Anual(4, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 3, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 3, 31))) {
                setAnual(fi, 4);
            }
        }
        addListaAnual(4);
        pre = new Anual(5, preAvaliacao);
        ava = new Anual(5, avaliacao);
        hor = new Anual(5, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 4, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 4, 32))) {
                setAnual(fi, 5);
            }
        }
        addListaAnual(5);
        pre = new Anual(6, preAvaliacao);
        ava = new Anual(6, avaliacao);
        hor = new Anual(6, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 5, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 5, 31))) {
                setAnual(fi, 6);
            }
        }
        addListaAnual(6);
        pre = new Anual(7, preAvaliacao);
        ava = new Anual(7, avaliacao);
        hor = new Anual(7, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 6, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 6, 32))) {
                setAnual(fi, 7);
            }
        }
        addListaAnual(7);
        pre = new Anual(8, preAvaliacao);
        ava = new Anual(8, avaliacao);
        hor = new Anual(8, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 7, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 7, 32))) {
                setAnual(fi, 8);
            }
        }
        addListaAnual(8);
        pre = new Anual(9, preAvaliacao);
        ava = new Anual(9, avaliacao);
        hor = new Anual(9, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 8, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 8, 31))) {
                setAnual(fi, 9);
            }
        }
        addListaAnual(9);
        pre = new Anual(10, preAvaliacao);
        ava = new Anual(10, avaliacao);
        hor = new Anual(10, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 9, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 9, 32))) {
                setAnual(fi, 10);
            }
        }
        addListaAnual(10);
        pre = new Anual(11, preAvaliacao);
        ava = new Anual(11, avaliacao);
        hor = new Anual(11, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 10, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 10, 32))) {
                setAnual(fi, 11);
            }
        }
        addListaAnual(11);
        pre = new Anual(12, preAvaliacao);
        ava = new Anual(12, avaliacao);
        hor = new Anual(12, horaAdicional);
        for (Financeiro fi : listaFinanceiro) {
            if (UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).after(new java.sql.Date(ano, 11, 0)) && UtilConverter.converterLocalDateToUtilDate(fi.getDataInicial()).before(new java.sql.Date(ano, 11, 32))) {
                setAnual(fi, 12);
            }
        }
        addListaAnual(12);

        double total = 0;
        int div = 0;
        for (Anual a : listaTotal) {
            total += a.getValor();
            div++;
        }
        total = total / div;
        parametros.put("ttMedia", total);
    }

//    double total = 0;
//        int div = 0;
//        for (Anual a : listaTotal) {
//            total += a.getValor();
//            div++;
//        }
//        System.out.println("div " + div);
//        total = total / div;
//        parametros.put("ttMedia", Controller.converterDoubleDoisDecimais(total));
    public void setParametrosAnual() {
        try {
            parametros.put("1janQTD", preJanQTD);
            parametros.put("1fevQTD", preFevQTD);
            parametros.put("1marQTD", preMarQTD);
            parametros.put("1abrQTD", preAbrQTD);
            parametros.put("1maiQTD", preMaiQTD);
            parametros.put("1junQTD", preJunQTD);
            parametros.put("1julQTD", preJulQTD);
            parametros.put("1agoQTD", preAgoQTD);
            parametros.put("1setQTD", preSetQTD);
            parametros.put("1outQTD", preOutQTD);
            parametros.put("1novQTD", preNovQTD);
            parametros.put("1dezQTD", preDezQTD);
            parametros.put("2janQTD", avaJanQTD);
            parametros.put("2fevQTD", avaFevQTD);
            parametros.put("2marQTD", avaMarQTD);
            parametros.put("2abrQTD", avaAbrQTD);
            parametros.put("2maiQTD", avaMaiQTD);
            parametros.put("2junQTD", avaJunQTD);
            parametros.put("2julQTD", avaJulQTD);
            parametros.put("2agoQTD", avaAgoQTD);
            parametros.put("2setQTD", avaSetQTD);
            parametros.put("2outQTD", avaOutQTD);
            parametros.put("2novQTD", avaNovQTD);
            parametros.put("2dezQTD", avaDezQTD);

            parametros.put("preJan", preJan);
            parametros.put("preFev", preFev);
            parametros.put("preMar", preMar);
            parametros.put("preAbr", preAbr);
            parametros.put("preMai", preMai);
            parametros.put("preJun", preJun);
            parametros.put("preJul", preJul);
            parametros.put("preAgo", preAgo);
            parametros.put("preSet", preSet);
            parametros.put("preOut", preOut);
            parametros.put("preNov", preNov);
            parametros.put("preDez", preDez);

            parametros.put("avaJan", avaJan);
            parametros.put("avaFev", avaFev);
            parametros.put("avaMar", avaMar);
            parametros.put("avaAbr", avaAbr);
            parametros.put("avaMai", avaMai);
            parametros.put("avaJun", avaJun);
            parametros.put("avaJul", avaJul);
            parametros.put("avaAgo", avaAgo);
            parametros.put("avaSet", avaSet);
            parametros.put("avaOut", avaOut);
            parametros.put("avaNov", avaNov);
            parametros.put("avaDez", avaDez);

            parametros.put("horJan", horJan);
            parametros.put("horFev", horFev);
            parametros.put("horMar", horMar);
            parametros.put("horAbr", horAbr);
            parametros.put("horMai", horMai);
            parametros.put("horJun", horJun);
            parametros.put("horJul", horJul);
            parametros.put("horAgo", horAgo);
            parametros.put("horSet", horSet);
            parametros.put("horOut", horOut);
            parametros.put("horNov", horNov);
            parametros.put("horDez", horDez);
        } catch (Exception ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpar() {
        parametros.clear();
        pre = new Anual(1, EnumServico.PreAvaliacao.getServico());
        ava = new Anual(1, EnumServico.Avaliacao.getServico());
        hor = new Anual(1, EnumServico.HoraAdicional.getServico());
        listaAnual = new ArrayList<Anual>();
        isAnual = false;
        preJanQTD = 0;
        preFevQTD = 0;
        preMarQTD = 0;
        preAbrQTD = 0;
        preMaiQTD = 0;
        preJunQTD = 0;
        preJulQTD = 0;
        preAgoQTD = 0;
        preSetQTD = 0;
        preOutQTD = 0;
        preNovQTD = 0;
        preDezQTD = 0;
        avaJanQTD = 0;
        avaFevQTD = 0;
        avaMarQTD = 0;
        avaAbrQTD = 0;
        avaMaiQTD = 0;
        avaJunQTD = 0;
        avaJulQTD = 0;
        avaAgoQTD = 0;
        avaSetQTD = 0;
        avaOutQTD = 0;
        avaNovQTD = 0;
        avaDezQTD = 0;
        preJan = 0;
        preFev = 0;
        preMar = 0;
        preAbr = 0;
        preMai = 0;
        preJun = 0;
        preJul = 0;
        preAgo = 0;
        preSet = 0;
        preOut = 0;
        preNov = 0;
        preDez = 0;
        avaJan = 0;
        avaFev = 0;
        avaMar = 0;
        avaAbr = 0;
        avaMai = 0;
        avaJun = 0;
        avaJul = 0;
        avaAgo = 0;
        avaSet = 0;
        avaOut = 0;
        avaNov = 0;
        avaDez = 0;
        horJan = 0;
        horFev = 0;
        horMar = 0;
        horAbr = 0;
        horMai = 0;
        horJun = 0;
        horJul = 0;
        horAgo = 0;
        horSet = 0;
        horOut = 0;
        horNov = 0;
        horDez = 0;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainRelatorio(VBox mainRelatorio) {
        this.mainRelatorio = mainRelatorio;
    }

    public void setIsAnual(boolean isAnual) {
        this.isAnual = isAnual;
    }

}
