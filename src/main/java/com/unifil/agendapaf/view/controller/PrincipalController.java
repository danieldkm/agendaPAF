package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooser;
import com.unifil.agendapaf.DateChooserSkin;
import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.notificador.Agendador;
import com.unifil.agendapaf.service.ContatoService;
import com.unifil.agendapaf.service.TelefoneService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.UtilConverter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrincipalController {

    public PrincipalController(Stage stage) {
        this.stage = stage;
    }

    public PrincipalController() {
    }

    @FXML
    public void initialize() {
        sceneManager = SceneManager.getInstance();
        Platform.setImplicitExit(false);
        Agendador agendador = new Agendador();
        try {
            agendador.inicia();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dc = new DateChooser(principalId, txtArea);
        pane.getChildren().add(dc);
        lista = Controller.getEmpresasHomologadas();
        principalId.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                atalhos(t);
            }
        });
        Usuario ul = sceneManager.getUsuarioLogado();
        if (!ul.getTipo().equals("Gerencial")) {
            mCadastros.setVisible(false);
            mRelatorio.setVisible(false);
            miConsultarHistorico.setVisible(false);
        }
    }

    @FXML
    private void setOnActionMenuLogout() {
        System.out.println("logout");
//        isLogout = true;
        stage.close();
        sceneManager.rerecarregar();
        sceneManager.showNewLogin(false);
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
    private double paneW = 0;
    private double paneH = 0;
    private ObservableList<EmpresasHomologadas> lista = FXCollections.observableArrayList();
    private Stage stage;
    private RelatorioController relatorioController;
    private SceneManager sceneManager;
//    private LoginController application;
//    private boolean isLogout = false;

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
        sceneManager.showFinanceiro(false, null, null);
    }

    @FXML
    protected void iniciarCadastroFeriado() {
        sceneManager.showFeriado();
    }

    @FXML
    protected void iniciarCadastroEmpresa() {
        sceneManager.showEmpresa(false);
    }

    @FXML
    protected void iniciarCadastroUsuario() {
        sceneManager.showUsuario();
    }

    @FXML
    protected void iniciarCadastroAgenda() {
        sceneManager.setDataSelecionada(null);
        sceneManager.showAgenda(null);
    }

    @FXML
    protected void iniciarCadastroLaudo() {
        sceneManager.showLaudo(null);
    }

    @FXML
    protected void iniciarConsultaAgenda() {
        sceneManager.showTabelaAgenda();
    }

    @FXML
    protected void iniciarConsultaHistorico() {
        sceneManager.showTabelaHistorico();
    }

    @FXML
    protected void iniciarConsultaEmpresa() {
        sceneManager.showTabelaEmpresa(false, false, false, false, true, false, false);
    }

    @FXML
    protected void iniciarConsultaEmpresasHomologadas() {
        sceneManager.showTabelaEmpresasHomologadas();
    }

    @FXML
    protected void iniciarCalendarioSemestral() {
        String n = null;
        do {
            n = UtilDialog.criarDialogInput("Confirmação", "1 - Primeiro semestre\n2 - Segundo semestre", "Digite 1 ou 2");
            if (n == null) {
                break;
            }
        } while (!n.equals("1") && !n.equals("2"));
        if (n != null) {
            sceneManager.showCalendarioSemestral(n);
        }
    }

    @FXML
    protected void iniciarRelatorio() {
        sceneManager.showRelatorio();
    }

    public void showAlert() {
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

            ca2.setTime(UtilConverter.converterLocalDateToUtilDate(eh.getDataAviso()));
            ca2.set(Calendar.HOUR_OF_DAY, 0);
            ca2.set(Calendar.MINUTE, 0);
            ca2.set(Calendar.SECOND, 0);
            ca2.set(Calendar.MILLISECOND, 0);

            // se ca1 for maior que ca2 ou se ca1 for igual a ca2 entra no if
            if (ca1.after(ca2) || ca1.equals(ca2)) {
                if (sceneManager.getAlertaController() == null) {
                    if (eh.getVisualizado().equals("NAO")) {
                        sceneManager.showAlerta();
                        for (Empresa empresa : StaticLista.getListaGlobalEmpresa()) {
                            if (empresa.getId().equals(eh.getIdEmpresa().getId())) {
                                ContatoService cs = new ContatoService();
                                Contato contato = null;
                                for (Contato c : cs.findByIdEmpresa(empresa)) {
                                    if (c.selecionadoBoolean()) {
                                        contato = c;
                                        break;
                                    }
                                }
                                JPA.em(false).close();
                                TelefoneService ts = new TelefoneService();
                                Telefone tel = null;
                                for (Telefone t : ts.findByIdEmpresa(empresa)) {
                                    if (t.selecionadoBoolean()) {
                                        tel = t;
                                        break;
                                    }
                                }
                                JPA.em(false).close();
                                sceneManager.getAlertaController().setTxtaTexto("\nEmpresa: " + eh.getIdEmpresa().getDescricao()
                                        + "\nContato: " + contato.getNome()
                                        + "\nE-mail: " + eh.getEmail()
                                        + "\nTel: " + tel.getFixo()
                                        + "\nData homologada: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(eh.getDataHomologada()), "dd/MM/yyyy")
                                );
                                break;
                            }
                        }
                    }
                } else {
                    if (eh.getVisualizado().equals("NAO")) {
                        for (Empresa empresa : Controller.getEmpresas()) {
                            if (empresa.getId().equals(eh.getIdEmpresa().getId())) {
                                ContatoService cs = new ContatoService();
                                Contato contato = null;
                                for (Contato c : cs.findByIdEmpresa(empresa)) {
                                    if (c.selecionadoBoolean()) {
                                        contato = c;
                                        break;
                                    }
                                }
                                JPA.em(false).close();
                                TelefoneService ts = new TelefoneService();
                                Telefone tel = null;
                                for (Telefone t : ts.findByIdEmpresa(empresa)) {
                                    if (t.selecionadoBoolean()) {
                                        tel = t;
                                        break;
                                    }
                                }
                                JPA.em(false).close();
                                sceneManager.getAlertaController().setTxtaTexto("\n------------------------------------------"
                                        + "\nEmpresa: " + eh.getIdEmpresa().getDescricao()
                                        + "\nContato: " + contato.getNome()
                                        + "\nE-mail: " + eh.getEmail()
                                        + "\nTel: " + tel.getFixo()
                                        + "\nData homologada: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(eh.getDataHomologada()), "dd/MM/yyyy")
                                );
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
        UtilDialog.criarDialogInfomation("Sobre", "UniFil - PAF-ECF",
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
//            ALERTA o avisa que quer fechar o stage, assim aciona setOnClose
            stage.fireEvent(
                    new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPrincipalId(BorderPane principalId) {
        this.principalId = principalId;
    }
}
