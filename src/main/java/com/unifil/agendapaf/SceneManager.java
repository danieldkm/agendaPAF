package com.unifil.agendapaf;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.email.Email;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.PopUp;
import com.unifil.agendapaf.util.TrayIcon;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.controller.AgendarController;
import com.unifil.agendapaf.view.controller.AlertaController;
import com.unifil.agendapaf.view.controller.CalendarioSemestralController;
import com.unifil.agendapaf.view.controller.EmailController;
import com.unifil.agendapaf.view.controller.EmpresaController;
import com.unifil.agendapaf.view.controller.EscolherDocsController;
import com.unifil.agendapaf.view.controller.FeriadoController;
import com.unifil.agendapaf.view.controller.FerramentaBDController;
import com.unifil.agendapaf.view.controller.FerramentaEmailController;
import com.unifil.agendapaf.view.controller.FinanceiroController;
import com.unifil.agendapaf.view.controller.InicialController;
import com.unifil.agendapaf.view.controller.LaudoController;
import com.unifil.agendapaf.view.controller.FerramentaLaudoController;
import com.unifil.agendapaf.view.controller.LoginController;
import com.unifil.agendapaf.view.controller.MotivoReagendamentoController;
import com.unifil.agendapaf.view.controller.NewLoginController;
import com.unifil.agendapaf.view.controller.FerramentaFinanceiroController;
import com.unifil.agendapaf.view.controller.PrincipalController;
import com.unifil.agendapaf.view.controller.RelatorioController;
import com.unifil.agendapaf.view.controller.RelatorioFinanceiroController;
import com.unifil.agendapaf.view.controller.TabelaAgendaController;
import com.unifil.agendapaf.view.controller.TabelaEmpresaController;
import com.unifil.agendapaf.view.controller.TabelaEmpresasHomologadasController;
import com.unifil.agendapaf.view.controller.TabelaFeriadoController;
import com.unifil.agendapaf.view.controller.TabelaFinanceiroController;
import com.unifil.agendapaf.view.controller.TabelaHistoricoController;
import com.unifil.agendapaf.view.controller.TabelaUsuarioController;
import com.unifil.agendapaf.view.controller.UsuarioController;
import com.unifil.agendapaf.view.controller.VisualizadorMotivoController;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author danielmorita
 */
public class SceneManager {

    private Stage inicialStage;
    private Stage principalStage;
    private Stage agendaStage;

    private VBox loginLayout;

    private MainApp application;
    private static SceneManager instance;

    private InicialController inicialController;
    private NewLoginController newLoginController;
    private LoginController loginController;
    private PrincipalController principalController;
    private AgendarController agendaController;
    private EmpresaController empresaController;
    private TabelaEmpresaController tabelaEmpresaController;
    private TabelaAgendaController tabelaAgendaController;
    private AlertaController alertaController;
    private CalendarioSemestralController calendarioSemestralController;
    private FeriadoController feriadoController;
    private FerramentaBDController ferramentaBDController;
    private FinanceiroController financeiroController;
    private LaudoController laudoController;
    private FerramentaLaudoController ferramentaLaudoController;
    private MotivoReagendamentoController motivoReagendamentoController;
    private FerramentaFinanceiroController ferramentaFinanceiroController;
    private FerramentaEmailController ferramentaEmailController;
    private RelatorioController relatorioController;
    private RelatorioFinanceiroController relatorioFinanceiroController;
    private TabelaEmpresasHomologadasController empresasHomologadasController;
    private TabelaFeriadoController tabelaFeriadoController;
    private TabelaFinanceiroController tabelaFinanceiroController;
    private TabelaHistoricoController tabelaHistoricoController;
    private TabelaUsuarioController tabelaUsuarioController;
    private UsuarioController usuarioController;
    private VisualizadorMotivoController visulizadorMotivoController;
    private EscolherDocsController escolherDocsController;
    private EmailController emailController;

    private Usuario usuarioLogado;
    private Agenda agendaEncontrada;
    private Empresa empresaEncontrada;
    private Feriado feriadoEncontrado;
    private Financeiro financeiroEncontrada;
    private Usuario usuarioEncontrada;

    private PopUp popUp;

