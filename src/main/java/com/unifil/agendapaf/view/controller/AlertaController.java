package com.unifil.agendapaf.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AlertaController {

    @FXML
    public void initialize() {
    }

    @FXML
    private BorderPane mainAlerta;
    @FXML
    private TextArea txtaTexto;

    private Stage stage;

    public void setMainAlerta(BorderPane mainAlerta) {
        this.mainAlerta = mainAlerta;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setTxtaTexto(String txtaTexto) {
        this.txtaTexto.setText(this.txtaTexto.getText() + txtaTexto);
    }

}
