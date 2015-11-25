package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.service.UsuarioService;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class LoginController {

    public LoginController() {
    }

    @FXML
    public void initialize() {
        mensagem = new Mensagem(stage);
        sceneManager = SceneManager.getInstance();
        btnLocal.setTooltip(new Tooltip("BD Local"));
        btnServidor.setTooltip(new Tooltip("BD Servidor"));
        btnFerramenta.setTooltip(new Tooltip("Configurar conexão"));

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
                .toY(100)//5
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        scaleTransition2 = ScaleTransitionBuilder.create()
                .node(rect2)
                .duration(Duration.seconds(1))
                //                .toX(0)
                .toY(50)
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

        ImageView imgFerramenta = new ImageView("/image/ferramenta32px.png");
        ImageView imgLocal = new ImageView("/image/local32px.png");
        ImageView imgServidor = new ImageView("/image/servidor32px.png");

        btnFerramenta.setGraphic(imgFerramenta);
        btnLocal.setGraphic(imgLocal);
        btnServidor.setGraphic(imgServidor);

        logo.toBack();
    }

    @FXML
    private VBox MainLogin;
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
    @FXML
    private Label lblLogin;

    private Stage stage;
    private ScaleTransition scaleTransition1;
    private ScaleTransition scaleTransition2;
    private ParallelTransition pt;
    private boolean logando = false;
    private SceneManager sceneManager;
    private Mensagem mensagem;

    @FXML
    private void setOnActionBtnFerramenta() {
        Dialogos d = new Dialogos(stage);
        Pair<String, String> pair = d.login();
//        StaticBoolean.setSelectedLocal(false);
        if (isAutentic(pair.getKey(), pair.getValue())) {
            if (btnServidor.isSelected() || btnLocal.isSelected()) {
                if (btnLocal.isSelected()) {
//                    StaticBoolean.setSelectedLocal(true);
                    sceneManager.setBDLOCAL(Boolean.TRUE);
                } else {
//                    StaticBoolean.setSelectedLocal(false);
                    sceneManager.setBDSERVIDOR(Boolean.TRUE);
                }
                sceneManager.showFerramentaBD();
            } else {
                mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
            }
        } else {
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
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
        if (!logando) {
            logando = true;
            if (btnLocal.isSelected() || btnServidor.isSelected()) {
                logo.toFront();
                pt = new ParallelTransition(scaleTransition1, scaleTransition2);
                pt.play();
                new java.util.Timer()
                        .schedule(new java.util.TimerTask() {
                            @Override
                            public void run() {
                                pt.pause();
                                //aki come�a uma nova aplica��o para n�o ocorrer conflito entre as threads
                                //executa dpois de executar o timer
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        if (isAutentic(txtLogin.getText(), txtSenha.getText())) {
//                                                    SceneManager.getInstance().closeLogin();
                                            stage.close();
                                            sceneManager.showPrincipal();
//                                                    RunAnotherApp.runAnotherApp(PrincipalController.class);
                                        } else {
                                            voltarAnimacao();
                                            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
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
                mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
            }
        }
    }

    private boolean isAutentic(String login, String senha) {
        try {
            UsuarioService us = new UsuarioService();
            Usuario logar = us.login(login, senha);
            if (logar != null) {
                sceneManager.setUsuarioLogado(logar);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logando = false;
        return false;
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

    public void setStage(Stage rootStage) {
        this.stage = rootStage;
    }
//    private boolean verificarSincronismo(String nomeArquivo) {
//        boolean sincronizar = false;
//        try {
////            BufferedReader buf = new BufferedReader(new FileReader("conexao.txt"));
//            BufferedReader buf = new BufferedReader(new FileReader(nomeArquivo));
//            String line = buf.readLine();
//            JSONHuffman j = new JSONHuffman();
//            while (line != null) {
//                String a[] = line.split("[-]");
//                switch (a[0]) {
//                    case "sincronizacao":
//                        if (j.getFraseJSON(a[1].trim(), "sincronizacao", "sistema.json").equals("true")) {
//                            System.out.println("TRUE");
//                            sincronizar = true;
//                        } else {
//                            System.out.println("FALSE");
//                            sincronizar = false;
//                        }
//                }
//                line = buf.readLine();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//        return sincronizar;
//    }
//    public void stageLogin() {
//        stage.show();
//        pt.play();
//        logo.toBack();
//        voltarAnimacao();
//    }
//    private FXMLLoader loadController(String url) {
//        InputStream fxmlStream = null;
//        try {
//            fxmlStream = getClass().getResourceAsStream(url);
//            FXMLLoader loader = new FXMLLoader();
//            loader.load(fxmlStream);
//            if (loader.getController() == null) {
//                System.out.println("controlle is null");
//            }
//            return loader.load(fxmlStream);
////            return (Controller) loader.getController();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    private void iniciarLogin() {
//        try {
//            LoginController profile = (LoginController) replaceSceneContent(EnumCaminho.Login.getCaminho());
//        } catch (Exception e) {
//        }
//    }
//
//    private Initializable replaceSceneContent(String fxml) throws Exception {;
//        FXMLLoader loader = new FXMLLoader();
////        fxml = "fxml/" + fxml;
//        System.out.println("fxml aaa " + fxml);
//        InputStream in = LoginController.class
//                .getResourceAsStream(fxml);
//        loader.setBuilderFactory(
//                new JavaFXBuilderFactory());
//        loader.setLocation(LoginController.class
//                .getResource(fxml));
//        BorderPane page;
////        AnchorPane page;
//
//        try {
//            page = (BorderPane) loader.load(in);
////            page = (AnchorPane) loader.load(in);
//        } finally {
//            in.close();
//        }
//        Scene scene = new Scene(page);
//
//        stage.setScene(scene);
//
//        stage.sizeToScene();
//        return (Initializable) loader.getController();
//    }
//
}
