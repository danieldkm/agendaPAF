package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InformacoesController extends Application implements Initializable {

    public static Stage stageInfo;

    @Override
    public void start(Stage primaryStage) {

        stageInfo = primaryStage;
        stageInfo.initStyle(StageStyle.UNDECORATED);
        try {
            mainInformacao = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Informacoes.getCaminho()));
        } catch (IOException ex) {
            Logger.getLogger(InformacoesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(mainInformacao);
        stageInfo.setScene(scene);
        stageInfo.setTitle("Informações");
        stageInfo.setResizable(false);
//        stageInfo.initOwner(this.myParent);
        stageInfo.show();
        stageInfo.toFront();
//        stageInfo.getIcons().add(Controller.icoPAF);
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private BorderPane mainInformacao;
    @FXML
    private TextArea txtArea;

//    private ObservableList<Agenda> listaAgendas = Controller.getAgendasOrderBy();
    public static Date dataSelecionada;

}
