package com.unifil.agendapaf.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VisualizadorMotivoController {

    @FXML
    public void initialize() {
    }

    @FXML
    private BorderPane mainMotivo;
    @FXML
    public TextArea txtMotivo;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setMainMotivo(BorderPane mainMotivo) {
        this.mainMotivo = mainMotivo;
    }

    public void setMotivo(String motivo) {
        txtMotivo.setText(motivo);
    }

}
