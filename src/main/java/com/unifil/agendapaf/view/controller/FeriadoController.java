package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.service.FeriadoService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class FeriadoController {

    @FXML
    public void initialize() {
        try {
            mensagem = new Mensagem(stage);
            if (SceneManager.getInstance().getFeriadoEncontrado() != null) {
                feriadoEncontrado = SceneManager.getInstance().getFeriadoEncontrado();
                SceneManager.getInstance().setFeriadoEncontrado(null);
                isUpdate = true;
                setCampos();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar feriado", e);
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

    private Stage stage;
    private boolean isUpdate = false;
    private Feriado feriadoEncontrado;
    private Mensagem mensagem;

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
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
            }
        } catch (Exception e) {
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e);
            e.printStackTrace();
            JPA.em(false).close();
        }

    }

    @FXML
    private void actionBtnDeletar() {
        try {
            if (feriadoEncontrado != null) {
                Dialogos d = new Dialogos(stage);
                Optional<ButtonType> result = d.confirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    FeriadoService fs = new FeriadoService();
                    fs.deletar(feriadoEncontrado);
                    JPA.em(false).close();
                    actionBtnLimpar();
                }
                StaticLista.setListaGlobalFeriado(Controller.getFeriados());
            } else {
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FeriadoErroNaoExiste.getMensagem());
            }
        } catch (Exception e) {
            JPA.em(false).close();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FeriadoErroTentarDeletar.getMensagem() + feriadoEncontrado.getId(), e);
            e.printStackTrace();
        }
    }

    @FXML
    private void actionBtnLimpar() {
        txtFeriado.setText("");
        dataFeriado.setValue(null);
        isUpdate = false;
        feriadoEncontrado = null;
        SceneManager.getInstance().setFeriadoEncontrado(null);
    }

    @FXML
    private void iniciarTabelaFeriado() {
        stage.close();
        SceneManager.getInstance().showTabelaFeriado();
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
            mensagem.aviso(EnumMensagem.Requer.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    public void setCampos() {
        txtFeriado.setText(feriadoEncontrado.getFeriado());
        dataFeriado.setValue(feriadoEncontrado.getData());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainFeriado(VBox mainFeriado) {
        this.mainFeriado = mainFeriado;

    }
}
