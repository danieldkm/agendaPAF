package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.service.FeriadoService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class FeriadoController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (utilDialog == null) {
                utilDialog = new UtilDialog();
            }

            mainFeriado.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            if (StaticObject.getFeriadoEncontrada() != null) {
                feriadoEncontrado = StaticObject.getFeriadoEncontrada();
                StaticObject.setFeriadoEncontrada(null);
                isUpdate = true;
                setCampos();
            }
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar feriado", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainFeriado = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.Feriado.getCaminho()));
            Scene scene = new Scene(mainFeriado);
            scene.getStylesheets().add(EnumCaminho.CSS.getCaminho());
            stage.setScene(scene);
            stage.setTitle("Cadastrar Feriados");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                }
            });
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    if (txtFeriado != null) {
                        actionBtnLimpar();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start feriado", e, "Exception:");
        }
    }

    @FXML
    private TextField txtFeriado;
    @FXML
    private VBox mainFeriado;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnDeletar;
    @FXML
    private DatePicker dataFeriado;

    private static Stage stage;
    private boolean isUpdate = false;
    private Feriado feriadoEncontrado;
    private UtilDialog utilDialog;

    @FXML
    private void actionBtnSalvar() {
        try {
            if (validarCampos()) {
                FeriadoService fs = new FeriadoService();
                Feriado f = new Feriado();
                f.setFeriado(txtFeriado.getText());
                f.setData(dataFeriado.getValue());
                if (isUpdate) {
                    f.setId(feriadoEncontrado.getId());
                    fs.editar(f);
                    JPA.em(false).close();
                } else {
                    fs.salvar(f);
                    JPA.em(false).close();
                }
                actionBtnLimpar();
                StaticLista.setListaGlobalFeriado(Controller.getFeriados());
                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
            }
        } catch (Exception e) {
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception");
            e.printStackTrace();
            JPA.em(false).close();
        }

    }

    @FXML
    private void actionBtnDeletar() {
        try {
            if (feriadoEncontrado != null) {
                Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    FeriadoService fs = new FeriadoService();
                    fs.deletar(feriadoEncontrado);
                    JPA.em(false).close();
                    actionBtnLimpar();
                }
                StaticLista.setListaGlobalFeriado(Controller.getFeriados());
            } else {
                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FeriadoErroNaoExisteEmpresa.getMensagem());
            }
        } catch (Exception e) {
            JPA.em(false).close();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FeriadoErroTentarDeletar.getMensagem() + feriadoEncontrado.getId(), e, "Exception");
            e.printStackTrace();
        }
    }

    @FXML
    private void actionBtnLimpar() {
        txtFeriado.setText("");
        dataFeriado.setValue(null);
        isUpdate = false;
        feriadoEncontrado = null;
        StaticObject.setFeriadoEncontrada(null);
    }

    @FXML
    private void iniciarTabelaFeriado() {
        stage.close();
        RunAnotherApp.runAnotherApp(TabelaFeriadoController.class);
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        String preencher = "";
        if (txtFeriado.getText().equals("")) {
            preencher += "Preencher o nome do feriado\n";
            validationSupport.registerValidator(txtFeriado, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (dataFeriado.getValue() == null) {
            preencher += "Preencher a data";
            validationSupport.registerValidator(txtFeriado, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (!ok) {
            utilDialog.criarDialogWarning(EnumMensagem.Requer.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    public void setCampos() {
        txtFeriado.setText(feriadoEncontrado.getFeriado());
        dataFeriado.setValue(feriadoEncontrado.getData());
    }
}
