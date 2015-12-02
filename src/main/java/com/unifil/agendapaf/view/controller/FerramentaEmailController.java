package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.aux.FerramentaEmail;
import com.unifil.agendapaf.util.Criptografia;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
        System.out.println("FerramentaEmail " + ferramentaEmail);
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

    @FXML
    private ImageView img;

    private Stage stage;
    private FerramentaEmail ferramentaEmail;
    private UtilFile utilFile;
    private Criptografia cifra;
    private Mensagem mensagem;
    private File fileImg;

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
                ferramentaEmail.setSenha(cifra.cifrarVigenere(txtSenha.getText()));
                if (fileImg != null) {
                    ferramentaEmail.setCaminhoImg(fileImg.getAbsolutePath());
                }
                utilFile.salvarArquivo(new File("FerramentaEmail.xml"), utilFile.marshal(ferramentaEmail));
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
            } catch (Exception e) {
                e.printStackTrace();
                mensagem.erro(EnumMensagem.ErroSalvar.getTitulo(), EnumMensagem.ErroSalvar.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e);
            }
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fileImg = fc.showOpenDialog(stage);
        if (img == null) {
            try {
                img = new ImageView(new Image(fileImg.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(FerramentaEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Image image = new Image(fileImg.toURI().toURL().toString());
                img.setFitWidth(image.getWidth());
                img.setFitHeight(image.getHeight());
                img.setImage(new Image(fileImg.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(FerramentaEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private boolean validarCampos() {
        if (txtEmail.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoEmailNulo.getMensagem());
            return false;
        } else if (txtHostname.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoHostNulo.getMensagem());
            return false;
        } else if (txtPortaSMTP.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoSMTPNulo.getMensagem());
            return false;
        } else if (txtSenha.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoSenhaNulo.getMensagem());
            return false;
        } else if (txtRepetirSenha.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoRepetirSenhaNulo.getMensagem());
            return false;
        }
        if (!txtSenha.getText().equals(txtRepetirSenha.getText())) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.FerramentaEmailAvisoSenhaDiferente.getMensagem());
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
        txtRepetirSenha.setText(cifra.decifrarVigenere(ferramentaEmail.getSenha()));
        txtSenha.setText(cifra.decifrarVigenere(ferramentaEmail.getSenha()));
        if (ferramentaEmail.getCaminhoImg() != null) {
            if (!ferramentaEmail.getCaminhoImg().equals("")) {
                fileImg = new File(ferramentaEmail.getCaminhoImg());
                try {
                    Image image = new Image(fileImg.toURI().toURL().toString());
                    img.setFitWidth(image.getWidth());
                    img.setFitHeight(image.getHeight());
                    img.setImage(new Image(fileImg.toURI().toURL().toString()));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FerramentaEmailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
