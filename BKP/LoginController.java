package com.unifil.agendapaf;

import com.unifil.agendapaf.view.controller.*;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.huffman.JSONHuffman;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class LoginController extends Application implements Initializable {

    public static void main(String[] args) {
//        launch(args);
        Application.launch(LoginController.class, (java.lang.String[]) null);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLocal.setTooltip(new Tooltip("BD Local"));
        btnServidor.setTooltip(new Tooltip("BD Servidor"));
        btnFerramenta.setTooltip(new Tooltip("Configurar conexão"));
//        MainLogin.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent t) {
//                if (t.getCode() == KeyCode.ESCAPE) {
//                    stage.close();
//                }
//            }
//        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtLogin.requestFocus();
            }
        });
        scaleTransition1 = ScaleTransitionBuilder.create()
                .node(rect1)
                .duration(Duration.seconds(1))
                //                .toX(0)
                .toY(5)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        scaleTransition2 = ScaleTransitionBuilder.create()
                .node(rect2)
                .duration(Duration.seconds(1))
                //                .toX(0)
                .toY(7)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();

        txtSenha.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                btnLogar.arm();
                btnLogar.fire();
            }
        });

        ImageView imgFerramenta = new ImageView("/Imagem/ferramenta32px.png");
        ImageView imgLocal = new ImageView("/Imagem/local32px.png");
        ImageView imgServidor = new ImageView("/Imagem/servidor32px.png");

        btnFerramenta.setGraphic(imgFerramenta);
        btnLocal.setGraphic(imgLocal);
        btnServidor.setGraphic(imgServidor);

        logo.toBack();
        conectarComBD();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        try {
//            try {//TODO habilitar apos finalizar ;;
//                fxmlController.setLogs("LoginController");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            stage = primaryStage;
            mainLogin = FXMLLoader.load(LoginController.class.getResource(EnumCaminho.Login.getCaminho()));
//            iniciarLogin();
            Scene s = new Scene(mainLogin);
            stage.setScene(s);
            stage.setTitle("Agenda PAF-ECF");
            stage.setResizable(false);
            stage.show();
//            stage.toFront();
//            File exeFile = new File("");
//            System.out.println(exeFile.getAbsolutePath()+ "/agendaPAF_ECF.ico");
//            Controller.icoPAF = new Image(exeFile.getAbsolutePath() + "/agendaPAF_ECF.ico");
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//
//                @Override
//                public void handle(WindowEvent t) {
//                    try {
//                        System.out.println("Fechando conexao");
//                        if (Dao.getCon() != null) {
//                            Dao.getCon().close();
//                        }
//                        System.exit(0);
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            });
//            stage.setOnHidden(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent t) {
////                    System.out.println("HIDE");
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void iniciarSincrinizacaoBD() {
        try {
//            RunAnotherApp.runAnotherApp(SincronizarBDController.class);
//            SincronizarBDController profile = (SincronizarBDController) replaceSceneContent("/view/SincronizarBD.fxml");
        } catch (Exception e) {
        }
    }

    private static Stage stage;
    private ScaleTransition scaleTransition1;
    private ScaleTransition scaleTransition2;
    @FXML
    private VBox mainLogin;
    @FXML
    private Rectangle rect1;
    @FXML
    private Rectangle rect2;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private ImageView logo;
    @FXML
    private HBox hbImg;
    @FXML
    private ToggleButton btnLocal;
    @FXML
    private ToggleButton btnServidor;
    @FXML
    private Button btnFerramenta;
    private ParallelTransition pt;
    private FXMLController fxmlController = new FXMLController();
    private UtilDialog dialog = new UtilDialog();

    @FXML
    private void setOnActionBtnFerramenta() {
        Pair<String, String> pair = dialog.criarLoginDialog();
        StaticBoolean.setSelectedLocal(false);
        StaticBoolean.setLocal(false);
        StaticBoolean.setServidor(false);
        if (isAutentic(pair.getKey(), pair.getValue())) {
            if (btnServidor.isSelected() || btnLocal.isSelected()) {
                if (btnLocal.isSelected()) {
                    StaticBoolean.setSelectedLocal(true);
                    StaticBoolean.setLocal(true);
                } else {
                    StaticBoolean.setSelectedLocal(false);
                    StaticBoolean.setServidor(true);
                }
                RunAnotherApp.runAnotherApp(FerramentaBDController.class);
            } else {
                dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
            }
        } else {
            dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
        }
    }

    @FXML
    private void setOnActionToggleButtonLocal() {
        if (btnServidor.isSelected()) {
            btnServidor.setSelected(false);
        }
    }

    @FXML
    private void setOnActionToggleButtonServidor() {
        if (btnLocal.isSelected()) {
            btnLocal.setSelected(false);
        }
    }

    @FXML
    private void setOnActionBtnLogar() {
        conectarComBD();
        if (btnLocal.isSelected() || btnServidor.isSelected()) {

//            if (Dao.getCon() != null) {
            logo.toFront();
            pt = new ParallelTransition(scaleTransition1, scaleTransition2);
            pt.play();
            new java.util.Timer()
                    .schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    pt.pause();
                                    //aki come�a uma nova aplica��o para n�o ocorrer conflito entre as threads
                                    //executa dpois de executar o timer
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            if (isAutentic(txtLogin.getText(), txtSenha.getText())) {
                                                stage.close();
                                                RunAnotherApp.runAnotherApp(PrincipalController.class);
                                            } else {
                                                dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
                                                voltarAnimacao();
                                                logo.toBack();
                                            }
                                        }
                                    });
                                }
                            },
                            1000
                    );
