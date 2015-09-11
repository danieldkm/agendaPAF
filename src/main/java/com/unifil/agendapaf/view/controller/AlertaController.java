package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AlertaController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        mainAlerta = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.Alerta.getCaminho()));
        Scene scene = new Scene(mainAlerta);
        stage.setScene(scene);
        stage.setTitle("Alerta!!!");
        stage.setResizable(false);
//        stage.initOwner(this.myParent);
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
//        stage.getIcons().add(Controller.icoPAF);
        stage.toFront();
    }

    @FXML
    private BorderPane mainAlerta;
    @FXML
    public static TextArea txtaTexto;

    public static Stage stage;

}