    private Boolean reagendamento = Boolean.FALSE;
    private Boolean cancelamento = Boolean.FALSE;
    private Boolean update = Boolean.FALSE;
    private Boolean BDLOCAL = Boolean.FALSE;
    private Boolean BDSERVIDOR = Boolean.FALSE;

    private LocalDate dataSelecionada;
    private Mensagem mensagem;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    protected void setPrimaryStage(MainApp application) {
        this.application = application;
        initPrimaryStage();
    }

    private void initPrimaryStage() {
//        try {//TODO habilitar apos finalizar ;
//            Util.setLogs("SceneManager");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
////        showLogin();
        inicial();

    }

    public void rerecarregar() {
//        JPA.getFactory();
        StaticLista.setListaGlobalAgenda(Controller.getAgendas());
        StaticLista.setListaGlobalHistorico(Controller.getHistoricos());
        StaticLista.setListaGlobalFeriado(Controller.getFeriados());
        StaticLista.setListaGlobalEstado(Controller.getEstados());
        StaticLista.setListaGlobalCidade(Controller.getCidades());
        StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
        StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
        StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
        StaticLista.setListaGlobalEmpresasHomologadas(Controller.getEmpresasHomologadas());
    }

    public void inicial() {
        try {
            Stage stage = this.application.getStage();
            FXMLLoader rootLoader = new FXMLLoader();
            rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLInicial.getCaminho()));
            Parent loginLayout = rootLoader.load();
            inicialController = rootLoader.getController();
            inicialController.setStage(stage);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            criarPadrao("Carregando", stage, loginLayout);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNewLogin(boolean erro) {
        try {
            Stage rootStage = new Stage();
            FXMLLoader rootLoader = new FXMLLoader();
            rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLNewLogin.getCaminho()));
            Parent parent = rootLoader.load();
            newLoginController = rootLoader.getController();
            newLoginController.setStage(rootStage);
            rootStage.initStyle(StageStyle.UNDECORATED);
            rootStage.setResizable(false);
            criarPadrao("Login", rootStage, parent);
            if (erro) {
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao tentar estabelecer comunicação com o Bando de dados!!\nO programa será encerrado.");
                Platform.exit();
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showLogin() {
        try {
            Stage rootStage = this.application.getStage();
            FXMLLoader rootLoader = new FXMLLoader();
            rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLLogin.getCaminho()));
            loginLayout = (VBox) rootLoader.load();
            loginController = rootLoader.getController();
            loginController.setStage(rootStage);
            criarPadrao("Agenda PAF-ECF", rootStage, loginLayout);

        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showPrincipal() {
        try {
            principalStage = new Stage();
            mensagem = new Mensagem(principalStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLPrincipal.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            principalController = loader.getController();
            principalController.setStage(principalStage);
            principalController.setPrincipalId(layout);

            principalStage.getIcons().add(new Image(EnumCaminho.ImgLogoPAFECFUniFil.getCaminho()) {
            });

            criarPadrao("Agenda PAF-ECF", principalStage, layout);
            TrayIcon trayIcon = new TrayIcon();
            trayIcon.createTrayIcon(principalStage);
            popUp = new PopUp();
            popUp.criarPopUp();
            principalController.showAlert();
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showAgenda(String motivo) {
        try {
            agendaStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLAgenda.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            agendaController = loader.getController();
            agendaController.setStage(agendaStage);
            if (dataSelecionada != null) {
                agendaController.setDataSelecionada(dataSelecionada);
            }
            agendaController.setMotivo(motivo);
            criarPadraoModal("Agendamento", agendaStage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start da agenda", e);
        }
    }

    /**
     * Construir tela de empresa
     *
     * @param isTabelaEmpresa variavel boolena para verificar se esta sendo
     * aberto atraves da tabela de empresa
     */
    public void showEmpresa(boolean isTabelaEmpresa) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLEmpresa.getCaminho()));
            Parent layout = loader.load();
            empresaController = loader.getController();
            empresaController.setStage(stage);
            empresaController.setMainEmpresa(layout);
            empresaController.setIsTabelaEmpresa(isTabelaEmpresa);

            criarPadraoModal("Cadastro de empresa", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro do start empresa", e);
        }
    }

    /**
     * Construir o palco e a cena para a tela da tabela de empresa
     *
     * @param isEmpresa variavel boolena para verificar se esta sendo aberto
     * atraves da tela de cadastro de empresa
     * @param isAgenda veem da tela de agenda
     * @param isTabelaAgenda veem da tabela de agenda
     * @param isRelatorio veem da tela de relatorio
     * @param isConsulta veem da tela de consulta
     * @param isFinanceiro veem da tela de financeiro
     * @param isLaudo veem da tela de laudo
     */
    public void showTabelaEmpresa(boolean isEmpresa, boolean isAgenda, boolean isTabelaAgenda, boolean isRelatorio, boolean isConsulta, boolean isFinanceiro, boolean isLaudo) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaEmpresa.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaEmpresaController = loader.getController();
            tabelaEmpresaController.setStage(stage);
            tabelaEmpresaController.setBooleans(isEmpresa, isAgenda, isTabelaAgenda, isRelatorio, isConsulta, isFinanceiro, isLaudo);
            tabelaEmpresaController.setMainTbEmpresa(layout);
            criarPadraoModal("Tabela de empresa", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela empresa", e);
        }
    }

    public void showTabelaAgenda() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaAgenda.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaAgendaController = loader.getController();
            tabelaAgendaController.setStage(stage);
            tabelaAgendaController.setMainTbAgenda(layout);
            tabelaAgendaController.setDataSelecionada(dataSelecionada);
            criarPadraoModal("Tabela de agenda", stage, layout);

        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela agenda", e);
        }
    }

    public void showAlerta() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLAlerta.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            alertaController = loader.getController();
            alertaController.setStage(stage);
            alertaController.setMainAlerta(layout);
            criarPadraoModal("Alerta!!!", stage, layout);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start ALERTA", e);
        }
    }

    public void showCalendarioSemestral(String n) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLCalendarioSemestral.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            calendarioSemestralController = loader.getController();
            calendarioSemestralController.setStage(stage);
            calendarioSemestralController.setMainCalendario(layout);
            calendarioSemestralController.setFormatDate(n);
            criarPadraoModal("Calendário semestral", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start CALENDARIO SEMESTRAL", e);
        }
    }

    public void showFeriado() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFeriado.getCaminho()));
            VBox layout = (VBox) loader.load();
            feriadoController = loader.getController();
            feriadoController.setStage(stage);
            feriadoController.setMainFeriado(layout);
            criarPadraoModal("Cadastrar feriados", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start feriado", e);
        }
    }

    public void showFerramentaBD() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFerramentaBD.getCaminho()));
            VBox layout = (VBox) loader.load();
            ferramentaBDController = loader.getController();
            ferramentaBDController.setStage(stage);
            ferramentaBDController.setMainFerramentaBD(layout);
            criarPadraoModal("Ferramenta BD", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showFinanceiro(boolean isTabelaEmpresa, Empresa empresa, Agenda agenda) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFinanceiro.getCaminho()));
            VBox layout = (VBox) loader.load();
            financeiroController = loader.getController();
            financeiroController.setStage(stage);
            financeiroController.setMainFinanceiro(layout);
            if (isTabelaEmpresa) {
                financeiroController.setCampos(empresa);
            } else if (empresa != null && agenda != null) {
                financeiroController.setCampos(empresa, agenda);
            }
            criarPadraoModal("Cadastro de financeiro", stage, layout);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start financeiro", e);
        }
    }

    public void showLaudo(ObservableList<String> files) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLLaudo.getCaminho()));
            VBox layout = (VBox) loader.load();
            laudoController = loader.getController();
            laudoController.setStage(stage);
            laudoController.setMain(layout);
            if (files != null) {
                laudoController.setFiles(files);
            }
            criarPadraoModal("Laudo", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showFerramentaLaudo() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFerramentaLaudo.getCaminho()));
            VBox layout = (VBox) loader.load();
            ferramentaLaudoController = loader.getController();
            ferramentaLaudoController.setStage(stage);
            ferramentaLaudoController.setMain((VBox) layout);
