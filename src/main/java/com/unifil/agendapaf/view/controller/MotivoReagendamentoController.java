package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MotivoReagendamentoController {

    @FXML
    public void initialize() {
        mensagem = new Mensagem(stage);
        sceneManager = SceneManager.getInstance();
    }

    @FXML
    private BorderPane mainMotivo;
    @FXML
    private TextArea txtaTexto;

    private Stage stage;
    private SceneManager sceneManager;
    private Mensagem mensagem;

    @FXML
    private void actionBtnContinuar() {
        if (!txtaTexto.getText().equals("")) {
//            StaticString.setTxtMotivoReagendamento(txtaTexto.getText());
            if (sceneManager.getCancelamento()) {
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.MotivoReagendamentoParaCancelar.getMensagem());
            }
            sceneManager.showAgenda(txtaTexto.getText());
            stage.close();
        } else {
            mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.MotivoReagendamentoPreencherMotivo.getMensagem());
        }
    }

    @FXML
    private void actionBtnCancelar() {
        sceneManager.setCancelamento(Boolean.FALSE);
        sceneManager.setReagendamento(Boolean.FALSE);
        sceneManager.setAgendaEncontrada(null);
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainMotivo(BorderPane mainMotivo) {
        this.mainMotivo = mainMotivo;
    }

}
