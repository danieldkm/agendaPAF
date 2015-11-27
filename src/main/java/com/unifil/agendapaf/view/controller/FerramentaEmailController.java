package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.aux.FerramentaEmail;
import com.unifil.agendapaf.util.Criptografia;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class FerramentaEmailController {

    @FXML
    private void initialize() {
        utilFile = new UtilFile();
        cifra = new Criptografia();
        mensagem = new Mensagem(stage);
        MaskFieldUtil.numericField(txtPortaSMTP);
        ferramentaEmail = (FerramentaEmail) utilFile.unmarshalFromFile(FerramentaEmail.class, "FerramentaEmail.xml");
        setCampos(ferramentaEmail);
    }

    @FXML
    private Label lblAgenda;

    @FXML
    private TextField txtHostname;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPortaSMTP;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtRepetirSenha;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private VBox principal;

    private Stage stage;
    private FerramentaEmail ferramentaEmail;
    private UtilFile utilFile;
    private Criptografia cifra;
    private final String chave = "pafecf7326";
    private Mensagem mensagem;

    @FXML
    void onActionBtnCancelar(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onActionBtnSalvar(ActionEvent event) {
        if (validarCampos()) {
            try {
                ferramentaEmail = new FerramentaEmail();
                ferramentaEmail.setEmail(txtEmail.getText());
                ferramentaEmail.setHostName(txtHostname.getText());
                ferramentaEmail.setPortaSMTP(Integer.parseInt(txtPortaSMTP.getText()));
                ferramentaEmail.setSenha(cifra.cifrarVigenere(txtSenha.getText(), chave));
                utilFile.salvarArquivo(new File("FerramentaEmail.xml"), utilFile.marshal(ferramentaEmail));
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
            } catch (Exception e) {
                e.printStackTrace();
                mensagem.erro(EnumMensagem.ErroSalvar.getTitulo(), EnumMensagem.ErroSalvar.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e);
            }
        }
    }

    private boolean validarCampos() {
        if (txtEmail.getText().equals("")) {
            return false;
        } else if (txtHostname.getText().equals("")) {
            return false;
        } else if (txtPortaSMTP.getText().equals("")) {
            return false;
        } else if (txtSenha.getText().equals("")) {
            return false;
        }
        if (!txtSenha.getText().equals(txtRepetirSenha.getText())) {
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtHostname.setText("");
        txtPortaSMTP.setText("");
        txtSenha.setText("");
        txtRepetirSenha.setText("");
        txtEmail.setText("");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPrincipal(VBox layout) {
        this.principal = layout;
    }

    private void setCampos(FerramentaEmail ferramentaEmail) {
        txtEmail.setText(ferramentaEmail.getEmail());
        txtHostname.setText(ferramentaEmail.getHostName());
        txtPortaSMTP.setText(ferramentaEmail.getPortaSMTP() + "");
        txtRepetirSenha.setText(cifra.decifrarVigenere(ferramentaEmail.getSenha(), chave));
        txtSenha.setText(cifra.decifrarVigenere(ferramentaEmail.getSenha(), chave));
    }
}