//            stage.setResizable(false);
//            stage.initStyle(StageStyle.UNDECORATED);
            criarPadraoModal("Laudo ferramenta", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start visualizar motivo", e);
        }
    }

    public void showMotivoReagendamento() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLMotivoReagendamento.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            motivoReagendamentoController = loader.getController();
            motivoReagendamentoController.setStage(stage);
            motivoReagendamentoController.setMainMotivo(layout);
            criarPadraoModal("Motivo", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start agenda", e);
        }
    }

    public void showFerramentaFinanceiro() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFerramentaFinanceiro.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            ferramentaFinanceiroController = loader.getController();
            ferramentaFinanceiroController.setStage(stage);
            ferramentaFinanceiroController.setPrincipal(layout);
            criarPadraoModal("Ferramenta Financeiro", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start parametro", e);
        }
    }

    public void showFerramentaEmail() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLFerramentaEmail.getCaminho()));
            VBox layout = (VBox) loader.load();
            ferramentaEmailController = loader.getController();
            ferramentaEmailController.setStage(stage);
            ferramentaEmailController.setPrincipal(layout);
            criarPadraoModal("Ferramenta E-mail", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }
    
    public void showEmail(Email email) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLEmail.getCaminho()));
            AnchorPane layout = (AnchorPane) loader.load();
            emailController = loader.getController();
            emailController.setStage(stage);
            emailController.setPrincipal(layout);
            emailController.setEmail(email);
            criarPadraoModal("E-mail", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e);
        }
    }

    public void showRelatorio() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLRelatorio.getCaminho()));
            VBox layout = (VBox) loader.load();
            relatorioController = loader.getController();
            relatorioController.setStage(stage);
            relatorioController.setMainRelatorio(layout);
            criarPadraoModal("Relatório", stage, layout);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start relatorio", e);
        }
    }

    public void showRelatorioFinanceiro(String titulo, String tipo) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLRelatorioFinanceiro.getCaminho()));
            VBox layout = (VBox) loader.load();
            relatorioFinanceiroController = loader.getController();
            relatorioFinanceiroController.setStage(stage);
            relatorioFinanceiroController.setMainRelatorioFinanceiro(layout);
            relatorioFinanceiroController.setCampos(titulo, tipo);
            criarPadraoModal(titulo, stage, layout);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start relatorio financeiro", e);
        }
    }

    public void showTabelaEmpresasHomologadas() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaEmpresasHomologadas.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            empresasHomologadasController = loader.getController();
            empresasHomologadasController.setStage(stage);
            empresasHomologadasController.setMainTbHomologadas(layout);
            criarPadraoModal("Tabela de empresas homologadas", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela empresa homologadas", e);
        }
    }

    public void showTabelaFeriado() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaFeriados.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaFeriadoController = loader.getController();
            tabelaFeriadoController.setStage(stage);
            tabelaFeriadoController.setMainTabelaFeriado(layout);
            criarPadraoModal("Tabela de feriado", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela feriado", e);
        }
    }

    public void showTabelaFinanceiro() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaFinanceiro.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaFinanceiroController = loader.getController();
            tabelaFinanceiroController.setStage(stage);
            tabelaFinanceiroController.setMainTabelaFinanceiro(layout);
            criarPadraoModal("Tabela financeiro", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela financeiro", e);
        }
    }

    public void showTabelaHistorico() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaHistorico.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaHistoricoController = loader.getController();
            tabelaHistoricoController.setStage(stage);
            tabelaHistoricoController.setMainTbHistorico(layout);
            criarPadraoModal("Tabela histórico", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela historico", e);
        }
    }

    public void showTabelaUsuario() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLTabelaUsuario.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            tabelaUsuarioController = loader.getController();
            tabelaUsuarioController.setStage(stage);
            tabelaUsuarioController.setMainTbUsuario(layout);
            criarPadraoModal("Tabela de usuário", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela usuario", e);
        }
    }

    public void showUsuario() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLUsuario.getCaminho()));
            VBox layout = (VBox) loader.load();
            usuarioController = loader.getController();
            usuarioController.setStage(stage);
            usuarioController.setMainUsuario(layout);
            criarPadraoModal("Cadastro de usuário", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start usuario", e);
        }
    }

    public void showVisualizadorMotivo(String motivo) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLVisualizadorMotivo.getCaminho()));
            BorderPane layout = (BorderPane) loader.load();
            visulizadorMotivoController = loader.getController();
            visulizadorMotivoController.setStage(stage);
            visulizadorMotivoController.setMainMotivo(layout);
            visulizadorMotivoController.setMotivo(motivo);
            criarPadraoModal("Motivo!!", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start visualizar motivo", e);
        }
    }

    public void showEscolherDocs() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLEscolherDosc.getCaminho()));
            VBox layout = (VBox) loader.load();
            escolherDocsController = loader.getController();
            System.out.println("escolhcer " + escolherDocsController);
            escolherDocsController.setStage(stage);
            escolherDocsController.setVbMain((VBox) layout);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            criarPadraoModal("Gerar docs", stage, layout);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start visualizar motivo", e);
        }
    }

    /**
     * Construir o palco e a cena padrão para BorderPane com Modal
     *
     * @param titulo
     * @param stage palco
     * @param layout BorderPane
     */
    private void criarPadraoModal(String titulo, Stage stage, Parent layout) {
        Scene scene = new Scene(layout);
        stage.setTitle(titulo);
        stage.initOwner(scene.getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.toFront();

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            }
        });
    }

    /**
     * Construir o palco e a cena padrão para BorderPane
     *
     * @param titulo
     * @param stage palco
     * @param layout BorderPane
     */
    private void criarPadrao(String titulo, Stage stage, Parent layout) {
        Scene scene = new Scene(layout);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.out.println("FECHAR PROGRAMA");
                if (JPA.em(false) != null) {
                    if (JPA.em(false).isOpen()) {
                        JPA.em(false).close();
                    }
                }
                JPA.getFactory().close();
                System.exit(0);
            }
        });
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public PopUp getPopUp() {
        return popUp;
    }

    public AlertaController getAlertaController() {
        return alertaController;
    }

    public VisualizadorMotivoController getVisulizadorMotivoController() {
        return visulizadorMotivoController;
    }

    public Boolean getReagendamento() {
        return reagendamento;
    }

    public void setReagendamento(Boolean reagendamento) {
        this.reagendamento = reagendamento;
    }

    public Boolean getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(Boolean cancelamento) {
        this.cancelamento = cancelamento;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getBDLOCAL() {
        return BDLOCAL;
    }

    public void setBDLOCAL(Boolean BDLOCAL) {
        this.BDLOCAL = BDLOCAL;
    }

    public Boolean getBDSERVIDOR() {
        return BDSERVIDOR;
    }

    public void setBDSERVIDOR(Boolean BDSERVIDOR) {
        this.BDSERVIDOR = BDSERVIDOR;
    }

    public LocalDate getDataSelecionada() {
        return dataSelecionada;
    }

    public void setDataSelecionada(LocalDate dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

    public Agenda getAgendaEncontrada() {
        return agendaEncontrada;
    }

    public void setAgendaEncontrada(Agenda agendaEncontrada) {
        this.agendaEncontrada = agendaEncontrada;
    }

    public Empresa getEmpresaEncontrada() {
        return empresaEncontrada;
    }

    public void setEmpresaEncontrada(Empresa empresaEncontrada) {
        this.empresaEncontrada = empresaEncontrada;
    }

    public RelatorioController getRelatorioController() {
        return relatorioController;
    }

    public Feriado getFeriadoEncontrado() {
        return feriadoEncontrado;
    }

    public void setFeriadoEncontrado(Feriado feriadoEncontrado) {
        this.feriadoEncontrado = feriadoEncontrado;
    }

    public Financeiro getFinanceiroEncontrada() {
        return financeiroEncontrada;
    }

    public void setFinanceiroEncontrada(Financeiro financeiroEncontrada) {
        this.financeiroEncontrada = financeiroEncontrada;
    }

    public Usuario getUsuarioEncontrada() {
        return usuarioEncontrada;
    }

    public void setUsuarioEncontrada(Usuario usuarioEncontrada) {
        this.usuarioEncontrada = usuarioEncontrada;
    }

    public LaudoController getLaudoController() {
        return laudoController;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public AgendarController getAgendaController() {
        return agendaController;
    }

}