//            }
        } else {
            dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
        }
    }

    private boolean isAutentic(String login, String senha) {
        try {
//            conectarComBD();
            for (Usuario u : Controller.getUsuarios()) {
                if (u.getNomeLogin().equals(login) && u.getSenha().equals(senha)) {
                    StaticObject.setUsuarioLogado(u);
                    System.out.println("autenticado! \nusuário logando: " + u.getNome());
                    System.out.println("Tipo do usuário " + u.getTipo());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void conectarComBD() {
//        if (btnLocal.isSelected()) {
//            Dao.setCon(ConexaoLocal.getInstance());
//            Controller.titulo = "Agenda PAF-ECF - BD Local";
//            if (Dao.getCon() == null) {
//                dialog.criarDialogWarning("Informa��o", "Informa��o do sistema", "Erro ao tentar estabelecer conex�o com BD Local");
//            }
//        } else if (btnServidor.isSelected()) {
//            Dao.setCon(Conexao.getInstance());
//            Controller.titulo = "Agenda PAF-ECF - BD Servidor";
//            if (Dao.getCon() == null) {
//                fxmlController.criarDialogWarning("Informa��o", "Informa��o do sistema", "Erro ao tentar estabelecer conex�o com BD servidor");
//            } else {
//                Controller.setIsVerificarScript(verificarSincronismo("sistema.txt"));
//                System.out.println("Controller.isVerificarScript() " + Controller.isVerificarScript());
//                if (Controller.isVerificarScript()) {
////            caso conectar com o bd do servidor, iremos conectar com o bd local
////            e retornar uma lista de script, caso a lista conter 1 ou mais linhas
////            iremos executar cada linha para refletir na bd do servidor
////            talves seja melhor melhorar esse condigo
////                System.out.println("listaScript size  " + a.size());
//                    ObservableList<Script> a = fxmlController.selectScript(ConexaoLocal.getInstance());
//                    if (a.size() > 0) {
//                        Optional<ButtonType> r = fxmlController.criarDialogConfirmacao("Informa��o", "Realizar sincroniza��o do BD LOCAL", "Tem certeza que deseja transferir os dados da BD local para o BD servidor?");
//                        if (r.get() == ButtonType.OK) {
//                            fxmlController.executarConteudoScript(a, Dao.getCon(), true);
//                        }
//                    }
//                }
//            }
//        }
    }

    private boolean verificarSincronismo(String nomeArquivo) {
        boolean sincronizar = false;
        try {
//            BufferedReader buf = new BufferedReader(new FileReader("conexao.txt"));
            BufferedReader buf = new BufferedReader(new FileReader(nomeArquivo));
            String line = buf.readLine();
            JSONHuffman j = new JSONHuffman();
            while (line != null) {
                String a[] = line.split("[-]");
                switch (a[0]) {
                    case "sincronizacao":
                        if (j.getFraseJSON(a[1].trim(), "sincronizacao", "sistema.json").equals("true")) {
                            System.out.println("TRUE");
                            sincronizar = true;
                        } else {
                            System.out.println("FALSE");
                            sincronizar = false;
                        }
                }
                line = buf.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return sincronizar;
    }

    public void hideStageLogin() {
        stage.hide();
    }

    public void stageLogin() {
//        limparCampos();
        stage.show();
        pt.play();
        logo.toBack();
        voltarAnimacao();
//        System.out.println(verificarConexao());
    }

    private void voltarAnimacao() {
        pt.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        pt.pause();
                    }
                },
                1000
        );
        limparCampos();
        txtLogin.requestFocus();
    }

    private void limparCampos() {
        txtLogin.setText("");
        txtSenha.setText("");

    }

    private FXMLLoader loadController(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            if (loader.getController() == null) {
                System.out.println("controlle is null");
            }
            return loader.load(fxmlStream);
//            return (Controller) loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void iniciarLogin() {
        try {
            LoginController profile = (LoginController) replaceSceneContent(EnumCaminho.Login.getCaminho());
        } catch (Exception e) {
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {;
        FXMLLoader loader = new FXMLLoader();
//        fxml = "fxml/" + fxml;
        System.out.println("fxml aaa " + fxml);
        InputStream in = LoginController.class
                .getResourceAsStream(fxml);
        loader.setBuilderFactory(
                new JavaFXBuilderFactory());
        loader.setLocation(LoginController.class
                .getResource(fxml));
        BorderPane page;
//        AnchorPane page;

        try {
            page = (BorderPane) loader.load(in);
//            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);

        stage.setScene(scene);

        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
