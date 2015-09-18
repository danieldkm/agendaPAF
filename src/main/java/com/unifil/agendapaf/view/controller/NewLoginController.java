package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.animations.FadeInLeftTransition;
import com.unifil.agendapaf.animations.FadeInLeftTransition1;
import com.unifil.agendapaf.animations.FadeInRightTransition;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.service.UsuarioService;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class NewLoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text lblRudyCom;
    @FXML
    private Label lblClose;
    @FXML
    private ToggleButton btnLocal;
    @FXML
    private ToggleButton btnServidor;
    @FXML
    private Button btnFerramenta;

    private Stage stage;
    private boolean logando = false;
    private SceneManager sceneManager;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        sceneManager = SceneManager.getInstance();
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            ImageView imgFerramenta = new ImageView("/image/ferramenta32px.png");
            ImageView imgLocal = new ImageView("/image/local32px.png");
            ImageView imgServidor = new ImageView("/image/servidor32px.png");

            btnFerramenta.setGraphic(imgFerramenta);
            btnLocal.setGraphic(imgLocal);
            btnServidor.setGraphic(imgServidor);
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });

            txtPassword.setOnAction((ActionEvent event) -> {
                btnLogin.arm();
                btnLogin.fire();
            });
        });
    }

    @FXML
    private void setOnActionBtnLogar() {
        if (!logando) {
            logando = true;
            if (btnLocal.isSelected() || btnServidor.isSelected()) {
                if (isAutentic(txtUsername.getText(), txtPassword.getText())) {
                    stage.close();
                    sceneManager.showPrincipal();
                } else {
                    UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
                }
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
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

    private void limparCampos() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public void setStage(Stage rootStage) {
        this.stage = rootStage;
    }

    @FXML
    private void setOnActionBtnFerramenta() {
        Pair<String, String> pair = UtilDialog.criarLoginDialog();
        if (isAutentic(pair.getKey(), pair.getValue())) {
            if (btnServidor.isSelected() || btnLocal.isSelected()) {
                if (btnLocal.isSelected()) {
                    sceneManager.setBDLOCAL(Boolean.TRUE);
                } else {
                    sceneManager.setBDSERVIDOR(Boolean.TRUE);
                }
                sceneManager.showFerramentaBD();
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginBDSelecionado.getMensagem());
            }
        } else {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LoginIncorreto.getMensagem());
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
}
