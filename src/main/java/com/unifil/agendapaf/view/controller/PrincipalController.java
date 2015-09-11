package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooser;
import com.unifil.agendapaf.DateChooserSkin;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.notificador.Agendador;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticCalendar;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.statics.StaticPopUp;
import com.unifil.agendapaf.util.TrayIcon;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItemBuilder;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class PrincipalController extends FXMLController implements Initializable {

//    public void setApp(LoginController application) {
//        this.application = application;
//    }
    public PrincipalController(Stage stage) {
        this.stage = stage;
    }

    public PrincipalController() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Iniciando o start da tela principal");
        stage = primaryStage;
        Platform.setImplicitExit(false);
        principalId = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Principal.getCaminho()));
        Scene scene = new Scene(principalId);
        stage.setScene(scene);
        stage.setTitle("AgendaPAF");
        stage.setResizable(true);
//        stage.initOwner(this.myParent);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.getIcons().add(Controller.icoPAF);
        stage.show();
        stage.toFront();
//        stage.setOnHidden(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent t) {
//                if (stage != null) {
//                    stage.close();
//                }
//                if (isLogout) {
//                    try {
//                        System.out.println("Fechando conexao");
//                        Dao.getCon().close();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                    System.exit(0);
//                }
//            }
//        });
//
//        stage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
//                System.out.println("minimized:" + t1.booleanValue());
//            }
//        });
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent t) {
//                System.exit(0);
//            }
//        });
        StaticPopUp.setPopUp(new ContextMenu());
        criarPopUp();
        trayIcon = new TrayIcon();
        trayIcon.createTrayIcon(stage);
//        trayIcon = new TrayIcon();
//        trayIcon.createTrayIcon(stage);
//        LoginController.hideStageLogin();
        System.out.println("Finalizando o start da tela principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("Iniciando o initialize da tela principal");
        if (utilDialog == null) {
            utilDialog = new UtilDialog();
        }
        utilConverter = new UtilConverter();
        Agendador agendador = new Agendador();
        try {
            agendador.inicia();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dc = new DateChooser(principalId, txtArea);
        pane.getChildren().add(dc);
        lista = Controller.getEmpresasHomologadas();
        showAlert();
        principalId.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                atalhos(t);
            }
        });

        Usuario ul = StaticObject.getUsuarioLogado();
        if (!ul.getTipo().equals("Gerencial")) {
            mCadastros.setVisible(false);
            mRelatorio.setVisible(false);
            miConsultarHistorico.setVisible(false);
        }

        StaticLista.setListaGlobalAgenda(Controller.getAgendas());
        StaticLista.setListaGlobalHistorico(Controller.getHistoricos());
        StaticLista.setListaGlobalFeriado(Controller.getFeriados());
        StaticLista.setListaGlobalEstado(Controller.getEstados());
        StaticLista.setListaGlobalCidade(Controller.getCidades());
        StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
        StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
        StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
        StaticLista.setListaGlobalEmpresasHomologadas(Controller.getEmpresasHomologadas());

        System.out.println("Finalizando o initialize da tela principal");
    }

    @FXML
    private void setOnActionMenuLogout() {
        System.out.println("logout");
        isLogout = true;
        stage.close();
        RunAnotherApp.runAnotherApp(LoginController.class);
    }

    @FXML
    public Pane pane;
    @FXML
    public BorderPane principalId;
    @FXML
    private MenuItem miCadastroEmpresa;
    @FXML
    private MenuItem miCadastroUsuario;
    @FXML
    private MenuItem miConsultaAgenda;
    @FXML
    private MenuItem miConsultarHistorico;
    @FXML
    private MenuItem miConsultaEmpresa;
    @FXML
    private MenuItem miConsultaEmpresasHomologadas;
    @FXML
    private MenuItem miCadastroAgenda;
    @FXML
    private MenuItem miAbout;
    @FXML
    private MenuItem miConfigurar;
    @FXML
    private MenuItem miLaudo;
    @FXML
    private Menu mCadastros;
    @FXML
    private Menu mRelatorio;
    @FXML
    private Menu mSair;
    @FXML
    private Menu mSincronizar;
    @FXML
    private MenuItem miLogout;
    @FXML
    private MenuItem miFeriado;
    @FXML
    private TextArea txtArea;
    @FXML
    private TextArea txtArea2;
    @FXML
    public SplitPane spPane;

    private DateChooser dc;
    private LoginController application;
    private double paneW = 0;
    private double paneH = 0;
    private ObservableList<EmpresasHomologadas> lista = FXCollections.observableArrayList();
    private static Stage stage;
    private boolean isLogout = false;
    private RelatorioController relatorioController;
    private TrayIcon trayIcon;
    private UtilDialog utilDialog;
    private UtilConverter utilConverter;

    /**
     * Metodo para redimensionar o calendario conforme a resolução da tela
     */
    @FXML
    private void mouseMovedPrincipal() {
        if (paneW != pane.getWidth() || paneH != pane.getHeight()) {
            paneW = pane.getWidth();
            paneH = pane.getHeight();
            dc.setPrefSize(paneW, paneH);

        }
    }

    @FXML
    protected void iniciarCadastroFinanceiro() {
        RunAnotherApp.runAnotherApp(FinanceiroController.class);
    }

    @FXML
    protected void iniciarCadastroFeriado() {
        RunAnotherApp.runAnotherApp(FeriadoController.class);
    }

    @FXML
    protected void iniciarCadastroEmpresa() {
        RunAnotherApp.runAnotherApp(EmpresaController.class);
    }

    @FXML
    protected void iniciarCadastroUsuario() {
        RunAnotherApp.runAnotherApp(UsuarioController.class);
    }

    @FXML
    protected void iniciarCadastroAgenda() {
        RunAnotherApp.runAnotherApp(AgendarController.class);
    }

    @FXML
    protected void iniciarCadastroLaudo() {
        RunAnotherApp.runAnotherApp(LaudoController.class);
    }

    @FXML
    protected void iniciarConsultaAgenda() {
        RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
    }

    @FXML
    protected void iniciarConsultaHistorico() {
        RunAnotherApp.runAnotherApp(TabelaHistoricoController.class);
    }

    @FXML
    protected void iniciarConsultaEmpresa() {
        StaticBoolean.setConsulta(true);
        RunAnotherApp.runAnotherApp(TabelaEmpresaController.class);
    }

    @FXML
    protected void iniciarConsultaEmpresasHomologadas() {
        RunAnotherApp.runAnotherApp(TabelaEmpresasHomologadasController.class);
    }

    @FXML
    protected void iniciarCalendarioSemestral() {
        RunAnotherApp.runAnotherApp(CalendarioSemestralController.class);
    }

    @FXML
    protected void iniciarRelatorio() {
        RunAnotherApp.runAnotherApp(RelatorioController.class);
    }

    @FXML
    private void setOnMousePressedTextArea(MouseEvent me) {
        System.out.println("RELEASED");
//        if (me.isPrimaryButtonDown()) {
//            setX(me.getX());
//            setY(me.getY());
//            System.out.println("X " + me.getSceneX());
//            System.out.println("Y " + me.getSceneY());
//            System.out.println("Bot�o esquerdo");
//            runAnotherApp(PopUpController.class);
//        }
    }

    @FXML
    private void onActionMSincronizar() {
        System.out.println("Reavaliar");
//        Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalConfirmarSincronizarBD.getMensagem());
//        if (result.get() == ButtonType.OK) {
//            Dao.setCon(Conexao.getInstance());
//            if (Dao.getCon() == null) {
//                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroConexaoBD.getMensagem());
//            } else {
//                ObservableList<Script> a = selectScript(ConexaoLocal.getInstance());
////                System.out.println("listaScript size  " + a.size());
//                if (a.size() > 0) {
//                    executarConteudoScript(a, Dao.getCon(), true);
//                }
////                Dao<Script> dao = new Dao(Script.class);
//                System.out.println("SERVIDOR " + Controller.dbServidor);
////                if (Controller.dbServidor) {
//                executarConteudoScript(Controller.getScript(), ConexaoLocal.getInstance(), false);
////                } else {
////                    FXMLController.criarDialogInfomation("Informa��o", "Sincronizar BD local", "N�o foi possivel estabelecer conex�o com o servidor");
////                }
//            }
//            Dao.setCon(ConexaoLocal.getInstance());
//        }
    }

    private void showAlert() {
        Calendar ca1 = new GregorianCalendar();
        Calendar ca2 = new GregorianCalendar();
        for (EmpresasHomologadas eh : lista) {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ca1.setTime(sqlDate);
            ca1.set(Calendar.HOUR_OF_DAY, 0);
            ca1.set(Calendar.MINUTE, 0);
            ca1.set(Calendar.SECOND, 0);
            ca1.set(Calendar.MILLISECOND, 0);

            ca2.setTime(utilConverter.converterLocalDateToUtilDate(eh.getDataAviso()));
            ca2.set(Calendar.HOUR_OF_DAY, 0);
            ca2.set(Calendar.MINUTE, 0);
            ca2.set(Calendar.SECOND, 0);
            ca2.set(Calendar.MILLISECOND, 0);

            // se ca1 for maior que ca2 ou se ca1 for igual a ca2 entra no if
            if (ca1.after(ca2) || ca1.equals(ca2)) {
                if (AlertaController.stage == null) {
                    if (eh.getVisualizado().equals("NAO")) {
                        RunAnotherApp.runAnotherApp(AlertaController.class);
                        for (Empresa empresa : Controller.getEmpresas()) {
//                        for (Empresa empresa : Conf.getEmpresas()) {
                            if (empresa.getId().equals(eh.getIdEmpresa().getId())) {
                                AlertaController.txtaTexto.setText(AlertaController.txtaTexto.getText() + "\nEmpresa: " + eh.getIdEmpresa().getDescricao()
                                        + "\nContato: " + empresa.getNomeContato()
                                        + "\nE-mail: " + eh.getEmail()
                                        + "\nTel " + empresa.getTelefone()
                                        + new Button("Testando TODO"));
                                break;
                            }
                        }
                    }
                } else {
                    if (eh.getVisualizado().equals("NAO")) {
                        for (Empresa empresa : Controller.getEmpresas()) {
//                        for (Empresa empresa : Conf.getEmpresas()) {
                            if (empresa.getId().equals(eh.getIdEmpresa().getId())) {
                                AlertaController.txtaTexto.setText(AlertaController.txtaTexto.getText() + "\n------------------------------------------"
                                        + "\nEmpresa: " + eh.getIdEmpresa().getDescricao()
                                        + "\nContato: " + empresa.getNomeContato()
                                        + "\nE-mail: " + eh.getEmail()
                                        + "\nTel " + empresa.getTelefone());
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

    @FXML
    private void sobre() {
        utilDialog.criarDialogInfomation("Sobre", "UniFil - PAF-ECF",
                "Desenvolvido por: Daniel K. Morita\n"
                + "Ver.: 1.1\n"
                + "Copyright - 2015 PAF-ECF. Todos os direitos reservados.\n"
                + "Informações do PAF-ECF\n"
                + "Técnico Responsável: Sando T. Pinto\n"
                + "E-mail: paf.ecf@unifil.br\n"
                + "Tel.:(43)3375-7326\n"
                + "Homologadores:\n"
                + "José Ricardo Guidetti Junior\n"
                + "Sando T. Pinto\n"
                + "Daniel K. Morita");
    }

    public void criarPopUp() {
        try {
            StaticPopUp.getPopUp().getItems().addAll(
                    MenuItemBuilder.create()
                    .text("Agendar")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            RunAnotherApp.runAnotherApp(AgendarController.class);
                        }
                    })
                    .
                    build(),
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Reagendar")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            StaticBoolean.setReagendamento(true);
                            RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
                        }
                    })
                    .build(),
                    /*.graphic(createIcon())*/
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Atualizar Agenda")
                    .onAction(new EventHandler() {

                        @Override
                        public void handle(Event t) {
                            AgendaService as = new AgendaService();
                            ObservableList<Agenda> listaAgendas = as.findByDate(StaticCalendar.getDataSelecionada());
                            JPA.em(false).close();
                            if (listaAgendas.size() == 0) {
                                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                            } else {
                                StaticBoolean.setUpdate(true);
                                RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
                            }
                        }

                    }).build(),
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Cancelar Agendamento")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            AgendaService as = new AgendaService();
                            ObservableList<Agenda> listaAgendas = as.findByDate(StaticCalendar.getDataSelecionada());
                            JPA.em(false).close();
                            if (listaAgendas.size() == 0) {
                                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                            } else {
                                boolean dataAgendada = false;
                                for (Agenda agenda : listaAgendas) {
                                    if (agenda.getStatusAgenda().equals("Data Agendada")) {
                                        dataAgendada = true;
                                        break;
                                    }
                                }
                                if (dataAgendada) {
                                    StaticBoolean.setCancelamento(true);
                                    RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
                                } else {
                                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                                }
                            }
                        }
                    })
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!! Classe: Controller.class  Metodo: criarPopUp");
        }
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }

    private void atalhos(KeyEvent t) {
//        System.out.println("ATALHO PRINCIPAL");
        // Verifica se a tecla a e o control est�o pressionados
        if (t.getCode() == KeyCode.A && t.isControlDown()) {
//                    System.out.println("CTRL + " + t.getCode());
            iniciarCadastroAgenda();
        } else if (t.getCode() == KeyCode.E && t.isControlDown()) {
            iniciarCadastroEmpresa();
        } else if (t.getCode() == KeyCode.U && t.isControlDown()) {
            iniciarCadastroUsuario();
        } else if (t.getCode() == KeyCode.R && t.isControlDown()) {
            iniciarRelatorio();
        } else if (t.getCode() == KeyCode.S && t.isControlDown()) {
            iniciarCalendarioSemestral();
        } else if (t.getCode() == KeyCode.LEFT) {
            DateChooserSkin.getMonthBack().arm();
            DateChooserSkin.getMonthBack().fire();
        } else if (t.getCode() == KeyCode.RIGHT) {
            DateChooserSkin.getMonthForward().arm();
            DateChooserSkin.getMonthForward().fire();
        } else if (t.getCode() == KeyCode.ESCAPE) {
            Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Confirme para finalizar o programa");
            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        }
    }
}
