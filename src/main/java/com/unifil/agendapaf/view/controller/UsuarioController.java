package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.service.UsuarioService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.time.LocalDate;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class UsuarioController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
            if (sceneManager.getUsuarioEncontrada() != null) {
                usuarioEncontrado = sceneManager.getUsuarioEncontrada();
                sceneManager.setUsuarioEncontrada(null);
                setCampos();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar usuário", e, "Exception:");
        }
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        try {
//            stage = primaryStage;
//            mainUsuario = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Usuario.getCaminho()));
//            Scene scene = new Scene(mainUsuario);
//            stage.setScene(scene);
//            stage.setTitle("Cadastro de Usuário");
////        stage.setResizable(false);
////        stage.initOwner(this.myParent);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
//            stage.toFront();
////            stage.getIcons().add(Controller.icoPAF);
//            stage.setOnHidden(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent t) {
////                TabelaEmpresaController.isEmpresa = false;
//                }
//            });
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//
//                @Override
//                public void handle(WindowEvent event) {
//                    if (txtLogin != null) {
//                        resetarCampos();
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start usuario", e, "Exception:");
//        }
//    }
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private VBox mainUsuario;
    @FXML
    private ComboBox cbTipo;

    private Usuario u;
    private static Stage stage;
    private SceneManager sceneManager;
    private Usuario usuarioEncontrado;

    private boolean isUpdate = false;

    @FXML
    private void onActionBtnDeletar() {
        try {
            if (usuarioEncontrado != null) {
                Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.UsuarioPerguntaDeletar.getTitulo(), EnumMensagem.UsuarioPerguntaDeletar.getSubTitulo(), EnumMensagem.UsuarioPerguntaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    UsuarioService us = new UsuarioService();
                    us.deletar(usuarioEncontrado);
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                    resetarCampos();
                    JPA.em(false).close();
                    StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
                }
            } else {
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.UsuarioErroNaoExiste.getMensagem());
            }
        } catch (Exception e) {
            JPA.em(false).close();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao tentar deletar usuario: " + usuarioEncontrado.getId(), e, "Exception");
            e.printStackTrace();
        }
    }

    @FXML
    private void actionBtnSalvar() {
        if (isUpdate) {
            actionBtnAlterar();
        } else {
            try {
                if (validarCampos()) {
                    u = new Usuario();
                    u.setNomeLogin(txtLogin.getText());
                    u.setNome(txtNome.getText());
                    u.setSenha(txtSenha.getText());
                    u.setDataCadastro(LocalDate.now());
                    u.setTipo(cbTipo.getValue() + "");
                    u.setEmail(txtEmail.getText());

                    UsuarioService us = new UsuarioService();
                    us.salvar(u);
                    JPA.em(false).close();
                    resetarCampos();
                    StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                }
            } catch (Exception e) {
                JPA.em(false).close();
                UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception:");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void actionBtnCancelar() {
        resetarCampos();
    }

    @FXML
    protected void iniciarTabelaUsuario() {
        stage.close();
        sceneManager.showTabelaUsuario();
    }

    private void actionBtnAlterar() {
        try {
            if (validarCampos()) {
                Usuario u = new Usuario();
                u.setId(usuarioEncontrado.getId());
                u.setNome(txtNome.getText());
                u.setNomeLogin(txtLogin.getText());
                u.setSenha(txtSenha.getText());
                u.setTipo(cbTipo.getValue() + "");
                u.setDataCadastro(usuarioEncontrado.getDataCadastro());
                u.setEmail(txtEmail.getText());

                UsuarioService us = new UsuarioService();
                us.editar(u);
                JPA.em(false).close();
                resetarCampos();
                StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
            }
        } catch (Exception e) {
            JPA.em(false).close();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroAtualizar.getMensagem(), e, "Exception:");
            e.printStackTrace();
        }
    }

    private void resetarCampos() {
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtEmail.setText("");
        usuarioEncontrado = null;
        sceneManager.setUsuarioEncontrada(null);
        isUpdate = false;
    }

    private boolean validarCampos() {
        boolean ok = true;
        String preencher = "";
        if (txtLogin.getText().equals("")) {
            preencher = alertaLogin(preencher);
            ok = false;
        }
        if (txtNome.getText().equals("")) {
            preencher = alertaNome(preencher);
            ok = false;
        }
        if (txtSenha.getText().equals("")) {
            preencher = alertaSenha(preencher);
            ok = false;
        }
        if (cbTipo.getValue() == null) {
            preencher = alertaCbTipo(preencher);
            ok = false;
        }
        if (txtEmail.getText() == null) {
            preencher = alertaEmail(preencher);
            ok = false;
        } else {
            if (txtEmail.getText().equals("")) {
                preencher = alertaEmail(preencher);
                ok = false;
            }
        }
        if (!ok) {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    private String alertaLogin(String preencher) {
        ValidationSupport validationSupport = new ValidationSupport();
        preencher += EnumMensagem.UsuarioInformarLogin.getMensagem() + "\n";
        validationSupport.registerValidator(txtLogin, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
        return preencher;
    }

    private String alertaNome(String preencher) {
        ValidationSupport validationSupport = new ValidationSupport();
        preencher += EnumMensagem.UsuarioInformarNome.getMensagem() + "\n";
        validationSupport.registerValidator(txtNome, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
        return preencher;
    }

    private String alertaSenha(String preencher) {
        ValidationSupport validationSupport = new ValidationSupport();
        preencher += EnumMensagem.UsuarioInformarSenha.getMensagem() + "\n";
        validationSupport.registerValidator(txtSenha, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
        return preencher;
    }

    private String alertaCbTipo(String preencher) {
        ValidationSupport validationSupport = new ValidationSupport();
        preencher += EnumMensagem.UsuarioInformarComboBoxTipo.getMensagem() + "\n";
        validationSupport.registerValidator(cbTipo, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
        return preencher;
    }

    private String alertaEmail(String preencher) {
        ValidationSupport validationSupport = new ValidationSupport();
        preencher += EnumMensagem.UsuarioInformarEmail.getMensagem() + "\n";
        validationSupport.registerValidator(txtEmail, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
        return preencher;
    }

    public void setCampos() {
        txtLogin.setText(usuarioEncontrado.getNomeLogin());
        txtNome.setText(usuarioEncontrado.getNome());
        txtSenha.setText(usuarioEncontrado.getSenha());
        txtEmail.setText(usuarioEncontrado.getEmail());
        cbTipo.getSelectionModel().selectFirst();
        isUpdate = true;
        for (int i = 0; i < 2; i++) {
            if (!cbTipo.getValue().equals(usuarioEncontrado.getTipo())) {
                cbTipo.getSelectionModel().selectNext();
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainUsuario(VBox mainUsuario) {
        this.mainUsuario = mainUsuario;
    }

}
